package Controladores;

import Actividades.Actividade;
import Actividades.Evento;
import DAO.DAOActividade;
import DAO.DAOUtilizador;
import Exceptions.EventoLotadoException;
import Exceptions.UtilizadorJaInscritoException;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorNormal;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by filipeandre135 on 30-04-2014.
 */
public class ControladorAdmin implements Initializable{

    @FXML protected TableView<Evento> tabelaEventos;
    @FXML protected ListView<UtilizadorNormal> participantes;
    private ObservableList<UtilizadorNormal> listaparts = FXCollections.observableArrayList();
    private static ObservableList<Evento> eventos = FXCollections.observableArrayList();
    private static Stage addEventoStage;
    @FXML protected static ComboBox<Evento> cbEventos;
    @FXML protected ComboBox<String> cbMetereologia;
    @FXML protected TextArea txtDetalhes;
    @FXML protected TextField txtEmail;
    @FXML protected Button btaddParticipante;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        participantes.setItems(listaparts);
        tabelaEventos.setItems(eventos);
        ArrayList<Evento> evs = new DAOActividade().getEventos();
        eventos.addAll(evs);
        cbEventos.getItems().clear();
        cbEventos.getItems().addAll(evs);
        cbMetereologia.getItems().clear();
        cbMetereologia.getItems().add("Chuva");
        cbMetereologia.getItems().add("Sol");
        cbMetereologia.getItems().add("Vento e Nublado");
        tabelaEventos.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>()
        {
            @Override
            public void onChanged(Change<? extends Integer> change)
            {
                if (change.getList().size() >= 1)
                {
                    btaddParticipante.setDisable(false);
                }
                else
                {
                    btaddParticipante.setDisable(true);
                }
            }

        });

    }

    @FXML
    private void selectEvento(ActionEvent e)
    {
        listaparts.clear();
        Evento ev = cbEventos.getSelectionModel().getSelectedItem();
        listaparts.addAll(ev.getParticipantes());
    }

    @FXML
    private void addEvento(ActionEvent e) throws IOException
    {
        Parent root =  FXMLLoader.load(getClass().getResource("/Vistas/VistaNvEvento.fxml"));

        addEventoStage = new Stage();
        addEventoStage.setTitle("Nova Actividade");
        addEventoStage.setScene(new Scene(root, 450, 450));
        addEventoStage.show();
    }

    @FXML
    private void addParticipante(ActionEvent e)
    {
        String email ="";
        email = txtEmail.getText();
        try
        {
        UtilizadorNormal ut = new DAOUtilizador().getUtilizador(email);
            Evento ev = tabelaEventos.getSelectionModel().getSelectedItem();
            if(ev==null)return;
            try
            {
            ev.addParticipante(ut);
            }catch(UtilizadorJaInscritoException ex){System.out.println(ex.getMessage());}
            catch(EventoLotadoException ex1){System.out.println(ex1.getMessage());}
            new DAOActividade().saveEventos(eventos);
            eventos.clear();
            eventos.addAll(new DAOActividade().getEventos());
        }catch(UtilizadorNaoExisteException ex){return;}
    }

    @FXML
    private void simularEvento(ActionEvent e)
    {
        Evento ev = cbEventos.getSelectionModel().getSelectedItem();
        TreeMap <Double,UtilizadorNormal> parts = new TreeMap<Double,UtilizadorNormal>();

        for(UtilizadorNormal u : ev.getParticipantes())
        {
            double media = 0;
            media = u.getMedia(ev.getActividade());
            Random randomGenerator = new Random();
            int correuBem = randomGenerator.nextInt(2);
            int perc = randomGenerator.nextInt(10)+15;
            if(correuBem==1)
            {parts.put(media+perc,u);}
            else
            {parts.put(media-perc,u);}

        }
        StringBuffer sb = new StringBuffer();
        int posicao = 1;
        NavigableMap<Double,UtilizadorNormal> nmap=parts.descendingMap();
        for(Map.Entry<Double,UtilizadorNormal> entry : nmap.entrySet()) {
            Double key = entry.getKey();
            UtilizadorNormal value = entry.getValue();
            sb.append(posicao+"º " + value.getEmail()+"   "+key+"\n");
            posicao++;
        }
        txtDetalhes.setText(sb.toString());



    }

    public static void adicionarEvento(Evento e)
    {
        eventos.add(e);
        cbEventos.getItems().add(e);
        new DAOActividade().saveEventos(eventos);
    }

    public static void closeStage()
    {
        addEventoStage.close();
    }
}
