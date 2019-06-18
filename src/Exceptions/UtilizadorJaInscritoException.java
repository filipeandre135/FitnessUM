package Exceptions;

/**
 * Created by filipeandre135 on 07-06-2014.
 */
public class UtilizadorJaInscritoException extends Exception{

    public UtilizadorJaInscritoException()
    {
        super("Utilizador já está inscrito no evento");
    }
}
