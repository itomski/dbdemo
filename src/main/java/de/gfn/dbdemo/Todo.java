package de.gfn.dbdemo;

public class Todo {

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String name;

    public Todo() {
    }

    public Todo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
