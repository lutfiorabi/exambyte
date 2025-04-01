# Exambyte
**Exambyte** soll ein Illias Testsystem sein für die Klausurzulassung in Programmierpraktikum.
Er soll Testausführung, manuelle Korrektur durch Korrektoren, Ergebnisse anzeigen für Studierende und Organisator:innen.

**Implementierung:**
- **Technologie:** Webanwendung mit Java und Spring Framework.
- **Datenbank:** Technologie wird später bekanntgegeben.

## Funktionen:
- **Userverwaltung:**
    - **Authentifizierung:** Über GitHub.
    - **Autorisierung:** Zusätzlich zur Standardrolle für Studierende gibt es spezielle Rollen für Korrektoren und Organisatoren.
    - **Rollenänderung:** Über eine Konfigurationsdatei ohne Neukompilierung der Anwendung.


- **Korrection und Bewertung:**
    - **Korrektoren:** Zugriff auf zugeteilte Freitextantworten, Bewertung mit Punkten.
    - **Organisatoren:** Zugriff auf alle Antworten, Änderungen der Punktzahlen, und Verwaltung des Teststatus.

## Testtypen:
- **Multiple-Choice-Fragen (MC):** Fragen mit vorgegebenen Antwortmöglichkeiten
- **Freitextfragen:** Antworten werden in einem Textfeld eingegeben

$\rightarrow$ Lösungsvorschläge werden nach Testende angezeigt.

## Testverwaltung:
- **Test erstellen:** Startzeitpunkt, Endzeitpunkt und Ergebnis-Veröffentlichungszeitpunkt setzen.
- **Testdurchführung:** Fragen sind ab Startzeitpunkt sichtbar und bis Endzeitpunkt bearbeitbar.
- **Testbewertung:** Automatische Auswertung der MC-Fragen; Freitextfragen werden manuell bewertet.
- **Ergebnisanzeige:** Ergebnisse werden nach dem Ergebnis-Veröffentlichungszeitpunkt angezeigt.

## Darstellung der Prüfungsergebnisse und -zustände:
- **Zulassungsstatus:** Übersicht über den aktuellen Prüfungsstatus der Studierenden.
- **Korrekturstand:** Organisatoren haben eine Übersicht über den aktuellen Korrekturstand, um Verzögerungen zu erkennen.
- **Überblick über Ergebnisse:** Analysen zu den Resultaten der Studierenden für Organisatoren. Identifikation von Problemen in Fragestellungen möglich.
