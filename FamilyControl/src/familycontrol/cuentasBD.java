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
public class cuentasBD {
 
    private int id;
    private Double saldoInicial;
    private Double ingresoAdicional;
    private Double gastoFijo;
    private String conceptoFijo;
    private Double gastoVariable;
    private String concepto;
    private Date fechaGastoFijo;
    private Date fechaGastoVariable;
    
    

    public cuentasBD(int id,  Double saldoInicial, Double ingresoAdicional, 
            Double gastoFijo, String conceptoFijo, Double gastoVariable, String concepto, Date fechaGastoFijo, Date fechaGastoVariable) {
        this.id = id;
        this.saldoInicial = saldoInicial;
        this.ingresoAdicional = ingresoAdicional;
        this.gastoFijo = gastoFijo;
        this.conceptoFijo = conceptoFijo;
        this.gastoVariable = gastoVariable;
        this.concepto = concepto;
        this.fechaGastoFijo = fechaGastoFijo;
        
    }
               
    public cuentasBD(int id, Double gastoFijo, String conceptoFijo, Date fechaGastoFijo){
        this.id = id;
        this.gastoFijo = gastoFijo;
        this.conceptoFijo = conceptoFijo;
        this.fechaGastoFijo = fechaGastoFijo;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }


    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getIngresoAdicional() {
        return ingresoAdicional;
    }

    public void setIngresoAdicional(Double ingresoAdicional) {
        this.ingresoAdicional = ingresoAdicional;
    }

    public Double getGastoFijo() {
        return gastoFijo;
    }

    public void setGastoFijo(Double gastoFijo) {
        this.gastoFijo = gastoFijo;
    }

    public String getConceptoFijo() {
        return conceptoFijo;
    }

    public void setConceptoFijo(String conceptoFijo) {
        this.conceptoFijo = conceptoFijo;
    }

    public Double getGastoVariable() {
        return gastoVariable;
    }

    public void setGastoVariable(Double gastoVariable) {
        this.gastoVariable = gastoVariable;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFechaGastoFijo() {
        return fechaGastoFijo;
    }

    public void setFechaGastoFijo(Date fechaGastoFijo) {
        this.fechaGastoFijo = fechaGastoFijo;
    }

    public Date getFechaGastoVariable() {
        return fechaGastoVariable;
    }

    public void setFechaGastoVariable(Date fechaGastoVariable) {
        this.fechaGastoVariable = fechaGastoVariable;
    }

   
    
}
