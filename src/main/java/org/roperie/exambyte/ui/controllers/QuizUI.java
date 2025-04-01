package org.roperie.exambyte.ui.controllers;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.roperie.exambyte.application.services.QuizApplicationServices;
import org.roperie.exambyte.config.OrganisatorOnly;
import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.ui.forms.QuizForm;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class QuizUI {

    private final QuizApplicationServices appServices;

    public QuizUI(QuizApplicationServices appServices) {
        this.appServices = appServices;
    }

    @GetMapping("/")
    public String start(OAuth2AuthenticationToken auth, HttpSession session) {
        //getUsernameFromGitHub(auth, m);
        saveUsernameInSession(auth, session);
        return "redirect:/startseite";
    }

    @GetMapping("/startseite")
    public String startseite(HttpSession session, Model m) {
        //getUsernameFromGitHub(auth, m);
        String username = (String) session.getAttribute("username");
        m.addAttribute("name", username);
        return "startseite";
    }

    @GetMapping("/quizanzeige")
    public String quizanzeige(Model m) {
        List<Quiz> quizzes = appServices.alleQuizzes();
        m.addAttribute("quizzes", quizzes);
        return "quizanzeige";
    }

    @PostMapping("/quizanzeige")
    public String quizanzeige() {
        return "quizanzeige";
    }

    @GetMapping("/quizerstellung")
    @OrganisatorOnly
    public String quizerstellung() { return "quizerstellung"; }

    @GetMapping("/quizerstellung/title")
    @OrganisatorOnly
    public String quizerstellungTitle(Model m) {
        m.addAttribute("quizForm", new QuizForm());
        return "quiztitle";

    }

    @PostMapping("/quizerstellung/title")
    @OrganisatorOnly
    public String quizerstellungTitle(@Valid QuizForm quizForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("quizForm", quizForm);
            return "quiztitle";
        }

        if(quizForm.getEndzeit().isBefore(quizForm.getStartzeit())) {
            bindingResult.rejectValue("endzeit", "error.quizForm", "Die Endzeit muss nach der Startzeit liegen.");
            model.addAttribute("quizForm", quizForm);
            return "quiztitle";
        }

        String encodedTitle = URLEncoder.encode(quizForm.getTitle(), StandardCharsets.UTF_8);
        Quiz quiz = new Quiz(quizForm.getTitle());
        quiz.setStartzeit(quizForm.getStartzeit());
        quiz.setEndzeit(quizForm.getEndzeit());
        appServices.saveQuiz(quiz);
        return "redirect:/quizansicht/" + encodedTitle;
    }

    /*private void getUsernameFromGitHub(OAuth2AuthenticationToken auth, Model m){
        String login = auth.getPrincipal().getAttribute("login");
        m.addAttribute("name", login);
    }*/

    private void saveUsernameInSession(OAuth2AuthenticationToken auth, HttpSession session){
        String name = auth.getPrincipal().getAttribute("login");
        session.setAttribute("username", name);
    }
}
