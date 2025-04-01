package org.roperie.exambyte.ui.controllers;

import jakarta.servlet.http.HttpSession;
import org.roperie.exambyte.application.services.QuizApplicationServices;
import org.roperie.exambyte.domain.Abgabe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/alle-abgaben")
public class AufgabenKorrekturUI {

    private final QuizApplicationServices appService;

    public AufgabenKorrekturUI(QuizApplicationServices appService) {
        this.appService = appService;
    }


    @GetMapping("")
    public String alleAbgabenAnzeigen(HttpSession session, Model model) {
        List<Abgabe> abgaben = appService.getAllAbgaben();
        model.addAttribute("abgaben", abgaben);
        return "alle-abgaben";
    }

    @GetMapping("/{studentname}/abgabe={abgabeId}")
    public String aufAbgabeZugreifen(@PathVariable String studentname, @PathVariable String abgabeId, Model model) {
        Abgabe abgabe = appService.getAbgabeById(studentname, abgabeId);
        if(abgabe == null) {
            return "redirect:/alle-abgaben";
        }
        Map<String, String> freitextantwortenMitFragen = transformMap(abgabe.getFreitextAnswer(), abgabe);

        model.addAttribute("abgabe", abgabe);
        model.addAttribute("studentname", studentname);
        model.addAttribute("abgabeId", abgabeId);
        model.addAttribute("freitextantwortenMitFragen", freitextantwortenMitFragen);
        model.addAttribute("korrektur", new HashMap<>());
        return "korrektur";
    }

    /*
    @PostMapping("/{studentname}/abgabe={abgabeId}")
    public String korrekturUndPunktzahlEintragen(@PathVariable String studentname,
                                                 @PathVariable String abgabeId,
                                                 @RequestParam Map<String, String> korrektur,
                                                 @RequestParam double vergebenePunktzahl){
        Abgabe abgabe = appService.getAbgabeById(studentname, abgabeId);
        if (abgabe == null) {
            return "redirect:/alle-abgaben";
        }
        // Update the korrektur map
        Map<UUID, String> updatedKorrektur = new HashMap<>();
        for (Map.Entry<String, String> entry : korrektur.entrySet()) {
            UUID key = UUID.fromString(entry.getKey());
            updatedKorrektur.put(key, entry.getValue());
        }
        abgabe.setKorrektur(updatedKorrektur);

        // Update the vergebenePunktzahl
        abgabe.setVergebenePunktzahl(vergebenePunktzahl);

        // Save the updated Abgabe object
        appService.updateAbgabeWithKorrektur(abgabe);
        return "redirect:/alle-abgaben";
    }*/

    private Map<String, String> transformMap(Map<UUID, String> freitextantworten, Abgabe abgabe){
        Map<String, String> freitextantwortenMitFragen = new LinkedHashMap<>();
        for (Map.Entry<UUID, String> entry : abgabe.getFreitextAnswer().entrySet()) {
            String frage = appService.getFrageById(entry.getKey());
            freitextantwortenMitFragen.put(frage, entry.getValue());
        }
        return freitextantwortenMitFragen;
    }

}
