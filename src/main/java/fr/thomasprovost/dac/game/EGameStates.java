package fr.thomasprovost.dac.game;

public enum EGameStates {

    WAITING("En attente"),
    PLAYING("En jeu"),
    FINISHED("Terminé");

    private String name;

    EGameStates(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
