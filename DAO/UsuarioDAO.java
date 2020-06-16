
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Caio Henrique
 */
public class UsuarioDAO {
    private String usuario;
    private String senha;
    private String email;
    
    

    
    
    /**
     * 
     * @param usuario 
     * @param senha
     * @return 
     */
    public ResultSet buscarCadastro(String usuario,String senha){
        this.usuario = usuario;
        this.senha= senha;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/cadastros?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
            PreparedStatement consult = conn.prepareStatement("select * from usuarios where nome = ? and senha = ?");
            consult.setString(1,this.usuario);
            consult.setString(2,this.senha);
            ResultSet result = consult.executeQuery();

            return result ;
        }catch(SQLException a) {
            
        }catch(ClassNotFoundException b ){
        
        }
        
    return null;   
    }
    
    /**
     * 
     * @param email
     * @return 
     */
    public Object buscarEmail(String email){
        this.email = email;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/cadastros?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");

            PreparedStatement resultEmail = conn.prepareStatement("select * from usuarios where email = ?");
            resultEmail.setString(1, this.email);
            ResultSet rsEmail = resultEmail.executeQuery();
            return rsEmail;
        }catch(ClassNotFoundException a ){
            
        }catch(SQLException e ){}
    
        return null;
    }
    /**
     * 
     * @param usuario
     * @return 
     */
    public ResultSet buscarUsuario(String usuario){
        this.email = usuario;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/cadastros?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");

            PreparedStatement resultUsuario = conn.prepareStatement("select * from usuarios where nome = ?");
            resultUsuario.setString(1, this.email);
            ResultSet rsUsuario = resultUsuario.executeQuery();
            return rsUsuario;
        }catch(ClassNotFoundException |SQLException e ){
            JOptionPane.showMessageDialog(null, e);
        }
    
        return null;
    }
    
    
    /**
     * 
     * @param nome
     * @param email
     * @param senha
     * @return 
     */
    public boolean cadastrarNovoUsuario (String nome, String email,String senha){
    this.usuario = nome;
    this.email = email;
    this.senha = senha;
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/cadastros?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
        PreparedStatement st = conn.prepareStatement("insert into usuarios(nome,senha,email)values(?,MD5(?),?)");
        st.setString(1,this.usuario);
        st.setString(2, this.senha);
        st.setString(3, this.email);
        st.executeUpdate();
        conn.close();
        return true;
        
    }catch(SQLException | ClassNotFoundException ex){
        JOptionPane.showMessageDialog(null,ex);
    }
    
    return false ;
    }
    public boolean validarCodigo(String nome){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/imagens?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");

            PreparedStatement resultUsuario = conn.prepareStatement("select * from img where descricao = ?");
            resultUsuario.setString(1, nome);
            ResultSet rsUsuario = resultUsuario.executeQuery();
            return !rsUsuario.next();
        }catch(ClassNotFoundException |SQLException e ){
            JOptionPane.showMessageDialog(null, e);
        }
    
        return false;
    }
   
}

