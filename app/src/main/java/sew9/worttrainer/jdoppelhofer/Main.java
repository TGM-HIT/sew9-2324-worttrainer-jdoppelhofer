package sew9.worttrainer.jdoppelhofer;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Speichern speicherStrategie = new Speichern("pfad/zur/datei.json");
        Rechtsschreibtrainer trainer = speicherStrategie.laden();

        if (trainer == null) {
            trainer = new Rechtsschreibtrainer(new Wort[] {
                    new Wort("Hund", "https://img.freepik.com/vektoren-kostenlos/hund-im-einfachen-stil-des-gekritzels-auf-weissem-hintergrund_1308-87569.jpg"),
                    new Wort("Katze", "https://img.freepik.com/vektoren-kostenlos/eine-aufklebervorlage-von-katzen-cartoon-figur_1308-73786.jpg?size=626&ext=jpg&ga=GA1.1.1826414947.1698969600&semt=ais")
            });
        }

        boolean weitermachen = true;
        boolean warRichtig = false;

        while (weitermachen) {
            Wort aktuellesWort = trainer.getAktuellesWort();
            String nachricht = "Statistik:\nRichtig: " + /* ... */ "\nFalsch: " + /* ... */
                    "\nIst das Bild von: " + aktuellesWort.getWort() +
                    "\nWar der letzte Versuch richtig? " + (warRichtig ? "Ja" : "Nein");

            //String eingabe = JOptionPane.showInputDialog(null, nachricht, "Wort raten", JOptionPane.PLAIN_MESSAGE);

            Controller controller = new Controller(trainer);
            GUI gui = new GUI(controller);
            controller.setGUI(gui);
        }

        speicherStrategie.speichern(trainer);
    }
}
