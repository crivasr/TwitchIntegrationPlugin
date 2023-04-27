package tk.camikase.TwitchIntegration.executors;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.utils.CryptoUtil;

public final class ClaimSubExecutor implements CommandExecutor {
    private final TwitchIntegrationPlugin twitchIntegrationPlugin;

    public ClaimSubExecutor(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        this.twitchIntegrationPlugin = twitchIntegrationPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof final Player player)) {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }

        final UUID uuid = player.getUniqueId();
        final String state = CryptoUtil.randomString(7, "0123456789abcdefghijklmnopqrstuvwxyz");

        twitchIntegrationPlugin.getWebhookHandler().notifyState(state);
        twitchIntegrationPlugin.getStateMap().put(state, uuid);

        player.sendMessage(
                   ChatColor.GREEN + "Linkea tu cuenta de Twitch aqui: \n" +
                   ChatColor.AQUA + "-     https://luisardito.camikase.tk/link?state=" +
                   state
        );

        return true;
    }

}
