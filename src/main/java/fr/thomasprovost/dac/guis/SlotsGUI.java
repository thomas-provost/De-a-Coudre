package fr.thomasprovost.dac.guis;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.thomasprovost.dac.DacMinigame;
import fr.thomasprovost.dac.listeners.PlayerListeners;
import fr.thomasprovost.dac.utils.ItemBuilder;
import fr.thomasprovost.dac.utils.Title;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SlotsGUI implements InventoryProvider {

    public static SmartInventory INVENTORY = SmartInventory.builder()
            .id("slots")
            .provider(new SlotsGUI())
            .size(5, 9)
            .title("§e• §fConfiguration des §lplaces")
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

        ClickableItem slots = ClickableItem.empty(new ItemBuilder(Material.BEACON, 1).displayname("§e• §fGestion des §lplaces")
                .lore("",
                        "§7Ce paramètre permet de modifier le nombre",
                        "§7de place disponible dans votre partie.",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getSlots() + " place(s)",
                        ""
                ).build());

        ClickableItem current = ClickableItem.empty(new ItemBuilder(Material.NETHER_STAR, 1).displayname("§e• §fValeur actuelle : §6" + DacMinigame.getInstance().getGameManager().getSlots()).build());

        ClickableItem bigPlus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 13).displayname("§e• §fAjouter §l5 places").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getSlots() + 5 < 100) {
                DacMinigame.getInstance().getGameManager().setSlots(DacMinigame.getInstance().getGameManager().getSlots() + 5);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                DacMinigame.getInstance().getGameManager().setSlots(100);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6maximum §fde places !");
                INVENTORY.open(player);
            }
        });

        ClickableItem plus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 5).displayname("§e• §fAjouter §l1 place").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getSlots() + 1 < 100) {
                DacMinigame.getInstance().getGameManager().setSlots(DacMinigame.getInstance().getGameManager().getSlots() + 1);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6maximum §fde places !");
            }
        });

        ClickableItem minus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 1).displayname("§e• §fRetirer §l1 place").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getSlots() - 1 > 1) {
                DacMinigame.getInstance().getGameManager().setSlots(DacMinigame.getInstance().getGameManager().getSlots() - 1);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6minimum §fde places !");
            }
        });

        ClickableItem bigMinus = ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE, 1).durability((byte) 14).displayname("§e• §fRetirer §l5 places").build(), e -> {
            if(DacMinigame.getInstance().getGameManager().getSlots() - 5 > 1) {
                DacMinigame.getInstance().getGameManager().setSlots(DacMinigame.getInstance().getGameManager().getSlots() - 5);
                player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                INVENTORY.open(player);
            } else {
                player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
                DacMinigame.getInstance().getGameManager().setSlots(2);
                player.sendMessage("§8[§e!§8] §fVous avez atteint le §6minimum §fde places !");
                INVENTORY.open(player);
            }
        });


        ClickableItem reset = ClickableItem.of(new ItemBuilder(Material.TNT, 1).displayname("§e• §fRéinitialiser les §lplaces")
                .lore("",
                        "§7Vous souhaitez revenir comme avant ?",
                        "§7Attention, c'est instantané !",
                        "",
                        "§e➲ Cliquez pour réinitialiser."
                ).build(), e -> {
            DacMinigame.getInstance().getGameManager().setSlots(10);
            player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            INVENTORY.open(player);
            player.sendMessage("§8[§e!§8] §fPlaces réinitialisées avec §asuccès§f !");
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
