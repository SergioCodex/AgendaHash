/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendahash;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author root-admin
 */
public class Agenda implements Serializable{

    //Atributos.
    private Hashtable<String, Persona> tb;

    public Agenda() {

        tb = new Hashtable<String, Persona>();

    }

    public boolean agregar(String dni, String nombre, long telefono) {

        if (!tb.containsKey(dni)) {

            Persona p1 = new Persona(dni, nombre, telefono);

            tb.put(dni, p1);

            return true;

        } else {

            return false;

        }

    }

    public boolean eliminar(String dni) {

        if (tb.containsKey(dni)) {

            tb.remove(dni);

            return true;

        } else {

            return false;

        }

    }

    public Persona recuperar(String dni) {

        if (tb.containsKey(dni)) {

            return tb.get(dni);

        } else {

            return null;
        }

    }

    public Enumeration<String> total() {

        return tb.keys();

    }

}
