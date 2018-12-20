/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package generadorhorario;

import capaDominio.FranjaHoraria;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Sergi Aragall
 */
public class GeneradorHorario {
//Métodes d'aquesta clase han de ser static, ja que no hi ha cap instancia generada (de fet, tot amb una instancia ho podriem tractar aixi)
    
    /**
     * Main del programa. Juntament amb el menu de l'aplicacio. Hi han tres opcions Generar o Cargar archivo o salir.
     * Quan es genera un horari, aquest s'imprimeix per pantalla.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        boolean salir=false;
        capaDominio.controladorDominio control = null;
        while(!salir){
            Scanner sc = new Scanner(System.in);
            System.out.println("\nGENERADOR DE HORARIOS");
            System.out.println("1-Generar horario");
            System.out.println("2-Cargar horario");
            System.out.println("3-Cambiar asignatura de hora (Se ha tenido que generar y/o cargar un horario)");
            System.out.println("4-Modficar horario (Se ha tenido que generar y/o cargar un horario)");
            System.out.println("5-Salir");
            System.out.println("¿Que desea hacer?");
            int opcion = sc.nextInt();
            switch (opcion){
                case 1: System.out.println("Que asignaturas desea leer");
                        System.out.println("1-Ejemplo 1");
                        System.out.println("2-Ejemplo 2");
                        System.out.println("3-Ejemplo 3");
                        int asig = sc.nextInt();
                        sc.nextLine();//Limpiar buffer
                        if(asig>0 && asig<4){
                            control = new capaDominio.controladorDominio("ejemplo"+asig);
                            mostrar_horario_por_pantalla(control.Generar());
                            System.out.println("¿Desea guardar el horario generado?(S/N)");
                            char op = sc.next().charAt(0);
                            if(op=='S' || op=='s'){
                                //guardar_horario(control.getCjtA());
                                System.out.println("Que nombre desea poner al horario?");
                                String nombre = sc.next();
                                control.guardar(nombre, "ejemplo"+asig);
                            }
                        }
                        break;
                case 2: System.out.println("Que horario desea cargar? Hay los siguientes: (Escribir sin el .txt)");
                        String ruta = "Persistencia/horarios_guardados/Asignaciones/";
                        File carpetas = new File(ruta);
                        if (carpetas.exists()){
                          String[] archivos = carpetas.list();  
                             for (String c : archivos){
                                System.out.println(c);
                            }
                        }
                        String nombre = sc.next();
                        //System.out.println("Que escenario carga?");
                        //int asig2 = sc.nextInt();
                        control = new capaDominio.controladorDominio("ejemplo"+1);
                        //control.Generar();
                        if (!control.cargar(nombre)) break;
                        mostrar_horario_por_pantalla(control.getCjtA());
                        
                        break;
                case 3: int fh1, fh2;
                        System.out.println("Escribe el fh1:");
                        fh1 = sc.nextInt();
                        System.out.println("Escribe el fh2:");
                        fh2 = sc.nextInt();
                        control.cambiar_asignacion(control.getAulas().get(0), control.getAsignaturas().get(0) , fh1, fh2);
                        mostrar_horario_por_pantalla(control.getCjtA());
                case 4: System.out.println("1-Eliminar");
                        System.out.println("2-Crear");
                        int aux = sc.nextInt();
                        if (aux == 1){
                            System.out.println("1-Asignatura");
                            System.out.println("2-Aula");
                            System.out.println("3-Materia");
                            int aux2 = sc.nextInt();
                            if (aux2 == 1){
                                System.out.println("Escribir informacion de asignatura: tipusClase, grupo, siglas.");
                                String tipusclase, grupo, siglas, codigo;
                                int fh;
                                tipusclase = sc.next();
                                grupo = sc.next();
                                siglas = sc.next();
                                System.out.println("Escribir informacion de la aula en la que estaba: codigo");
                                codigo = sc.next();
                                System.out.println("Escribir informacion de la Franja Horaria en la que estaba: hora ini (en int)");
                                fh = sc.nextInt();
                                control.eliminar_asignatura(control.StringtoAsignatura(tipusclase, grupo, null, siglas), control.StringtoAula(codigo), control.inttoFranjaHoraria(fh));
                                mostrar_horario_por_pantalla(control.getCjtA());

                            }
                            else if (aux2 == 2){
                                System.out.println("Escribir informacion de aula: codigo");
                                String codigo = sc.next();
                                control.eliminar_aula(control.StringtoAula(codigo));
                                mostrar_horario_por_pantalla(control.getCjtA());

                            }
                            else if (aux2 == 3){
                                System.out.println("Escribir informacion de materia: siglas");
                                String siglas = sc.next();
                                control.eliminar_materia(control.StringtoMateria(siglas));
                                mostrar_horario_por_pantalla(control.getCjtA());
                            }
                        }
                        else if (aux == 2){
                            
                        }
                        break;
                case 5:salir = true;
                        break;
                default: System.out.println("Opcion no valida");
            }
        }
        
        
        
        
        
    }
    /**
     * Imprimeix l'horari per la pantalla.
     * @param cjt Conjunt d'asignacins que es volen imprimir.
     */
    private static void mostrar_horario_por_pantalla(capaDominio.CjtAsignaciones cjt){
        Map<capaDominio.FranjaHoraria, Map<capaDatos.Aula, capaDatos.Asignatura>> m = cjt.getCjtA();   
        System.out.println("HORAS              LUNES              MARTES              MIERCOLES              JUEVES              VIERNES");    
        for (int hora = 0; hora < 12; hora++) {
            System.out.print((hora+8) + ":00"); 
            boolean acabat = false; int i = 0;
            while (!acabat) {
                if (i != 0) System.out.print("     ");
                acabat = true;
                for (int dia = 0; dia < 5; dia++){
                    capaDominio.FranjaHoraria fh_aux = new capaDominio.FranjaHoraria(hora+8, Dia_semana(dia));
                    System.out.print("          ");
                    if (m.containsKey(fh_aux) && m.get(fh_aux).size() > i){
                        Iterator e = m.get(fh_aux).keySet().iterator();
                        for (int j = 0; j < i && e.hasNext(); j++) e.next();
                        if (e.hasNext()){       
                            capaDatos.Aula key = (capaDatos.Aula) e.next();
                            aux_mostrar_horario_por_pantalla(key, m.get(fh_aux).get(key));
                            acabat = false;
                        }
                    } else System.out.print("               ");
                }
                System.out.println("");
                i++;
            }
        }
    }
    
    /**
     * Auxiliar per imprimir les asignacions d'una forma ordenada.
     * @param a Asignacion que es vol impimir.
     */
    private static void aux_mostrar_horario_por_pantalla(capaDatos.Aula a, capaDatos.Asignatura as){
        System.out.print(as.getMat().getSiglas() + " " + as.getGrupo() + " " + as.getTipusClase() + " [" + a.getCodigo() + "]");
    }
    
    private static FranjaHoraria.Dia Dia_semana(int dia){
        
        if (dia == 0) return FranjaHoraria.Dia.LUNES;
        if (dia == 1) return FranjaHoraria.Dia.MARTES;
        if (dia == 2) return FranjaHoraria.Dia.MIERCOLES;
        if (dia == 3) return FranjaHoraria.Dia.JUEVES;
        return FranjaHoraria.Dia.VIERNES;
    }
}