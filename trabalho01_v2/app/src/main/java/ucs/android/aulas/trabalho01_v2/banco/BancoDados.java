package ucs.android.aulas.trabalho01_v2.banco;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ucs.android.aulas.trabalho01_v2.R;
import ucs.android.aulas.trabalho01_v2.model.Bebida;
import ucs.android.aulas.trabalho01_v2.model.Mesa;
import ucs.android.aulas.trabalho01_v2.model.Pedido;
import ucs.android.aulas.trabalho01_v2.model.PedidoItem;
import ucs.android.aulas.trabalho01_v2.model.Prato;
import ucs.android.aulas.trabalho01_v2.model.Produto;
public class BancoDados {

    private BancoDados() {
    }

    private List<Produto> listaProdutos = new ArrayList<>();
    private List<Mesa> mesaslista = new ArrayList<>();
    private List<Pedido> pedidoslista = new ArrayList<>();
    private List<Bebida> bebidaslista = new ArrayList<>();
    private List<PedidoItem> pedidoitemlista = new ArrayList<>();

    private static BancoDados instancia;


    public void setProduto(Produto produto){

        this.listaProdutos.add(produto);
    }


    public static BancoDados getInstancia() {
        if (instancia == null) {
            instancia = new BancoDados();
        }
        return instancia;
    }

    public Pedido getPedido(int nro) {
      ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
       for (Pedido p : pedidoslista) {
               return p;
       }
       return null;
    }

    public void setPedido(Pedido pedido) {
        this.pedidoslista.add(pedido);
    }

    public Mesa getMesa(int nro) {
        for (Mesa m : mesaslista) {
            if (m.getId() == nro) {
                return m;
            }
        }
        return null;
    }

    public void setMesa(Mesa mesa) {
        this.mesaslista.add(mesa);
    }

    private Pedido cursorToPedido(Cursor cursor) {
        Pedido pedido = new Pedido();
        pedido.setId(Integer.parseInt(cursor.getString(0)));
        return pedido;
    }

