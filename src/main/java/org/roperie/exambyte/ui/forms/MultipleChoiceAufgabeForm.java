package org.roperie.exambyte.ui.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

public class MultipleChoiceAufgabeForm {

    @Size(min = 1, message = "Es muss mindestens eine richtige Antwort angegeben werden")
    @NotNull(message="Es muss mindestens eine richtige Antwort angegeben werden")
    private Set<Integer> richtigenAntworten;

    @NotNull(message = "Die Liste der Antworten darf nicht null sein")
    private List<@NotEmpty(message = "Eine Antwort darf nicht leer sein") String> antworten;

    @NotNull
    @Size(min=1, message="Die Frage darf nicht leer sein")
    private String frage;

    public Set<Integer> getRichtigenAntworten() {
        return richtigenAntworten;
    }

    public void setRichtigenAntworten(Set<Integer> richtigenAntworten) {
        this.richtigenAntworten = richtigenAntworten;
    }

    public List<String> getAntworten() {
        return antworten;
    }

    public void setAntworten(List<String> antworten) {
        this.antworten = antworten;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

}
