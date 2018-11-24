/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDominio;

import capaDatos.Asignatura;
import capaDatos.Aula;
import capaDatos.Materia;
import capaDatos.Requisito;
import capaDominio.Asignacion;
import capaDominio.CjtAsignaciones;
import capaDominio.FranjaHoraria;
import capaDominio.Restricciones;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author casa1
 */
public class RestriccionesTest {
    Restricciones rest = new Restricciones();
    static Scanner sc = new Scanner(System.in);
    
    public RestriccionesTest(Restricciones rest) {
        this.rest = rest;
    }
    
    public RestriccionesTest() {
    }
    
    public void testComprueba_Restricciones(){
  /*      System.out.println("Esta pruebas son estaticas ya que dependen de diversas variables.");
        Materia mat1 = new Materia("Prueba1", "P1", 1);
        CjtAsignaciones CA1 = new CjtAsignaciones();
        Asignatura a1 = new Asignatura(mat1, 10, Asignatura.TipusClase.T, 20, 2);
        Asignatura a2 = new Asignatura(mat1, 11, Asignatura.TipusClase.L, 0, 0);
        FranjaHoraria f1 = new FranjaHoraria(8, 9, FranjaHoraria.Dia.LUNES);
        FranjaHoraria f2 = new FranjaHoraria(10, 11, FranjaHoraria.Dia.LUNES);
        Aula au1 = new Aula("au1", 20, Asignatura.TipusClase.T);
        ArrayList<Aula> aulas = new ArrayList<>();
        aulas.add(au1);
        Asignacion as1 = new Asignacion(au1, a2, f2);
        CA1.addelement(as1);
        System.out.println("Prueba de asignatura y aulas con capacidad y tipo correcto "
                + ",franja horaria sin asignaciones conflictivas (return aula disponible): "+rest.Comprueba_Restricciones(a1,f1,CA1,aulas));
        aulas = new ArrayList<>();
        au1 = new Aula("au1", 10, Asignatura.TipusClase.T);
        aulas.add(au1);
        System.out.println("Prueba de asignatura y aulas con capacidad o tipo incorrecto "
                + ",franja horaria sin asignaciones conflictivas (return null): "+rest.Comprueba_Restricciones(a1,f1,CA1,aulas));
        aulas = new ArrayList<>();
        au1 = new Aula("au1", 20, Asignatura.TipusClase.T);
        aulas.add(au1);
        as1 = new Asignacion(au1, a2, f1);
        CA1.addelement(as1);
        System.out.println("Prueba de asignatura y aulas con capacidad y tipo correcto "
                + ",franja horaria con asignaciones conflictivas (return null): "+rest.Comprueba_Restricciones(a1,f1,CA1,aulas));
    }
    
    public void testcapacidadValida(){
        /*Asignatura a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, 4, 3);
        Aula a2 = new Aula("PRUEBA1", 4, Asignatura.TipusClase.T);
        System.out.println("Prueba con capacidad de asignatura igual a capacidad aula (return true): "+rest.capacidadValida(a1, a2));
        
        a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, 7, 3);
        a2 = new Aula("PRUEBA2", 4, Asignatura.TipusClase.T);
        System.out.println("Prueba con capacidad de asignatura mayor a capacidad aula (return false): "+rest.capacidadValida(a1, a2));
        
        a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, 2, 3);
        a2 = new Aula("PRUEBA3", 4, Asignatura.TipusClase.T);
        System.out.println("Prueba con capacidad de asignatura menor a capacidad aula (return true): "+rest.capacidadValida(a1, a2));
        */
        System.out.println("Introduzca la capacidad del asignatura:");
        int capAs = sc.nextInt();
        System.out.println("Introducza la capadidad de la aula");
        int capAu = sc.nextInt();
        Asignatura a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, capAs, 3);
        Aula a2 = new Aula("PRUEBA1", capAu, Asignatura.TipusClase.T);
        if(rest.capacidadValida(a1, a2)) System.out.println("La capacidad es valida");
        else System.err.println("La capacidad no es valida");
    }
    
    public void testcompartenTipo(){
        Asignatura a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, 4, 3);
        Aula a2 = new Aula("PRUEBA1", 4, Asignatura.TipusClase.L);
        System.out.println("Prueba con una asignatura y aula con tipos diferentes (return false): "+rest.compartenTipo(a1, a2));//a2 esta en la lista de a1
        
        a1 = new Asignatura(null, 0, Asignatura.TipusClase.T, 4, 3);
        a2 = new Aula("PRUEBA2", 4, Asignatura.TipusClase.T);
        System.out.println("Prueba con una asignatura y aula con tipos identicos (return true): "+rest.compartenTipo(a1, a2));        
    }  
    
    public void testesCorequisito(){
        Materia mat1 = new Materia("Prueba1", "P1", 1);
        Materia mat2 = new Materia("Prueba2", "P2", 1);
        Materia mat3 = new Materia("Prueba3", "P3", 1);
        ArrayList<Materia> mats = new ArrayList<>();
        mats.add(mat2);
        Requisito req1 = new Requisito(Requisito.TipoR.co, mats);
        mat1.setRequisito(req1);
        Asignatura a1 = new Asignatura(mat1, 0, Asignatura.TipusClase.T, 0, 0);
        Asignatura a2 = new Asignatura(mat2, 0, Asignatura.TipusClase.T, 0, 0);
        System.out.println("Prueba con una asignatura a2 que es corequisito de la asignatura a1(return true): "+rest.esCorequisito(a1, a2));
        a2 = new Asignatura(mat3, 0, Asignatura.TipusClase.T, 0, 0);
        System.out.println("Prueba con una asignatura a2 que no es corequisito de la asignatura a1(return false): "+rest.esCorequisito(a1, a2));
    }
    
    public void testpertMismoGrupo(){
        Materia mat1 = new Materia("Prueba1", "P1", 1);
        Materia mat2 = new Materia("Prueba2", "P2", 2);
        Asignatura a1 = new Asignatura(mat1, 10, Asignatura.TipusClase.T, 0, 0);
        Asignatura a2 = new Asignatura(mat1, 11, Asignatura.TipusClase.L, 0, 0);
        System.out.println("Prueba con una asignatura a1(Grupo 10T) y otra a2(Grupo 11L) de la misma materia(return true): "+rest.pertMismoGrupo(a1, a2));
        a2 = new Asignatura(mat1, 20, Asignatura.TipusClase.T, 0, 0);
        System.out.println("Prueba con una asignatura a1(Grupo 10T) y otra a2(Grupo 20T) de la misma materia (return true): "+rest.pertMismoGrupo(a1, a2));
        a1 = new Asignatura(mat1, 11, Asignatura.TipusClase.L, 0, 0);
        a2 = new Asignatura(mat1, 12, Asignatura.TipusClase.P, 0, 0);
        System.out.println("Prueba con una asignatura a1(Grupo 11L) y otra a2(Grupo 12P) de la misma materia (return false): "+rest.pertMismoGrupo(a1, a2));
        a1 = new Asignatura(mat1, 10, Asignatura.TipusClase.T, 0, 0);
        a2 = new Asignatura(mat2, 10, Asignatura.TipusClase.T, 0, 0);
        System.out.println("Prueba con una asignatura a1(Grupo 10T) y otra a2(Grupo 10T) de distintas materias(return false): "+rest.pertMismoGrupo(a1, a2));
    }
    
    public void testtienenDistintoNivel(){
        Materia mat1 = new Materia("Prueba1", "P1", 1);
        Materia mat2 = new Materia("Prueba2", "P2", 2);
        Materia mat3 = new Materia("Prueba3", "P3", 1);
        Asignatura a1 = new Asignatura(mat1, 10, Asignatura.TipusClase.T, 0, 0);
        Asignatura a2 = new Asignatura(mat2, 11, Asignatura.TipusClase.L, 0, 0);
        System.out.println("Prueba con dos asignaturas de distinto nivel(return true): "+rest.tienenDistintoNivel(a1, a2));
        a2 = new Asignatura(mat3, 10, Asignatura.TipusClase.T, 0, 0);
        System.out.println("Prueba con dos asignaturas de distinto nivel(return false): "+rest.tienenDistintoNivel(a1, a2));
    }
    public static void main (String [] args){
        RestriccionesTest r1 = new RestriccionesTest();
        boolean aux = true;
        while(aux){
            System.out.println("Test Restricciones:\n¿Que función desea probar?(1..7)");
            System.out.println("1.Comprueba_Restricciones()\n2.capacidadValida()\n3.compartenTipo()\n"
                    + "4.esCorequisito()\n5.pertMismoGrupo()\n6.tienenDistintoNivel()\n7.Salir");
            int res = sc.nextInt();
            switch(res){
                case 1:
                        r1.testComprueba_Restricciones();
                        break;
                case 2:
 //                       r1.testcapacidadValida();
                        break;
                case 3:
                        r1.testcompartenTipo();
                        break;
                case 4:
                        r1.testesCorequisito();
                        break;
                case 5:
                        r1.testpertMismoGrupo();
                        break;
                case 6:
                        r1.testtienenDistintoNivel();
                        break;
                case 7: aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
}
