/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadorhorario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author alumne
 */
public class MenuGH {
    
    
    public MenuGH(){
        
    }
    
    public void menuejecutar(String opcion){
        if (opcion == "Generar"){
            // Llamar a la clase generarhorario donde se imprime
          //  mostrar_horario_por_pantalla(control.Generar());
            
        }
        else if (opcion == "Cargar"){
            String file;
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Elije fitxero:");
            try{
                file = buff.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
            
        }
        else if (opcion == "Guardar"){
            int h = 0;
            File file = new File("./Horario" + Integer.toString(h) + ".txt");
            while(file.exists()){
                file = new File("./Horario" + Integer.toString(++h) + ".txt");
            }// Crea el archivo con nombre Horario1 o Horario2... depende de los que hayan creados.
            
            // Guardar Horario.
            
            
            System.out.println("Nombre del archivo: Horario" + Integer.toString(h) + ".txt");
        
        }   
        
    

    }
}
    
    
    