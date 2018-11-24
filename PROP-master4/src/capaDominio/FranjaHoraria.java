/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author alumne
 */
    
    public class FranjaHoraria {
        
        public enum Dia {
            LUNES, MARTES, MIERCOLES, JUEVES,VIERNES 
        }
        
        private int HoraIni;
        private Dia dia;   
    
        
        public FranjaHoraria(Integer horaIni, Dia dia) {
            this.HoraIni = horaIni;
            this.dia = dia;
        }
        
        public int getHoraIni(){
            return HoraIni;
        }

        public Dia getDia(){
            return dia;
        }
        
        public int unificar_values(){
            return (dia.ordinal() * 12 + (HoraIni - 8));
        }
        
         public void set_values (Integer value){
            this.dia = convert_int_to_Dia(value/12);
            this.HoraIni = value%12 + 8;
        }
        
         
        public boolean seguentHora(){
            if (HoraIni  == 19) {
                if (dia == Dia.VIERNES) return false;
                seguentDia();
            } else {
                HoraIni++;
            }
            return true;
        }
            
        private void seguentDia(){
            if (dia == Dia.LUNES) dia = Dia.MARTES;
            else if (dia == Dia.MARTES) dia = Dia.MIERCOLES;
            else if (dia == Dia.MIERCOLES) dia = Dia.JUEVES;
            else if (dia == Dia.JUEVES) dia = Dia.VIERNES;            
            HoraIni = 8;
        }
        
        private Dia convert_int_to_Dia(int i) {
            switch (i){
                case 0: return Dia.LUNES;
                case 1: return Dia.MARTES;
                case 2: return Dia.MIERCOLES;
                case 3: return Dia.JUEVES;
                default: return Dia.VIERNES;
            }
        }
          
        @Override
        public boolean equals(Object o){
            FranjaHoraria FH = (FranjaHoraria) o;
            return FH.getDia() == dia && FH.getHoraIni() == HoraIni;
        } 
        
        @Override
        public int hashCode() {
            return 0;
        }
    }
