package Actividades;

import Controladores.ControladorUtilizador;
import Utilizadores.UtilizadorNormal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by filipeandre135 on 23-04-2014.
 */
public class Tenis extends Estacionaria implements Serializable{

    public Tenis()
    {
        super();
    }

    public Tenis(int tempo,String metereologia,GregorianCalendar data)
    {
        super(tempo,metereologia,data);
    }

    public Tenis(Tenis a)
    {
        super(a);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Tenis a = (Tenis)o;
        return (super.equals(a));
    }

    public Tenis clone(){return new Tenis(this);}

    public String toString()
    {
        return super.toString();
    }

    public int calcularCalorias(){
        double met = 7;
        UtilizadorNormal u= ControladorUtilizador.getUser();
        if(u == null)return 0;
        char genero = u.getGenero();
        int peso = u.getPeso();
        int altura = u.getAltura();
        Calendar dn = u.getDn();
        Calendar now = new GregorianCalendar();
        int idade = u.calcularIdade();
        double bmr = 0;
        if(genero == 'M')
            bmr = (13.75 * peso) +(5*altura) - (6.76 * idade) + 66;
        if(genero == 'F')
            bmr = (9.56 * peso) +(1.85*altura) - (4.68 * idade) + 655;

        return (int)Math.round((bmr / 24) * met * this.getTempo()/60);
    }
}
