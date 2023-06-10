package sk.ibadtomas.dzavorkasystem.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeChanger implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println("Sender nieje hráč!");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("gms")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("§8[§c9.A§8] §7Prepnutie do Survivial módu.");
            player.setAllowFlight(true);
            player.setFlying(true);
            return true;
        } else if (command.getName().equalsIgnoreCase("gmsp")) {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("§8[§c9.A§8] §7Prepnutie do Spectátor módu.");
            return true;
        }

        return false;
    }
}