package sew9.worttrainer.jdoppelhofer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WortTest {
    @Test
    void Test1() {
        Wort wort = new Wort("Test", "https://www.test.com/");
        assertEquals("Test", wort.getWort());
        assertEquals("https://www.test.com/", wort.getUrl());
    }

    @Test
    void Test2() {
        assertThrows(IllegalArgumentException.class, () -> {new Wort(null, null);});
    }

    @Test
    void Test3() {
        assertThrows(IllegalArgumentException.class, () -> {new Wort("", "");});
    }
}
