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
public class formAlbum extends javax.swing.JFrame {

    /**
     * Creates new form addAlbum
     */
    
    private int id_album;
    private String judul;
    private Object grup;
    private int harga;
    private String gambar;
    private Menu menu;
    HandlingImage imgHandler = new HandlingImage();
    private dbConnection db;

    // class untuk mengambil nilai key (id_grup) dari value (nama grup) yang dipilih pada comboBox
    class comboBoxGrup{
      
        private int id_grup;
        private String nama; 

        public comboBoxGrup(int key, String value){
            this.id_grup = key;
            this.nama = value;
        }
        
        @Override
        // menampilkan nama grup pada combo box, tapi id grup yang dimasukan ke dalam db
        public String toString(){
            return nama;
        }
        public int getKey(){
            return id_grup;
        }
        
        public String getValue(){
            return nama;
        }
    }
    
    // kontruktur saat ingin add data
    public formAlbum(Menu menu) {
        initComponents();
        this.db = new dbConnection();
        this.menu = menu;
        setAddForm();
    }
    
    // kontruktur saat ingin update data
     public formAlbum(int id, Menu menu) {
        initComponents();
        this.db = new dbConnection();
        this.id_album = id;
        this.menu = menu;
        setUpdateForm();
    }
     
    // isi variabel atribut album dari db
    public void setAlbum(){
        try{
            String sql = "SELECT * FROM album WHERE id_album = '"+this.id_album+"'";
            ResultSet res = db.selectQuery(sql);
            while(res.next()){
                this.judul = res.getString("judul");
                this.harga = res.getInt("harga");
                this.gambar = res.getString("gambar");
            }            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
       
    // setting form untuk add data
     public void setAddForm(){
        judulLabel.setText("ADD ALBUM");
        updateBtn.setVisible(false);
        grupComboBox.removeAllItems();
        try{
            String sql = "SELECT id_grup, nama FROM grup";
            ResultSet res = db.selectQuery(sql);
            while(res.next()){
                grupComboBox.addItem(new comboBoxGrup(res.getInt("id_grup"),res.getString("nama")));
            }          
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    // setting form untuk update data
    public void setUpdateForm(){
        setAlbum();
        addBtn.setVisible(false);
        judulLabel.setText("UPDATE ALBUM");
        judulField.setText(this.judul);
        hargaField.setText(String.valueOf(this.harga));
        gambarField.setText(this.gambar);
        grupComboBox.removeAllItems();
        try{
            String sql = "SELECT id_grup, nama FROM grup";
            ResultSet res = db.selectQuery(sql);
            while(res.next()){
                grupComboBox.addItem(new comboBoxGrup(res.getInt("id_grup"),res.getString("nama")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void addData(){
        judul = judulField.getText();
        harga = Integer.parseInt(hargaField.getText());
        Object grup = grupComboBox.getSelectedItem();
         // Get Data from Form
        if((judul != null) &&(harga != -1) && (imgHandler.getFile() != null)){
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            this.gambar = ft.format(dNow)+"_"+imgHandler.getFile().getName();
                imgHandler.uploadImg("Album",this.gambar);
                String sql = "INSERT INTO `album` ( `id_grup`, `judul`,`harga`, `gambar`) VALUES ( '"+((comboBoxGrup)grup).getKey()+"','"+judul+"','"+harga+"','"+gambar+"')";
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
            // TODO add your handling code here:
        judul = judulField.getText();
        harga = Integer.parseInt(hargaField.getText());
        grup = grupComboBox.getSelectedItem();
        gambar = gambarField.getText();
        if((judul != null) &&(harga != -1) && (imgHandler.getFile() != null)){
         {
                imgHandler.uploadImg("Album",this.gambar);
            }
            int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin mengubah data ini ?", "Edit Data", JOptionPane.YES_NO_OPTION);
            if (opsi == JOptionPane.YES_OPTION){
                 
                    String sql = "UPDATE `album` SET judul = '"+judul+"', id_grup = '"+((comboBoxGrup)grup).getKey()+"', harga = '"+harga+"', gambar = '"+this.gambar+"' WHERE id_album = '"+id_album+"'";
                    db.updateQuery(sql);
                    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    this.menu.setPanel();
                    this.dispose();
               
            }
        }else{
            JOptionPane.showMessageDialog(null, "Lengkapi Form", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void clearForm(){
        judulField.setText("");
        hargaField.setText("");
        grupComboBox.setSelectedIndex(0);
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
        judulField = new javax.swing.JTextField();
        hargaField = new javax.swing.JTextField();
        browseImgBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        gambarField = new javax.swing.JTextField();
        grupComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusableWindowState(false);
        setLocation(new java.awt.Point(520, 200));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(360, 300));

        judulLabel.setBackground(new java.awt.Color(102, 102, 255));
        judulLabel.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        judulLabel.setForeground(new java.awt.Color(102, 102, 255));
        judulLabel.setText("ADD ALBUM");

        jLabel2.setBackground(new java.awt.Color(102, 102, 255));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 153));
        jLabel2.setText("Judul");

        jLabel3.setBackground(new java.awt.Color(102, 102, 255));
        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Harga");

        jLabel4.setBackground(new java.awt.Color(102, 102, 255));
        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Grup");

        jLabel5.setBackground(new java.awt.Color(102, 102, 255));
        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Gambar");

        judulField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                judulFieldActionPerformed(evt);
            }
        });

        hargaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaFieldActionPerformed(evt);
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(judulLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(resetBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(judulField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addBtn)
                                .addComponent(browseImgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(grupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(judulLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(judulField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hargaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(grupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(browseImgBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gambarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetBtn)
                    .addComponent(updateBtn)
                    .addComponent(addBtn))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void judulFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_judulFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_judulFieldActionPerformed

    private void hargaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaFieldActionPerformed

    private void gambarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gambarFieldActionPerformed

    }//GEN-LAST:event_gambarFieldActionPerformed

    private void browseImgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImgBtnActionPerformed
        // TODO add your handling code here:
        imgHandler.Browse();
        gambarField.setText(imgHandler.getPath());
    }//GEN-LAST:event_browseImgBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_resetBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        addData();
    }//GEN-LAST:event_addBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
       updateData();
    }//GEN-LAST:event_updateBtnActionPerformed

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
            java.util.logging.Logger.getLogger(formAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new formAlbum().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton browseImgBtn;
    private javax.swing.JTextField gambarField;
    private javax.swing.JComboBox<comboBoxGrup> grupComboBox;
    private javax.swing.JTextField hargaField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField judulField;
    private javax.swing.JLabel judulLabel;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
