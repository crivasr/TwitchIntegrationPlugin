package tk.camikase.TwitchIntegration.executors;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.utils.Crypto;

public class ClaimSubExecutor implements CommandExecutor {
    TwitchIntegrationPlugin plugin = null;

    public ClaimSubExecutor(TwitchIntegrationPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();
        String state = Crypto.randomString(7, "0123456789abcdefghijklmnopqrstuvwxyz");

        plugin.getWebhookHandler().notifyState(state);
        plugin.getStateMap().put(state, uuid);

        player.sendMessage(ChatColor.GREEN + "Linkea tu cuenta de twitch aqui: \n" + ChatColor.AQUA
                + "-     https://luisardito.camikase.tk/link?state=" + state);

        return true;
    }

}
