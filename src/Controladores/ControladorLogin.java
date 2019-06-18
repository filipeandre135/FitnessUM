package Controladores;

import DAO.DAOUtilizador;
import Exceptions.PasswordErradaException;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Admin;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorNormal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 30-04-2014.
 */
public class ControladorLogin implements Initializable {

    @FXML private TextField textfield_email;
    @FXML private PasswordField textfield_password;
    private static Stage stage;
    public static Stage registaStage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }



    @FXML
    private void loginHandler(ActionEvent e) throws IOException {

    String email = textfield_email.getText();
    String password = textfield_password.getText();
    DAOUtilizador dao = new DAOUtilizador();
        Utilizador u =null;
        try
        {
    u = dao.validaUtilizador(email,password);
        }
        catch(PasswordErradaException pex){
            Dialogs.showErrorDialog(stage, pex.getMessage(), "Error Dialog", "title");
        }
        catch(UtilizadorNaoExisteException ex){
            Dialogs.showErrorDialog(stage, ex.getMessage(), "Error Dialog", "title");
        }
        if(u instanceof UtilizadorNormal)
        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/VistaNormal.fxml"));
        ControladorUtilizador controller = fxmlLoader.getController();
        ControladorUtilizador.setUser(u);
        Parent root = (Parent) fxmlLoader.load();


        Scene scene = new Scene(root);
        Node trigger = (Node) e.getSource();
        Stage stage = (Stage) trigger.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        ControladorUtilizador.setStage(stage);
        stage.show();
        }
        if(u instanceof Admin)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/VistaAdmin.fxml"));
            ControladorAdmin controller = fxmlLoader.getController();
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            Node trigger = (Node) e.getSource();
            Stage stage = (Stage) trigger.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
    }

    @FXML
    private void mostrarRegisto(ActionEvent e) throws IOException
    {
    Parent root;

            try {
                root = FXMLLoader.load(getClass().getResource("/Vistas/VistaRegistar.fxml"));
                registaStage = new Stage();
                registaStage.setTitle("Registar");
                registaStage.setScene(new Scene(root, 450, 450));
                registaStage.show();


            } catch (IOException exc) {
                exc.printStackTrace();
            }




    }

    public static void setStage(Stage s){ stage = s;}


}
