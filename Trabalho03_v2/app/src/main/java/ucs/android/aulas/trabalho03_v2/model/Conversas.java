package ucs.android.aulas.trabalho03_v2.model;

import java.io.Serializable;

public class Conversas implements Serializable {
    private int co_idl, co_usuario_remetente, co_usuario_destinatario;
    private String co_data, co_localizacao, co_msg, co_nome_remetente, co_nome_destinario;

    public Conversas() { }

    public Conversas(int co_usuario_remetente, int co_usuario_destinatario, String co_data, String co_localizacao, String co_msg, String co_nome_remetente, String co_nome_destinario) {
        super();
//        this.co_idl = co_idl;
        this.co_usuario_remetente = co_usuario_remetente;
        this.co_usuario_destinatario = co_usuario_destinatario;
        this.co_data = co_data;
        this.co_localizacao = co_localizacao;
        this.co_msg = co_msg;
        this.co_nome_remetente = co_nome_remetente;
        this.co_nome_destinario = co_nome_destinario;


    }

    public int getId() {
        return co_idl;
    }

    public void setId(int co_idl) {
        this.co_idl = co_idl;
    }

    public int getUsuarioRemetente() {
        return co_usuario_remetente;
    }

    public void setUsuarioRemetente(int co_usuario_remetente) {
        this.co_usuario_remetente = co_usuario_remetente;
    }

    public int getUsuarioDestinatario() {
        return co_usuario_destinatario;
    }

    public void setUsuarioDestinatario(int co_usuario_destinatario) {
        this.co_usuario_destinatario = co_usuario_destinatario;
    }

    public String getData() {
        return co_data;
    }

    public void setData(String co_data) {
        this.co_data = co_data;
    }

    public String getNomeRemetente() {
        return co_nome_remetente;
    }

    public void setNomeRemetente(String co_nome_remetente) {
        this.co_nome_remetente = co_nome_remetente;
    }

    public String getNomeDestinatario() {
        return co_nome_destinario;
    }

    public void setNomeDestinatario(String co_nome_destinario) {
        this.co_data = co_nome_destinario;
    }

    public String getLocalizacao() {
        return co_localizacao;
    }

    public void setLocalizacao(String co_localizacao) {
        this.co_localizacao = co_localizacao;
    }

    public String getMsg() {
        return co_msg;
    }

    public void setMsg(String co_msg) {
        this.co_msg = co_msg;
    }
}
