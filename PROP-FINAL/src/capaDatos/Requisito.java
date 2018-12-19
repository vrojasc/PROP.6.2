/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

import java.util.ArrayList;


/**
 *
 * @author Victor Rojas
 */
public class Requisito {

    public Requisito() {}
    /**
     * Enumeracio utilitzada per diferenciar els diferents tipus de requisits.
     */
     public enum TipoR {
        pre, co
     }
     /**
      * Variable per guardar el tipo de requisit.
      */
    private TipoR tipoR;
    /**
     * Arraylist on es guarda a quines asignatures afecta aquest requisit.
     */
    private ArrayList<Materia> mats; 
    
/**
 * Constructora de Requisito amb parametres.
 * @param tipoR Tipus de requisit que se li asignara a aquest requisit.
 * @param mats Arraylist amb les materies involucrades en aquest requisit.
 */
    public Requisito(TipoR tipoR, ArrayList<Materia> mats) {
        this.tipoR = tipoR;
        this.mats = mats;
    }
/**
 * Obte el tipo de Requisit
 * @return Retorna el tipoR.
 */
    public TipoR getTipoR() {
        return tipoR;
    }
/**
 * Fixa un valor per la variable tipoR
 * @param tipoR Asignara al tipo requisit de la clase el tipoR.
 */
    public void setTipoR(TipoR tipoR) {
        this.tipoR = tipoR;
    }
/**
 * Obte l'Arraylist mats de la clase Requisit.
 * @return Retorna el arraylist mats.
 */
    public ArrayList<Materia> getMats() {
        return mats;
    }
/**
 * Fixa el arraylist de materias al que li entra com a parametre.
 * @param mats Asignara l'Arraylist de materies el que entra com a parametre.
 */
    public void setMats(ArrayList<Materia> mats) {
        this.mats = mats;
    }

    @Override
    public String toString() {
        return "Requisito{" + "tipoR=" + tipoR + ", mats=" + mats + '}';
    }
    
    
    
    
}
