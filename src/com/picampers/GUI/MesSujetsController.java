/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.picampers.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Mortadha
 */
public class MesSujetsController implements Initializable {

    @FXML
    private Button btnaffiche1;
    @FXML
    private TextField searchSujet;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> tabid;
    @FXML
    private TableColumn<?, ?> tabtitre;
    @FXML
    private TableColumn<?, ?> tabuser;
    @FXML
    private TableColumn<?, ?> tabtag1;
    @FXML
    private TableColumn<?, ?> tabtag2;
    @FXML
    private TableColumn<?, ?> tabtag3;
    @FXML
    private TableColumn<?, ?> tabdate;
    @FXML
    private TableColumn<?, ?> tabnb;
    @FXML
    private Label lblusr;
    @FXML
    private Button retour;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleBtnaffiche1Action(ActionEvent event) {
    }

    @FXML
    private void searchSujet(InputMethodEvent event) {
    }

    @FXML
    private void searchSujet(KeyEvent event) {
    }

    @FXML
    private void handleAjouterAction(ActionEvent event) {
    }

    @FXML
    private void handleretourAction(ActionEvent event) {
    }

    @FXML
    private void handlemodifieraction(ActionEvent event) {
    }

    @FXML
    private void handlesupprimeraction(ActionEvent event) {
    }

    @FXML
    private void handleafficheraction(ActionEvent event) {
    }
    
}
