package Actividades;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by filipeandre135 on 09-04-2014.
 */
public abstract class ComAltimetria extends Distancia implements Serializable
{
    private int altitudeMaxima;
    private int altitudeMinima;

    public ComAltimetria()
    {
        super();
        this.altitudeMaxima = -1;
        this.altitudeMinima = -1;

    }

    public ComAltimetria(int tempo,String metereologia,GregorianCalendar data,double distancia,double velocidade_maxima,int altitudeMaxima,int altitudeMinima)
    {
        super(tempo,metereologia,data,distancia,velocidade_maxima);
        this.altitudeMaxima = altitudeMaxima;
        this.altitudeMinima = altitudeMinima;
    }

    public ComAltimetria(ComAltimetria ca)
    {
        super(ca);
        this.altitudeMinima = ca.getAltitudeMinima();
        this.altitudeMaxima = ca.getAltitudeMaxima();
    }

    public int getAltitudeMaxima() {
        return altitudeMaxima;
    }

    public void setAltitudeMaxima(int altitudeMaxima) {
        this.altitudeMaxima = altitudeMaxima;
    }

    public int getAltitudeMinima() {
        return altitudeMinima;
    }

    public void setAltitudeMinima(int altitudeMinima) {
        this.altitudeMinima = altitudeMinima;
    }


    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        ComAltimetria a = (ComAltimetria)o;
        return (this.altitudeMaxima == a.getAltitudeMaxima() && this.altitudeMinima == a.getAltitudeMinima() && super.equals(a));
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Altitude Maxima: "+this.altitudeMaxima + " m\nAltitude Minima:"+this.altitudeMinima +" m");
        return sb.toString();
    }

    public abstract ComAltimetria clone();
}
