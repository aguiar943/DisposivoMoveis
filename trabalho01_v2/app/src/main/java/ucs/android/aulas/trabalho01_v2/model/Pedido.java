package ucs.android.aulas.trabalho01_v2.model;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int id;
    private Boolean bpago;
    private Produto produto;
    private Mesa mesa;

    public Pedido() { }

    public Pedido(int id, boolean bpago, Produto produto, Mesa mesa) {
        super();
        this.id = id;
        this.bpago = bpago;
        this.produto = produto;
        this.mesa = mesa;
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

    public void setPedido(int id, boolean bpago, String snomeproduto, Produto produto, Mesa mesa){
        this.id = id;
        this.bpago = bpago;
        this.produto = produto;
        this.mesa = mesa;
    }

    public Produto getPedidoProdutos(int nro) {
        return produto;
    }

    public Mesa getMesaid(int nro) {
        return mesa;
    }

    public int getPedido(int nro) {
        return id;
    }
    public int getPedidoNro() {
        return id;
    }

    public int getPedidoMesas(){
        return id;
    }

}
