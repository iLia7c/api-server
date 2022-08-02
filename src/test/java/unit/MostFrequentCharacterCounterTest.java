package unit;

import luxoft.CharacterCounter;
import luxoft.MostFrequentCharacterCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MostFrequentCharacterCounterTest {
    CharacterCounter counter;

    @Test
    public void testPositive() {
        String message = "AA      B";

        counter = new MostFrequentCharacterCounter(message);

        Assertions.assertTrue(counter.getCharacter().equals("A"));
    }

    @Test
    public void testNegative() {
        String positive = null;

        counter = new MostFrequentCharacterCounter(positive);

        Exception thrown = assertThrows(
                Exception.class,
                () -> counter.getCharacter(),
                "Expected Exception, but it didn't"
        );
    }
}
