/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


/**
 *
 * @author Sergi
 */
public class GestionDatos { 
    
    
    private ArrayList<Asignatura> cjt_asignatures;
    private ArrayList<Aula> cjt_aules;

    private String datos;
    
    public GestionDatos(String datos) {
        this.datos = datos;
        cargar_Asignatures();
        cargar_Aules();
    }
  
    
    private void cargar_Asignatures(){
        cjt_asignatures = new ArrayList<Asignatura>();
        File prueba = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            prueba = new File("ArchivosExternos/" + datos + "/Materies.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);
            ArrayList<Materia> m = new ArrayList<Materia>();
            String linea;            
            //Lectura Materias
            while((linea=br.readLine()) != null) {
                    int aux = linea.indexOf("-", 0);
                    int aux2 = linea.indexOf("-", aux+1);
                    int nivel = Integer.parseInt(linea.substring(aux2+1, aux2+2));
                    m.add(new Materia(linea.substring(0, aux), //nom
                                      linea.substring(aux+1, aux2), //siglas
                                      nivel));  //nivel
                    if (nivel > 2){
                        String esp = linea.substring(aux2+3);
                        m.get(m.size() - 1).setEspecialitat(string_to_especialitat(esp)); //especialitat
                    }
            }

            //Lectura Asignaturas
            prueba = new File("ArchivosExternos/" + datos + "/Assignatures.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);         
            while((linea=br.readLine()) != null) {
                     int aux = linea.indexOf("-", 0);
                     int aux2 = linea.indexOf("-", aux+1);
                     int aux3 = linea.indexOf("-", aux2+3);
                     cjt_asignatures.add(new Asignatura(getMateria(m, linea.substring(0, aux)), //Materia
                                                                    Integer.parseInt(linea.substring(aux+1, aux2)), //Grupo 
                                                                    char_to_tipusClase(linea.substring(aux2+1, aux2+2).charAt(0)),  //TipusClase
                                                                    Integer.parseInt(linea.substring(aux2+3, aux3)), //Capacidad
                                                                    Integer.parseInt(linea.substring(aux3+1, aux3+2))));    //horaClase
            }
            
            //Lectura Requisitos
            prueba = new File("ArchivosExternos/" + datos + "/Requisits.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);    
            while((linea=br.readLine()) != null) {
                    ArrayList<Materia> mats = new ArrayList<Materia>();
                    int aux = linea.indexOf("(", 0);
                    int aux2 = linea.indexOf("-", aux+1);
                    mats.add(getMateria(m, linea.substring(aux+1, aux2-1)));
                    int aux3;
                    while ((aux3 = linea.indexOf("-", aux2+1)) != -1){
                       mats.add(getMateria(m, linea.substring(aux2+1, aux3)));
                       aux2 = aux3;
                    }
                    mats.add(getMateria(m, linea.substring(aux2+1)));         
                    Requisito req = new Requisito(int_to_tipoR(aux),    //tipoR
                                                  mats);                //Materias
                    for (int i = 0; i < mats.size(); i++){
                        mats.get(i).setRequisito(req);
                    }
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
    }
    //carga Asignatures.txt y crea todas las Asignaturas, Materias y requisitos
    
    
    private void cargar_Aules(){ 
        cjt_aules = new ArrayList<Aula>();
        File prueba = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            prueba = new File("ArchivosExternos/" + datos + "/Aules.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);
            
            // Lectura de las aulas
            String linea;
            while((linea=br.readLine()) != null){
                int aux = linea.indexOf("-", 0);
                int aux2 = linea.indexOf("-", aux+1);
                cjt_aules.add(new Aula(linea.substring(0, aux),     //Codigo
                                       Integer.parseInt(linea.substring(aux+1, aux2)), //capacidad 
                                       char_to_tipusClase(linea.substring(aux2+1).charAt(0)))); //tipusClase
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
    }
    //carga Aules.txt y crea todas las Aulas
    

    
       
    
    private Asignatura.TipusClase char_to_tipusClase(char c){
        if (c == 'T') return Asignatura.TipusClase.T;
        if (c == 'P') return Asignatura.TipusClase.P;
        return Asignatura.TipusClase.L;
    }
    //pre: c = T,P o L
    //post: transforma el char c a tipusClase
    
    
    private Materia.Especialitat string_to_especialitat(String esp){
        if ("COMPUTACIO".equals(esp)) return Materia.Especialitat.Computació;
        if ("ENGINYERIACOMPUTADORS".equals(esp)) return Materia.Especialitat.EnginyeriaComputadors;
        if ("ENGINYERIASOFTWARE".equals(esp)) return Materia.Especialitat.EnginyeriaSoftware;
        if ("SISTEMESINFORMACIO".equals(esp)) return Materia.Especialitat.SistemesInformació;
        return Materia.Especialitat.TecnologiesInformació;   //"TECNOLOGIESINFORMACIO"
    }
    //pre: esp = "COMPUTACIO", "ENGINYERIACOMPUTADORS", "ENGINYERIASOFTWARE", "SISTEMESINFORMACIO" o "TECNOLOGIESINFORMACIO"
    //post: transforma el string esp a Especialitat
    
    private Requisito.TipoR int_to_tipoR(int aux){
        if (aux == 3) return Requisito.TipoR.pre;
        else return Requisito.TipoR.co;
    }
    //pre: aux = 3 o 2, hace referencia al número de letras, es decir pre 3 y co 2
    //post: devuelve el tipoR especificado
    
    private Materia getMateria(ArrayList<Materia> m, String siglas){
        for (int i = 0; i < m.size(); i++){
            if (m.get(i).getSiglas().equals(siglas)) return m.get(i);
        }        
        return null;
    }
            
    //pre: existeix una materia en m amb les sigles passades per parametre
    //post: retorna la materia que te com a clau primaria siglas
    
    public ArrayList<Asignatura> getcjt_asignatures(){
        return cjt_asignatures;
    }

    public ArrayList<Aula> getcjt_aules(){
        return cjt_aules;
    }

    @Override
    public String toString() {
        return "GestionDatos{" + "cjt_aules=" + cjt_aules + '}';
    }
    
    
}
