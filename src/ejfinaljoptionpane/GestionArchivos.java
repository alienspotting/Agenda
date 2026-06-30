/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejfinaljoptionpane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase se encarga de la gestión de archivos - guardado y carga de la agenda
 */
public class GestionArchivos {
    static Scanner teclado= new Scanner(System.in);
    
    // CARGA - CREACIÓN DE AGENDA
    /**
        * Carga la agenda desde un fichero binario.
        *
        * Si el fichero existe y contiene datos válidos, se reconstruye la agenda
        * a partir de la lista de personas almacenada.
        * Si no existe el fichero o ocurre un error de lectura, se crea una agenda vacía.
        *
        * @return Agenda inicializada con los datos cargados o vacía si no hay datos.
    */
    
    public static Agenda cargarAgenda(){
        ArrayList<Persona> listaAgenda;
        Agenda miAgenda;
           

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("miAgenda.dat"))) {
            listaAgenda = (ArrayList<Persona>) entrada.readObject();
            miAgenda= new Agenda(listaAgenda);
            

        } catch (IOException | ClassNotFoundException e) {
            miAgenda = new Agenda();
        }

        return miAgenda;
    }
    
    // GUARDADO DE AGENDA
    /**
        * Guarda la lista de personas en un fichero binario.
        *
        * Serializa la lista completa de contactos y la almacena en el archivo
        * "miAgenda.dat", sobrescribiendo el contenido anterior.
        * Si ocurre un error durante el proceso, se muestra un mensaje de error.
        *
        * @param listaPersonas lista de personas que se desea guardar.
    */
    
    public static void guardarAgenda (ArrayList<Persona> listaPersonas){
        try(ObjectOutputStream fichero=new ObjectOutputStream(new FileOutputStream("miAgenda.dat"))){
         fichero.writeObject(listaPersonas);
            
        }catch (IOException ex){
            System.out.println("Error al guardar la agenda");
        }
        
    }
    
    
    
}
    

