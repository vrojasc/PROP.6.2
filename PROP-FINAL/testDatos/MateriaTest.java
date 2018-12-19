/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDatos;

import capaDatos.Materia;
import capaDatos.Requisito;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class MateriaTest {
    Materia mat = new Materia();
    static Scanner sc = new Scanner(System.in);
    
    public void testMateria(){
        System.out.println("Ingresa el nombre de la materia");
        String nombre = sc.nextLine();
        System.out.println("Ingresa las siglas de la materia");
        String siglas = sc.nextLine();
        System.out.println("Ingresa el nivel de la materia");
        int nivel = sc.nextInt();
        sc.nextLine();//Para limpiar cache
        mat = new Materia(nombre, siglas, nivel);
        if(mat==null) System.out.println("Constructor fallido");
        else System.out.println("Aula creada correctamente, utilice los test de los get para poder revisar los datos");
    
    }
    
    public void testgetEspecialidad(){
        System.out.println("Especialidad recibida: "+mat.getEspecialitat());        
    }
    
    public void testgetNom(){
        System.out.println("Nombre recibidos: "+mat.getNom());        
    }
    
    public void testsetEspecialidad(){
        System.out.println("Escoge la especialidad");
        System.out.println("0.Sin especialidad\n1.Computació\n2.Enginyeria de computadors\n3.Enginyeria del software\n"
                + "4.Sistemes de informació\n5.Tecnologies de la informació");
        int esp = sc.nextInt();
        sc.nextLine();
        switch(esp){
            case 1: mat.setEspecialitat(Materia.Especialitat.Computació);
            break;
            case 2: mat.setEspecialitat(Materia.Especialitat.EnginyeriaComputadors);
            break;
            case 3: mat.setEspecialitat(Materia.Especialitat.EnginyeriaSoftware);
            break;
            case 4: mat.setEspecialitat(Materia.Especialitat.SistemesInformació);
            break;
            case 5: mat.setEspecialitat(Materia.Especialitat.TecnologiesInformació);
            break;
            case 0: mat.setEspecialitat(null);break;
            default: break;
        }
        System.out.println("Especialidad modificada correctamente");
        
    }
    
    public void testsetRequisito(){
        Requisito req = new Requisito();
        System.out.println("Las materias a las que hace referencia el requisito"
                + " seran cargadas por defecto");
        System.out.println("Ingresa el tipo de requisito(1->pre o 2->co)");
        int re = sc.nextInt();
        sc.nextLine();//Limpiar buffer
        Materia m1 = new Materia("Prueba1", "P1", 1);
        ArrayList<Materia>mats = new ArrayList<>();
        mats.add(m1);
        if(re==1) req = new Requisito(Requisito.TipoR.pre, mats);
        else if(re==2) req = new Requisito(Requisito.TipoR.co, mats);
        else{
                System.out.println("Opcion no valida, pre por defecto");
                req = new Requisito(Requisito.TipoR.pre, mats);
        }
        mat.setRequisito(req);
        System.out.println("Requisito cargado correctamente");
    }
    
    public void testgetSiglas(){
        System.out.println("Siglas recibidas: "+mat.getSiglas());        
    }
    
    public void testgetNivel(){
        System.out.println("Nivel recibido: "+mat.getNivel());        
    }
    
    public void testgetRequisito(){
        System.out.println("Requisitos recibidos: "+mat.getReqs());
    }
    
    public static void main (String [] args){
        MateriaTest r1 = new MateriaTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test Materia:\n¿Que función desea probar?(1..8)");
            System.out.println("1.Materia()\n2.getEspecialidad()\n3.getNom()\n"
                    + "4.setEspecialidad()\n5.setRequisito()\n6.getSiglas()\n7.getNivel()\n"
                    + "8.getRequisito()\n9.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testMateria();
                        break;
                case 2:
                        r1.testgetEspecialidad();
                        break;
                case 3:
                        r1.testgetNom();
                        break;
                case 4:
                        r1.testsetEspecialidad();
                        break;
                case 5:
                        r1.testsetRequisito();
                        break;
                case 6:
                        r1.testgetSiglas();
                        break;
                case 7:
                        r1.testgetNivel();
                        break;
                case 8: r1.testgetRequisito();
                        break;
                case 9: aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
