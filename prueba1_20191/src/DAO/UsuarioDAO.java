package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {
    private conexion Con;
    private UsuarioDAO pDAO;
    public UsuarioDAO() {
        Con = new conexion();
    }
    public Usuario getbyID(int id){
        Usuario p = null;
        Connection accesoBD = Con.getConexion();
        String sql="SELECT * FROM usuario WHERE id_usuario = "+id+"" ;
        pDAO = new UsuarioDAO();
        try{
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            if(resultados.first()){
                int idd = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                p = new Usuario(idd, nombre);
            return p;}
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener persona");
            e.printStackTrace();
            return null;
        }}
        public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> Usuario = new ArrayList<>();
        Connection accesoBD = Con.getConexion();
        try{
            String sql="SELECT * FROM usuario ";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            while ( resultados.next() ) {
                int id = resultados.getInt("id_usuario");
                String nombre = resultados.getString("nombre");
                Usuario.add(new Usuario(id, nombre));}
            return Usuario;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }}
        public boolean insertarPersona( String nombre){
        Connection accesoBD = Con.getConexion();
        try{
            String sql="INSERT INTO usuario (nombre)" + " VALUES ('"+nombre+"')";
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al insertar usuario");
            e.printStackTrace();
            return false;
        }}
        public boolean eliminarpersonabyid(int id){
        Connection accesoBD = Con.getConexion();
        try{
            String sql="DELETE FROM usuario WHERE id = "+id;
            Statement st = accesoBD.createStatement();
            st.executeUpdate(sql);
            return true;
        }catch (Exception e){
            System.out.println();
            System.out.println("Error al eliminar usuario");
            e.printStackTrace();
            return false;
        }}
}
