
package Model;
import DAO.SalvarImagemDAO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/**
 *
 * @author Caio Henrique
 */
   
public class Usuario {
    BufferedImage imagem;    
    private static String nome ;
    private static String senha ;
    private static String email; 
    private static int id ;
    public static String descricao ;
    public static String pasta = "Nova Foto 2";

    public static int getId() {
        return id;
    }
    
    
    public static String getName(){
        
        return nome;
    
    }

    public static String getEmail() {
        return email;
    }
    
    public static boolean validarEmail(Object emailBD){
        if(emailBD != null){
            ResultSet result = (ResultSet) emailBD;
            try {
                if(result.next()){
                    Usuario.email = result.getString("email");
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    return false;
    }
    public static boolean validarUsuario(ResultSet usuarioBD ){
            
            if(usuarioBD != null){
                
                
                try {
                    if (usuarioBD.next()) {
                        
                        Usuario.nome  = usuarioBD.getString("nome");
                        Usuario.senha = usuarioBD.getString("senha");
                        Usuario.id = usuarioBD.getInt("id");
                        
                        
                        
                        return true;
                    }else{
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
            }
        return false;
        }
        
        public static String criptografandoSenha(String senha){
        
        String passwordToHash = senha;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            return generatedPassword;
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        
        
        
        return null;
    }
    public static boolean tratamentoEmail(String email){
        
        Usuario.email=email;
        int i;
        i = email.length();
        if(i >0){
           if(email.toLowerCase().contains("@hotmail.com")||email.toLowerCase().contains("@gmail.com")||email.toLowerCase().contains("@outlook.com")||email.toLowerCase().contains("@yahoo.com")||email.toLowerCase().contains("edu")||email.toLowerCase().contains("gov")){
                
               return true;  
                
            }
        }
        
        return false;
    }
    
    
    public boolean uploadImagem(JFileChooser fc,String nomeCod,String cod) throws IOException{
        // Inicializando uma Varialvel do Tipo BufferedImage 
        imagem = null;
        
        //Capturando a Extenção da Imagem 
        String tipo = fc.getTypeDescription(fc.getSelectedFile());
        
        //Capturando o nome da imagem
        String nome = fc.getSelectedFile().getName();
        
        //Lendo o Arquivo Selecionado
        imagem = ImageIO.read(fc.getSelectedFile());
        
			//OBTEM A IMAGEM E TRANSFORMA EM BYTES[]
        ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
	ImageIO.write((BufferedImage)imagem, "jpg", bytesImg);//seta a imagem para bytesImg
	bytesImg.flush();//limpa a variável
	byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
	bytesImg.close();//fecha a conversão
        
        /**Enviando para outra classe fazer a Inserção dos Bites no Banco de Dados, enviando "nomeDaImagem","nomeDoCodigo",
         * "A Imagem em Bite","Codigo Gerado"*/
        return SalvarImagemDAO.salvandoImagem(nome,nomeCod, byteArray,cod);
        
    }
    
    public boolean downloadImagem(String idImagem) throws SQLException, IOException{
        imagem = null;
        int idImage = Integer.parseInt(idImagem);
        JFileChooser fc = new JFileChooser();
        int escolha = fc.showSaveDialog(null);
        String imgNome ="";
        while(!imgNome.toLowerCase().contains(".jpg")||!imgNome.toLowerCase().contains(".png")){
            
            switch (escolha) {
            case JFileChooser.CANCEL_OPTION:
            System.out.println("Cancelado");
            return false;
            case JFileChooser.ERROR_OPTION:
            System.out.println("Error");
            break;
            case JFileChooser.APPROVE_OPTION:
                imgNome = fc.getSelectedFile().getName();
                String URL = fc.getCurrentDirectory().toString();
                if(imgNome.contains(".jpg")){
                    byte[] imgBytes = SalvarImagemDAO.recuperarImagemUnica(getId(), idImage);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
                    BufferedImage bImage2 = ImageIO.read(bis);
                    ImageIO.write(bImage2, "jpg", new File(URL+"\\"+imgNome) );
                }else if(imgNome.contains(".png")){
                    byte[] imgBytes = SalvarImagemDAO.recuperarImagemUnica(getId(), idImage);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
                    BufferedImage bImage2 = ImageIO.read(bis);
                    ImageIO.write(bImage2, "png", new File(URL+"\\"+imgNome) );
                }else{
                    imgNome += ".jpg";
                    byte[] imgBytes = SalvarImagemDAO.recuperarImagemUnica(getId(), idImage);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imgBytes);
                    BufferedImage bImage2 = ImageIO.read(bis);
                    ImageIO.write(bImage2, "png", new File(URL+"\\"+imgNome) );
                
                }
            }
            
          return true; 
        }
        return false;
     }
        
    
}
