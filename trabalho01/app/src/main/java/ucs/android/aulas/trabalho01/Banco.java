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
}
