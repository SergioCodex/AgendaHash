/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendahash;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

/**CLASE AGENDA QUE COMPLEMENTA A AGENDAHASH. Cuenta con los métodos para
 * introducir, eliminar y modificar contactos. 
 *
 * @author Sergio Granero García
 * @version V1.5
 */
public class Agenda implements Serializable{

    //Atributos.
    private Hashtable<String, Persona> tb;

    //Constructor.
    public Agenda() {

        tb = new Hashtable<String, Persona>();

    }

    /**Método que agrega a una hashtable agenda un objeto persona.
     * 
     * @param dni
     * @param nombre
     * @param telefono
     * @return boolean true si se ha introducido, false si no.
     */
    public boolean agregar(String dni, String nombre, long telefono) {

        if (tb.containsKey(dni)) {
            
            return false;
            
        } else {
            
            Persona p1 = new Persona(dni, nombre, telefono);

            tb.put(dni, p1);

            return true;

        }

    }

    /**Elimina un objeto persona de la hashtable a partir de la clave 
     * proporcionada.
     * 
     * @param dni clave
     * @return boolean true si se ha podido eliminar, false si no.
     */
    public boolean eliminar(String dni) {

        if (tb.containsKey(dni)) {

            tb.remove(dni);

            return true;

        } else {

            return false;

        }

    }

    /**Recupera un objeto persona a partir de una clave.
     * 
     * @param dni clave
     * @return Persona solo si existe en la tabla hash, nulo si no.
     */
    public Persona recuperar(String dni) {

        if (tb.containsKey(dni)) {

            return tb.get(dni);

        } else {

            return null;
        }

    }

    /**Método que devuelve una colección enumeration con todas las claves
     * de la tabla hash.
     * 
     * @return Enumeration Collection.
     */
    public Enumeration<String> total() {

        return tb.keys();

    }

}
