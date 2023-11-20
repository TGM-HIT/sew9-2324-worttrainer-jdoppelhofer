package sew9.worttrainer.jdoppelhofer;

/**
 * Diese Klasse ist die Hauptklasse des Programms.
 * @author Julian Doppelhofer
 */
public class Main {
    public static void main(String[] args) {
        Speichern speichern = new Speichern("save.json");

        Rechtsschreibtrainer trainer = new Rechtsschreibtrainer(new Wort[] {
                new Wort("Hund", "https://img.freepik.com/vektoren-kostenlos/hund-im-einfachen-stil-des-gekritzels-auf-weissem-hintergrund_1308-87569.jpg"),
                new Wort("Katze", "https://img.freepik.com/vektoren-kostenlos/eine-aufklebervorlage-von-katzen-cartoon-figur_1308-73786.jpg?size=626&ext=jpg&ga=GA1.1.1826414947.1698969600&semt=ais")
        });

        Controller controller = new Controller(trainer, speichern);
        GUI gui = new GUI(controller);
        controller.setGUI(gui);
    }
}
