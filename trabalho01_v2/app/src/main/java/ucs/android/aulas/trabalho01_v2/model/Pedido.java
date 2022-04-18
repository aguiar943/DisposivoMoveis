package ucs.android.aulas.trabalho01_v2.model;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {

    private int id;
    private Boolean bpago;
    private Produto produto;

    public Pedido() { }

    public Pedido(int id, boolean bpago, Produto produto) {
        super();
        this.id = id;
        this.bpago = bpago;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getBpago() {
        return bpago;
    }

    public void setBpago(boolean bpago) {
        this.bpago = bpago;
    }

    public void setPedido(int id, boolean bpago, String snomeproduto, Produto produto){
        this.id = id;
        this.bpago = bpago;
        this.produto = produto;
    }


}
