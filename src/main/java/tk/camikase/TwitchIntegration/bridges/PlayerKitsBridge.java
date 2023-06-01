package tk.camikase.TwitchIntegration.bridges;

import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import pk.ajneb97.PlayerKits;
import pk.ajneb97.managers.JugadorManager;
import pk.ajneb97.model.JugadorDatos;
import pk.ajneb97.model.KitJugador;

import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;

public final class PlayerKitsBridge {
    private final PlayerKits playerKitsPlugin;
    private final JugadorManager jugadorManager; // my eyes

    public PlayerKitsBridge(final TwitchIntegrationPlugin twitchIntegrationPlugin) {
        final Plugin pKit = twitchIntegrationPlugin.getServer().getPluginManager().getPlugin("PlayerKits");

        if (!(pKit instanceof PlayerKits)) {
            playerKitsPlugin = null;
            jugadorManager = null;

            Bukkit.getLogger().log(Level.SEVERE, "PlayerKits not found, shutting down...");
            Bukkit.shutdown();

            return;
        }

        playerKitsPlugin = (PlayerKits) pKit;
        jugadorManager = playerKitsPlugin.getJugadorManager();
    }

    public long getCooldown(final UUID uuid, final String kitIdentifier) {
        final JugadorDatos jugadorDatos = jugadorManager.getJugadorPorUUID(uuid.toString());

        if (jugadorDatos == null) return 0L;

        for (final KitJugador kit : jugadorDatos.getKits()) {
            if (kit.getNombre().equals(kitIdentifier)) {
                return kit.getCooldown();
            }
        }

        return 0L;
    }

    public void setCooldown(final UUID uuid, final String kitIdentifier, final long cooldown) {
        final JugadorDatos jugadorDatos = jugadorManager.getJugadorPorUUID(uuid.toString());

        if (jugadorDatos == null) return;

        final String cooldownString = playerKitsPlugin.getKits().getString("Kits." + kitIdentifier + ".cooldown");
        if (cooldownString == null) return;

        final long parsedCooldown = Long.parseLong(cooldownString) * 1000;

        for (final KitJugador kit : jugadorDatos.getKits()) {
            if (kit.getNombre().equals(kitIdentifier)) {
                kit.setCooldown(System.currentTimeMillis() - parsedCooldown + cooldown * 1000);
                break;
            }
        }
    }

    public void setRawCooldown(final UUID uuid, final String kitIdentifier, final long cooldown) {
        final JugadorDatos jugadorDatos = jugadorManager.getJugadorPorUUID(uuid.toString());
        if (jugadorDatos == null) return;

        for (final KitJugador kit : jugadorDatos.getKits()) {
            if (kit.getNombre().equals(kitIdentifier)) {
                kit.setCooldown(cooldown);
                break;
            }
        }
    }
}
