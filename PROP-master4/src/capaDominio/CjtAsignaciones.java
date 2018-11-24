/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Victor
 */

public class CjtAsignaciones {

    private Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> cjt_asignaciones; 

   
    

    public CjtAsignaciones(){
        cjt_asignaciones = new HashMap<>();
    }

    
    
    public Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> getCjtA(){
        return cjt_asignaciones;
    }
    
    
    
    public void addelement(FranjaHoraria fh, capaDatos.Aula au, capaDatos.Asignatura a){
        FranjaHoraria fh_aux = new FranjaHoraria(fh.getHoraIni(), fh.getDia());
        for (int i = 0; i < a.getHoraClase(); i++){
            if (!cjt_asignaciones.containsKey(fh_aux)){
                cjt_asignaciones.put(new FranjaHoraria(fh_aux.getHoraIni(), fh_aux.getDia()), new HashMap <>());
            }
            cjt_asignaciones.get(fh_aux).put(au, a);
            fh_aux.seguentHora();
        }
    }
    //pre: la clave fh, no tiene una clave Aula llena
    
    
    public void delelement(FranjaHoraria fh, capaDatos.Aula au, capaDatos.Asignatura a){
       FranjaHoraria fh_aux = new FranjaHoraria(fh.getHoraIni(), fh.getDia());
        for (int i = 0; i < a.getHoraClase(); i++){
            cjt_asignaciones.get(fh_aux).remove(au);
            fh_aux.seguentHora();
      }
    }
    //pre: la clave fh, tiene un map con una aula != null
    
 /*   
     public Map<Integer, ArrayList<Asignacion>> get_asignaciones_ordenadas (){
        Map<Integer, ArrayList<Asignacion>> m = new HashMap<Integer, ArrayList<Asignacion>>();  
        for (int i = 0; i < cjt_asignaciones.size(); i++){
            int val = cjt_asignaciones.get(i).getFranjaHoraria().unificar_values();
            for (int j = 0; j < cjt_asignaciones.get(i).getAsignatura().getHoraClase(); j++){
                if (!m.containsKey(val+j))
                    m.put(val+j, new ArrayList<>());
                m.get(val+j).add(cjt_asignaciones.get(i));      
            }
        }           
        return (HashMap<Integer, ArrayList<Asignacion>>) m;
    }    */
    //pre: el conjunt assignacions ha estat montat
    //post: es retorna un map ordenat amb les clases segons el dia i l'hora
}
