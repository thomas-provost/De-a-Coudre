package fr.thomasprovost.dac.game;

import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.utils.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.thomasprovost.dac.utils.TabList.setTabListHeaderFooter;

public class GameTask extends BukkitRunnable {

    private static final DacMinigame main = DacMinigame.getInstance();

    @Override
    public void run() {
        for(Player players : Bukkit.getOnlinePlayers()){
            Scoreboard.updateScoreboard(players);
            setTabListHeaderFooter(players);
            if(main.getGameManager().getGameState().equals(EGameStates.WAITING)){
                if(!(main.getGameManager().getLobbyRegion().isIn(players))){
                    players.teleport(new Location(Bukkit.getWorld("world"), 0.5, 101, 0.5, 0, 0));
                    players.playSound(players.getLocation(), Sound.VILLAGER_NO, 1, 1);
                    players.playSound(players.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
                    players.sendMessage("§8[§c!§8] §cHep hep hep ! N'allez pas si loin !");
                }
            }
        }
    }
}
