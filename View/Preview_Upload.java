/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Form onde sera apresentada a imagem 
 * 
 * @author Caio Henrique
 */
public class Preview_Upload extends javax.swing.JPanel {
    private final BufferedImage imagem ;

    
    
    public Preview_Upload(BufferedImage imagem) throws SQLException, IOException {
        initComponents();
        this.imagem = imagem;
        this.setVisible(true);
    }
    
    
   
    
  
    
    //Desenhando a image recebida nesse painel.
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - this.imagem.getWidth()) / 2;
        int y = (this.getHeight() - this.imagem.getHeight()) / 2;
        
        
        
        g.drawImage(this.imagem, x,y ,(int)this.imagem.getWidth(),(int)this.imagem.getHeight(), this);
        
    }
    
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(760, 402));
        setLayout(new java.awt.GridLayout(1, 0));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
