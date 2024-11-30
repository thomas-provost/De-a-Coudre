package fr.thomasprovost.dac.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MaxLivesGUI implements InventoryProvider {

    public static SmartInventory INVENTORY = SmartInventory.builder()
            .id("maxLives")
            .provider(new MaxLivesGUI())
            .size(5, 9)
            .title("§e• §fConfiguration des vies §lmaximales")
            .manager(DacMinigame.getInstance().getInventoryManager())
            .build();

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        ClickableItem border = ClickableItem.empty(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).displayname("§f§l@thomas-provost").build());

        inventoryContents.set(0, 0, border);
        inventoryContents.set(0, 1, border);
        inventoryContents.set(0, 7, border);
        inventoryContents.set(0, 8, border);
        inventoryContents.set(1, 0, border);
        inventoryContents.set(1, 8, border);
        inventoryContents.set(3, 0, border);
        inventoryContents.set(3, 8, border);
        inventoryContents.set(4, 0, border);
        inventoryContents.set(4, 1, border);
        inventoryContents.set(4, 7, border);
        
        ClickableItem slots = ClickableItem.empty(new ItemBuilder(Material.GOLDEN_APPLE, 1).displayname("§e• §fVies au §lmaximales")
                .lore("",
                        "§7Vous souhaitez modifier le nombre de",
                        "§7vies maximales de vos joueurs ? Modifiez",
                        "§7là dès maintenant !",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getMaxLives() + " vie(s)",
                        ""
                ).build());

        ClickableItem current = ClickableItem.empty(new ItemBuilder(Material.NETHER_STAR, 1).displayname("§e• §fValeur actuelle : §6" + DacMinigame.getInstance().getGameManager().getMaxLives() + " vie(s)").build());

        ClickableItem bigPlus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 13).displayname("§e• §fAjouter §l5 vies").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getMaxLives() + 5 < 50) {
                DacMinigame.getInstance().getGameManager().setMaxLives(DacMinigame.getInstance().getGameManager().getMaxLives() + 5);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                DacMinigame.getInstance().getGameManager().setMaxLives(50);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6maximum §fde vies !");
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
                INVENTORY.open(player);
            }
        });

        ClickableItem plus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 5).displayname("§e• §fAjouter §l1 vie").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getMaxLives() + 1 < 50) {
                DacMinigame.getInstance().getGameManager().setMaxLives(DacMinigame.getInstance().getGameManager().getMaxLives() + 1);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6maximum §fde vies !");
            }
        });

        ClickableItem minus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 1).displayname("§e• §fRetirer §l1 vie").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getMaxLives() - 1 >= 0) {
                DacMinigame.getInstance().getGameManager().setMaxLives(DacMinigame.getInstance().getGameManager().getMaxLives() - 1);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6minimum §fde vies !");
            }
        });

        ClickableItem bigMinus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 14).displayname("§e• §fRetirer §l5 vies").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getMaxLives() - 5 > 0) {
                DacMinigame.getInstance().getGameManager().setMaxLives(DacMinigame.getInstance().getGameManager().getMaxLives() - 5);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                DacMinigame.getInstance().getGameManager().setMaxLives(0);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6minimum §fde vies !");
                if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                    DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
                }
                INVENTORY.open(player);
            }
        });


        ClickableItem reset = ClickableItem.of(new ItemBuilder(Material.TNT, 1).displayname("§e• §fRéinitialiser les §lvies")
                .lore("",
                        "§7Vous souhaitez revenir comme avant ?",
                        "§7Attention, c'est instantané !",
                        "",
                        "§e➲ Cliquez pour réinitialiser."
                ).build(), e -> {
            DacMinigame.getInstance().getGameManager().setMaxLives(5);
            if(DacMinigame.getInstance().getGameManager().getLives() > DacMinigame.getInstance().getGameManager().getMaxLives()) {
                DacMinigame.getInstance().getGameManager().setLives(DacMinigame.getInstance().getGameManager().getMaxLives());
            }
            player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            INVENTORY.open(player);
            player.sendMessage("§8[§e!§8] §fvies réinitialisées avec §asuccès§f !");
        });

        ClickableItem back = ClickableItem.of(new ItemBuilder(Material.ARROW, 1).displayname("§e• §fMenu §lprincipal")
                .lore("",
                        "§7Cliquez pour revenir au menu principal.",
                        "",
                        "§e➲ Cliquez pour y aller."
                ).build(), e -> {
            HostGUI.INVENTORY.open(player);
        });


        inventoryContents.set(0, 4, slots);
        inventoryContents.set(4, 4, back);

        inventoryContents.set(2, 2, bigMinus);
        inventoryContents.set(2, 3, minus);
        inventoryContents.set(2, 4, current);
        inventoryContents.set(2, 5, plus);
        inventoryContents.set(2, 6, bigPlus);
        inventoryContents.set(4, 8, reset);
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
