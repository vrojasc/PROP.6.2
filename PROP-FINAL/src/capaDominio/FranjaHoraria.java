/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDominio;

/**
 *
 * @author Alberto Camacho
 */
    
    public class FranjaHoraria {

        public FranjaHoraria() {
        }

        /**
         * Enumeracio amb els dies de la semana, LUNES, MARTES, MIERCOLES, JUEVES i VIERNES.
         */
        public enum Dia {
            LUNES, MARTES, MIERCOLES, JUEVES,VIERNES 
        }
        /**
         * Hora d'inici de la franja horaria.
         */
        private int HoraIni;
        /**
         * Dia de la franja horaria.
         */
        private Dia dia;
        
        /**
         * Constructora de la clase FranjaHoraria amb parametres d'entrada.
         * @param horaIni Hora d'inici que s'asignara a la hora d'inici de la clase.
         * @param dia Dia que s'asignara al Dia de la clase.
         */        
        public FranjaHoraria(Integer horaIni, Dia dia) {
            this.HoraIni = horaIni;
            this.dia = dia;
        }
        /**
         * Obte la hora d'inici de la clase FranjaHoraria.
         * @return Retorna la HoraIni.
         */
        public int getHoraIni(){
            return HoraIni;
        }
        /**
         * Obte el dia de la clase FranjaHoraria.
         * @return Retorna el Dia.
         */
        public Dia getDia(){
            return dia;
        }
        /**
         * Calcula l'hora "global" de la franja horaria, per tal de tenir un seguiment de franjes. 
         * @return Retonra un valor per saber l'hora inicial i el dia.
         */
        public int unificar_values(){
            return (dia.ordinal() * 12 + (HoraIni - 8));
        }
        
        //--------------------------------------------------------
        public void set_values (Integer value){
            this.dia = convert_int_to_Dia(value/12);
            this.HoraIni = value%12 + 8;
        }
        
        static public Dia StringtoDia(String dia){
            if ("LUNES".equals(dia)) return Dia.LUNES;
            else if ("MARTES".equals(dia)) return Dia.MARTES;
            else if ("MIERCOLES".equals(dia)) return Dia.MIERCOLES;
            else if ("JUEVES".equals(dia)) return Dia.JUEVES;
            else if ("VIERNES".equals(dia)) return Dia.VIERNES;
            return null;
        }
        
        public String getDiaString(){
            if (dia == Dia.LUNES) return "LUNES";
            else if (dia == Dia.MARTES) return "MARTES";
            else if (dia == Dia.MIERCOLES) return "MIERCOLES";
            else if (dia == Dia.JUEVES) return "JUEVES";
            else if (dia == Dia.VIERNES) return "VIERNES";
            return null;
        }
         /**
          * Calcula la seguent hora per la FranjaHoraria actual.
          * @return Retorna true si es pot augmentar la franja horaria, es a dir que no son les 8 de la tarda de divendres, i fals altrament.
          */
        public boolean seguentHora(){
            if (HoraIni  == 19) {
                if (dia == Dia.VIERNES) return false;
                seguentDia();
            } else {
                HoraIni++;
            }
            return true;
        }
            /**
             * Cambia el Dia pel seguent i augmenta les hores, HoraIni i HoraFi.
             */
        private void seguentDia(){
            if (dia == Dia.LUNES) dia = Dia.MARTES;
            else if (dia == Dia.MARTES) dia = Dia.MIERCOLES;
            else if (dia == Dia.MIERCOLES) dia = Dia.JUEVES;
            else if (dia == Dia.JUEVES) dia = Dia.VIERNES;            
            HoraIni = 8;
        }
        
        static public Dia convert_int_to_Dia(int i) {
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
