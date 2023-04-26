package tk.camikase.TwitchIntegration.handlers;

import java.io.IOException;
import java.util.List;
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
import tk.camikase.TwitchIntegration.storage.controller.MemberDataController;
import tk.camikase.TwitchIntegration.storage.model.MemberModel;

public final class MyEventHandler implements Listener {
    private final TwitchIntegrationPlugin twitchIntegrationPlugin;

    private final LuckPermsBridge luckPermsBridge;
    private final TwitchBridge twitchBridge;
    private final MemberDataController memberDataController;

    public MyEventHandler(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        this.twitchIntegrationPlugin = twitchIntegrationPlugin;

        luckPermsBridge = twitchIntegrationPlugin.getLuckPermsBridge();
        twitchBridge = twitchIntegrationPlugin.getTwitchBridge();

        memberDataController = twitchIntegrationPlugin.getStorageHelper().getMemberDataController();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(final PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(twitchIntegrationPlugin, () -> {
            final UUID uuid = event.getPlayer().getUniqueId();

            final List<MemberModel> members = memberDataController.getMembersByUniqueId(uuid);
            if (members.size() == 0) {
                twitchIntegrationPlugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + uuid.toString() + " no tiene cuenta linkeada.");

                if (luckPermsBridge.isPlayerInGroup(event.getPlayer(), "suscriptor")) {
                    event.getPlayer().sendMessage(ChatColor.YELLOW
                            + "No tienes ninguna cuenta linkeada, para recuperar el rol de sub ejecuta el siguiente comando y sigue las instrucciones: "
                            + ChatColor.BLUE + "/claimsub");
                }

                luckPermsBridge.removePermission(uuid, "group.suscriptor");
                return;
            }

            try {
                if (twitchBridge.isSub(members.get(0).twitchId, twitchIntegrationPlugin.getPluginConfig().getBroadcasterId())) {
                    luckPermsBridge.addPermission(uuid, "group.suscriptor");
                    twitchIntegrationPlugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + uuid.toString() + " es sub.");

                    return;
                }

                luckPermsBridge.removePermission(uuid, "group.suscriptor");

                twitchIntegrationPlugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + uuid.toString() + " no es sub.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
