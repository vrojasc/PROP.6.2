/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hamcrest.*;

/**
 *
 * 
 */
public class CjtAsignacionesTest {
    /*
    public CjtAsignacionesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //System.out.println("setUpClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        //System.out.println("setUpClass");
    }
    
    @Before
    public void setUp() {
        //System.out.println("setUpClass");
    }
    
    @After
    public void tearDown() {
        //System.out.println("setUpClass");
    }

    /**
     * Test of getCjtA method, of class CjtAsignaciones.
     
    @Test
    public void testGetCjtA() {
        System.out.println("getCjtA");
        capaDominio.CjtAsignaciones CJTA = new capaDominio.CjtAsignaciones();
        
        capaDatos.Aula aula = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria = new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        capaDominio.Asignacion Asig = new Asignacion(aula, asignatura , franjahoraria);
        
        CJTA.addelement(Asig);
        
        assertEquals("Las dos Asignaciones son iguales.", Asig, CJTA.getCjtA().get(0));
      
    }

    /**
     * Test of addelement method, of class CjtAsignaciones.
     
    @Test
    public void testAddelement() {
        System.out.println("addelement");
        capaDominio.CjtAsignaciones CJTA = new capaDominio.CjtAsignaciones();
        
        capaDatos.Aula aula = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria = new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        capaDominio.Asignacion Asig = new Asignacion(aula, asignatura , franjahoraria);
        
        CJTA.addelement(Asig);
        
        assertEquals("Añadido elemento correctamente.", Asig, CJTA.getCjtA().get(0));
    }

    /**
     * Test of delelement method, of class CjtAsignaciones.
     
    @Test
    public void testDelelement() {
        System.out.println("delelement");
        capaDominio.CjtAsignaciones CJTA = new capaDominio.CjtAsignaciones();
        
        capaDatos.Aula aula = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria = new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        capaDominio.Asignacion Asig = new Asignacion(aula, asignatura , franjahoraria);
        
        CJTA.addelement(Asig);
        CJTA.delelement();
        
        assertEquals("Borrado del ultimo elemento correcto", 0, CJTA.getCjtA().size());
       
    }

    /**
     * Test of get_asignaciones_ordenadas method, of class CjtAsignaciones.
     
    @Test
    public void testGet_asignaciones_ordenadas() {
        System.out.println("get_asignaciones_ordenadas");
        capaDominio.CjtAsignaciones CJTA = new capaDominio.CjtAsignaciones();
        
        capaDatos.Aula aula = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria = new capaDominio.FranjaHoraria(8, 10, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        capaDominio.Asignacion Asig = new Asignacion(aula, asignatura , franjahoraria);
        
        capaDatos.Aula aula2 = new capaDatos.Aula("A5102", 40, capaDatos.Asignatura.TipusClase.T);
        capaDominio.FranjaHoraria franjahoraria2 = new capaDominio.FranjaHoraria(13, 15, FranjaHoraria.Dia.LUNES);
        capaDatos.Materia materia2 = new capaDatos.Materia("Fisica", "F", 1);
        capaDatos.Asignatura asignatura2 = new capaDatos.Asignatura(materia, 20, Asignatura.TipusClase.T, 40, 2);
        capaDominio.Asignacion Asig2 = new Asignacion(aula, asignatura , franjahoraria);
         
        CJTA.addelement(Asig2);
        CJTA.addelement(Asig);
        
        //Map<Integer, ArrayList<Asignacion>> expResult = new HashMap<Integer, ArrayList<Asignacion>>();
        //expResult.put(Asig.getFranjaHoraria().unificar_values(), new ArrayList<>());
        //expResult.put(Asig2.getFranjaHoraria().unificar_values(), new ArrayList<>());
        Map<Integer, ArrayList<Asignacion>> result = CJTA.get_asignaciones_ordenadas();
        Set set = result.entrySet();
        Iterator it = set.iterator();
        Map.Entry m = (Map.Entry)it.next(); 
        assertEquals("",m.getKey(), Asig.getFranjaHoraria().unificar_values());
       
    }
    */
}