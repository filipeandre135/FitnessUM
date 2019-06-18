package Controladores;

import Actividades.Actividade;
import Actividades.Recorde;
import Comparators.ComparatorActividadeData;
import DAO.DAOUtilizador;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Amigo;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorNormal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class ControladorUtilizador implements Initializable{

    @FXML protected TextField txtNome,txtAltura,txtIdade,txtPeso,txtDesportoFav,txtCalorias;
    @FXML protected Label userLabel;
    @FXML protected TextArea detalhesTxtArea;
    private static UtilizadorNormal u;
    @FXML protected TableView<Actividade> tabelaActividades;
    @FXML protected TableView<Recorde> tabelaRecordes;
    private static ObservableList<Recorde> listaRecordes = FXCollections.observableArrayList();
    private static ObservableList<Actividade> listaActividades = FXCollections.observableArrayList();
    @FXML protected ListView<Amigo> listaAmigos;
    private static ObservableList<Amigo> listAmigos = FXCollections.observableArrayList();
    @FXML protected TextField txtNomeAmigo,txtAlturaAmigo,txtIdadeAmigo,txtPesoAmigo,txtDesportoFavAmigo,txtEmailAmigo;
    @FXML protected TextField txtAno,txtMes;
    private static Stage addActvStage;
    private static Stage verActvStage;
    private static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        userLabel.setText("Olá, " + u.getNome());
        tabelaActividades.setItems(listaActividades);
        listaAmigos.setItems(listAmigos);
        tabelaRecordes.setItems(listaRecordes);
            Iterator it = u.getAmigos().entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry pairs = (Map.Entry)it.next();
                Amigo a = (Amigo)pairs.getValue();
                listAmigos.add(a);
            }
        Iterator it2 = u.getRecordes().entrySet().iterator();
        while (it2.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it2.next();
            Recorde r = (Recorde)pairs.getValue();
            listaRecordes.add(r);
        }
        listaActividades.addAll(u.getActividades());
        tabelaActividades.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                Actividade a = tabelaActividades.getSelectionModel().getSelectedItem();
                if(a!=null)
                detalhesTxtArea.setText(a.toString());
            }

        });

        listaAmigos.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> change) {
                Amigo a = listaAmigos.getSelectionModel().getSelectedItem();
                if(a!=null)
                {
                    txtNomeAmigo.setText(a.getNome());
                    txtAlturaAmigo.setText(a.getAltura()+"");
                    txtPesoAmigo.setText(a.getPeso()+"");
                    txtIdadeAmigo.setText(a.calcularIdade()+"");
                    txtDesportoFavAmigo.setText(a.getDesporto_fav());
                }
            }

        });

        listaActividades.addListener(new ListChangeListener<Actividade>(){

            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Actividade> pChange) {
                while(pChange.next()) {
                    int sumcalorias = 0;
                    for(Actividade a : listaActividades)
                    {
                        sumcalorias += a.getCalorias();
                    }
                    txtCalorias.setText(sumcalorias+"");
                }
            }
        });


        this.txtNome.setText(u.getNome());
        this.txtAltura.setText(u.getAltura()+"");
        this.txtPeso.setText(u.getPeso()+"");
        this.txtDesportoFav.setText(u.getDesporto_fav());
        this.txtIdade.setText(u.calcularIdade()+"");
        int sumcalorias = 0;
        for(Actividade a : this.listaActividades)
        {
            sumcalorias += a.getCalorias();
        }
        this.txtCalorias.setText(sumcalorias+"");

    }




    @FXML
    private void novaActividade(ActionEvent e)throws IOException
    {
        Parent root =  FXMLLoader.load(getClass().getResource("/Vistas/VistaNvActividade.fxml"));

        addActvStage = new Stage();
        addActvStage.setTitle("Nova Actividade");
        addActvStage.setScene(new Scene(root, 450, 450));
        addActvStage.show();

    }

    @FXML
    private void verActividadesAmigo(ActionEvent e)throws IOException
    {
        Amigo a = listaAmigos.getSelectionModel().getSelectedItem();
        if(a==null){Dialogs.showErrorDialog(stage, "Nenhum amigo selecionado", "Error Dialog", "title");return;}
        ControladorActsAmigo.setAmigo(a.getEmail());
        Parent root = FXMLLoader.load(getClass().getResource("/Vistas/VistaActsAmigo.fxml"));
        verActvStage = new Stage();
        verActvStage.setTitle("Actividades do "+a.getNome());
        verActvStage.setScene(new Scene(root,1000,500));
        verActvStage.show();
    }

    @FXML
    private void adicionarAmigo(ActionEvent e)throws IOException,UtilizadorNaoExisteException
    {
        DAOUtilizador dao = new DAOUtilizador();
        String email = this.txtEmailAmigo.getText();
        Amigo a =null;
        try
        {
        a = dao.getAmigo(email);
        }catch(UtilizadorNaoExisteException uex){
            Dialogs.showErrorDialog(stage, uex.getMessage(), "Error Dialog", "title");return;
        }
        this.listAmigos.add(a);
        u.addAmigo(a);
        dao.actualizarUtilizador(u);
    }

    public static void setUser(Utilizador un)
    {
        u = (UtilizadorNormal)un;
    }

    public static void addActividade(Actividade a)
    {
        listaActividades.add(a);
        u.addActividade(a);
        listaRecordes.clear();
        Iterator it = u.getRecordes().entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();
            Recorde r = (Recorde)pairs.getValue();
            listaRecordes.add(r);
        }
        new DAOUtilizador().actualizarUtilizador(u);
    }

    public static UtilizadorNormal getUser(){ return u;}

    public static void closeaddActvStage()
    {
        addActvStage.close();
    }

    public static void closeverActvStage()
    {
        verActvStage.close();
    }

    public static void setStage(Stage s)
    {
        stage = s;
    }

    @FXML
    private void getActividadesTodas(ActionEvent e)
    {
        this.listaActividades.clear();
        this.listaActividades.addAll(u.getActividades());
    }

    @FXML
    private void getActividadesAnoMes(ActionEvent e)
    {
        try{
        this.listaActividades.clear();
       TreeSet<Actividade> aux = u.getActividades();
        String ano = txtAno.getText();
        String mes = txtMes.getText();
        int year = Integer.parseInt(ano);
        for(Actividade a : aux)
        {
            if(mes.equals(""))
            {
                if(a.getData().get(Calendar.YEAR) == year)
                    listaActividades.add(a);
            }
            else
            {
                int month = Integer.parseInt(mes);
                if(a.getData().get(Calendar.YEAR) == year && a.getData().get(Calendar.MONTH)== month-1)
                    listaActividades.add(a);
            }
        }
        }catch(NumberFormatException nex){Dialogs.showErrorDialog(stage, "Ano/Mes inválido", "Error Dialog", "title");}
    }

    @FXML
    private void removerActividade(ActionEvent e) throws ClassNotFoundException,InstantiationException,IllegalAccessException
    {
        Actividade a = tabelaActividades.getSelectionModel().getSelectedItem();
        if(a!= null)
        {
            u.removeActividade(a);
            listaActividades.remove(a);
            u.actualizarRecordes();
            listaRecordes.clear();
            Iterator it2 = u.getRecordes().entrySet().iterator();
            while (it2.hasNext())
            {
                Map.Entry pairs = (Map.Entry)it2.next();
                Recorde r = (Recorde)pairs.getValue();
                listaRecordes.add(r);
            }
            new DAOUtilizador().actualizarUtilizador(u);
        }
    }






    }
