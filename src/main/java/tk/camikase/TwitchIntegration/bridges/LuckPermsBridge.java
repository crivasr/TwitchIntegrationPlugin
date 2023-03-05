package tk.camikase.TwitchIntegration.bridges;

import java.util.UUID;

import org.bukkit.entity.Player;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;

public class LuckPermsBridge {
    private LuckPerms luckPermsApi = null;

    public LuckPermsBridge() {
        luckPermsApi = LuckPermsProvider.get();
    }

    public void addPermission(UUID uuid, String permission) {
        luckPermsApi.getUserManager().modifyUser(uuid, user -> {
            user.data().add(Node.builder(permission).build());
        });
    }

    public void removePermission(UUID uuid, String permission) {
        luckPermsApi.getUserManager().modifyUser(uuid, user -> {
            user.data().remove(Node.builder(permission).build());
        });
    }

    public boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
