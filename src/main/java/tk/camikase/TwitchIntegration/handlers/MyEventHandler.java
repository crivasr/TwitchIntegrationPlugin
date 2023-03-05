package tk.camikase.TwitchIntegration.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.bridges.LuckPermsBridge;
import tk.camikase.TwitchIntegration.bridges.TwitchBridge;
import tk.camikase.TwitchIntegration.database.Member;
import tk.camikase.TwitchIntegration.database.databaseManager;

public class MyEventHandler implements Listener {
    private TwitchIntegrationPlugin plugin = null;

    private LuckPermsBridge luckPermsBridge = null;
    private TwitchBridge twitchBridge = null;
    private databaseManager dbManager = null;

    public MyEventHandler(TwitchIntegrationPlugin plugin) {
        this.plugin = plugin;

        luckPermsBridge = plugin.getLuckPermsBridge();
        twitchBridge = plugin.getTwitchBridge();
        dbManager = plugin.getdbManager();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                UUID minecraftUUID = event.getPlayer().getUniqueId();

                if (minecraftUUID == null) {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "null uuid");
                    return;
                }

                ArrayList<Member> members = dbManager.getByMCUUID(minecraftUUID);
                if (members.size() == 0) {
                    plugin.getServer().getConsoleSender()
                            .sendMessage(ChatColor.YELLOW + minecraftUUID.toString() + " no tiene cuenta linkeada.");

                    if (luckPermsBridge.isPlayerInGroup(event.getPlayer(), "suscriptor")) {
                        event.getPlayer().sendMessage(ChatColor.YELLOW
                                + "No tienes ninguna cuenta linkeada, para recuperar el rol de sub ejecuta el siguiente comando y sigue las instrucciones: "
                                + ChatColor.BLUE + "/claimsub");
                    }
                    luckPermsBridge.removePermission(minecraftUUID, "group.suscriptor");
                    return;
                }
                try {
                    if (twitchBridge.isSub(members.get(0).twitchId, plugin.getConfig().getString("BROADCASTER_ID"))) {
                        luckPermsBridge.addPermission(minecraftUUID, "group.suscriptor");
                        plugin.getServer().getConsoleSender()
                                .sendMessage(ChatColor.GREEN + minecraftUUID.toString() + " es sub.");
                        return;
                    }

                    luckPermsBridge.removePermission(minecraftUUID, "group.suscriptor");
                    plugin.getServer().getConsoleSender()
                            .sendMessage(ChatColor.YELLOW + minecraftUUID.toString() + " no es sub.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
