/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

/**
 *
 * @author Alberto Camacho
 */
public class Aula {
    
    /**
 *
 * Identificador de l'aula.
 */
    private String codigo;
/**
 *
 *  Cuantitat de persones que hi caben.
 */
    private int capacidad;
/**
 *
 * Tipus d'aula, perque sera utilitzada, pot ser per laboratori(L), teoria(T) o problemes(P).
 */
    private Asignatura.TipusClase tipusClase;

/**
 * Constructora amb parametres d'entrada.
 * @param codigo Identificador que s'asignara a l'aula.
 * @param capacidad Capacidad que asignara a l'aula.
 * @param tipusClase Per a que sera utilitzada la clase (T,L,P). 
 */
    public Aula(String codigo, int capacidad, Asignatura.TipusClase tipusClase) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.tipusClase = tipusClase;
    }
/**
 * Compara dos clases Aula dient si son iguals o no.
 * @param A Aula amb la qual la clase sera comparada.
 * @return Retorna true si l'Aula A (aula d'entrada com parametre) es igual a la actual clase, fals altrament.
 */
    public boolean equals(Aula A){
        return A.getCodigo().equals(codigo) && A.getCapacidad() == capacidad 
                && A.getTipusClase().equals(tipusClase);
    }
    /**
    * Constructora sense parametres d'entrada.
    */
    public Aula() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Obtenir el codi de l'aula.
     * @return Retorna el codigo identificador de la clase.
     */
    public String getCodigo() {
        return codigo;
    }
/**
 * Fixar un codi per l'aula.
 * @param codigo Cogigo que s'asignara al codigo de la clase.
 */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
/**
 * Obtenir la capacitat de l'aula.
 * @return Retorna la capacitat disponible.
 */
    public int getCapacidad() {
        return capacidad;
    }
/**
 * Fixa una capacitat per l'aula.
 * @param capacidad Sera la capacitat que se li asignara a l'aula.
 */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
/**
 * Obtenir el tipus de clase que es fara a l'aula.
 * @return Retorna el tipus de clase.
 */
    public Asignatura.TipusClase getTipusClase() {
        return tipusClase;
    }
/**
 * Fixa un tipus de clase per l'aula.
 * @param tipusClase Sera el tipus de clase que se li asignara a l'aula.
 */
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
