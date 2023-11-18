package sew9.worttrainer.jdoppelhofer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpeichernTest {
    @Test
    void Test01() {
        Speichern speichern = new Speichern("test_trainer.json");
        Wort[] worte = new Wort[]{
                new Wort("Hund", "https://beispielbild.com/hund.jpg"),
                new Wort("Katze", "https://beispielbild.com/katze.jpg")
        };
        Rechtsschreibtrainer trainer = new Rechtsschreibtrainer(worte);

        // Speichern des Trainers
        speichern.speichern(trainer);

        // Laden des Trainers
        Rechtsschreibtrainer geladenerTrainer = speichern.laden();

        assertNotNull(geladenerTrainer);
        assertEquals(trainer.getInsgeamtWorte(), geladenerTrainer.getInsgeamtWorte());
        assertEquals(trainer.getRichtigeWorte(), geladenerTrainer.getRichtigeWorte());
    }

    @Test
    void Test02() {
        Speichern speichern = new Speichern("nichtda.json");
        assertNull(speichern.laden());
    }
}
