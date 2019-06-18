package Utilizadores;
import Actividades.Actividade;

import java.io.Serializable;
import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * Write a description of class Utilizador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Utilizador implements Serializable
{
    // instance variables - replace the example below with your own
    private String email;
    private String password;


    /**
     * Constructor for objects of class Utilizador
     */
    public Utilizador()
    {
        this.email = "";
        this.password = "";
    }
    
    public Utilizador(String email,String password)
    {
        this.email = email;
        this.password = password;

    }
    
    public Utilizador(Utilizador u)
    {
        this.email = u.getEmail();
        this.password = u.getPassword();
    }
    
    public String getEmail(){return this.email;}
    public String getPassword(){ return this.password;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}


    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Email: "+email +"\n");
        return sb.toString();
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Utilizador u = (Utilizador)o;
        return (this.getEmail().equals(u.getEmail()));
    }

    public Utilizador clone(){ return new Utilizador(this);}

}
