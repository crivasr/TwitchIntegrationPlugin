package tk.camikase.TwitchIntegration.link;

import org.bukkit.Bukkit;
import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.bridges.LuckPermsBridge;
import tk.camikase.TwitchIntegration.bridges.PlayerKitsBridge;
import tk.camikase.TwitchIntegration.bridges.TwitchBridge;
import tk.camikase.TwitchIntegration.storage.controller.MemberDataController;
import tk.camikase.TwitchIntegration.storage.model.MemberModel;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;


public final class AccountLinkHelper implements IAccountLinkHelper {
    private final TwitchIntegrationPlugin twitchIntegrationPlugin;

    private final MemberDataController dataController;

    private final TwitchBridge twitchBridge;
    private final LuckPermsBridge luckPermsBridge;
    private final PlayerKitsBridge playerKitsBridge;

    public AccountLinkHelper(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        this.twitchIntegrationPlugin = twitchIntegrationPlugin;

        dataController = twitchIntegrationPlugin.getStorageHelper().getMemberDataController();

        twitchBridge = twitchIntegrationPlugin.getTwitchBridge();
        luckPermsBridge = twitchIntegrationPlugin.getLuckPermsBridge();
        playerKitsBridge = twitchIntegrationPlugin.getPlayerKitsBridge();
    }

    @Override
    public void linkAccount(final String twitchId, final String state) {
        Bukkit.getScheduler().runTaskAsynchronously(twitchIntegrationPlugin, () -> {
            final UUID uuid = twitchIntegrationPlugin.getStateMap().remove(state);

            if (uuid == null) {
                twitchIntegrationPlugin.getLogger().log(Level.SEVERE, String.format("!! Invalid state for %s", twitchId));
                return;
            }

            final List<MemberModel> members = dataController.getMembersByTwitchId(twitchId);
            long max = playerKitsBridge.getCooldown(uuid, "Sub");

            for (final MemberModel member : members) {
                long cd = playerKitsBridge.getCooldown(member.getUuid(), "Sub");
                max = Math.max(cd, max);
            }

            playerKitsBridge.setRawCooldown(uuid, "Sub", max);

            try {
                if (twitchBridge.isSub(twitchId, twitchIntegrationPlugin.getPluginConfig().getBroadcasterId())) {
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
