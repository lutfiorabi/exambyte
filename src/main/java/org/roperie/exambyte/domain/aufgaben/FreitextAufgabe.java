package org.roperie.exambyte.domain.aufgaben;

import java.util.UUID;

public class FreitextAufgabe extends Aufgaben {
    private double maximalPunktzahl;

    public FreitextAufgabe(UUID id, String frage, double maximalPunktzahl) {
        super(id, frage);
        this.maximalPunktzahl = maximalPunktzahl;
    }


    @Override
    public double getMaximalPunktzahl() {
        return maximalPunktzahl;
    }

    public void setMaximalPunktzahl(double maximalPunktzahl) { this.maximalPunktzahl = maximalPunktzahl; }

    @Override
    public boolean isFreitext(){
        return true;
    }

    @Override
    public String antwortenAusgeben(){
        return "";
    }
}
