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
public class Asignatura {

    public Asignatura() {
        
    }

    
     public enum TipusClase {
        T, L, P
    }
    
    private Materia mat;
    private int grupo;
    private TipusClase tipusClase;
    private int capacidad;
    private int horaClase; 
    
    public boolean equals(Asignatura A){
        return A.getMat().equals(mat) && A.getTipusClase() == tipusClase &&
                A.getGrupo() == grupo && A.getCapacidad() == capacidad && A.getHoraClase() == horaClase;
    }
    
    public Asignatura(Materia mat, int grupo, TipusClase tipusClase, int capacidad, int horaClase){
        this.mat = mat;
        this.grupo = grupo;
        this.tipusClase = tipusClase;
        this.capacidad = capacidad;
        this.horaClase = horaClase;
    }

    public Materia getMat() {
        return mat;
    }

    public void setMat(Materia mat) {
        this.mat = mat;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public TipusClase getTipusClase() {
        return tipusClase;
    }

    public void setTipusClase(TipusClase tipusClase) {
        this.tipusClase = tipusClase;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(int horaClase) {
        this.horaClase = horaClase;
    }
    
}
