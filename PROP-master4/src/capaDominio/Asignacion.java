/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import capaDatos.Asignatura;
import capaDatos.Aula;


/**
 *
 * @author Victor
 */

public class Asignacion {
    
    
    private capaDatos.Aula aula;
    private capaDatos.Asignatura asignatura;
    private FranjaHoraria franjaHoraria;
    
    
    public boolean equals(Asignacion A){
        return A.getAsignatura().equals(asignatura) && A.getAula().equals(aula) && A.getFranjaHoraria().equals(franjaHoraria);
    }
    
    public Asignacion(capaDatos.Aula aula, capaDatos.Asignatura asignatura, FranjaHoraria franjaHoraria){
        this.aula = aula;
        this.asignatura = asignatura;
        this.franjaHoraria = franjaHoraria;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }
    
    
}
