/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDominio;

import capaDominio.FranjaHoraria;
import capaDominio.FranjaHoraria.Dia;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class FranjaHorariaTest {
    /*FranjaHoraria fh = new FranjaHoraria();
    static Scanner sc = new Scanner(System.in);
    
    public void testFranjaHoraria(){
        System.out.println("Ingresa una hora de inicio(Entero)");
        int horaI = sc.nextInt();
        System.out.println("Ingresa una hora final(Entero)");
        int horaF = sc.nextInt();
        System.out.println("Escoja un dia:\n1-Lunes\n2-Martes\n3-Miercoles\n4-Jueves\n5-Viernes");
        int op = sc.nextInt();
        switch(op){
            case 1: fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.LUNES);
            break;
            case 2: fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.MARTES);
            break;
            case 3: fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.MIERCOLES);
            break;
            case 4: fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.JUEVES);
            break;
            case 5: fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.VIERNES);
            break;
            default:System.out.println("Opcion no valida, por defecto se escojerá lunes"); 
                    fh = new FranjaHoraria(horaI, horaF, FranjaHoraria.Dia.LUNES);
            break;
        }
        if(fh!=null) System.out.println("Constructora creada con exito");
        else System.out.println("Fallo en el constructor");
    }
    
    public void testgetHoraIni(){
        System.out.println("Hora de inicio recibida: "+fh.getHoraIni());
    }
    
    public void testgetHoraFi(){
        
        System.out.println("Hora final recibida: "+fh.getHoraFi());
    }
    
    public void testgetDia(){
        
        System.out.println("Dia recibido: "+fh.getHoraIni());
    }
    
    public void testunificar_values(){
        if(fh==null) System.out.println("Es necesario usar el constructor para poder utilizar esta prueba");
        else {
            System.out.println("El dia cargado es "+fh.getDia()+" y la hora inicial es "+fh.getHoraIni());
            System.out.println("Esto devuelve las horas lectivas que han pasado desde"
                    + " la primera hora del lunes hasta la hora ini.\nEn este caso devuelve: "+fh.unificar_values());
        }
    }
    
    public void testseguentHora(){
        System.out.println("Devuelve cierto si no es la ultima hora del viernes y luego suma 1 a la hora inicial y final: "+fh.seguentHora());
    }
    
    public static void main (String [] args){
        FranjaHorariaTest r1 = new FranjaHorariaTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test Asignatura:\n¿Que función desea probar?(1..7)");
            System.out.println("1.FranjaHoraria()\n2.getHoraIni()\n3.getHoraFi()\n"
                    + "4.getDia()\n5.unificar_values()\n6.seguentHora()\n7.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testFranjaHoraria();
                        break;
                case 2:
                        r1.testgetHoraIni();
                        break;
                case 3:
                        r1.testgetHoraFi();
                        break;
                case 4:
                        r1.testgetDia();
                        break;
                case 5:
                        r1.testunificar_values();
                        break;
                case 6:
                        r1.testseguentHora();
                        break;
                case 7:aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }*/
}
