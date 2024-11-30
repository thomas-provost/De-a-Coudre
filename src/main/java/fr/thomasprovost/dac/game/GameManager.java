package fr.thomasprovost.dac.game;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {

    private ArrayList<GamePlayer> gamePlayers = new ArrayList<>();
    private ArrayList<Player> spectators = new ArrayList<>();

    private int lives = 1;
    private int slots = 10;
    private int maxLives = 5;
    private int jumpTime = 10;
    private String gameName = ChatColor.YELLOW + "• §f§lDé à coudre " + ChatColor.YELLOW + "•";

    private GamePlayer host = null;
    private String hostName = "§c§lAucun";

    private EFinishType finishType = EFinishType.RESET;
    private EGameStates gameState = EGameStates.WAITING;


    public void addPlayer(GamePlayer gamePlayer) {
        gamePlayers.add(gamePlayer);
    }

    public void removePlayer(GamePlayer gamePlayer) {
        gamePlayers.remove(gamePlayer);
    }

    public void addSpectator(Player player) {
        spectators.add(player);
    }

    public void removeSpectator(Player player) {
        spectators.remove(player);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public EFinishType getFinishType() {
        return finishType;
    }

    public void setFinishType(EFinishType finishType) {
        this.finishType = finishType;
    }

    public EGameStates getGameState() {
        return gameState;
    }

    public void setGameState(EGameStates gameState) {
        this.gameState = gameState;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    public int getJumpTime() {
        return jumpTime;
    }

    public void setJumpTime(int jumpTime) {
        this.jumpTime = jumpTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public GamePlayer getGamePlayer(Player player) {
        for(GamePlayer gamePlayer : gamePlayers) {
            if(gamePlayer.getPlayer().equals(player)) {
                return gamePlayer;
            }
        }
        return null;
    }

    public ArrayList<Player> getSpectators() {
        return spectators;
    }

    public GamePlayer getHost() {
        return host;
    }

    public void setHost(GamePlayer host) {
        this.host = host;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
