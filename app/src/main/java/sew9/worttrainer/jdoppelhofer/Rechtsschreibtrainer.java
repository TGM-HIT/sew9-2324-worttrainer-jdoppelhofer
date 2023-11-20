package sew9.worttrainer.jdoppelhofer;

import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse beinhaltet die Wörter und die Statistik des Rechtsschreibtrainers.
 * @author Julian Doppelhofer
 */
public class Rechtsschreibtrainer {
    private Wort[] woerter; //Array mit allen Wörtern
    private Wort aktuellesWort; //Das aktuelle Wort
    private Set<Integer> ausgewaehlteWoerter; // Set mit allen bereits ausgewählten Wörtern
    private int insgesamtWorte; //Anzahl aller geratenen Wörter
    private int richtigeWorte; //Anzahl aller richtig geratenen Wörter
    private int falscheWorte; //Anzahl aller falsch geratenen Wörter

    public Rechtsschreibtrainer(Wort[] woerter) {
        this.woerter = woerter;
        ausgewaehlteWoerter = new HashSet<>();
        wortWaehlen();
    }

    /**
     * Wählt ein zufälliges Wort aus.
     */
    public void wortWaehlen() {
        if (ausgewaehlteWoerter.size() >= woerter.length) {
            // Alle Wörter wurden bereits ausgewählt
            ausgewaehlteWoerter.clear(); // Zurücksetzen, um von vorn zu beginnen
        }

        int index;
        do {
            index = (int) (Math.random() * woerter.length);
        } while (ausgewaehlteWoerter.contains(index));

        wortWaehlen(index);
    }

    /**
     * Wählt ein Wort aus.
     * @param index Index des Wortes
     */
    public void wortWaehlen(int index) {
        if (index < 0 || index >= woerter.length)
            throw new IllegalArgumentException();
        aktuellesWort = woerter[index];
        ausgewaehlteWoerter.add(index);
    }

    /**
     * Gibt zurück, ob das Wort richtig geraten wurde.
     * @param wort Das geratene Wort
     * @return true, wenn das Wort richtig geraten wurde, sonst false
     */
    public boolean raten(String wort) {
        insgesamtWorte++; // Statistik für jeden Versuch aktualisieren
        if (wort == null || wort.isEmpty())
            throw new IllegalArgumentException();
        if (wort.equals(aktuellesWort.getWort())) {
            richtigeWorte++;
            wortWaehlen();
            return true;
        }
        falscheWorte++;
        return false;
    }

    /**
     * Gibt das aktuelle Wort zurück.
     * @return Das aktuelle Wort
     */
    public Wort getAktuellesWort() {
        return aktuellesWort;
    }

    public Wort[] getWoerter() {
        return woerter;
    }

    public int getInsgeamtWorte() {
        return insgesamtWorte;
    }

    public int getRichtigeWorte() {
        return richtigeWorte;
    }

    public int getFalscheWorte() {
        return falscheWorte;
    }

    /**
     * Setzt die Statistik.
     * @param insgesamt Anzahl aller geratenen Wörter
     * @param richtig Anzahl aller richtig geratenen Wörter
     * @param falsch Anzahl aller falsch geratenen Wörter
     */
    public void setStatistik(int insgesamt, int richtig, int falsch) {
        this.insgesamtWorte = insgesamt;
        this.richtigeWorte = richtig;
        this.falscheWorte = falsch;
    }
}
