package Actividades;

import Utilizadores.UtilizadorNormal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


/**
 * Abstract class Actividade - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Actividade implements Serializable
{
    // instance variables - replace the example below with your own
    private int tempo; /** em segundos */
    private int calorias;
    private String metereologia;
    private GregorianCalendar data;

    public Actividade()
    {
        this.tempo = -1;
        this.metereologia = "";
        this.calorias = this.calcularCalorias();
        this.data = new GregorianCalendar();
    }
    public Actividade(int tempo,String metereologia,GregorianCalendar data)
    {
        this.tempo = tempo;
        this.metereologia = metereologia;
        this.calorias = this.calcularCalorias();
        this.data = data;
    }

    public Actividade(Actividade a)
    {
        this.tempo = a.getTempo();
        this.calorias = a.getCalorias();
        this.metereologia = a.getMetereologia();
    }
    public String getNome(){return this.getClass().getSimpleName();}
    public String getDataString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        fmt.setCalendar(this.data);
        String dateFormatted = fmt.format(data.getTime());
        return dateFormatted;
    }
    public int getTempo(){return this.tempo;}
    public void setTempo(int tempo){this.tempo = tempo;this.calorias= this.calcularCalorias();}
    public int getCalorias(){return this.calorias;}
    public void setCalorias(int calorias){this.calorias = calorias;}
    public String getMetereologia(){return this.metereologia;}
    public void setMetereologia(String metereologia){this.metereologia = metereologia;}
    public GregorianCalendar getData(){return this.data;}
    public void setData(GregorianCalendar data){this.data = data;}
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Actividade a = (Actividade)o;
        return (this.tempo == a.getTempo() && this.metereologia.equals(a.getMetereologia()) && this.calorias == a.getCalorias());
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Tempo: "+ this.tempo+" min(s)\nCalorias: "+this.calorias+"\n");
        sb.append("Metereologia: "+this.getMetereologia()+"\n");
        return sb.toString();
    }
    public abstract int calcularCalorias();

    public abstract Actividade clone();



/**
    Calorie Burn = (BMR / 24) x MET x T
    where

    For males: BMR = (13.75 x WKG) + (5 x HC) - (6.76 x age) + 66
    For females: BMR = (9.56 x WKG) + (1.85 x HC) - (4.68 x age) + 655

    and

            BMR = Basal Metabolic Rate (over 24 hours)
    MET = Metabolic Equivalent (for selected activity)
    T = Activity duration time (in hours)
    HC = Height (in centimetres)
    WKG = Weight (in kilograms)
 */
}


