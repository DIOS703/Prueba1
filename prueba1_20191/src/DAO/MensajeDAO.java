package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;

public class MensajeDAO {
    private conexion Con;
    private MensajeDAO mDAO;
    private UsuarioDAO uDAO;
    public MensajeDAO() {
        Con = new conexion();
    }
    public ArrayList<Mensaje> getmensajesporID(int id){
        mDAO = new MensajeDAO();
        uDAO = new UsuarioDAO();
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = Con.getConexion();
        String sql="SELECT * FROM mensaje WHERE id_usr_emisor = "+id+"" ;
        try{
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            while ( resultados.next() ) {
                int idreceptor = resultados.getInt("id_usr_receptor");
                String contenido = resultados.getString("contenido");
                int idmensaje = resultados.getInt("id_mensaje");
                Usuario emisor = uDAO.getbyID(id);
                Usuario receptor = uDAO.getbyID(idreceptor);
                mensajes.add(new Mensaje( idmensaje, emisor, receptor, contenido));}
            return mensajes;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener persona");
            e.printStackTrace();
            return null;
        }}
        public boolean insertarmensaje( int id_emisor, int id_receptor, String contenido){
        Connection accesoBD = Con.getConexion();
        try{
            String sql="INSERT INTO mensaje (contenido, id_usr_emisor, id_usr_receptor)" + " VALUES ('" +contenido+"',' "+id_emisor+" ','"+id_receptor+"')";
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al insertar persona");
            e.printStackTrace();
            return false;
        }}
}
