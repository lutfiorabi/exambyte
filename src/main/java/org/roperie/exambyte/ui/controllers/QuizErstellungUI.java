package org.roperie.exambyte.ui.controllers;

import jakarta.validation.Valid;
import org.roperie.exambyte.application.services.QuizApplicationServices;
import org.roperie.exambyte.config.OrganisatorOnly;
import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.domain.aufgaben.Aufgaben;
import org.roperie.exambyte.domain.aufgaben.FreitextAufgabe;
import org.roperie.exambyte.domain.aufgaben.MultipleChoiceAufgabe;
import org.roperie.exambyte.ui.forms.FreitextAufgabeForm;
import org.roperie.exambyte.ui.forms.MultipleChoiceAufgabeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequestMapping("/quizansicht")
@OrganisatorOnly
public class QuizErstellungUI {

    private final QuizApplicationServices appServices;

    public QuizErstellungUI(QuizApplicationServices appServices) {
        this.appServices = appServices;
    }

    @GetMapping("/{title}")
    public String saveQuiz(@PathVariable String title, Model m) {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        m.addAttribute("title", decodedTitle);
        return "quizansicht";
    }


    @PostMapping("/{title}")
    public String aufgabehinzugefuegt(@PathVariable String title, Model m){
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        Quiz quiz = appServices.getQuizByTilte(decodedTitle);
        m.addAttribute("title", quiz.getTitle());
        m.addAttribute("aufgaben", quiz.getAufgaben());
        m.addAttribute("gesamtPunktzahl", quiz.getMaximalPunktzahl());

        return "quizansicht";
    }

    @GetMapping("/{title}/aufgabehinzufuegen/freitext")
    public String addFreeTextQuestion(@PathVariable String title, Model m) {
        m.addAttribute("isFreitext", true);
        m.addAttribute("title", title);
        m.addAttribute("freitextAufgabeForm", new FreitextAufgabeForm());

        return "aufgabehinzufuegen";
    }

    @PostMapping("/{title}/aufgabehinzufuegen/freitext")
    public String addedFreeTextQuestion(@PathVariable String title,
                                        @Valid FreitextAufgabeForm form,
                                        BindingResult bindingResult,
                                        Model m) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("isFreitext", true);
            m.addAttribute("title", title);
            m.addAttribute("freitextAufgabeForm", form);
            return "aufgabehinzufuegen";
        }
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        appServices.addFreitext(form, decodedTitle);
        return "forward:/quizansicht/" + title;
    }

    @GetMapping("/{title}/aufgabehinzufuegen/multiple")
    public String addMultipleChoiceQuestion(@PathVariable String title, Model m) {
        m.addAttribute("isFreitext", false);
        m.addAttribute("title", title);
        m.addAttribute("multipleChoiceAufgabeForm", new MultipleChoiceAufgabeForm());
        return "aufgabehinzufuegen";
    }

    @PostMapping("/{title}/aufgabehinzufuegen/multiple")
    public String addedMultiplceChoiceQuestion(@PathVariable String title,
                                               @Valid MultipleChoiceAufgabeForm form,
                                               BindingResult bindingResult,
                                               Model m
                                               ) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("isFreitext", false);
            m.addAttribute("title", title);
            m.addAttribute("multipleChoiceAufgabeForm", form);
            return "aufgabehinzufuegen";
        }

        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        appServices.addMultipleChoice(form, decodedTitle);
        return "forward:/quizansicht/" + title;
    }

    @PostMapping("/{title}/aufgabe/{aufgabeId}/delete")
    public String deleteAufgabe(@PathVariable String title, @PathVariable UUID aufgabeId) {
        System.out.println("Lösche Aufgabe mit ID: " + aufgabeId + " aus Quiz: " + title);
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        appServices.deleteAufgabe(decodedTitle, aufgabeId);
        return "forward:/quizansicht/" + title;
    }

    @GetMapping("/{title}/aufgabe/{aufgabeId}/edit")
    public String editAufgabe(@PathVariable String title, @PathVariable UUID aufgabeId, Model m){
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        Aufgaben aufgabe = appServices.getAufgabeById(aufgabeId);
        if (aufgabe instanceof FreitextAufgabe){
            m.addAttribute("isFreitext", true);
            m.addAttribute("aufgabe", aufgabe);
        } else if (aufgabe instanceof MultipleChoiceAufgabe){
            m.addAttribute("isFreitext", false);
            m.addAttribute("aufgabe", aufgabe);
        }
        m.addAttribute("title", decodedTitle);
        return "aufgabeeditieren";
    }

    @PostMapping("/{title}/aufgabe/{aufgabeId}/edit")
    public String updateAufgabe(@PathVariable String title,
                                @PathVariable UUID aufgabeId,
                                @RequestParam String frage,
                                @RequestParam(required = false) Double maximalPunkte){
        Quiz quiz = appServices.getQuizByTilte(title);
        quiz.updateAufgabe(aufgabeId, frage, maximalPunkte);

        return "forward:/quizansicht/" + title;
    }

    @PostMapping("/{title}/uebersicht")
    public String uebersicht(@PathVariable String title, Model m){
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        Quiz quiz = appServices.getQuizByTilte(decodedTitle);
        m.addAttribute("title", quiz.getTitle());
        m.addAttribute("aufgaben", quiz.getAufgaben());
        m.addAttribute("gesamtPunktzahl", quiz.getMaximalPunktzahl());
        //m.addAttribute("bewertungsstand", quiz.getBewertungsstand());

        return "uebersicht";
    }

    @PostMapping("/{title}/save")
    public String quizSpeichern(@PathVariable String title){
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        Quiz quiz = appServices.getQuizByTilte(decodedTitle);
        //appServices.saveQuiz(quiz);
        return "redirect:/quizerstellung";
    }
}
