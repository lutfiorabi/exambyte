package org.roperie.exambyte;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ExamByteApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("Die Startseite ist über /startseite erreichbar")
    void test_startseite() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/startseite"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("startseite"));
    }

    @Test
    @DisplayName("Bei / redirect zu /startseite")
    void test_startRedirect() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/startseite"));
    }

    @Test
    @DisplayName("Die Webseite ist über /testanzeige erreichbar")
    void test_testanzeige() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/testanzeige"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testanzeige"));
    }

}
