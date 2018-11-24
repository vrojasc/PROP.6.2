/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

/**
 *
 * @author Sergi
 */
public class Aula {
    
    private String codigo;
    private int capacidad;
    private Asignatura.TipusClase tipusClase;

    public Aula(String codigo, int capacidad, Asignatura.TipusClase tipusClase) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.tipusClase = tipusClase;
    }
    
    public Aula(){}
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Asignatura.TipusClase getTipusClase() {
        return tipusClase;
    }

    public void setTipusClase(Asignatura.TipusClase tipusClase) {
        this.tipusClase = tipusClase;
    }

    @Override
    public String toString() {
        return "Aula{" + "codigo=" + codigo + ", capacidad=" + capacidad + ", tipusClase=" + tipusClase + '}';
    }
    
    
    @Override
    public boolean equals(Object o){
        Aula a = (Aula) o;
        return a.codigo == codigo;
    } 
        
    @Override
    public int hashCode() {
        return 0;
    }
    
    
}
