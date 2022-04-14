package ucs.android.aulas.trabalho01;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private Banco () {}

    private List<Produto> produtoslista = new ArrayList<>();
    private List<Mesa> mesaslista = new ArrayList<>();
    private List<Pedido> pedidoslista = new ArrayList<>();

    private static Banco instancia;

    public static Banco getInstancia() {
        if (instancia == null){
            instancia = new Banco();
        }
        return instancia;
    }

    public Mesa getMesa(int nro) {
        for (Mesa m : mesaslista) {
            if (m.getNum() == nro) {
                return m;
            }
        }
        return null;
    }

    public void setMesa(Mesa mesas){

        this.mesaslista.add(mesas);
    }

    public Produto getProduto(String nome, double valor, int qtd){
        for (Produto p : produtoslista) {
            if (p.getNome().equals(nome)) {
//                return nome;
            }
        }
        return null;
    }

    public void setProduto(Produto produto){

        this.produtoslista.add(produto);
    }
}
