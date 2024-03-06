package xyz.ipiepiepie.test_5;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Выбрать (в виде списка) из текста все предложения, в которых есть повторяющиеся слова.
 * Учесть, что регистр букв в одинаковых словах может отличаться.
 * Словом считается непрерывная последовательность символов (строчных и прописных) А-Я, A-Z и цифр.
 * Концом предложения считаются символы точка, '!' и '?'.
 * Началом предложения – любой символ, отличный от данных трех и пробела, первый после конца предыдущего предложения (или вообще первый в тексте).
 */
public class Main {
    
    public static void main(String... args) {
        System.out.println(process("abc gb. abc d! lol D"));
    }
    
    private static Map<String, List<String>> process(String text) {
        String[] proposals = text.split("[.?!]");
        Map<String, List<String>> repeats = new HashMap<>();
        
        for (String proposal : proposals) {
            // iterate over words in proposal
            for (String word : proposal.split(" ")) {
                List<String> localRepeats = new ArrayList<>();
                
                // skip spaces and blank words
                if (word.isBlank()) continue;
                
                // iterate over another proposals
                for (String searchable : proposals) {
                    if (count(searchable, word) > 0) localRepeats.add(searchable);
                }
                
                // add local repeats to repeats if it occurs more than one time
                if (localRepeats.size() > 1)
                    repeats.put(word.toLowerCase(), localRepeats);
            }
        }
        
        return repeats;
    }
    
    private static int count(String text, String searchable) {
        int count = 0;
        // make searchable pattern matcher
        Matcher m = Pattern.compile(searchable, Pattern.CASE_INSENSITIVE).matcher(text);
        
        // count occurrences
        while (m.find()) count++;
        
        return count;
    }
}
