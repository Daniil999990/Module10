package org.example.word;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class wordsWord {
    private  HashMap<String, Integer> hashMap;

    public void read(){
        try (FileReader fileReader = new FileReader("files/words.txt")){
            Scanner scanner = new Scanner(fileReader);
            String a = "";
            String x = "";
            hashMap = new HashMap<>();
            int count = 0;
            while (scanner.hasNextLine()){

                a += scanner.next() + " ";
                x = a;
            }

            String[] array = a.split(" ");
            for (int i = 0; i < array.length; i++) {
                if (hashMap.containsKey(array[i])){
                    hashMap.put(array[i], hashMap.get(array[i]) + 1);
                }else hashMap.put(array[i], 1);
            }
            hashMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(System.out::println);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        org.example.word.wordsWord wordOfWords = new org.example.word.wordsWord();
        wordOfWords.read();
    }
}