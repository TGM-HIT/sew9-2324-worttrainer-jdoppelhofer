package sew9.worttrainer.jdoppelhofer;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Diese Klasse speichert und ladet den Trainer
 * @author Julian Doppelhofer
 */
public class Speichern {
        private String dateipfad;

        public Speichern(String dateipfad) {
            this.dateipfad = dateipfad;
        }

    /**
     * Speichert den Trainer in eine JSON-Datei
     * @param trainer Der Trainer
     */
    public void speichern(Rechtsschreibtrainer trainer) {
        JSONObject json = new JSONObject();
        JSONArray wortArray = new JSONArray();

        // Alle Wörter in ein JSON-Array schreiben
        for (Wort wort : trainer.getWoerter()) {
            JSONObject wortObjekt = new JSONObject();
            wortObjekt.put("wort", wort.getWort());
            wortObjekt.put("url", wort.getUrl());
            wortArray.put(wortObjekt);
        }

        // Alle Daten in ein JSON-Objekt schreiben
        json.put("woerter", wortArray);
        json.put("insgesamtWorte", trainer.getInsgeamtWorte());
        json.put("richtigeWorte", trainer.getRichtigeWorte());
        json.put("falscheWorte", trainer.getFalscheWorte());

        // Index des aktuellen Wortes speichern
        if (trainer.getAktuellesWort() != null) {
            json.put("aktuellesWortIndex", Arrays.asList(trainer.getWoerter()).indexOf(trainer.getAktuellesWort()));
        } else {
            json.put("aktuellesWortIndex", -1);
        }

        // JSON-Objekt in Datei schreiben
        try (FileWriter file = new FileWriter(dateipfad)) {
            file.write(json.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt den Trainer aus einer JSON-Datei
     * @return Der Trainer
     */
    public Rechtsschreibtrainer laden() {
        try {
            // Dateiinhalt in String umwandeln
            String inhalt = new String(Files.readAllBytes(Paths.get(dateipfad)));
            JSONObject json = new JSONObject(inhalt);

            // JSON-Objekt in Trainer umwandeln
            JSONArray wortArray = json.getJSONArray("woerter");
            Wort[] woerter = new Wort[wortArray.length()];

            // Alle Wörter aus dem JSON-Array in ein Wort-Array schreiben
            for (int i = 0; i < wortArray.length(); i++) {
                JSONObject wortObjekt = wortArray.getJSONObject(i);
                String wort = wortObjekt.getString("wort");
                String url = wortObjekt.getString("url");
                woerter[i] = new Wort(wort, url);
            }

            // Trainer erstellen und Statistik setzen
            Rechtsschreibtrainer trainer = new Rechtsschreibtrainer(woerter);
            trainer.setStatistik(json.getInt("insgesamtWorte"), json.getInt("richtigeWorte"), json.getInt("falscheWorte"));

            // Aktuelles Wort setzen
            int aktuellesWortIndex = json.optInt("aktuellesWortIndex", -1);
            if (aktuellesWortIndex != -1 && aktuellesWortIndex < woerter.length) {
                trainer.wortWaehlen(aktuellesWortIndex);
            }

            return trainer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
