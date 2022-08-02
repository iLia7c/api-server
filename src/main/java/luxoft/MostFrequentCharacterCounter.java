package luxoft;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentCharacterCounter implements CharacterCounter {
    private String message;
    public MostFrequentCharacterCounter(String message) {
        this.message = message;
    }

    public String getCharacter()  {
        if (message == null) {
            throw new RuntimeException();
        }

        return fastGetImplementation();
    }

    private String fastGetImplementation() {
        char[] characters = message.toCharArray();
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : characters) {
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }
        int max = 0;
        Character needed = null;
        for (Character c : frequencyMap.keySet()) {
            if (frequencyMap.get(c) > max && !isWhiteSpace(c)) {
                needed  = c;
                max = frequencyMap.get(c);
            }
        }

        return String.valueOf(needed);
    }

    public boolean isWhiteSpace(Character c) {
        return c.equals(' ') || c.equals('\t') || c.equals('\n') || c.equals('\r');
    }

}
