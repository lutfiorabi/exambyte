package Builders;

import org.roperie.exambyte.domain.aufgaben.FreitextAufgabe;

import java.util.UUID;

public class FreitextAufgabeBuilder {

    private UUID id;
    private String frage;
    private double maximalPunktzahl;

    public FreitextAufgabeBuilder setFrage(String frage) {
        this.frage = frage;
        return this;
    }

    public FreitextAufgabeBuilder setMaximalPunktzahl(double maximalPunktzahl) {
        this.maximalPunktzahl = maximalPunktzahl;
        return this;
    }

    public FreitextAufgabe build() {
        return new FreitextAufgabe(id, frage, maximalPunktzahl);
    }
}
