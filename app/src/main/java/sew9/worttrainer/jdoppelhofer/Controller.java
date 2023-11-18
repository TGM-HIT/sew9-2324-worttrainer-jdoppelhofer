package sew9.worttrainer.jdoppelhofer;

/**
 * Diese Klasse ist der Controller des MVC-Patterns.
 * @author Julian Doppelhofer
 */
public class Controller {
    private Rechtsschreibtrainer rechtsschreibtrainer;
    private GUI gui;
    private Speichern speichern;

    public Controller(Rechtsschreibtrainer rechtsschreibtrainer, Speichern speichern) {
        this.rechtsschreibtrainer = rechtsschreibtrainer;
        this.speichern = speichern;
    }

    /**
     * Überprüft das eingegebene Wort.
     * @param wort Das eingegebene Wort
     */
    public void check(String wort) {
        boolean richtig = rechtsschreibtrainer.raten(wort);
        if (!richtig) {
            rechtsschreibtrainer.wortWaehlen(); // Zum nächsten Wort wechseln, wenn falsch geraten
        }
        gui.updateImage(rechtsschreibtrainer.getAktuellesWort());   // Bild aktualisieren
        // Statistik aktualisieren
        gui.updateStatistik(rechtsschreibtrainer.getInsgeamtWorte(),
                rechtsschreibtrainer.getRichtigeWorte(),
                rechtsschreibtrainer.getFalscheWorte());
    }

    public Wort getWort() {
        return rechtsschreibtrainer.getAktuellesWort();
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public void speichern() {
        speichern.speichern(rechtsschreibtrainer);
    }

    /**
     * Lädt den Rechtsschreibtrainer.
     */
    public void laden() {
        Rechtsschreibtrainer geladenerTrainer = speichern.laden();
        if (geladenerTrainer != null) {
            this.rechtsschreibtrainer = geladenerTrainer;
            // Aktualisieren Sie die GUI mit den neuen Daten
            gui.updateImage(rechtsschreibtrainer.getAktuellesWort());
            gui.updateStatistik(rechtsschreibtrainer.getInsgeamtWorte(),
                    rechtsschreibtrainer.getRichtigeWorte(),
                    rechtsschreibtrainer.getFalscheWorte());
        }
    }
}