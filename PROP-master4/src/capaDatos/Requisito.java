/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaDatos;

import java.util.ArrayList;


/**
 *
 * @author Alberto
 */
public class Requisito {

    public Requisito() {}
    
     public enum TipoR {
        pre, co
     }
     
    private TipoR tipoR;
    private ArrayList<Materia> mats; 
    

    public Requisito(TipoR tipoR, ArrayList<Materia> mats) {
        this.tipoR = tipoR;
        this.mats = mats;
    }

    public TipoR getTipoR() {
        return tipoR;
    }

    public void setTipoR(TipoR tipoR) {
        this.tipoR = tipoR;
    }

    public ArrayList<Materia> getMats() {
        return mats;
    }

    public void setMats(ArrayList<Materia> mats) {
        this.mats = mats;
    }
    
    
    
    
}
