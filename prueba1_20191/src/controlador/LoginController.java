package controlador;


import DAO.MensajeDAO;
import DAO.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import vista.BandejaEntrada;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Mensaje;
import modelo.Usuario;
import vista.Login;
/**
 *
 * @author chelo
 */
public class LoginController implements ActionListener{
    
    private int idpersona;
    private Login vLogin;

    public LoginController(Login vLogin) {
        this.vLogin = vLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("ingresar")){
            
            String nombre= vLogin.getNombreTf().getText(); //nombre obtenido
            
            UsuarioDAO uDAO = new UsuarioDAO(); //No modificar
            
            ArrayList<Usuario>usuariosBD = uDAO.getUsuarios();
            boolean nombreExisteEnBD=false; 
            int id=-1;
            for(int i = 0; i < usuariosBD.size(); i++) {
                
               if(usuariosBD.get(i).getNombre().equals(nombre)){
                 nombreExisteEnBD=true;
                 id=usuariosBD.get(i).getId_usuario();
                 idpersona = id;
                 break;
               } 
               
            }
            
            if(nombreExisteEnBD){
                MensajeDAO mensaje = new MensajeDAO();
                ArrayList<Mensaje> mensajes = mensaje.getmensajesporID(idpersona);
                BandejaEntrada bandejaVentana= new BandejaEntrada(id);//sería el Id del usuario logueado
                bandejaVentana.setVisible(true);
                DefaultTableModel dtm = (DefaultTableModel) bandejaVentana.getjTable1().getModel();
                dtm.setRowCount(0);
                    for(int i=0; i<mensajes.size();i++){
                        Mensaje a = mensajes.get(i);
                        Object[] fila = {a.getEmisor().getNombre(),a.getContenido()};
                        dtm.addRow(fila);
        }
  
            }else{
                JOptionPane.showMessageDialog(vLogin, "Error el usuariono existe en la BD", "ERROR", JOptionPane.ERROR);
            }
        }
        
    } 
    
    
}