    public ArrayList<Pedido> getAllPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        for (Pedido p : pedidoslista) {
            if (p.getBpago() == false) {
                listaPedidos.add(p);
            }

        }
        return listaPedidos;
    }

    public ArrayList<Pedido> getAllPedidosCT() {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        for (Pedido p : pedidoslista) {
                listaPedidos.add(p);
        }
        return listaPedidos;
    }

    public ArrayList<Pedido> getRemovePedido(int nro) {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        for (Pedido p : pedidoslista) {
            if (p.getId() == nro){
                p.setBpago(true);
//                listaPedidos.remove(p);
            }
        }
        return listaPedidos;
    }

    public ArrayList<Pedido> getPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
        int iaux = -1;
        for (Pedido p : pedidoslista) {
            if (iaux == p.getId()) {
            } else {
                if (p.getBpago() == false) {
                    listaPedidos.add(p);
                }
            }
            iaux = (p.getId());
        }
        return listaPedidos;
    }

    public ArrayList<Pedido> MostraItens(int nro){
        ArrayList<Pedido> pedidoslista = new ArrayList<Pedido>();
        for (Pedido p : pedidoslista) {
            if (p.getId() == nro) {
                pedidoslista.add(p);
            }
        }
        return pedidoslista;
    }

    private Mesa cursorToMesa(Cursor cursor) {
        Mesa mesa = new Mesa();
        mesa.setId(Integer.parseInt(cursor.getString(0)));
        return mesa;
    }

    public ArrayList<Mesa> getAllMesas() {
        ArrayList<Mesa> listaMesas = new ArrayList<Mesa>();
        for (int i = 1; i <= 50; i++) {
            Mesa mesas = new Mesa();
            if (mesas.getId() != i){
                mesas.setId(i);
                listaMesas.add(mesas);
            }
        }
        return listaMesas;
    }

    public ArrayList<Produto> getAllProdutosBebidas(String sBebida) {

        ArrayList<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.clear();

        if (sBebida.equals("")) {

        } else {

            if (sBebida.equals("B")) {

                Bebida bebida = new Bebida("500ml");
                Bebida bebida1 = new Bebida("1L");
                Bebida bebida2 = new Bebida("2L");
                Bebida bebida3 = new Bebida("Lata");
                Bebida bebida4 = new Bebida("600ml");
                Bebida bebida5 = new Bebida("250ml");
                Bebida bebida6 = new Bebida("Copo");
                PedidoItem pedido = new PedidoItem(0);

                listaProdutos.add(new Produto(1, "AGUA SEM GÁS", "", 3.00f, bebida, null, R.drawable.aguas,pedido));
                listaProdutos.add(new Produto(2, "AGUA COM GÁS", "", 3.50f, bebida1, null, R.drawable.aguas,pedido));
                listaProdutos.add(new Produto(3, "H20", "", 5.00f, bebida1, null, R.drawable.refrig,pedido));
                listaProdutos.add(new Produto(4, "GUARANÁ LATA", "", 3.50f, bebida1, null, R.drawable.refrig,pedido));
                listaProdutos.add(new Produto(5, "PEPSI LATA", "", 4.25f, bebida1, null, R.drawable.refrig,pedido));
                listaProdutos.add(new Produto(6, "COCA LATA", "", 3.25f, bebida1, null, R.drawable.refrig,pedido));
                listaProdutos.add(new Produto(7, "COCA 600ML", "", 4.50f, bebida1, null, R.drawable.refrig,pedido));
                listaProdutos.add(new Produto(8, "COCA 2L", "", 6.00f, bebida1, null, R.drawable.lata,pedido));
                listaProdutos.add(new Produto(9, "PEPSI 600ML", "", 5.00f, bebida1, null, R.drawable.ks,pedido));
                listaProdutos.add(new Produto(10, "CERVEJA LATA", "", 4.60f, bebida1, null, R.drawable.cerveja,pedido));
                listaProdutos.add(new Produto(11, "CERVEJA LONG", "", 6.00f, bebida1, null, R.drawable.cerveja,pedido));
                listaProdutos.add(new Produto(12, "CERVEJA LITRÃO", "", 15.00f, bebida1, null, R.drawable.cerveja,pedido));
                listaProdutos.add(new Produto(13, "CERVEJA 600ML", "", 10.00f, bebida1, null, R.drawable.cerveja,pedido));
                listaProdutos.add(new Produto(14, "SUCO LARANJA - COPO", "", 12.00f, bebida1, null, R.drawable.suco,pedido));
                listaProdutos.add(new Produto(15, "SUCO MORANGO - COPO", "", 12.00f, bebida1, null, R.drawable.suco,pedido));
                listaProdutos.add(new Produto(16, "SUCO ABACAXI - COPO", "", 12.00f, bebida1, null, R.drawable.suco,pedido));
            } else {
                Prato prato1 = new Prato(1);
                Prato prato2 = new Prato(2);
                Prato prato3 = new Prato(3);
                Prato prato4 = new Prato(4);
                PedidoItem pedido = new PedidoItem(0);


                listaProdutos.add(new Produto(2, "X DA CASA - MINI", "", 15.00f, null, prato1, R.drawable.xis,pedido));
                listaProdutos.add(new Produto(3, "X DE FRANGO", "", 30.50f, null, prato1, R.drawable.xis,pedido));
                listaProdutos.add(new Produto(4, "X SALADA", "", 24.50f, null, prato1, R.drawable.xis,pedido));
                listaProdutos.add(new Produto(5, "TORRADA", "", 19.00f, null, prato1, R.drawable.torrada1,pedido));
                listaProdutos.add(new Produto(6, "BAURU", "", 30.50f, null, prato2, R.drawable.bauru,pedido));
                listaProdutos.add(new Produto(7, "BAURU - 4 PESSOAS", "", 80.50f, null, prato4, R.drawable.bauru,pedido));
                listaProdutos.add(new Produto(8, "1/2 BAURU", "", 20.50f, null, prato1, R.drawable.bauru,pedido));
                listaProdutos.add(new Produto(9, "HOT DOG", "", 14.25f, null, prato1, R.drawable.hotdog,pedido));
                listaProdutos.add(new Produto(10, "ALA MINUTA", "", 21.25f, null, prato1, R.drawable.ala,pedido));
            }
        }
        return listaProdutos;
    }
}
