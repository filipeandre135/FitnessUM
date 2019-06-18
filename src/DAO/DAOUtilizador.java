package DAO;

import Exceptions.EmailIndisponivelException;
import Exceptions.PasswordErradaException;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.Admin;
import Utilizadores.Amigo;
import Utilizadores.Utilizador;
import Utilizadores.UtilizadorNormal;
import sun.security.util.Password;

import java.io.*;

public class DAOUtilizador
{
    public DAOUtilizador(){}


    public int registarUtilizador(Utilizador u) throws EmailIndisponivelException
    {

        File f = new File(u.getEmail()+"Data.txt");
        if(f.exists() && !f.isDirectory()) { throw new EmailIndisponivelException(); }
        try{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(u.getEmail()+"Data.txt",false));
        outputStream.writeObject(u);
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){System.out.print(e);}
        
        return 1;
    }
    public Utilizador validaUtilizador(String email,String password) throws PasswordErradaException,UtilizadorNaoExisteException,IOException
    {   Utilizador u = null;
        ObjectInputStream inputStream = null;
        try
        {
        inputStream = new ObjectInputStream(new FileInputStream(email+"Data.txt"));
        Object obj = null;
            obj = inputStream.readObject();
            inputStream.close();
            if(obj!= null && obj instanceof UtilizadorNormal)
            {
                UtilizadorNormal ut = (UtilizadorNormal)obj;
                u = ut;
            }
            if(obj!= null && obj instanceof Admin)
                u =(Admin)obj;
        }catch(EOFException e){}
        catch(FileNotFoundException ex){throw new UtilizadorNaoExisteException();}
        catch(ClassNotFoundException ex2){}
        catch(IOException iex){System.out.println(iex.getMessage());}
        if(u!=null)
        {if(u.getPassword().equals(password))
            return u;
         else
            throw new PasswordErradaException();
        }
        return null;
    }


    public Amigo getAmigo(String email) throws UtilizadorNaoExisteException
    {
        UtilizadorNormal u=null;
        Amigo a = null;
        try
        {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(email+"Data.txt"));
        Object obj = null;
        obj = inputStream.readObject();
        if(obj!= null && obj instanceof UtilizadorNormal)
        {
            u = (UtilizadorNormal)obj;
        }
            inputStream.close();
    }catch(EOFException e){}
    catch(IOException ex){throw new UtilizadorNaoExisteException();}
    catch(ClassNotFoundException ex2){}
       a = new Amigo(u.getEmail(),u.getNome(),u.getGenero(),u.getAltura(),u.getPeso(),u.getDn(),u.getDesporto_fav(),u.getActividades());

        return a;
    }

    public UtilizadorNormal getUtilizador(String email) throws UtilizadorNaoExisteException
    {
        UtilizadorNormal u=null;
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(email+"Data.txt"));
            Object obj = null;
            obj = inputStream.readObject();
            inputStream.close();
            if(obj!= null && obj instanceof UtilizadorNormal)
            {
                u = (UtilizadorNormal)obj;
            }

        }catch(EOFException e){}
        catch(IOException ex){throw new UtilizadorNaoExisteException();}
        catch(ClassNotFoundException ex2){}

        return u;
    }

    public int actualizarUtilizador(UtilizadorNormal u)
    {
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(u.getEmail()+"Data.txt",false));
            outputStream.writeObject(u);
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){System.out.print(e);}

        return 1;
    }
}