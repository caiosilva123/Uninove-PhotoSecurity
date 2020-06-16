
package Control;

import View.TelaInicial;
import DAO.UsuarioDAO;
import Model.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class ControlUsuario {
   
    //Logando...    
    public static boolean logarUsuario(String usuario,String senha) throws SQLException, IOException{
        //Validaçoes 
        if(Usuario.validarUsuario(new UsuarioDAO().buscarCadastro(usuario, Usuario.criptografandoSenha(senha)))){
            new TelaInicial();
            return true;
        }
    return false;
    }
    
    //Registrando...
    public static boolean registrarUsuario(String usuario, String senha, String email){
        //Validações  
        if(Usuario.tratamentoEmail(email)){
            if(Usuario.validarUsuario(new UsuarioDAO().buscarUsuario(usuario))!= true){
                if(Usuario.validarEmail(new UsuarioDAO().buscarEmail(email))!=true){
                    if(new UsuarioDAO().cadastrarNovoUsuario(usuario, email, senha)){
                        return true;
                    }

                }else{
                JOptionPane.showMessageDialog(null, "Email já cadastrado !");

                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario já cadastrado !");

            }
        }else{
            JOptionPane.showMessageDialog(null, "Email Invalido, Por Favor Insira um email valido");
        }
        return false;
    }
}
