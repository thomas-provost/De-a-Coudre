package fr.thomasprovost.dac.game;

import org.bukkit.Material;

public enum EPoolType {

    WATER("Eau", Material.WATER_BUCKET),
    LAVA("Lave", Material.LAVA_BUCKET),;

    private final String name;
    private final Material material;

    EPoolType(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }
}
