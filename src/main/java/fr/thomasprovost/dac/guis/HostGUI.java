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

public class HostGUI implements InventoryProvider {

    public static SmartInventory INVENTORY = SmartInventory.builder()
            .id("host")
            .provider(new HostGUI())
            .size(5, 9)
            .title("§e• §fConfiguration de la §lpartie")
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


        ClickableItem gameName = ClickableItem.of(new ItemBuilder(Material.NAME_TAG, 1).displayname("§e• §fRenommer la §lpartie")
                .lore("",
                        "§7Envie de customiser le nom de votre",
                        "§7partie ? C'est ici que ça se passe !",
                        "",
                        "§8• §fNom actuel: §6" + DacMinigame.getInstance().getGameManager().getGameName(),
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            player.closeInventory();
            Title.sendTitle(player, 10, 40, 10, "§fEntrez le nom de la partie", "§fUtilisez §cannuler §fpour annuler");
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
            PlayerListeners.rename = player;
        });

        ClickableItem lives = ClickableItem.of(new ItemBuilder(Material.APPLE, 1).displayname("§e• §fVies au §ldémarrage")
                .lore("",
                        "§7Vous souhaitez modifier le nombre de",
                        "§7vies de vos joueurs ? Modifiez là dès",
                        "§7maintenant !",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getLives() + " vie(s)",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            StartLivesGUI.INVENTORY.open(player);
        });

        ClickableItem maxLives = ClickableItem.of(new ItemBuilder(Material.GOLDEN_APPLE, 1).displayname("§e• §fVies §lmaximales")
                .lore("",
                        "§7Vous souhaitez modifier le nombre de",
                        "§7vies maximales de vos joueurs ? Modifiez",
                        "§7là dès maintenant !",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getMaxLives() + " vie(s)",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            MaxLivesGUI.INVENTORY.open(player);
        });

        ClickableItem slots = ClickableItem.of(new ItemBuilder(Material.BEACON, 1).displayname("§e• §fGestion des §lplaces")
                .lore("",
                        "§7Ce paramètre permet de modifier le nombre",
                        "§7de place disponible dans votre partie.",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getSlots() + " place(s)",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            SlotsGUI.INVENTORY.open(player);

        });

        ClickableItem finishType = ClickableItem.of(new ItemBuilder(Material.BARRIER, 1).displayname("§e• §fType de §lfin")
                .lore("",
                        "§7Vous souhaitez modifier le type de fin",
                        "§7de votre partie ? Pour cela, changez ce",
                        "§7paramètre !",
                        "",
                        "§8• §fType actuel: §6" + DacMinigame.getInstance().getGameManager().getFinishType().getName(),
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            DacMinigame.getInstance().getGameManager().nextFinishType();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
            INVENTORY.open(player);
            player.sendMessage("§8[§e!§8] §fType de fin modifié avec §asuccès§f en §6" + DacMinigame.getInstance().getGameManager().getFinishType().getName() + " §f!");
        });

        ClickableItem poolType = ClickableItem.of(new ItemBuilder(DacMinigame.getInstance().getGameManager().getPoolType().getMaterial(), 1).displayname("§e• §fType de §lpiscine")
                .lore("",
                        "§7Vous souhaitez modifier le type de piscine",
                        "§7de votre partie ? Pour cela, changez ce",
                        "§7paramètre !",
                        "",
                        "§8• §fType actuel: §6" + DacMinigame.getInstance().getGameManager().getPoolType().getName(),
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {
            DacMinigame.getInstance().getGameManager().nextPoolType();
            player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
            INVENTORY.open(player);
            player.sendMessage("§8[§e!§8] §fType de piscine modifié avec §asuccès§f en §6" + DacMinigame.getInstance().getGameManager().getPoolType().getName() + " §f!");
        });


        ClickableItem map = ClickableItem.of(new ItemBuilder(Material.MAP, 1).displayname("§e• §fChanger de §lCarte")
                .lore("",
                        "§7Vous avez une carte favorite ?",
                        "§7Vous pouvez la changer ici !",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {

        });

        ClickableItem reset = ClickableItem.of(new ItemBuilder(Material.TNT, 1).displayname("§e• §fRéinitialiser les §lparamètres")
                .lore("",
                        "§7Vous souhaitez revenir comme avant ?",
                        "§7Attention, c'est instantané !",
                        "",
                        "§e➲ Cliquez pour réinitialiser."
                ).build(), e -> {
            DacMinigame.getInstance().getGameManager().resetSettings();
            player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            INVENTORY.open(player);
            player.sendMessage("§8[§e!§8] §fParamètres réinitialisés avec §asuccès§f !");
        });

        ClickableItem jumpTime = ClickableItem.of(new ItemBuilder(Material.WATCH, 1).displayname("§e• §fTemps de §lsaut")
                .lore("",
                        "§7Vous souhaitez modifier le temps de",
                        "§7saut de votre partie ? Modifiez le dès",
                        "§7maintenant !",
                        "",
                        "§8• §fValeur actuelle: §6" + DacMinigame.getInstance().getGameManager().getJumpTime() + " seconde(s)",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {

        });

        ClickableItem whitelist = ClickableItem.of(new ItemBuilder(Material.BOOK_AND_QUILL, 1).displayname("§e• §fListe §lblanche")
                .lore("",
                        "§7La liste blanche permet de restreindre",
                        "§7l'accès à votre partie à des joueurs",
                        "§7prédéfinis.",
                        "",
                        "§8• §fStatut: ", //TODO : Statut de la liste blanche
                        "",
                        "§e➲ Cliquez pour modifier."
        ).build(), e -> {

        });

        ClickableItem start = ClickableItem.of(new ItemBuilder(Material.INK_SACK, 1).durability((byte) 10).displayname("§e• §fDémarrer la §lpartie")
                .lore("",
                        "§7Tout est prêt ? Allons-y !",
                        "",
                        "§e➲ Cliquez pour démarrer."
                ).build(), e -> {

        });

        ClickableItem templates = ClickableItem.of(new ItemBuilder(Material.COMMAND, 1).displayname("§e• §fGestion des §lmodèles")
                .lore("",
                        "§7Vous souhaitez gagner du temps dans",
                        "§7la configuration de votre partie ?",
                        "§7Créez et gérez vos modèles ici !",
                        "",
                        "§e➲ Cliquez pour modifier."
                ).build(), e -> {

        });


        inventoryContents.set(4, 6, whitelist);
        inventoryContents.set(4, 4, start);
        inventoryContents.set(4, 2, templates);

        inventoryContents.set(1, 3, gameName);
        inventoryContents.set(1, 4, lives);
        inventoryContents.set(1, 5, slots);
        inventoryContents.set(2, 2, finishType);
        inventoryContents.set(2, 3, jumpTime);
        inventoryContents.set(2, 4, maxLives);
        inventoryContents.set(2, 5, poolType);
        inventoryContents.set(2, 6, map);
        inventoryContents.set(4, 8, reset);
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
