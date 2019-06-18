package Exceptions;

/**
 * Created by filipeandre135 on 03-06-2014.
 */
public class UtilizadorNaoExisteException extends Exception
{
    public UtilizadorNaoExisteException()
    {
        super("Utilizador não registado");
    }
}
