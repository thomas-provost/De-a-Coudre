package fr.thomasprovost.dac.utils;

import fr.mrmicky.fastboard.FastBoard;
import fr.thomasprovost.dac.DacMinigame;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class Scoreboard {

    private static FastBoard board;
    private static HashMap<Player, FastBoard> boards = new HashMap<>();

    public static void createScoreboard(Player player) {
        boards.put(player, new FastBoard(player));
        boards.get(player).updateTitle(DacMinigame.getInstance().getGameManager().getGameName());
    }

    public static void deleteScoreboard(Player player) {
        boards.remove(player);
    }

    public static void updateScoreboard(Player player) {
        boards.get(player).updateTitle(DacMinigame.getInstance().getGameManager().getGameName());
        boards.get(player).updateLines(
                "§r",
                ChatColor.WHITE + "Places: " + ChatColor.YELLOW + DacMinigame.getInstance().getGameManager().getGamePlayers().size() + "/" + DacMinigame.getInstance().getGameManager().getSlots(),
                ChatColor.WHITE + "Hôte : " + ChatColor.YELLOW + DacMinigame.getInstance().getGameManager().getHostName(),
                "",
                ChatColor.WHITE + "Dev: @thomas-provost"
        );

    }

    public static FastBoard getScoreboard(Player player) {
        return boards.get(player);
    }


}
