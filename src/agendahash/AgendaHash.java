/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendahash;

import Sleer2.SLeer2;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Agenda de contactos. Serialización/Hastables.
 *
 * @author Sergio Granero García
 * @version V2.3
 */
public class AgendaHash {

    //Atributos.
    private static String dni;
    private static String nombre;
    private static long telefono;
    private static Agenda aq;

    /**
     * Método que da 15 saltos de líneas.
     *
     */
    public static void espacios() {

        SLeer2.datoString("Enter para continuar...");

        for (int i = 0; i < 15; i++) {

            System.out.println("");

        }

    }

    /**
     * Método que pide por pantalla un número, asegurándose que esté entre 1 y
     * 6.
     *
     * @return int número introducido por teclado.
     */
    public static int pideNumero() {

        int num;

        do {

            num = SLeer2.datoInt("Introduce una opción: ");

            if (num < 1 || num > 6) {

                System.err.println("\n¡Opción no disponible!\n");

            }

        } while (num < 1 || num > 6);

        return num;

    }

    /**
     * Método que pide un nombre por pantalla, se asegura de que se haya
     * introducido algún valor.
     *
     * @return String nombre introducido por teclado.
     */
    public static String pideNombre() {

        String nom;

        do {

            nom = SLeer2.datoString("Introduce un nombre: ");

            if (nom.isEmpty()) {

                System.err.println("¡No ha introducido ningún nombre!");

            }

        } while (nom.isEmpty());

        return nom;
    }

    /**
     * Método que pide un DNI por pantalla, realiza una serie de comprobaciones
     * para averiguar si el DNI es correcto, es decir, que contenga 8 dígitos y
     * una letra.
     *
     * @return String dni que se introduce por pantalla.
     */
    public static String pideDni() {

        //Atributo.
        String dni;
        boolean long_correcta = false;
        boolean letra_correcta = false;

        do {

            dni = SLeer2.datoString("Introduce el dni: ").toUpperCase();

            //COMPRUEBA LA LONGITUD.
            if (dni.length() != 9) {

                System.err.println("DNI erróneo.");

            } else {
                long_correcta = true;
            }

            //COMPROBAR SI HAY MÁS LETRAS.
            //COMPRUEBA SI LA ÚLTIMA POSICIÓN ES UNA LETRA.
            if (!((dni.charAt(dni.length() - 1)) >= 'A') || !((dni.charAt(dni.length() - 1)) <= 'Z')) {

                System.err.println("DNI erróneo.");

            } else {
                letra_correcta = true;
            }

        } while (!long_correcta || !letra_correcta);

        return dni;

    }

    /**
     * Método que pide un teléfono de 9 cifras por pantalla.
     *
     * @return Long Wrappers que contiene el número de teléfono.
     */
    public static Long pideTelefono() {

        Long telef;
        long num;

        do {
            num = SLeer2.datoLong("Introduce un teléfono: ");
            telef = new Long(num);

            if (telef.toString().length() != 9) {
                System.err.println("\n¡Número incorrecto! Vuelve a intentarlo.");
            }

        } while (telef.toString().length() != 9);

        return telef;

    }

    /**
     * Método que pide al usuario si desea continuar con la acción, en este
     * caso, borrar un contacto.
     *
     * @return String respuesta negativa o positiva.
     */
    public static String pideRespuesta() {

        String resp;
        do {
            resp = SLeer2.datoString("¿Seguro que quieres borrar el contacto? [s/n]: ");

            if (!resp.toLowerCase().equals("s") && !resp.toLowerCase().equals("n")) {

                System.err.println("\nRespuesta desconocida, vuelva a intentarlo.\n");

            }

        } while (!resp.toLowerCase().equals("s") && !resp.toLowerCase().equals("n"));

        return resp.toLowerCase();
    }

    /**
     * Método que carga el fichero agenda.dat, serilializando el
     * objeto agenda.
     *
     */
    private static void cargarAgendaHash() {

        File agenda = null;
        FileInputStream flujo_entrada = null;
        ObjectInputStream ois = null;

        aq = new Agenda();

        try {

            agenda = new File("Agenda.dat");

            if (agenda.exists()) {

                flujo_entrada = new FileInputStream(agenda);
                ois = new ObjectInputStream(flujo_entrada);

                aq = (Agenda) ois.readObject();

                /*while (true) {

                    Persona p = null;
                    p = (Persona) ois.readObject();

                    aq.agregar(p.getDni(), p.getNombre(), p.getTelefono());

                }*/
            }

        } catch (EOFException eof) {
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Error.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada.");
        } finally {

            if (ois != null) {
                try {
                    ois.close();
                    flujo_entrada.close();
                } catch (IOException ex) {
                    System.out.println("IOException.");
                }

            }

        }
    }

