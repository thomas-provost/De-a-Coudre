package fr.thomasprovost.dac.game;

import fr.thomasprovost.dac.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {

    private ArrayList<GamePlayer> gamePlayers = new ArrayList<>();
    private ArrayList<Player> spectators = new ArrayList<>();

    private int lives = 1;
    private int slots = 10;
    private int maxLives = 5;
    private int jumpTime = 10;
    private EPoolType poolType = EPoolType.WATER;
    private String gameName = ChatColor.YELLOW + "• §f§lDé à coudre " + ChatColor.YELLOW + "•";
    private final Cuboid lobbyRegion = new Cuboid(new Location(Bukkit.getWorld("world"), -65, 90, -65), new Location(Bukkit.getWorld("world"), 65, 150, 65));

    private GamePlayer host = null;
    private String hostName = "§c§lAucun";

    private EFinishType finishType = EFinishType.RESET;
    private EGameStates gameState = EGameStates.WAITING;


    public void resetSettings(){
        lives = 1;
        slots = 10;
        maxLives = 5;
        jumpTime = 10;
        poolType = EPoolType.WATER;
        gameName = ChatColor.YELLOW + "• §f§lDé à coudre " + ChatColor.YELLOW + "•";
        finishType = EFinishType.RESET;
        gameState = EGameStates.WAITING;
    }

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

    public Cuboid getLobbyRegion() {
        return lobbyRegion;
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

    public void nextFinishType() {
        if(finishType.equals(EFinishType.RESET)) {
            finishType = EFinishType.DEATHMATCH;
        } else if(finishType.equals(EFinishType.DEATHMATCH)) {
            finishType = EFinishType.EQUALITY;
        } else {
            finishType = EFinishType.RESET;
        }
    }

    public void nextPoolType() {
        if(poolType.equals(EPoolType.WATER)) {
            poolType = EPoolType.LAVA;
        } else {
            poolType = EPoolType.WATER;
        }
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

    public EPoolType getPoolType() {
        return poolType;
    }

    public void setPoolType(EPoolType poolType) {
        this.poolType = poolType;
    }
}
