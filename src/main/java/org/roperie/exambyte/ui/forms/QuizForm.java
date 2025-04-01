package org.roperie.exambyte.ui.forms;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class QuizForm {

    @NotNull
    @Size(min=1, message="Der Titel darf nicht leer sein")
    private String title;

    @NotNull
    @Future(message = "Die Startzeit darf nicht in der Vergangenheit liegen")
    private LocalDateTime startzeit;

    @NotNull
    @Future(message = "Die Endzeit muss in der Zukunft liegen")
    private LocalDateTime endzeit;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartzeit() { return startzeit; }

    public void setStartzeit(LocalDateTime startzeit) { this.startzeit = startzeit; }

    public LocalDateTime getEndzeit() { return endzeit; }

    public void setEndzeit(LocalDateTime endzeit) { this.endzeit = endzeit; }

}
