/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejfinaljoptionpane;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author mvisu
 */
public class AgendaUI {
    
    Agenda agenda;

    public AgendaUI(Agenda agenda) {
        this.agenda = agenda;
    }
    
    
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
            
            eleccion= solicitarEnteroOpcional("siguiente id: "+agenda.siguienteID+"\n1. Añadir personas \n 2. Eliminar personas \n 3. Modificar personas \n 4. Mostrar ordenado por apellidos \n 5. Mostrar ordenado por edad \n 6. Salir \n Introduce tu elección: ");
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
             case 1 ->  this.aniadirPersona();
             case 2 -> { boolean vacia= agenda.listaVacia();
                        if(!vacia)
                            this.eliminarPersona();
             }case 3 -> {boolean vacia= agenda.listaVacia();
                        if(!vacia)
                            this.modificarPersona();
             }case 4 -> { boolean vacia= agenda.listaVacia();
                        if(!vacia){                
                            ArrayList<Persona> ordenadasApellidos= agenda.listaPersonas.stream().sorted((a,b)->a.getApellidos().compareToIgnoreCase(b.getApellidos())).collect(Collectors.toCollection(ArrayList::new));
                             listarPersonas(ordenadasApellidos);
                        }
            }case 5 ->  { boolean vacia= agenda.listaVacia();
                            if(!vacia){                                
                             ArrayList<Persona> ordenadasEdad=  agenda.listaPersonas.stream().sorted((a,b)->a.getEdad()-b.getEdad()).collect(Collectors.toCollection(ArrayList::new));
                             listarPersonas(ordenadasEdad);
                             
                            }        
            }case 6 -> {GestionArchivos.guardarAgenda(agenda.listaPersonas);
                        JOptionPane.showMessageDialog(null, "¡Adiós!"); 
             }
             
        }
       
    }
     
     
     // SOLICITAR ENTERO
    public int solicitarEntero(String mensaje){
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
    public int solicitarEnteroOpcional(String mensaje) {
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
    public String solicitarStringNoNulo(String mensaje){
        
        String eleccion=JOptionPane.showInputDialog(mensaje);
        while (eleccion==null||eleccion.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Se debe introducir un dato");
            eleccion= JOptionPane.showInputDialog(mensaje);            
        }
        return eleccion;       
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
         String listaActual=listarPersonasSimplificado(agenda.listaPersonas);
         
         String nombre= JOptionPane.showInputDialog(listaActual+"\nIntroduce el nombre de la persona que deseas "+accion);
         String apellido= JOptionPane.showInputDialog(listaActual+"\nIntroduce el apellido de la persona que deseas "+accion);
         
         if ((nombre==null||nombre.trim().isEmpty())&&(apellido==null||apellido.trim().isEmpty())){
             return -1;
         } else {
             ArrayList<Persona> resultados=agenda.buscarPersonas(nombre, apellido);         
            int id=this.seleccionarPersona(resultados);
            return id;
         }
         
    }
    
     
     // SOLICITUD DATOS PARA GESTION AGENDA
     //AÑADIR PERSONA
     public void aniadirPersona(){
        
        int id=agenda.getSiguienteID();        
        
        String nombre= solicitarStringNoNulo("Introduce el nombre");
        String apellidos=solicitarStringNoNulo("Introduce los apellidos"); 
        int edad= solicitarEntero("Introduce la edad:");
        String localidad= solicitarStringNoNulo("Introduce la localidad:"); 
        String telefono= solicitarStringNoNulo("Introduce el teléfono");
        
        Persona p= new Persona(id, nombre, apellidos, edad, localidad, telefono);
        if (agenda.aniadirPersona(p)){            
            JOptionPane.showMessageDialog(null, "Persona añadida correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha añadido a la persona");
        }      
        
    }
     
     //ELIMINAR PERSONA
     public void eliminarPersona(){  
         int id=flujoSeleccionPersonaExistente("eliminar");
         if (id!=-1){
             boolean eliminado= agenda.eliminarPersona(id);
             
             if (eliminado){
                 JOptionPane.showMessageDialog(null, "Eliminada correctamente");
             } else {
                 JOptionPane.showMessageDialog(null, "La persona no existe. No se ha modificado la agenda.");
             }
            
         }             
     }
     
     //MODIFICAR PERSONA
      public void modificarPersona(){
                  
         int id= flujoSeleccionPersonaExistente("modificar");
         if (id!=-1){
             Persona existente= agenda.buscarPorId(id);
             if (existente== null){
                    JOptionPane.showMessageDialog(null, "No se ha encontrado a la persona en la agenda. No se ha modificado nada.");
             } else {
                 //Pide los datos
                 String nuevoNombre=JOptionPane.showInputDialog("Introduce el nuevo nombre o intro si no lo deseas modificar");
                if (nuevoNombre != null && nuevoNombre.trim().isEmpty()){
                    nuevoNombre=null;
                }

                String nuevoApellidos=JOptionPane.showInputDialog("Introduce el nuevo apellido o intro si no lo deseas modificar");
                if (nuevoApellidos != null && nuevoApellidos.trim().isEmpty()){
                    nuevoApellidos=null;
                }

                int nuevaEdad=solicitarEnteroOpcional("Introduce la nueva edad o intro si no lo deseas modificar");
                
                String nuevaLocalidad=JOptionPane.showInputDialog("Introduce la nueva localidad o intro si no lo deseas modificar");
                if (nuevaLocalidad != null && nuevaLocalidad.trim().isEmpty()){
                    nuevaLocalidad=null;
                }

                String nuevoTelefono=JOptionPane.showInputDialog("Introduce el nuevo teléfono o intro si no lo deseas modificar"); 
                if (nuevoTelefono != null && nuevoTelefono.trim().isEmpty()){
                   nuevoTelefono=null;
                }
                
                //Construccion objeto:
                Persona nuevosDatos= new Persona(
                    id,
                    nuevoNombre,
                    nuevoApellidos,
                    nuevaEdad,
                    nuevaLocalidad,
                    nuevoTelefono
                );
                
                // Llamar a agenda
                boolean ok= agenda.modificarPersona(id, nuevosDatos);
                if (ok) {
                    JOptionPane.showMessageDialog(null, "Persona modificada correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar la persona");
                }
            }            
            
         }
     }
     
}
