package ucs.android.aulas.trabalho01_v2.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Produto implements Serializable {
    private int id;
    private String snomeproduto;
    private String sdescricaoproduto;
    private float fpreco;
    private Bebida bebida;
    private Prato prato;
    private int id_imagem;


    public Produto() { }



    public Produto(int id,String snomeproduto, String sdescricaoproduto, Float fpreco, Bebida bebida, Prato prato, int id_imagem) {
        super();
        this.id = id;
        this.snomeproduto = snomeproduto;
        this.sdescricaoproduto = sdescricaoproduto;
        this.fpreco = fpreco;
        this.bebida = bebida;
        this.prato = prato;
        this.id_imagem = id_imagem;
    }

    public void setProduto(int id,String snomeproduto, String sdescricaoproduto, Float fpreco, Bebida bebida, Prato prato, int id_imagem) {
        this.id = id;
        this.snomeproduto = snomeproduto;
        this.sdescricaoproduto = sdescricaoproduto;
        this.fpreco = fpreco;
        this.bebida = bebida;
        this.prato = prato;
        this.id_imagem = id_imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return snomeproduto;
    }

    public void setNomeProduto(String snomeproduto) {
        this.snomeproduto = snomeproduto;
    }

    public String getDescricaoProduto() {
        return sdescricaoproduto;
    }

    public void setDescricaoProduto(String sdescricaoproduto) {
        this.sdescricaoproduto = sdescricaoproduto;
    }

    public Float getPrecoProduto() {
        return fpreco;
    }

    public void setPrecoProduto(Float fpreco) {
        this.fpreco = fpreco;
    }


    public Bebida getBebida() {
        return bebida;
    }

    public void setgetBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setprato(Prato prato) {
        this.prato = prato;
    }

    public int getid_imagem() {
        return id_imagem;
    }

    public void setid_imagem(int id_imagem) {
        this.id_imagem = id_imagem;
    }

}
