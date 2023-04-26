package tk.camikase.TwitchIntegration;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import lombok.AccessLevel;
import lombok.Getter;

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
import tk.camikase.TwitchIntegration.link.AccountLinkHelper;
import tk.camikase.TwitchIntegration.link.IAccountLinkHelper;
import tk.camikase.TwitchIntegration.storage.IStorageHelper;
import tk.camikase.TwitchIntegration.storage.StorageHelper;
import tk.camikase.TwitchIntegration.utils.CryptoUtil;

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

    private IAccountLinkHelper accountLinkHelper;

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

        accountLinkHelper = new AccountLinkHelper(this);

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
}
