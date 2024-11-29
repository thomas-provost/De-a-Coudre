package fr.thomasprovost.dac.game;

public enum EFinishType {

    DEATHMATCH("Match à mort"),
    EQUALITY("Egalité"),
    RESET("Réinitialisation");

    private String name;

    EFinishType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
