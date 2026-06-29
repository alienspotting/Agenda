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
 *
 *  Hacer un programa que nos permita 1. Añadir Personas a la agenda, 2. Eliminar Personas de la Agenda, 3. Modificar Personas de la Agenda, 4. Mostrar ordenados Por Apellidos, 5. Mostrar ordenados por edad. 
 * La información que se vaya añadiendo cuando se cierre el programa se guardará en un fichero, que se podrá recuperar cuando se vuelva a abrir la aplicación. 
 * Si una Persona ya está en la agenda, no se podrá añadir y el programa nos avisará. Lo mismo si intentamos eliminar una Persona que no exista.
 * 
 * ORGANIZACION:
 *  -Menu() se encarga de llamar al resto de metodos  de menú (leerOpcionMenu() y manejoMenu())
 *      -leerOpcionMenu pide la opcion y valida que esté en rango
 *      -manejoMenu es el switch - llama a las funciones según la eleccion
 * -Funciones de la agenda:
 *      -aniadirPersona: primero pide ID y valida si ya existe ese ID en la agenda. Si no existe, solicita el resto de datos y crea y añade a la persona a la agenda.
 *      -eliminarPersona elimina una persona
 *      -Modificar Persona modifica persona. Permite introducir nuevo dato o intro/cancelar para dejar el dato original
 * -Otras funciones a parte:
 *      solicitarEntero pide un entero y controla el error en caso de que se introduzca algo que no sea un entero. Se le pasa un mensaje para mostrar en el panel.
 *      solicitarStringNoNulo solicita String hasta que se introduzca un dato. Usado en aniadirPersona.  Se le pasa un mensaje para mostrar en el panel.
 *      solicitarEnteroOpcional pide un Entero pero admite que se introduzca enter o se pulse cancelar. Se utiilza para "Modificar persona" -> Edad. También para que el usuario pueda pulsar cancelar en el menú y en las opciones de modificar y eliminary salga.
 *      listarPersonas recibe un ArrayList y muestra las personas
 *      listaVacia comprueba si hay algun elemento en la lista
 *      buscarPersonas busca por nombre y apellido y devuelve un ArrayList con los resultados obtenidos
 *
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
    
    
    
    //MÉTODOS
    
    
     //MENU
    
    public void menu(){
        int eleccion=0;
        while (eleccion!=6){
            eleccion= leerOpcionMenu();
            manejoMenu(eleccion);
        }
    }
    
    
       // Solicitar y validar opcion 
    public int leerOpcionMenu(){
        
        boolean enRango=false;
        
        int eleccion=0;
        
        while (!enRango){          
            
            eleccion= solicitarEnteroOpcional("siguiente id: "+siguienteID+"\n1. Añadir personas \n 2. Eliminar personas \n 3. Modificar personas \n 4. Mostrar ordenado por apellidos \n 5. Mostrar ordenado por edad \n 6. Salir \n Introduce tu elección: ");
            if (eleccion==-1){
                eleccion=6;
                enRango=true;
            }else if (eleccion>=1&&eleccion<=6){
                   enRango=true;
               } else{
                 JOptionPane.showMessageDialog(null, "Debes introducir un número entre 1 y 6. Introduce de nuevo el número:");
               }  
        }      

        return eleccion;        
    }
        
    
    
    public void manejoMenu(int eleccion){
        switch (eleccion){
             case 1 ->  aniadirPersona();
             case 2 -> { boolean vacia= listaVacia();
                        if(!vacia)
                            eliminarPersona();
             }case 3 -> {boolean vacia= listaVacia();
                        if(!vacia)
                            modificarPersona();
             }case 4 -> { boolean vacia= listaVacia();
                        if(!vacia){                
                            ArrayList<Persona> ordenadasApellidos= listaPersonas.stream().sorted((a,b)->a.getApellidos().compareToIgnoreCase(b.getApellidos())).collect(Collectors.toCollection(ArrayList::new));
                             listarPersonas(ordenadasApellidos);
                        }
            }case 5 ->  { boolean vacia= listaVacia();
                            if(!vacia){                                
                             ArrayList<Persona> ordenadasEdad=  listaPersonas.stream().sorted((a,b)->a.getEdad()-b.getEdad()).collect(Collectors.toCollection(ArrayList::new));
                             listarPersonas(ordenadasEdad);
                             
                            }        
            }case 6 -> {GestionArchivos.guardarAgenda(this.listaPersonas);
                        JOptionPane.showMessageDialog(null, "¡Adiós!"); 
             }
             
        }
       
    }

