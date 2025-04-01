package org.roperie.exambyte.domain.quiz;

import org.roperie.exambyte.domain.aufgaben.Aufgaben;
import org.roperie.exambyte.domain.aufgaben.FreitextAufgabe;
import org.roperie.exambyte.domain.aufgaben.MultipleChoiceAufgabe;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class Quiz implements Comparable<Quiz> {

    private final UUID id;
    private final String title;
    private final String encodedTitle;
    private final List<Aufgaben> aufgaben;
    private double maximalPunktzahl = 0;
    private double vergebenePunkte = 0;
    private boolean bestanden = false;
    private String bewertungsstand = "Nicht bearbeitet";

    private LocalDateTime startzeit = LocalDateTime.now();
    private LocalDateTime endzeit = LocalDateTime.now();

    public Quiz(UUID id, String title, List<Aufgaben> aufgaben) {
        this.id = id;
        this.title = title;
        this.encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        this.aufgaben = aufgaben;

    }

    public Quiz(String title){
        this(UUID.randomUUID(), title, new ArrayList<>());
    }

    @Override
    public int compareTo(Quiz other){
        return title.toLowerCase().compareTo(other.title.toLowerCase());
    }

    public UUID id(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Aufgaben> getAufgaben() {
        return aufgaben;
    }

    public void addFreitextAufgabe(FreitextAufgabe aufgabe){
        aufgaben.add(aufgabe);
        maximalPunktzahl += aufgabe.getMaximalPunktzahl();
    }

    public void addMultipleChoiceAufgabe(MultipleChoiceAufgabe aufgabe){
        aufgaben.add(aufgabe);
        maximalPunktzahl += aufgabe.getMaximalPunktzahl();
    }

    @Override
    public String toString(){
        return "Quiz[" +
                "id=" + id + ", " +
                "title=" + title + ']';
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Quiz quiz = (Quiz) o;
        return Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode(){ return Objects.hash(id); }

    /*
    Funktionen zu Verwaltung bereits existierender Aufgaben
     */
    public void deleteAufgabe(UUID aufgabeId) {
        Iterator<Aufgaben> aufgabenIterator = aufgaben.iterator();
        while (aufgabenIterator.hasNext()) {
            Aufgaben aufgabe = aufgabenIterator.next();
            if (aufgabeId.equals(aufgabe.getId())) {
                aufgabenIterator.remove();
                maximalPunktzahl -= aufgabe.getMaximalPunktzahl();
                break;
            }
        }
    }

    public void updateAufgabe(UUID aufgabeId, String neueFrage, double neueMaxPunktzahl){
        for (Aufgaben aufgabe : aufgaben) {
            if (aufgabeId.equals(aufgabe.getId())) {
                maximalPunktzahl -= aufgabe.getMaximalPunktzahl();
                aufgabe.setFrage(neueFrage);
                if(aufgabe instanceof FreitextAufgabe){
                    ((FreitextAufgabe) aufgabe).setMaximalPunktzahl(neueMaxPunktzahl);
                }
                recalculateMaximalPunktzahl();
                return;
            }
        }

        throw new IllegalArgumentException("Aufgabe mit ID " + aufgabeId + " nicht gefunden.");
    }

    private void recalculateMaximalPunktzahl() {
        maximalPunktzahl = aufgaben.stream()
                .mapToDouble(Aufgaben::getMaximalPunktzahl)
                .sum();
    }









    //TODO: muss entfernt werden
    public void addAufgabe(Aufgaben aufgabe) {
        maximalPunktzahl += aufgabe.getMaximalPunktzahl();
        aufgaben.add(aufgabe);
    }


    public double getMaximalPunktzahl() {
        return maximalPunktzahl;
    }


    public boolean getBestanden(){ return bestanden;}

    public void setBestanden(Quiz quiz){
        if(quiz.vergebenePunkte >= maximalPunktzahl / 2){
            this.bestanden = true;
        }
    }



    public String getBewertungsstand() {
        return bewertungsstand;
    }

    public void setBewertungsstandToBewertet() {
        this.bewertungsstand = "fertig bearbeitet";
    }

    public String getEncodedTitle() {
        return encodedTitle;
    }

    public LocalDateTime getStartzeit() {
        return startzeit;
    }

    public LocalDateTime getEndzeit() {
        return endzeit;
    }

    public void setStartzeit(LocalDateTime startzeit){
        this.startzeit = startzeit;
    }

    public void setEndzeit(LocalDateTime endzeit){
        this.endzeit = endzeit;
    }

    public boolean isStartzeitBeforeAndisEndzeitAfter(){
        return startzeit.isBefore(LocalDateTime.now()) && endzeit.isAfter(LocalDateTime.now());
    }
}
