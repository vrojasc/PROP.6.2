/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDatos;

import capaDatos.Asignatura;
import capaDatos.Aula;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class AulaTest {
    
    Aula au = new Aula();
    static Scanner sc = new Scanner(System.in);
    
    public void testAula(){
        System.out.println("Ingresa el codigo del aula");
        String codigo = sc.nextLine();
        System.out.println("Ingresa la capacidad del aula");
        int capacidad = sc.nextInt();
        sc.nextLine();//Para limpiar cache
        System.out.println("Ingresa el tipo de clase del aula(T, L o P)");
        char tipo = sc.next().charAt(0);
        if(tipo=='T') au = new Aula(codigo, capacidad, Asignatura.TipusClase.T);
        else if(tipo=='L') au = new Aula(codigo, capacidad, Asignatura.TipusClase.L);
        else if(tipo=='P') au = new Aula(codigo, capacidad, Asignatura.TipusClase.P);
        else{
            System.out.println("Tipo no reconocido correctamente, por defecto T");
            au = new Aula(codigo, capacidad, Asignatura.TipusClase.T);
        }
        if(au==null) System.out.println("Constructor fallido");
        else System.out.println("Aula creada correctamente, utilice los test de los get para poder revisar los datos");
    
    }
        
    public void testgetCodigo(){
        System.out.println("Codigo recibida: "+au.getCodigo());
    }
    
    public void testsetCodigo(){
        System.out.println("Ingresa el codigo del aula");
        String codigo = sc.nextLine();
        au.setCodigo(codigo);
        if (au.getCodigo().equals(codigo)) System.out.println("Codigo modificado correctamente");
        else System.out.println("Error al modificar");   
    }
    
    public void testgetCapacidad(){
        System.out.println("Capacidad recibida: "+au.getCapacidad());        
    }
    
    public void testsetCapacidad(){
        System.out.println("Ingresa la capacidad del aula");
        String codigo = sc.nextLine();
        au.setCodigo(codigo);
        if (au.getCodigo().equals(codigo)) System.out.println("Capacidad modificada correctamente");
        else System.out.println("Error al modificar"); 
        
    }
    
    public void testgetTipusClase(){
        System.out.println("Tipo de clase recibido: "+au.getTipusClase());        
    }
    
    public void testsetTipusClase(){
        System.out.println("Ingresa el tipo de clase del aula(T,L o P)");
        char tipo = sc.next().charAt(0);
        if(tipo=='T') au.setTipusClase(Asignatura.TipusClase.T);
        else if(tipo=='L') au.setTipusClase(Asignatura.TipusClase.L);
        else if(tipo=='P') au.setTipusClase(Asignatura.TipusClase.P);
        else{
            System.out.println("Tipo no reconocido correctamente, por defecto T");
            au.setTipusClase(Asignatura.TipusClase.T);
        }
        System.out.println("Tipo clase modificado correctamente"); 
    }
    public static void main (String [] args){
        AulaTest r1 = new AulaTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test Aula:\n¿Que función desea probar?(1..8)");
            System.out.println("1.Aula()\n2.getCodigo()\n3.setCodigo()\n"
                    + "4.getCapacidad()\n5.setCapacidad()\n6.getTipusClase()\n7.setTipusClase()\n8.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testAula();
                        break;
                case 2:
                        r1.testgetCodigo();
                        break;
                case 3:
                        r1.testsetCodigo();
                        break;
                case 4:
                        r1.testgetCapacidad();
                        break;
                case 5:
                        r1.testsetCapacidad();
                        break;
                case 6:
                        r1.testgetTipusClase();
                        break;
                case 7:
                        r1.testsetTipusClase();
                        break;
                case 8: aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
