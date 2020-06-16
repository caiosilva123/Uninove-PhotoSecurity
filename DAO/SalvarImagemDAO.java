
package DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *Classe de conexão com banco de dados relacionado a IMAGEM.
 * @author Caio Henrique
 */
public class SalvarImagemDAO {
    
    /**
     * Recebendo Upload.
     * @param nomeCod   recebe nome do cod.
     * @param tipo      recebe a extensão da imagem.
     * @param imagem    recebe a imagem em Array de Byte.
     * @param cod       recebe o codigo de identificação da imagem.
     * @return 
     */
    public  static boolean salvandoImagem(String nome ,String nomecod,byte [] imagem, String cod){
        //try para tratar possiveis erros
        try {
            //Inicializando Drive de Conexão.
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Abrindo Conexão.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/imagens?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
            //Gerando comando SQL para ser executado no Banco de dados.
            PreparedStatement st = conn.prepareStatement("insert into img(nomeImg,descricao,img,codigo)values(?,?,?,?)");
            st.setString(1,nome);
            st.setString(2, nomecod);
            st.setBytes(3,imagem);
            st.setString(4, cod);
            //Executando o comando SQL.
            st.execute();
            //Fechando Conexão.
            conn.close();
            return true;
        }catch(ClassNotFoundException |SQLException b){
            JOptionPane.showMessageDialog(null,b);
        }
        return false;
    }
    //======================================Metodo Fora de uso============================
    public static byte[] recuperarImagem(int id ,String pasta) throws SQLException{
        byte[] imagem = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/banco_imagem?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
            PreparedStatement resultUsuario = conn.prepareStatement("select * from imagens where ID = ? and PASTA = ?");
            resultUsuario.setInt(1,id);
            resultUsuario.setString(2, pasta);
            ResultSet rsUsuario = resultUsuario.executeQuery();

            while(rsUsuario.next()) {
                Blob blob = rsUsuario.getBlob("img");
                imagem = blob.getBytes(1l, (int)blob.length());
            }
            return imagem;
        }catch(ClassNotFoundException a){}
        catch(SQLException e ){
            System.out.println("Erro no Banco");
        }
        return null;
    }
   
    
                    //Recuperando para apresentar na tela "Minhas Fotos


    /**
     * 
     * @param id        recebe o ID do Usuario.
     * @param idImagem  recebe ID da imagem.
     * @return          retorna a imagem.
     * @throws SQLException 
     */
    public static byte[] recuperarImagemUnica(int id ,int idImagem ) throws SQLException{
        byte[] imagem = null ;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/banco_imagem?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
        
            PreparedStatement resultUsuario = conn.prepareStatement("select * from imagens where ID = ? and IDIMAGEM = ?");
            resultUsuario.setInt(1,id);
            resultUsuario.setInt(2,idImagem);

            ResultSet rsUsuario = resultUsuario.executeQuery();

            while(rsUsuario.next()) {
                Blob blob = rsUsuario.getBlob("img");
                imagem = blob.getBytes(1l, (int)blob.length());
            }
            return imagem;
        }catch(ClassNotFoundException a){
        }catch(SQLException e ){
            System.out.println("Erro no Banco");
        }
        return null;
    }
    /**
     * 
     * @param id recebe ID do usuario.
     * @param pasta
     * @return retorna ArrayList com os ids das imagem.
     * @throws SQLException 
     */
    public static ArrayList<String> recuperarImagemIds(int id ,String pasta) throws SQLException{
        int count = 0;
        int[] ids;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/banco_imagem?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");

            PreparedStatement resultUsuario = conn.prepareStatement("select * from imagens where ID = ? and PASTA = ?");
            resultUsuario.setInt(1,id);
            resultUsuario.setString(2, pasta);
            ResultSet rsUsuario = resultUsuario.executeQuery();


            ResultSetMetaData metaDadosDoRs = rsUsuario.getMetaData();
            int numeroColunas = metaDadosDoRs.getColumnCount();
            ArrayList listinha = new ArrayList<String>(numeroColunas);

            while(rsUsuario.next()) {
                    listinha.add(rsUsuario.getString("idimagem"));
            }

            return listinha;
        }catch(ClassNotFoundException a){}
        catch(SQLException e ){
            System.out.println("Erro no Banco");
        }
        return null;
    }
    /**
     * Recuperando a descrição de cada imagem.
     * @param id        recebe ID do Usuario.
     * @param idImagem  recebe id da imagem.
     * @return          retorna a descrição
     * @throws SQLException 
     */
    public static String recuperarDescricaoImagem(int id ,String idImagem ) throws SQLException{
        String descricao = "";
        int idImage;
        idImage = Integer.parseInt(idImagem);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/banco_imagem?useSSL=false&useTimezone=true&serverTimezone=UTC","root","");
        
            PreparedStatement resultUsuario = conn.prepareStatement("select * from imagens where ID = ? and IDIMAGEM = ?");
            resultUsuario.setInt(1,id);
            resultUsuario.setInt(2,idImage);

            ResultSet rsUsuario = resultUsuario.executeQuery();

            while(rsUsuario.next()) {
                descricao = rsUsuario.getString("descricao");
                
            }
            return descricao;
        }catch(ClassNotFoundException a){
        }catch(SQLException e ){
            System.out.println("Erro no Banco");
        }
        return null;
    }
}
