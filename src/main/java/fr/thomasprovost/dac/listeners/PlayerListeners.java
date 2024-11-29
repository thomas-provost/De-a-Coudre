package fr.thomasprovost.dac.listeners;

import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.game.EGameStates;
import fr.thomasprovost.dac.game.GamePlayer;
import fr.thomasprovost.dac.utils.Scoreboard;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {

    private final DacMinigame main = DacMinigame.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Scoreboard.createScoreboard(player);

        if(main.getGameManager().getGameState().equals(EGameStates.WAITING)) {
            GamePlayer gamePlayer = new GamePlayer(player);
            main.getGameManager().addPlayer(gamePlayer);

            player.setGameMode(GameMode.ADVENTURE);
            player.setMaxHealth(20);
            player.setHealth(20);
            player.setLevel(30);
            player.getInventory().clear();
        }else{
            main.getGameManager().addSpectator(player);
            player.setGameMode(GameMode.SPECTATOR);
            player.setLevel(0);
        }
    }
}
