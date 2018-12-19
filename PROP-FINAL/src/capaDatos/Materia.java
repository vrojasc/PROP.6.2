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
public class Materia {

    /**
     * Constructora de Materia sin parametros.
     */
    public Materia() {
        reqs = new ArrayList<Requisito>();
    }

    /**
     * Enumeracio utilitzada per especificar el tipus despecialitat escollida en cas de ser-hi necessaria.
     */
    public enum Especialitat {
        Computació, EnginyeriaComputadors, EnginyeriaSoftware, SistemesInformació, TecnologiesInformació
    }
    /**
     * Nom de la Materia.
     */
     private String nom;
     /**
      * Siglas de la Materia.
      */
     private String siglas;
     /**
      * Nivell al que pertany la Materia. 
      * Si aquesta es de nivell 2 vol dir que es sol realitzar al tercer i quart semestre.
      */
     private int nivel;
     /**
      * Especialitat de la Materia.
      */
     private Especialitat especialitat; 
     /**
      * Requisits que te la Materia.
      */
     private ArrayList<Requisito> reqs;
/**
 * Obte l'especialitat de la Materia. 
 * @return Retorna l'especialitat de la Materia.
 */
    public Especialitat getEspecialitat() {
        return especialitat;
    }
    /**
     * Obte el nom.
     * @return Retorna el nom de la Materia.
     */
     public String getNom() {
        return nom;
    }
     /**
      * Compara dos Materies retornant si son iguals o no.
      * @param M Altre materia amb la que es vol comparar.
      * @return Retorna true si les dos clases Materia son iguals i false altrament.
      */
     public boolean equals(Materia M){
         return M.getNom().equals(nom) && M.getSiglas().equals(siglas) &&
                 M.getNivel() == nivel && M.getEspecialitat() == especialitat
                 && M.getReqs().equals(reqs);
     }
     
     /**
      * Constructora de Materia amb parametres.
      * @param nom Nom que s'asignara a la nova Materia.
      * @param siglas Siglas que tindra la nova Materia.
      * @param nivel Nivell al que correspondra la nova Materia.
      */
    public Materia(String nom, String siglas, int nivel) {
        this.nom = nom;
        this.siglas = siglas;
        this.nivel = nivel;
        reqs = new ArrayList<Requisito>();
    }    
    
    
   /**
    * Fixa la especialitat. El nivell d'aquesta ha de ser superior a dos.
    * @param especialitat Valor que tindra l'especialitat de la Materia.
    */
    public void setEspecialitat(Especialitat especialitat){
        this.especialitat = especialitat;
    }
    /**
     * Afegeix un requisit al vector de reqs.
     * @param req REquisit que sera afegit.
     */
    public void setRequisito(Requisito req){
        reqs.add(req);
    }
     
    /**
     * Obte las siglas de la Materia.
     * @return Retorna las siglas.
     */
    public String getSiglas(){
        return siglas;
    }
    /**
     * Obte el nivell de la Materia.
     * @return Retorna el nivell.
     */
    public int getNivel(){
        return nivel;
    }
    /**
     * Obte tots els requisits per una Materia.
     * @return Retorna els requisits reqs.
     */
    public ArrayList<Requisito> getReqs(){
        return reqs;
    }

    @Override
    public String toString() {
        return "Materia{" + "nom=" + nom + ", siglas=" + siglas + ", nivel=" + nivel + ", especialitat=" + especialitat + ", reqs=" + reqs + '}';
    }
     
    
}
