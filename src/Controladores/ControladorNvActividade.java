package Controladores;

import Actividades.Actividade;
import Actividades.ComAltimetria;
import Actividades.Distancia;
import Actividades.Estacionaria;
import DAO.DAOActividade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 21-05-2014.
 */
public class ControladorNvActividade implements Initializable
{
@FXML protected ComboBox<String> cbActividades,cbMetereologia;
@FXML protected TextField metereoTxt;
@FXML protected TextField dataTxt;
@FXML protected TextField tempoTxt;
@FXML protected TextField distanciaTxt;
@FXML protected Label distanciaLabel;
@FXML protected TextField velMaxTxt;
@FXML protected Label velMaxLabel;
@FXML protected TextField altMaxTxt;
@FXML protected Label altMaxLabel;
@FXML protected TextField altMinTxt;
@FXML protected Label altMinLabel;
@FXML protected Label m1Label,m2Label,m3Label,msLabel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbActividades.getItems().clear();
        DAOActividade dao = new DAOActividade();
        cbActividades.getItems().addAll(dao.getActividades());
        cbMetereologia.getItems().clear();
        cbMetereologia.getItems().add("Chuva");
        cbMetereologia.getItems().add("Sol");
        cbMetereologia.getItems().add("Vento e Nublado");


    }

    @FXML
    private void addActividade(ActionEvent e) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException
    {
        String actividade = "Actividades."+cbActividades.getSelectionModel().getSelectedItem();
        Class actv = Class.forName(actividade);
        Object o = actv.newInstance();
        String metereo="";
        int tempo=0,altMax=0,altMin=0;
        double distancia =0,velMax =0;
        GregorianCalendar data;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        data = new GregorianCalendar();
        try{
            Date date = df.parse(dataTxt.getText());
            data.setTime(date);
        }catch(ParseException ex){}
        metereo = cbMetereologia.getSelectionModel().getSelectedItem();
        if(metereo.equals(""))
            return;//erro metereologia não foi selected
        try{
            tempo = Integer.parseInt(tempoTxt.getText());
        }catch(NumberFormatException ex){}
        try{
            distancia = Double.parseDouble(distanciaTxt.getText());
        }catch(NumberFormatException ex){}
        try{
            velMax = Double.parseDouble(velMaxTxt.getText());
        }catch(NumberFormatException ex){}
        try{
            altMax = Integer.parseInt(altMaxTxt.getText());
        }catch(NumberFormatException ex){}
        try{
            altMin = Integer.parseInt(altMinTxt.getText());
        }catch(NumberFormatException ex){}
        if(o instanceof Estacionaria)
        {
            ((Estacionaria)o).setTempo(tempo);
            ((Estacionaria)o).setMetereologia(metereo);
            ((Estacionaria)o).setData(data);
        }

        if(o instanceof Distancia)
        {
            ((Distancia)o).setTempo(tempo);
            ((Distancia)o).setMetereologia(metereo);
            ((Distancia)o).setData(data);
            ((Distancia)o).setDistancia(distancia);
            ((Distancia)o).setVelocidade_maxima(velMax);
        }

        if(o instanceof ComAltimetria)
        {
            ((ComAltimetria)o).setTempo(tempo);
            ((ComAltimetria)o).setMetereologia(metereo);
            ((ComAltimetria)o).setData(data);
            ((ComAltimetria)o).setDistancia(distancia);
            ((ComAltimetria)o).setVelocidade_maxima(velMax);
            ((ComAltimetria)o).setAltitudeMaxima(altMax);
            ((ComAltimetria)o).setAltitudeMinima(altMin);
        }

        ControladorUtilizador.addActividade((Actividade)o);
        ControladorUtilizador.closeaddActvStage();
    }


    @FXML
    private void selectActividade(ActionEvent e) throws IOException,ClassNotFoundException,InstantiationException,IllegalAccessException
    {
        String actividade = "Actividades."+cbActividades.getSelectionModel().getSelectedItem();
        Class actv = Class.forName(actividade);
        Object o = actv.newInstance();
        if(o instanceof Distancia)
        {
            distanciaTxt.setVisible(true);
            distanciaLabel.setVisible(true);
            m1Label.setVisible(true);
            msLabel.setVisible(true);
            velMaxLabel.setVisible(true);
            velMaxTxt.setVisible(true);
            altMaxLabel.setVisible(false);
            altMaxTxt.setVisible(false);
            altMinLabel.setVisible(false);
            altMinTxt.setVisible(false);
            m2Label.setVisible(false);
            m3Label.setVisible(false);
        }
        if(o instanceof ComAltimetria)
        {
            distanciaTxt.setVisible(true);
            distanciaLabel.setVisible(true);
            m1Label.setVisible(true);
            msLabel.setVisible(true);
            velMaxLabel.setVisible(true);
            velMaxTxt.setVisible(true);
            altMaxLabel.setVisible(true);
            altMaxTxt.setVisible(true);
            altMinLabel.setVisible(true);
            altMinTxt.setVisible(true);
            m2Label.setVisible(true);
            m3Label.setVisible(true);
        }

        if(o instanceof Estacionaria)
        {
            distanciaTxt.setVisible(false);
            distanciaLabel.setVisible(false);
            m1Label.setVisible(false);
            msLabel.setVisible(false);
            velMaxLabel.setVisible(false);
            velMaxTxt.setVisible(false);
            altMaxLabel.setVisible(false);
            altMaxTxt.setVisible(false);
            altMinLabel.setVisible(false);
            altMinTxt.setVisible(false);
            m2Label.setVisible(false);
            m3Label.setVisible(false);
        } 
    }



}
