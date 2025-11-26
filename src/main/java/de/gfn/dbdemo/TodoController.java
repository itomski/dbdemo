package de.gfn.dbdemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TodoController implements Initializable {

    @FXML
    private ListView<Todo> todos;

    @FXML
    private TextField eingabe;

    private Predicate<Todo> filter;

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
        else if(event.getCode() == KeyCode.BACK_SPACE) {
            Todo todo = todos.getSelectionModel().getSelectedItem();
            TodoRepository.delete(todo);
            updateAusgabe();
        }
    }

    @FXML
    protected void filter(ActionEvent event) throws SQLException {

        // Welcher Button wurde geklick?
        String auswahl = ((Button)event.getSource()).getText();

        // Filter einsetzen
        switch(auswahl) {
            case "offen":
                filter = t -> !t.isDone();
                break;

            case "erledigt":
                filter = t -> t.isDone();
                break;

            default:
                filter = null;
        };

        // Ausgabe mit Filter vornehmen
        updateAusgabe();
    }

    private void updateAusgabe() throws SQLException {
        // todos = lokale Variable
        // this.todos = Instanzvariable
        // FXCollections.observableList verpackt eine "normale" Liste in eine ObservableList
        ObservableList<Todo> todos = FXCollections.observableList(TodoRepository.findAll());

        if(filter == null) {
            // Ohne Filter
            this.todos.setItems(todos);
        }
        else {
            // Mit Filter
            this.todos.setItems(new FilteredList<>(todos, filter));
        }
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