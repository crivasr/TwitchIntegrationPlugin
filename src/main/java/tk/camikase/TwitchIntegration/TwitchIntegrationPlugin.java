package tk.camikase.TwitchIntegration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import tk.camikase.TwitchIntegration.HTTPS.MyHttpsServer;
import tk.camikase.TwitchIntegration.bridges.LuckPermsBridge;
import tk.camikase.TwitchIntegration.bridges.PlayerKitsBridge;
import tk.camikase.TwitchIntegration.bridges.TwitchBridge;
import tk.camikase.TwitchIntegration.database.Member;
import tk.camikase.TwitchIntegration.database.databaseManager;
import tk.camikase.TwitchIntegration.executors.ClaimSubExecutor;
import tk.camikase.TwitchIntegration.handlers.MyEventHandler;
import tk.camikase.TwitchIntegration.handlers.WebhookHandler;
import tk.camikase.TwitchIntegration.utils.Crypto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TwitchIntegrationPlugin extends JavaPlugin implements Listener {

    private static TwitchBridge twitchBridge = null;
    private static LuckPermsBridge luckPermsBridge = null;
    private static PlayerKitsBridge playerKitsBridge = null;
    private static TwitchIntegrationPlugin instance = null;
    private static databaseManager dbManager = null;
    private static MyHttpsServer httpsServer = null;
    private static WebhookHandler webhookHandler = null;

    private static Map<String, UUID> stateMap = new HashMap<String, UUID>();

    @Override
    public void onEnable() {
        getLogger().info("onEnable has been invoked!");
        instance = this;

        File dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        saveDefaultConfig();
        FileConfiguration config = getConfig();

        String JDBCUrl = config.getString("JDBC_URL");
        String username = config.getString("DB_USERNAME");
        String password = config.getString("DB_PASSWORD");
        int port = config.getInt("PORT");

        luckPermsBridge = new LuckPermsBridge();
        playerKitsBridge = new PlayerKitsBridge();
        twitchBridge = new TwitchBridge();
        dbManager = new databaseManager(JDBCUrl, username, password);
        httpsServer = new MyHttpsServer(port);

        webhookHandler = new WebhookHandler(Crypto.randomString(32, "0123456789abcdef"));

        httpsServer.addContext("/link", webhookHandler);

        dbManager.createTable();

        this.getCommand("claimsub").setExecutor(new ClaimSubExecutor(this));
        getServer().getPluginManager().registerEvents(new MyEventHandler(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

    public static TwitchIntegrationPlugin getInstance() {
        return instance;
    }

    public Map<String, UUID> getStateMap() {
        return stateMap;
    }

    public LuckPermsBridge getLuckPermsBridge() {
        return luckPermsBridge;
    }

    public PlayerKitsBridge getPlayerKitsBridge() {
        return playerKitsBridge;
    }

    public TwitchBridge getTwitchBridge() {
        return twitchBridge;
    }

    public databaseManager getdbManager() {
        return dbManager;
    }

    public MyHttpsServer getHttpsServer() {
        return httpsServer;
    }

    public WebhookHandler getWebhookHandler() {
        return webhookHandler;
    }

    public void linkAccounts(String twitchId, String state) {
        Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                UUID minecraftUUID = stateMap.remove(state);

                ConsoleCommandSender logger = getServer().getConsoleSender();
                if (minecraftUUID == null) {
                    logger.sendMessage(ChatColor.RED + "Invalid state");
                    return;
                }

                ArrayList<Member> members = dbManager.getByTwitchId(twitchId);
                long max = playerKitsBridge.getRawCooldown(minecraftUUID, "Sub");

                for (int i = 0; i < members.size(); i++) {
                    long cd = playerKitsBridge.getRawCooldown(members.get(i).minecraftUUID, "Sub");
                    max = cd > max ? cd : max;
                }

                playerKitsBridge.setRawCooldown(minecraftUUID, "Sub", max);

                try {
                    if (twitchBridge.isSub(twitchId, getConfig().getString("BROADCASTER_ID"))) {
                        luckPermsBridge.addPermission(minecraftUUID, "group.suscriptor");
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                dbManager.unlinkAll(twitchId);
                dbManager.linkAccounts(twitchId, minecraftUUID);
            }
        });
    }
}
