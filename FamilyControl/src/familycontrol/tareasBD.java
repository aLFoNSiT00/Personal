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
public class tareasBD {

    private int id;
    private String tarea;
    private Date fechaInicio;
    private Date fechaFin;
    private String comentarios;

    public tareasBD(int id, String tarea, Date fechaInicio, Date fechaFin, String comentarios) {
        this.id = id;
        this.tarea = tarea;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.comentarios = comentarios;
    }

    public tareasBD() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
