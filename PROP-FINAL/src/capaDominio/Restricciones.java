/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Victor Rojas
 */
public class Restricciones {

    private ArrayList<ArrayList<String>> suavizar;
    
    private boolean b1,b2,b3;
    private ArrayList<Integer> punteros;
    
    
    
    /**
     * Constructora de la clase Restricciones sense parametres.
     */
    public Restricciones(){
       
    }
    
    public void setRestricciones (ArrayList<ArrayList<String>> suavizar){
        this.suavizar = suavizar;
    }
    
    private void comprueba_suavizar_restricciones (capaDatos.Asignatura a){
        b1 = false; b2 = false; b3 = false;
        punteros = new ArrayList<>();
        if (!suavizar.isEmpty()){
            for (int i = 0; i < 3; i++){
                boolean fin = false;
                for (int j = 0; j < suavizar.get(i).size() && !fin; j++){
                    if (a.getMat().getSiglas() == suavizar.get(i).get(j)){
                        if (i == 0) b1 = true;
                        else if (i == 1) b2 = true;
                        else if (i == 2) b3 = true;
                        fin = true;
                    } 
                }
            }
            for (int i = 0; i < suavizar.get(3).size(); i++){
                if (a.getMat().getSiglas() == suavizar.get(3).get(i)){
                    if (i%2 == 0) punteros.add(i+1);
                    else punteros.add(i-1);
                }
            }
        }
    }
    
    
    //RESTRICCIONES NO SUAVIZABLES
    /**
     * Comproba si l'asignatura a1 i l'aula a2 tenen el mateix tipus de clase(T, L, P).
     * @param a1 Asignatura que es vol comparar el tipus de clase.
     * @param a2 Aula que es vol comparar el tipus de clase.
     * @return Retorna true si tant a1 com a2 tenen el mateix tipus de clase, i fals altrament.
     */
    private boolean compartenTipo(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getTipusClase()==a2.getTipusClase();
    }
    /**
     * Comprova que l'asignatura a1 es pugui realitzar a l'aula a2 degut a les capacitats de cadascuna de les clases.
     * @param a1 Asignatura que es vol comparar la seva capacitat.
     * @param a2 Aula que es vol comparar la seva capacitat.
     * @return Retorna true si es pot fer l'asignatura a1 a l'aula a2 comprovant les capacitats, fals altrament.
     */
    private boolean capacidadValida(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getCapacidad()<=a2.getCapacidad();
    }
    
    
    //RESTRICCIONES SUAVIZABLES
    /**
     * Comprova si l'asingatura a1 te nivell diferent a l'asignatura a2.
     * @param a1 Asignatura que sera comparada.
     * @param a2 Segona Asignatura que sera comparada.
     * @return Retorna true si a1 i a2 no son del mateix nivell i fals altrament.
     */
    private boolean tienenDistintoNivel(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getNivel() != a2.getMat().getNivel() || a1.getMat()== a2.getMat();
    }
    /**
     * Comprova si l'asignatura a1 i l'asignatura a2 tenen confliques per els correquisits.
     * @param a1 Primera Asignatura que es comparara.
     * @param a2 Segona Asignatura que es comparara amb la primera.
     * @return Retorna false si no son correquisit i fals altrament.
     */
    private boolean esCorequisito(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        boolean esCoreq = false;
        ArrayList<capaDatos.Requisito> auxReq = a1.getMat().getReqs();
        ArrayList<capaDatos.Materia> auxMat = null;
        for(int i=0;i<auxReq.size(); i++)
            if (auxReq.get(i).getTipoR()==capaDatos.Requisito.TipoR.co) auxMat = auxReq.get(i).getMats();
        if(auxMat == null) return false;
        for(int i=0; i<auxMat.size() && !esCoreq; i++){
            if (auxMat.get(i).equals(a2.getMat())) esCoreq = true;
        }
        return esCoreq;
    }
    /**
     * Comprova que dos asignatures, a1 i a2 no pertanyin al mateix grup
     * @param a1 Primera asignatura que sera comparada.
     * @param a2 Segona asignatura que sera comparada amb la primera.
     * @return Retorna true si son del mateix nivell i fals altrament.
     */
    private boolean pertMismoGrupo(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getSiglas() == a2.getMat().getSiglas() && 
                (((int)a1.getGrupo()/10 == (int)a2.getGrupo()/10) ||  (a1.getGrupo()%10==0 && a2.getGrupo()%10==0) )&&
                (a1.getGrupo()%10==a2.getGrupo()%10 || 
                a1.getGrupo()%10!=a2.getGrupo()%10 &&(a1.getGrupo()%10==0 || a2.getGrupo()%10==0));
    }
    
    
    private boolean puedenIrJuntas(capaDatos.Asignatura a2){
        for (int i = 0; i < punteros.size(); i++){
            if (a2.getMat().getSiglas() == suavizar.get(3).get(punteros.get(i))) return false;
        }
        return true;
    }
        
    
    //-----------------------------------------
    private boolean rest_asignatura_fh(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return ((b1 || tienenDistintoNivel(a1, a2)) &&
                (b2 || (!esCorequisito(a1, a2) && !esCorequisito(a2, a1))) && 
                (b3 || !pertMismoGrupo(a1, a2)) &&
                        puedenIrJuntas(a2));
    }
     
