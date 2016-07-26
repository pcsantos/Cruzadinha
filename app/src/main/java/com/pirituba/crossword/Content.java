package com.pirituba.crossword;

public class Content {

    private int code;
    private String word;
    private String hint;
    private int firstCell;
    private int size;
    private boolean horizontal;

    public Content(int code, String word, String hint, int firstCell, int size, boolean horizontal) {
        this.code = code;
        this.word = word;
        this.hint = hint;
        this.firstCell = firstCell;
        this.size = size;
        this.horizontal = horizontal;
    }

    public int getCode() {
        return this.code;
    }
    public String getWord() {
        return this.word;
    }

    public String getHint() {
        return this.hint;
    }

    public int getSize() {
        return this.size;
    }

    public int getFirstCell() {
        return this.firstCell;
    }

    public boolean getHorizontal() {
        return this.horizontal;
    }
}