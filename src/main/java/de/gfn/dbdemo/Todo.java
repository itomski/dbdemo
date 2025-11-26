package de.gfn.dbdemo;

public class Todo {

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String name;

    private boolean done;

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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void toggleDone() {
        this.done = !done;
    }

    @Override
    public String toString() {
        return name + " (" + (done ? "erledigt" : "offen") + ")";
    }
}
