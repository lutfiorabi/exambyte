package org.roperie.exambyte.persistence;


import org.roperie.exambyte.domain.quiz.Quiz;
import org.roperie.exambyte.services.QuizRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDataRepository quizDataRepository;

    public QuizRepositoryImpl(QuizDataRepository quizDataRepository) {
        this.quizDataRepository = quizDataRepository;
    }

    @Override
    public Collection<Quiz> findAll() {
        return quizDataRepository.findAll()
                .stream()
                .map(this::toQuiz)
                .toList();
    }

    private Quiz toQuiz(QuizDto quizDto) {
        return new Quiz(quizDto.title());
    }


    @Override
    public Optional<Quiz> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Quiz save(Quiz quiz) {
        return null;
    }

}
