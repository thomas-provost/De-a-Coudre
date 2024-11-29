package fr.thomasprovost.dac;

import fr.minuskube.inv.InventoryManager;
import fr.thomasprovost.dac.game.GameManager;
import fr.thomasprovost.dac.game.GameTask;
import fr.thomasprovost.dac.listeners.PlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DacMinigame extends JavaPlugin {

    private static DacMinigame INSTANCE;
    private static GameManager gameManager;
    private static InventoryManager inventoryManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getLogger().info("* Loading Dé à coudre Minigame by thomas-provost...");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("* Version : " + getDescription().getVersion());
        Bukkit.getLogger().info("* Loading configuration...");

        INSTANCE = this;
        gameManager = new GameManager();

        GameTask gameTask = new GameTask();
        gameTask.runTaskTimer(this, 0, 20);

        inventoryManager = new InventoryManager(this);
        inventoryManager.init();

        Bukkit.getPluginManager().registerEvents(new PlayerListeners(), this);

        Bukkit.getLogger().info("* Sounds good !");
        Bukkit.getLogger().info(" ");
        Bukkit.getLogger().info("* Dé à coudre Minigame loaded !");
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static DacMinigame getInstance() {
        return INSTANCE;
    }

    @Override
    public void onDisable() {
    }
}
