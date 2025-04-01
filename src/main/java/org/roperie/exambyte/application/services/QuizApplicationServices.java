package org.roperie.exambyte.application.services;


import org.roperie.exambyte.domain.Abgabe;
import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.domain.aufgaben.Aufgaben;
import org.roperie.exambyte.services.*;
import org.roperie.exambyte.ui.forms.AbgabeForm;
import org.roperie.exambyte.ui.forms.FreitextAufgabeForm;
import org.roperie.exambyte.ui.forms.MultipleChoiceAufgabeForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizApplicationServices {
    private final QuizErstellungService erstellungService;
    private final QuizVerwaltungService verwaltungService;
    private final QuizBewertungService bewertungService;
    private final QuizBearbeitungService bearbeitungService;

    private final QuizRepository quizRepository;

    public QuizApplicationServices(QuizRepository quizRepository) {

        this.quizRepository = quizRepository;

        this.bearbeitungService = new QuizBearbeitungService();
        this.verwaltungService = new QuizVerwaltungService(this.quizRepository);
        this.bewertungService = new QuizBewertungService();
        this.erstellungService = new QuizErstellungService();
    }



    public List<Quiz> getAllTests(){
        return verwaltungService.getAllTests();
    }


    public List<Quiz> alleQuizzes() {
        return verwaltungService.alleQuizzes();
    }

    // Quizerstellungsmethoden
/*    public void addAufgabe(Quiz quiz, FreitextAufgabeForm form){
        erstellungService.addFreiTextAufgabe(quiz, frage, maximalPunktzahl);
    }

    public void addAufgabe(Quiz quiz, MultipleChoiceAufgabeForm aufgabe){

    }
*/



    // Verwaltungsmethoden


    public void deleteQuiz(Quiz q) {
        verwaltungService.deleteQuiz(q);
    }

    public void editQuiz(Quiz q_old, Quiz q_new) {
        verwaltungService.editQuiz(q_old, q_new);
    }

    public void saveQuiz(Quiz q) { verwaltungService.saveQuiz(q); }

    public Quiz getQuizByTilte(String title){
        Quiz quiz = verwaltungService.findQuizByTitle(title);
        if (quiz == null) {
            throw new IllegalArgumentException("No Such Quiz Title");
        }
        return quiz;
    }

    /*public UUID getQuizIdByTitle(String title) {
        Quiz quiz = getQuizByTilte(title);
        if (quiz == null) {
            throw new IllegalArgumentException("No Such Quiz Title");
        }
        return quiz.getQuizID();
    }*/

    public double getGesamtPunktzahl(String title){
        Quiz quiz = getQuizByTilte(title);
        return quiz.getMaximalPunktzahl();
    }

    public void addFreitext(FreitextAufgabeForm form, String title) {

        Quiz q = verwaltungService.findQuizByTitle(title);
        erstellungService.addFreitext(form, q);
    }

    public void addMultipleChoice(MultipleChoiceAufgabeForm form, String title){
        Quiz q = verwaltungService.findQuizByTitle(title);
        erstellungService.addMultipleChoice(form, q);
    }

    public void deleteAufgabe(String title, UUID aufgabeId) {
        Quiz quiz = verwaltungService.findQuizByTitle(title);
        erstellungService.deleteAufgabe(quiz, aufgabeId);
    }

    public Aufgaben getAufgabeById(UUID aufgabeId) {
        return verwaltungService.findAufgabeById(aufgabeId);
    }

    public void updateAufgabe(String title, String frage, UUID aufgabeId, double maximalPunktzahl) {
        Quiz quiz = verwaltungService.findQuizByTitle(title);
        erstellungService.updateAufgabe(quiz, aufgabeId, frage, maximalPunktzahl);
    }

    // Bearbeitung Methoden
    public List<Abgabe> getAllAbgaben(){
        return bearbeitungService.getAllAbgaben();
    }

    /*public void addAbgabe(AbgabeForm form, String title, String studentenname) {
        UUID quizID = getQuizIdByTitle(title);
        bearbeitungService.addAbgabe(form, quizID, studentenname);
    }*/

    /*public void updateAbgabeWithKorrektur(Abgabe abgabe) {
        bearbeitungService.updateAbgabeWithKorrektur(abgabe);
    }*/

    public Abgabe getAbgabeById(String studentenname, String abgabeId){
        return bearbeitungService.getAbgabeById(studentenname, abgabeId);
    }

    public String getFrageById(UUID aufgabeId){
        return verwaltungService.getFrageByAufgabeId(aufgabeId);
    }

}
