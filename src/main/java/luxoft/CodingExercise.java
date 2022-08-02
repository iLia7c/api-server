package luxoft;

public class CodingExercise {

    public static void main(final String[] args) {
        final String message = "Welcome to this interview!"
                + " What is the MOST frequent non-whitespace character in this String?"
                + " HINT: the return should be the character 'T' (guarantee result).";

        final CharacterCounter characterCounter = new MostFrequentCharacterCounter(message);

        System.out.println(characterCounter.getCharacter());

    }
}