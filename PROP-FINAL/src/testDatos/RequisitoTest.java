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
public class RequisitoTest {
    Requisito re = new Requisito();
    static Scanner sc = new Scanner(System.in);
    
    public void testRequisito(){
        System.out.println("Ingresa el tipo de requisito(1->pre o 2->co");
        int tipo = sc.nextInt();
        System.out.println("¿Cuantas materias deseas como requisito?(Se generaran automaticamente");
        int cantidad = sc.nextInt();
        ArrayList<Materia> aux = new ArrayList<>();
        for(int i=0;i<cantidad;i++){
            aux.add(new Materia("prueba"+i,"P"+1,1));
        }
        if(tipo==1) re = new Requisito(Requisito.TipoR.pre, aux);
        else re = new Requisito(Requisito.TipoR.co, aux);
        if(re!=null)System.out.println("Requisito inicializado correctamente");
        else System.out.println("Problema en la inicializacion");
    }
    
    public void testgetTipoR(){
        System.out.println("Tipo recibido: "+re.getTipoR());
    }
    
    public void testsetTipoR(){
        System.out.println("Ingresa el tipo de requisito(1->pre o 2->co");
        int tipo = sc.nextInt();
        if (tipo==1) re.setTipoR(Requisito.TipoR.pre);
        else re.setTipoR(Requisito.TipoR.co);
        if(re.getTipoR()==Requisito.TipoR.pre || re.getTipoR()==Requisito.TipoR.co) System.out.println("Tipo de restriccion asignado con éxito");
        else System.out.println("Problema al asignar el tipo");
    }
    
    public void testgetMats(){
        if(re.getMats()==null) System.out.println("Aun no se ha inicializado el requisito.\n"
                + "Por favor utilice el constructor o el set.");
        else{
            System.out.println("Tamaño de la lista de materias: "+re.getMats().size());
            System.out.println("¿Desea ver la lista de materias?(S/N)");
            char mostrar = sc.next().charAt(0);
            if(mostrar=='S' || mostrar == 's') System.out.println(re.getMats());
        }
    }
    
    public void testsetMats(){
        System.out.println("¿Cuantas materias deseas como requisito?(Se generaran automaticamente");
        int cantidad = sc.nextInt();
        ArrayList<Materia> aux = new ArrayList<>();
        for(int i=0;i<cantidad;i++){
            aux.add(new Materia("prueba"+i,"P"+1,1));
        }
        re.setMats(aux);
        if(re.getMats().equals(aux)) System.out.println(cantidad+" materias añadidas con exito a la lista.");
        else System.out.println("Fallo al añadir las materias");
    }
    public static void main (String [] args){
        RequisitoTest r1 = new RequisitoTest();
        boolean aux = true;
        while(aux){
            System.out.println("\n");
            System.out.println("Test Asignatura:\n¿Que función desea probar?(1..6)");
            System.out.println("1.Requisito()\n2.geetTipoR()\n3.setTipoR()\n"
                    + "4.getMats()\n5.setMats()\n6.Salir");
            System.out.println("Importante: Es recomendable utilizar el test del constructor o los set antes que los get, en caso contrario, devolveran nulo");
            int res = sc.nextInt();
            sc.nextLine();//Para limpiar el salto de linea del buffer
            switch(res){
                case 1:
                        r1.testRequisito();
                        break;
                case 2:
                        r1.testgetTipoR();
                        break;
                case 3:
                        r1.testsetTipoR();
                        break;
                case 4:
                        r1.testgetMats();
                        break;
                case 5:
                        r1.testsetMats();
                        break;
                case 6:
                        aux = false;
                        break;
                default:System.out.println("Opcion no valida");
                        break;
            }
        }
    }
    
}
