package org.roperie.exambyte.services;

import org.roperie.exambyte.domain.quiz.Quiz;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository {

    Collection<Quiz> findAll();

    Optional<Quiz> findById(UUID id);

    Quiz save(Quiz quiz);
}
