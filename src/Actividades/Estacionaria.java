package Actividades;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by filipeandre135 on 07-05-2014.
 */
public abstract class Estacionaria extends Actividade implements Serializable {

    public Estacionaria()
    {
        super();
    }

    public Estacionaria(int tempo,String metereologia,GregorianCalendar data)
    {
        super(tempo,metereologia,data);
    }

    public Estacionaria(Actividade a)
    {
        super(a);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Estacionaria a = (Estacionaria)o;
        return (super.equals(a));
    }

    public String toString()
    {
        return super.toString();
    }

    public abstract int calcularCalorias();

    public abstract Estacionaria clone();
}
