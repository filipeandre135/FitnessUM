package Actividades;

import Exceptions.EventoLotadoException;
import Exceptions.UtilizadorJaInscritoException;
import Utilizadores.UtilizadorNormal;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by filipeandre135 on 02-05-2014.
 */
public class Evento implements Serializable{
    private String nome;
    private String localInscricao;
    private int nParticipantes;
    private GregorianCalendar dataLimiteInscricao;
    private Actividade actividade;
    private ArrayList<UtilizadorNormal> participantes;

    public Evento()
    {
        this.nome = "";
        this.localInscricao = "";
        this.nParticipantes = -1;
        this.dataLimiteInscricao = new GregorianCalendar();
        this.participantes = new ArrayList<UtilizadorNormal>();
    }

    public Evento(String nome,String local,int np,GregorianCalendar data,Actividade a)
    {
        this.nome = nome;
        this.localInscricao = local;
        this.nParticipantes = np;
        this.dataLimiteInscricao = data;
        this.participantes = new ArrayList<UtilizadorNormal>();
        this.actividade = a;
    }

    public Evento(Evento e)
    {
        this.nome = e.getNome();
        this.localInscricao = e.getLocalInscricao();
        this.nParticipantes = e.getnParticipantes();
        this.dataLimiteInscricao = e.getDataLimiteInscricao();
        this.participantes = e.getParticipantes();
        this.actividade = e.getActividade();
    }

    public ArrayList<UtilizadorNormal> getParticipantes()
    {
        ArrayList<UtilizadorNormal>res = new ArrayList<UtilizadorNormal>();
        for(UtilizadorNormal u : participantes)
        {
            res.add(u);
        }

        return res;
    }

    public int getNp()
    {
        return participantes.size();
    }

    public Actividade getActividade(){return this.actividade;}
    public void setActividade(Actividade a){this.actividade = a;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getnParticipantes() {
        return nParticipantes;
    }

    public void setnParticipantes(int nParticipantes) {
        this.nParticipantes = nParticipantes;
    }

    public String getLocalInscricao() {
        return localInscricao;
    }

    public void setLocalInscricao(String localInscricao) {
        this.localInscricao = localInscricao;
    }

    public GregorianCalendar getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(GregorianCalendar dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public String getDataString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        fmt.setCalendar(this.dataLimiteInscricao);
        String dateFormatted = fmt.format(dataLimiteInscricao.getTime());
        return dateFormatted;
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Evento e = (Evento)o;
        return (this.nome.equals(e.getNome()) && this.localInscricao.equals(e.getLocalInscricao()) && this.nParticipantes == e.getnParticipantes() && this.dataLimiteInscricao.equals(e.getDataLimiteInscricao()));
    }

    public Evento clone(){return new Evento(this);}


    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.nome);

        return sb.toString();
    }

    public void addParticipante(UtilizadorNormal u) throws UtilizadorJaInscritoException,EventoLotadoException
    {
        if(participantes.contains(u))
            throw new UtilizadorJaInscritoException();
        if(participantes.size()== this.getnParticipantes())
            throw new EventoLotadoException();
        this.participantes.add(u);
    }

}
