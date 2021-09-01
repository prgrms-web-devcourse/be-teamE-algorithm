import java.io.*;
import java.util.*;

public class Solution1830 {
    public static void main(String[] args) throws IOException {
        String[] sentences = { "HaEaLaLaObWORLDb", "HELLOWORLD", "HaEaLaLaObWORLDb", "aHbEbLbLbOacWdOdRdLdDc", //HELLOWORLD
                "aHELLOa bWORLDb", "HaEaLaLObWORLDb", "aHELLOWORLDa", "HaEaLaLaOWaOaRaLaD", "abHELLObaWORLD",
                "AxAxAxAoBoBoB", //invalid
                "SpIpGpOpNpGJqOqA" }; // SIGONG JOA
        String answer = "";

        for (String sentence : sentences) {
            answer = "";
            String[] spaceValid = sentence.split(" ");
            if (spaceValid.length > 1) {
                answer = "invalid";
                System.out.println(sentence + " is " + answer);
                continue;
            }

            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < sentence.length(); i++) {
                map.put(sentence.charAt(i), map.getOrDefault(sentence.charAt(i), 0) + 1);
            }

            calc(sentence, map);

            System.out.println(map.toString());

            System.out.println(sentence + " is " + answer);
        }

    }
    
    static String calc(String sentence, Map<Character, Integer> map) {
        String result = "";
        boolean temp = false;// 한단어인 상태
        Character special = '0';
        String sentenceTemp = sentence;

        if (Character.isLowerCase(sentence.charAt(0))) { //맨 앞글자가 소문자일 때 --> 규칙 2
            special = sentence.charAt(0);
            if (map.get(special) != 2)
                return "invalid";

            String[] splitRole2 = sentence.split(sentence.substring(0, 1));
            for (int i = 0; i < splitRole2.length; i++) {
                result += calc(splitRole2[i], map);
            }

        }
        

        if (Character.isUpperCase(sentence.charAt(0))) {// 대문자일 때 -->규칙1 Or not
            if(Character.isLowerCase(sentence.charAt(1))){//규칙1
                special = sentence.charAt(1);
                Integer lastSpecialIndex = 0;
                for (int i = 0; i < sentence.length(); i++) {
                    if (sentence.charAt(i) == special) {
                        lastSpecialIndex = i;
                    }
                }
                


            }else{ //nothing
                // for (int i = 0; i < sentence.length(); i++) {

                //     if (Character.isLowerCase(sentence.charAt(i))) {
                //         if (sentence.charAt(i) == special)
                //             break;
                //     }
                    
                //     result += sentence.charAt(i);
                // }
            }

        }
        return result;
    }
}
