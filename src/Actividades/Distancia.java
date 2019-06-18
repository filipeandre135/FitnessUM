package Actividades;
import Actividades.Actividade;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by filipeandre135 on 09-04-2014.
 */
public abstract class Distancia extends Actividade implements Serializable
{
    private double distancia; /** ms */
    private double velocidade_maxima;
    private double d10;


    public Distancia()
    {
        super();
        this.distancia = -1;
        this.velocidade_maxima = -1;
        this.d10 = -1;
    }

    public Distancia(int tempo,String metereologia,GregorianCalendar data,double distancia,double velocidade_maxima)
    {
        super(tempo,metereologia,data);
        this.distancia = distancia;
        this.velocidade_maxima = velocidade_maxima;
        this.d10 = (10*distancia)/tempo;
    }

    public Distancia(Distancia d)
    {
        super(d);
        this.distancia = d.getDistancia();
        this.d10 = (10*d.getDistancia())/d.getTempo();
        this.velocidade_maxima = d.getVelocidade_maxima();
    }
    public double getDistancia(){return this.distancia;}
    public void setDistancia(double distancia){this.distancia = distancia;this.d10 = (10*this.getDistancia())/this.getTempo();}
    public double getVelocidade_maxima(){return this.velocidade_maxima;}
    public void setVelocidade_maxima(double velocidade_maxima){this.velocidade_maxima = velocidade_maxima;}
    public double getd10(){return this.d10;}
    public void setd10(double d10){this.d10= d10;}

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Distancia a = (Distancia)o;
        return (this.distancia == a.getDistancia() && this.velocidade_maxima == a.getVelocidade_maxima() && super.equals(a));
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        sb.append("Distancia: "+ this.distancia+" m\nVelocidade Maxima: "+this.velocidade_maxima+" m/s\n");
        sb.append("Distância em 10 min: "+this.d10+" metros\n");
        return sb.toString();
    }

    public abstract Distancia clone();
}
