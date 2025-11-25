package de.gfn.dbdemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML
    private Label ausgabe;

    @FXML
    private TextField eingabe;

    @FXML
    protected void eingabeVerarbeiten() throws SQLException {
        Todo todo = new Todo();
        todo.setName(eingabe.getText()); // Todo aus der Eingabe erzeugen
        TodoRepository.insert(todo); // Todo speichern
        updateAusgabe();
        eingabe.clear(); // Textfeld leeren
    }

    private void updateAusgabe() throws SQLException {
        StringBuilder sb = new StringBuilder();
        for(Todo t : TodoRepository.findAll()) {
            sb.append(t.getName()).append("\n"); // StringBuilder mit Todos füllen
        }
        ausgabe.setText(sb.toString()); // Inhalt des StringBuilders in Label schreiben
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wird einmalig beim Start der App ausgeführt
        try {
            TodoRepository.createTable(); // Wenn noch nicht da, DB Tabelle bauen
            updateAusgabe(); // Ausgabe der Altdaten
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}