// SOLICITAR ENTERO
    private int solicitarEntero(String mensaje){
        boolean esValido=false;
        int eleccion=0;
        while (!esValido){
            try{            
                eleccion= Integer.parseInt(JOptionPane.showInputDialog(mensaje));
                esValido=true;
            } catch (InputMismatchException | NumberFormatException ex){                
                JOptionPane.showMessageDialog(null, "La elección no es válida, debes introducir un número.");
            }
        }
        return eleccion;       
    }
    
    // SOLICITAR ENTERO OPCIONAL
    private int solicitarEnteroOpcional(String mensaje) {
        boolean esValido=false;        
        int numeroValidado=0;

    while (!esValido) {

        String numero = JOptionPane.showInputDialog(mensaje);

        if (numero == null || numero.equals("")) {
            return -1;
        }

        try {
            numeroValidado= Integer.parseInt(numero);
            esValido=true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Debes introducir un número válido");
        }
    }
    
    return numeroValidado;
}
    
// SOLICITAR STRING Y COMPROBAR QUE NO ES NULL
    private String solicitarStringNoNulo(String mensaje){
        
        String eleccion=JOptionPane.showInputDialog(mensaje);
        while (eleccion==null||eleccion.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Se debe introducir un dato");
            eleccion= JOptionPane.showInputDialog(mensaje);            
        }
        return eleccion;       
    }
    
    // COMPROBAR LISTA VACIA
    private boolean listaVacia(){
        if (listaPersonas.isEmpty()){
                JOptionPane.showMessageDialog(null, "Todavía no hay personas en la agenda.");
                return true;
        }else
            return false;
    }
    
    //LISTAR PERSONAS
    private void listarPersonas(ArrayList<Persona> lista){
        String listar="";
        for (Persona p: lista){
            listar+=p+"\n";
        }
        JOptionPane.showMessageDialog(null, listar);
    }
    
       //LISTAR PERSONAS SIMPLIFICADO
    private String listarPersonasSimplificado(ArrayList<Persona> lista){
        String listar="";
        for (Persona p: lista){
            listar+="- "+p.nombre+" "+
                    p.apellidos+" - "+p.telefono+"\n";
        }
        return listar;
    }
    
    //BUSCAR PERSONAS
    private ArrayList<Persona> buscarPersonas(String nombre, String apellidos){
        ArrayList<Persona> resultado=new ArrayList<>();
        if (nombre!=null){            
            nombre=nombre.trim().toLowerCase();
        }
        if (apellidos!=null){            
            apellidos=apellidos.trim().toLowerCase();
        }
        if (nombre !=null && !nombre.trim().equalsIgnoreCase("")&&apellidos!=null && !apellidos.trim().equalsIgnoreCase("")){
            for (Persona p: this.listaPersonas){
                if (p.nombre.toLowerCase().contains(nombre)&&p.apellidos.toLowerCase().contains(apellidos))
                    resultado.add(p);
            }
         } else if ((nombre !=null && !nombre.trim().isEmpty())&&(apellidos==null || apellidos.trim().equalsIgnoreCase(""))){
              for (Persona p: this.listaPersonas){
                if (p.nombre.toLowerCase().contains(nombre))
                    resultado.add(p);
            } 
         }else if ((nombre ==null || nombre.trim().isEmpty())&&( apellidos!=null && !apellidos.trim().isEmpty())){
             for (Persona p: this.listaPersonas){
                if (p.apellidos.toLowerCase().contains(apellidos))
                    resultado.add(p);
            } 
         }
             
        
        return resultado;
    }
    
    // SELECCIONAR PERSONA ENTRE LOS RESULTADOS OBTENIDOS
    private int seleccionarPersona (ArrayList<Persona> listado){
        String cadena= "";
        int contador=1;
        int eleccion=-1;
        if (listado==null || listado.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se han obtenido resultados");
            return -1;
        } else {            
            for (Persona p: listado){
                cadena+=contador+" - "+p.nombre+" "+p.apellidos+" - "+p.telefono+"\n";
                contador++;
            }
            eleccion=solicitarEnteroOpcional("Se han obtenido varias coincidencias. Selecciona el número de la lista de la persona que deseas seleccionar: \n"+cadena);
            if (eleccion==-1){
                return eleccion;
            }
            else {
               boolean enRango=false;
            
                while (!enRango){
                    if (eleccion<1||eleccion>listado.size()){
                        eleccion=solicitarEnteroOpcional(cadena+"\nSelecciona un número dentro de rango");
                        if (eleccion==-1){
                            return eleccion;
                        }
                    } else {
                        enRango=true;
                    }
                }
                return listado.get(eleccion-1).identificador;
            }            
            
        }
        
    }

    //PIDE DATOS DE PERSONA QUE SE QUIERE MODIFICAR O ELIMINAR
     private int flujoSeleccionPersonaExistente(String accion){
         String listaActual=listarPersonasSimplificado(this.listaPersonas);
         /*String nombre= this.solicitarStringNoNulo();
         String apellido= this.solicitarStringNoNulo(listaActual+"\nIntroduce el apellido de la persona que deseas "+accion);*/
         String nombre= JOptionPane.showInputDialog(listaActual+"\nIntroduce el nombre de la persona que deseas "+accion);
         String apellido= JOptionPane.showInputDialog(listaActual+"\nIntroduce el apellido de la persona que deseas "+accion);
         
         if ((nombre==null||nombre.trim().isEmpty())&&(apellido==null)||apellido.trim().isEmpty()){
             return -1;
         } else {
             ArrayList<Persona> resultados=buscarPersonas(nombre, apellido);         
            int id=this.seleccionarPersona(resultados);
            return id;
         }
         
    }
     
     
