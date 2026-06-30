/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejfinaljoptionpane;


/**
 * ORGANIZACION:
 *  -Agenda: Métodos de agenda (CRUD), y métodos auxiliares que no requieren input del usuario.
 *  -AgendaUI: Métodos de menú o que recogen input del usuario.
 *  -Gestion Archivos: Abre y lee agenda pre-existente, o crea nueva si no lo hay. Guarda antes de cerrar.
 *  -Persona: Modelo Persona.

 * -Funciones de la agenda:
 *      -aniadirPersona: primero pide ID y valida si ya existe ese ID en la agenda. Si no existe, solicita el resto de datos y crea y añade a la persona a la agenda.
 *      -eliminarPersona elimina una persona
 *      -Modificar Persona modifica persona. Permite introducir nuevo dato o intro/cancelar para dejar el dato original
 * -Otras funciones a parte:
 *      
 *      
 *      
 *   
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Agenda miAgenda= GestionArchivos.cargarAgenda();
          AgendaUI ui= new AgendaUI(miAgenda);        
          ui.menu();
    }
    
}
