package org.roperie.exambyte;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.roperie.exambyte.application.services.QuizApplicationServices;
import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.services.QuizRepository;
import org.roperie.exambyte.services.QuizVerwaltungService;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuizErstellungTest {

    private QuizRepository quizRepository;
    private QuizVerwaltungService quizVerwaltungService;
    private QuizApplicationServices appService;

    @BeforeEach
    void setUp(){
        quizRepository = mock(QuizRepository.class);
        quizVerwaltungService = new QuizVerwaltungService(quizRepository);
        appService = new QuizApplicationServices(quizRepository);
    }

    @Test
    @DisplayName("Prüfe, ob alle Test vorhanden sind, sobald sie erstellt wurden")
    void test_alleQuizzesVorhanden(){

        //Arrange
        Quiz q1 = new Quiz("Test1");
        Quiz q2 = new Quiz("Test2");
        List<Quiz> mockQuizzes = Arrays.asList(q1, q2);

        when(quizRepository.findAll()).thenReturn(mockQuizzes);

        //Act
        List<Quiz> result = appService.alleQuizzes();

        //Assert
        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getTitle());
        assertEquals("Test2", result.get(1).getTitle());
    }
}
