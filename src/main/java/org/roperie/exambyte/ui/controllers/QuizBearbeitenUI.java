package org.roperie.exambyte.ui.controllers;

import jakarta.servlet.http.HttpSession;
import org.roperie.exambyte.application.services.QuizApplicationServices;
import org.roperie.exambyte.domain.Abgabe;
import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.ui.forms.AbgabeForm;
import org.roperie.exambyte.ui.forms.MultipleChoiceAufgabeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quiz/{title}")
public class QuizBearbeitenUI {

    private final QuizApplicationServices appServices;


    public QuizBearbeitenUI(QuizApplicationServices appServices) {
        this.appServices = appServices;
    }

    @GetMapping()
    public String quizBearbeiten(@PathVariable String title, Model m) {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        m.addAttribute("title", decodedTitle);

        Quiz quiz = appServices.getQuizByTilte(decodedTitle);
        m.addAttribute("quiz", quiz);

        m.addAttribute("quizTitle", title);

        m.addAttribute("abgabeForm", new AbgabeForm());

        return "bearbeiten";
    }

    @PostMapping("/submit")
    public String submit(@PathVariable String title, Model model, AbgabeForm abgabeForm, HttpSession session) {
        String decodedTitle = URLDecoder.decode(title, StandardCharsets.UTF_8);
        String studentenname = (String) session.getAttribute("username");
        //appServices.addAbgabe(abgabeForm, decodedTitle, studentenname);

        return "redirect:/quizanzeige";
    }

}