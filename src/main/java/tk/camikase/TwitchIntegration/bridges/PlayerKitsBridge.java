package tk.camikase.TwitchIntegration.bridges;

import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import pk.ajneb97.PlayerKits;
import pk.ajneb97.managers.JugadorManager;
import pk.ajneb97.model.JugadorDatos;
import pk.ajneb97.model.KitJugador;
import tk.camikase.TwitchIntegration.TwitchIntegrationPlugin;
import tk.camikase.TwitchIntegration.utils.FakePlayer;

public class PlayerKitsBridge {

    private PlayerKits playerKit = null;
    private JugadorManager jugadorManager = null;

    public PlayerKitsBridge() {
        Plugin pKit = Bukkit.getPluginManager().getPlugin("PlayerKits");

        if (pKit == null || !(pKit instanceof PlayerKits)) {
            Logger logger = TwitchIntegrationPlugin.getInstance().getLogger();
            logger.info("PlayerKits not found");
        }

        playerKit = (PlayerKits) pKit;
        jugadorManager = playerKit.getJugadorManager();
    }

    public PlayerKits getPlayerKit() {
        if (playerKit != null)
            return playerKit;

        Plugin pKit = Bukkit.getPluginManager().getPlugin("PlayerKits");

        if (pKit != null && pKit instanceof PlayerKits) {
            playerKit = (PlayerKits) pKit;
        }

        return playerKit;
    }

    public long getCooldown(Player player, String kit) {
        return jugadorManager.getCooldown(player, kit);
    }

    public long getCooldown(UUID uuid, String kit) {
        FakePlayer jugador = new FakePlayer(uuid);
        return jugadorManager.getCooldown(jugador, kit);
    }

    public void setCooldown(Player player, String kit, long cooldown) {
        JugadorDatos jugadorDatos = jugadorManager.getJugadorPorUUID(player.getUniqueId().toString());
        if (jugadorDatos == null)
            return;

        String cdString = playerKit.getKits().getString("Kits." + kit + ".cooldown");
        long cd = Long.parseLong(cdString) * 1000;

        for (KitJugador k : jugadorDatos.getKits()) {
            if (k.getNombre().equals(kit)) {
                k.setCooldown(System.currentTimeMillis() - cd + cooldown * 1000);
                break;
            }
        }
    }

    public void setRawCooldown(Player player, String kit, long cooldown) {
        JugadorDatos jugadorDatos = jugadorManager.getJugadorPorUUID(player.getUniqueId().toString());
        if (jugadorDatos == null)
            return;

        for (KitJugador k : jugadorDatos.getKits()) {
            if (k.getNombre().equals(kit)) {
                k.setCooldown(cooldown);
                break;
            }
        }
    }

    public void setRawCooldown(UUID uuid, String kit, long cooldown) {
        FakePlayer jugador = new FakePlayer(uuid);
        setRawCooldown(jugador, kit, cooldown);
    }

    public void setCooldown(UUID uuid, String kit, long cooldown) {
        FakePlayer jugador = new FakePlayer(uuid);
        setCooldown(jugador, kit, cooldown);
    }
}
