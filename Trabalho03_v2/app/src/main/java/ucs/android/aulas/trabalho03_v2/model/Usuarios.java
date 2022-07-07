package ucs.android.aulas.trabalho03_v2.model;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private int us_idl;
    private String us_usuario, us_online;
    private String sUsuarioLogado;

    public Usuarios() { }

    public Usuarios(int us_idl, String us_usuario, String us_online, String sUsuarioLogado) {
        super();
        this.us_idl = us_idl;
        this.us_usuario = us_usuario;
        this.us_online = us_online;
        this.sUsuarioLogado = sUsuarioLogado;

    }

    public int getId() {
        return us_idl;
    }

    public void setId(int us_idl) {
        this.us_idl = us_idl;
    }

    public String getUsuario() {
        return us_usuario;
    }

    public void setUsuario(String us_usuario) {
        this.us_usuario = us_usuario;
    }

    public String getOnline() {
        return us_online;
    }

    public void setOnline(String us_online) {
        this.us_online = us_online;
    }

    public String getUsuarioLogado() {
        return sUsuarioLogado;
    }

    public void setUsuarioLogado(String sUsuarioLogado) {
        this.sUsuarioLogado = sUsuarioLogado;
    }

}