//FUNCIONES AGENDA
    //AÑADIR PERSONA
    void aniadirPersona(){
        
        int id=this.siguienteID;        
        
        String nombre= solicitarStringNoNulo("Introduce el nombre");
        String apellidos=solicitarStringNoNulo("Introduce los apellidos"); 
        int edad= solicitarEntero("Introduce la edad:");
        String localidad= solicitarStringNoNulo("Introduce la localidad:"); 
        String telefono= solicitarStringNoNulo("Introduce el teléfono");

        listaPersonas.add(new Persona(id, nombre, apellidos, edad, localidad, telefono));
        JOptionPane.showMessageDialog(null, "Persona añadida correctamente");
        
        this.siguienteID+=1;
        
    }
    
   
    
    //ELIMINAR PERSONA
     void eliminarPersona(){  
         int id=flujoSeleccionPersonaExistente("eliminar");
         if (id!=-1){
             if (!listaPersonas.contains(new Persona(id))){
             JOptionPane.showMessageDialog(null, "La persona no existe. No se ha modificado la agenda.");
            }else {
                    listaPersonas.remove(new Persona(id));
                    JOptionPane.showMessageDialog(null, "Eliminada correctamente");
            }   
         }
             
     }
     
     //MODIFICAR PERSONA
     void modificarPersona(){
         /*String listado="";
         ArrayList<Persona> nombres= listaPersonas.stream().sorted((a,b)->a.getIdentificador()-b.getIdentificador()).collect(Collectors.toCollection(ArrayList::new));
         for (Persona p: nombres){
             listado+=p.getIdentificador()+". "+p.getNombre()+" "+p.getApellidos()+"\n";
         }
         */
         
         int id= flujoSeleccionPersonaExistente("modificar");
         if (id!=-1){
            if (!listaPersonas.contains(new Persona(id))){
                JOptionPane.showMessageDialog(null, "La persona no existe. No se ha modificado la agenda.");
            }else {
                   int indice= listaPersonas.indexOf(new Persona(id));
                   Persona personaModificada=listaPersonas.get(indice);

                   String nuevoNombre=JOptionPane.showInputDialog("Introduce el nuevo nombre o intro si no lo deseas modificar");

                   if (nuevoNombre != null && !nuevoNombre.equals("")){
                       personaModificada.setNombre(nuevoNombre);
                   }

                   String nuevoApellidos=JOptionPane.showInputDialog("Introduce el nuevo apellido o intro si no lo deseas modificar");
                   if (nuevoApellidos != null && !nuevoApellidos.equals("")){
                       personaModificada.setApellidos(nuevoApellidos);
                   }

                   int nuevaEdad=solicitarEnteroOpcional("Introduce la nueva edad o intro si no lo deseas modificar");
                   if (nuevaEdad>=0){
                       personaModificada.setEdad(nuevaEdad);
                   }

                   String nuevaLocalidad=JOptionPane.showInputDialog("Introduce la nueva localidad o intro si no lo deseas modificar");
                   if (nuevaLocalidad != null && !nuevaLocalidad.equals("")){
                       personaModificada.setLocalidad(nuevaLocalidad);
                   }

                   String nuevoTelefono=JOptionPane.showInputDialog("Introduce el nuevo teléfono o intro si no lo deseas modificar"); 
                   if (nuevoTelefono != null && !nuevoTelefono.equals("")){
                       personaModificada.setTelefono(nuevoTelefono);
                   }


            }       
         }
     }

    public ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
     
     
     

}