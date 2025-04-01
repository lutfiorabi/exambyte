package org.roperie.exambyte.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface QuizDataRepository extends CrudRepository<QuizDto, Integer> {

    Collection<QuizDto> findAll();

    QuizDto save(QuizDto quiz);

}
