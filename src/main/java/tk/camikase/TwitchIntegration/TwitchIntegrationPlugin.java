package tk.camikase.TwitchIntegration;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import tk.camikase.TwitchIntegration.config.PluginConfig;
import tk.camikase.TwitchIntegration.database.DatabaseHelper;
import tk.camikase.TwitchIntegration.database.IDatabaseHelper;
import tk.camikase.TwitchIntegration.https.MyHttpsServer;
import tk.camikase.TwitchIntegration.bridges.LuckPermsBridge;
import tk.camikase.TwitchIntegration.bridges.PlayerKitsBridge;
import tk.camikase.TwitchIntegration.bridges.TwitchBridge;
import tk.camikase.TwitchIntegration.executors.ClaimSubExecutor;
import tk.camikase.TwitchIntegration.handlers.MyEventHandler;
import tk.camikase.TwitchIntegration.handlers.WebhookHandler;
import tk.camikase.TwitchIntegration.storage.IStorageHelper;
import tk.camikase.TwitchIntegration.storage.StorageHelper;
import tk.camikase.TwitchIntegration.storage.controller.MemberDataController;
import tk.camikase.TwitchIntegration.storage.model.MemberModel;
import tk.camikase.TwitchIntegration.utils.CryptoUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@Getter(AccessLevel.PUBLIC)
public final class TwitchIntegrationPlugin extends JavaPlugin {
    private static TwitchIntegrationPlugin twitchIntegrationPlugin;

    private PluginConfig pluginConfig;

    private ListeningExecutorService executorService;

    private IDatabaseHelper databaseHelper;
    private IStorageHelper storageHelper;

    private TwitchBridge twitchBridge;
    private LuckPermsBridge luckPermsBridge;
    private PlayerKitsBridge playerKitsBridge;
    private MyHttpsServer httpsServer;
    private WebhookHandler webhookHandler;

    private final Map<String, UUID> stateMap = new ConcurrentHashMap<>();

    @Override
    public void onLoad() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        twitchIntegrationPlugin = this;

        final FileConfiguration config = getConfig();

        pluginConfig = new PluginConfig(
                config.getString("database.address"),
                config.getInt("database.port"),
                config.getString("database.database"),
                config.getString("database.username"),
                config.getString("database.password"),
                config.getString("twitch.broadcaster"),
                config.getInt("http.port")
        );

        playerKitsBridge = new PlayerKitsBridge(this);
        luckPermsBridge = new LuckPermsBridge();
        twitchBridge = new TwitchBridge();

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.init();

        storageHelper = new StorageHelper(this);

        httpsServer = new MyHttpsServer(this);

        webhookHandler = new WebhookHandler(this, CryptoUtil.randomString(32, "0123456789abcdef"));

        httpsServer.addContext("/link", webhookHandler);

        getCommand("claimsub").setExecutor(new ClaimSubExecutor(this));

        getServer().getPluginManager().registerEvents(new MyEventHandler(this), this);
    }

    @Override
    public void onDisable() {
        webhookHandler = null;
        httpsServer = null;

        playerKitsBridge = null;
        luckPermsBridge = null;
        twitchBridge = null;

        storageHelper = null;

        databaseHelper.shutdown();
        databaseHelper = null;

        executorService = null;
        twitchIntegrationPlugin = null;
    }

    // Is this being used?
    public void linkAccounts(String twitchId, String state) {
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            final UUID uuid = stateMap.remove(state);

            final ConsoleCommandSender logger = getServer().getConsoleSender();

            if (uuid == null) {
                logger.sendMessage(ChatColor.RED + "Invalid state");
                return;
            }

            final MemberDataController dataController = storageHelper.getMemberDataController();
            final List<MemberModel> members = dataController.getMembersByTwitchId(twitchId);
            long max = playerKitsBridge.getRawCooldown(uuid, "Sub");

            for (final MemberModel member : members) {
                long cd = playerKitsBridge.getRawCooldown(member.getUuid(), "Sub");
                max = Math.max(cd, max);
            }

            playerKitsBridge.setRawCooldown(uuid, "Sub", max);

            try {
                if (twitchBridge.isSub(twitchId, getConfig().getString("BROADCASTER_ID"))) {
                    luckPermsBridge.addPermission(uuid, "group.suscriptor");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            dataController.unlinkAccount(twitchId);
            dataController.linkAccount(uuid, twitchId);
        });
    }
}
