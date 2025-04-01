package org.roperie.exambyte.domain.aufgaben;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MultipleChoiceAufgabe extends Aufgaben {

    private final double maximalPunktzahl;
    private final List<String> antworten;
    private final Set<Integer> richtigenAntworten;

    public MultipleChoiceAufgabe(UUID id, String frage, List<String> antworten, Set<Integer> richtigenAntworten) {
        super(id, frage);
        this.maximalPunktzahl = (double) richtigenAntworten.size() / 2;
        this.antworten = antworten;
        this.richtigenAntworten = richtigenAntworten;
    }

    public List<String> getAntworten() {
        return antworten;
    }


    public Set<Integer> getRichtigenAntworten() {
        return richtigenAntworten;
    }

    @Override
    public double getMaximalPunktzahl() {
        return maximalPunktzahl;
    }


    @Override
    public boolean isFreitext(){
        return false;
    }

    @Override
    public String antwortenAusgeben(){
        List<String> antworten = getAntworten();
        return String.join(", ", antworten);
    }
}
