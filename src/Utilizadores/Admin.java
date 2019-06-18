package Utilizadores;

import java.io.Serializable;

/**
 * Write a description of class Admin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Admin extends Utilizador implements Serializable
{
    

    /**
     * Constructor for objects of class Admin
     */
    public Admin()
    {
        super();
    }
    
    public Admin(String email,String password)
    {
        super(email,password);
    }
    
    public Admin(Admin a)
    {
        super(a.getEmail(),a.getPassword());
    }

    public Admin clone()
    {
        return new Admin(this);
    }

    public boolean equals(Object o) {

        if (this == o)
            return true;
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        Admin a = (Admin)o;
        return (super.equals(a));
    }

    
}
