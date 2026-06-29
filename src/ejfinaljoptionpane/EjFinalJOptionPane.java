/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejfinaljoptionpane;


/**
 *
 * @author IFC303_1
 */
public class EjFinalJOptionPane {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Agenda miAgenda= GestionArchivos.cargarAgenda();
          AgendaUI ui= new AgendaUI(miAgenda);        
          ui.menu();
    }
    
}
