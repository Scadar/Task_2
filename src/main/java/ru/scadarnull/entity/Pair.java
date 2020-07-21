package ru.scadarnull.entity;

public class Pair {

    private Long id;
    private String value;

    public Pair() {
    }

    public Pair(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
