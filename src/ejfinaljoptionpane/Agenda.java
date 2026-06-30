/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejfinaljoptionpane;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * -ESTA CLASE CENTRALIZA: FUNCIONALIDADES CRUD de la AGENDA y métodos AUXILIARES que no requieren interacción del usuario
 *-MÉTODOS AGENDA
 *  -aniadirPersona() : recibe una persona y la añade en listaPersonas
 *  -eliminarPersona(): recibe ID y elimina de listaPersonas la persona con ese ID
 *  -modificarPersona(): recibe ID y nuevosDatos. Comprueba si los datos introducidos son o no null, 
 *      y solo modifica aquellos campos en los que el usuario ha introducido información.
 * -listaVacia comprueba si hay algun elemento en listaPersonas
 * -buscarPersonas busca por nombre y apellido y devuelve un ArrayList con los resultados obtenidos
 * -buscarPersonasPorID busca personas según ID y devuelve Persona que coincide con ese ID
 */     
public class Agenda {
    
    ArrayList<Persona> listaPersonas;
    int siguienteID;
    
    //CONSTRUCTORES
    public Agenda() {
        listaPersonas= new ArrayList<>();
        siguienteID= 1;
    }

    public Agenda(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;        
        siguienteID= listaPersonas.stream().mapToInt(x->x.identificador).max().getAsInt()+1;
    }
    
    
    
    /*--------------------MÉTODOS AUXILIARES--------------------*/
  
    // COMPROBAR LISTA VACIA
    public boolean listaVacia(){
        if (listaPersonas.isEmpty()){
                JOptionPane.showMessageDialog(null, "Todavía no hay personas en la agenda.");
                return true;
        }else
            return false;
    }
    
   
    //BUSCAR PERSONAS
    /**
        * Busca las personas que coinciden con el nombre y/o apellidos indicados.
        * Si alguno de los parámetros es null o está vacío, ese criterio no se utiliza
        * en la búsqueda.
        *
        * @param nombre Nombre a buscar.
        * @param apellidos Apellidos a buscar.
        * @return Lista de personas que cumplen los criterios de búsqueda.
    */
 
    public ArrayList<Persona> buscarPersonas(String nombre, String apellidos){
      ArrayList<Persona> resultado = new ArrayList<>();

      boolean hayNombre = nombre != null && !nombre.trim().isEmpty();
      boolean hayApellidos = apellidos != null && !apellidos.trim().isEmpty();
      
      if (hayNombre){
          nombre= nombre.trim().toLowerCase();
      }
      
      if (hayApellidos){
          apellidos=apellidos.trim().toLowerCase();
      }       
       

        for (Persona p : this.listaPersonas) {

           if (hayNombre && !hayApellidos){
               if (p.nombre.toLowerCase().contains(nombre)){
                   resultado.add(p);
               }
           } else if (!hayNombre && hayApellidos){
               if (p.apellidos.toLowerCase().contains(apellidos)){
                   resultado.add(p);
               }
           } else if (hayNombre&&hayApellidos){
               if (p.apellidos.toLowerCase().contains(apellidos)&&p.nombre.toLowerCase().contains(nombre)){
                   resultado.add(p);
               }
           }
        }

        return resultado;    
    }
    
    //BUSCAR PERSONA POR ID
    public Persona buscarPorId(int id) {
    for (Persona p : listaPersonas) {
        if (p.getIdentificador() == id) {
            return p;
        }
    }
    return null;
    }
     
    /*--------------------FUNCIONES AGENDA--------------------*/
    //AÑADIR PERSONA
    /**
        * Añade una nueva persona a la agenda.
        *
        * @param p Persona a la que añadir
        * @return true si la persona se añadió correctamente.
     */
 
    public boolean aniadirPersona(Persona p) {
        boolean correcto=listaPersonas.add(p);        
        
        if (correcto){
            siguienteID++;
        }
        return correcto;
    }
    
    
    //ELIMINAR PERSONA
     /**
        * Elimina una persona de la agenda según su ID
        *
        * @param id int - ID de la persona que se desea eliminar
        * @return true si la persona se eliminó correctamente.
     */
    
    public boolean eliminarPersona(int id) {
        return listaPersonas.removeIf(p -> p.getIdentificador() == id);
    }
    
     
     //MODIFICAR PERSONA
    /**
        * Modifica datos de persona ya existente en la agenda
        * Revisa previamente si el campo es o no null. En caso de ser null, ese atributo no es modificado
        * en la persona original.
        * @param id int - ID de la persona que se desea modificar
        * @param nuevosDatos Persona - nuevos datos que se desean introducir en la persona.
        * @return true si la persona se modificó correctamente.
     */
    public boolean modificarPersona(int id, Persona nuevosDatos) {

        for (Persona p : listaPersonas) {
            if (p.getIdentificador() == id) {

                if (nuevosDatos.getNombre() != null)
                    p.setNombre(nuevosDatos.getNombre());

                if (nuevosDatos.getApellidos() != null)
                    p.setApellidos(nuevosDatos.getApellidos());

                if (nuevosDatos.getEdad() > 0)
                    p.setEdad(nuevosDatos.getEdad());

                if (nuevosDatos.getLocalidad() != null)
                    p.setLocalidad(nuevosDatos.getLocalidad());

                if (nuevosDatos.getTelefono() != null)
                    p.setTelefono(nuevosDatos.getTelefono());

                return true;
            }
        }

        return false;
    }
    
    
      /*--------------------GETTERS AND SETTERS--------------------*/

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public int getSiguienteID() {
        return siguienteID;
    }

    public void setSiguienteID(int siguienteID) {
        this.siguienteID = siguienteID;
    }
     
     
     

}