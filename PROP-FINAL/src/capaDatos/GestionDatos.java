/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 *
 * @author Sergi Aragall
 */
public class GestionDatos { 
    
    /**
     * Arraylist que conte totes les asignatures posibles per generar l'horari.
     */
    private ArrayList<Asignatura> cjt_asignatures;
    /**
     * Arraylist amb totes les aules disponibles per asignarlis diferents asignatures a cada hora i dia.
     */
    private ArrayList<Aula> cjt_aules;
    
    
    private ArrayList<Materia> cjt_materias;
/**
 * Variable utilitzada per obrir l'arxiu corresponent i carregar l'horari amb el nom desitjat.
 */
    private String datos;
    
    /**
     * Constructora de GestionDatos, encarregada de carregar els arxius que contenen tota la informacio necessaria
     * per generar l'horari. (Asignatures, Materies, Aules i Requisits
     * @param datos Variable d'entrada utilitzada per saber quins arxius s'han de carregar.
     */
    public GestionDatos(String datos) {
        this.datos = datos;
        cargar_Asignatures();
        cargar_Aules();
    }

    public GestionDatos() {}
  
    /**
     * Funcio encarregada de carregar totes las asignatures del arxiu trobat dins la carpeta amb nom igual que la variable "datos".
     * Per tal de carregarles tambe es necessari llegir i carregar els altres arxius adicionals, Materia i Requisits.
     */
    private void cargar_Asignatures(){
        cjt_asignatures = new ArrayList<Asignatura>();
        File prueba = null;
        FileReader fr = null;
        BufferedReader br = null;
        cjt_materias = new ArrayList<>();
        try {
            prueba = new File(datos + "/Materies.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);
            String linea;            
            //Lectura Materias
            while((linea=br.readLine()) != null) {
                    int aux = linea.indexOf("-", 0);
                    int aux2 = linea.indexOf("-", aux+1);
                    int nivel = Integer.parseInt(linea.substring(aux2+1, aux2+2));
                    cjt_materias.add(new Materia(linea.substring(0, aux), //nom
                                      linea.substring(aux+1, aux2), //siglas
                                      nivel));  //nivel
                    if (nivel > 2){
                        String esp = linea.substring(aux2+3);
                        cjt_materias.get(cjt_materias.size() - 1).setEspecialitat(string_to_especialitat(esp)); //especialitat
                    }
            }

            //Lectura Asignaturas
            prueba = new File(datos + "/Assignatures.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);         
            while((linea=br.readLine()) != null) {
                     int aux = linea.indexOf("-", 0);
                     int aux2 = linea.indexOf("-", aux+1);
                     int aux3 = linea.indexOf("-", aux2+3);
                     cjt_asignatures.add(new Asignatura(getMateria(cjt_materias, linea.substring(0, aux)), //Materia
                                                                    Integer.parseInt(linea.substring(aux+1, aux2)), //Grupo 
                                                                    char_to_tipusClase(linea.substring(aux2+1, aux2+2).charAt(0)),  //TipusClase
                                                                    Integer.parseInt(linea.substring(aux2+3, aux3)), //Capacidad
                                                                    Integer.parseInt(linea.substring(aux3+1, aux3+2))));    //horaClase
            }
            
            //Lectura Requisitos
            prueba = new File(datos + "/Requisits.txt");
            fr = new FileReader(prueba);
            br = new BufferedReader(fr);    
            while((linea=br.readLine()) != null) {
                    ArrayList<Materia> mats = new ArrayList<Materia>();
                    int aux = linea.indexOf("(", 0);
                    int aux2 = linea.indexOf("-", aux+1);
                    mats.add(getMateria(cjt_materias, linea.substring(aux+1, aux2-1)));
                    int aux3;
                    while ((aux3 = linea.indexOf("-", aux2+1)) != -1){
                       mats.add(getMateria(cjt_materias, linea.substring(aux2+1, aux3)));
                       aux2 = aux3;
                    }
                    mats.add(getMateria(cjt_materias, linea.substring(aux2+1)));         
                    Requisito req = new Requisito(int_to_tipoR(aux),    //tipoR
                                                  mats);                //Materias
                    for (int i = 0; i < mats.size(); i++){
                        mats.get(i).setRequisito(req);
                    }
            }
            
            
        }catch(Exception e){
                System.out.println("La carpeta no existe");
        }finally{
            try {                    
                if( null != fr ){   
                    fr.close();     
                }                  
            }catch (Exception e2){ 
            }
        }
    }
 
    /**
     * Funcio encarregada de llegir i carregar les aules trobades dins la carpeta amb nom igual que la variable "datos".
     * 
     */
    private void cargar_Aules(){ 
        cjt_aules = new ArrayList<Aula>();
        File prueba = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            prueba = new File( datos + "/Aules.txt");
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
            System.out.println("");
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

    

    
       
    /**
     * Funcio utilitzada per transformar un char a un TipusClase, enumeracio definida previament a Asignatura.
     * @param c Tres posibles valors T, L o P  
     * @return Retorna el corresponent TipusClase per els diferents valors de c.
     */
    private Asignatura.TipusClase char_to_tipusClase(char c){
        if (c == 'T') return Asignatura.TipusClase.T;
        if (c == 'P') return Asignatura.TipusClase.P;
        return Asignatura.TipusClase.L;
    }
 
    /**
     * Funcio utilitzada per transformar uns String a una especialitat.
     * @param esp Pot ser una de les 5 especialitats existents "COMPUTACIO", "ENGINYERIACOMPUTADORS", "ENGINYERIASOFTWARE", "SISTEMESINFORMACIO" o "TECNOLOGIESINFORMACIO".
     * @return Retorna la corresponent Especialitat(enumeracio d'una Materia) per el valor de l'entrada esp.
     */
    private Materia.Especialitat string_to_especialitat(String esp){
        if ("COMPUTACIO".equals(esp)) return Materia.Especialitat.Computació;
        if ("ENGINYERIACOMPUTADORS".equals(esp)) return Materia.Especialitat.EnginyeriaComputadors;
        if ("ENGINYERIASOFTWARE".equals(esp)) return Materia.Especialitat.EnginyeriaSoftware;
        if ("SISTEMESINFORMACIO".equals(esp)) return Materia.Especialitat.SistemesInformació;
        return Materia.Especialitat.TecnologiesInformació;   //"TECNOLOGIESINFORMACIO"
    }

    /**
     * Funcio utilitzada per transformar un int cap a un tipos TipoR, utilitzat per la clase Requisito.
     * @param aux Valor que es vol transformar (si es 3 es igual a prerrequisit, y si no es un correquisit), fa referencia al numero de lletres del requisit, pre = 3 lletres.
     * @return Retorna el valor d'entrada convertit en TipoR.
     */
    private Requisito.TipoR int_to_tipoR(int aux){
        if (aux == 3) return Requisito.TipoR.pre;
        else return Requisito.TipoR.co;
    }
    /**
     * Funcio que retorna una Materia i aquesta es troba dins d'un Arraylist. Busca una Materia, aquesta ha de ser-hi.
     * @param m Arraylist amb Materies.
     * @param siglas Valor de les sigles de la Materia que es vol buscar.
     * @return Retorna la Materia que te com a sigles sigles. Aquesta es unica.
     */
    private Materia getMateria(ArrayList<Materia> m, String siglas){
        for (int i = 0; i < m.size(); i++){
            if (m.get(i).getSiglas().equals(siglas)) return m.get(i);
        }        
        return null;
    }
            
    /**
     * Funcio per obtenir l'Arraylist amb les asignatures.
     * @return Retorna el Arraylist amb totes les asignatures carregades, anomenat cjt_asignatures.
     */
    public ArrayList<Asignatura> getcjt_asignatures(){
        return cjt_asignatures;
    }
/**
 * Funcio per obtemir l'Arraylist amb les Aules
 * @return Retorna el Arraylist amb totes les Aules carregades, anomenat cjt_aules.
 */
    public ArrayList<Aula> getcjt_aules(){
        return cjt_aules;
    }
    
    public ArrayList<Materia> getcjt_materias(){
        return cjt_materias;
    }

    @Override
    public String toString() {
        return "GestionDatos{" + "cjt_aules=" + cjt_aules + '}';
    }
    
    
    public boolean guardar_horario(String name, String datos) throws IOException{
        String destino = "Persistencia/horarios_guardados/Entorno/" + name;
        String origen = "Persistencia/" + datos;
        File fo = new File(origen); 
        File fd = new File(destino);
        if (fo.isDirectory() && !fd.exists()){
            fd.mkdir();
            String[] archivos = fo.list();  
            for (String archivo : archivos){
                File fileo = new File(origen + "/" + archivo);
                File filed = new File(destino + "/" + archivo);
                InputStream in = new FileInputStream(fileo);
                OutputStream out = new FileOutputStream(filed);
                
                byte[] buf = new byte[1024];
                int len;
                
                while ((len = in.read(buf)) > 0){
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
            return true;
        }
        return false;
    }

    public boolean cargar_horario(String name) {
        String ruta = "Persistencia/horarios_guardados/Entorno/" + name;
        
        File file = new File(ruta);         
        if (!file.exists()) return false;
        
        datos = ruta;
        cargar_Asignatures();
        cargar_Aules();

       return true;
    }

    public boolean del_aula(Aula aula){
        return cjt_aules.remove(aula);
    }
    
    public boolean del_materia(Materia mat) {
        return cjt_materias.remove(mat);
    }

    public boolean del_asignatura(Asignatura asignatura) {
        return cjt_asignatures.remove(asignatura);
    }
    
    
}
