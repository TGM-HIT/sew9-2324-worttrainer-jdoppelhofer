package sew9.worttrainer.jdoppelhofer;

public class Controller {
    private Rechtsschreibtrainer rechtsschreibtrainer;
    private GUI gui;

    public Controller(Rechtsschreibtrainer rechtsschreibtrainer) {
        this.rechtsschreibtrainer = rechtsschreibtrainer;
    }

    public void check(String wort) {
        if (rechtsschreibtrainer.raten(wort)) {
            gui.updateImage(rechtsschreibtrainer.getAktuellesWort());
        }
    }

    public Wort getWort() {
        return rechtsschreibtrainer.getAktuellesWort();
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
    }
}
