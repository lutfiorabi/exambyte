package org.roperie.exambyte.services;

import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.domain.aufgaben.Aufgaben;
import org.roperie.exambyte.services.exceptions.NichtVorhandenException;
import org.roperie.exambyte.services.exceptions.UngueltigeEingabe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizVerwaltungService {

    private final List<Quiz> quizzes = new ArrayList<>();

    private final QuizRepository quizRepository;

    public QuizVerwaltungService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> alleQuizzes(){
        return quizRepository.findAll().stream().toList();
    }

    public Quiz quiz(UUID id){
        return quizRepository.findById(id).orElseThrow(NichtVorhandenException::new);
    }




















    public UUID quizHinzufuegen(String titel){
        if (titel == null || titel.isBlank()) throw new UngueltigeEingabe();
        Quiz quiz = new Quiz(titel);
        Quiz savedQuiz = quizRepository.save(quiz);
        return savedQuiz.id();
    }

    public void freitextaufgabeHinzufuegen(){
        //TODO
    }

    public void multipleChoiceAufgabeHinzufuegen(){
        //TODO
    }






    public List<Quiz> getAllTests(){
        return quizzes;
    }

    public void deleteQuiz(Quiz quiz) {
        if (!quizzes.contains(quiz)) {
            return;
        }
        quizzes.remove(quiz);
    }

    public void editQuiz(Quiz q_old, Quiz q_new) {
        quizzes.set(quizzes.indexOf(q_old), q_new);
    }

    public void saveQuiz(Quiz q){ quizzes.add(q); }

    public Quiz findQuizByTitle(String title) {
        return quizzes.stream()
                .filter(quiz -> quiz.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    public Aufgaben findAufgabeById(UUID aufgabeId) {
        for(Quiz quiz : quizzes) {
            for(Aufgaben aufgabe: quiz.getAufgaben()) {
                if(aufgabe.getId().equals(aufgabeId)) {
                    return aufgabe;
                }
            }
        }
        throw new IllegalArgumentException("Aufgabe nicht gefunden");
    }

    public String getFrageByAufgabeId(UUID aufgabeId) {
        Aufgaben aufgabe = findAufgabeById(aufgabeId);
        return (aufgabe != null) ? aufgabe.getFrage() : "Frage nicht gefunden";
    }
}
