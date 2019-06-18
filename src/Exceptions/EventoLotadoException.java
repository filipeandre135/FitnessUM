package Exceptions;

import Actividades.Evento;

/**
 * Created by filipeandre135 on 07-06-2014.
 */
public class EventoLotadoException extends Exception
{
    public EventoLotadoException()
    {
        super("Este Evento está lotado.");
    }
}
