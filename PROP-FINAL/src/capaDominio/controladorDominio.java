/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import capaDatos.Aula;
import capaDatos.Materia;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Alberto Camacho
 */
public class controladorDominio {
/**
 * GestionDatos amb totes les asignatures i les aules carregades.
 */
    private capaDatos.GestionDatos gestionDatos2;
    /*
    Clase Restricciones que comprobara les restriccions a l'hora d'asignar aules a asignatures.
    */
    private Restricciones rest;
    /*
    * Conjunt d'asignacions on es guardaran totes les asignacions que es van creant durant l'execucio per tal de guardar l'horari.
    */
    private capaDominio.CjtAsignaciones CJTA;
        
    
    
    /**
     * Constructora amb un parametre d'entrada.
     * @param datos Correspon al nom del arxiu que es vol carregar.
     */
     public controladorDominio(String datos) {
        gestionDatos2 = new capaDatos.GestionDatos("Persistencia/" + datos);
        CJTA = new capaDominio.CjtAsignaciones();
        rest = new capaDominio.Restricciones(gestionDatos2.getcjt_asignatures());
    }
     
     public ArrayList<Asignatura> getAsignaturas(){
         return gestionDatos2.getcjt_asignatures();
     }
     public ArrayList<Aula> getAulas(){
         return gestionDatos2.getcjt_aules();
     }

     public void setRestricciones(ArrayList<ArrayList<String>> suavizar){
         rest.setRestricciones(suavizar);
     }
     //pre: suavizar es un arrayList de tamaño 4, con las materias de los 4 tipos a las que se anula la restriccion
     
     
       /**
        * Funcio que genera recursivament una posible combinacio d'asignacions entre aules i asignatures. I les va guradan al CjtAsignaciones de la clase controladorDominio.
        * @param A Arraylist que conte totes les asignatues que s'han d'asignar.
        * @param i Variable per indicar a traves dels nivells de recursivitat quina de les asignatures estem tractan.
        * @return Retorna true si hi existeix una posible combinacio i fals altrament.
        */     
    private boolean Generar_r(int i){
        if (i >= gestionDatos2.getcjt_asignatures().size()){
            return true; // Todas las asignaturas han sido asignadas.
        }
        else {
            boolean b = false; boolean more_hours = true;
            capaDatos.Asignatura Aux = gestionDatos2.getcjt_asignatures().get(i); 
            FranjaHoraria fh_aux = new FranjaHoraria(8, FranjaHoraria.Dia.LUNES);

            do {
                capaDatos.Aula aux_aula = rest.Comprueba_Restricciones(i, fh_aux, CJTA, gestionDatos2.getcjt_aules());        
                if (aux_aula != null) {
                    CJTA.addelement(fh_aux, aux_aula, Aux);
                    b = Generar_r(i+1);// hacer recursividad    
                    if (!b){
                        CJTA.delelement(fh_aux, aux_aula, Aux);
                        rest.desmarcar_fh(i, fh_aux);
                        more_hours = fh_aux.seguentHora();
                    }  
                }                     
            } while (!b && more_hours);
            
            return b;   
        }
    }
    
    /**
     * Funcio encarregada de cridar a la funcio recursiva de creacio de l'horari.
     * @return Retorna un conjunt d'asignacions no vuit a no ser que no hi hagi combinacio posible.
     */
    public capaDominio.CjtAsignaciones Generar(){
        ArrayList<capaDatos.Asignatura> A = gestionDatos2.getcjt_asignatures();
        if(!Generar_r(0)) System.out.println("no hay horario");
        return CJTA;
    }
    
    /**
     * Funcion que retorna el conjunt d'asignacions que componen l'horari.
     * @return Retorna el conjunt d'asignacions
     */
    public CjtAsignaciones getCjtA() {
        return CJTA;
    }
    
    /**
     * Funcio per guardar l'horari una vegada ha sigut generat. 
     * @param nombre Nom dels arxius/carpetes en els que sera guardat l'horari.
     * @param datos Actual carpeta con todo el entorno guardado.
     * @return Retorna false si ja existeix l'horari guardat, y  true si s'ha pogut guardar. 
     * @throws IOException 
     */
      public boolean guardar(String nombre, String datos) throws IOException{
        if (CJTA.guardar_horario(nombre)){
            return gestionDatos2.guardar_horario(nombre, datos);
        }
        return false;
    }
    /**
     * Funcio per cargar l'horari previament guardat.
     * @param nombre Nom de la carpeta on esta l'horari guardat.
     * @return Retorna false si no existeix l'horari que es vol carregar, y retorna true si s'ha pogut carregar.
     * @throws IOException 
     */
    public boolean cargar(String nombre) throws IOException {
        if (gestionDatos2.cargar_horario(nombre)){
            return CJTA.cargar_horario(nombre, gestionDatos2.getcjt_asignatures(), gestionDatos2.getcjt_aules());
        }
        return false;
    }
    
    private Asignatura StringstoAsignatura(String tipusClase, String grupo, String capacidad, String horaclase, Materia mat){
        Asignatura asignatura = null;
        return asignatura;
    }
    
    
    
    // DESPUES DE ELIMINAR HABRIA QUE VOLVER A CARGAR.
    public boolean eliminar_asignatura(Asignatura asignatura){
        return gestionDatos2.del_asignatura(asignatura);
    }
    public boolean eliminar_aula(Aula aula){
        return gestionDatos2.del_aula(aula);
    }
    public boolean eliminar_materia(Materia mat){
        return gestionDatos2.del_materia(mat);
    }
  
    
    
    /**
     * Funcio per transformar un int en la clase FranjaHoraria.
     * @param fh El int amb la corresponent FranjaHoraria.
     * @return Retorna la FranjaHoraria.
     */
    public FranjaHoraria inttoFranjaHoraria(int fh){
        FranjaHoraria franja = null;
        FranjaHoraria.Dia dia = FranjaHoraria.convert_int_to_Dia(fh/12);
        Integer  hora = fh%12 + 7;
        franja = new FranjaHoraria(hora, dia);
        return franja;
    }
    
    /**
     * Cambia una asignatura d'asignacio. Colocantla a la nova FranjaHoraria seleccinada.
     * @param aula Aula en la que esta asignada la Asignatura que sea desea cambiar.
     * @param asignatura Asignatura que se quiere cambiar.
     * @param fh1 FranjaHoraria en la que esta actualemente asignada l'asignatura.
     * @param fh2 FranjaHoraria en la que se quiere crear la nueva asignacion.
     * @return Retorna true si s'ha cambiat correctament l'asingacio i false altrament. 
     */
    public boolean cambiar_asignacion(capaDatos.Aula aula, capaDatos.Asignatura asignatura, int fh1, int fh2){
     /*   FranjaHoraria f1 = inttoFranjaHoraria(fh1);
        FranjaHoraria f2 = inttoFranjaHoraria(fh2);
        
        System.out.println(f1.getDiaString() + "  " + f1.getHoraIni());
        System.out.println(f2.getDiaString() + "  " + f2.getHoraIni());
        capaDatos.Aula aux_aula = rest.Comprueba_Restricciones(asignatura, f2, CJTA, gestionDatos2.getcjt_aules()); 
        if(aux_aula == null) return false;
        System.out.println("Se ha encontrado aula " + aula.getCodigo());
        CJTA.delelement(f1, aula, asignatura);
        CJTA.addelement(f2, aula, asignatura);*/
        return true;
    }
}
