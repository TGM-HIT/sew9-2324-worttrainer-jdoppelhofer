package sew9.worttrainer.jdoppelhofer;

/**
 * Diese Klasse beinhaltet die Wörter und die Statistik des Rechtsschreibtrainers.
 */
public class Rechtsschreibtrainer {
    private Wort[] woerter; //Array mit allen Wörtern
    private Wort aktuellesWort; //Das aktuelle Wort
    private int insgesamtWorte; //Anzahl aller geratenen Wörter
    private int richtigeWorte; //Anzahl aller richtig geratenen Wörter
    private int falscheWorte; //Anzahl aller falsch geratenen Wörter

    public Rechtsschreibtrainer(Wort[] woerter) {
        this.woerter = woerter;
        wortWaehlen();
    }

    /**
     * Wählt ein zufälliges Wort aus.
     */
    public void wortWaehlen() {
        int index = (int) (Math.random() * woerter.length);
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
    }

    /**
     * Gibt zurück, ob das Wort richtig geraten wurde.
     * @param wort Das geratene Wort
     * @return true, wenn das Wort richtig geraten wurde, sonst false
     */
    public boolean raten(String wort) {
        if (wort == null || wort.isEmpty())
            throw new IllegalArgumentException();
        if (wort.equals(aktuellesWort.getWort())) {
            richtigeWorte++;
            return true;
        }
        falscheWorte++;
        return false;
    }

    public String getAktuellesWort() {
        return aktuellesWort.getWort();
    }

    public String getUrl() {
        return aktuellesWort.getUrl();
    }
}
