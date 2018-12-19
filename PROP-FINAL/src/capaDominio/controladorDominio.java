/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import capaDatos.Aula;
import java.io.IOException;
//import capaDominio.FranjaHoraria.Dia;
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
        rest = new capaDominio.Restricciones();
    }
     
     public ArrayList<Asignatura> getAsignaturas(){
         return gestionDatos2.getcjt_asignatures();
     }
     public ArrayList<Aula> getAulas(){
         return gestionDatos2.getcjt_aules();
     }
     /* FUERA
    
 //   private CjtAsignaciones cjtAssigs;
    public class FHaula{
        private FranjaHoraria FH;
        private capaDatos.Aula aula;
        
        public FHaula(FranjaHoraria FH, capaDatos.Aula aula){
            this.FH = FH;
            this.aula = aula;
        }
        public FranjaHoraria getFH(){
            return FH;
        }
        public capaDatos.Aula getAula(){
            return aula;
        }
    };
    
   
    
    private FranjaHoraria.Dia Dia_semana(int dia){
        
        if (dia == 0) return FranjaHoraria.Dia.LUNES;
        else if (dia == 1) return FranjaHoraria.Dia.MARTES;
        else if (dia == 2) return FranjaHoraria.Dia.MIERCOLES;
        else if (dia == 3) return FranjaHoraria.Dia.JUEVES;
        else return FranjaHoraria.Dia.VIERNES;
    }
    private ArrayList<FHaula>  PosiblesFH(capaDatos.Asignatura A){
        //Devuelve el conjunto de Franja horarias junto con las clases a las que podriamos meter la Asignatura A.
        ArrayList<FHaula> ArrayFHaula = new ArrayList<>();
        capaDatos.Aula aula = new capaDatos.Aula();
        int HorasC = A.getHoraClase(); 
        for (int diai = 0; diai < 5;++diai){
            for (int horai = 8; (horai+HorasC) <= 20;){
                FranjaHoraria FH = new FranjaHoraria(horai,horai+HorasC-1, Dia_semana(diai));
                aula = rest.Comprueba_Restricciones(A, FH, CJTA, gestionDatos2.getcjt_aules());
                if (aula != null){//Funcion que dice si podria añadir la asignatura A en la FH.  
                    FHaula fhaula = new FHaula(FH, aula);
                    ArrayFHaula.add(fhaula);
                    // Hay que añadir a que clase ira a parte de la FH. Supongo que creando un struck con FranjaHoraria y Aula ya estara.
                }
                //horai;
            //    horai+=HorasC;
            horai++;
            
            }
        }
        return ArrayFHaula;          
    }
  */
       /**
        * Funcio que genera recursivament una posible combinacio d'asignacions entre aules i asignatures. I les va guradan al CjtAsignaciones de la clase controladorDominio.
        * @param A Arraylist que conte totes les asignatues que s'han d'asignar.
        * @param i Variable per indicar a traves dels nivells de recursivitat quina de les asignatures estem tractan.
        * @return Retorna true si hi existeix una posible combinacio i fals altrament.
        */     
    private boolean Generar_r(ArrayList<capaDatos.Asignatura> A, int i){
        if (i >= A.size()){
            return true; // Todas las asignaturas han sido asignadas.
        }
        else {
            boolean b = false; boolean more_hours = true;
            capaDatos.Asignatura Aux = A.get(i++); // Cojo una asignatura. 
            FranjaHoraria fh_aux = new FranjaHoraria(8, FranjaHoraria.Dia.LUNES);

            do {
                capaDatos.Aula aux_aula = rest.Comprueba_Restricciones(Aux, fh_aux, CJTA, gestionDatos2.getcjt_aules());        
                if (aux_aula != null) {
                    CJTA.addelement(fh_aux, aux_aula, Aux);
                    b = Generar_r(A, i);// hacer recursividad    
                    if (!b){
                        CJTA.delelement(fh_aux, aux_aula, Aux);
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
        if(!Generar_r(A, 0)) System.out.println("no hay horario");
        return CJTA;
    }

    public CjtAsignaciones getCjtA() {
        return CJTA;
    }
    
      public boolean guardar(String nombre, String datos) throws IOException{
        if (CJTA.guardar_horario(nombre)){
            return gestionDatos2.guardar_horario(nombre, datos);
        }
        return false;
    }
    
    public boolean cargar(String nombre) throws IOException {
        if (gestionDatos2.cargar_horario(nombre)){
            return CJTA.cargar_horario(nombre, gestionDatos2.getcjt_asignatures(), gestionDatos2.getcjt_aules());
        }
        return false;
    }
    
    public boolean mod_asignatura(){
        return false;
        
    }
    public boolean mod_aula(){
        return false;
        
    }
    public boolean mod_materia(){
        return false;
        
    }
    public boolean mod_requisits(){
        return false;
        
    }
    
    public FranjaHoraria inttoFranjaHoraria(int fh){
        FranjaHoraria franja = null;
        FranjaHoraria.Dia dia = FranjaHoraria.convert_int_to_Dia(fh/12);
        Integer  hora = fh%12 + 7;
        franja = new FranjaHoraria(hora, dia);
        return franja;
    }
    
    public boolean cambiar_asignacion(capaDatos.Aula aula, capaDatos.Asignatura asignatura, int fh1, int fh2){
        FranjaHoraria f1 = inttoFranjaHoraria(fh1);
        FranjaHoraria f2 = inttoFranjaHoraria(fh2);
        
        System.out.println(f1.getDiaString() + "  " + f1.getHoraIni());
        System.out.println(f2.getDiaString() + "  " + f2.getHoraIni());
        capaDatos.Aula aux_aula = rest.Comprueba_Restricciones(asignatura, f2, CJTA, gestionDatos2.getcjt_aules()); 
        if(aux_aula == null) return false;
        System.out.println("Se ha encontrado aula " + aula.getCodigo());
        CJTA.delelement(f1, aula, asignatura);
        CJTA.addelement(f2, aula, asignatura);
        return true;
    }
}
