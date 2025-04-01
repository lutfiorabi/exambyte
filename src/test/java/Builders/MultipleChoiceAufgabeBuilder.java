package Builders;

import org.roperie.exambyte.domain.aufgaben.MultipleChoiceAufgabe;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MultipleChoiceAufgabeBuilder {

    private UUID id;
    private String frage;
    private List<String> antworten;
    private Set<Integer> richtigenAntworten;

    public MultipleChoiceAufgabeBuilder setFrage(String frage) {
        this.frage = frage;
        return this;
    }

    public MultipleChoiceAufgabeBuilder setAntworten(List<String> antworten) {
        this.antworten = antworten;
        return this;
    }

    public MultipleChoiceAufgabeBuilder setRichtigenAntworten(Set<Integer> richtigenAntworten) {
        this.richtigenAntworten = richtigenAntworten;
        return this;
    }

    public MultipleChoiceAufgabe build() {
        return new MultipleChoiceAufgabe(id, frage, antworten, richtigenAntworten);
    }
}
