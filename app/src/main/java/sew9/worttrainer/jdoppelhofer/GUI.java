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
    private JPanel imagePanel = new JPanel();
    public GUI(Controller controller) {
        this.controller = controller;

        // Erstellen des Fensters
        JFrame frame = new JFrame("Rechtsschreibtrainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        try {
            Wort wort = controller.getWort();
            Image image = ImageIO.read(new URL(wort.getUrl())).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(image)));   // Bild hinzufügen
        } catch (Exception e) {
            imagePanel.add(new JLabel("Bild konnte nicht geladen werden: " + e.getMessage()));
        }

        // Erstellen des Eingabefelds
        JPanel inputPanel = new JPanel();
        JTextField textField = new JTextField(25);
        textField.addActionListener(e -> controller.check(textField.getText()));    // Bei Enter-Taste check() aufrufen
        inputPanel.add(textField);

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
        // Neues Bild laden
        try {
            Image image = ImageIO.read(new URL(wort.getUrl())).getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(image)));
        } catch (Exception e) {
            imagePanel.add(new JLabel("Bild konnte nicht geladen werden: " + e.getMessage()));
        }
        imagePanel.revalidate();   // Neuzeichnen
        imagePanel.repaint();     // Neuzeichnen
    }
}