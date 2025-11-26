package de.gfn.dbdemo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML
    private ListView<Todo> todos;

    @FXML
    private TextField eingabe;

    @FXML
    protected void eingabeVerarbeiten(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.ENTER) {
            Todo todo = new Todo();
            todo.setName(eingabe.getText()); // Todo aus der Eingabe erzeugen
            TodoRepository.insert(todo); // Todo speichern
            updateAusgabe(); // Ausgabe updaten
            eingabe.clear(); // Textfeld leeren
        }
    }

    @FXML
    protected void statusAendern(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.SPACE) {
            Todo todo = todos.getSelectionModel().getSelectedItem();
            todo.toggleDone();
            TodoRepository.update(todo);
            updateAusgabe();
        }
    }

    private void updateAusgabe() throws SQLException {
        // todos = lokale Variable
        // this.todos = Instanzvariable
        List<Todo> todos = TodoRepository.findAll();
        // FXCollections.observableList verpackt eine "normale" Liste in eine ObservableList
        this.todos.setItems(FXCollections.observableList(todos));
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