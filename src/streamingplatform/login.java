
package streamingplatform;

import java.sql.*;
import streamingConectDB.ConectareBD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
/**
 *
 * @author roxana
 */
public class login extends javax.swing.JFrame {
    
    public login() {
        initComponents();
        //this.setUndecorated(true);
        //this.setExtendedState(MAXIMIZED_BOTH);
        //this.setLocationRelativeTo(null);
        mesaj_da.setVisible(false);
        mesaj_nu.setVisible(false);
        email_parola_incorecta.setVisible(false);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        baraXMinMax = new java.awt.Panel();
        iconXMinMax = new java.awt.Panel();
        butonX = new java.awt.Panel();
        Xmouse = new javax.swing.JLabel();
        butonMax = new java.awt.Panel();
        Maxmouse = new javax.swing.JLabel();
        butonMin = new java.awt.Panel();
        Minmouse = new javax.swing.JLabel();
        backgroundMare = new java.awt.Panel();
        loginbox = new java.awt.Panel();
        loginLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        TextField = new javax.swing.JTextField();
        parolaLabel = new javax.swing.JLabel();
        PasswordField = new javax.swing.JPasswordField();
        conectare = new javax.swing.JPanel();
        butonconectare = new javax.swing.JButton();
        crearecont = new javax.swing.JPanel();
        butoncrearecont = new javax.swing.JButton();
        mesaj_nu = new javax.swing.JLabel();
        email_parola_incorecta = new javax.swing.JLabel();
        mesaj_da = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        baraXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        baraXMinMax.setPreferredSize(new java.awt.Dimension(900, 30));
        baraXMinMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                baraXMinMaxMousePressed(evt);
            }
        });
        baraXMinMax.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                baraXMinMaxMouseDragged(evt);
            }
        });
        baraXMinMax.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        iconXMinMax.setPreferredSize(new java.awt.Dimension(90, 30));
        iconXMinMax.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        butonX.setBackground(new java.awt.Color(0, 0, 0));
        butonX.setPreferredSize(new java.awt.Dimension(30, 30));
        butonX.setLayout(new java.awt.BorderLayout());

        Xmouse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Xmouse.setForeground(new java.awt.Color(102, 102, 102));
        Xmouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Xmouse.setText("X");
        Xmouse.setPreferredSize(new java.awt.Dimension(30, 30));
        Xmouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XmouseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                XmouseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                XmouseMouseExited(evt);
            }
        });
        butonX.add(Xmouse, java.awt.BorderLayout.EAST);

        iconXMinMax.add(butonX, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 30, 30));

        butonMax.setBackground(new java.awt.Color(0, 0, 0));
        butonMax.setLayout(new java.awt.BorderLayout());

        Maxmouse.setForeground(new java.awt.Color(102, 102, 102));
        Maxmouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Maxmouse.setText("⬜");
        Maxmouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaxmouseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaxmouseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaxmouseMouseExited(evt);
            }
        });
        butonMax.add(Maxmouse, java.awt.BorderLayout.CENTER);

        iconXMinMax.add(butonMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        butonMin.setBackground(new java.awt.Color(0, 0, 0));
        butonMin.setLayout(new java.awt.BorderLayout());

        Minmouse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Minmouse.setForeground(new java.awt.Color(102, 102, 102));
        Minmouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minmouse.setText("_");
        Minmouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MinmouseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MinmouseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MinmouseMouseExited(evt);
            }
        });
        butonMin.add(Minmouse, java.awt.BorderLayout.CENTER);

        iconXMinMax.add(butonMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        baraXMinMax.add(iconXMinMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(1031, 0, -1, -1));

        getContentPane().add(baraXMinMax, java.awt.BorderLayout.PAGE_START);

        backgroundMare.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginbox.setBackground(new java.awt.Color(0, 0, 0));
        loginbox.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loginLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(255, 0, 0));
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Login");
        loginbox.add(loginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 130, 40));

        emailLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("E-mail");
        loginbox.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        TextField.setBackground(new java.awt.Color(255, 255, 255));
        TextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TextField.setForeground(new java.awt.Color(0, 0, 0));
        TextField.setText("exemplu@gmail.com");
        TextField.setToolTipText("");
        TextField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TextField.setOpaque(true);
        TextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TextFieldFocusLost(evt);
            }
        });
        TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldActionPerformed(evt);
            }
        });
        loginbox.add(TextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 340, 40));

        parolaLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        parolaLabel.setForeground(new java.awt.Color(255, 255, 255));
        parolaLabel.setText("Parola");
        loginbox.add(parolaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 50, -1));

        PasswordField.setBackground(new java.awt.Color(255, 255, 255));
        PasswordField.setForeground(new java.awt.Color(0, 0, 0));
        PasswordField.setText("password");
        PasswordField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PasswordField.setOpaque(true);
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });
        loginbox.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 340, 40));

        conectare.setBackground(new java.awt.Color(220, 20, 60));
        conectare.setLayout(new java.awt.BorderLayout());

        butonconectare.setBackground(new java.awt.Color(220, 20, 60));
        butonconectare.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        butonconectare.setForeground(new java.awt.Color(255, 255, 255));
        butonconectare.setText("Conectare");
        butonconectare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        butonconectare.setBorderPainted(false);
        butonconectare.setContentAreaFilled(false);
        butonconectare.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonconectare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonconectareActionPerformed(evt);
            }
        });
        conectare.add(butonconectare, java.awt.BorderLayout.CENTER);

        loginbox.add(conectare, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 340, 50));

        crearecont.setBackground(new java.awt.Color(220, 20, 60));
        crearecont.setLayout(new java.awt.BorderLayout());

        butoncrearecont.setBackground(new java.awt.Color(220, 20, 60));
        butoncrearecont.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        butoncrearecont.setForeground(new java.awt.Color(0, 0, 0));
        butoncrearecont.setText("Creare Cont ");
        butoncrearecont.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        butoncrearecont.setBorderPainted(false);
        butoncrearecont.setContentAreaFilled(false);
        butoncrearecont.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butoncrearecont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butoncrearecontActionPerformed(evt);
            }
        });
        crearecont.add(butoncrearecont, java.awt.BorderLayout.CENTER);

        loginbox.add(crearecont, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 340, 50));

        mesaj_nu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_nu.setForeground(new java.awt.Color(255, 255, 255));
        mesaj_nu.setText("Plata nu a fost efectuata. Inregistreaza-te din nou.");
        loginbox.add(mesaj_nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 20));

        email_parola_incorecta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email_parola_incorecta.setForeground(new java.awt.Color(255, 255, 255));
        email_parola_incorecta.setText("E-mail sau parola incorecta.");
        loginbox.add(email_parola_incorecta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 20));

        mesaj_da.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_da.setForeground(new java.awt.Color(255, 255, 255));
        mesaj_da.setText("Inregistrarea s-a facut cu succes. Multumim!");
        loginbox.add(mesaj_da, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 20));

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jTextField1.setBorder(null);
        loginbox.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 30, 20));

        backgroundMare.add(loginbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 380, 450));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/m9bb.jpg"))); // NOI18N
        backgroundMare.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, -80, -1, -1));

        getContentPane().add(backgroundMare, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1121, 776);
    }// </editor-fold>//GEN-END:initComponents

    
    public void changecolor(Panel fundal, Color rand){
           fundal.setBackground(rand);
    } 
    
    private void XmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseEntered
        changecolor(butonX, new Color(128,0,0));
    }//GEN-LAST:event_XmouseMouseEntered

    private void XmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseExited
        changecolor(butonX, new Color(0,0,0));
    }//GEN-LAST:event_XmouseMouseExited

    private void XmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseClicked
        System.exit(0); // buton inchidere aplicatie
    }//GEN-LAST:event_XmouseMouseClicked

    private void MaxmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseEntered
        changecolor(butonMax, new Color(128,0,0));
    }//GEN-LAST:event_MaxmouseMouseEntered

    private void MaxmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseExited
        changecolor(butonMax, new Color(0,0,0));
    }//GEN-LAST:event_MaxmouseMouseExited

    private void MaxmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseClicked
         if (this.getExtendedState() != MAXIMIZED_BOTH){ // buton de micsorare/marire fereastra
            this.setExtendedState(MAXIMIZED_BOTH);
        }
         else {this.setExtendedState(NORMAL);
         }
    }//GEN-LAST:event_MaxmouseMouseClicked

    private void MinmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseEntered
        changecolor(butonMin, new Color(128,0,0));
    }//GEN-LAST:event_MinmouseMouseEntered

    private void MinmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseExited
        changecolor(butonMin, new Color(0,0,0));
    }//GEN-LAST:event_MinmouseMouseExited

    private void MinmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseClicked
        setState(ICONIFIED); // buton pentru pune aplicatia in bara
    }//GEN-LAST:event_MinmouseMouseClicked

    private void TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldActionPerformed

    private void butonconectareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonconectareActionPerformed
        // buton de conectare in aplicatie
        
        String user = TextField.getText(); // se introduce nume utilizaor
        String pass = String.valueOf(PasswordField.getPassword()); // se introduce parola utilizator
        
        if(user.isEmpty() || pass.isEmpty() || user == "exemplu@gmail.com" || pass == "password")
            email_parola_incorecta.setVisible(true);
        else{
            try {
                Connection con = ConectareBD.bdConectare(); // se face conectarea cu baza de data
                Statement stm = con.createStatement();
                Statement stmU = con.createStatement();
                
                // se cauta in baza de date utilizatorul introdus si se verifica
                String select = "select * from administrator where numeAdmin='"+user+"' and parolaAdmin='"+pass+"';"; 
                String select_u = "select * from utilizator where email='"+user+"' and parola='"+pass+"';";
                ResultSet rs = stm.executeQuery(select);
                ResultSet rs_u = stmU.executeQuery(select_u);
                        
                if(rs.next()){
                    new meniu().setVisible(true); // se deschide fereastra pentru administrator
                    meniu.nume_admin.setText(rs.getString(2));
                    setVisible(false);
                    System.out.println("Success");    
                }
                else if(rs_u.next()){ // se deschide fereastra pentru utilizator
                    new interfataUser().setVisible(true);
                    //String nume_u = rs_u.getString(3);
                    //String prenume_u = rs_u.getString(4);
                    interfataUser.nume_utilizator.setText(rs_u.getString(4));
                    
                    // mai jos sunt cautate informatii despre abonamentul utilizatrului
                    select_u = "select abonamentID, utilizatorID from utilizator where email = '"+user+"';";
                    rs_u = stmU.executeQuery(select_u);
                    
                    int id_u = 0, id = 0;
                    if(rs_u.next()){
                        id_u = rs_u.getInt("abonamentID");
                        id = rs_u.getInt("utilizatorID");
                    }
                    
                    select_u = "select tipAbonament, plataLunara from abonament where abonamentID = "+id_u+";";
                    rs_u = stmU.executeQuery(select_u);
                    
                    if(rs_u.next()){
                        interfataUser.label_nume_abonament.setText(rs_u.getString("tipAbonament"));
                        interfataUser.label_pret.setText(rs_u.getString("plataLunara"));
                    }
                    
                    select_u = "select max(dataPlatii) UltimaData from plati where utilizatorID = "+id+" and status = 'platit';";
                    rs_u = stmU.executeQuery(select_u);
                   
                    if(rs_u.next())
                        interfataUser.data_efectuare_plata.setText(rs_u.getString("UltimaData"));
                    
                    setVisible(false);
                    System.out.println("Success");
                }
                else{
                    System.out.println("Failure");
                    email_parola_incorecta.setVisible(true);
                }
                con.close();
            } 
            catch (Exception e) { // mesaj ce apare in cazul in care ceva nu merge bine
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
        }
        
        /*if(TextField.getText().equals("admin") && PasswordField.getText().equals("parola")){
            new meniu().setVisible(true);
            setVisible(false);
        }
        else {
            email_parola_incorecta.setVisible(true);
        }*/
    }//GEN-LAST:event_butonconectareActionPerformed

    private void butoncrearecontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butoncrearecontActionPerformed
        new inregistrare().setVisible(true); //  buton care duce la inregistrarea de utilizator
        setVisible(false);
    }//GEN-LAST:event_butoncrearecontActionPerformed

    
    // mai jos sunt lucruri legate mai mult de interfata cu utilizatorul
    private void TextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldFocusGained
        String numeVal = TextField.getText(); // in momentul cand se apasa pe casuta text de introducere utilizator ti se permite sa introduci un nou text
        
        if(numeVal.equals("exemplu@gmail.com") || numeVal.equals(""))
            TextField.setText("");
        else TextField.setText(numeVal);
    }//GEN-LAST:event_TextFieldFocusGained

    private void PasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusGained
        String numeVal = String.valueOf(PasswordField.getPassword());; // in momentul cand se apasa pe casuta text de introducere parola ti se permite sa introduci un nou text
        
        if(numeVal.equals("password") || numeVal.equals("")) 
            PasswordField.setText("");
        else PasswordField.setText(numeVal);
    }//GEN-LAST:event_PasswordFieldFocusGained

    private void TextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextFieldFocusLost
        String numeVal = TextField.getText();
        
        if(numeVal.equals("exemplu@gmail.com") || numeVal.equals("")) // daca nu este completata cu ceva se revine la textul initial
            TextField.setText("exemplu@gmail.com");
        else TextField.setText(numeVal);
    }//GEN-LAST:event_TextFieldFocusLost

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost
       String numeVal = String.valueOf(PasswordField.getPassword()); // daca nu este completata cu ceva se revine la textul initial
        
        if(numeVal.equals("password") || numeVal.equals(""))
            PasswordField.setText("password");
        else PasswordField.setText(numeVal);
    }//GEN-LAST:event_PasswordFieldFocusLost

    int xMouse, yMouse;
    private void baraXMinMaxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baraXMinMaxMousePressed
       xMouse = evt.getX();
       yMouse = evt.getY();
    }//GEN-LAST:event_baraXMinMaxMousePressed

    
    private void baraXMinMaxMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_baraXMinMaxMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_baraXMinMaxMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Maxmouse;
    private javax.swing.JLabel Minmouse;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JTextField TextField;
    private javax.swing.JLabel Xmouse;
    private javax.swing.JLabel background;
    private java.awt.Panel backgroundMare;
    public static java.awt.Panel baraXMinMax;
    private java.awt.Panel butonMax;
    private java.awt.Panel butonMin;
    private java.awt.Panel butonX;
    private javax.swing.JButton butonconectare;
    private javax.swing.JButton butoncrearecont;
    private javax.swing.JPanel conectare;
    private javax.swing.JPanel crearecont;
    private javax.swing.JLabel emailLabel;
    public static javax.swing.JLabel email_parola_incorecta;
    private java.awt.Panel iconXMinMax;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel loginLabel;
    private java.awt.Panel loginbox;
    public static javax.swing.JLabel mesaj_da;
    public static javax.swing.JLabel mesaj_nu;
    private javax.swing.JLabel parolaLabel;
    // End of variables declaration//GEN-END:variables
}
