package Controladores;

import Actividades.Actividade;
import Actividades.Distancia;
import Actividades.Evento;
import DAO.DAOActividade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by filipeandre135 on 07-06-2014.
 */
public class ControladorNvEvento implements Initializable{

    @FXML protected ComboBox<String> cbActividades;
    @FXML protected TextField txtNome,txtData,txtLocal,txtNp;
    public void initialize(URL url, ResourceBundle rb)
    {

        ArrayList<String> acts = new DAOActividade().getActividades();
        cbActividades.getItems().clear();
        for(String s : acts)
        {
            try
            {
            String actividade = "Actividades."+s;
            Class actv = Class.forName(actividade);
            Object o = actv.newInstance();
                if(o instanceof Distancia)
                {
                    cbActividades.getItems().add(s);
                }
            }
            catch(ClassNotFoundException cex){}
            catch(InstantiationException iex){}
            catch(IllegalAccessException iaex){}
        }

    }

    @FXML
    private void addEvento(ActionEvent e) throws ClassNotFoundException,IllegalAccessException,InstantiationException
    {
        String nome="";
        String data = "";
        String local="";
        String np = "";
        int num =0;
        nome = txtNome.getText();
        if(nome.equals(""))
            return;

        data = txtData.getText();
        if(data.equals(""))
            return;
        GregorianCalendar date;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = new GregorianCalendar();
        try{
            Date dat = df.parse(data);
            date.setTime(dat);
        }catch(ParseException ex){}

        local = txtLocal.getText();
        if(local.equals(""))
            return;

        np = txtNp.getText();

        try{
            num = Integer.parseInt(np);
        }catch(NumberFormatException ex){}

        String actividade = "Actividades."+cbActividades.getSelectionModel().getSelectedItem();
        Class actv = Class.forName(actividade);
        Object o = actv.newInstance();
        Evento ev = new Evento(nome,local,num,date,(Actividade)o);
        ControladorAdmin.adicionarEvento(ev);
        ControladorAdmin.closeStage();
    }
}
