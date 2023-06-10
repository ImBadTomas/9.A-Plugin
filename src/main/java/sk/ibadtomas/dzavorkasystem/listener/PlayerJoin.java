package sk.ibadtomas.dzavorkasystem.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.getInventory().clear();


        player.sendMessage("&8---------------------------------------");
        player.sendMessage("     §c§l9.A DŽavorka");
        player.sendMessage("    §7Náš server bol najlepší");
        player.sendMessage("");
        player.sendMessage("§7c/gms /gmsp   §7(Môžeš lietať)");
        player.sendMessage("");
        player.sendMessage("&8---------------------------------------");
    }
}
