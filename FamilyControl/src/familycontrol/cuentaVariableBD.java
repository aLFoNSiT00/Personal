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
public class cuentaVariableBD {
    
    private int id;
    private Double gastoVariable;
    private String conceptoVariable;
    private Date fechaVariable;

    public cuentaVariableBD(int id, Double gastoVariable, String conceptoVariable, Date fechaVariable) {
        this.id = id;
        this.gastoVariable = gastoVariable;
        this.conceptoVariable =conceptoVariable;
        this.fechaVariable = fechaVariable;
    }
    
    public cuentaVariableBD(){
        
    }

    public String getConceptoVariable() {
        return conceptoVariable;
    }

    public void setConceptoVariable(String conceptoVariable) {
        this.conceptoVariable = conceptoVariable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getGastoVariable() {
        return gastoVariable;
    }

    public void setGastoVariable(Double gastoVariable) {
        this.gastoVariable = gastoVariable;
    }

    public Date getFechaVariable() {
        return fechaVariable;
    }

    public void setFechaVariable(Date fechaVariable) {
        this.fechaVariable = fechaVariable;
    }
    
}
