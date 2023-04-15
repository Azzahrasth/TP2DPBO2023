/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2dpbo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author sitih
 */
public class HandlingImage {
    private String path;
    private File file_img;
    private ImageIcon img;
    
    public HandlingImage(){
    }
    
    // browse img
    public void Browse()
    {
        JFileChooser file = new JFileChooser();
        //filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png", "jpeg");
        file.addChoosableFileFilter(filter);
        file.setAcceptAllFileFilterUsed(false);
        int res = file.showOpenDialog(null);
        //if the user clicks on save in Jfilechooser
        if(res == JFileChooser.APPROVE_OPTION){
          this.file_img = file.getSelectedFile();
          this.path =  file_img.getName();
        }
        
    }
     
    // resize img, sesuaiin sama ukuran label foto
    public void resize()
    {
      ImageIcon l_path = new ImageIcon(this.path);
      Image l_img = l_path.getImage();
      Image newImg = l_img.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(newImg);
      this.img = image;
    }
    
     public void uploadImg(String lokasi, String gambar){
         try {
            Path copied = Paths.get("src/gambar/"+lokasi+"/"+gambar);
            Path originalPath = Paths.get(getFile().getAbsolutePath());
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
          } catch (IOException e) {
              JOptionPane.showMessageDialog(null, e.getMessage());
              e.printStackTrace();
          }
    }
     
    public String getPath(){
        return this.path;
    }
    
    public File getFile(){
        return this.file_img;
    }
    
    public ImageIcon getImg(){
        return this.img;
    }
}

