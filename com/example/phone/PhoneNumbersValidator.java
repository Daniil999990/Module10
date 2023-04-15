package com.example.phone;

import java.io.*;

class NumberPhone {
    public void readNumber(File file){
        int c;
        StringBuilder pN = new StringBuilder();
        try (FileReader fr = new FileReader(file)) {
            while ((c = fr.read()) != -1) {
                pN.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] pNs = pN.toString().split("\n");
        StringBuilder newString = new StringBuilder();
        String x = "^\\d{3}-\\d{3}-\\d{4}$";
        String a = "\\(\\d{3}\\) \\d{3}-\\d{4}";
        for (String p : pNs) {
            if (p.matches(x) || p.matches(a)) {
                newString.append(p);
                newString.append("\n");
            }
        }
        System.out.println(newString);
    }

    public static void main(String[] args) {
        File file = new File("files/test.txt");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("""
                    987-123-4567
                    123 456 7890
                    (123) 456-7890""");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NumberPhone numberPhone = new NumberPhone();
        numberPhone.readNumber(file);
    }
}