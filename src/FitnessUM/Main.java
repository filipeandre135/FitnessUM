package FitnessUM;
import Actividades.Basquetebol;
import Controladores.ControladorLogin;
import DAO.DAOUtilizador;
import Exceptions.EmailIndisponivelException;
import Utilizadores.Admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Utilizadores.UtilizadorNormal;

import java.util.GregorianCalendar;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Fitness UM");

        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/VistaLogin.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.centerOnScreen();

        ControladorLogin.setStage(stage);
        stage.show();
    }
}