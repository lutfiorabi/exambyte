package org.roperie.exambyte.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table("quiz")
public record QuizDto(@Id Integer id,
                      String title,
                      String username,
                      double maximalePunktzahl,
                      boolean bestanden,
                      LocalDateTime startzeit,
                      LocalDateTime endzeit,
                      List<AufgabenDto> aufgaben) {
}

