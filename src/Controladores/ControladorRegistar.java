package Controladores;

import DAO.DAOActividade;
import DAO.DAOUtilizador;
import Exceptions.EmailIndisponivelException;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorNormal;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by filipeandre135 on 30-04-2014.
 */
public class ControladorRegistar implements Initializable{
    @FXML private TextField nomeTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField confirmaPasswordTextField;
    @FXML private TextField alturaTextField;
    @FXML private TextField pesoTextField;
    @FXML private TextField dataTextField;
    @FXML private ComboBox<String> cbSexo;
    @FXML private ComboBox<String> cbDesportos;
     private static Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    cbSexo.getItems().clear();
    cbSexo.getItems().addAll("Masculino","Feminino");
        DAOActividade dao = new DAOActividade();
    cbDesportos.getItems().clear();
    cbDesportos.getItems().addAll(dao.getActividades());
    }

    @FXML
    private void registar(ActionEvent e) throws IOException,EmailIndisponivelException
    {
        String nome,email,pw,cpw;
        int altura=0,peso=0;
        GregorianCalendar dn;

        nome = nomeTextField.getText();
        email = emailTextField.getText();
        pw = passwordTextField.getText(); // CODIFICAR PASSWORD (MD5?)
        cpw = confirmaPasswordTextField.getText(); // MESMO
        if(!pw.equals(cpw))
        {Dialogs.showErrorDialog(stage, "A password não foi confirmada correctamente", "Error Dialog", "title");return;}
        try{
        altura = Integer.parseInt(alturaTextField.getText());
        }catch(NumberFormatException ex){Dialogs.showErrorDialog(stage, "A altura inserida não é um número", "Error Dialog", "title");return;
        }
        try{
        peso = Integer.parseInt(pesoTextField.getText());
        }catch(NumberFormatException ex1){Dialogs.showErrorDialog(stage, "O peso inserido não é numero", "Error Dialog", "title");return;
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        dn = new GregorianCalendar();
        try{
            Date date = df.parse(dataTextField.getText());
            dn.setTime(date);
        }catch(ParseException ex){Dialogs.showErrorDialog(stage, "Data em formato inválido use o formato dd-mm-aaaa", "Error Dialog", "title");return;
        }
        if(nome ==null || peso ==0|| email == null || cbSexo.getSelectionModel().getSelectedItem()==null || altura ==0 || dn==null || cbDesportos.getSelectionModel().getSelectedItem()==null)
        {Dialogs.showErrorDialog(stage, "Alguns dados não foram preenchidos", "Error Dialog", "title");return;}
        UtilizadorNormal u = new UtilizadorNormal(email,pw,nome,cbSexo.getSelectionModel().getSelectedItem().charAt(0),altura,peso,dn,cbDesportos.getSelectionModel().getSelectedItem());

        try
        {
        new DAOUtilizador().registarUtilizador(u);
        }catch(EmailIndisponivelException eex){Dialogs.showErrorDialog(stage, eex.getMessage(), "Error Dialog", "title");return;
        }
        ControladorLogin.registaStage.close();

    }

    public static void setStage(Stage s){ControladorRegistar.stage = s;}
}