    /**
     * Método que serializa el objeto agenda y lo guarda en un fichero.
     *
     */
    public static void guardarAgendaHash() {

        File agenda;
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {

            agenda = new File("Agenda.dat");
            fos = new FileOutputStream(agenda);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(aq);

            //String clave;
            //Persona per;

            /*Enumeration<String> lista = aq.total();

            while (lista.hasMoreElements()) {

                clave = lista.nextElement();
                per = (Persona) aq.recuperar(clave);
                oos.writeObject(per);

            }*/
            if (oos != null) {
                oos.close();
                fos.close();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fichero no encontrado.");
        } catch (IOException ex) {
            System.out.println("IOException.");
        }

    }

    /**
     * Imprime el menú del ejercicio agenda.
     *
     * @return int número de la opción seleccionada.
     */
    public static int menuAgenda() {

        System.out.println("\n+---------------------------------------+"
                + "\n|\t      MENÚ AGENDA\t\t|"
                + "\n+---------------------------------------+"
                + "\n|\t\t\t\t\t|"
                + "\n|\t[1] - Agregar persona\t\t|"
                + "\n|\t[2] - Buscar persona\t\t|"
                + "\n|\t[3] - Eliminar persona\t\t|"
                + "\n|\t[4] - Modificar persona\t\t|"
                + "\n|\t[5] - Listar agenda\t\t|"
                + "\n|\t[6] - Cerrar agenda\t\t|"
                + "\n|\t\t\t\t\t|"
                + "\n+---------------------------------------+");

        return pideNumero();

    }

    /**
     * Método que añade un contacto a la agenda, preguntando en el mismo el DNI,
     * el nombre y el telefono a guardar.
     *
     */
    public static void agregarAgenda() {

        dni = pideDni();
        nombre = pideNombre();
        telefono = pideTelefono();

        if (aq.agregar(dni, nombre, telefono)) {
            System.out.println("\n\nContacto añadido.");
        } else {
            System.err.println("Esa persona ya está registrada.");
        }

    }

    /**Realiza una búsqueda dependiendo del dni introducido por teclado.
     * 
     */
    public static void buscarAgenda() {

        dni = pideDni();
        Persona per;

        per = aq.recuperar(dni);

        if (per != null) {

            System.out.println("\n\n### Contacto ###");
            per.print();

        } else {
            System.err.println("Ese contacto no existe.");
        }

    }

    /**Elimina un contacto de la agenda introduciendo su DNI. A su vez pregunta
     * si el usuario está seguro de su decisión.
     * 
     */
    public static void eliminarAgenda() {

        dni = pideDni();
        Persona per;
        String resp;
        boolean borrado;

        per = aq.recuperar(dni);

        if (per != null) {

            resp = pideRespuesta();

            if (resp.equals("s")) {

                borrado = aq.eliminar(dni);

                if (borrado) {
                    System.out.println("\n\nContacto borrado.");
                } else {
                    System.err.println("\n\nNo es posible borrar.");
                }

            } else {

                System.out.println("\nCancelando...");

            }

        } else {
            System.err.println("\nEse contacto no existe.");
        }

    }

    /**Modifica un contacto de la agenda. Se introduce el dni del contacto que
     * se desea modificar.
     * 
     */
    public static void modificarAgenda() {

        dni = pideDni();

        Persona per = (Persona) aq.recuperar(dni);

        if (per != null) {

            System.out.println("\n\n### Contacto ###");
            per.print();

            if (aq.eliminar(dni)) {

                System.out.println("");
                nombre = pideNombre();
                telefono = pideTelefono();

                aq.agregar(dni, nombre, telefono);

                System.out.println("\n\n[Contacto modificado]");

            } else {

                System.err.println("\nImposible modificar.");

            }

        } else {

            System.err.println("\nEse contacto no existe.");

        }

    }

    /**Método que lista por pantalla por orden de DNI todos los contactos de la 
     * agenda.
     * 
     */
    public static void listarAgenda() {

        String clave;
        Persona per;
        int cont = 1;

        //ArrayList<String> list = Collection.list(aq.total());
        //Collection.sort(list);
        Enumeration<String> lista = aq.total();
        ArrayList<String> dnies = new ArrayList<String>();

        while (lista.hasMoreElements()) {

            clave = lista.nextElement();
            dnies.add(clave);

        }

        Collections.sort(dnies);

        for (String dni : dnies) {

            per = (Persona) aq.recuperar(dni);
            System.out.println("\n### Contacto Nº" + cont + " ###");
            per.print();
            System.out.println("");
            cont++;

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Carga.
        cargarAgendaHash();

        //ATRIBUTOS.
        int opcion;

        //Menú.
        opcion = menuAgenda();

        while (opcion != 6) {

            switch (opcion) {
                case 1:

                    SLeer2.limpiar();
                    agregarAgenda();
                    break;

                case 2:

                    SLeer2.limpiar();
                    buscarAgenda();
                    break;

                case 3:

                    SLeer2.limpiar();
                    eliminarAgenda();
                    break;

                case 4:

                    SLeer2.limpiar();
                    modificarAgenda();
                    break;

                case 5:

                    SLeer2.limpiar();
                    System.out.println("");
                    listarAgenda();
                    break;

                case 6:

                    SLeer2.limpiar();
                    break;

                default:
                    throw new AssertionError();
            }

            espacios();
            opcion = menuAgenda();

        }

        //Guardado.
        guardarAgendaHash();

        System.out.println("\n\n[Agenda guardada. Fin del programa]\n");

    }//FIN MAIN

}//FIN CLASS
