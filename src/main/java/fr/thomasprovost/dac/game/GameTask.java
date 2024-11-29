package fr.thomasprovost.dac.game;

import fr.thomasprovost.dac.utils.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {

    @Override
    public void run() {
        for(Player players : Bukkit.getOnlinePlayers()){
            Scoreboard.updateScoreboard(players);
        }
    }
}