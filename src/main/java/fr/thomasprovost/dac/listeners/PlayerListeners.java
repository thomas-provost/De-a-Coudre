package fr.thomasprovost.dac.listeners;

import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.game.EGameStates;
import fr.thomasprovost.dac.game.GamePlayer;
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
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListeners implements Listener {

    private final DacMinigame main = DacMinigame.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Scoreboard.createScoreboard(player);
        event.setJoinMessage(null);

        if(main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
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


            player.getInventory().setItem(8, new ItemStack(new ItemBuilder(Material.DARK_OAK_DOOR_ITEM, 1).displayname("§e• §fQuitter").build()));
            player.getInventory().setItem(7, new ItemStack(new ItemBuilder(Material.BOOK, 1).displayname("§e• §fVoir les règles").build()));

            if(main.getGameManager().getHost() == null){
                main.getGameManager().setHost(gamePlayer);
                main.getGameManager().setHostName(gamePlayer.getName());
            }

            if(main.getGameManager().getHost().equals(gamePlayer)){
                player.getInventory().setItem(0, new ItemStack(new ItemBuilder(Material.COMMAND_MINECART, 1).displayname("§e• §fConfiguration de la partie").build()));
            }

        }else{
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
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player && main.getGameManager().getGameState().equals(EGameStates.WAITING)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        Scoreboard.deleteScoreboard(player);
        if(main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
            main.getGameManager().removePlayer(main.getGameManager().getGamePlayer(player));
            Bukkit.broadcastMessage("§c« §f" + player.getName() + " §7§o(" + main.getGameManager().getGamePlayers().size() + "/" + main.getGameManager().getSlots() + ")");

        }
    }
}
