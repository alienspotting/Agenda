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
 * Esta clase se encargará de la gestión de archivos - guardado y carga de la agenda
 */
public class GestionArchivos {
    static Scanner teclado= new Scanner(System.in);
    
    // CARGA - CREACIÓN DE AGENDA
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
    public static void guardarAgenda (ArrayList<Persona> listaPersonas){
        try(ObjectOutputStream fichero=new ObjectOutputStream(new FileOutputStream("miAgenda.dat"))){
         fichero.writeObject(listaPersonas);
            
        }catch (IOException ex){
            System.out.println("Error al guardar la agenda");
        }
        
    }
    
    
    
}
    

