package sk.ibadtomas.dzavorkasystem.listener;

import org.bukkit.ChatColor;
import org.bukkit.block.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;

import sk.ibadtomas.dzavorkasystem.MainClass;

public class BuildMode  implements Listener, CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("dzavorka.full")) {
            player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
            return true;
        }

        if (!MainClass.getInstance().getBuildModeStatus().contains(player.getUniqueId())) {
            MainClass.getInstance().getBuildModeStatus().add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + "Build mode sa zapol.");
            return true;
        }
        if (MainClass.getInstance().getBuildModeStatus().contains(player.getUniqueId())) {
            MainClass.getInstance().getBuildModeStatus().remove(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "Build mode sa vypol.");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        MainClass.getInstance().getBuildModeStatus().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!MainClass.getInstance().getBuildModeStatus().contains(event.getPlayer().getUniqueId())) {
            Player player = event.getPlayer();
            player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!MainClass.getInstance().getBuildModeStatus().contains(event.getPlayer().getUniqueId())) {
            Player player = event.getPlayer();
            player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (!MainClass.getInstance().getBuildModeStatus().contains(event.getPlayer().getUniqueId())) {
            Player player = event.getPlayer();
            player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!MainClass.getInstance().getBuildModeStatus().contains(event.getPlayer().getUniqueId())) {
            Player player = event.getPlayer();
            player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }

        InventoryHolder holder = event.getClickedInventory().getHolder();

        if (holder instanceof BlockState) {
            Block block = ((BlockState) holder).getBlock();

            if (block.getState() instanceof Chest) {
                if (!MainClass.getInstance().getBuildModeStatus().contains(event.getWhoClicked().getUniqueId())) {
                    Player player = (Player) event.getWhoClicked();
                    player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() == null) {
            return;
        }
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof BlockState) {
            Block block = ((BlockState) holder).getBlock();

            if (block.getState() instanceof Chest) {
                if (!MainClass.getInstance().getBuildModeStatus().contains(event.getWhoClicked().getUniqueId())) {
                    Player player = (Player) event.getWhoClicked();
                    player.sendMessage("§8[§c9.A§8] §7Na toto nemáš oprávnenia");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

}
