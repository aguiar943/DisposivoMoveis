package ucs.android.aulas.trabalho03_v1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CpuUsageInfo;
import android.os.StrictMode;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ucs.android.aulas.trabalho03_v1.model.Conversas;
import ucs.android.aulas.trabalho03_v1.model.Usuarios;

public class Database  {

    private static final String DRIVER = "org.postgresql.Driver";
    private String URL = "jdbc:postgresql://pg-aguiar.cvxzy9dklpfi.sa-east-1.rds.amazonaws.com/";
    private static final String DATABASE = "db_aguiar";
    private static final String USER = "postgres";
    private static final String PASS = "masterkey";
    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;

    public boolean  getConnection() throws Exception  {
        if (android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            Class.forName(DRIVER);
            URL = URL + DATABASE;
            conn = DriverManager.getConnection(URL, USER, PASS);

            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        }
        return true;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {

        closeConnection(con);

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con, stmt);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public void AddConversa (String dataenvio, int iremetente, int destinatario, String localizacao, String msg) throws SQLException {
       PreparedStatement stmt = conn.prepareStatement("INSERT INTO public.ag_conversas( " +
               "co_data, co_usuario_remetente, co_usuario_destinatario, co_localizacao, co_msg) " +
        "    VALUES ( ?, ?, ?, ?, ?);");
        stmt.setString(1, dataenvio);
        stmt.setInt(2, iremetente);
        stmt.setInt(3, destinatario);
        stmt.setString(4, localizacao);
        stmt.setString(5, msg);
        int rows = stmt.executeUpdate();
        stmt.close();
    }

    public void AddUsuario (String Usuario, String Online) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO public.ag_usuarios( " +
                "us_usuario, us_online) " +
                "    VALUES ( ?, ?);");
        stmt.setString(1, Usuario);
        stmt.setString(2, Online);
        int rows = stmt.executeUpdate();
        stmt.close();
    }

    public void DesconectadaUsuario (String Usuario, String Online) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE  public.ag_usuarios  SET " +
                "us_usuario = ?,  " +
                "us_online = ? ;");
        stmt.setString(1, Usuario);
        stmt.setString(2, Online);
        int rows = stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Conversas> getMostraConversas(int usuario_destinatario, int usuario_remetente) throws SQLException{
        String SQL;
        ArrayList<Conversas> listaMSG = new ArrayList<Conversas>();
        SQL = " select * from ag_conversas where co_usuario_destinatario =" + usuario_destinatario +
              " and co_usuario_remetente = " + usuario_remetente;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()){
            Conversas conversas = new Conversas();
            conversas.setMsg(rs.getString("co_msg"));
            listaMSG.add(conversas);
        }
        rs.close();
        st.close();
        return listaMSG;
    }


    public ArrayList<Usuarios> getMostraOnlines() throws SQLException{
        String SQL;
        ArrayList<Usuarios> listaOnlines = new ArrayList<Usuarios>();
        SQL = "select * from ag_usuarios where us_online ='S'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(SQL);
        while (rs.next()){
            Usuarios usuarios = new Usuarios();
            usuarios.setUsuario(rs.getString("us_usuario"));
            listaOnlines.add(usuarios);
        }
        rs.close();
        st.close();
        return listaOnlines;
    }

    private Usuarios cursorToUsuarios(Cursor cursor) {
        Usuarios usuarios = new Usuarios();
        usuarios.setUsuario(cursor.getString(1));
        usuarios.setOnline(cursor.getString(2));
        return usuarios;
    }
}