package org.roperie.exambyte.services;


import org.roperie.exambyte.domain.Abgabe;
import org.roperie.exambyte.ui.forms.AbgabeForm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizBearbeitungService {

    private final List<Abgabe> abgaben = new ArrayList<>();

    public List<Abgabe> getAllAbgaben() {
        return abgaben;
    }

    public void addAbgabe(AbgabeForm form, UUID quizId, String studentenname) {
        Abgabe neueAbgabe = new Abgabe(quizId, form.getFreitextAnswers(), form.getMultipleAnswers(), studentenname);
        abgaben.removeIf(abgabe -> abgabe.getQuizID().equals(quizId) && abgabe.getStudentname().equals(studentenname));
        abgaben.add(neueAbgabe);
    }

    public Abgabe getAbgabeById(String studentenname, String abgabeId){
        try {
            UUID abgabeUUID = UUID.fromString(abgabeId);
            return abgaben.stream()
                    .filter(abgabe -> abgabe.getStudentname().equals(studentenname) && abgabe.getAbgabeId().equals(abgabeUUID))
                    .findFirst()
                    .orElse(null);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /*public void updateAbgabeWithKorrektur(Abgabe abgabe) {
        abgaben.removeIf(a -> a.getAbgabeId().equals(abgabe.getAbgabeId()));
        abgaben.add(abgabe);
    }*/

}
