package sew9.worttrainer.jdoppelhofer;

/**
 * Diese Klasse beinhaltet das Wort und die URL zum Bild des Wortes.
 * @author Julian Doppelhofer
 */
public class Wort {
    private String wort;
    private String url;

    public Wort(String wort, String url) {
        if (wort == null || wort.isEmpty() || url == null || url.isEmpty()) //Prüft ob wort oder url null oder leer ist
            throw new IllegalArgumentException();   //Wenn ja, dann wird eine IllegalArgumentException geworfen
        this.wort = wort;
        this.url = url;
    }

    public String getWort() {
        return wort;
    }

    public String getUrl() {
        return url;
    }
}
