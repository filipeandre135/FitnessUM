package Comparators;

import Actividades.Actividade;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by filipeandre135 on 07-05-2014.
 */
public class ComparatorActividadeData implements Comparator<Actividade>,Serializable {

    public int compare(Actividade m1, Actividade m2)
    {
        if(m1.equals(m2))
            return 0;
        boolean before = (m1.getData().before(m2.getData()));
        if(before == true)
            return -1;
        else return 1;
    }

}
