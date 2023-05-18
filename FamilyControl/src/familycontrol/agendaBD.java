/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package familycontrol;

import java.sql.Date;

/**
 *
 * @author Data
 */
public class agendaBD {
    
    
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private Date cumpleaños;
    private String direccion;

    public agendaBD(int id, String nombre, String apellido, String telefono, String mail, Date cumpleaños, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.cumpleaños = cumpleaños;
        this.direccion = direccion;
    }

    public agendaBD() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCumpleaños() {
        return cumpleaños;
    }

    public void setCumpleaños(Date cumpleaños) {
        this.cumpleaños = cumpleaños;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
