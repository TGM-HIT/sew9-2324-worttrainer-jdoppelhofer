package sew9.worttrainer.jdoppelhofer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Diese Klasse stellt die GUI dar.
 * Sie ist für die Darstellung des Bildes und des Eingabefelds zuständig.
 * @author Julian Doppelhofer
 */
public class GUI {
    private Controller controller;
    private JFrame frame;
    private JPanel imagePanel = new JPanel();
    private JTextField textField;
    private JLabel statistikLabel;

    public GUI(Controller controller) {
        this.controller = controller;

        frame = new JFrame("Rechtsschreibtrainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Menü
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Datei");
        JMenuItem speichernItem = new JMenuItem("Speichern");
        JMenuItem ladenItem = new JMenuItem("Laden");

        speichernItem.addActionListener(e -> controller.speichern());
        ladenItem.addActionListener(e -> controller.laden());

        menu.add(speichernItem);
        menu.add(ladenItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Bild Panel
        imagePanel = new JPanel();
        updateImage(controller.getWort());

        // Eingabefeld und Statistik
        JPanel inputPanel = new JPanel(new BorderLayout());
        textField = new JTextField(25);
        textField.addActionListener(e -> {
            controller.check(textField.getText());
            textField.setText(""); // Textfeld leeren nach der Eingabe
        });

        statistikLabel = new JLabel("Statistik: 0 Versuche, 0 Richtig, 0 Falsch");
        inputPanel.add(statistikLabel, BorderLayout.NORTH);
        inputPanel.add(textField, BorderLayout.CENTER);

        // Hinzufügen der Panels zum Fenster
        frame.add(imagePanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Fenster anzeigen
        frame.pack();
        frame.setVisible(true);
    }


    /**
     * Aktualisiert das Bild.
     * @param wort Das aktuelle Wort
     */
    public void updateImage(Wort wort) {
        imagePanel.removeAll(); // Altes Bild entfernen
        try {
            Image image = ImageIO.read(new URL(wort.getUrl())).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(image)));
        } catch (Exception e) {
            imagePanel.add(new JLabel("Bild konnte nicht geladen werden: " + e.getMessage()));
        }
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    /**
     * Setzt die Statistik.
     * @param insgesamt Anzahl der Versuche
     * @param richtig Anzahl der richtigen Versuche
     * @param falsch Anzahl der falschen Versuche
     */
    public void updateStatistik(int insgesamt, int richtig, int falsch) {
        statistikLabel.setText("Statistik: " + insgesamt + " Versuche, " + richtig + " Richtig, " + falsch + " Falsch");
    }
}