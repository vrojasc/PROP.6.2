/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDatos;


import capaDatos.GestionDatos;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class GestionDatosTest {
    GestionDatos gd = new GestionDatos();
    static Scanner sc = new Scanner(System.in);
    
    public void testGestionDatos(){
        System.out.println("Ingresa de que carpeta quieres leer los datos(STRING)");
        String aux = sc.nextLine();
        gd = new GestionDatos(aux);
        if(gd!=null)System.out.println("La carpeta "+aux+" será utilizada");
    }
    
    
    public static void main (String [] args){
        GestionDatosTest r1 = new GestionDatosTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test GestionDatos:\n¿Que función desea probar?(1..2)");
            System.out.println("1.GestionDatos()\n2.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testGestionDatos();
                        break;
                case 2:aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
