/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;



/**
 * @author Alberto Camacho
 */
public class Asignatura {

    public Asignatura() {
        
    }

    /**
     * Enumeracio per diferenciar els tipus de clase posibles.
     */
    public enum TipusClase {
        T, L, P
    }
 /**
 *  Materia.
 */
    private Materia mat;
    /**
 *  Grupo al que pertany l'asignatura.
 */
    private int grupo;
    /**
 *  MTipus de clase que es l'asignatura, por ser T, L, P.
 */
    private TipusClase tipusClase;
    /**
 *  Capacitat que necessita l'asignatura per poder realitzarse. Numero d'alumnes que asistiran.
 */
    private int capacidad;
    /**
 *  Hores que dura la clase.
 */
    private int horaClase; 
    
    /**
 * Compara dos Asignatures dient si son iguals o no.
 * @param A Asignatura a comparar si es igual a la clase.
 * @return true si l'asignatura d'entrada es igual a la clase asignatura.
 */
    public boolean equals(Asignatura A){
        return A.getMat().equals(mat) && A.getTipusClase() == tipusClase &&
                A.getGrupo() == grupo && A.getCapacidad() == capacidad && A.getHoraClase() == horaClase;
    }
 /**
 * Constructora de la clase Asignatura.
 * @param mat Materia que s'asignara a l'asignatura.
 * @param tipusClase Tipus de clase que es vol assignar (pot ser T = teoria, L = laboratori, P = problemes)
 */
    public Asignatura(Materia mat, int grupo, TipusClase tipusClase, int capacidad, int horaClase){
        this.mat = mat;
        this.grupo = grupo;
        this.tipusClase = tipusClase;
        this.capacidad = capacidad;
        this.horaClase = horaClase;
    }
   /**
 * @return Retorna la materia de la clase.
 */
    public Materia getMat() {
        return mat;
    }
 /**
 * @param mat Materia mat que es vol asignar.
 */
    public void setMat(Materia mat) {
        this.mat = mat;
    }
 /**
 * @return Retorna el grup de la clase.
 */
    public int getGrupo() {
        return grupo;
    }
/**
 * @param grupo Grupo de la clase que es vol asignar.
 */
    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }
 /**
 * @return Retorna el tipus de clase (pot ser T = teoria, L = laboratori, P = problemes) de l'Asignatura.
 */
    public TipusClase getTipusClase() {
        return tipusClase;
    }
     
    static public TipusClase StringtoTipusclase(String tc){
        if (tc.equals("T")) return TipusClase.T;
        else if (tc.equals("L")) return TipusClase.L;
        else return TipusClase.P;
    }
    
    static public String TipusClasetoString(TipusClase tc){
        if (tc == TipusClase.T) return "T";
        else if (tc == TipusClase.L) return "L";
        else return "P";
    }
/**
 * @param tipusClase Tipo de clase de l'Asignatura que es vol asignar.
 */
    public void setTipusClase(TipusClase tipusClase) {
        this.tipusClase = tipusClase;
    }
 /**
 * @return Retorna la capacitat que necessita l'Aula.
 */
    public int getCapacidad() {
        return capacidad;
    }
/**
 * @param capacidad Capacidad que es vol asignar.
 */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
 /**
 * @return Retorna les hores que dura una Asignatura.
 */
    public int getHoraClase() {
        return horaClase;
    }
/**
 * @param horaClase Numero de hores que dura una asignatura que es vol asignar.
 */
    public void setHoraClase(int horaClase) {
        this.horaClase = horaClase;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "mat=" + mat + ", grupo=" + grupo + ", tipusClase=" + tipusClase + ", capacidad=" + capacidad + ", horaClase=" + horaClase + '}';
    }
    
    
    
}