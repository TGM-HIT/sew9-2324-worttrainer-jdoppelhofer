package sew9.worttrainer.jdoppelhofer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RechtsschreibtrainerTest {
    @Test
    public void Test1() {
        Rechtsschreibtrainer rechtsschreibtrainer = new Rechtsschreibtrainer(new Wort[] {
                new Wort("Test", "https://www.test.com/"),
                new Wort("Test2", "https://www.test2.com/")
        });
        rechtsschreibtrainer.wortWaehlen(0);
        assertTrue(rechtsschreibtrainer.raten("Test"));
    }

    @Test
    public void Test2() {
        Rechtsschreibtrainer rechtsschreibtrainer = new Rechtsschreibtrainer(new Wort[] {
                new Wort("Test", "https://www.test.com/"),
                new Wort("Test2", "https://www.test2.com/")
        });
        rechtsschreibtrainer.wortWaehlen(0);
        assertFalse(rechtsschreibtrainer.raten("Test2"));
    }

    @Test
    public void Test3() {
        Rechtsschreibtrainer rechtsschreibtrainer = new Rechtsschreibtrainer(new Wort[] {
                new Wort("Test", "https://www.test.com/"),
                new Wort("Test2", "https://www.test2.com/")
        });
        assertThrows(IllegalArgumentException.class, () -> {rechtsschreibtrainer.wortWaehlen(-1);});
        assertThrows(IllegalArgumentException.class, () -> {rechtsschreibtrainer.wortWaehlen(3);});
    }
}
