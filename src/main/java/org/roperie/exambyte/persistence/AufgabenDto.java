package org.roperie.exambyte.persistence;

import org.springframework.data.relational.core.mapping.Table;

@Table("aufgaben")
public record AufgabenDto(String frage) { }

