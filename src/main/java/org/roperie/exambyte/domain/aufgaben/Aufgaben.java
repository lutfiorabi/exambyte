package org.roperie.exambyte.domain.aufgaben;

import java.util.UUID;

public abstract class Aufgaben {

    private final UUID id;
    public String frage;


    public Aufgaben(UUID id, String frage) {
        this.id = id;
        this.frage = frage;
    }

    public String getFrage(){return frage;}

    public void setFrage(String frage) { this.frage = frage; }
    
    public abstract double getMaximalPunktzahl();

    public abstract boolean isFreitext();

    public abstract String antwortenAusgeben();

    public UUID getId(){return id;}

}
