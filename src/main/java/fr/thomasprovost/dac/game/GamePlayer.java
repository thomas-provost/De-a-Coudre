package fr.thomasprovost.dac.game;

import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {

    private final String name;
    private boolean isHost;
    private int lives;
    private boolean isDead;
    private final Player player;
    private UUID uuid;

    public GamePlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.isHost = false;
        this.lives = 1;
        this.isDead = false;
        this.uuid = player.getUniqueId();
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
