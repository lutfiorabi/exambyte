package org.roperie.exambyte.domain;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Abgabe {
    private final UUID quizID;
    private String status;
    private double vergebenePunktzahl;
    private final Map<UUID, String> freitextAnswer;
    private final Map<UUID, Set<Integer>> multipleAnswers;
    private UUID abgabeId;

    private final String studentname;

    //private Map<UUID, String> korrektur = new HashMap<>();

    // für später schon mal hinzugefügt
    private LocalTime abgabezeit;

    public Abgabe(UUID quizID, Map<UUID, String> freitextAnswer, Map<UUID, Set<Integer>> multipleAnswers, String studentname) {
        this.quizID = quizID;
        this.freitextAnswer = freitextAnswer;
        this.multipleAnswers = multipleAnswers;
        this.status = "Abgegeben";
        this.vergebenePunktzahl = 0.0;
        this.studentname = studentname;
        this.abgabeId = UUID.randomUUID();

    }

    public UUID getQuizID() {
        return quizID;
    }

    public double getVergebenePunktzahl() {
        return vergebenePunktzahl;
    }

    public void setVergebenePunktzahl(double vergebenePunktzahl) {
        this.vergebenePunktzahl = vergebenePunktzahl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<UUID, String> getFreitextAnswer() {
        return freitextAnswer;
    }

    public Map<UUID, Set<Integer>> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void addMultipleAnswer(UUID aufgabeID, Set<Integer> multipleAnswers) {
        this.multipleAnswers.put(aufgabeID, multipleAnswers);
    }

    public void addFreitextAnswer(UUID aufgabeID, String answer) {
        this.freitextAnswer.put(aufgabeID, answer);
    }

    // auch hier schon mal für später
    public LocalTime getAbgabezeit() {
        return abgabezeit;
    }

    // auch hier schon mal für später
    public void setAbgabezeit(LocalTime abgabezeit) {
        this.abgabezeit = abgabezeit;
    }

    public String getStudentname() {
        return studentname;
    }

    public UUID getAbgabeId() {
        return abgabeId;
    }


    /*public Map<UUID, String> getKorrektur() {
        return korrektur;
    }

    public void setKorrektur(Map<UUID, String> korrektur) {
        this.korrektur = korrektur;
    }*/

}
