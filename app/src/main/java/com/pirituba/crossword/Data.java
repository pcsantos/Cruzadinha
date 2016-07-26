package com.pirituba.crossword;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private String file;

    public Data(String file) {
        this.file = file;
    }

    public List<Content> loadData() {
        List<Content> listWords = new ArrayList<Content>();
        Content word9 = new Content(1, "ESCALENOS", "Triângulos com  três lados diferentes.", 10, 9, true);
        listWords.add(word9);
        Content word5 = new Content(2, "ANGULO", "Pode ser medido por um transferidor.", 31, 6, true);
        listWords.add(word5);
        Content word8 = new Content(3, "COLINEAR", "Número qualquer de pontos sobre uma\n mesma reta.", 60, 8, true);
        listWords.add(word8);
        Content word4 = new Content(4, "AFIM", "Função polinomial de grau 1.", 80, 4, true);
        listWords.add(word4);
        Content word10 = new Content(5, "RAIO", "Seu dobro é o diâmetro.", 85, 4, true);
        listWords.add(word10);
        Content word1 = new Content(6, "SEMANA", "Espaço de sete dias.", 103, 6, true);
        listWords.add(word1);
        Content word2 = new Content(7, "ALGARISMOS", "Símbolos utilizados para representação\n de números.", 4, 10, false);
        listWords.add(word2);
        Content word7 = new Content(8, "SENO", "Cateto oposto sobre hipotenusa.", 9, 4, false);
        listWords.add(word7);
        Content word6 = new Content(9, "OCTAL", "Sistema de numeração que utiliza oito\n algarismos.", 41, 5, false);
        listWords.add(word6);
        Content word3 = new Content(10, "SOMA", "Uma das principais operações básicas da\n aritmética.", 69, 4, false);
        listWords.add(word3);

        return listWords;
    }
}