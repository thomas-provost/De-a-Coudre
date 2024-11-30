package fr.thomasprovost.dac.listeners;

import com.google.gson.Gson;
import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.game.EGameStates;
import fr.thomasprovost.dac.game.GamePlayer;
import fr.thomasprovost.dac.guis.HostGUI;
import fr.thomasprovost.dac.utils.ItemBuilder;
import fr.thomasprovost.dac.utils.Scoreboard;
import fr.thomasprovost.dac.utils.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PlayerListeners implements Listener {

    private final DacMinigame main = DacMinigame.getInstance();
    public static Player rename = null;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Scoreboard.createScoreboard(player);
        event.setJoinMessage(null);

        if (main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
            GamePlayer gamePlayer = new GamePlayer(player);
            main.getGameManager().addPlayer(gamePlayer);

            player.setGameMode(GameMode.ADVENTURE);
            player.setMaxHealth(20);
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setLevel(30);
            player.setExp(1);
            player.getInventory().clear();

            player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 0, false, false));

            player.sendMessage("§7§m----------------------------------\n" +
                    "§e• §f§lDé à coudre\n" +
                    "§f \n" +
                    "Un célèbre mode de jeu ancestrale qui arrive en host !\n" +
                    "§f \n" +
                    "§eSautez§f dans une piscine et tentez de réaliser des §edés\n" +
                    "§eà coudre§f afin de sortir vainqueur ! Montrez dès à présent\n" +
                    "à vos amis qui est le §e§lmeilleur plongeur§f !\n" +
                    "§f \n" +
                    "§8§l- §fCatégorie : §6§lCHILL\n" +
                    "§8§l- §fType : §6§lDETENTE\n" +
                    "§f \n" +
                    "§fDéveloppement : §l@thomas-provost\n" +
                    "§7§m----------------------------------");

            player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5, 0, 0));

            Bukkit.broadcastMessage("§a» §f" + player.getName() + " §7§o(" + main.getGameManager().getGamePlayers().size() + "/" + main.getGameManager().getSlots() + ")");
            Title.sendTitle(player, 10, 40, 10, "§e• §fDé à coudre §e•", "§fPar §l@thomas-provost");
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);


            player.getInventory().setItem(8, new ItemStack(new ItemBuilder(Material.DARK_OAK_DOOR_ITEM, 1).displayname("§e• §fQuitter la §lpartie").build()));
            player.getInventory().setItem(7, new ItemStack(new ItemBuilder(Material.BOOK, 1).displayname("§e• §fVoir les §lrègles").build()));

            if (main.getGameManager().getHost() == null) {
                main.getGameManager().setHost(gamePlayer);
                main.getGameManager().setHostName(gamePlayer.getName());
            }

            if (main.getGameManager().getHost().equals(DacMinigame.getInstance().getGameManager().getGamePlayer(player))) {
                player.getInventory().setItem(0, new ItemStack(new ItemBuilder(Material.COMMAND_MINECART, 1).displayname("§e• §fConfiguration de la §lpartie").build()));
            }

        } else {
            main.getGameManager().addSpectator(player);
            player.setGameMode(GameMode.SPECTATOR);
            player.setLevel(0);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }

    @EventHandler
    public void onAchievement(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null) {
            return;
        }
        if (player.getItemInHand() == null) {
            return;
        }
        ItemStack item = event.getItem();
        if (item != null && item.getItemMeta().getDisplayName().equals("§e• §fQuitter la §lpartie")) {
            player.kickPlayer("§7[§c!§7] §fVous avez quitté la partie !");
            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 1);
            ;
        }
        if (item != null && item.getItemMeta().getDisplayName().equals("§e• §fVoir les §lrègles")) {
            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 1);
            player.sendMessage("§e• §f§lRègles de la partie:\n" +
                    "§r §r\n" +
                    "§fLe but de la partie est de réaliser des §edés à coudre\n" +
                    "§fPour réaliser un dé, vous devez §esauter entre 4 blocs\n" +
                    "§r §r\n" +
                    "§8- §fVie de départ : §6" + DacMinigame.getInstance().getGameManager().getLives() + " vie(s)\n" +
                    "§8- §fVies maximum : §6" + DacMinigame.getInstance().getGameManager().getMaxLives() + " vie(s)\n" +
                    "§8- §fTemps de saut : §6" + DacMinigame.getInstance().getGameManager().getJumpTime() + " seconde(s)\n" +
                    "§8- §fType de piscine : §6" + DacMinigame.getInstance().getGameManager().getPoolType().getName() + "\n" +
                    "§8- §fType de fin de partie : §6" + DacMinigame.getInstance().getGameManager().getFinishType().getName() + "\n" +
                    "§r §r\n" +
                    "§e• §fBonne chance !");
        }
        if (item != null && item.getItemMeta().getDisplayName().equals("§e• §fConfiguration de la §lpartie")) {
            HostGUI.INVENTORY.open(player);
            player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 1);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (rename == player) {
            String message = event.getMessage();
            if (message.length() > 20) {
                player.sendMessage("§7[§c!§7] §fLe nom de la partie ne peut pas dépasser 20 caractères !");
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
            } else if (message.length() < 3) {
                player.sendMessage("§7[§c!§7] §fLe nom de la partie doit contenir au moins 3 caractères !");
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
            } else if (message.equalsIgnoreCase("annuler")) {
                rename = null;
                player.sendMessage("§7[§e!§7] §fVous avez §cannulé §fla modification du nom de la partie !");
                player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1, 1);
                HostGUI.INVENTORY.open(player);
            } else {
                DacMinigame.getInstance().getGameManager().setGameName("§e• §f§l" + message + " §e•");
                player.sendMessage("§7[§a!§7] §fLe nom de la partie a été modifié en §e• §f§l" + message + " §e•");
                player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1, 1);
                rename = null;
                HostGUI.INVENTORY.open(player);
            }
            event.setCancelled(true);
        }
        if (DacMinigame.getInstance().getGameManager().getHost().equals(DacMinigame.getInstance().getGameManager().getGamePlayer(player))) {
            event.setFormat("§e§lHôte §e" + player.getName() + " §8» §f" + event.getMessage());
        } else {
            event.setFormat("§7" + player.getName() + " §8» §7" + event.getMessage());
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();

        if (rename == player) {
            rename = null;
        }

        Scoreboard.deleteScoreboard(player);
        if (main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
            main.getGameManager().removePlayer(main.getGameManager().getGamePlayer(player));
            Bukkit.broadcastMessage("§c« §f" + player.getName() + " §7§o(" + main.getGameManager().getGamePlayers().size() + "/" + main.getGameManager().getSlots() + ")");

        }
    }
}
