package ucs.android.aulas.trabalho01_v2.model;

import java.io.Serializable;

public class Mesa implements Serializable {

    private int id;
    private boolean bUsada;

    public Mesa() { }

    public Mesa(int id, boolean bUsada) {
        super();
        this.id = id;
        this.bUsada = bUsada;
    }

    public void setMesa(int id, boolean bUsada){
        this.id = id;
        this.bUsada = bUsada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getUsada() {
        return bUsada;
    }

    public void setUsada(boolean bUsada) {
        this.bUsada = bUsada;
    }
}
