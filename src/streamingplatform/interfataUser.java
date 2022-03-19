/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streamingplatform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import streamingConectDB.ConectareBD;

/**
 *
 * @author roxana
 */
public class interfataUser extends javax.swing.JFrame {

    boolean a = true;
    public interfataUser() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        extensie_profil.setVisible(false);
        meniu.setPreferredSize(new Dimension(50, meniu.getHeight()));
        
        scroll_bara_cautare_filme.setVisible(false);
        panel_plata.setVisible(false);
        mesaj_Scucces_plata.setVisible(false);
        
        jScrollPane_lista_mea.setVisible(false);
        panel_plata.setVisible(false);
        
        jScrollPane_istoric_plati.setVisible(false);
        tabel_setari(tabel_istoric_plati);
        
        
        add_filme_sb();
    }
    
    void tabel_setari(JTable t){
        t.getTableHeader().setFont((new Font("Tahoma", Font.PLAIN, 14)));
        t.getTableHeader().setOpaque(false);
        t.getTableHeader().setBackground(new Color(220,20,60));
        t.getTableHeader().setForeground(Color.WHITE);
        t.getRowHeight(30);
    }
    
    
    public void add_filme_sb(){
        all_scrollbar();
        
        try {
            Connection con = ConectareBD.bdConectare(); 
            Statement stm = con.createStatement();
            
            String select = "select numeFilm, gen from filme;";
            ResultSet rs = stm.executeQuery(select);
            
            BoxLayout box1 = new BoxLayout(scroll_f_aventura, BoxLayout.X_AXIS);
            scroll_f_aventura.setLayout(box1);
            BoxLayout box2 = new BoxLayout(scroll_f_actiune, BoxLayout.X_AXIS);
            scroll_f_actiune.setLayout(box2);
            BoxLayout box3 = new BoxLayout(scroll_f_sf, BoxLayout.X_AXIS);
            scroll_f_sf.setLayout(box3);
            BoxLayout box5 = new BoxLayout(scroll_f_drama, BoxLayout.X_AXIS);
            scroll_f_drama.setLayout(box5);
            BoxLayout box6 = new BoxLayout(scroll_f_istoric, BoxLayout.X_AXIS);
            scroll_f_istoric.setLayout(box6);
            BoxLayout box7 = new BoxLayout(scroll_f_biografic, BoxLayout.X_AXIS);
            scroll_f_biografic.setLayout(box7);
            BoxLayout box4 = new BoxLayout(scroll_f_comedie, BoxLayout.X_AXIS);
            scroll_f_comedie.setLayout(box4);
            
            while(rs.next()){
                int red, green, blue;
                red = (int)(Math.random() * 255);
                green = (int)(Math.random() * 255);
                blue = (int)(Math.random() * 255);
                Color c = new Color(red, green, blue);
                
                        
                JPanel panel = new JPanel();
                panel.setSize(new Dimension(130, 180));
                panel.setMaximumSize(new Dimension(130, 180));
                panel.setMaximumSize(new Dimension(130, 180));
                panel.setPreferredSize(new Dimension(130, 180));
                panel.setBorder(new LineBorder (new Color(52,40,44),2));
                panel.setLayout(new GridBagLayout());
                panel.setBackground(c);
                String nume_film = "<html>";
                nume_film += rs.getString("numeFilm");
                nume_film += "</html>";
                JLabel jl = new JLabel(nume_film,JLabel.CENTER);
                jl.setPreferredSize(new Dimension(90,60));
                jl.setFont(new Font("Tahoma", Font.PLAIN, 16));
                panel.add(jl);
                
                String gen_film = rs.getString("gen");
                if(gen_film.equals("Aventura"))
                    scroll_f_aventura.add(panel);
                if(gen_film.equals("Actiune"))
                    scroll_f_actiune.add(panel);
                else if(gen_film.equals("SF"))
                    scroll_f_sf.add(panel);
                else if(gen_film.equals("Drama"))
                    scroll_f_drama.add(panel);
                else if(gen_film.equals("Comedie"))
                    scroll_f_comedie.add(panel);
                else if(gen_film.equals("Istoric"))
                    scroll_f_istoric.add(panel);
                else if(gen_film.equals("Biografic"))
                    scroll_f_biografic.add(panel);
                 
            }
        } catch (Exception e) {
            System.out.print("Ceva nu a mers bine ");
        }
    }
    
    ScrollBarCustom scrollbar_h(ScrollBarCustom sp){
       sp = new ScrollBarCustom();
       sp.setOrientation(JScrollBar.HORIZONTAL);
       return sp;
    }
    
    public void all_scrollbar(){
       lista_filme.setVerticalScrollBar(new ScrollBarCustom());
       ScrollBarCustom sb_a = null, sb_av = null, sb_d = null, sb_i = null, sb_c = null, sb_sf = null, sb_b = null; 
       
       scroll_actiune.setHorizontalScrollBar(scrollbar_h(sb_a));
       scroll_aventura.setHorizontalScrollBar(scrollbar_h(sb_av));
       scroll_comedie.setHorizontalScrollBar(scrollbar_h(sb_c)); 
       scroll_drama.setHorizontalScrollBar(scrollbar_h(sb_d));
       scroll_istoric.setHorizontalScrollBar(scrollbar_h(sb_i));
       scroll_sf.setHorizontalScrollBar(scrollbar_h(sb_sf));
       scroll_biografic.setHorizontalScrollBar(scrollbar_h(sb_b));
       
       //add_filme_sb();
    }

    public void changecolor(Panel fundal, Color rand){
           fundal.setBackground(rand);
    } 
    
     public void hidemenu(JPanel ext_m, JPanel ext, boolean dashbord){
        if(dashbord == true){
          ext_m.setPreferredSize(new Dimension(250, ext_m.getHeight()));
          ext.setVisible(true);
          //ext_p.setVisible(true);
        }  
        else {
         ext_m.setPreferredSize(new Dimension(50, ext_m.getHeight()));
         ext.setVisible(false);
        //ext_p.setVisible(false);
        }    
    } 
   
    public void hidemenu2(JPanel ext_m, JPanel ext, JPanel ext_p, boolean dashbord){
        if(dashbord == true){
          ext_m.setPreferredSize(new Dimension(250, ext_m.getHeight()));
          ext.setVisible(true);
          ext_p.setVisible(true);
        }  
        else {
         ext_m.setPreferredSize(new Dimension(50, ext_m.getHeight()));
         ext.setVisible(false);
         ext_p.setVisible(false);
        }    
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
        meniu = new javax.swing.JPanel();
        shortMeniu = new java.awt.Panel();
        linieMeniu = new java.awt.Panel();
        butonMeniu = new java.awt.Panel();
        meniuImg = new javax.swing.JLabel();
        linieProfil = new java.awt.Panel();
        butonProfil = new java.awt.Panel();
        profilImg = new javax.swing.JLabel();
        extensie_meniu = new javax.swing.JPanel();
        extensie_profil = new javax.swing.JPanel();
        nume_utilizator = new javax.swing.JLabel();
        deconectare_utilizator = new javax.swing.JLabel();
        buton_acasa = new javax.swing.JButton();
        buton_plata = new javax.swing.JButton();
        buton_lista_mea = new javax.swing.JButton();
        dashbord = new javax.swing.JPanel();
        panou_info = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_plata = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        data_efectuare_plata = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mesaj_Scucces_plata = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane_istoric_plati = new javax.swing.JScrollPane();
        tabel_istoric_plati = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        label_nume_abonament = new javax.swing.JLabel();
        label_pret = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane_lista_mea = new javax.swing.JScrollPane();
        panel_lista_mea = new javax.swing.JPanel();
        lista_filme = new javax.swing.JScrollPane();
        scroll_panel = new javax.swing.JPanel();
        scroll_bara_cautare_filme = new javax.swing.JScrollPane();
        panel_bara_cautare_filme = new javax.swing.JPanel();
        l_actiune = new javax.swing.JLabel();
        l_sf = new javax.swing.JLabel();
        l_drama = new javax.swing.JLabel();
        l_comedie = new javax.swing.JLabel();
        l_istoric = new javax.swing.JLabel();
        l_biografic = new javax.swing.JLabel();
        l_aventura1 = new javax.swing.JLabel();
        scroll_biografic = new javax.swing.JScrollPane();
        scroll_f_biografic = new javax.swing.JPanel();
        scroll_istoric = new javax.swing.JScrollPane();
        scroll_f_istoric = new javax.swing.JPanel();
        scroll_comedie = new javax.swing.JScrollPane();
        scroll_f_comedie = new javax.swing.JPanel();
        scroll_drama = new javax.swing.JScrollPane();
        scroll_f_drama = new javax.swing.JPanel();
        scroll_sf = new javax.swing.JScrollPane();
        scroll_f_sf = new javax.swing.JPanel();
        scroll_actiune = new javax.swing.JScrollPane();
        scroll_f_actiune = new javax.swing.JPanel();
        scroll_aventura = new javax.swing.JScrollPane();
        scroll_f_aventura = new javax.swing.JPanel();
        bara_cautare_filme = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        baraXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        baraXMinMax.setPreferredSize(new java.awt.Dimension(900, 30));
        baraXMinMax.setLayout(new java.awt.BorderLayout());

        iconXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        iconXMinMax.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        butonX.setBackground(new java.awt.Color(0, 0, 0));
        butonX.setName("X"); // NOI18N
        butonX.setPreferredSize(new java.awt.Dimension(30, 30));
        butonX.setLayout(new java.awt.BorderLayout());

        Xmouse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Xmouse.setForeground(new java.awt.Color(102, 102, 102));
        Xmouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Xmouse.setText("X");
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
        butonX.add(Xmouse, java.awt.BorderLayout.CENTER);

        iconXMinMax.add(butonX, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        butonMax.setBackground(new java.awt.Color(0, 0, 0));
        butonMax.setPreferredSize(new java.awt.Dimension(30, 30));
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

        iconXMinMax.add(butonMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        butonMin.setBackground(new java.awt.Color(0, 0, 0));
        butonMin.setPreferredSize(new java.awt.Dimension(30, 30));
        butonMin.setLayout(new java.awt.BorderLayout());

        Minmouse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Minmouse.setForeground(new java.awt.Color(102, 102, 102));
        Minmouse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minmouse.setText("_");
        Minmouse.setToolTipText("");
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

        iconXMinMax.add(butonMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        baraXMinMax.add(iconXMinMax, java.awt.BorderLayout.EAST);

        getContentPane().add(baraXMinMax, java.awt.BorderLayout.PAGE_START);

        meniu.setBackground(new java.awt.Color(0, 0, 0));
        meniu.setPreferredSize(new java.awt.Dimension(250, 486));
        meniu.setLayout(new java.awt.BorderLayout());

        shortMeniu.setBackground(new java.awt.Color(220, 20, 60));
        shortMeniu.setPreferredSize(new java.awt.Dimension(50, 550));
        shortMeniu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        linieMeniu.setBackground(new java.awt.Color(220, 20, 60));
        linieMeniu.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout linieMeniuLayout = new javax.swing.GroupLayout(linieMeniu);
        linieMeniu.setLayout(linieMeniuLayout);
        linieMeniuLayout.setHorizontalGroup(
            linieMeniuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linieMeniuLayout.setVerticalGroup(
            linieMeniuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        shortMeniu.add(linieMeniu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 5));

        butonMeniu.setBackground(new java.awt.Color(220, 20, 60));
        butonMeniu.setLayout(new java.awt.BorderLayout());

        meniuImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        meniuImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/2867922_menu_icon.png"))); // NOI18N
        meniuImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        meniuImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meniuImgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                meniuImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                meniuImgMouseExited(evt);
            }
        });
        butonMeniu.add(meniuImg, java.awt.BorderLayout.CENTER);

        shortMeniu.add(butonMeniu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, 50));

        linieProfil.setBackground(new java.awt.Color(220, 20, 60));
        linieProfil.setPreferredSize(new java.awt.Dimension(50, 5));

        javax.swing.GroupLayout linieProfilLayout = new javax.swing.GroupLayout(linieProfil);
        linieProfil.setLayout(linieProfilLayout);
        linieProfilLayout.setHorizontalGroup(
            linieProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        linieProfilLayout.setVerticalGroup(
            linieProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        shortMeniu.add(linieProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 54, 50, 5));

        butonProfil.setBackground(new java.awt.Color(220, 20, 60));
        butonProfil.setLayout(new java.awt.BorderLayout());

        profilImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_32.png"))); // NOI18N
        profilImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        profilImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilImgMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilImgMouseExited(evt);
            }
        });
        butonProfil.add(profilImg, java.awt.BorderLayout.CENTER);

        shortMeniu.add(butonProfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 55, 50, 50));

        meniu.add(shortMeniu, java.awt.BorderLayout.LINE_START);

        extensie_meniu.setBackground(new java.awt.Color(0, 0, 0));
        extensie_meniu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        extensie_meniu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        extensie_profil.setBackground(new java.awt.Color(0, 0, 0));
        extensie_profil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        extensie_profil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nume_utilizator.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nume_utilizator.setForeground(new java.awt.Color(255, 255, 255));
        extensie_profil.add(nume_utilizator, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 90, 20));

        deconectare_utilizator.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deconectare_utilizator.setForeground(new java.awt.Color(255, 255, 255));
        deconectare_utilizator.setText("Deconectare");
        deconectare_utilizator.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconectare_utilizator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconectare_utilizatorMouseClicked(evt);
            }
        });
        extensie_profil.add(deconectare_utilizator, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        extensie_meniu.add(extensie_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 1250));

        buton_acasa.setBackground(new java.awt.Color(0, 0, 0));
        buton_acasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_acasa.setForeground(new java.awt.Color(255, 255, 255));
        buton_acasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/db_home24.png"))); // NOI18N
        buton_acasa.setText("ACASA");
        buton_acasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_acasa.setBorderPainted(false);
        buton_acasa.setContentAreaFilled(false);
        buton_acasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_acasa.setIconTextGap(10);
        buton_acasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_acasaActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_acasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 40));

        buton_plata.setBackground(new java.awt.Color(0, 0, 0));
        buton_plata.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_plata.setForeground(new java.awt.Color(255, 255, 255));
        buton_plata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/time-is-money.png"))); // NOI18N
        buton_plata.setText("PLATESTE");
        buton_plata.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_plata.setBorderPainted(false);
        buton_plata.setContentAreaFilled(false);
        buton_plata.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_plata.setIconTextGap(10);
        buton_plata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_plataActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_plata, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 150, 40));

        buton_lista_mea.setBackground(new java.awt.Color(0, 0, 0));
        buton_lista_mea.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_lista_mea.setForeground(new java.awt.Color(255, 255, 255));
        buton_lista_mea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/db_movie24.png"))); // NOI18N
        buton_lista_mea.setText("LISTA MEA");
        buton_lista_mea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_lista_mea.setBorderPainted(false);
        buton_lista_mea.setContentAreaFilled(false);
        buton_lista_mea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_lista_mea.setIconTextGap(10);
        buton_lista_mea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_lista_meaActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_lista_mea, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, 40));

        meniu.add(extensie_meniu, java.awt.BorderLayout.CENTER);

        getContentPane().add(meniu, java.awt.BorderLayout.LINE_START);

        dashbord.setBackground(new java.awt.Color(0, 0, 0));
        dashbord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panou_info.setBackground(new java.awt.Color(0, 0, 0));
        panou_info.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        panou_info.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bine ai venit!");
        panou_info.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 120, 60));

        dashbord.add(panou_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 0, 190, 900));

        panel_plata.setBackground(new java.awt.Color(0, 0, 0));
        panel_plata.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Plata Abonament");
        panel_plata.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 320, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Pentru a efectua plata abonamentului trebui selectata optiunea:");
        panel_plata.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 420, 40));

        jRadioButton1.setBackground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Da, vreau sa platesc");
        panel_plata.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Plata trebuie efectuata pe data");
        panel_plata.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        data_efectuare_plata.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        data_efectuare_plata.setForeground(new java.awt.Color(220, 20, 60));
        panel_plata.add(data_efectuare_plata, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 120, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Neefectuarea platii pana la data stabilita va intrerupe accesul  la continut.");
        panel_plata.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, -1, 30));

        mesaj_Scucces_plata.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        mesaj_Scucces_plata.setForeground(new java.awt.Color(220, 20, 60));
        mesaj_Scucces_plata.setText("Plata a fost realizata cu succes!");
        panel_plata.add(mesaj_Scucces_plata, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 650, -1, 20));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(220, 20, 60));
        jButton2.setText("Vizualizare Istoric Plati");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panel_plata.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 200, -1, -1));

        jScrollPane_istoric_plati.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane_istoric_plati.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tabel_istoric_plati.setBackground(new java.awt.Color(0, 0, 0));
        tabel_istoric_plati.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_istoric_plati.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_istoric_plati.setForeground(new java.awt.Color(255, 255, 255));
        tabel_istoric_plati.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data Platii", "Status"
            }
        ));
        tabel_istoric_plati.setGridColor(new java.awt.Color(0, 0, 0));
        tabel_istoric_plati.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tabel_istoric_plati.setSelectionForeground(new java.awt.Color(153, 153, 153));
        tabel_istoric_plati.setShowHorizontalLines(true);
        jScrollPane_istoric_plati.setViewportView(tabel_istoric_plati);

        panel_plata.add(jScrollPane_istoric_plati, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 250, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Abonament:");
        panel_plata.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 100, 30));

        label_nume_abonament.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_nume_abonament.setForeground(new java.awt.Color(220, 20, 60));
        panel_plata.add(label_nume_abonament, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 120, 50));

        label_pret.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_pret.setForeground(new java.awt.Color(220, 20, 60));
        panel_plata.add(label_pret, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 100, 50));

        jPanel1.setBackground(new java.awt.Color(220, 20, 60));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton1.setBackground(new java.awt.Color(220, 20, 60));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PLATESTE");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(jButton1, java.awt.BorderLayout.CENTER);

        panel_plata.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 120, 40));

        dashbord.add(panel_plata, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1260, 800));

        jScrollPane_lista_mea.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane_lista_mea.setBorder(null);
        jScrollPane_lista_mea.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane_lista_mea.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane_lista_mea.setAutoscrolls(true);

        javax.swing.GroupLayout panel_lista_meaLayout = new javax.swing.GroupLayout(panel_lista_mea);
        panel_lista_mea.setLayout(panel_lista_meaLayout);
        panel_lista_meaLayout.setHorizontalGroup(
            panel_lista_meaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        panel_lista_meaLayout.setVerticalGroup(
            panel_lista_meaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 839, Short.MAX_VALUE)
        );

        jScrollPane_lista_mea.setViewportView(panel_lista_mea);

        dashbord.add(jScrollPane_lista_mea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 820));

        lista_filme.setBackground(new java.awt.Color(255, 255, 255));
        lista_filme.setBorder(null);
        lista_filme.setForeground(new java.awt.Color(0, 0, 0));
        lista_filme.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        lista_filme.setToolTipText("\n");
        lista_filme.setAutoscrolls(true);

        scroll_panel.setBackground(new java.awt.Color(0, 0, 0));
        scroll_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll_bara_cautare_filme.setBackground(new java.awt.Color(51, 51, 51));
        scroll_bara_cautare_filme.setBorder(null);
        scroll_bara_cautare_filme.setForeground(new java.awt.Color(51, 51, 51));

        panel_bara_cautare_filme.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout panel_bara_cautare_filmeLayout = new javax.swing.GroupLayout(panel_bara_cautare_filme);
        panel_bara_cautare_filme.setLayout(panel_bara_cautare_filmeLayout);
        panel_bara_cautare_filmeLayout.setHorizontalGroup(
            panel_bara_cautare_filmeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1240, Short.MAX_VALUE)
        );
        panel_bara_cautare_filmeLayout.setVerticalGroup(
            panel_bara_cautare_filmeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );

        scroll_bara_cautare_filme.setViewportView(panel_bara_cautare_filme);

        scroll_panel.add(scroll_bara_cautare_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1240, 780));

        l_actiune.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_actiune.setForeground(new java.awt.Color(255, 255, 255));
        l_actiune.setText("Actiune");
        scroll_panel.add(l_actiune, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 70, -1));

        l_sf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_sf.setForeground(new java.awt.Color(255, 255, 255));
        l_sf.setText("SF");
        scroll_panel.add(l_sf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, -1, -1));

        l_drama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_drama.setForeground(new java.awt.Color(255, 255, 255));
        l_drama.setText("Drama");
        scroll_panel.add(l_drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 830, -1, -1));

        l_comedie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_comedie.setForeground(new java.awt.Color(255, 255, 255));
        l_comedie.setText("Comedie");
        scroll_panel.add(l_comedie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1070, -1, -1));

        l_istoric.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_istoric.setForeground(new java.awt.Color(255, 255, 255));
        l_istoric.setText("Istoric");
        scroll_panel.add(l_istoric, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1310, 100, 20));

        l_biografic.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_biografic.setForeground(new java.awt.Color(255, 255, 255));
        l_biografic.setText("Biografic");
        scroll_panel.add(l_biografic, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1550, -1, -1));

        l_aventura1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_aventura1.setForeground(new java.awt.Color(255, 255, 255));
        l_aventura1.setText("Aventura");
        scroll_panel.add(l_aventura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, 20));

        scroll_biografic.setBorder(null);
        scroll_biografic.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_biografic.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_biograficLayout = new javax.swing.GroupLayout(scroll_f_biografic);
        scroll_f_biografic.setLayout(scroll_f_biograficLayout);
        scroll_f_biograficLayout.setHorizontalGroup(
            scroll_f_biograficLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_biograficLayout.setVerticalGroup(
            scroll_f_biograficLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_biografic.setViewportView(scroll_f_biografic);

        scroll_panel.add(scroll_biografic, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1580, 1220, 180));

        scroll_istoric.setBorder(null);
        scroll_istoric.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_istoric.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_istoricLayout = new javax.swing.GroupLayout(scroll_f_istoric);
        scroll_f_istoric.setLayout(scroll_f_istoricLayout);
        scroll_f_istoricLayout.setHorizontalGroup(
            scroll_f_istoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_istoricLayout.setVerticalGroup(
            scroll_f_istoricLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_istoric.setViewportView(scroll_f_istoric);

        scroll_panel.add(scroll_istoric, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1340, 1220, 180));

        scroll_comedie.setBorder(null);
        scroll_comedie.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_comedie.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_comedieLayout = new javax.swing.GroupLayout(scroll_f_comedie);
        scroll_f_comedie.setLayout(scroll_f_comedieLayout);
        scroll_f_comedieLayout.setHorizontalGroup(
            scroll_f_comedieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_comedieLayout.setVerticalGroup(
            scroll_f_comedieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_comedie.setViewportView(scroll_f_comedie);

        scroll_panel.add(scroll_comedie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 1100, 1220, 180));

        scroll_drama.setBorder(null);
        scroll_drama.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_drama.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_dramaLayout = new javax.swing.GroupLayout(scroll_f_drama);
        scroll_f_drama.setLayout(scroll_f_dramaLayout);
        scroll_f_dramaLayout.setHorizontalGroup(
            scroll_f_dramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_dramaLayout.setVerticalGroup(
            scroll_f_dramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_drama.setViewportView(scroll_f_drama);

        scroll_panel.add(scroll_drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 860, 1220, 180));

        scroll_sf.setBorder(null);
        scroll_sf.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_sf.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_sfLayout = new javax.swing.GroupLayout(scroll_f_sf);
        scroll_f_sf.setLayout(scroll_f_sfLayout);
        scroll_f_sfLayout.setHorizontalGroup(
            scroll_f_sfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_sfLayout.setVerticalGroup(
            scroll_f_sfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_sf.setViewportView(scroll_f_sf);

        scroll_panel.add(scroll_sf, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 1220, 180));

        scroll_actiune.setBackground(new java.awt.Color(51, 51, 51));
        scroll_actiune.setBorder(null);
        scroll_actiune.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_actiune.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_actiuneLayout = new javax.swing.GroupLayout(scroll_f_actiune);
        scroll_f_actiune.setLayout(scroll_f_actiuneLayout);
        scroll_f_actiuneLayout.setHorizontalGroup(
            scroll_f_actiuneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_actiuneLayout.setVerticalGroup(
            scroll_f_actiuneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        scroll_actiune.setViewportView(scroll_f_actiune);

        scroll_panel.add(scroll_actiune, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 1220, 180));

        scroll_aventura.setBackground(new java.awt.Color(51, 51, 51));
        scroll_aventura.setBorder(null);
        scroll_aventura.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        scroll_f_aventura.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout scroll_f_aventuraLayout = new javax.swing.GroupLayout(scroll_f_aventura);
        scroll_f_aventura.setLayout(scroll_f_aventuraLayout);
        scroll_f_aventuraLayout.setHorizontalGroup(
            scroll_f_aventuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1220, Short.MAX_VALUE)
        );
        scroll_f_aventuraLayout.setVerticalGroup(
            scroll_f_aventuraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );

        scroll_aventura.setViewportView(scroll_f_aventura);

        scroll_panel.add(scroll_aventura, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1220, 180));

        bara_cautare_filme.setBackground(new java.awt.Color(255, 255, 255));
        bara_cautare_filme.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bara_cautare_filme.setForeground(new java.awt.Color(0, 0, 0));
        bara_cautare_filme.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        bara_cautare_filme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bara_cautare_filmeActionPerformed(evt);
            }
        });
        scroll_panel.add(bara_cautare_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 310, 30));

        lista_filme.setViewportView(scroll_panel);

        dashbord.add(lista_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 820));

        getContentPane().add(dashbord, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void XmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_XmouseMouseClicked

    private void XmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseEntered
        changecolor(butonX, new Color(128,0,0));
    }//GEN-LAST:event_XmouseMouseEntered

    private void XmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseExited
        changecolor(butonX, new Color(0,0,0));
    }//GEN-LAST:event_XmouseMouseExited

    private void MaxmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseClicked
        if (this.getExtendedState() != MAXIMIZED_BOTH){
            this.setExtendedState(MAXIMIZED_BOTH);
        }
        else this.setExtendedState(NORMAL);
    }//GEN-LAST:event_MaxmouseMouseClicked

    private void MaxmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseEntered
        changecolor(butonMax, new Color(128,0,0));
    }//GEN-LAST:event_MaxmouseMouseEntered

    private void MaxmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseExited
        changecolor(butonMax, new Color(0,0,0));
    }//GEN-LAST:event_MaxmouseMouseExited

    private void MinmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseClicked
        setState(ICONIFIED);
    }//GEN-LAST:event_MinmouseMouseClicked

    private void MinmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseEntered
        changecolor(butonMin, new Color(128,0,0));
    }//GEN-LAST:event_MinmouseMouseEntered

    private void MinmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseExited
        changecolor(butonMin, new Color(0,0,0));
    }//GEN-LAST:event_MinmouseMouseExited

    private void meniuImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meniuImgMouseClicked
        //clikmenu(butonMeniu, butonProfil, a);

        if(a == true){
            hidemenu(meniu, extensie_meniu, a);
            butonMeniu.setBackground(new Color(255, 95, 31 ));
            SwingUtilities.updateComponentTreeUI(this);
            a = false;
            all_scrollbar();
            lista_filme.setVisible(true);
        }
        else {
            hidemenu(meniu, extensie_meniu, a);
            butonMeniu.setBackground(new Color(220, 20, 60));
            SwingUtilities.updateComponentTreeUI(this);
            a = true;
            all_scrollbar();
            lista_filme.setVisible(true);
        }
    }//GEN-LAST:event_meniuImgMouseClicked

    private void meniuImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meniuImgMouseEntered
        changecolor(linieMeniu, new Color(255, 95, 31 ));
    }//GEN-LAST:event_meniuImgMouseEntered

    private void meniuImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meniuImgMouseExited
        changecolor(linieMeniu, new Color(220, 20, 60));
    }//GEN-LAST:event_meniuImgMouseExited

    private void profilImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilImgMouseClicked
        if(a == true){
            hidemenu2(meniu,extensie_meniu,extensie_profil, a);
            butonProfil.setBackground(new Color(255, 95, 31 ));
            SwingUtilities.updateComponentTreeUI(this);
            a = false;
            all_scrollbar();
            lista_filme.setVisible(true);
        }
        else {
            hidemenu2(meniu,extensie_meniu, extensie_profil,  a);
            butonProfil.setBackground(new Color(220, 20, 60));
            SwingUtilities.updateComponentTreeUI(this);
            a = true;
            all_scrollbar();
            lista_filme.setVisible(true);
        }
    }//GEN-LAST:event_profilImgMouseClicked

    private void profilImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilImgMouseEntered
        changecolor(linieProfil, new Color(255, 95, 31 ));
    }//GEN-LAST:event_profilImgMouseEntered

    private void profilImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilImgMouseExited
        changecolor(linieProfil, new Color(220, 20, 60));
    }//GEN-LAST:event_profilImgMouseExited

    private void deconectare_utilizatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconectare_utilizatorMouseClicked
        new login().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_deconectare_utilizatorMouseClicked

    private void buton_lista_meaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_lista_meaActionPerformed
        lista_filme.setVisible(false);
        panel_plata.setVisible(false);
        jScrollPane_lista_mea.setVisible(true);
    }//GEN-LAST:event_buton_lista_meaActionPerformed

    private void buton_plataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_plataActionPerformed
        lista_filme.setVisible(false);
        panel_plata.setVisible(true);
        jScrollPane_lista_mea.setVisible(false);
    }//GEN-LAST:event_buton_plataActionPerformed

    private void bara_cautare_filmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bara_cautare_filmeActionPerformed
        if(bara_cautare_filme.getText() != ""){
            scroll_bara_cautare_filme.setVerticalScrollBar(new ScrollBarCustom());
            scroll_bara_cautare_filme.setVisible(true);
        }
        else if(bara_cautare_filme.getText() == "") scroll_bara_cautare_filme.setVisible(false);
        
    }//GEN-LAST:event_bara_cautare_filmeActionPerformed

    private void buton_acasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_acasaActionPerformed
        all_scrollbar();
        lista_filme.setVisible(true);
        panel_plata.setVisible(false);
        jScrollPane_lista_mea.setVisible(false);
    }//GEN-LAST:event_buton_acasaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /*jScrollPane_istoric_plati.setVisible(true);
        
         String nume_r = del_r.getText();
       
       try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                String select = "select F.filmID from filme F left join regizor R on F.regizorID = R.regizorID where R.nume = '"+nume_r+"';";
                ResultSet rs = stm.executeQuery(select);
                
                int id_f = 0;
                
                while(rs.next()){
                    id_f = rs.getInt("filmID");
                    Statement stm1= con.createStatement();
                    stm1.execute("delete from actor_has_filme where filmID = "+id_f+" ");
                }
                
                select = "select regizorID from regizor where nume = '"+nume_r+"';";
                rs = stm.executeQuery(select);
                
                int id_r = 0;
                if(rs.next())
                    id_r = rs.getInt("regizorID");
                
                select = "delete from filme where regizorID = "+id_r+";";
                stm.execute(select);
                
                select = "delete from regizor where nume = '"+nume_r+"';";
                stm.execute(select);
                
                label_del_r.setVisible(false);
                label_del_r.setVisible(true);
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }*/
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfataUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Maxmouse;
    private javax.swing.JLabel Minmouse;
    private javax.swing.JLabel Xmouse;
    private java.awt.Panel baraXMinMax;
    private javax.swing.JTextField bara_cautare_filme;
    private java.awt.Panel butonMax;
    private java.awt.Panel butonMeniu;
    private java.awt.Panel butonMin;
    private java.awt.Panel butonProfil;
    private java.awt.Panel butonX;
    private javax.swing.JButton buton_acasa;
    private javax.swing.JButton buton_lista_mea;
    private javax.swing.JButton buton_plata;
    private javax.swing.JPanel dashbord;
    public static javax.swing.JLabel data_efectuare_plata;
    public static javax.swing.JLabel deconectare_utilizator;
    private javax.swing.JPanel extensie_meniu;
    private javax.swing.JPanel extensie_profil;
    private java.awt.Panel iconXMinMax;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane_istoric_plati;
    private javax.swing.JScrollPane jScrollPane_lista_mea;
    private javax.swing.JLabel l_actiune;
    private javax.swing.JLabel l_aventura1;
    private javax.swing.JLabel l_biografic;
    private javax.swing.JLabel l_comedie;
    private javax.swing.JLabel l_drama;
    private javax.swing.JLabel l_istoric;
    private javax.swing.JLabel l_sf;
    public static javax.swing.JLabel label_nume_abonament;
    public static javax.swing.JLabel label_pret;
    private java.awt.Panel linieMeniu;
    private java.awt.Panel linieProfil;
    private javax.swing.JScrollPane lista_filme;
    private javax.swing.JPanel meniu;
    private javax.swing.JLabel meniuImg;
    private javax.swing.JLabel mesaj_Scucces_plata;
    public static javax.swing.JLabel nume_utilizator;
    private javax.swing.JPanel panel_bara_cautare_filme;
    private javax.swing.JPanel panel_lista_mea;
    private javax.swing.JPanel panel_plata;
    private javax.swing.JPanel panou_info;
    private javax.swing.JLabel profilImg;
    private javax.swing.JScrollPane scroll_actiune;
    private javax.swing.JScrollPane scroll_aventura;
    private javax.swing.JScrollPane scroll_bara_cautare_filme;
    private javax.swing.JScrollPane scroll_biografic;
    private javax.swing.JScrollPane scroll_comedie;
    private javax.swing.JScrollPane scroll_drama;
    private javax.swing.JPanel scroll_f_actiune;
    private javax.swing.JPanel scroll_f_aventura;
    private javax.swing.JPanel scroll_f_biografic;
    private javax.swing.JPanel scroll_f_comedie;
    private javax.swing.JPanel scroll_f_drama;
    private javax.swing.JPanel scroll_f_istoric;
    private javax.swing.JPanel scroll_f_sf;
    private javax.swing.JScrollPane scroll_istoric;
    private javax.swing.JPanel scroll_panel;
    private javax.swing.JScrollPane scroll_sf;
    private java.awt.Panel shortMeniu;
    private javax.swing.JTable tabel_istoric_plati;
    // End of variables declaration//GEN-END:variables
}


