package org.roperie.exambyte.services;

import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.domain.aufgaben.FreitextAufgabe;
import org.roperie.exambyte.domain.aufgaben.MultipleChoiceAufgabe;
import org.roperie.exambyte.ui.forms.FreitextAufgabeForm;
import org.roperie.exambyte.ui.forms.MultipleChoiceAufgabeForm;

import java.util.UUID;

public class QuizErstellungService {

    public void addFreitext(FreitextAufgabeForm form, Quiz q) {
        q.addAufgabe(new FreitextAufgabe(UUID.randomUUID(), form.getFrage(), form.getMaximalPunkte()));
    }

    public void addMultipleChoice(MultipleChoiceAufgabeForm form, Quiz q) {
        q.addAufgabe(new MultipleChoiceAufgabe(UUID.randomUUID() ,form.getFrage(), form.getAntworten(), form.getRichtigenAntworten()));
    }

    public void deleteAufgabe(Quiz q, UUID aufgabeId){
        q.deleteAufgabe(aufgabeId);
    }

    public void updateAufgabe(Quiz q, UUID aufgabeId, String frage, double maximalPunktzahl){
        q.updateAufgabe(aufgabeId, frage, maximalPunktzahl);
    }




}