    /**
     * Obte una posible aula a la que podria anar l'asignatura a la franja horaria tenint en compte les asignatures que ja es fan a aquella franja horaria i les aules disponibles.
     * @param asig Asignatura a la que es vol asignar una aula.
     * @param franH Franja horaria a la que es vol fer l'asignacio.
     * @param cjtA CjtAsignaicons fetes fins el moment, per comprobar restriccions entre asignatures que es facin a l'hora.
     * @param aulas Aules disponibles que hi hauran per la franja horaria franH.
     * @return Retorna una aula a la que podria realitzarse l'asignatura asig, i en cas de no haver-hi cap disponible retornar null.
     */
    //------------------------------------------------------------
    public capaDatos.Aula Comprueba_Restricciones(capaDatos.Asignatura asig, FranjaHoraria franH, CjtAsignaciones cjtA, ArrayList<capaDatos.Aula> aulas){
        Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> cjtAsig = cjtA.getCjtA();
        boolean more_hours; int fh_validas = 0; 
        ArrayList<capaDatos.Aula> aulaAux1 = (ArrayList<capaDatos.Aula>)aulas.clone();
        
        int j = 0;
        while (j < aulaAux1.size()){
            if (!compartenTipo(asig, aulaAux1.get(j)) || !capacidadValida(asig, aulaAux1.get(j))) aulaAux1.remove(j);
            else j++;
        }
        
        comprueba_suavizar_restricciones(asig);
        ArrayList<capaDatos.Aula> aulaAux = (ArrayList<capaDatos.Aula>)aulaAux1.clone();
        do {
            if (cjtAsig.containsKey(franH)){
                boolean correct = true;
                Iterator e = cjtAsig.get(franH).values().iterator();
                while (e.hasNext() && correct){
                    capaDatos.Asignatura a = (capaDatos.Asignatura) e.next();
                    if (!rest_asignatura_fh(asig, a)) correct = false;
                }  
                if (correct){
                    Iterator e2 = cjtAsig.get(franH).keySet().iterator();
                    while (e2.hasNext()){  
                        capaDatos.Aula key = (capaDatos.Aula) e2.next();
                        if (aulaAux.contains(key)) aulaAux.remove(key);
                    }

                    if (!aulaAux.isEmpty()) fh_validas++;
                }
                if (!correct || aulaAux.isEmpty()){
                    fh_validas = 0;
                    aulaAux = (ArrayList<capaDatos.Aula>)aulaAux1.clone();
                }
            } else fh_validas++;
           
            more_hours = franH.seguentHora();
            if (franH.getHoraIni() + (asig.getHoraClase() - fh_validas) > 20) {
                if (franH.getDia() == FranjaHoraria.Dia.VIERNES){
                        franH.set_values(59);
                        return null;
                }
                franH.set_values(((franH.unificar_values()/12)+1) * 12);
                fh_validas = 0;
                aulaAux = (ArrayList<capaDatos.Aula>)aulaAux1.clone();
            }        
        } while (more_hours && fh_validas != asig.getHoraClase());
        
        if (fh_validas == asig.getHoraClase()){
            franH.set_values(franH.unificar_values()-asig.getHoraClase());
            return aulaAux.get(0);
        }
        
        franH.set_values(59);
        return null;
    }
}
