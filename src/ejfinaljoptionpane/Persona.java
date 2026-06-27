/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejfinaljoptionpane;

import java.io.Serializable;

/**
 *
 * Necesitamos guardar los datos asociados a personas en nuestra agenda. Guardaremos el identificador (único por persona), el nombre y los apellidos, la edad, 
 * la localidad donde vive y su número de teléfono. 
 */
public class Persona implements Serializable {
    int identificador;
    String nombre;
    String apellidos;
    int edad;
    String localidad;
    String telefono;
    
    //CONSTRUCTORES

    public Persona(int identificador, String nombre, String apellidos, int edad, String localidad, String telefono) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.localidad = localidad;
        this.telefono = telefono;
    }

    public Persona(int identificador) {
        this.identificador = identificador;
    }
    
    // EQUALS

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.identificador;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return this.identificador == other.identificador;
    }
        
    
    // to String

    @Override
    public String toString() {
        return "- "+ nombre + " " + apellidos + ". \n \t \t \t \t \t Edad: " + edad + ", localidad: " + localidad + "\n \t \t \t \t \t Teléfono: " + telefono;
    }
    
    
    
    // GETTERS AND SETTERS

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
