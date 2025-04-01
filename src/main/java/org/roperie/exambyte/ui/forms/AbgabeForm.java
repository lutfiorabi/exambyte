package org.roperie.exambyte.ui.forms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AbgabeForm {

    private Map<UUID, String> freitextAnswers = new HashMap<>();

    private Map<UUID, Set<Integer>> multipleAnswers = new HashMap<>();

    public Map<UUID, String> getFreitextAnswers() {
        return freitextAnswers;
    }

    public void setFreitextAnswers(Map<UUID, String> freitextAnswers) {
        this.freitextAnswers = freitextAnswers;
    }

    public Map<UUID, Set<Integer>> getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(Map<UUID, Set<Integer>> multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }
}
