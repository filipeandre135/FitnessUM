package Utilizadores;

import Actividades.Actividade;
import Comparators.ComparatorActividadeData;
import javafx.fxml.Initializable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by filipeandre135 on 21-05-2014.
 */
public class Amigo implements Serializable
{


    private String email;
    private String nome;
    private char genero;
    private int altura;
    private int peso;
    private GregorianCalendar dn;
    private String desporto_fav;
    private TreeSet<Actividade> actividades;
    private HashMap<String,Actividade> recordes;

    /**
     * Constructor for objects of class Normal
     */
    public Amigo()
    {
        this.email = "";
        this.nome = "";
        this.genero = '\0';
        this.altura = 0;
        this.peso = 0;
        this.dn = new GregorianCalendar();
        this.desporto_fav = "";
        this.actividades = new TreeSet<Actividade>(new ComparatorActividadeData());
        this.recordes = new HashMap<String, Actividade>();
    }

    public Amigo(String email,String nome,char genero,int altura,int peso,GregorianCalendar dn,String desporto,TreeSet<Actividade> acts)
    {
        this.email = email;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.dn = (GregorianCalendar)dn.clone();
        this.desporto_fav = desporto;
        this.actividades = new TreeSet<Actividade>(new ComparatorActividadeData());
        this.actividades.addAll(acts);
        this.recordes = new HashMap<String, Actividade>();
    }

    public Amigo(Amigo un)
    {
        this.email = un.getEmail();
        this.genero = un.getGenero();
        this.nome = un.getNome();
        this.peso = un.getPeso();
        this.altura = un.getAltura();
        this.dn = un.getDn();
        this.desporto_fav = un.getDesporto_fav();
        this.actividades = un.getActividades();
        this.recordes = un.getRecordes();
    }

    public HashMap<String,Actividade> getRecordes()
    {
        HashMap<String,Actividade> res = new HashMap<String,Actividade>(this.recordes.size());
        Iterator it = this.recordes.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();
            res.put((String)pairs.getKey(),(Actividade)pairs.getValue());
            it.remove();
        }

        return res;
    }

    public TreeSet<Actividade> getActividades()
    {
        TreeSet<Actividade> res = new TreeSet<Actividade>(new ComparatorActividadeData());
        Iterator it = this.actividades.iterator();
        while(it.hasNext())
        {
            Actividade a = (Actividade)it.next();
            res.add(a);
            it.remove();
        }

        return res;
    }


    public void addActividade(Actividade a)
    {
        this.actividades.add(a);
    }

    public int calcularIdade()
    {
        Calendar now = new GregorianCalendar();
        int idade = now.get(Calendar.YEAR) - dn.get(Calendar.YEAR);
        if ((dn.get(Calendar.MONTH) > now.get(Calendar.MONTH))
                || (dn.get(Calendar.MONTH) == now.get(Calendar.MONTH) && dn.get(Calendar.DAY_OF_MONTH) > now
                .get(Calendar.DAY_OF_MONTH))) {
            idade--;
        }

        return idade;
    }

    public char getGenero(){return this.genero;}

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public GregorianCalendar getDn() {
        return dn;
    }

    public String getDesporto_fav() {
        return desporto_fav;
    }

    public void setDesporto_fav(String desporto_fav) {
        this.desporto_fav = desporto_fav;
    }
    public String getEmail(){return email;}

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Nome: " + this.nome + "\n");
        sb.append("Email: "+this.getEmail()+"\n");
        return sb.toString();
    }

    public Amigo clone()
    {
        return new Amigo(this);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Amigo u = (Amigo)o;
        return ( this.actividades.equals(u.getActividades()) && this.recordes.equals(u.getRecordes()) && this.nome.equals(u.getNome()) && this.genero==u.getGenero() && this.altura == u.getAltura() && this.peso == u.getPeso() && this.desporto_fav.equals(u.getDesporto_fav()) &&this.dn.equals(u.getDn()));
    }
}
