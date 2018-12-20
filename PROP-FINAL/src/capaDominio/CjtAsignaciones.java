/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import static capaDatos.Asignatura.StringtoTipusclase;
import static capaDatos.Asignatura.TipusClasetoString;
import capaDatos.Aula;
import static capaDominio.FranjaHoraria.StringtoDia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static java.util.Spliterators.iterator;

/**
 *
 * @author Sergi Aragall
 */
public class CjtAsignaciones {
/**
 * Arraylist on es guarda totes les asignacions realitzades. Creades per la clase controladorDominio, per tal de construir l'horari.
 */
    private Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> cjt_asignaciones;  
/**
 * Constructora sense parametres de la clase CjtAsignacion.
 */
    public CjtAsignaciones(){
        cjt_asignaciones = new HashMap<>();
    }
    
    /**
     * Obte l'arraylist d'asignacions de la clase CjtAsignacions.
     * @return Retorna l'arraylist d'asignacions.
     */
    public Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> getCjtA(){
        return cjt_asignaciones;
    }
    /**
     * Afegeix un element, en aquest cas una asignacio, a l'arraylist de la clase CjtAsignacions.
     * @param A Asignacion que sera afegida al arraylist d'asignacions.
     */
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
    /**
     * Elimina l'ultim element de l'arraylist d'asignacions de la clase CjtAsignacions.
     */
    public void delelement(FranjaHoraria fh, capaDatos.Aula au, capaDatos.Asignatura a){
       FranjaHoraria fh_aux = new FranjaHoraria(fh.getHoraIni(), fh.getDia());
        for (int i = 0; i < a.getHoraClase(); i++){
            cjt_asignaciones.get(fh_aux).remove(au);
            fh_aux.seguentHora();
      }
    }    

 /**
     *
     * @param name
     * @return
     * @throws IOException
     */     
     public boolean guardar_horario(String name) throws IOException{
         String ruta = "Persistencia/horarios_guardados/Asignaciones/" + name + ".txt";
         File file = new File(ruta);
         BufferedWriter bw = null;
         
         if (file.exists())
         {
             return false; 
         }
         else
         {
            bw = new BufferedWriter(new FileWriter(ruta,false));
             bw.write(name); // nombre del archivo
             bw.newLine();
             String siglas, grupo, tipoclase, codigo, dia, horaini;
             
             //for (Asignacion asig : cjt_asignaciones){
             
             for ( Map.Entry<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> entry : cjt_asignaciones.entrySet()){
                 
                 FranjaHoraria key = entry.getKey();
                 Map<capaDatos.Aula, capaDatos.Asignatura> m_aux = entry.getValue();
                 
                 for (Map.Entry<capaDatos.Aula, capaDatos.Asignatura> entry2 : m_aux.entrySet()){
                     capaDatos.Aula keyaula = entry2.getKey();
                     capaDatos.Asignatura keyasig = entry2.getValue();
                     
                    siglas = keyasig.getMat().getSiglas();
                    grupo = Integer.toString(keyasig.getGrupo());
                    tipoclase = TipusClasetoString(keyasig.getTipusClase());
                    codigo = keyaula.getCodigo();
                    dia = key.getDiaString();
                    horaini = Integer.toString(key.getHoraIni());

                    bw.write(siglas);bw.newLine();                         
                    bw.write(grupo);bw.newLine();
                    bw.write(tipoclase);bw.newLine();
                    bw.write(codigo);bw.newLine();
                    bw.write(dia);bw.newLine();
                    bw.write(horaini);bw.newLine();
                 }
               //  bw.write("!"+asig);bw.newLine();
             }
             
             bw.close();
             return true;
         }
     }
     
     
     
     public void crear(String siglas, String grupo, String tipoclase, String codigo, String dia, String horaini, ArrayList<Asignatura> asignaturas, ArrayList<Aula> aulas){
         //Asignacion aux = new Asignacion();
         capaDatos.Asignatura aux_asignatura = null;
         capaDatos.Aula aux_aula = null;
         boolean found = false;
         for(Asignatura a : asignaturas){
             if (a.getMat().getSiglas().equals(siglas) && Integer.toString(a.getGrupo()).equals(grupo) && a.getTipusClase() == StringtoTipusclase(tipoclase)){
                 aux_asignatura = a;
             }
         }
         for(Aula a : aulas){
             if (a.getCodigo().equals(codigo)){
                 aux_aula = a;
             }
         }
         FranjaHoraria fh = new FranjaHoraria(Integer.parseInt(horaini), StringtoDia(dia));
         if (!cjt_asignaciones.containsKey(fh)) cjt_asignaciones.put(fh, new HashMap<>());
         cjt_asignaciones.get(fh).put(aux_aula, aux_asignatura);
         
     }
        // return aux;
     
     
     public boolean cargar_horario(String name, ArrayList<Asignatura> asignaturas, ArrayList<Aula> aulas) throws IOException{
         String ruta = "Persistencia/horarios_guardados/Asignaciones/" + name + ".txt";
         File file = new File(ruta);         
         if (!file.exists()) return false;
         
         //Map<FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> aux = new HashMap<>();
         cjt_asignaciones = new HashMap<>();
         BufferedReader br = new BufferedReader(new FileReader(ruta));
         
         String linia = br.readLine();
         String siglas, grupo, codigo, dia, horaini, tipoclase;
         while ((linia = br.readLine()) != null){
             siglas = linia;    linia = br.readLine();
             grupo = linia;     linia = br.readLine();
             tipoclase = linia; linia = br.readLine();
             codigo = linia;    linia = br.readLine();
             dia = linia;       linia = br.readLine();
             horaini = linia;
             crear(siglas, grupo, tipoclase, codigo, dia, horaini, asignaturas, aulas);
          //   aux.add(crear(siglas, grupo, tipoclase, codigo, dia, horaini, asignaturas, aulas));
             
         }
        // cjt_asignaciones = aux;
         return true;
     }
     
     
     // Eliminar una instancia sabiendo solo la franjahoraria y la asignatura
     public boolean eliminar_asignatura(Asignatura asignatura, FranjaHoraria fh){
         Aula aux_aula = null;
         for ( Map.Entry<capaDatos.Aula, capaDatos.Asignatura> entry : cjt_asignaciones.get(fh).entrySet()){
                 if (entry.getValue().equals(asignatura)){
                     aux_aula = entry.getKey();
                     delelement(fh, aux_aula, asignatura);
                     return true;
                 }
        }
         return false;
     }
}