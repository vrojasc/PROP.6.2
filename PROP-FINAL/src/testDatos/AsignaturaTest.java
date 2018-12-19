/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDatos;

import capaDatos.Asignatura;
import capaDatos.Materia;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class AsignaturaTest {
    Asignatura asig = new Asignatura();
    static Scanner sc = new Scanner(System.in);
    
    public void testAsignatura(){
        System.out.println("Se ha creado un mat por defecto en el constructor. Si desea cambiarlo, utilice el testsetMat()");
        Materia mat = new Materia("Prueba1", "P1", 1);
        System.out.println("Ingresa el grupo de la asignatura");
        int grupo = sc.nextInt();
        sc.nextLine();//Para limpiar el buffer
        System.out.println("Ingresa el tipo de clase de la asignatura(T, L o P)");
        char tipo = sc.next().charAt(0);
        System.out.println("Ingresa la capacidad de la asignatura");
        int capacidad = sc.nextInt();
        System.out.println("Ingresa el numero de horas de la asignatura");
        int hora = sc.nextInt();
        if(tipo=='T') asig = new Asignatura(mat, grupo, Asignatura.TipusClase.T, capacidad, hora);
        else if(tipo=='L') asig = new Asignatura(mat, grupo, Asignatura.TipusClase.L, capacidad, hora);
        else if(tipo=='P') asig = new Asignatura(mat, grupo, Asignatura.TipusClase.P, capacidad, hora);
        else{
            System.out.println("Tipo no reconocido correctamente, por defecto T");
            asig = new Asignatura(mat, grupo, Asignatura.TipusClase.T, capacidad, hora);
        }
        if(asig==null) System.out.println("Constructor fallido");
        else System.out.println("Asignatura creada correctamente, utilice los test de los get para poder revisar los datos");
    }
    
    public void testgetMat(){
        System.out.println("Materia recibida: "+asig.getMat());
    }
    
    public void testsetMat(){
        System.out.println("Ingresa el nombre de la materia");
        String nom = sc.nextLine();
        System.out.println("Ingresa las siglas de la materia");
        String siglas = sc.nextLine();
        System.out.println("Ingresa el nivel de la materia");
        int nivel = sc.nextInt();
        Materia mat = new Materia(nom, siglas, nivel);
        asig.setMat(mat);
        if (asig.getMat().equals(mat)) System.out.println("Materia modificada correctamente");
        else System.out.println("Error al modificar");
    }
    
    public void testgetGrupo(){
        System.out.println("Grupo recibido: "+asig.getGrupo());        
    }
    
    public void testsetGrupo(){
        System.out.println("Ingresa el grupo de la asignatura");
        int grupo = sc.nextInt();
        asig.setGrupo(grupo);
        if (asig.getGrupo()==grupo) System.out.println("Grupo modificado correctamente");
        else System.out.println("Error al modificar");         
    }
    
    public void testgetTipusClase(){
        System.out.println("Tipo de clase recibido: "+asig.getTipusClase());
    }
    
    public void testsetTipusClase(){
        System.out.println("Ingresa el tipo de clase de la asignatura(T,L o P)");
        char tipo = sc.next().charAt(0);
        if(tipo=='T') asig.setTipusClase(Asignatura.TipusClase.T);
        else if(tipo=='L') asig.setTipusClase(Asignatura.TipusClase.L);
        else if(tipo=='P') asig.setTipusClase(Asignatura.TipusClase.P);
        else{
            System.out.println("Tipo no reconocido correctamente, por defecto T");
            asig.setTipusClase(Asignatura.TipusClase.T);
        }
        System.out.println("Tipo clase modificado correctamente");        
    }
    
    public void testgetCapacidad(){
        System.out.println("Capacidad recibida: "+asig.getCapacidad());
    }
    
    public void testsetCapacidad(){
        System.out.println("Ingresa la capacidad de la asignatura");
        int cap = sc.nextInt();
        asig.setCapacidad(cap);
        if (asig.getCapacidad()==cap) System.out.println("Grupo modificado correctamente");
        else System.out.println("Error al modificar");        
    }
    
    public void testgetHoraClase(){
        System.out.println("Horas de clase recibida: "+asig.getHoraClase());
    }
    
    public void testsetHoraClase(){
        System.out.println("Ingresa el numero de horas de la asignatura");
        int hora = sc.nextInt();
        asig.setHoraClase(hora);
        if (asig.getHoraClase()==hora) System.out.println("Horas de clase modificada correctamente");
        else System.out.println("Error al modificar");  
    }
    
    public static void main (String [] args){
        AsignaturaTest r1 = new AsignaturaTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test Asignatura:\n¿Que función desea probar?(1..12)");
            System.out.println("1.Asignatura()\n2.getMat()\n3.setMat()\n"
                    + "4.getGrupo()\n5.setGrupo()\n6.getTipusClase()\n7.setTipusClase()\n8.getCapacidad()\n9.setCapacidad()\n"
                    + "10.getHoraClase()\n11.setHoraClase()\n12.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testAsignatura();
                        break;
                case 2:
                        r1.testgetMat();
                        break;
                case 3:
                        r1.testsetMat();
                        break;
                case 4:
                        r1.testgetGrupo();
                        break;
                case 5:
                        r1.testsetGrupo();
                        break;
                case 6:
                        r1.testgetTipusClase();
                        break;
                case 7:
                        r1.testsetTipusClase();
                        break;
                case 8:
                        r1.testgetCapacidad();
                        break;
                case 9:
                        r1.testsetCapacidad();
                        break;
                case 10:
                        r1.testgetHoraClase();
                        break;
                case 11:
                        r1.testsetHoraClase();
                        break;
                case 12: aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
