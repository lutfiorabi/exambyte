package org.roperie.exambyte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class UIQuizErstellungTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Die Erstellungsseite ist über /quizerstellung erreichbar")
    void test_quizerstellung() throws Exception{
        mvc.perform(get("/quizerstellung"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("quizerstellung"));
    }

    @Test
    @DisplayName("Der Button Quiz erstellen leitet den User zur richtigen Seite und die Seite ist erreichbar")
    void test_quizerstellenButton() throws Exception{
        mvc.perform(get("/quizerstellung"))
                .andExpect(status().isOk())
                .andExpect(xpath("//a[@href='quizerstellung/title']/button").exists());

        mvc.perform(get("/quizerstellung/title"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("quiztitle"));
    }

    @Test
    @DisplayName("Es erscheint eine Fehlermeldung, wenn kein Titel übergeben wird")
    void test_keinTitelUebergeben() throws Exception {
        mvc.perform(post("/quizerstellung/title")
                .param("title", "") //simuliert ein leeres Feld
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("quizForm", "title")) //Validierungsfehler für title
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Der Titel darf nicht leer sein"))); //Error message tritt auf
    }

    @Test
    @DisplayName("Test-Titel wird richtig eingetragen")
    void test_richtigerTitel() throws Exception {

        String title = "Test des Grauens";

        mvc.perform(post("/quizerstellung/title")
                .param("title", title) // simuliere gültigen Titel und ob richtig encoded wird
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().is3xxRedirection()) //Erfolgreich: Redirect
                .andExpect(redirectedUrl("/quizansicht/Test+des+Grauens")); //URL nach redirect prüfen
    }

    @Test
    @DisplayName("Validierung schlägt fehl und bleibt auf Seite")
    void test_validierungSchlaegtFehlUndBleibtAufSeite() throws Exception {
        mvc.perform(post("/quizerstellung/title")
                .param("title", "") // ungültig
                .contentType("application/x-www-form-urlencoded"))
                .andExpect(status().isOk()) // Keine Weiterleitung
                .andExpect(view().name("quiztitle")) // Bleibt auf der Seite
                .andExpect(model().attributeHasFieldErrors("quizForm", "title")); // Validierungsfehler vorhanden
    }




}
