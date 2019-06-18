
/**
 * Created by filipeandre135 on 09-04-2014.
 */
package Utilizadores;
import Actividades.Actividade;
import Actividades.Distancia;
import Actividades.Recorde;
import Comparators.ComparatorActividadeData;
import DAO.DAOActividade;
import DAO.DAOUtilizador;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Utilizador;

import java.io.BufferedReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Write a description of class Normal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UtilizadorNormal extends Utilizador implements Serializable
{
    private String nome;
    private char genero;
    private int altura;
    private int peso;
    private GregorianCalendar dn;
    private String desporto_fav;
    private HashMap<String,Amigo> amigos;
    private TreeSet<Actividade> actividades;
    private HashMap<String, Recorde> recordes;

    /**
     * Constructor for objects of class Normal
     */
    public UtilizadorNormal()
    {
        this.nome = "";
        this.genero = '\0';
        this.altura = 0;
        this.peso = 0;
        this.dn = new GregorianCalendar();
        this.desporto_fav = "";
        this.amigos = new HashMap<String,Amigo>();
        this.actividades = new TreeSet<Actividade>(new ComparatorActividadeData());
        this.recordes = new HashMap<String, Recorde>();
    }

    public UtilizadorNormal(String email,String password,String nome,char genero,int altura,int peso,GregorianCalendar dn,String desporto)
    {
        super(email,password);
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.dn = (GregorianCalendar)dn.clone();
        this.desporto_fav = desporto;
        this.amigos = new HashMap<String,Amigo>();
        this.actividades = new TreeSet<Actividade>(new ComparatorActividadeData());
        this.recordes = new HashMap<String, Recorde>();
        try
        {
            this.actualizarRecordes();
        }catch(ClassNotFoundException cex){}catch(InstantiationException iex){}catch(IllegalAccessException iaex){}

    }

    public UtilizadorNormal(UtilizadorNormal un)
    {
        super(un.getEmail(),un.getPassword());
        this.genero = un.getGenero();
        this.nome = un.getNome();
        this.altura = un.getAltura();
        this.dn = un.getDn();
        this.desporto_fav = un.getDesporto_fav();
        this.amigos = un.getAmigos();
        this.actividades = un.getActividades();
        this.recordes = un.getRecordes();
    }

    public HashMap<String,Recorde> getRecordes()
    {
        HashMap<String,Recorde> res = new HashMap<String,Recorde>(this.recordes.size());
        Iterator it = this.recordes.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();
            res.put((String)pairs.getKey(),(Recorde)pairs.getValue());
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
        }

        return res;
    }

    public HashMap<String,Amigo> getAmigos()
    {
        HashMap<String,Amigo> res = new HashMap<String,Amigo>(this.amigos.size());
        Iterator it = this.amigos.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();
            try
            {
            Amigo a = new DAOUtilizador().getAmigo((String)pairs.getKey());
            res.put((String)pairs.getKey(),a);
            }catch(UtilizadorNaoExisteException uex){continue;}
            it.remove();
        }

        return res;
    }

    public void addActividade(Actividade a)
    {
        this.actividades.add(a);
        try
        {
        this.actualizarRecordes();
        }catch(ClassNotFoundException cex){}catch(InstantiationException iex){}catch(IllegalAccessException iaex){}
    }

    public void removeActividade(Actividade a)
    {
        this.actividades.remove(a);
    }

    public void addAmigo(Amigo a)
    {
        this.amigos.put(a.getEmail(),a);
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
    public String getEmail(){return super.getEmail();}

    public void actualizarRecordes() throws ClassNotFoundException,InstantiationException,IllegalAccessException
    {
        ArrayList<String> acts = new DAOActividade().getActividades();
        boolean distancia = false;
        for(String s : acts)
        {
            Iterator it = this.getActividades().iterator();
            double d = 0;
            String aux = "Actividades."+s;
            Class actv = Class.forName(aux);
            Object o = actv.newInstance();
            if(o instanceof Distancia)
                distancia = true;
            while(it.hasNext())
            {
                Actividade a = (Actividade)it.next();
                if(a instanceof Distancia)
                {
                    if(a.getNome().equals(s))
                        if(((Distancia) a).getd10() > d)
                        {d = ((Distancia) a).getd10();}
                }

            }
            if(distancia)
            {
                this.recordes.put(s,new Recorde(s,d));
            }

            distancia = false;
        }
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

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()+"\n");
        sb.append("Nome: " + this.nome + "\n");
        sb.append("Email: "+this.getEmail()+"\n");
        sb.append("Altura: "+this.altura+"\n");
        sb.append("Peso: "+this.peso+"\n");
        sb.append("Sexo: "+this.getGenero()+"\n");
        sb.append("Desporto Favorito: "+this.desporto_fav+"\n");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); /** formato da data dd - dia , MM - mes ,yyyy-ano, mm -minutos ....*/
        dateFormat.setCalendar(this.dn);
        sb.append("Data Nascimento: "+dateFormat.format(this.dn.getTime()));
        return sb.toString();
    }

    public UtilizadorNormal clone()
    {
        return new UtilizadorNormal(this);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        UtilizadorNormal u = (UtilizadorNormal)o;
        return (super.equals(u) && this.actividades.equals(u.getActividades()) && this.recordes.equals(u.getRecordes()) && this.amigos.equals(u.getAmigos()) && this.nome.equals(u.getNome()) && this.genero==u.getGenero() && this.altura == u.getAltura() && this.peso == u.getPeso() && this.desporto_fav.equals(u.getDesporto_fav()) &&this.dn.equals(u.getDn()));
    }

    public double getMedia(Actividade a)
    {
        double sum =0;
        double nacts = 0;

        for(Actividade act : this.actividades)
        {
            if(act instanceof Distancia)
            {
                Distancia aux = (Distancia)act;
                if(a.getNome().equals(aux.getNome()))
                {
                    sum += aux.getd10();
                    nacts++;
                }
            }
        }
        if(nacts==0)return 0;
        return sum/nacts;
    }


}
