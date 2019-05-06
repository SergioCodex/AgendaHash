/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendahash;

import java.io.Serializable;

/**
 *
 * @author Sergio Granero García
 * @version V1.
 */
public class Persona implements Serializable {

    //Atributos.
    private String dni;
    private String nombre;
    private long telefono;

    //CONSTRUCTORES
    public Persona(String dni, String nombre, long telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;

    }

    public Persona() {

    }

    //GETTERS
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    //SETTERS
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**Método que imprime los atributos de un objeto persona.
     * 
     */
    public void print() {

        System.out.println("\n- DNI: " + this.getDni()
                + "\n- Nombre: " + this.getNombre()
                + "\n- Telefono: " + this.getTelefono());

    }

}
