package Actividades;

import java.io.Serializable;

/**
 * Created by filipeandre135 on 06-06-2014.
 */
public class Recorde implements Serializable
{
    private String actividade;
    private double d10;
    public Recorde()
    {
        this.actividade= "";
        this.d10 = 0;
    }
    public Recorde(String a,double v)
    {
        this.actividade = a;
        this.d10 = v;
    }

    public Recorde(Recorde r)
    {
        this.actividade = r.getActividade();
        this.d10 = r.getD10();

    }

    public String getActividade() {
        return actividade;
    }

    public void setActividade(String actividade) {
        this.actividade = actividade;
    }

    public double getD10() {
        return d10;
    }

    public void setD10(double d10) {
        this.d10 = d10;
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Recorde a = (Recorde)o;
        return (a.getActividade().equals(this.getActividade()) && a.getD10()==this.getD10());
    }

    public Recorde clone(){return new Recorde(this);}


}
