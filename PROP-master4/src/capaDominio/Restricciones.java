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
 * @author Victor
 */
public class Restricciones {
  //  private ArrayList<Integer> conts;

    
    public Restricciones(){
        
    }
    
    public boolean compartenTipo(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getTipusClase()==a2.getTipusClase();
    }
    
    public boolean capacidadValida(capaDatos.Asignatura a1, capaDatos.Aula a2){
        return a1.getCapacidad()<=a2.getCapacidad();
    }
    
    public boolean tienenDistintoNivel(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getNivel() != a2.getMat().getNivel() || a1.getMat()== a2.getMat();
    }
    
    public boolean esCorequisito(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        boolean esCoreq = false;
        ArrayList<capaDatos.Requisito> auxReq = a1.getMat().getReqs();
        ArrayList<capaDatos.Materia> auxMat = null;
        for(int i=0;i<auxReq.size(); i++)
            //No se si esto esta bien, comprobar!!
            if (auxReq.get(i).getTipoR()==capaDatos.Requisito.TipoR.co) auxMat = auxReq.get(i).getMats();
        if(auxMat == null) return false;
        for(int i=0; i<auxMat.size() && !esCoreq; i++){
            if (auxMat.get(i).equals(a2.getMat())) esCoreq = true;
        }
        return esCoreq;
    }
    
    public boolean pertMismoGrupo(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return a1.getMat().getSiglas() == a2.getMat().getSiglas() && 
                (((int)a1.getGrupo()/10 == (int)a2.getGrupo()/10) ||  (a1.getGrupo()%10==0 && a2.getGrupo()%10==0) )&&
                (a1.getGrupo()%10==a2.getGrupo()%10 || 
                a1.getGrupo()%10!=a2.getGrupo()%10 &&(a1.getGrupo()%10==0 || a2.getGrupo()%10==0));
    }
    
    private boolean rest_asignatura_fh(capaDatos.Asignatura a1, capaDatos.Asignatura a2){
        return (tienenDistintoNivel(a1, a2) &&
                        !esCorequisito(a1, a2) && 
                        !esCorequisito(a2, a1) &&
                        !pertMismoGrupo(a1, a2));
    }
    
    
    
    
        
    public capaDatos.Aula Comprueba_Restricciones(capaDatos.Asignatura asig, FranjaHoraria franH, CjtAsignaciones cjtA, ArrayList<capaDatos.Aula> aulas){
        Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> cjtAsig = cjtA.getCjtA();
        boolean more_hours; int fh_validas = 0; 
        ArrayList<capaDatos.Aula> aulaAux1 = (ArrayList<capaDatos.Aula>)aulas.clone();
        
        int j = 0;
        while (j < aulaAux1.size()){
            if (!compartenTipo(asig, aulaAux1.get(j)) || !capacidadValida(asig, aulaAux1.get(j))) aulaAux1.remove(j);
            else j++;
        }
        
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
