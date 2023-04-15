/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tp2dpbo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author sitih
 */
public class formGrup extends javax.swing.JFrame {

    /**
     * Creates new form formGrup
     */
 
    
    private int id_grup;
    private String nama;
    private String agensi;
    private int jumlah_member;
    private String gambar;
    private Menu menu;
    HandlingImage imgHandler = new HandlingImage();
    private dbConnection db;
    
    // kontruktur saat ingin add data
    public formGrup(Menu menu) {
        initComponents();
        this.db = new dbConnection();
        this.menu =menu;
        setAddForm();
    }
    
    // kontruktur saat ingin update data
    public formGrup(int id, Menu menu) {
        initComponents();
        this.db = new dbConnection();
        this.id_grup = id;
        this.menu = menu;
        setUpdateForm();
    }

    // isi variabel atribut grup dari db
     public void setGrup(){
        try{
            String sql = "SELECT * FROM grup WHERE id_grup = '"+this.id_grup+"'";
            ResultSet res = db.selectQuery(sql);
            while(res.next()){
                this.id_grup = res.getInt("id_grup");
                this.nama = res.getString("nama");
                this.agensi = res.getString("agensi");
                this.jumlah_member = res.getInt("jmlh_member");
                this.gambar = res.getString("gambar");
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }   
    }
    
     // setting form untuk add data
    public void setAddForm(){
        judulLabel.setText("ADD GRUP");
        updateBtn.setVisible(false);   
    }
    
    // setting form untuk update data
    public void setUpdateForm(){
        setGrup();
        addBtn.setVisible(false);
        judulLabel.setText("Update GRUP");
        namaField.setText(this.nama);
        agensiField.setText(this.agensi);
        jmlhMemberField.setText(String.valueOf(this.jumlah_member));
        gambarField.setText(this.gambar);
    }
    

    public void addData(){
         
         // Get Data from Form
   
        nama = namaField.getText();
        agensi = agensiField.getText();
        jumlah_member = Integer.parseInt(jmlhMemberField.getText());
        
        if((nama != null) && (agensi != null) &&(jumlah_member != -1) && (imgHandler.getFile() != null)){
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            this.gambar = ft.format(dNow)+"_"+imgHandler.getFile().getName();
           
               imgHandler.uploadImg("Grup",this.gambar);
                String sql = "INSERT INTO `grup` ( `nama`, `agensi`,`jmlh_member`, `gambar`) VALUES ( '"+nama+"','"+agensi+"','"+jumlah_member+"','"+gambar+"')";
                db.updateQuery(sql);
                JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                this.menu.setPanel();
                
                this.dispose();           
        }else{
            JOptionPane.showMessageDialog(null, "Lengkapi Data Anda", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void updateData(){
         nama = namaField.getText();
         agensi = agensiField.getText();
        jumlah_member = Integer.parseInt(jmlhMemberField.getText());
        gambar = gambarField.getText();
        if((nama != null) && (agensi != null) && (jumlah_member != -1)){
            if(imgHandler.getFile()!=null){
                
                imgHandler.uploadImg("Grup",this.gambar);
            }
            int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin mengubah data ini ?", "Update Data", JOptionPane.YES_NO_OPTION);
            if (opsi == JOptionPane.YES_OPTION){
                 
                    String sql = "UPDATE `grup` SET nama = '"+nama+"', agensi = '"+agensi+"', jmlh_member = '"+jumlah_member+"', gambar = '"+this.gambar+"' WHERE id_grup = '"+id_grup+"'";
                    db.updateQuery(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    this.menu.setPanel();
                    this.dispose();
               
            }
        }else{
            JOptionPane.showMessageDialog(null, "Lengkapi Data Anda", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    public void clearForm(){
        namaField.setText("");
        agensiField.setText("");
        jmlhMemberField.setText("");
        gambarField.setText("");
        imgHandler = new HandlingImage();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        judulLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namaField = new javax.swing.JTextField();
        agensiField = new javax.swing.JTextField();
        browseImgBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jmlhMemberField = new javax.swing.JTextField();
        gambarField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(520, 200));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 300));

        judulLabel.setBackground(new java.awt.Color(102, 102, 255));
        judulLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        judulLabel.setForeground(new java.awt.Color(102, 102, 255));
        judulLabel.setText("ADD GRUP");

        jLabel2.setBackground(new java.awt.Color(102, 102, 255));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Nama");

        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Agensi");

        jLabel4.setBackground(new java.awt.Color(102, 102, 255));
        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Jumlah Member");

        jLabel5.setBackground(new java.awt.Color(102, 102, 255));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Gambar");

        namaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaFieldActionPerformed(evt);
            }
        });

        agensiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agensiFieldActionPerformed(evt);
            }
        });

        browseImgBtn.setBackground(new java.awt.Color(0, 153, 204));
        browseImgBtn.setFont(new java.awt.Font("Tw Cen MT", 1, 11)); // NOI18N
        browseImgBtn.setForeground(new java.awt.Color(255, 255, 255));
        browseImgBtn.setText("Browse");
        browseImgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseImgBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(153, 153, 255));
        addBtn.setFont(new java.awt.Font("Tw Cen MT", 1, 11)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 0, 0));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        resetBtn.setBackground(new java.awt.Color(153, 204, 255));
        resetBtn.setFont(new java.awt.Font("Tw Cen MT", 1, 11)); // NOI18N
        resetBtn.setForeground(new java.awt.Color(255, 255, 255));
        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        updateBtn.setBackground(new java.awt.Color(153, 153, 255));
        updateBtn.setFont(new java.awt.Font("Tw Cen MT", 1, 11)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(0, 0, 0));
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jmlhMemberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmlhMemberFieldActionPerformed(evt);
            }
        });

        gambarField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gambarFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(resetBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(judulLabel)
                                .addGap(127, 127, 127))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(updateBtn)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(48, 48, 48)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addBtn)
                                    .addComponent(browseImgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(agensiField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jmlhMemberField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(judulLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agensiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jmlhMemberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(browseImgBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn)
                    .addComponent(updateBtn)
                    .addComponent(addBtn))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void namaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaFieldActionPerformed

    private void agensiFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agensiFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agensiFieldActionPerformed

    private void browseImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImgBtnActionPerformed
              // TODO add your handling code here:
        imgHandler.Browse();
        gambarField.setText(imgHandler.getPath());
       
    }//GEN-LAST:event_browseImgBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addData();
    }//GEN-LAST:event_addBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_resetBtnActionPerformed

    
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
       updateData();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void jmlhMemberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmlhMemberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmlhMemberFieldActionPerformed

    private void gambarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gambarFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_gambarFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new formGrup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField agensiField;
    private javax.swing.JButton browseImgBtn;
    private javax.swing.JTextField gambarField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jmlhMemberField;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JTextField namaField;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
