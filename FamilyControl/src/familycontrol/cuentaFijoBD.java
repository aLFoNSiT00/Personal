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
public class cuentaFijoBD {
    
    private int id;
    private Double gastoFijo;
    private String conceptoFijo;
    private Date fechaFijo;
    

    public cuentaFijoBD(int id, Double gastoFijo, String conceptoFijo, Date fechaFijo) {
        this.id = id;
        this.gastoFijo = gastoFijo;
        this.conceptoFijo = conceptoFijo;
        this.fechaFijo = fechaFijo;
    }
    
    public cuentaFijoBD(){
        
        
    }

    public String getConceptoFijo() {
        return conceptoFijo;
    }

    public void setConceptoFijo(String conceptoFijo) {
        this.conceptoFijo = conceptoFijo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getGastoFijo() {
        return gastoFijo;
    }

    public void setGastoFijo(Double gastoFijo) {
        this.gastoFijo = gastoFijo;
    }

    public Date getFechaFijo() {
        return fechaFijo;
    }

    public void setFechaFijo(Date fechaFijo) {
        this.fechaFijo = fechaFijo;
    }
    
    
    
}
