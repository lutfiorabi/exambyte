package org.roperie.exambyte.ui.forms;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class FreitextAufgabeForm {

    @NotNull(message = "Die Frage darf nicht null sein")
    @Size(min=1, message="Die Frage darf nicht leer sein")
    private String frage;

    @NotNull(message="Es müssen Punkte angegeben werden")
    @DecimalMin(value = "0.0", inclusive = false, message = "Die maximalen Punkte müssen größer als 0 sein")
    private double maximalPunkte;

    public double getMaximalPunkte() {
        return maximalPunkte;
    }

    public void setMaximalPunkte(double maximalPunkte) {
        this.maximalPunkte = maximalPunkte;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

}
