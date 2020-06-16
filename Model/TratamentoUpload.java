
package Model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Caio Henrique
 */
public class TratamentoUpload {
   
    BufferedImage image;
    ManipularImagem manipularImagem = new ManipularImagem();
    File imagem;
    
    public BufferedImage selecionarImagem (JFileChooser fc , int res){
        

            if (res == JFileChooser.APPROVE_OPTION) {
                File arquivo = fc.getSelectedFile();
                String url = String.valueOf(arquivo);
                image = manipularImagem.setImagemDimensao(url);
                
            } else {
                JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
            }
            return image;
    }
    public byte[] getByteImagem(File imagem){
        boolean isPng = false;
        
        if(imagem != null){
            isPng = imagem.getName().endsWith("png");
            try{
            BufferedImage image  = ImageIO.read(imagem);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int type = BufferedImage.TYPE_INT_RGB;
                if(isPng){
                    type = BufferedImage.BITMASK;
                }
                byte[] byArray = out.toByteArray();
                return byArray;
            }catch(IOException e){
            
            }
        }
        return null;
    }
    
    
}
