package DAO;

import Actividades.Evento;
import Exceptions.UtilizadorNaoExisteException;
import Utilizadores.UtilizadorNormal;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class DAOActividade{


    public ArrayList<String> getActividades()
    {
        ArrayList<String> actividades = new ArrayList<String>();
        try
        {
            BufferedReader ficheiro = new BufferedReader(new FileReader("Activities.txt"));
            while(ficheiro.ready())
            {
                String actv = ficheiro.readLine();
                actividades.add(actv);
            }

        }catch(IOException e){System.out.println(e.getMessage());}
    return actividades;
    }

    public ArrayList<Evento> getEventos()
    {
        ArrayList<Evento> res = new ArrayList<Evento>();
        Evento e = null;
        try
        {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("eventos"));
            Object obj = null;
            while((obj = inputStream.readObject())!= null)
            {
                if(obj!= null && obj instanceof Evento)
                {
                    e = (Evento)obj;
                    res.add(e);
                }
            }
            inputStream.close();
        }catch(EOFException ex){}
        catch(IOException ex){}
        catch(ClassNotFoundException ex2){}

        return res;
    }

    public void saveEventos(ObservableList<Evento> evs)
    {
        try { ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("eventos",false));
            for(Evento e : evs)
            {
                outputStream.writeObject(e);
            }
            outputStream.flush();
            outputStream.close();}catch (IOException e) {System.out.println(e.getMessage());}
    }

}