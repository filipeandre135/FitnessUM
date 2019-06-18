package Controladores;

import Actividades.Actividade;
import DAO.DAOUtilizador;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Amigo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 05-06-2014.
 */
public class ControladorActsAmigo implements Initializable
{
    private static String a;
    @FXML protected TableView<Actividade> tabelaActividades;
    @FXML protected TextArea detalhesTxtArea;
    private ObservableList<Actividade> listaActividades = FXCollections.observableArrayList();
    public void initialize(URL url, ResourceBundle rb)
    {
        tabelaActividades.setItems(listaActividades);
        try{
        listaActividades.addAll(new DAOUtilizador().getAmigo(a).getActividades());
        }catch(UtilizadorNaoExisteException fex){}


        tabelaActividades.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                Actividade a = tabelaActividades.getSelectionModel().getSelectedItem();
                if(a!=null)
                    detalhesTxtArea.setText(a.toString());
            }

        });
    }



    @FXML
    private void closeStage(ActionEvent e) throws IOException {
        ControladorUtilizador.closeverActvStage();
    }



        public static void setAmigo(String am)
    {
        a = am;
    }
}
