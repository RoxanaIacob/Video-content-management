
package streamingplatform;

import java.sql.*;
import streamingConectDB.ConectareBD;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;



/**
 *
 * @author roxana
 */
public class meniu extends javax.swing.JFrame {
   
    boolean a = true;
    int ok = 0;
    DefaultListModel defaultListModel = new DefaultListModel();
    
    public meniu() { // aceasta functie imi permite sa setez unele lucruri de la inceput: cum ar fi ca anumite ferestre, mesaje, tabele sa fie invizibile,
                                                                        // caracteristicile de design a unor tabele, combo box-uri
       initComponents();
       setExtendedState(MAXIMIZED_BOTH);
       //extensie_meniu.setVisible(false);
       extensie_profil.setVisible(false);
       meniu.setPreferredSize(new Dimension(50, meniu.getHeight()));
       scroll_add_film.setVisible(false);
       scroll_date_utilizator.setVisible(false);
       date_abonamente.setVisible(false);
       scroll_date_film.setVisible(false);
       
       ComboBoxR();
       AutoCompleteDecorator.decorate(cb_regizor);
       ComboBoxA();
       AutoCompleteDecorator.decorate(cb_a1);
       AutoCompleteDecorator.decorate(cb_a2);
       AutoCompleteDecorator.decorate(cb_a3);
       mesaj_err_ra.setVisible(false);
       mesaj_err_f.setVisible(false);
       ad_regizor_actor.setVisible(false);
       mesaj_err_R.setVisible(false);
       mesaj_err_A.setVisible(false);
       mesaj_modificare_succes.setVisible(false);
       mesaj_modificare_actor.setVisible(false);
       mesaj_modificare_film.setVisible(false);
       mesaj_modificare_regizor.setVisible(false);
       
       //update_table_abonamente();
       //update_table_filme();
       
       tabel_setari(tabel_abonament);
       tabel_setari(tabel_au);
       tabel_setari(tabel_utilizator);
       tabel_setari(tabel_gen_u);
       tabel_setari(tabel_filme_recomandate);
       tabel_setari(tabel_filme_vizionate);
       tabel_setari(tabel_filme);
       tabel_setari(tabel_actori);
       tabel_setari(tabel_regizori);
       tabel_setari(tabel_filme_cr);
       tabel_setari(tabel_filme_ca);
       tabel_setari(tabel_cr_actori);
       tabel_setari(tabel_filme1);
       tabel_setari(tabel_actori1);
       tabel_setari(tabel_regizori1);
       tabel_setari(tabel_restantieri);
        tabel_setari(tabel_filme_populare);
       
       jScrollPane_actori.setVisible(false);
       jScrollPane_filme.setVisible(false);
       jScrollPane_regizori.setVisible(false);
       jScrollPane_actori1.setVisible(false);
       jScrollPane_filme1.setVisible(false);
       jScrollPane_regizori1.setVisible(false);
       
       
       add_filme_sb(); // aceasta functie imi genereaza listele de filme de pe pagina principala
       
       bindData(myList_r, "regizor"); // aceasta functie imi genereaza liste de cautare in functie de ce am nevoie sa caut
       bindData(myList_a, "actor");
       bindData(myList_f, "filme");
       bindData(myList_dr, "regizor");
       bindData(myList_da, "actor");
       bindData(myList_df, "filme");
       
       scroll_mylist_r.setVisible(false);
       scroll_mylist_a.setVisible(false);
       scroll_mylist_f.setVisible(false);
       scroll_mylist_df.setVisible(false);
       scroll_mylist_da.setVisible(false);
       scroll_mylist_dr.setVisible(false);
       scroll_cr_actori.setVisible(false);
       jScrollPane_lista_cautare_filme.setVisible(false);
       
       label_del_a.setVisible(false);
       label_del_f.setVisible(false);
       label_del_r.setVisible(false);
       
       updateCombox();
       update_table_utilizatori();
    }
    
    public void add_filme_sb(){  // aceasta functie imi genereaza listele de filme de pe pagina principala
        all_scrollbar();
        
        try {
            // ma conectez la baza de date
            Connection con = ConectareBD.bdConectare(); 
            Statement stm = con.createStatement();
            
            // selectez numele si genul filmului din data de baze
            String select = "select numeFilm, gen from filme;";
            ResultSet rs = stm.executeQuery(select);
            
            //  creez cate o lista pentru fiecare categorie de film
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
                int red, green, blue; // mi se genereaza o culoare random pentru fiecare film
                red = (int)(Math.random() * 255);
                green = (int)(Math.random() * 255);
                blue = (int)(Math.random() * 255);
                Color c = new Color(red, green, blue);
                
                        
                JPanel panel = new JPanel(); // creez pentru fiecre film un "poster"
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
                
                // adaug filmele in categoria din care fac parte
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
    
    ScrollBarCustom scrollbar_h(ScrollBarCustom sp){ // functie care sa-mi genereze bara de scroll pe orizontala
       sp = new ScrollBarCustom();
       sp.setOrientation(JScrollBar.HORIZONTAL);
       return sp;
    }
    
    public void all_scrollbar(){ // functie design pentru scroll bar din pagina principala
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
       
    void tabel_setari(JTable t){ // functie design pentru tabelele create in aplicatie
        t.getTableHeader().setFont((new Font("Tahoma", Font.PLAIN, 12)));
        t.getTableHeader().setOpaque(false);
        t.getTableHeader().setBackground(new Color(52,40,44));
        t.getTableHeader().setForeground(Color.WHITE);
        t.getRowHeight(30);
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
        nume_admin = new javax.swing.JLabel();
        deconectareA = new javax.swing.JLabel();
        buton_acasa = new javax.swing.JButton();
        buton_filme = new javax.swing.JButton();
        buton_utilizatori = new javax.swing.JButton();
        buton_setari = new javax.swing.JButton();
        buton_abonamente = new javax.swing.JButton();
        dashbord = new javax.swing.JPanel();
        scroll_add_film = new javax.swing.JScrollPane();
        add_date_film = new javax.swing.JPanel();
        label_ad_film = new javax.swing.JLabel();
        ad_regizor_actor = new javax.swing.JPanel();
        label_regizor = new javax.swing.JLabel();
        numeregizor = new javax.swing.JTextField();
        sep_numeregizor = new javax.swing.JSeparator();
        nationalitateregizor = new javax.swing.JTextField();
        sep_nationalitateregizor = new javax.swing.JSeparator();
        nrpremii = new javax.swing.JTextField();
        sep_nrpremii = new javax.swing.JSeparator();
        mesaj_err_R = new javax.swing.JLabel();
        background_addR = new javax.swing.JPanel();
        b_addR = new javax.swing.JButton();
        label_actor = new javax.swing.JLabel();
        numeactor = new javax.swing.JTextField();
        sep_numeactor = new javax.swing.JSeparator();
        nationalitateactor = new javax.swing.JTextField();
        sep_nationalitateactor = new javax.swing.JSeparator();
        rol_memorabil = new javax.swing.JTextField();
        sep_rol_memorabil = new javax.swing.JSeparator();
        mesaj_err_A = new javax.swing.JLabel();
        background_addA = new javax.swing.JPanel();
        b_addA = new javax.swing.JButton();
        bX_ra = new javax.swing.JButton();
        add_fillm = new javax.swing.JPanel();
        numefilm = new javax.swing.JTextField();
        sem_numefilm = new javax.swing.JSeparator();
        anaparitie = new javax.swing.JTextField();
        sep_anaparitie = new javax.swing.JSeparator();
        gen = new javax.swing.JTextField();
        sep_gen = new javax.swing.JSeparator();
        tara = new javax.swing.JTextField();
        sep_tara = new javax.swing.JSeparator();
        descriere = new javax.swing.JTextField();
        sep_descriere = new javax.swing.JSeparator();
        duratafilm = new javax.swing.JTextField();
        sep_duratafilm = new javax.swing.JSeparator();
        cb_a1 = new javax.swing.JComboBox<>();
        rf_nume = new javax.swing.JLabel();
        actor2_nume = new javax.swing.JLabel();
        actor3_nume = new javax.swing.JLabel();
        actor1_nume = new javax.swing.JLabel();
        cb_a2 = new javax.swing.JComboBox<>();
        cb_a3 = new javax.swing.JComboBox<>();
        cb_regizor = new javax.swing.JComboBox<>();
        background_addfilm = new javax.swing.JPanel();
        b_ad_film = new javax.swing.JButton();
        mesaj_err_ra = new javax.swing.JLabel();
        background_addra = new javax.swing.JPanel();
        b_ad_ra = new javax.swing.JButton();
        mesaj_err_f = new javax.swing.JLabel();
        panel_rt1 = new javax.swing.JPanel();
        bara_panel1 = new javax.swing.JPanel();
        optiuni_tabel1 = new javax.swing.JPanel();
        refresh_panel1 = new javax.swing.JPanel();
        b_refresh1 = new javax.swing.JButton();
        filme_panel1 = new javax.swing.JPanel();
        b_viz_filme1 = new javax.swing.JButton();
        regizor_panel1 = new javax.swing.JPanel();
        b_viz_regizori1 = new javax.swing.JButton();
        actor_panel1 = new javax.swing.JPanel();
        b_viz_actori1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        tabel_panel1 = new javax.swing.JPanel();
        jScrollPane_actori1 = new javax.swing.JScrollPane();
        tabel_actori1 = new javax.swing.JTable();
        jScrollPane_regizori1 = new javax.swing.JScrollPane();
        tabel_regizori1 = new javax.swing.JTable();
        jScrollPane_filme1 = new javax.swing.JScrollPane();
        tabel_filme1 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        del_f = new javax.swing.JTextField();
        b_del_f = new javax.swing.JButton();
        scroll_mylist_df = new javax.swing.JScrollPane();
        myList_df = new javax.swing.JList<>();
        del_r = new javax.swing.JTextField();
        b_del_r = new javax.swing.JButton();
        scroll_mylist_dr = new javax.swing.JScrollPane();
        myList_dr = new javax.swing.JList<>();
        del_a = new javax.swing.JTextField();
        scroll_mylist_da = new javax.swing.JScrollPane();
        myList_da = new javax.swing.JList<>();
        b_del_f1 = new javax.swing.JButton();
        label_del_r = new javax.swing.JLabel();
        label_del_a = new javax.swing.JLabel();
        label_del_f = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        text_descriere_film = new javax.swing.JTextArea();
        m_rol_actor = new javax.swing.JTextField();
        m_nat_actor = new javax.swing.JTextField();
        m_label_nume_actor = new javax.swing.JLabel();
        m_label_nume_film = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        m_label_nume_regizor = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        m_nat_regizor = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        m_nr_premii = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        m_buton_film = new javax.swing.JButton();
        mesaj_modificare_actor = new javax.swing.JLabel();
        mesaj_modificare_regizor = new javax.swing.JLabel();
        mesaj_modificare_film = new javax.swing.JLabel();
        m_buton_regizor = new javax.swing.JButton();
        m_buton_actor = new javax.swing.JButton();
        b_select_r = new javax.swing.JButton();
        b_select_a = new javax.swing.JButton();
        b_select_f = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        scroll_date_film = new javax.swing.JScrollPane();
        date_filme = new javax.swing.JPanel();
        label_ad_film1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_rt = new javax.swing.JPanel();
        bara_panel = new javax.swing.JPanel();
        optiuni_tabel = new javax.swing.JPanel();
        refresh_panel = new javax.swing.JPanel();
        b_refresh = new javax.swing.JButton();
        filme_panel = new javax.swing.JPanel();
        b_viz_filme = new javax.swing.JButton();
        regizor_panel = new javax.swing.JPanel();
        b_viz_regizori = new javax.swing.JButton();
        actor_panel = new javax.swing.JPanel();
        b_viz_actori = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tabel_panel = new javax.swing.JPanel();
        jScrollPane_actori = new javax.swing.JScrollPane();
        tabel_actori = new javax.swing.JTable();
        jScrollPane_regizori = new javax.swing.JScrollPane();
        tabel_regizori = new javax.swing.JTable();
        jScrollPane_filme = new javax.swing.JScrollPane();
        tabel_filme = new javax.swing.JTable();
        poster_film = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        l_film = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        caut_date_film_b = new javax.swing.JButton();
        cautare_f = new javax.swing.JTextField();
        scroll_mylist_f = new javax.swing.JScrollPane();
        myList_f = new javax.swing.JList<>();
        label_cautare_a = new javax.swing.JLabel();
        cautare_a = new javax.swing.JTextField();
        scroll_mylist_a = new javax.swing.JScrollPane();
        myList_a = new javax.swing.JList<>();
        caut_film_b1 = new javax.swing.JButton();
        scroll_filme_populare = new javax.swing.JScrollPane();
        tabel_filme_populare = new javax.swing.JTable();
        scroll_ca_filme = new javax.swing.JScrollPane();
        tabel_filme_ca = new javax.swing.JTable();
        label_cautare_r = new javax.swing.JLabel();
        label_cautare_r2 = new javax.swing.JLabel();
        cautare_r = new javax.swing.JTextField();
        scroll_mylist_r = new javax.swing.JScrollPane();
        myList_r = new javax.swing.JList<>();
        caut_actor_b = new javax.swing.JButton();
        caut_film_b = new javax.swing.JButton();
        scroll_cr_actori = new javax.swing.JScrollPane();
        tabel_cr_actori = new javax.swing.JTable();
        scroll_cr_filme = new javax.swing.JScrollPane();
        tabel_filme_cr = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        l_an_f = new javax.swing.JLabel();
        l_gen_f = new javax.swing.JLabel();
        l_durata_f = new javax.swing.JLabel();
        l_regizor_f = new javax.swing.JLabel();
        l_tara_f = new javax.swing.JLabel();
        l_distributie = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        nr_filme_r = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nr_actori_r = new javax.swing.JLabel();
        l_descriere_f = new javax.swing.JTextArea();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        text_an_f = new javax.swing.JTextField();
        b_cutare_filme_popurare = new javax.swing.JButton();
        lista_filme = new javax.swing.JScrollPane();
        scroll_panel = new javax.swing.JPanel();
        bara_cautare_f = new javax.swing.JTextField();
        jScrollPane_lista_cautare_filme = new javax.swing.JScrollPane();
        list_cautare_filme = new javax.swing.JList<>();
        l_aventura = new javax.swing.JLabel();
        l_actiune = new javax.swing.JLabel();
        l_sf = new javax.swing.JLabel();
        l_drama = new javax.swing.JLabel();
        l_comedie = new javax.swing.JLabel();
        l_istoric = new javax.swing.JLabel();
        l_biografic = new javax.swing.JLabel();
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
        buton_cauta = new javax.swing.JButton();
        scroll_date_utilizator = new javax.swing.JScrollPane();
        date_utilizator = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_utilizator = new javax.swing.JTable();
        background_vd = new javax.swing.JPanel();
        viz_date_b = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        background_selectare_date = new javax.swing.JPanel();
        selectare_date_b = new javax.swing.JButton();
        background_vg = new javax.swing.JPanel();
        viz_gen_b = new javax.swing.JButton();
        text_nume = new javax.swing.JTextField();
        text_prenume = new javax.swing.JTextField();
        text_adresa = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabel_gen_u = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        text_gen = new javax.swing.JTextField();
        background_cg = new javax.swing.JPanel();
        buton_viz_filme = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabel_filme_recomandate = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabel_filme_vizionate = new javax.swing.JTable();
        date_abonamente = new javax.swing.JPanel();
        label_abonamente = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_abonament = new javax.swing.JTable();
        background_vza2 = new javax.swing.JPanel();
        selectare_date_abonament = new javax.swing.JButton();
        background_vza1 = new javax.swing.JPanel();
        afisare_descriere = new javax.swing.JButton();
        background_vza = new javax.swing.JPanel();
        vza_b = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        background_rez = new javax.swing.JPanel();
        rez_b = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabel_restantieri = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabel_au = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        combobox_abonamente = new javax.swing.JComboBox<>();
        label_descriere_abonament = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        text_pret = new javax.swing.JTextField();
        text_descriere = new javax.swing.JTextArea();
        background_rez1 = new javax.swing.JPanel();
        b_modifica = new javax.swing.JButton();
        mesaj_modificare_succes = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        label_abonament = new javax.swing.JLabel();
        panou_info = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        baraXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        baraXMinMax.setPreferredSize(new java.awt.Dimension(900, 30));
        baraXMinMax.setLayout(new java.awt.BorderLayout());

        iconXMinMax.setBackground(new java.awt.Color(0, 0, 0));
        iconXMinMax.setPreferredSize(new java.awt.Dimension(90, 30));
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

        nume_admin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nume_admin.setForeground(new java.awt.Color(255, 255, 255));
        extensie_profil.add(nume_admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 80, 20));

        deconectareA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deconectareA.setForeground(new java.awt.Color(255, 255, 255));
        deconectareA.setText("Deconectare");
        deconectareA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deconectareA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deconectareAMouseClicked(evt);
            }
        });
        extensie_profil.add(deconectareA, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        extensie_meniu.add(extensie_profil, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 1820));

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
        buton_acasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buton_acasaMouseClicked(evt);
            }
        });
        buton_acasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_acasaActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_acasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 150, 40));

        buton_filme.setBackground(new java.awt.Color(0, 0, 0));
        buton_filme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_filme.setForeground(new java.awt.Color(255, 255, 255));
        buton_filme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/db_movie24.png"))); // NOI18N
        buton_filme.setText("FILME");
        buton_filme.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_filme.setBorderPainted(false);
        buton_filme.setContentAreaFilled(false);
        buton_filme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_filme.setIconTextGap(10);
        buton_filme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buton_filmeMouseClicked(evt);
            }
        });
        extensie_meniu.add(buton_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 120, 40));

        buton_utilizatori.setBackground(new java.awt.Color(0, 0, 0));
        buton_utilizatori.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_utilizatori.setForeground(new java.awt.Color(255, 255, 255));
        buton_utilizatori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/db_person24.png"))); // NOI18N
        buton_utilizatori.setText("UTILIZATORI");
        buton_utilizatori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_utilizatori.setBorderPainted(false);
        buton_utilizatori.setContentAreaFilled(false);
        buton_utilizatori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_utilizatori.setIconTextGap(10);
        buton_utilizatori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_utilizatoriActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_utilizatori, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 180, 40));

        buton_setari.setBackground(new java.awt.Color(0, 0, 0));
        buton_setari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_setari.setForeground(new java.awt.Color(255, 255, 255));
        buton_setari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus_24.png"))); // NOI18N
        buton_setari.setText("SETARI");
        buton_setari.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_setari.setBorderPainted(false);
        buton_setari.setContentAreaFilled(false);
        buton_setari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_setari.setIconTextGap(10);
        buton_setari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buton_setariMouseClicked(evt);
            }
        });
        extensie_meniu.add(buton_setari, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 130, 40));

        buton_abonamente.setBackground(new java.awt.Color(0, 0, 0));
        buton_abonamente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buton_abonamente.setForeground(new java.awt.Color(255, 255, 255));
        buton_abonamente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/db_abonamente24.png"))); // NOI18N
        buton_abonamente.setText(" ABONAMENTE");
        buton_abonamente.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_abonamente.setBorderPainted(false);
        buton_abonamente.setContentAreaFilled(false);
        buton_abonamente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_abonamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_abonamenteActionPerformed(evt);
            }
        });
        extensie_meniu.add(buton_abonamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 190, 40));

        meniu.add(extensie_meniu, java.awt.BorderLayout.CENTER);

        getContentPane().add(meniu, java.awt.BorderLayout.LINE_START);

        dashbord.setBackground(new java.awt.Color(0, 0, 0));
        dashbord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll_add_film.setBackground(new java.awt.Color(255, 255, 255));
        scroll_add_film.setBorder(null);
        scroll_add_film.setForeground(new java.awt.Color(0, 0, 0));
        scroll_add_film.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_add_film.setAutoscrolls(true);

        add_date_film.setBackground(new java.awt.Color(220, 20, 60));
        add_date_film.setPreferredSize(new java.awt.Dimension(1250, 1500));
        add_date_film.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_ad_film.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        label_ad_film.setForeground(new java.awt.Color(255, 255, 255));
        label_ad_film.setText("Adauga Film");
        add_date_film.add(label_ad_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 220, 50));

        ad_regizor_actor.setBackground(new java.awt.Color(255, 255, 255));
        ad_regizor_actor.setForeground(new java.awt.Color(255, 51, 204));
        ad_regizor_actor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_regizor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_regizor.setForeground(new java.awt.Color(220, 20, 60));
        label_regizor.setText("Adauga Regizor");
        ad_regizor_actor.add(label_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 260, 30));

        numeregizor.setBackground(new java.awt.Color(255, 255, 255));
        numeregizor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numeregizor.setForeground(new java.awt.Color(220, 20, 60));
        numeregizor.setText("Nume");
        numeregizor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        numeregizor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numeregizorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                numeregizorFocusLost(evt);
            }
        });
        ad_regizor_actor.add(numeregizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 260, 30));

        sep_numeregizor.setBackground(new java.awt.Color(220, 20, 60));
        sep_numeregizor.setForeground(new java.awt.Color(220, 20, 60));
        sep_numeregizor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_numeregizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 260, 2));

        nationalitateregizor.setBackground(new java.awt.Color(255, 255, 255));
        nationalitateregizor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nationalitateregizor.setForeground(new java.awt.Color(220, 20, 60));
        nationalitateregizor.setText("Nationalitate");
        nationalitateregizor.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nationalitateregizor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nationalitateregizorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nationalitateregizorFocusLost(evt);
            }
        });
        ad_regizor_actor.add(nationalitateregizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 260, 30));

        sep_nationalitateregizor.setBackground(new java.awt.Color(220, 20, 60));
        sep_nationalitateregizor.setForeground(new java.awt.Color(220, 20, 60));
        sep_nationalitateregizor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_nationalitateregizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 260, 2));

        nrpremii.setBackground(new java.awt.Color(255, 255, 255));
        nrpremii.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nrpremii.setForeground(new java.awt.Color(220, 20, 60));
        nrpremii.setText("Numar Premii Castigate");
        nrpremii.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        nrpremii.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nrpremiiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nrpremiiFocusLost(evt);
            }
        });
        nrpremii.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nrpremiiActionPerformed(evt);
            }
        });
        ad_regizor_actor.add(nrpremii, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 260, 30));

        sep_nrpremii.setBackground(new java.awt.Color(220, 20, 60));
        sep_nrpremii.setForeground(new java.awt.Color(220, 20, 60));
        sep_nrpremii.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_nrpremii, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 260, 2));

        mesaj_err_R.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_err_R.setForeground(new java.awt.Color(153, 0, 51));
        mesaj_err_R.setText("!!! Trebuie sa adaugati numele regizorului  !!!");
        ad_regizor_actor.add(mesaj_err_R, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        background_addR.setBackground(new java.awt.Color(220, 20, 60));
        background_addR.setLayout(new java.awt.BorderLayout());

        b_addR.setBackground(new java.awt.Color(220, 20, 60));
        b_addR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_addR.setForeground(new java.awt.Color(255, 255, 255));
        b_addR.setText("Adauga Regizor");
        b_addR.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_addR.setBorderPainted(false);
        b_addR.setContentAreaFilled(false);
        b_addR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_addR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addRActionPerformed(evt);
            }
        });
        background_addR.add(b_addR, java.awt.BorderLayout.CENTER);

        ad_regizor_actor.add(background_addR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 130, 30));

        label_actor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_actor.setForeground(new java.awt.Color(220, 20, 60));
        label_actor.setText("Adauga Actor");
        ad_regizor_actor.add(label_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 230, 30));

        numeactor.setBackground(new java.awt.Color(255, 255, 255));
        numeactor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numeactor.setForeground(new java.awt.Color(220, 20, 60));
        numeactor.setText("Nume");
        numeactor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        numeactor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numeactorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                numeactorFocusLost(evt);
            }
        });
        ad_regizor_actor.add(numeactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 260, 30));

        sep_numeactor.setBackground(new java.awt.Color(220, 20, 60));
        sep_numeactor.setForeground(new java.awt.Color(220, 20, 60));
        sep_numeactor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_numeactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 260, 2));

        nationalitateactor.setBackground(new java.awt.Color(255, 255, 255));
        nationalitateactor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nationalitateactor.setForeground(new java.awt.Color(220, 20, 60));
        nationalitateactor.setText("Nationalitate");
        nationalitateactor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        nationalitateactor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nationalitateactorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nationalitateactorFocusLost(evt);
            }
        });
        ad_regizor_actor.add(nationalitateactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 260, 30));

        sep_nationalitateactor.setBackground(new java.awt.Color(220, 20, 60));
        sep_nationalitateactor.setForeground(new java.awt.Color(220, 20, 60));
        sep_nationalitateactor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_nationalitateactor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 260, 2));

        rol_memorabil.setBackground(new java.awt.Color(255, 255, 255));
        rol_memorabil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rol_memorabil.setForeground(new java.awt.Color(220, 20, 60));
        rol_memorabil.setText("Rol Memorabil");
        rol_memorabil.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rol_memorabil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rol_memorabilFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                rol_memorabilFocusLost(evt);
            }
        });
        ad_regizor_actor.add(rol_memorabil, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 260, 30));

        sep_rol_memorabil.setBackground(new java.awt.Color(220, 20, 60));
        sep_rol_memorabil.setForeground(new java.awt.Color(220, 20, 60));
        sep_rol_memorabil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        ad_regizor_actor.add(sep_rol_memorabil, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 260, 2));

        mesaj_err_A.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_err_A.setForeground(new java.awt.Color(153, 0, 51));
        mesaj_err_A.setText("!!! Trebuie sa adaugati numele actorului !!!");
        ad_regizor_actor.add(mesaj_err_A, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        background_addA.setBackground(new java.awt.Color(220, 20, 60));
        background_addA.setLayout(new java.awt.BorderLayout());

        b_addA.setBackground(new java.awt.Color(220, 20, 60));
        b_addA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_addA.setForeground(new java.awt.Color(255, 255, 255));
        b_addA.setText("Adauga Actor\n");
        b_addA.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_addA.setBorderPainted(false);
        b_addA.setContentAreaFilled(false);
        b_addA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_addA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_addAActionPerformed(evt);
            }
        });
        background_addA.add(b_addA, java.awt.BorderLayout.CENTER);

        ad_regizor_actor.add(background_addA, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 130, 30));

        bX_ra.setBackground(new java.awt.Color(255, 255, 255));
        bX_ra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        bX_ra.setForeground(new java.awt.Color(0, 0, 0));
        bX_ra.setText("X");
        bX_ra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bX_ra.setBorderPainted(false);
        bX_ra.setContentAreaFilled(false);
        bX_ra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bX_ra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bX_raActionPerformed(evt);
            }
        });
        ad_regizor_actor.add(bX_ra, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 50, 30));

        add_date_film.add(ad_regizor_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 310, 540));

        add_fillm.setBackground(new java.awt.Color(255, 255, 255));
        add_fillm.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add_fillm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        numefilm.setBackground(new java.awt.Color(255, 255, 255));
        numefilm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numefilm.setForeground(new java.awt.Color(220, 20, 60));
        numefilm.setText("Nume Film");
        numefilm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        numefilm.setCaretColor(new java.awt.Color(255, 255, 255));
        numefilm.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        numefilm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                numefilmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                numefilmFocusLost(evt);
            }
        });
        add_fillm.add(numefilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 340, 30));

        sem_numefilm.setBackground(new java.awt.Color(220, 20, 60));
        sem_numefilm.setForeground(new java.awt.Color(220, 20, 60));
        sem_numefilm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sem_numefilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 340, 2));

        anaparitie.setBackground(new java.awt.Color(255, 255, 255));
        anaparitie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anaparitie.setForeground(new java.awt.Color(220, 20, 60));
        anaparitie.setText("Anul Aparitiei");
        anaparitie.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        anaparitie.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                anaparitieFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                anaparitieFocusLost(evt);
            }
        });
        anaparitie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anaparitieActionPerformed(evt);
            }
        });
        add_fillm.add(anaparitie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 340, 30));

        sep_anaparitie.setBackground(new java.awt.Color(220, 20, 60));
        sep_anaparitie.setForeground(new java.awt.Color(220, 20, 60));
        sep_anaparitie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sep_anaparitie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 340, 2));

        gen.setBackground(new java.awt.Color(255, 255, 255));
        gen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gen.setForeground(new java.awt.Color(220, 20, 60));
        gen.setText("Gen");
        gen.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                genFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                genFocusLost(evt);
            }
        });
        add_fillm.add(gen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 340, 30));

        sep_gen.setBackground(new java.awt.Color(220, 20, 60));
        sep_gen.setForeground(new java.awt.Color(220, 20, 60));
        sep_gen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sep_gen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 340, 2));

        tara.setBackground(new java.awt.Color(255, 255, 255));
        tara.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tara.setForeground(new java.awt.Color(220, 20, 60));
        tara.setText("Tara");
        tara.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tara.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                taraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                taraFocusLost(evt);
            }
        });
        add_fillm.add(tara, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 340, 30));

        sep_tara.setBackground(new java.awt.Color(220, 20, 60));
        sep_tara.setForeground(new java.awt.Color(220, 20, 60));
        sep_tara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sep_tara, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 340, 2));

        descriere.setBackground(new java.awt.Color(255, 255, 255));
        descriere.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descriere.setForeground(new java.awt.Color(220, 20, 60));
        descriere.setText("Descriere");
        descriere.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        descriere.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriereFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriereFocusLost(evt);
            }
        });
        add_fillm.add(descriere, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 340, 30));

        sep_descriere.setBackground(new java.awt.Color(220, 20, 60));
        sep_descriere.setForeground(new java.awt.Color(220, 20, 60));
        sep_descriere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sep_descriere, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 340, 2));

        duratafilm.setBackground(new java.awt.Color(255, 255, 255));
        duratafilm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        duratafilm.setForeground(new java.awt.Color(220, 20, 60));
        duratafilm.setText("Durata Film (min)");
        duratafilm.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        duratafilm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                duratafilmFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                duratafilmFocusLost(evt);
            }
        });
        add_fillm.add(duratafilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 340, 30));

        sep_duratafilm.setBackground(new java.awt.Color(220, 20, 60));
        sep_duratafilm.setForeground(new java.awt.Color(220, 20, 60));
        sep_duratafilm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 20, 60)));
        add_fillm.add(sep_duratafilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 340, 2));

        cb_a1.setBackground(new java.awt.Color(255, 255, 255));
        cb_a1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_a1.setForeground(new java.awt.Color(153, 0, 51));
        cb_a1.setToolTipText("");
        cb_a1.setAutoscrolls(true);
        cb_a1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb_a1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_a1.setVerifyInputWhenFocusTarget(false);
        add_fillm.add(cb_a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 340, 30));

        rf_nume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rf_nume.setForeground(new java.awt.Color(220, 20, 60));
        rf_nume.setText("Regizor");
        add_fillm.add(rf_nume, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 50, 20));

        actor2_nume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        actor2_nume.setForeground(new java.awt.Color(220, 20, 60));
        actor2_nume.setText("Actor2");
        add_fillm.add(actor2_nume, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 50, 20));

        actor3_nume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        actor3_nume.setForeground(new java.awt.Color(220, 20, 60));
        actor3_nume.setText("Actor3");
        add_fillm.add(actor3_nume, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 50, 20));

        actor1_nume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        actor1_nume.setForeground(new java.awt.Color(220, 20, 60));
        actor1_nume.setText("Actor1");
        add_fillm.add(actor1_nume, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 50, 20));

        cb_a2.setBackground(new java.awt.Color(255, 255, 255));
        cb_a2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_a2.setForeground(new java.awt.Color(153, 0, 51));
        cb_a2.setToolTipText("");
        cb_a2.setAutoscrolls(true);
        cb_a2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb_a2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_a2.setVerifyInputWhenFocusTarget(false);
        add_fillm.add(cb_a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 340, 30));

        cb_a3.setBackground(new java.awt.Color(255, 255, 255));
        cb_a3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_a3.setForeground(new java.awt.Color(153, 0, 51));
        cb_a3.setToolTipText("");
        cb_a3.setAutoscrolls(true);
        cb_a3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb_a3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_a3.setOpaque(true);
        cb_a3.setVerifyInputWhenFocusTarget(false);
        add_fillm.add(cb_a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 340, 30));

        cb_regizor.setBackground(new java.awt.Color(255, 255, 255));
        cb_regizor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_regizor.setForeground(new java.awt.Color(153, 0, 51));
        cb_regizor.setToolTipText("");
        cb_regizor.setAutoscrolls(true);
        cb_regizor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb_regizor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cb_regizor.setVerifyInputWhenFocusTarget(false);
        cb_regizor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_regizorActionPerformed(evt);
            }
        });
        add_fillm.add(cb_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 340, 30));

        background_addfilm.setBackground(new java.awt.Color(220, 20, 60));
        background_addfilm.setLayout(new java.awt.BorderLayout());

        b_ad_film.setBackground(new java.awt.Color(220, 20, 60));
        b_ad_film.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_ad_film.setForeground(new java.awt.Color(255, 255, 255));
        b_ad_film.setText("Adauga Film");
        b_ad_film.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_ad_film.setContentAreaFilled(false);
        b_ad_film.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_ad_film.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ad_filmActionPerformed(evt);
            }
        });
        background_addfilm.add(b_ad_film, java.awt.BorderLayout.CENTER);

        add_fillm.add(background_addfilm, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 620, 130, 30));

        mesaj_err_ra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_err_ra.setForeground(new java.awt.Color(153, 0, 0));
        mesaj_err_ra.setText("!!! Trebuie sa selectezi / adaugi regizor si actor !!!");
        add_fillm.add(mesaj_err_ra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 320, -1));

        background_addra.setBackground(new java.awt.Color(0, 0, 0));
        background_addra.setLayout(new java.awt.BorderLayout());

        b_ad_ra.setBackground(new java.awt.Color(0, 0, 0));
        b_ad_ra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_ad_ra.setForeground(new java.awt.Color(255, 255, 255));
        b_ad_ra.setText("Adauga R / A");
        b_ad_ra.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_ad_ra.setBorderPainted(false);
        b_ad_ra.setContentAreaFilled(false);
        b_ad_ra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_ad_ra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ad_raActionPerformed(evt);
            }
        });
        background_addra.add(b_ad_ra, java.awt.BorderLayout.CENTER);

        add_fillm.add(background_addra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 130, 30));

        mesaj_err_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_err_f.setForeground(new java.awt.Color(153, 0, 0));
        mesaj_err_f.setText("!!! Trebuie sa completezi datele despre film !!!");
        add_fillm.add(mesaj_err_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        add_date_film.add(add_fillm, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 380, 670));

        panel_rt1.setBackground(new java.awt.Color(0, 0, 0));
        panel_rt1.setPreferredSize(new java.awt.Dimension(450, 450));
        panel_rt1.setLayout(new java.awt.BorderLayout());

        bara_panel1.setBackground(new java.awt.Color(220, 20, 60));
        bara_panel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bara_panel1.setPreferredSize(new java.awt.Dimension(480, 25));
        bara_panel1.setLayout(new java.awt.BorderLayout());

        optiuni_tabel1.setBackground(new java.awt.Color(255, 255, 255));
        optiuni_tabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));

        refresh_panel1.setBackground(new java.awt.Color(255, 255, 255));
        refresh_panel1.setOpaque(false);
        refresh_panel1.setLayout(new java.awt.BorderLayout());

        b_refresh1.setBackground(new java.awt.Color(255, 255, 255));
        b_refresh1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_refresh1.setForeground(new java.awt.Color(220, 20, 60));
        b_refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        b_refresh1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_refresh1.setBorderPainted(false);
        b_refresh1.setContentAreaFilled(false);
        b_refresh1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_refresh1.setFocusPainted(false);
        b_refresh1.setFocusable(false);
        b_refresh1.setRequestFocusEnabled(false);
        b_refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_refresh1ActionPerformed(evt);
            }
        });
        refresh_panel1.add(b_refresh1, java.awt.BorderLayout.CENTER);

        filme_panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        filme_panel1.setOpaque(false);
        filme_panel1.setPreferredSize(new java.awt.Dimension(30, 25));
        filme_panel1.setLayout(new java.awt.BorderLayout());

        b_viz_filme1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/movieee.png"))); // NOI18N
        b_viz_filme1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_filme1.setBorderPainted(false);
        b_viz_filme1.setContentAreaFilled(false);
        b_viz_filme1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_filme1.setFocusPainted(false);
        b_viz_filme1.setFocusable(false);
        b_viz_filme1.setRequestFocusEnabled(false);
        b_viz_filme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_filme1ActionPerformed(evt);
            }
        });
        filme_panel1.add(b_viz_filme1, java.awt.BorderLayout.CENTER);

        regizor_panel1.setOpaque(false);
        regizor_panel1.setPreferredSize(new java.awt.Dimension(30, 25));
        regizor_panel1.setLayout(new java.awt.BorderLayout());

        b_viz_regizori1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/director.png"))); // NOI18N
        b_viz_regizori1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_regizori1.setBorderPainted(false);
        b_viz_regizori1.setContentAreaFilled(false);
        b_viz_regizori1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_regizori1.setFocusPainted(false);
        b_viz_regizori1.setFocusable(false);
        b_viz_regizori1.setPreferredSize(new java.awt.Dimension(30, 30));
        b_viz_regizori1.setRequestFocusEnabled(false);
        b_viz_regizori1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_regizori1ActionPerformed(evt);
            }
        });
        regizor_panel1.add(b_viz_regizori1, java.awt.BorderLayout.CENTER);

        actor_panel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        actor_panel1.setOpaque(false);
        actor_panel1.setPreferredSize(new java.awt.Dimension(30, 25));
        actor_panel1.setLayout(new java.awt.BorderLayout());

        b_viz_actori1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/drama.png"))); // NOI18N
        b_viz_actori1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_actori1.setBorderPainted(false);
        b_viz_actori1.setContentAreaFilled(false);
        b_viz_actori1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_actori1.setFocusPainted(false);
        b_viz_actori1.setFocusable(false);
        b_viz_actori1.setRequestFocusEnabled(false);
        b_viz_actori1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_actori1ActionPerformed(evt);
            }
        });
        actor_panel1.add(b_viz_actori1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout optiuni_tabel1Layout = new javax.swing.GroupLayout(optiuni_tabel1);
        optiuni_tabel1.setLayout(optiuni_tabel1Layout);
        optiuni_tabel1Layout.setHorizontalGroup(
            optiuni_tabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optiuni_tabel1Layout.createSequentialGroup()
                .addComponent(refresh_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filme_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(regizor_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(actor_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        optiuni_tabel1Layout.setVerticalGroup(
            optiuni_tabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optiuni_tabel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(optiuni_tabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actor_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regizor_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filme_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refresh_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bara_panel1.add(optiuni_tabel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(220, 20, 60));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bara_panel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        panel_rt1.add(bara_panel1, java.awt.BorderLayout.PAGE_START);

        tabel_panel1.setBackground(new java.awt.Color(255, 255, 255));
        tabel_panel1.setPreferredSize(new java.awt.Dimension(450, 425));
        tabel_panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane_actori1.setBorder(null);

        tabel_actori1.setBackground(new java.awt.Color(255, 255, 255));
        tabel_actori1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_actori1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_actori1.setForeground(new java.awt.Color(0, 0, 0));
        tabel_actori1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Actor", "Nationalitate", "Rol Memorabil"
            }
        ));
        tabel_actori1.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_actori1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_actori1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_actori1.setShowHorizontalLines(true);
        jScrollPane_actori1.setViewportView(tabel_actori1);

        tabel_panel1.add(jScrollPane_actori1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 430));

        jScrollPane_regizori1.setBorder(null);

        tabel_regizori1.setBackground(new java.awt.Color(255, 255, 255));
        tabel_regizori1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_regizori1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_regizori1.setForeground(new java.awt.Color(0, 0, 0));
        tabel_regizori1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Regizor", "Nationalitate", "Nr premii"
            }
        ));
        tabel_regizori1.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_regizori1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_regizori1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_regizori1.setShowHorizontalLines(true);
        jScrollPane_regizori1.setViewportView(tabel_regizori1);

        tabel_panel1.add(jScrollPane_regizori1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 430));

        jScrollPane_filme1.setBorder(null);

        tabel_filme1.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_filme1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme1.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filme", "AnAparitie", "Gen"
            }
        ));
        tabel_filme1.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme1.setRequestFocusEnabled(false);
        tabel_filme1.setRowHeight(25);
        tabel_filme1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_filme1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_filme1.setShowHorizontalLines(true);
        jScrollPane_filme1.setViewportView(tabel_filme1);

        tabel_panel1.add(jScrollPane_filme1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 430));

        panel_rt1.add(tabel_panel1, java.awt.BorderLayout.CENTER);

        add_date_film.add(panel_rt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 450, 450));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Vizualizare filme / regizori / actori existenti");
        add_date_film.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Stergere Film / Regizor / Actor");
        add_date_film.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 820, -1, -1));

        del_f.setBackground(new java.awt.Color(255, 255, 255));
        del_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        del_f.setForeground(new java.awt.Color(220, 20, 60));
        del_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        del_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_fActionPerformed(evt);
            }
        });
        del_f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                del_fKeyReleased(evt);
            }
        });
        add_date_film.add(del_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 920, 230, 30));

        b_del_f.setBackground(new java.awt.Color(0, 0, 0));
        b_del_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_del_f.setForeground(new java.awt.Color(255, 255, 255));
        b_del_f.setText("Sterge Film");
        b_del_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_del_f.setBorderPainted(false);
        b_del_f.setFocusPainted(false);
        b_del_f.setFocusable(false);
        b_del_f.setOpaque(true);
        b_del_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_del_fActionPerformed(evt);
            }
        });
        add_date_film.add(b_del_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 920, 140, 30));

        myList_df.setBackground(new java.awt.Color(255, 255, 255));
        myList_df.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_df.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_df.setFocusable(false);
        myList_df.setRequestFocusEnabled(false);
        myList_df.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_dfMouseClicked(evt);
            }
        });
        scroll_mylist_df.setViewportView(myList_df);

        add_date_film.add(scroll_mylist_df, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 950, 230, 130));

        del_r.setBackground(new java.awt.Color(255, 255, 255));
        del_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        del_r.setForeground(new java.awt.Color(220, 20, 60));
        del_r.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        del_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_rActionPerformed(evt);
            }
        });
        del_r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                del_rKeyReleased(evt);
            }
        });
        add_date_film.add(del_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 990, 230, 30));

        b_del_r.setBackground(new java.awt.Color(0, 0, 0));
        b_del_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_del_r.setForeground(new java.awt.Color(255, 255, 255));
        b_del_r.setText("Sterge  Regizor");
        b_del_r.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_del_r.setBorderPainted(false);
        b_del_r.setFocusPainted(false);
        b_del_r.setFocusable(false);
        b_del_r.setOpaque(true);
        b_del_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_del_rActionPerformed(evt);
            }
        });
        add_date_film.add(b_del_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 990, 140, 30));

        myList_dr.setBackground(new java.awt.Color(255, 255, 255));
        myList_dr.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_dr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_dr.setFocusable(false);
        myList_dr.setRequestFocusEnabled(false);
        myList_dr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_drMouseClicked(evt);
            }
        });
        scroll_mylist_dr.setViewportView(myList_dr);

        add_date_film.add(scroll_mylist_dr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1020, 230, 130));

        del_a.setBackground(new java.awt.Color(255, 255, 255));
        del_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        del_a.setForeground(new java.awt.Color(220, 20, 60));
        del_a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        del_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                del_aActionPerformed(evt);
            }
        });
        del_a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                del_aKeyReleased(evt);
            }
        });
        add_date_film.add(del_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1060, 230, 30));

        myList_da.setBackground(new java.awt.Color(255, 255, 255));
        myList_da.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_da.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_da.setFocusable(false);
        myList_da.setRequestFocusEnabled(false);
        myList_da.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_daMouseClicked(evt);
            }
        });
        scroll_mylist_da.setViewportView(myList_da);

        add_date_film.add(scroll_mylist_da, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1090, 230, 130));

        b_del_f1.setBackground(new java.awt.Color(0, 0, 0));
        b_del_f1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_del_f1.setForeground(new java.awt.Color(255, 255, 255));
        b_del_f1.setText("Sterge  Actor");
        b_del_f1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_del_f1.setBorderPainted(false);
        b_del_f1.setFocusPainted(false);
        b_del_f1.setFocusable(false);
        b_del_f1.setOpaque(true);
        b_del_f1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_del_f1ActionPerformed(evt);
            }
        });
        add_date_film.add(b_del_f1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 1060, 140, 30));

        label_del_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_del_r.setForeground(new java.awt.Color(0, 0, 0));
        label_del_r.setText("Stergerea filmului a fost realizata cu succes.");
        add_date_film.add(label_del_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 990, 290, 30));

        label_del_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_del_a.setForeground(new java.awt.Color(0, 0, 0));
        label_del_a.setText("Stergerea filmului a fost realizata cu succes.");
        add_date_film.add(label_del_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 1060, 290, 30));

        label_del_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_del_f.setForeground(new java.awt.Color(0, 0, 0));
        label_del_f.setText("Stergerea filmului a fost realizata cu succes.");
        add_date_film.add(label_del_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 920, 290, 30));

        jSeparator9.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator9.setForeground(new java.awt.Color(52, 40, 44));
        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add_date_film.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 650, 10, 810));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Modificare Film / Regizor / Actor");
        add_date_film.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Film");
        add_date_film.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 740, -1, -1));

        text_descriere_film.setBackground(new java.awt.Color(255, 255, 255));
        text_descriere_film.setColumns(33);
        text_descriere_film.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_descriere_film.setForeground(new java.awt.Color(0, 0, 0));
        text_descriere_film.setLineWrap(true);
        text_descriere_film.setRows(5);
        text_descriere_film.setWrapStyleWord(true);
        text_descriere_film.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        add_date_film.add(text_descriere_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 840, -1, 80));

        m_rol_actor.setBackground(new java.awt.Color(255, 255, 255));
        m_rol_actor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_rol_actor.setForeground(new java.awt.Color(0, 0, 0));
        m_rol_actor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        add_date_film.add(m_rol_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1400, 210, -1));

        m_nat_actor.setBackground(new java.awt.Color(255, 255, 255));
        m_nat_actor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_nat_actor.setForeground(new java.awt.Color(0, 0, 0));
        m_nat_actor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        add_date_film.add(m_nat_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1340, 210, -1));

        m_label_nume_actor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        m_label_nume_actor.setForeground(new java.awt.Color(255, 255, 255));
        add_date_film.add(m_label_nume_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1270, 370, 40));

        m_label_nume_film.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        m_label_nume_film.setForeground(new java.awt.Color(255, 255, 255));
        add_date_film.add(m_label_nume_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 760, 370, 40));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Descriere Film");
        add_date_film.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 810, -1, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Regizor");
        add_date_film.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1000, -1, -1));

        m_label_nume_regizor.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        m_label_nume_regizor.setForeground(new java.awt.Color(255, 255, 255));
        add_date_film.add(m_label_nume_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1020, 270, 40));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nationalitate Regizor");
        add_date_film.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1070, -1, -1));

        m_nat_regizor.setBackground(new java.awt.Color(255, 255, 255));
        m_nat_regizor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_nat_regizor.setForeground(new java.awt.Color(0, 0, 0));
        m_nat_regizor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        add_date_film.add(m_nat_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1090, 210, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nationalitate");
        add_date_film.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1320, -1, -1));

        m_nr_premii.setBackground(new java.awt.Color(255, 255, 255));
        m_nr_premii.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_nr_premii.setForeground(new java.awt.Color(0, 0, 0));
        m_nr_premii.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        add_date_film.add(m_nr_premii, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1150, 50, -1));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Actor");
        add_date_film.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1250, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Numar Premii");
        add_date_film.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1130, -1, -1));

        m_buton_film.setBackground(new java.awt.Color(0, 0, 0));
        m_buton_film.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_buton_film.setForeground(new java.awt.Color(255, 255, 255));
        m_buton_film.setText("Modificare Film");
        m_buton_film.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_buton_film.setBorderPainted(false);
        m_buton_film.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_buton_film.setFocusPainted(false);
        m_buton_film.setFocusable(false);
        m_buton_film.setOpaque(true);
        m_buton_film.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_buton_filmActionPerformed(evt);
            }
        });
        add_date_film.add(m_buton_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 930, 150, 30));

        mesaj_modificare_actor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesaj_modificare_actor.setForeground(new java.awt.Color(0, 0, 0));
        mesaj_modificare_actor.setText("Modificarea s-a realizat cu succes!");
        add_date_film.add(mesaj_modificare_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1440, -1, -1));

        mesaj_modificare_regizor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesaj_modificare_regizor.setForeground(new java.awt.Color(0, 0, 0));
        mesaj_modificare_regizor.setText("Modificarea s-a realizat cu succes!");
        add_date_film.add(mesaj_modificare_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1190, -1, -1));

        mesaj_modificare_film.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        mesaj_modificare_film.setForeground(new java.awt.Color(0, 0, 0));
        mesaj_modificare_film.setText("Modificarea s-a realizat cu succes!");
        add_date_film.add(mesaj_modificare_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 940, -1, -1));

        m_buton_regizor.setBackground(new java.awt.Color(0, 0, 0));
        m_buton_regizor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_buton_regizor.setForeground(new java.awt.Color(255, 255, 255));
        m_buton_regizor.setText("Modificare Regizor");
        m_buton_regizor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_buton_regizor.setBorderPainted(false);
        m_buton_regizor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_buton_regizor.setFocusPainted(false);
        m_buton_regizor.setFocusable(false);
        m_buton_regizor.setOpaque(true);
        m_buton_regizor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_buton_regizorActionPerformed(evt);
            }
        });
        add_date_film.add(m_buton_regizor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 1180, 150, 30));

        m_buton_actor.setBackground(new java.awt.Color(0, 0, 0));
        m_buton_actor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        m_buton_actor.setForeground(new java.awt.Color(255, 255, 255));
        m_buton_actor.setText("Modificare Actor");
        m_buton_actor.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_buton_actor.setBorderPainted(false);
        m_buton_actor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        m_buton_actor.setFocusPainted(false);
        m_buton_actor.setFocusable(false);
        m_buton_actor.setOpaque(true);
        m_buton_actor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_buton_actorActionPerformed(evt);
            }
        });
        add_date_film.add(m_buton_actor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1430, 150, 30));

        b_select_r.setBackground(new java.awt.Color(255, 255, 255));
        b_select_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_select_r.setForeground(new java.awt.Color(220, 20, 60));
        b_select_r.setText("Selectare R");
        b_select_r.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_select_r.setBorderPainted(false);
        b_select_r.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_select_r.setFocusPainted(false);
        b_select_r.setFocusable(false);
        b_select_r.setOpaque(true);
        b_select_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_select_rActionPerformed(evt);
            }
        });
        add_date_film.add(b_select_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 580, 130, 30));

        b_select_a.setBackground(new java.awt.Color(255, 255, 255));
        b_select_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_select_a.setForeground(new java.awt.Color(220, 20, 60));
        b_select_a.setText("Selectare A");
        b_select_a.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_select_a.setBorderPainted(false);
        b_select_a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_select_a.setFocusPainted(false);
        b_select_a.setFocusable(false);
        b_select_a.setOpaque(true);
        b_select_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_select_aActionPerformed(evt);
            }
        });
        add_date_film.add(b_select_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 580, 130, 30));

        b_select_f.setBackground(new java.awt.Color(255, 255, 255));
        b_select_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_select_f.setForeground(new java.awt.Color(220, 20, 60));
        b_select_f.setText("Selectare F");
        b_select_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_select_f.setBorderPainted(false);
        b_select_f.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_select_f.setFocusPainted(false);
        b_select_f.setFocusable(false);
        b_select_f.setOpaque(true);
        b_select_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_select_fActionPerformed(evt);
            }
        });
        add_date_film.add(b_select_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 580, 130, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Rol Memorabil");
        add_date_film.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 1380, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Actor");
        add_date_film.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1040, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Regizor");
        add_date_film.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 970, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Film");
        add_date_film.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 900, -1, -1));

        scroll_add_film.setViewportView(add_date_film);

        dashbord.add(scroll_add_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 830));

        scroll_date_film.setBackground(new java.awt.Color(255, 255, 255));
        scroll_date_film.setBorder(null);
        scroll_date_film.setForeground(new java.awt.Color(0, 0, 0));
        scroll_date_film.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_date_film.setAutoscrolls(true);
        scroll_date_film.setMaximumSize(new java.awt.Dimension(1240, 1690));

        date_filme.setBackground(new java.awt.Color(220, 20, 60));
        date_filme.setPreferredSize(new java.awt.Dimension(1230, 1250));
        date_filme.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_ad_film1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        label_ad_film1.setForeground(new java.awt.Color(255, 255, 255));
        label_ad_film1.setText("Lista Filme");
        date_filme.add(label_ad_film1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 180, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Afisare intreaga lista de filme / regizori / actori");
        date_filme.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, -1));

        panel_rt.setBackground(new java.awt.Color(0, 0, 0));
        panel_rt.setLayout(new java.awt.BorderLayout());

        bara_panel.setBackground(new java.awt.Color(220, 20, 60));
        bara_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bara_panel.setPreferredSize(new java.awt.Dimension(480, 25));
        bara_panel.setLayout(new java.awt.BorderLayout());

        optiuni_tabel.setBackground(new java.awt.Color(255, 255, 255));
        optiuni_tabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));

        refresh_panel.setBackground(new java.awt.Color(255, 255, 255));
        refresh_panel.setOpaque(false);
        refresh_panel.setLayout(new java.awt.BorderLayout());

        b_refresh.setBackground(new java.awt.Color(255, 255, 255));
        b_refresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_refresh.setForeground(new java.awt.Color(220, 20, 60));
        b_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        b_refresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_refresh.setBorderPainted(false);
        b_refresh.setContentAreaFilled(false);
        b_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_refresh.setFocusPainted(false);
        b_refresh.setFocusable(false);
        b_refresh.setRequestFocusEnabled(false);
        b_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_refreshActionPerformed(evt);
            }
        });
        refresh_panel.add(b_refresh, java.awt.BorderLayout.CENTER);

        filme_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        filme_panel.setOpaque(false);
        filme_panel.setPreferredSize(new java.awt.Dimension(30, 25));
        filme_panel.setLayout(new java.awt.BorderLayout());

        b_viz_filme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/movieee.png"))); // NOI18N
        b_viz_filme.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_filme.setBorderPainted(false);
        b_viz_filme.setContentAreaFilled(false);
        b_viz_filme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_filme.setFocusPainted(false);
        b_viz_filme.setFocusable(false);
        b_viz_filme.setRequestFocusEnabled(false);
        b_viz_filme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_filmeActionPerformed(evt);
            }
        });
        filme_panel.add(b_viz_filme, java.awt.BorderLayout.CENTER);

        regizor_panel.setOpaque(false);
        regizor_panel.setPreferredSize(new java.awt.Dimension(30, 25));
        regizor_panel.setLayout(new java.awt.BorderLayout());

        b_viz_regizori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/director.png"))); // NOI18N
        b_viz_regizori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_regizori.setBorderPainted(false);
        b_viz_regizori.setContentAreaFilled(false);
        b_viz_regizori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_regizori.setFocusPainted(false);
        b_viz_regizori.setFocusable(false);
        b_viz_regizori.setPreferredSize(new java.awt.Dimension(30, 30));
        b_viz_regizori.setRequestFocusEnabled(false);
        b_viz_regizori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_regizoriActionPerformed(evt);
            }
        });
        regizor_panel.add(b_viz_regizori, java.awt.BorderLayout.CENTER);

        actor_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        actor_panel.setOpaque(false);
        actor_panel.setPreferredSize(new java.awt.Dimension(30, 25));
        actor_panel.setLayout(new java.awt.BorderLayout());

        b_viz_actori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/drama.png"))); // NOI18N
        b_viz_actori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_viz_actori.setBorderPainted(false);
        b_viz_actori.setContentAreaFilled(false);
        b_viz_actori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_viz_actori.setFocusPainted(false);
        b_viz_actori.setFocusable(false);
        b_viz_actori.setRequestFocusEnabled(false);
        b_viz_actori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_viz_actoriActionPerformed(evt);
            }
        });
        actor_panel.add(b_viz_actori, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout optiuni_tabelLayout = new javax.swing.GroupLayout(optiuni_tabel);
        optiuni_tabel.setLayout(optiuni_tabelLayout);
        optiuni_tabelLayout.setHorizontalGroup(
            optiuni_tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optiuni_tabelLayout.createSequentialGroup()
                .addComponent(refresh_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(filme_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(regizor_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(actor_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        optiuni_tabelLayout.setVerticalGroup(
            optiuni_tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optiuni_tabelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(optiuni_tabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actor_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regizor_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filme_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refresh_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bara_panel.add(optiuni_tabel, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(220, 20, 60));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        bara_panel.add(jPanel2, java.awt.BorderLayout.CENTER);

        panel_rt.add(bara_panel, java.awt.BorderLayout.PAGE_START);

        tabel_panel.setBackground(new java.awt.Color(255, 255, 255));
        tabel_panel.setPreferredSize(new java.awt.Dimension(490, 425));
        tabel_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane_actori.setBorder(null);

        tabel_actori.setBackground(new java.awt.Color(255, 255, 255));
        tabel_actori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_actori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_actori.setForeground(new java.awt.Color(0, 0, 0));
        tabel_actori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Actor", "Nationalitate", "Rol Memorabil"
            }
        ));
        tabel_actori.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_actori.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_actori.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_actori.setShowHorizontalLines(true);
        jScrollPane_actori.setViewportView(tabel_actori);

        tabel_panel.add(jScrollPane_actori, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 430));

        jScrollPane_regizori.setBorder(null);

        tabel_regizori.setBackground(new java.awt.Color(255, 255, 255));
        tabel_regizori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_regizori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_regizori.setForeground(new java.awt.Color(0, 0, 0));
        tabel_regizori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Regizor", "Nationalitate", "Nr premii"
            }
        ));
        tabel_regizori.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_regizori.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_regizori.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_regizori.setShowHorizontalLines(true);
        jScrollPane_regizori.setViewportView(tabel_regizori);

        tabel_panel.add(jScrollPane_regizori, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 430));

        jScrollPane_filme.setBorder(null);

        tabel_filme.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_filme.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filme", "AnAparitie", "Gen"
            }
        ));
        tabel_filme.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme.setRequestFocusEnabled(false);
        tabel_filme.setRowHeight(25);
        tabel_filme.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_filme.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_filme.setShowHorizontalLines(true);
        jScrollPane_filme.setViewportView(tabel_filme);

        tabel_panel.add(jScrollPane_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 430));

        panel_rt.add(tabel_panel, java.awt.BorderLayout.CENTER);

        date_filme.add(panel_rt, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 480, 450));

        poster_film.setBackground(new java.awt.Color(255, 255, 255));
        poster_film.setForeground(new java.awt.Color(255, 255, 255));
        poster_film.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/play.png"))); // NOI18N
        poster_film.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 80, 80));

        l_film.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        l_film.setForeground(new java.awt.Color(0, 0, 0));
        l_film.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        poster_film.add(l_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 190, 20));

        date_filme.add(poster_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 230, 340));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Afisare date despre un film:");
        date_filme.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 180, -1));

        caut_date_film_b.setBackground(new java.awt.Color(255, 255, 255));
        caut_date_film_b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        caut_date_film_b.setForeground(new java.awt.Color(0, 0, 0));
        caut_date_film_b.setText("Cauta Film");
        caut_date_film_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        caut_date_film_b.setBorderPainted(false);
        caut_date_film_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        caut_date_film_b.setRequestFocusEnabled(false);
        caut_date_film_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caut_date_film_bActionPerformed(evt);
            }
        });
        date_filme.add(caut_date_film_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 100, 30));

        cautare_f.setBackground(new java.awt.Color(255, 255, 255));
        cautare_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cautare_f.setForeground(new java.awt.Color(220, 20, 60));
        cautare_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cautare_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cautare_fActionPerformed(evt);
            }
        });
        cautare_f.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cautare_fKeyReleased(evt);
            }
        });
        date_filme.add(cautare_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 230, 20));

        myList_f.setBackground(new java.awt.Color(255, 255, 255));
        myList_f.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_f.setFocusable(false);
        myList_f.setRequestFocusEnabled(false);
        myList_f.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_fMouseClicked(evt);
            }
        });
        scroll_mylist_f.setViewportView(myList_f);

        date_filme.add(scroll_mylist_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 230, -1));

        label_cautare_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_cautare_a.setForeground(new java.awt.Color(255, 255, 255));
        label_cautare_a.setText("Cautare film dupa actor (afisarea se face in ordinea alfabetica):");
        date_filme.add(label_cautare_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 930, -1, -1));

        cautare_a.setBackground(new java.awt.Color(255, 255, 255));
        cautare_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cautare_a.setForeground(new java.awt.Color(220, 20, 60));
        cautare_a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cautare_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cautare_aActionPerformed(evt);
            }
        });
        cautare_a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cautare_aKeyReleased(evt);
            }
        });
        date_filme.add(cautare_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 960, 230, 20));

        myList_a.setBackground(new java.awt.Color(255, 255, 255));
        myList_a.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_a.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_a.setFocusable(false);
        myList_a.setRequestFocusEnabled(false);
        myList_a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_aMouseClicked(evt);
            }
        });
        scroll_mylist_a.setViewportView(myList_a);

        date_filme.add(scroll_mylist_a, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 980, 230, -1));

        caut_film_b1.setBackground(new java.awt.Color(255, 255, 255));
        caut_film_b1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        caut_film_b1.setForeground(new java.awt.Color(0, 0, 0));
        caut_film_b1.setText("Cauta Film");
        caut_film_b1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        caut_film_b1.setBorderPainted(false);
        caut_film_b1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        caut_film_b1.setRequestFocusEnabled(false);
        caut_film_b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caut_film_b1ActionPerformed(evt);
            }
        });
        date_filme.add(caut_film_b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 960, 100, 30));

        scroll_filme_populare.setBorder(null);

        tabel_filme_populare.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme_populare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_filme_populare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme_populare.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme_populare.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Film", "An Aparitie", "Nr vizonari"
            }
        ));
        tabel_filme_populare.setFocusable(false);
        tabel_filme_populare.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme_populare.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_filme_populare.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_filme_populare.setShowHorizontalLines(true);
        tabel_filme_populare.setShowVerticalLines(true);
        scroll_filme_populare.setViewportView(tabel_filme_populare);

        date_filme.add(scroll_filme_populare, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 690, 410, 120));

        scroll_ca_filme.setBorder(null);

        tabel_filme_ca.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme_ca.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_filme_ca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme_ca.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme_ca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Film", "AnAparitie", "Gen", "Durata"
            }
        ));
        tabel_filme_ca.setFocusable(false);
        tabel_filme_ca.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme_ca.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_filme_ca.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_filme_ca.setShowHorizontalLines(true);
        tabel_filme_ca.setShowVerticalLines(true);
        scroll_ca_filme.setViewportView(tabel_filme_ca);
        if (tabel_filme_ca.getColumnModel().getColumnCount() > 0) {
            tabel_filme_ca.getColumnModel().getColumn(2).setHeaderValue("Gen");
            tabel_filme_ca.getColumnModel().getColumn(3).setHeaderValue("Durata");
        }

        date_filme.add(scroll_ca_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1020, 670, 120));

        label_cautare_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_cautare_r.setForeground(new java.awt.Color(255, 255, 255));
        label_cautare_r.setText("Cautare film dupa regizor (afisarea se face in ordinea descrescatoare duratei filmului):");
        date_filme.add(label_cautare_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        label_cautare_r2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_cautare_r2.setForeground(new java.awt.Color(255, 255, 255));
        label_cautare_r2.setText("Cautare actori dupa regizor (se vor afisa actorii care au colaborat cu un anumit regior):");
        date_filme.add(label_cautare_r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, -1, -1));

        cautare_r.setBackground(new java.awt.Color(255, 255, 255));
        cautare_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cautare_r.setForeground(new java.awt.Color(220, 20, 60));
        cautare_r.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cautare_r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cautare_rActionPerformed(evt);
            }
        });
        cautare_r.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cautare_rKeyReleased(evt);
            }
        });
        date_filme.add(cautare_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, 230, 20));

        myList_r.setBackground(new java.awt.Color(255, 255, 255));
        myList_r.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        myList_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        myList_r.setFocusable(false);
        myList_r.setRequestFocusEnabled(false);
        myList_r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                myList_rMouseClicked(evt);
            }
        });
        scroll_mylist_r.setViewportView(myList_r);

        date_filme.add(scroll_mylist_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 230, -1));

        caut_actor_b.setBackground(new java.awt.Color(255, 255, 255));
        caut_actor_b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        caut_actor_b.setForeground(new java.awt.Color(0, 0, 0));
        caut_actor_b.setText("Cauta Actor");
        caut_actor_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        caut_actor_b.setBorderPainted(false);
        caut_actor_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        caut_actor_b.setRequestFocusEnabled(false);
        caut_actor_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caut_actor_bActionPerformed(evt);
            }
        });
        date_filme.add(caut_actor_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 630, 110, 30));

        caut_film_b.setBackground(new java.awt.Color(255, 255, 255));
        caut_film_b.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        caut_film_b.setForeground(new java.awt.Color(0, 0, 0));
        caut_film_b.setText("Cauta Film");
        caut_film_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        caut_film_b.setBorderPainted(false);
        caut_film_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        caut_film_b.setRequestFocusEnabled(false);
        caut_film_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caut_film_bActionPerformed(evt);
            }
        });
        date_filme.add(caut_film_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 630, 100, 30));

        scroll_cr_actori.setBorder(null);

        tabel_cr_actori.setBackground(new java.awt.Color(255, 255, 255));
        tabel_cr_actori.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_cr_actori.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_cr_actori.setForeground(new java.awt.Color(0, 0, 0));
        tabel_cr_actori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Actor", "Nationalitate", "Rol Memorabil"
            }
        ));
        tabel_cr_actori.setFocusable(false);
        tabel_cr_actori.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_cr_actori.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_cr_actori.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_cr_actori.setShowHorizontalLines(true);
        tabel_cr_actori.setShowVerticalLines(true);
        scroll_cr_actori.setViewportView(tabel_cr_actori);

        date_filme.add(scroll_cr_actori, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 670, 120));

        scroll_cr_filme.setBorder(null);

        tabel_filme_cr.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme_cr.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_filme_cr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme_cr.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme_cr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume Film", "AnAparitie", "Gen", "Durata"
            }
        ));
        tabel_filme_cr.setFocusable(false);
        tabel_filme_cr.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme_cr.setSelectionBackground(new java.awt.Color(204, 204, 204));
        tabel_filme_cr.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_filme_cr.setShowHorizontalLines(true);
        tabel_filme_cr.setShowVerticalLines(true);
        scroll_cr_filme.setViewportView(tabel_filme_cr);
        if (tabel_filme_cr.getColumnModel().getColumnCount() > 0) {
            tabel_filme_cr.getColumnModel().getColumn(3).setHeaderValue("Durata");
        }

        date_filme.add(scroll_cr_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 670, 120));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 40, 44));
        jLabel7.setText("An");
        date_filme.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(52, 40, 44));
        jLabel8.setText("Gen");
        date_filme.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(52, 40, 44));
        jLabel9.setText("Durata");
        date_filme.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(52, 40, 44));
        jLabel10.setText("Regizor");
        date_filme.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(52, 40, 44));
        jLabel11.setText("Distributie");
        date_filme.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(52, 40, 44));
        jLabel12.setText("Tara");
        date_filme.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(52, 40, 44));
        jLabel13.setText("Descriere");
        date_filme.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 430, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator1.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 300, 10));

        jSeparator2.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator2.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 300, 10));

        jSeparator3.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator3.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 300, 10));

        jSeparator4.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator4.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 300, 10));

        jSeparator5.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator5.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 300, -1));

        jSeparator6.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator6.setForeground(new java.awt.Color(52, 40, 44));
        date_filme.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, 300, 10));

        l_an_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_an_f.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(l_an_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 90, -1));

        l_gen_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_gen_f.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(l_gen_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 170, -1));

        l_durata_f.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        l_durata_f.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(l_durata_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 140, -1));

        l_regizor_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_regizor_f.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(l_regizor_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 160, -1));

        l_tara_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_tara_f.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(l_tara_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 150, -1));

        l_distributie.setEditable(false);
        l_distributie.setBackground(new java.awt.Color(220, 20, 60));
        l_distributie.setColumns(20);
        l_distributie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        l_distributie.setForeground(new java.awt.Color(255, 255, 255));
        l_distributie.setLineWrap(true);
        l_distributie.setRows(2);
        l_distributie.setWrapStyleWord(true);
        l_distributie.setBorder(null);
        l_distributie.setFocusable(false);
        l_distributie.setOpaque(false);
        date_filme.add(l_distributie, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nr. filme regizate:");
        date_filme.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 830, -1, -1));

        nr_filme_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nr_filme_r.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(nr_filme_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 830, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nr. actori cu care a colaborat:");
        date_filme.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 860, -1, -1));

        nr_actori_r.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nr_actori_r.setForeground(new java.awt.Color(255, 255, 255));
        date_filme.add(nr_actori_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 860, 80, -1));

        l_descriere_f.setEditable(false);
        l_descriere_f.setBackground(new java.awt.Color(220, 20, 60));
        l_descriere_f.setColumns(30);
        l_descriere_f.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        l_descriere_f.setForeground(new java.awt.Color(255, 255, 255));
        l_descriere_f.setLineWrap(true);
        l_descriere_f.setRows(5);
        l_descriere_f.setWrapStyleWord(true);
        l_descriere_f.setBorder(null);
        l_descriere_f.setFocusable(false);
        l_descriere_f.setOpaque(false);
        date_filme.add(l_descriere_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, -1, 50));

        jSeparator8.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator8.setForeground(new java.awt.Color(52, 40, 44));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        date_filme.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 10, 1170));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Afisarea celor mai vizionate filme din anul: ");
        date_filme.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 600, -1, -1));

        text_an_f.setBackground(new java.awt.Color(255, 255, 255));
        text_an_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_an_f.setForeground(new java.awt.Color(220, 20, 60));
        text_an_f.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        text_an_f.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        date_filme.add(text_an_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 600, 60, -1));

        b_cutare_filme_popurare.setBackground(new java.awt.Color(255, 255, 255));
        b_cutare_filme_popurare.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        b_cutare_filme_popurare.setForeground(new java.awt.Color(0, 0, 0));
        b_cutare_filme_popurare.setText("Cauta");
        b_cutare_filme_popurare.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_cutare_filme_popurare.setBorderPainted(false);
        b_cutare_filme_popurare.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_cutare_filme_popurare.setFocusPainted(false);
        b_cutare_filme_popurare.setFocusable(false);
        b_cutare_filme_popurare.setOpaque(true);
        b_cutare_filme_popurare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cutare_filme_popurareActionPerformed(evt);
            }
        });
        date_filme.add(b_cutare_filme_popurare, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 670, 70, 20));

        scroll_date_film.setViewportView(date_filme);

        dashbord.add(scroll_date_film, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 830));

        lista_filme.setBackground(new java.awt.Color(255, 255, 255));
        lista_filme.setBorder(null);
        lista_filme.setForeground(new java.awt.Color(0, 0, 0));
        lista_filme.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        lista_filme.setToolTipText("\n");
        lista_filme.setAutoscrolls(true);

        scroll_panel.setBackground(new java.awt.Color(0, 0, 0));
        scroll_panel.setPreferredSize(new java.awt.Dimension(1240, 1740));
        scroll_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bara_cautare_f.setBackground(new java.awt.Color(255, 255, 255));
        bara_cautare_f.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bara_cautare_f.setForeground(new java.awt.Color(51, 51, 51));
        bara_cautare_f.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        bara_cautare_f.setOpaque(true);
        bara_cautare_f.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bara_cautare_fActionPerformed(evt);
            }
        });
        scroll_panel.add(bara_cautare_f, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 310, 30));

        jScrollPane_lista_cautare_filme.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        list_cautare_filme.setBackground(new java.awt.Color(255, 255, 255));
        list_cautare_filme.setBorder(null);
        list_cautare_filme.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane_lista_cautare_filme.setViewportView(list_cautare_filme);

        scroll_panel.add(jScrollPane_lista_cautare_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 310, -1));

        l_aventura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_aventura.setForeground(new java.awt.Color(255, 255, 255));
        l_aventura.setText("Aventura");
        scroll_panel.add(l_aventura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, 20));

        l_actiune.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_actiune.setForeground(new java.awt.Color(255, 255, 255));
        l_actiune.setText("Actiune");
        scroll_panel.add(l_actiune, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 70, -1));

        l_sf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_sf.setForeground(new java.awt.Color(255, 255, 255));
        l_sf.setText("SF");
        scroll_panel.add(l_sf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        l_drama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_drama.setForeground(new java.awt.Color(255, 255, 255));
        l_drama.setText("Drama");
        scroll_panel.add(l_drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 790, -1, -1));

        l_comedie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_comedie.setForeground(new java.awt.Color(255, 255, 255));
        l_comedie.setText("Comedie");
        scroll_panel.add(l_comedie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1030, -1, -1));

        l_istoric.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_istoric.setForeground(new java.awt.Color(255, 255, 255));
        l_istoric.setText("Istoric");
        scroll_panel.add(l_istoric, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1270, 100, 20));

        l_biografic.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        l_biografic.setForeground(new java.awt.Color(255, 255, 255));
        l_biografic.setText("Biografic");
        scroll_panel.add(l_biografic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1510, -1, -1));

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

        scroll_panel.add(scroll_biografic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1540, 1220, 180));

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

        scroll_panel.add(scroll_istoric, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1300, 1220, 180));

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

        scroll_panel.add(scroll_comedie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 1060, 1220, 180));

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

        scroll_panel.add(scroll_drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 820, 1220, 180));

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

        scroll_panel.add(scroll_sf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 1220, 180));

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

        scroll_panel.add(scroll_actiune, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1220, 180));

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
            .addGap(0, 371, Short.MAX_VALUE)
        );

        scroll_aventura.setViewportView(scroll_f_aventura);

        scroll_panel.add(scroll_aventura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1220, 180));

        buton_cauta.setBackground(new java.awt.Color(220, 20, 60));
        buton_cauta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buton_cauta.setForeground(new java.awt.Color(255, 255, 255));
        buton_cauta.setText("Cauta");
        buton_cauta.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_cauta.setContentAreaFilled(false);
        buton_cauta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_cauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_cautaActionPerformed(evt);
            }
        });
        scroll_panel.add(buton_cauta, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 70, 30));

        lista_filme.setViewportView(scroll_panel);

        dashbord.add(lista_filme, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 800));

        scroll_date_utilizator.setBackground(new java.awt.Color(255, 255, 255));
        scroll_date_utilizator.setBorder(null);
        scroll_date_utilizator.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_date_utilizator.setAutoscrolls(true);

        date_utilizator.setBackground(new java.awt.Color(220, 20, 60));
        date_utilizator.setPreferredSize(new java.awt.Dimension(1250, 900));
        date_utilizator.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Utilizatori");
        date_utilizator.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 150, 50));

        jScrollPane1.setBorder(null);

        tabel_utilizator.setBackground(new java.awt.Color(255, 255, 255));
        tabel_utilizator.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_utilizator.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_utilizator.setForeground(new java.awt.Color(0, 0, 0));
        tabel_utilizator.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume", "Prenume", "Adresa", "Email", "DataAbonarii"
            }
        ));
        tabel_utilizator.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_utilizator.setRequestFocusEnabled(false);
        tabel_utilizator.setRowHeight(25);
        tabel_utilizator.setSelectionBackground(new java.awt.Color(203, 191, 191));
        tabel_utilizator.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tabel_utilizator);

        date_utilizator.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 890, 280));

        background_vd.setBackground(new java.awt.Color(255, 255, 255));
        background_vd.setLayout(new java.awt.BorderLayout());

        viz_date_b.setBackground(new java.awt.Color(255, 255, 255));
        viz_date_b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viz_date_b.setForeground(new java.awt.Color(220, 20, 60));
        viz_date_b.setText("Actualizare Tabel");
        viz_date_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        viz_date_b.setBorderPainted(false);
        viz_date_b.setContentAreaFilled(false);
        viz_date_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viz_date_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viz_date_bActionPerformed(evt);
            }
        });
        background_vd.add(viz_date_b, java.awt.BorderLayout.CENTER);

        date_utilizator.add(background_vd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 160, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Vizualizare genuri filme urmarite de un utilizator:");
        date_utilizator.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, -1, -1));

        background_selectare_date.setBackground(new java.awt.Color(255, 255, 255));
        background_selectare_date.setLayout(new java.awt.BorderLayout());

        selectare_date_b.setBackground(new java.awt.Color(255, 255, 255));
        selectare_date_b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectare_date_b.setForeground(new java.awt.Color(220, 20, 60));
        selectare_date_b.setText("Selectare Date");
        selectare_date_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        selectare_date_b.setBorderPainted(false);
        selectare_date_b.setContentAreaFilled(false);
        selectare_date_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectare_date_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectare_date_bActionPerformed(evt);
            }
        });
        background_selectare_date.add(selectare_date_b, java.awt.BorderLayout.CENTER);

        date_utilizator.add(background_selectare_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 150, 30));

        background_vg.setBackground(new java.awt.Color(255, 255, 255));
        background_vg.setLayout(new java.awt.BorderLayout());

        viz_gen_b.setBackground(new java.awt.Color(255, 255, 255));
        viz_gen_b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viz_gen_b.setForeground(new java.awt.Color(220, 20, 60));
        viz_gen_b.setText("Afisare Genuri");
        viz_gen_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        viz_gen_b.setBorderPainted(false);
        viz_gen_b.setContentAreaFilled(false);
        viz_gen_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viz_gen_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viz_gen_bActionPerformed(evt);
            }
        });
        background_vg.add(viz_gen_b, java.awt.BorderLayout.CENTER);

        date_utilizator.add(background_vg, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, 150, 30));

        text_nume.setBackground(new java.awt.Color(255, 255, 255));
        text_nume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_nume.setForeground(new java.awt.Color(0, 0, 0));
        text_nume.setText("Nume");
        text_nume.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        text_nume.setFocusable(false);
        text_nume.setRequestFocusEnabled(false);
        date_utilizator.add(text_nume, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 560, 160, -1));

        text_prenume.setBackground(new java.awt.Color(255, 255, 255));
        text_prenume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_prenume.setForeground(new java.awt.Color(0, 0, 0));
        text_prenume.setText("Prenume");
        text_prenume.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        text_prenume.setFocusable(false);
        text_prenume.setRequestFocusEnabled(false);
        date_utilizator.add(text_prenume, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 160, -1));

        text_adresa.setBackground(new java.awt.Color(255, 255, 255));
        text_adresa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_adresa.setForeground(new java.awt.Color(0, 0, 0));
        text_adresa.setText("Adresa");
        text_adresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        text_adresa.setFocusable(false);
        text_adresa.setRequestFocusEnabled(false);
        date_utilizator.add(text_adresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, 160, -1));

        tabel_gen_u.setBackground(new java.awt.Color(255, 255, 255));
        tabel_gen_u.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_gen_u.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_gen_u.setForeground(new java.awt.Color(0, 0, 0));
        tabel_gen_u.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Gen Film", "Nr Filme Din Acest Gen"
            }
        ));
        tabel_gen_u.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_gen_u.setSelectionBackground(new java.awt.Color(203, 191, 191));
        jScrollPane4.setViewportView(tabel_gen_u);

        date_utilizator.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, 380, 160));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Cautare filme dupa gen:");
        date_utilizator.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("<html>Recomandare filme in functie de genul cel mai vizionat de utilizator</html>");
        date_utilizator.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 820, 180, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("<html>Afisarea filmelor vizionate de catre un utilizator in functie de gen </html>");
        date_utilizator.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 820, 200, 40));

        text_gen.setBackground(new java.awt.Color(255, 255, 255));
        text_gen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_gen.setForeground(new java.awt.Color(0, 0, 0));
        text_gen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        text_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_genActionPerformed(evt);
            }
        });
        date_utilizator.add(text_gen, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 560, 140, -1));

        background_cg.setBackground(new java.awt.Color(255, 255, 255));
        background_cg.setLayout(new java.awt.BorderLayout());

        buton_viz_filme.setBackground(new java.awt.Color(255, 255, 255));
        buton_viz_filme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buton_viz_filme.setForeground(new java.awt.Color(220, 20, 60));
        buton_viz_filme.setText("Afisare Genuri");
        buton_viz_filme.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buton_viz_filme.setBorderPainted(false);
        buton_viz_filme.setContentAreaFilled(false);
        buton_viz_filme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buton_viz_filme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buton_viz_filmeActionPerformed(evt);
            }
        });
        background_cg.add(buton_viz_filme, java.awt.BorderLayout.CENTER);

        date_utilizator.add(background_cg, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 610, 150, 30));

        tabel_filme_recomandate.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme_recomandate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme_recomandate.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme_recomandate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filme Recomandate"
            }
        ));
        tabel_filme_recomandate.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme_recomandate.setSelectionBackground(new java.awt.Color(203, 191, 191));
        jScrollPane6.setViewportView(tabel_filme_recomandate);

        date_utilizator.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 660, 240, 160));

        tabel_filme_vizionate.setBackground(new java.awt.Color(255, 255, 255));
        tabel_filme_vizionate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabel_filme_vizionate.setForeground(new java.awt.Color(0, 0, 0));
        tabel_filme_vizionate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Filme Vizionate"
            }
        ));
        tabel_filme_vizionate.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_filme_vizionate.setSelectionBackground(new java.awt.Color(203, 191, 191));
        jScrollPane5.setViewportView(tabel_filme_vizionate);

        date_utilizator.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 660, 240, 160));

        scroll_date_utilizator.setViewportView(date_utilizator);
        date_utilizator.getAccessibleContext().setAccessibleName("");

        dashbord.add(scroll_date_utilizator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 1260, 830));

        date_abonamente.setBackground(new java.awt.Color(220, 20, 60));
        date_abonamente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_abonamente.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        label_abonamente.setForeground(new java.awt.Color(255, 255, 255));
        label_abonamente.setText("Abonamente");
        date_abonamente.add(label_abonamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 210, 50));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        tabel_abonament.setBackground(new java.awt.Color(255, 255, 255));
        tabel_abonament.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_abonament.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_abonament.setForeground(new java.awt.Color(51, 51, 51));
        tabel_abonament.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tipAbonament", "plataLunara(lei)"
            }
        ));
        tabel_abonament.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_abonament.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_abonament.setRowHeight(30);
        tabel_abonament.setSelectionBackground(new java.awt.Color(245, 219, 219));
        tabel_abonament.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_abonament.setShowGrid(false);
        tabel_abonament.setShowHorizontalLines(true);
        jScrollPane2.setViewportView(tabel_abonament);

        date_abonamente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 660, 120));

        background_vza2.setBackground(new java.awt.Color(255, 255, 255));
        background_vza2.setLayout(new java.awt.BorderLayout());

        selectare_date_abonament.setBackground(new java.awt.Color(255, 255, 255));
        selectare_date_abonament.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        selectare_date_abonament.setForeground(new java.awt.Color(220, 20, 60));
        selectare_date_abonament.setText("Selecteaza Abonament");
        selectare_date_abonament.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        selectare_date_abonament.setBorderPainted(false);
        selectare_date_abonament.setContentAreaFilled(false);
        selectare_date_abonament.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selectare_date_abonament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectare_date_abonamentActionPerformed(evt);
            }
        });
        background_vza2.add(selectare_date_abonament, java.awt.BorderLayout.CENTER);

        date_abonamente.add(background_vza2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 190, 30));

        background_vza1.setBackground(new java.awt.Color(255, 255, 255));
        background_vza1.setLayout(new java.awt.BorderLayout());

        afisare_descriere.setBackground(new java.awt.Color(255, 255, 255));
        afisare_descriere.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        afisare_descriere.setForeground(new java.awt.Color(220, 20, 60));
        afisare_descriere.setText("Descriere Abonament");
        afisare_descriere.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        afisare_descriere.setBorderPainted(false);
        afisare_descriere.setContentAreaFilled(false);
        afisare_descriere.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        afisare_descriere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afisare_descriereActionPerformed(evt);
            }
        });
        background_vza1.add(afisare_descriere, java.awt.BorderLayout.CENTER);

        date_abonamente.add(background_vza1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 190, 30));

        background_vza.setBackground(new java.awt.Color(255, 255, 255));
        background_vza.setLayout(new java.awt.BorderLayout());

        vza_b.setBackground(new java.awt.Color(255, 255, 255));
        vza_b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vza_b.setForeground(new java.awt.Color(220, 20, 60));
        vza_b.setText("Vizualizare Abonamente");
        vza_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        vza_b.setBorderPainted(false);
        vza_b.setContentAreaFilled(false);
        vza_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vza_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vza_bActionPerformed(evt);
            }
        });
        background_vza.add(vza_b, java.awt.BorderLayout.CENTER);

        date_abonamente.add(background_vza, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 190, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Afiseaza numarul de persoane care folosesc un abonament");
        date_abonamente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 380, -1));

        background_rez.setBackground(new java.awt.Color(255, 255, 255));
        background_rez.setLayout(new java.awt.BorderLayout());

        rez_b.setBackground(new java.awt.Color(0, 0, 0));
        rez_b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rez_b.setForeground(new java.awt.Color(220, 20, 60));
        rez_b.setText("Rezultat");
        rez_b.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rez_b.setBorderPainted(false);
        rez_b.setContentAreaFilled(false);
        rez_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rez_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rez_bActionPerformed(evt);
            }
        });
        background_rez.add(rez_b, java.awt.BorderLayout.CENTER);

        date_abonamente.add(background_rez, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, 30));

        jScrollPane7.setBorder(null);

        tabel_restantieri.setBackground(new java.awt.Color(255, 255, 255));
        tabel_restantieri.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_restantieri.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_restantieri.setForeground(new java.awt.Color(0, 0, 0));
        tabel_restantieri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nume", "Prenume"
            }
        ));
        tabel_restantieri.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_restantieri.setRequestFocusEnabled(false);
        tabel_restantieri.setRowHeight(30);
        tabel_restantieri.setSelectionBackground(new java.awt.Color(245, 219, 219));
        tabel_restantieri.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_restantieri.setShowHorizontalLines(true);
        jScrollPane7.setViewportView(tabel_restantieri);

        date_abonamente.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 660, 110));

        jScrollPane3.setBorder(null);

        tabel_au.setBackground(new java.awt.Color(255, 255, 255));
        tabel_au.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tabel_au.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabel_au.setForeground(new java.awt.Color(0, 0, 0));
        tabel_au.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Abonament", "NrUtilizatori"
            }
        ));
        tabel_au.setGridColor(new java.awt.Color(255, 255, 255));
        tabel_au.setRequestFocusEnabled(false);
        tabel_au.setRowHeight(30);
        tabel_au.setSelectionBackground(new java.awt.Color(245, 219, 219));
        tabel_au.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabel_au.setShowHorizontalLines(true);
        jScrollPane3.setViewportView(tabel_au);

        date_abonamente.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 660, 110));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Afiseaza utilizatorii care nu au platit in functie de abonament ");
        date_abonamente.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Selecteaza abonamentul:");
        date_abonamente.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        combobox_abonamente.setBackground(new java.awt.Color(255, 255, 255));
        combobox_abonamente.setEditable(true);
        combobox_abonamente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combobox_abonamente.setForeground(new java.awt.Color(0, 0, 0));
        combobox_abonamente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Abonament --" }));
        combobox_abonamente.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        combobox_abonamente.setFocusable(false);
        combobox_abonamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_abonamenteActionPerformed(evt);
            }
        });
        date_abonamente.add(combobox_abonamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 150, -1));

        label_descriere_abonament.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        label_descriere_abonament.setForeground(new java.awt.Color(255, 255, 255));
        date_abonamente.add(label_descriere_abonament, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 190, 100));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Descriere Abonament");
        date_abonamente.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Modifica Abonament");
        date_abonamente.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, -1, -1));

        jSeparator7.setBackground(new java.awt.Color(52, 40, 44));
        jSeparator7.setForeground(new java.awt.Color(52, 40, 44));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        date_abonamente.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, -1, 510));

        text_pret.setBackground(new java.awt.Color(255, 255, 255));
        text_pret.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_pret.setForeground(new java.awt.Color(0, 0, 0));
        text_pret.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        date_abonamente.add(text_pret, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 230, 30));

        text_descriere.setBackground(new java.awt.Color(255, 255, 255));
        text_descriere.setColumns(20);
        text_descriere.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text_descriere.setForeground(new java.awt.Color(0, 0, 0));
        text_descriere.setLineWrap(true);
        text_descriere.setRows(5);
        text_descriere.setWrapStyleWord(true);
        text_descriere.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        date_abonamente.add(text_descriere, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 230, 60));

        background_rez1.setBackground(new java.awt.Color(0, 0, 0));
        background_rez1.setLayout(new java.awt.BorderLayout());

        b_modifica.setBackground(new java.awt.Color(0, 0, 0));
        b_modifica.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b_modifica.setForeground(new java.awt.Color(220, 20, 60));
        b_modifica.setText("Modifica Date");
        b_modifica.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        b_modifica.setBorderPainted(false);
        b_modifica.setContentAreaFilled(false);
        b_modifica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        b_modifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_modificaActionPerformed(evt);
            }
        });
        background_rez1.add(b_modifica, java.awt.BorderLayout.CENTER);

        date_abonamente.add(background_rez1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 340, 170, 30));

        mesaj_modificare_succes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesaj_modificare_succes.setForeground(new java.awt.Color(0, 0, 0));
        mesaj_modificare_succes.setText("Modificare s-a realizat cu succes!");
        date_abonamente.add(mesaj_modificare_succes, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 610, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tip Abonament");
        date_abonamente.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Pret Abonament");
        date_abonamente.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Desciere Abonament");
        date_abonamente.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, -1, -1));

        label_abonament.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_abonament.setForeground(new java.awt.Color(255, 255, 255));
        date_abonamente.add(label_abonament, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, 240, 50));

        dashbord.add(date_abonamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1250, 800));

        panou_info.setBackground(new java.awt.Color(0, 0, 0));
        panou_info.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 40, 44)));
        panou_info.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bine ai venit!");
        panou_info.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 120, 60));

        dashbord.add(panou_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 0, 190, 900));

        getContentPane().add(dashbord, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void ComboBoxR(){ // combo box  in care se gasesc regizorii deja existenti in baza de date
        try {
            // conectare la baza de date
            Connection con = ConectareBD.bdConectare(); 
            Statement stm = con.createStatement();
            
            String select = "select nume from regizor;";
            ResultSet rs = stm.executeQuery(select);
            
            cb_regizor.addItem("--Alege Regizor--");
            while(rs.next()){ // adauga regizor din baza de date in combo box
                cb_regizor.addItem(rs.getString("nume"));
            }
        } catch (Exception e) {
            System.out.print("Ceva nu a mers bine ");
        }
    }
    
    public void ComboBoxA(){ // combo box  in care se gasesc actorii deja existenti in baza de date
        try {
            //conectare la baza de date
            Connection con = ConectareBD.bdConectare(); 
            Statement stm = con.createStatement();
            
            String select = "select nume from actor;";
            ResultSet rs = stm.executeQuery(select);
            
            // adauga actor din baza de date in combo box
            cb_a1.addItem("--Alege Actor1--");
            cb_a2.addItem("--Alege Actor2--");
            cb_a3.addItem("--Alege Actor3--");
            while(rs.next()){
                cb_a1.addItem(rs.getString("nume"));
                cb_a2.addItem(rs.getString("nume"));
                cb_a3.addItem(rs.getString("nume"));
            }
        } catch (Exception e) {
            System.out.print("Ceva nu a mers bine ");
        }
    }
    
    public void changecolor(Panel fundal, Color rand){
           fundal.setBackground(rand);
    } 
     // setari in legatura cu bara in care se gasesc butonul de exit, de minimizare aplicatie
    private void XmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseEntered
        changecolor(butonX, new Color(128,0,0));
    }//GEN-LAST:event_XmouseMouseEntered

    private void XmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseExited
        changecolor(butonX, new Color(0,0,0));
    }//GEN-LAST:event_XmouseMouseExited

    private void XmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XmouseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_XmouseMouseClicked

    private void MaxmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseEntered
        changecolor(butonMax, new Color(128,0,0));
    }//GEN-LAST:event_MaxmouseMouseEntered

    private void MaxmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseExited
        changecolor(butonMax, new Color(0,0,0));
    }//GEN-LAST:event_MaxmouseMouseExited

    private void MaxmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxmouseMouseClicked
        if (this.getExtendedState() != MAXIMIZED_BOTH){
            this.setExtendedState(MAXIMIZED_BOTH);
        }
        else this.setExtendedState(NORMAL);
    }//GEN-LAST:event_MaxmouseMouseClicked

    private void MinmouseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseEntered
        changecolor(butonMin, new Color(128,0,0));
    }//GEN-LAST:event_MinmouseMouseEntered

    private void MinmouseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseExited
        changecolor(butonMin, new Color(0,0,0));
    }//GEN-LAST:event_MinmouseMouseExited

    private void MinmouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MinmouseMouseClicked
        setState(ICONIFIED);
    }//GEN-LAST:event_MinmouseMouseClicked

    
    // funtie ce face extensie pentru meniu  cand unul dintre acele "butone" este apasat se extinde, aparand mai multe optiuni, sau se ascunde din nou daca este apasat
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

    // funtie ce face extensie pentru profil utilizator  cand unul dintre acele "butone" este apasat se extinde, aparand mai multe optiuni, sau se ascunde din nou daca este apasat
    private void profilImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilImgMouseClicked
        if(a == true){
            hidemenu2(meniu,extensie_meniu,extensie_profil, a);
            //all_scrollbar();
            butonProfil.setBackground(new Color(255, 95, 31 ));
            SwingUtilities.updateComponentTreeUI(this);
            a = false;
            all_scrollbar();
            lista_filme.setVisible(true);
        }
        else {
            hidemenu2(meniu,extensie_meniu, extensie_profil,  a);
           // all_scrollbar();
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

    //buton de imi deschide fereastra de setari, unde pot sa adaug, sterg, sa fac update la filme, regizori si actori
    private void buton_setariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buton_setariMouseClicked
        lista_filme.setVisible(false);
        scroll_add_film.setVerticalScrollBar(new ScrollBarCustom());
        scroll_add_film.setVisible(true);
        date_abonamente.setVisible(false);
        scroll_date_film.setVisible(false);
        scroll_date_utilizator.setVisible(false);
    }//GEN-LAST:event_buton_setariMouseClicked

    // setari pentru casetele text referitoare la regizor pentru a avea o interfata mai draguta cu utilizatorul
    private void numeregizorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numeregizorFocusGained
        numeregizor.setText("");
    }//GEN-LAST:event_numeregizorFocusGained

    private void nationalitateregizorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nationalitateregizorFocusGained
        nationalitateregizor.setText("");
    }//GEN-LAST:event_nationalitateregizorFocusGained

    private void nrpremiiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrpremiiFocusGained
        nrpremii.setText("");
    }//GEN-LAST:event_nrpremiiFocusGained

    private void numeregizorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numeregizorFocusLost
         String numeVal = numeregizor.getText();
        
        if(numeVal.equals("Nume") || numeVal.equals("")){
            numeregizor.setText("Nume");
        }
    }//GEN-LAST:event_numeregizorFocusLost

    private void nationalitateregizorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nationalitateregizorFocusLost
         String numeVal = nationalitateregizor.getText();
        
        if(numeVal.equals("Nationalitate") || numeVal.equals("")){
            nationalitateregizor.setText("Nationalitate");
        }
    }//GEN-LAST:event_nationalitateregizorFocusLost

    private void nrpremiiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nrpremiiFocusLost
         String numeVal = nrpremii.getText();
        
        if(numeVal.equals("Numar Premii Castigate") || numeVal.equals("")){
            nrpremii.setText("Numar Premii Castigate");
        }
    }//GEN-LAST:event_nrpremiiFocusLost

    // buton pentru a deschide feresatra pentru afisare date utilizatori
    private void buton_utilizatoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_utilizatoriActionPerformed
        lista_filme.setVisible(false);
        scroll_add_film.setVisible(false);
        date_abonamente.setVisible(false);
        scroll_date_film.setVisible(false);
        scroll_date_utilizator.setVerticalScrollBar(new ScrollBarCustom());
        scroll_date_utilizator.setVisible(true);
    }//GEN-LAST:event_buton_utilizatoriActionPerformed

    // aceasta funtie imi genereaza datele dorite despe utilizatori din baza de date in tabelul utilizatori ce se gasete in fereastra Utilizaori
    void update_table_utilizatori(){
         try {
             //conectare baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                // selectare date din baza de date
                String select = "select * from utilizator order by nume ASC;";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    //String id_u = String.valueOf(rs.getInt("utilizatorID"));
                    String nume_u = rs.getString("nume");
                    String prenume_u = rs.getString("prenume");
                    String adresa_u = rs.getString("adresa");
                    String email_u = rs.getString("email");
                    String dataAbonarii_u = String.valueOf(rs.getDate("dataAbonarii"));
                    
                    //date sunt adaugate in tabel
                    String date[] = {nume_u,prenume_u, adresa_u, email_u, dataAbonarii_u};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_utilizator.getModel();
                    tabel.addRow(date);
                }
               
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
     // setari pentru casetele text referitoare la filme pentru a avea o interfata mai draguta cu utilizatorul
    private void duratafilmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_duratafilmFocusLost
        String numeVal = duratafilm.getText();

        if(numeVal.equals("Durata Film (min)") || numeVal.equals("")){
            duratafilm.setText("Durata Film (min)");
        }
    }//GEN-LAST:event_duratafilmFocusLost

    private void duratafilmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_duratafilmFocusGained
        duratafilm.setText("");
    }//GEN-LAST:event_duratafilmFocusGained

    private void descriereFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriereFocusLost
        String numeVal = descriere.getText();

        if(numeVal.equals("Descriere") || numeVal.equals("")){
            descriere.setText("Descriere");
        }
    }//GEN-LAST:event_descriereFocusLost

    private void descriereFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriereFocusGained
        descriere.setText("");
    }//GEN-LAST:event_descriereFocusGained

    private void taraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_taraFocusLost
        String numeVal = tara.getText();

        if(numeVal.equals("Tara") || numeVal.equals("")){
            tara.setText("Tara");
        }
    }//GEN-LAST:event_taraFocusLost

    private void taraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_taraFocusGained
        tara.setText("");
    }//GEN-LAST:event_taraFocusGained

    private void genFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genFocusLost
        String numeVal = gen.getText();

        if(numeVal.equals("Gen") || numeVal.equals("")){
            gen.setText("Gen");
        }
    }//GEN-LAST:event_genFocusLost

    private void genFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genFocusGained
        gen.setText("");
    }//GEN-LAST:event_genFocusGained

    private void anaparitieFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_anaparitieFocusLost
        String numeVal = anaparitie.getText();

        if(numeVal.equals("An Aparitie") || numeVal.equals("")){
            anaparitie.setText("An Aparitie");
        }
    }//GEN-LAST:event_anaparitieFocusLost

    private void anaparitieFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_anaparitieFocusGained
        anaparitie.setText("");
    }//GEN-LAST:event_anaparitieFocusGained

    private void numefilmFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numefilmFocusLost
        String numeVal = numefilm.getText();

        if(numeVal.equals("Nume Film") || numeVal.equals("")){
            numefilm.setText("Nume Film");
        }
    }//GEN-LAST:event_numefilmFocusLost

    private void numefilmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numefilmFocusGained
        numefilm.setText("");
    }//GEN-LAST:event_numefilmFocusGained

    private void nrpremiiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nrpremiiActionPerformed
      
    }//GEN-LAST:event_nrpremiiActionPerformed

    private void cb_regizorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_regizorActionPerformed
        
    }//GEN-LAST:event_cb_regizorActionPerformed

    // buton de adaugare filme in baza de date
    private void b_ad_filmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ad_filmActionPerformed
        // sunt extrase informatiile despre film adaugate 
        String numeF = numefilm.getText();
        String anF = anaparitie.getText();
        String genF = gen.getText();
        String taraF = tara.getText();
        String descriereF = descriere.getText();
        String durataF = duratafilm.getText();
        String val_r = cb_regizor.getSelectedItem().toString();
        String val_a1 = cb_a1.getSelectedItem().toString();
        String val_a2 = cb_a2.getSelectedItem().toString();
        String val_a3 = cb_a3.getSelectedItem().toString();
       
        if(numeF.equals("Nume Film") || anF.equals("Anul Aparitiei") || genF.equals("Gen") || durataF.equals("Durata Film (min)"))
            mesaj_err_f.setVisible(true); // in cazul in care un camp nu eeste completat apare un mesaj err
        if(val_r == "--Alege Regizor--" || (val_a1 == "--Alege Actor1--" && val_a2 == "--Alege Actor2--" && val_a3 == "--Alege Actor3--"))
            mesaj_err_ra.setVisible(true); // in cazul in care regizorul sau actorii nu sunt selectati apare un mesaj de err
        else{
            try {
                // conctare la baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                Statement stm1 = con.createStatement();
                Statement stm2 = con.createStatement();
                Statement stm3 = con.createStatement();
                String select_r = "select regizorID from regizor where nume = '"+val_r+"';"; // se selecteaza un anumit ID pentru regizor in functie de valoarea introdusa 
                                                                                     // se gaseste ID regizorului pentru a a se face conexiunea cu filmul pe care il adaugam
                ResultSet rs_r = stm.executeQuery(select_r);
                
                int id_u = -1;
                if(rs_r.next()){
                    int an = Integer.parseInt(anF);
                    int durata = Integer.parseInt(durataF);
                    // INSERT filme: se adauga film si date despre acesta
                    String insert = "insert into filme(regizorID,numeFilm,anAparitie,gen,tara,descriere,durataFilm) values('"+rs_r.getInt(1)+"','"+numeF+"','"+an+"','"+genF+"','"+taraF+"','"+descriereF+"','"+durata+"');"; 
                    stm.executeUpdate(insert);   // se executa
                    
                    ResultSet rs_u = stm.executeQuery("select LAST_INSERT_ID() from filme;");
                    if (rs_u.next()) {
                        id_u = rs_u.getInt(1);
                    } 
                }
                
                String select_a = "select * from actor where nume = '"+val_a1+"';";
                ResultSet rs_a = stm1.executeQuery(select_a);
                
                if(rs_a.next()){
                    //String hhj = String.valueOf(rs_a.getInt("actorID"));
                    //System.out.println(hhj);
                    // se face inset si in tabelul de legatura dintre actor si filme: se ia ID filmului tocmai adaugat si id actorilor care au fost selectati in a juca in film
                    String insert = "insert into actor_has_filme values('"+id_u+"','"+rs_a.getInt(1)+"');";
                    stm1.executeUpdate(insert);
                }
                
                
                if(val_a2 != "--Alege Actor2--" || val_a2 != ""){
                    select_a = "select * from actor where nume = '"+val_a2+"';";
                    rs_a = stm2.executeQuery(select_a);
                    
                    if(rs_a.next()){
                        String insert = "insert into actor_has_filme values('"+id_u+"','"+rs_a.getInt(1)+"');";
                        stm2.executeUpdate(insert);
                    }
                }
                
                
                if(val_a3 != "--Alege Actor3--" || val_a3 != ""){
                    select_a = "select * from actor where nume = '"+val_a3+"';";
                    rs_a = stm3.executeQuery(select_a);
                
                    if(rs_a.next()){
                        String insert = "insert into actor_has_filme values('"+id_u+"','"+rs_a.getInt(1)+"');";
                        stm3.executeUpdate(insert);
                    }
                }
                   JOptionPane.showMessageDialog(null, "Success");
                   con.close(); 
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Success");
            }
        }
    }//GEN-LAST:event_b_ad_filmActionPerformed

    private void numeactorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numeactorFocusGained
        numeactor.setText("");
    }//GEN-LAST:event_numeactorFocusGained
  
    // setari pentru casetele text referitoare la actori pentru a avea o interfata mai draguta cu utilizatorul
    private void numeactorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numeactorFocusLost
        String numeVal = numeactor.getText();
        
        if(numeVal.equals("Nume") || numeVal.equals("")){
            numeactor.setText("Nume");
        }
    }//GEN-LAST:event_numeactorFocusLost

    private void rol_memorabilFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rol_memorabilFocusGained
        rol_memorabil.setText("");
    }//GEN-LAST:event_rol_memorabilFocusGained

    private void rol_memorabilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rol_memorabilFocusLost
        String numeVal = rol_memorabil.getText();
        
        if(numeVal.equals("Rol Memorabil") || numeVal.equals("")){
            rol_memorabil.setText("Rol Memorabil");
        }
    }//GEN-LAST:event_rol_memorabilFocusLost

    private void nationalitateactorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nationalitateactorFocusGained
        nationalitateactor.setText("");
    }//GEN-LAST:event_nationalitateactorFocusGained

    private void nationalitateactorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nationalitateactorFocusLost
        String numeVal = nationalitateactor.getText();
        
        if(numeVal.equals("Nationalitate") || numeVal.equals("")){
            nationalitateactor.setText("Nationalitate");
        }
    }//GEN-LAST:event_nationalitateactorFocusLost

    private void bX_raActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bX_raActionPerformed
       ad_regizor_actor.setVisible(false);
    }//GEN-LAST:event_bX_raActionPerformed

    private void b_ad_raActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ad_raActionPerformed
        ad_regizor_actor.setVisible(true);
    }//GEN-LAST:event_b_ad_raActionPerformed

    
    // buton de adaugare regizor in baza de date
    private void b_addRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addRActionPerformed
        String numeR = numeregizor.getText();
        String nationalitateR = nationalitateregizor.getText();
        //int nr_p = Integer.parseInt(nrpremii.getText());
        String nr_premii = nrpremii.getText();
        int nr_p = 0;
        System.out.println(nr_premii);
        if(nr_premii.equals("Numar Premii Castigate"))
            nr_p = 0;
        else 
            nr_p = Integer.parseInt(nrpremii.getText());
        System.out.println(numeR);
        if(numeR.equals("Nume")){
            mesaj_err_R.setVisible(true);
        System.out.println("bauuu");
        }
        else {
            try {
                Connection con = ConectareBD.bdConectare();
                Statement stm = con.createStatement();
                //INSERT regizor si date despre acesta
                String insert = "insert into regizor(nume, nationalitate, nrpremii) values('"+numeR+"', '"+nationalitateR+"', '"+nr_p+"');";
                stm.executeUpdate(insert);
                
                cb_regizor.removeAllItems();
                ComboBoxR();
                JOptionPane.showMessageDialog(null, "Succes");
                    
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
              }   
        }
    }//GEN-LAST:event_b_addRActionPerformed

    
    // buton de adagare actori in baza de date
    private void b_addAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_addAActionPerformed
        String numeA = numeactor.getText();
        String nationalitateA = nationalitateactor.getText();
        String rol = rol_memorabil.getText();
        
            if(numeA.equals("Nume")){
                 mesaj_err_A.setVisible(true);
            }
            else {
                try {

                    Connection con = ConectareBD.bdConectare();
                    Statement stm = con.createStatement();
                    //INSERT actori si daate despre acestia
                    String insert = "insert into actor(nume, nationalitate, rolMemorabil) values('"+numeA+"', '"+nationalitateA+"', '"+rol+"');";
                    stm.executeUpdate(insert);
                    
                    
                    cb_a1.removeAllItems();
                    cb_a2.removeAllItems();
                    cb_a3.removeAllItems();
                    ComboBoxA();
                    JOptionPane.showMessageDialog(null, "Succes");
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Not Succes");
                }
              
            }
    }//GEN-LAST:event_b_addAActionPerformed

    // aceasta functie imi ia datele selectate din baza de date abonamente si mi le afiseaza in aplicatie
    void update_table_abonamente(){
         try {
                // se conecteaza la baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                // selectare date din tabelul abonamente
                String select = "select * from abonament;";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    //String id_a = String.valueOf(rs.getInt("abonamentID"));
                    String tip_a = rs.getString("tipAbonament");
                    String pret_a = String.valueOf(rs.getFloat("plataLunara"));
                    
                    // se iau datele din baza de date si se pun in tabelele din aplicatie
                    String date[] = {tip_a, pret_a};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_abonament.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // buton vizualizare date despre abonamente, da refresh la tabel
    private void vza_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vza_bActionPerformed
        mesaj_modificare_succes.setVisible(false);
        text_descriere.setText("");
        text_pret.setText("");
        label_abonament.setText("");
        label_descriere_abonament.setText("");
        
        DefaultTableModel tabel = (DefaultTableModel)tabel_abonament.getModel();
        tabel.setRowCount(0);
        update_table_abonamente();
    }//GEN-LAST:event_vza_bActionPerformed

    // buton pentru a deschide fereastra Abonamente
    private void buton_abonamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_abonamenteActionPerformed
        lista_filme.setVisible(false);
        scroll_add_film.setVisible(false);
        date_abonamente.setVisible(true);
        scroll_date_film.setVisible(false);
        scroll_date_utilizator.setVisible(false);
    }//GEN-LAST:event_buton_abonamenteActionPerformed

    // functie ce imi genereaza intr-un tabel numarul de persoane pentru fiecare abonament
    void update_table_ua(){
    try {
        // conectare la baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //INTEROGARE SIMPLA afisare nr de abonati pentru fiecare abonament
                String select = "select A.tipAbonament as Abonament, count(B.utilizatorID) as NrUtilzatori from abonament A left join utilizator B on A.abonamentID = B.abonamentID group by A.tipAbonament";
                ResultSet rs = stm.executeQuery(select); // se executa comanda
                
                while(rs.next()){
                    String nume_a = rs.getString(1);
                    String nr_u = String.valueOf(rs.getInt(2));
                    
                    // se adauga datele in tabelul din aplicatie
                    String date[] = {nume_a, nr_u};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_au.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // buton de refrash date din tabelul utilizator - abonamente
    private void rez_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rez_bActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_au.getModel();
        tabel.setRowCount(0);
        update_table_ua();
        
    }//GEN-LAST:event_rez_bActionPerformed

    // buton de deconectare din aplicatie
    private void deconectareAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deconectareAMouseClicked
        new login().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_deconectareAMouseClicked

    // buton adauga date in  tabel filme
    void update_table_filme(JTable f){
            ok = 1;
            try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select numeFilm, anAparitie, gen from filme order by anAparitie DESC";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_f = rs.getString("numeFilm");
                    String an_f = rs.getString("anAparitie");
                    String gen_f = rs.getString("gen");
                    
                    String date[] = {nume_f, an_f, gen_f};
                    DefaultTableModel tabel = (DefaultTableModel)f.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    //buton adauga date in tabel regizori
    void update_table_regizori(JTable r){
            ok = 2;
            try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select * from regizor";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_r = rs.getString(2);
                    String nat_r = rs.getString(3);
                    String nr_p = String.valueOf(rs.getInt(4));
                    
                    String date[] = {nume_r, nat_r, nr_p};
                    DefaultTableModel tabel = (DefaultTableModel)r.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // buton adauga date in tabel actor
    void update_table_actor(JTable a){
            ok = 3;
            try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select * from actor";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_a = rs.getString(2);
                    String nat_a = rs.getString(3);
                    String rol_a = rs.getString(4);
                    
                    if(rol_a == null)
                        rol_a = "";
                    
                    String date[] = {nume_a, nat_a, rol_a};
                    DefaultTableModel tabel = (DefaultTableModel)a.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // boton pentru a deschide fereatra filme: unde se gasesc date depre filme
    private void buton_filmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buton_filmeMouseClicked
        lista_filme.setVisible(false);
        scroll_add_film.setVisible(false);
        date_abonamente.setVisible(false);
        scroll_date_film.setVerticalScrollBar(new ScrollBarCustom());
        scroll_date_film.setVisible(true);
        scroll_date_utilizator.setVisible(false);
    }//GEN-LAST:event_buton_filmeMouseClicked

    private void anaparitieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anaparitieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anaparitieActionPerformed

    private void buton_acasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buton_acasaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buton_acasaMouseClicked
   
    // buton care deschide fereastra principala
    private void buton_acasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_acasaActionPerformed
        all_scrollbar();
        lista_filme.setVisible(true);
        scroll_add_film.setVisible(false);
        date_abonamente.setVisible(false);
        scroll_date_film.setVisible(false);
        scroll_date_utilizator.setVisible(false);
    }//GEN-LAST:event_buton_acasaActionPerformed

    // buton refresh tabel filme, regizori, actori
    private void b_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_refreshActionPerformed
        if(ok == 1) {
            jScrollPane_filme.setVerticalScrollBar(new ScrollBarCustom());
            DefaultTableModel tabel = (DefaultTableModel)tabel_filme.getModel();
            tabel.setRowCount(0);
            update_table_filme(tabel_filme);
        }
        else if(ok == 2){
            jScrollPane_regizori.setVerticalScrollBar(new ScrollBarCustom());
            DefaultTableModel tabel = (DefaultTableModel)tabel_regizori.getModel();
            tabel.setRowCount(0);
            update_table_regizori(tabel_regizori);
        }
        else if(ok == 3){
            jScrollPane_actori.setVerticalScrollBar(new ScrollBarCustom());
            DefaultTableModel tabel = (DefaultTableModel)tabel_actori.getModel();
            tabel.setRowCount(0);
            update_table_actor(tabel_actori);
        }
        
    }//GEN-LAST:event_b_refreshActionPerformed

    private void cautare_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cautare_rActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cautare_rActionPerformed

    // buton vizualizare date film
    private void b_viz_filmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_filmeActionPerformed
        jScrollPane_filme.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_filme.setVisible(true);
        update_table_filme(tabel_filme);
        jScrollPane_regizori.setVisible(false);
        jScrollPane_actori.setVisible(false);
    }//GEN-LAST:event_b_viz_filmeActionPerformed

    // buton vizualizare date regizori
    private void b_viz_regizoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_regizoriActionPerformed
        jScrollPane_filme.setVisible(false);
        update_table_regizori(tabel_regizori);
        jScrollPane_regizori.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_regizori.setVisible(true);
        jScrollPane_actori.setVisible(false);
    }//GEN-LAST:event_b_viz_regizoriActionPerformed

    // buton vizualizare date actori
    private void b_viz_actoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_actoriActionPerformed
        jScrollPane_filme.setVisible(false);
        update_table_actor(tabel_actori);
        jScrollPane_regizori.setVisible(false);
        jScrollPane_actori.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_actori.setVisible(true);
    }//GEN-LAST:event_b_viz_actoriActionPerformed

    // creare lista de filme pentru bar de caurare filme din mediul Filme
    private ArrayList getList(String nume_t){
        ArrayList list_r = new ArrayList();
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                String select;
                if(nume_t.equals("filme"))
                    select = "select numeFilm from "+nume_t+"";
                else
                    select = "select nume from "+nume_t+"";
                ResultSet rs = stm.executeQuery(select);
                
                String nume_r;
                while(rs.next()){
                    
                    if(nume_t.equals("filme"))
                        nume_r = rs.getString("numeFilm");
                    else
                        nume_r = rs.getString("nume");
                    
                    list_r.add(nume_r);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
        return list_r;
    }
    
    // functie pentru adaugare elemente in lista
    private void bindData(JList list, String nume_t){
        getList(nume_t).stream().forEach((r) -> {
            defaultListModel.addElement(r);
        });
        list.setModel(defaultListModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    // functie de cautare in lista
    private void search(String searchE, String nume_t, JScrollPane s, JList l){
        if(searchE.equals("")){
            s.setVisible(false);
        }else{
        s.setVisible(true);
        s.setVerticalScrollBar(new ScrollBarCustom());
        
        DefaultListModel elem = new DefaultListModel();
        ArrayList list_r = getList(nume_t);
        
        list_r.stream().forEach((r) -> {
            String r_nume = r.toString().toLowerCase();
            if(r_nume.contains((searchE.toLowerCase()))){
                elem.addElement(r);
            }
        });
        defaultListModel = elem;
        l.setModel(defaultListModel);}
    }
    
 
    private void myList_rMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_rMouseClicked
        String nume = myList_r.getSelectedValue();
        scroll_mylist_r.setVisible(false);
        cautare_r.setText(nume);
    }//GEN-LAST:event_myList_rMouseClicked

    private void cautare_rKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cautare_rKeyReleased
        search(cautare_r.getText(), "regizor", scroll_mylist_r, myList_r);
    }//GEN-LAST:event_cautare_rKeyReleased

    // functie de afisare filme in functie de de regizor
    private void caut_film_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caut_film_bActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_filme_cr.getModel();
        tabel.setRowCount(0);
        scroll_cr_filme.setVisible(true);
        scroll_cr_actori.setVisible(false);
        String nume_r = cautare_r.getText();
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select nume from regizor";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    if(nume_r.equals(rs.getString("nume"))){
                        //interogarare simpla afisare filme dupa cautarea regizorului
                        /*select = "select F.numeFilm, F.anAparitie, F.gen, F.durataFilm from filme F " 
                                + "left join regizor R on F.regizorID = R.regizorID "
                                + "where R.nume = '"+nume_r+"' order by F.durataFilm DESC ";*/
                        
                        // INTEROGARE COMPLEXA afisare filme dupa cautarea regizorului
                        select = "select numeFilm, anAparitie, gen, durataFilm from filme "
                                 + "where regizorID in (select regizorID from regizor where nume = '"+nume_r+"')"
                                 + "order by durataFilm DESC;";
                        rs = stm.executeQuery(select);
                        
                        while(rs.next()){
                            String nume_f = rs.getString("numeFilm");
                            String an_f = String.valueOf(rs.getInt("anAparitie"));
                            String gen_f = rs.getString("gen");
                            String durata_f = String.valueOf(rs.getInt("durataFilm"));
                    
                            // se pun datele din baza de date in tabel
                            String date[] = {nume_f, an_f, gen_f, durata_f};
                            tabel = (DefaultTableModel)tabel_filme_cr.getModel();
                            tabel.addRow(date);
                        }
                        break;
                    }
                }
               //INTEROGARE SIMPLA afisare nr filme regizate de un regizor
               select = "select count(F.numeFilm) nrFilme from filme F join regizor R on R.regizorID = F.regizorID "
                       + "where R.nume = '"+nume_r+"' " 
                       + "group by R.nume;";
                rs = stm.executeQuery(select);
                int nr_f = 0;
                if(rs.next())
                    nr_f = rs.getInt("nrFilme");
                nr_filme_r.setText(String.valueOf(nr_f));
                
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_caut_film_bActionPerformed

    // functie de afisare filme in functie de actorul cautat
    private void caut_film_b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caut_film_b1ActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_filme_ca.getModel();
        tabel.setRowCount(0);
        String nume_a = cautare_a.getText();
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select nume from actor";
                ResultSet rs = stm.executeQuery(select);
                while(rs.next()){
                    if(nume_a.equals(rs.getString("nume"))){
                        //INTEROGARE SIMPLA afisare filme dupa cautarea actorului
                        select = "select F.numeFilm, F.anAparitie, F.gen, F.durataFilm from filme F " 
                                + "join actor_has_filme AF on F.filmID = AF.filmID " 
                                + "join actor A on A.actorID = AF.actorID " 
                                + "where A.nume = '"+nume_a+"' group by F.numeFilm order by F.numeFilm ASC;";
                        rs = stm.executeQuery(select);
                        
                        while(rs.next()){
                           // System.out.println("aici4");
                            String nume_f = rs.getString("numeFilm");
                            String an_f = String.valueOf(rs.getInt("anAparitie"));
                            String gen_f = rs.getString("gen");
                            String durata_f = String.valueOf(rs.getInt("durataFilm"));
                    
                            String date[] = {nume_f, an_f, gen_f, durata_f};
                            tabel = (DefaultTableModel)tabel_filme_ca.getModel();
                            tabel.addRow(date);
                        }
                        break;
                    }
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_caut_film_b1ActionPerformed

    private void myList_aMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_aMouseClicked
        String nume = myList_a.getSelectedValue();
        scroll_mylist_a.setVisible(false);
        cautare_a.setText(nume);
    }//GEN-LAST:event_myList_aMouseClicked

    private void cautare_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cautare_aActionPerformed
        
    }//GEN-LAST:event_cautare_aActionPerformed

    private void cautare_aKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cautare_aKeyReleased
        search(cautare_a.getText(), "actor", scroll_mylist_a, myList_a);
    }//GEN-LAST:event_cautare_aKeyReleased

    // functie de afisare actori in functie de regizorul cautat
    private void caut_actor_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caut_actor_bActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_cr_actori.getModel();
        tabel.setRowCount(0);
        scroll_cr_actori.setVisible(true);
        scroll_cr_filme.setVisible(false);
        
        String nume_r = cautare_r.getText();
        
        try {
            //conectarea la baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select nume from regizor";
                ResultSet rs = stm.executeQuery(select);
                while(rs.next()){
                    if(nume_r.equals(rs.getString("nume"))){
                        //INTEROGARE SIMPLA afisare actori dupa cautarea regizorului
                        select = "select distinct A.nume, A.nationalitate, A.rolMemorabil from actor A " 
                                + "join actor_has_filme AF on A.actorID = AF.actorID "
                                + "join filme F on F.filmID = AF.filmID "
                                + "join regizor R on R.regizorId = F.regizorID "
                                + "where R.nume = '"+nume_r+"';";
                        rs = stm.executeQuery(select); // executare query
                        
                        while(rs.next()){
                            String nume_a = rs.getString("nume");
                            String nat_a = rs.getString("nationalitate");
                            String rol_a = rs.getString("rolMemorabil");
                    
                            String date[] = {nume_a, nat_a, rol_a};
                            tabel = (DefaultTableModel)tabel_cr_actori.getModel();
                            tabel.addRow(date);
                        }
                        break;
                    }
                }
                // INTEROGARE SIMPLA afisare nr actori care au colaborat cu un regizor
                   select = "select count(distinct AF.actorID) nrActori from actor_has_filme AF  join filme F on F.filmID = AF.filmID " +
                              "join regizor R on R.regizorId = F.regizorID " +
                              "where R.nume = '"+nume_r+"' " +
                              "group by R.nume;";
                    rs = stm.executeQuery(select);
                    
                    if(rs.next())
                        nr_actori_r.setText(String.valueOf(rs.getInt("nrActori")));
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
        
    }//GEN-LAST:event_caut_actor_bActionPerformed

    private void cautare_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cautare_fActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cautare_fActionPerformed

    private void cautare_fKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cautare_fKeyReleased
        search(cautare_f.getText(), "filme", scroll_mylist_f, myList_f);
    }//GEN-LAST:event_cautare_fKeyReleased

    private void myList_fMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_fMouseClicked
        String nume = myList_f.getSelectedValue();
        scroll_mylist_f.setVisible(false);
        cautare_f.setText(nume);
    }//GEN-LAST:event_myList_fMouseClicked

    // functie de afisare date despre film
    private void caut_date_film_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caut_date_film_bActionPerformed
        String nume_f = cautare_f.getText();
        l_distributie.setText("");
        try {
            //conectare la baza de date
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select numeFilm, anAparitie, gen, tara, descriere, durataFilm from filme";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    if(nume_f.equals(rs.getString("numeFilm"))){
                        /*String n = "<html>";
                        n += rs.getString("numeFilm");
                        n += "</html>";*/
                        
                        l_film.setText(rs.getString("numeFilm"));
                        l_an_f.setText(String.valueOf(rs.getInt("anAparitie")));
                        l_gen_f.setText(rs.getString("gen"));
                        l_durata_f.setText(String.valueOf(rs.getInt("durataFilm")));
                        l_descriere_f.setText(rs.getString("descriere"));
                        l_tara_f.setText(rs.getString("tara"));
                        
                        //INTEROGARE SIMPLA afisare regizor dupa cautarea filmului
                        select = "select R.nume from regizor R join filme F on F.regizorId = R.regizorID where F.numeFilm = '"+nume_f+"';";
                        rs = stm.executeQuery(select);
                        if(rs.next())
                            l_regizor_f.setText(rs.getString("nume"));
                       
                        
                        //INTEROGARE SIMPLA asifare actori dupa cautarea filmului
                        select = "select A.nume from actor A join actor_has_filme AF on AF.actorID = A.actorID " 
                                 + "join filme F on F.filmID = AF.filmID "
                                 +"where F.numeFilm = '"+nume_f+"';";
                        rs = stm.executeQuery(select);
                        
                        String text = "";
                        while(rs.next()){
                            /*l_distributie.append(rs.getString("nume"));
                            l_distributie.append(", ");*/
                            text += rs.getString("nume");
                            text += ", ";
                        }
                        
                        StringBuffer sb = new StringBuffer(text);
                        sb.deleteCharAt(sb.length() - 2);
                        text = sb.toString();
                        l_distributie.setText(text);
                        
                        break;
                    }
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_caut_date_film_bActionPerformed

    // buton de refresh tabel filme, regizori, actori din fereastra Setari
    private void b_refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_refresh1ActionPerformed
        if(ok == 1) {
            jScrollPane_filme1.setVerticalScrollBar(new ScrollBarCustom());
            mesaj_modificare_film.setVisible(false);
            text_descriere_film.setText("");
            m_label_nume_film.setText("");
            DefaultTableModel tabel = (DefaultTableModel)tabel_filme1.getModel();
            tabel.setRowCount(0);
            update_table_filme(tabel_filme1);
        }
        else if(ok == 2){
            jScrollPane_regizori1.setVerticalScrollBar(new ScrollBarCustom());
            mesaj_modificare_regizor.setVisible(false);
            m_nat_regizor.setText("");
            m_nr_premii.setText("");
            m_label_nume_regizor.setText("");
            DefaultTableModel tabel = (DefaultTableModel)tabel_regizori1.getModel();
            tabel.setRowCount(0);
            update_table_regizori(tabel_regizori1);
        }
        else if(ok == 3){
            jScrollPane_actori1.setVerticalScrollBar(new ScrollBarCustom());
            mesaj_modificare_actor.setVisible(false);
            m_nat_actor.setText("");
            m_rol_actor.setText("");
            m_label_nume_actor.setText("");
            DefaultTableModel tabel = (DefaultTableModel)tabel_actori1.getModel();
            tabel.setRowCount(0);
            update_table_actor(tabel_actori1);
        }
    }//GEN-LAST:event_b_refresh1ActionPerformed
     
    // buton de vizualiazre filme
    private void b_viz_filme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_filme1ActionPerformed
        jScrollPane_filme1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_filme1.setVisible(true);
        update_table_filme(tabel_filme1);
        jScrollPane_regizori1.setVisible(false);
        jScrollPane_actori1.setVisible(false);
    }//GEN-LAST:event_b_viz_filme1ActionPerformed

    // buton de vizualiazre regizori
    private void b_viz_regizori1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_regizori1ActionPerformed
        jScrollPane_filme1.setVisible(false);
        update_table_regizori(tabel_regizori1);
        jScrollPane_regizori1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_regizori1.setVisible(true);
        jScrollPane_actori1.setVisible(false);
    }//GEN-LAST:event_b_viz_regizori1ActionPerformed

    // buton de vizualiazre actori
    private void b_viz_actori1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_viz_actori1ActionPerformed
        jScrollPane_filme1.setVisible(false);
        update_table_actor(tabel_actori1);
        jScrollPane_regizori1.setVisible(false);
        jScrollPane_actori1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane_actori1.setVisible(true);
    }//GEN-LAST:event_b_viz_actori1ActionPerformed

    private void del_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_rActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_del_rActionPerformed

    private void del_rKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_del_rKeyReleased
        search(del_r.getText(), "regizor", scroll_mylist_dr, myList_dr);
    }//GEN-LAST:event_del_rKeyReleased

    private void myList_daMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_daMouseClicked
        String nume = myList_da.getSelectedValue();
        scroll_mylist_da.setVisible(false);
        del_a.setText(nume);
    }//GEN-LAST:event_myList_daMouseClicked

    private void del_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_del_aActionPerformed

    private void del_aKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_del_aKeyReleased
        search(del_a.getText(), "actor", scroll_mylist_da, myList_da);
    }//GEN-LAST:event_del_aKeyReleased

    private void del_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_del_fActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_del_fActionPerformed

    private void del_fKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_del_fKeyReleased
        search(del_f.getText(), "filme", scroll_mylist_df, myList_df);
    }//GEN-LAST:event_del_fKeyReleased

    private void myList_drMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_drMouseClicked
        String nume = myList_dr.getSelectedValue();
        scroll_mylist_dr.setVisible(false);
        del_r.setText(nume);
    }//GEN-LAST:event_myList_drMouseClicked

    private void myList_dfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_myList_dfMouseClicked
        String nume = myList_df.getSelectedValue();
        scroll_mylist_df.setVisible(false);
        del_f.setText(nume);
    }//GEN-LAST:event_myList_dfMouseClicked
    
    // functie stergere filme din baza de date
    private void b_del_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_del_fActionPerformed
       String nume_f = del_f.getText();
       
       try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                String select = "select filmID from filme where numeFilm = '"+nume_f+"'";
                ResultSet rs = stm.executeQuery(select);
                
                int id_f = 0;
                if(rs.next())
                    id_f = rs.getInt("filmID");
                
                //DELETE filme: din tabelul de legatura si din tabelul filme
                select = "delete from actor_has_filme where filmID = "+id_f+" ";
                stm.execute(select);
                
                select = "delete from filme where numeFilm = '"+nume_f+"' ";
                stm.execute(select);
                
                label_del_f.setVisible(false);
                label_del_f.setVisible(true);
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_b_del_fActionPerformed

    
    // functie stergere regizori din baza de date
    private void b_del_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_del_rActionPerformed
        String nume_r = del_r.getText();
       
       try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                String select = "select F.filmID from filme F left join regizor R on F.regizorID = R.regizorID where R.nume = '"+nume_r+"';";
                ResultSet rs = stm.executeQuery(select);
                
                int id_f = 0;
                //DELETE regizor: pentru a sterge regizor trebuie sterse mai intai lagaturile filme actori, apoi sterse filmele care sunt regizate de acel regizor, si apoi regizorul
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
            }
    }//GEN-LAST:event_b_del_rActionPerformed

    
    // functie stergere actori din baza de date
    private void b_del_f1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_del_f1ActionPerformed
        String nume_a = del_a.getText();
       
       try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                //DELETE actor
                String select = "select actorID from actor where nume = '"+nume_a+"'";
                ResultSet rs = stm.executeQuery(select);
                
                int id_a = 0;
                if(rs.next())
                    id_a = rs.getInt("actorID");
                
                
                select = "delete from actor_has_filme where actorID = "+id_a+" ";
                stm.execute(select);
                
                select = "delete from actor where nume = '"+nume_a+"' ";
                stm.execute(select);
                
                label_del_a.setVisible(false);
                label_del_a.setVisible(true);
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_b_del_f1ActionPerformed

    // functie de refresh tabel
    private void viz_date_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viz_date_bActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_utilizator.getModel();
        tabel.setRowCount(0);
        update_table_utilizatori();

    }//GEN-LAST:event_viz_date_bActionPerformed

    private void viz_gen_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viz_gen_bActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_gen_u.getModel();
        tabel.setRowCount(0);
        update_table_gen_u();
    }//GEN-LAST:event_viz_gen_bActionPerformed

    private void buton_viz_filmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_viz_filmeActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_filme_vizionate.getModel();
        DefaultTableModel tabel1 = (DefaultTableModel)tabel_filme_recomandate.getModel();
        tabel.setRowCount(0);
        tabel1.setRowCount(0);
        update_table_filme_viz_re();
    }//GEN-LAST:event_buton_viz_filmeActionPerformed

    
    private void selectare_date_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectare_date_bActionPerformed
        int row = tabel_utilizator.getSelectedRow();
        String nume_u = tabel_utilizator.getValueAt(row, 0).toString();
        String prenume_u = tabel_utilizator.getValueAt(row, 1).toString();
        String adresa_u = tabel_utilizator.getValueAt(row, 2).toString();
        
        text_nume.setText(nume_u);
        text_prenume.setText(prenume_u);
        text_adresa.setText(adresa_u);
    }//GEN-LAST:event_selectare_date_bActionPerformed

    private void text_genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_genActionPerformed

    }//GEN-LAST:event_text_genActionPerformed

    // functie de afisare utilizatori care au intarziat cu plata abonamentului
    private void combobox_abonamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_abonamenteActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_restantieri.getModel();
        tabel.setRowCount(0);
        if(combobox_abonamente.getSelectedItem()!= "-- Abonament --"){
            try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //INTEROGARE COMPLEXA afisare utilizatori care nu au efectuat plata la timp
                String select = "select U.nume, U.prenume from utilizator U where abonamentID in "
                                 + "(select abonamentID from abonament where tipAbonament = '"+combobox_abonamente.getSelectedItem()+"') and utilizatorID in " 
                                 + "(select utilizatorID from plati where status = 'neplatit');";
                ResultSet rs = stm.executeQuery(select); // executare query
                
                while(rs.next()){
                    String nume_u = rs.getString("U.nume");
                    String prenume_u = rs.getString("U.prenume");
                    
                    String date[] = {nume_u, prenume_u};
                    tabel = (DefaultTableModel)tabel_restantieri.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
        }
            
    }//GEN-LAST:event_combobox_abonamenteActionPerformed

    private void afisare_descriereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afisare_descriereActionPerformed
        int row = tabel_abonament.getSelectedRow();
        String tip_a = tabel_abonament.getValueAt(row, 0).toString();
        String pret_a = tabel_abonament.getValueAt(row, 1).toString();
        
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select descriere from abonament where tipAbonament = '"+tip_a+"';";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String descriere_a = "<html>";
                    descriere_a += rs.getString("descriere");
                    descriere_a += "</html>";
                    label_descriere_abonament.setText(descriere_a);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_afisare_descriereActionPerformed

    //functie care ii modifica tabelul abonamente
    private void b_modificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_modificaActionPerformed
        int row = tabel_abonament.getSelectedRow();
        String tip_a = tabel_abonament.getValueAt(row, 0).toString();
        
        label_abonamente.setText(tip_a);
        String t_descriere = text_descriere.getText();
        String t_pret = text_pret.getText();
        
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //UPDATE abonament
                String select = "update abonament set descriere = '"+t_descriere+"', plataLunara = '"+Float.parseFloat(t_pret)+"' where tipAbonament = '"+tip_a+"';";
                stm.execute(select);
                
                mesaj_modificare_succes.setVisible(false);
                mesaj_modificare_succes.setVisible(true);
                
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_b_modificaActionPerformed

    private void selectare_date_abonamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectare_date_abonamentActionPerformed
        int row = tabel_abonament.getSelectedRow();
        String tip_a = tabel_abonament.getValueAt(row, 0).toString();
        String pret_a = tabel_abonament.getValueAt(row, 1).toString();
        
        label_abonament.setText(tip_a);
        text_pret.setText(pret_a);
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select descriere from abonament where tipAbonament = '"+tip_a+"';";
                ResultSet rs = stm.executeQuery(select);
                
                String descriere_a = null;
                if(rs.next())
                    text_descriere.setText(rs.getString("descriere"));
                    
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_selectare_date_abonamentActionPerformed

    private void bara_cautare_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bara_cautare_fActionPerformed
        
    }//GEN-LAST:event_bara_cautare_fActionPerformed

    // functie afisare actor / regizori dupa filmul cautat
    private void buton_cautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buton_cautaActionPerformed
        String nume = bara_cautare_f.getText();
        int k = 0;
        
        if(nume.equals(""))
            jScrollPane_lista_cautare_filme.setVisible(false);
        else{
        
         try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                
                String select = "select nume from actor";
                ResultSet rs = stm.executeQuery(select);
                
                DefaultListModel list = new DefaultListModel();
                while(rs.next()){
                    if(rs.getString("nume").equals(nume)){
                        k = 1;
                        break;
                    }   
                }
                
                select = "select nume from regizor";
                rs = stm.executeQuery(select);
                
                while(rs.next()){
                    if(rs.getString("nume").equals(nume)){
                        k = 2;
                        break;
                    }   
                }
                
                select = "select numeFilm from filme";
                rs = stm.executeQuery(select);
                
                while(rs.next()){
                    if(rs.getString("numeFilm").equals(nume)){
                        k = 3;
                        break;
                    }   
                }
                
                if(k == 1){
                    //INTEROGARE COMPLEXA cautare filme dupa actori
                    select = "select numeFilm from filme "
                             + "where filmID in (select filmID from actor_has_filme AF join actor A on A.actorID = AF.actorID "
                             + "where A.nume like '%"+nume+"%');";
                    rs = stm.executeQuery(select);
                    
                    while(rs.next()){
                        String nume_film = rs.getString("numeFilm");
                        list.addElement(nume_film); 
                    }
                }
                else if(k == 2){
                    //ITEROGARE COMPLEXA cautare filme dupa regizori
                    select = "select numeFilm from filme "
                             + "where regizorID in (select regizorID from regizor where nume like '%"+nume+"%');";
                    rs = stm.executeQuery(select);
                         
                    while(rs.next()){
                        String nume_film = rs.getString("numeFilm");
                        list.addElement(nume_film); 
                    }
                }
                else if(k == 3){
                    select = "select numeFilm from filme where numeFilm like '%" + nume + "%';";
                    rs = stm.executeQuery(select);
                         
                    while(rs.next()){
                        String nume_film = rs.getString("numeFilm");
                        list.addElement(nume_film); 
                    }
                }
                
               jScrollPane_lista_cautare_filme.setVerticalScrollBar(new ScrollBarCustom());
               jScrollPane_lista_cautare_filme.setVisible(true);
               list_cautare_filme.setModel(list); 
                
               con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
        }
    }//GEN-LAST:event_buton_cautaActionPerformed

    private void b_select_rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_select_rActionPerformed
        int row = tabel_regizori1.getSelectedRow();
        String nume_r = tabel_regizori1.getValueAt(row, 0).toString();
        String nat_r = tabel_regizori1.getValueAt(row, 1).toString();
        String premii_r = tabel_regizori1.getValueAt(row, 2).toString();
        
        m_label_nume_regizor.setText(nume_r);
        m_nat_regizor.setText(nat_r);
        m_nr_premii.setText(premii_r);
    }//GEN-LAST:event_b_select_rActionPerformed

    private void b_select_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_select_aActionPerformed
        int row = tabel_actori1.getSelectedRow();
        String nume_a = tabel_actori1.getValueAt(row, 0).toString();
        String nat_a = tabel_actori1.getValueAt(row, 1).toString();
        String rol_a = tabel_actori1.getValueAt(row, 2).toString();
        
        m_label_nume_actor.setText(nume_a);
        m_nat_actor.setText(nat_a);
        m_rol_actor.setText(rol_a);
    }//GEN-LAST:event_b_select_aActionPerformed

    private void b_select_fActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_select_fActionPerformed
        int row = tabel_filme1.getSelectedRow();
        String nume_f = tabel_filme1.getValueAt(row, 0).toString();
        
        m_label_nume_film.setText(nume_f);
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select descriere from filme where numeFilm = '"+nume_f+"';";
                ResultSet rs = stm.executeQuery(select);
                
                String descriere_a = null;
                if(rs.next())
                    text_descriere_film.setText(rs.getString("descriere"));
                    
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_b_select_fActionPerformed

    // functie de modficare tabel filme din baza de date
    private void m_buton_filmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_buton_filmActionPerformed
        int row = tabel_filme1.getSelectedRow();
        String nume_f = tabel_filme1.getValueAt(row, 0).toString();
        
        String t_descriere = text_descriere_film.getText();
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //UPDATE filme
                String select = "update filme set descriere = '"+t_descriere+"'  where numeFilm = '"+nume_f+"';";
                stm.execute(select);
                
                mesaj_modificare_film.setVisible(true);
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_m_buton_filmActionPerformed

    // functie modificare tabel regizor din baza de date
    private void m_buton_regizorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_buton_regizorActionPerformed
        int row = tabel_regizori1.getSelectedRow();
        String nume_r = tabel_regizori1.getValueAt(row, 0).toString();
        
        String nat_r = m_nat_regizor.getText();
        String nr_premii = m_nr_premii.getText();
        
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //UPDATE regizor
                String select = "update regizor set nationalitate = '"+nat_r+"', nrpremii = '"+Integer.parseInt(nr_premii)+"' where nume = '"+nume_r+"';";
                stm.execute(select);
                
                mesaj_modificare_regizor.setVisible(false);
                mesaj_modificare_regizor.setVisible(true);
                
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_m_buton_regizorActionPerformed

    // functie modficare tabel actori din baza de date
    private void m_buton_actorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_buton_actorActionPerformed
        int row = tabel_actori1.getSelectedRow();
        String nume_a = tabel_actori1.getValueAt(row, 0).toString();
        
        String nat_a = m_nat_actor.getText();
        String rol_a = m_rol_actor.getText();
        System.out.println(nat_a);
        System.out.println(rol_a);
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //UPDATE actor
                String select = "update actor set nationalitate = '"+nat_a+"', rolMemorabil = '"+rol_a+"' where nume = '"+nume_a+"';";
                stm.execute(select);
                System.out.println(nat_a);
        System.out.println(rol_a);
                mesaj_modificare_actor.setVisible(false);
                mesaj_modificare_actor.setVisible(true);
                
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_m_buton_actorActionPerformed

    //functie care afiseza filmele cele mai uramrite dintr-un an
    private void b_cutare_filme_popurareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cutare_filme_popurareActionPerformed
        DefaultTableModel tabel = (DefaultTableModel)tabel_filme_populare.getModel();
        tabel.setRowCount(0);
        String an_f = text_an_f.getText();
        
        try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //INTEROGARE SIMPLA afiseaza filmele cele mai uramrite dintr-un an
                String select = "select F.numeFilm, F.anAparitie, count(UF.utilizatorID) FilmePopulare from filme F join utilizator_has_filme UF on F.filmID = UF.filmID "
                                + " where F.anAparitie = "+an_f+" "
                                + " group by F.numeFilm "
                                + " order by FilmePopulare DESC;";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_f = rs.getString("F.numeFilm");
                    String an_film = rs.getString("F.anAparitie");
                    String nr_viz = rs.getString("FilmePopulare");
                    
                    String date[] = {nume_f, an_film, nr_viz};
                    tabel = (DefaultTableModel)tabel_filme_populare.getModel();
                    tabel.addRow(date);
                }
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }//GEN-LAST:event_b_cutare_filme_popurareActionPerformed

    void updateCombox(){
        
         try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                String select = "select tipAbonament from abonament";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next())
                    combobox_abonamente.addItem(rs.getString("tipAbonament"));
                
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // functie care imi genereaza date in tabel: in functie de utilizatorul cautat pot sa vad la ce fel de filme s-a uitat si sa afisez nr lor
    void update_table_gen_u(){
        
        String nume_u = text_nume.getText();
        String prenume_u = text_prenume.getText();
        String adresa_u = text_adresa.getText();
        System.out.println(nume_u);
        System.out.println(adresa_u);
        
         try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //INTEROGARE COMPLEXA nr filme pentru fiecare gen vizionat de utilizator 
                String select = "select  F.gen, count(F.gen) NrFilme from filme F where filmID in " 
                                 + "(select UF.filmID from utilizator_has_filme UF join utilizator U on UF.utilizatorID = U.utilizatorID " 
                                 + "where U.nume = '"+nume_u+"' and U.prenume = '"+prenume_u+"' and U.adresa = '"+adresa_u+"') "
                                 + "group by F.gen " 
                                 + "order by NrFilme DESC;";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nr_f = String.valueOf(rs.getInt("NrFilme"));
                    String gen = rs.getString("F.gen");
                    
                    String date[] = {gen, nr_f};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_gen_u.getModel();
                    tabel.addRow(date);
                }
               
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    // functie care imi afiseaza in tabel care imi afiseaza filme vazute dintr-o anumita cateogie in functie de persoana, cat si recomandari
    void update_table_filme_viz_re(){
        
        String nume_u = text_nume.getText();
        String prenume_u = text_prenume.getText();
        String adresa_u = text_adresa.getText();
        String gen_f = text_gen.getText();
        
        System.out.println(nume_u);
        System.out.println(gen_f);
        
         try {
                Connection con = ConectareBD.bdConectare(); 
                Statement stm = con.createStatement();
                //INTEROGARE COMPLEXA afiseaza filmele vazute dupa gen de o anumita persoana
                String select = "select F.numeFilm from filme F join utilizator_has_filme UF on F.filmID = UF.filmID "
                                + "where F.gen = '"+gen_f+"' and "
                                + "UF.utilizatorID = (select U.utilizatorID from utilizator U "
                                                      + "where U.nume = '"+nume_u+"' and U.prenume = '"+prenume_u+"' and adresa = '"+adresa_u+"');";
                ResultSet rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_film = rs.getString("F.numeFilm");
                    
                    String date[] = {nume_film};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_filme_vizionate.getModel();
                    tabel.addRow(date);
                }
                
                //INTEROGARE COMPLEXA afisare filme recomandate
                select = "select F.numeFilm from filme F where F.gen = '"+gen_f+"' and F.filmID not in "
                         + "(select UF.filmID from utilizator_has_filme UF where F.gen = '"+gen_f+"' and " 
                         + "UF.utilizatorID = (select U.utilizatorID from utilizator U "
                         + "where U.nume = '"+nume_u+"' and U.prenume = '"+prenume_u+"' and adresa = '"+adresa_u+"'));";
                
                rs = stm.executeQuery(select);
                
                while(rs.next()){
                    String nume_film = rs.getString("F.numeFilm");
                    
                    String date[] = {nume_film};
                    DefaultTableModel tabel = (DefaultTableModel)tabel_filme_recomandate.getModel();
                    tabel.addRow(date);
                }
               
                con.close();
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Not Succes");
            }
    }
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(meniu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(meniu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(meniu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(meniu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new meniu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Maxmouse;
    private javax.swing.JLabel Minmouse;
    private javax.swing.JLabel Xmouse;
    private javax.swing.JLabel actor1_nume;
    private javax.swing.JLabel actor2_nume;
    private javax.swing.JLabel actor3_nume;
    private javax.swing.JPanel actor_panel;
    private javax.swing.JPanel actor_panel1;
    private javax.swing.JPanel ad_regizor_actor;
    private javax.swing.JPanel add_date_film;
    private javax.swing.JPanel add_fillm;
    private javax.swing.JButton afisare_descriere;
    private javax.swing.JTextField anaparitie;
    private javax.swing.JButton bX_ra;
    private javax.swing.JButton b_ad_film;
    private javax.swing.JButton b_ad_ra;
    private javax.swing.JButton b_addA;
    private javax.swing.JButton b_addR;
    private javax.swing.JButton b_cutare_filme_popurare;
    private javax.swing.JButton b_del_f;
    private javax.swing.JButton b_del_f1;
    private javax.swing.JButton b_del_r;
    private javax.swing.JButton b_modifica;
    private javax.swing.JButton b_refresh;
    private javax.swing.JButton b_refresh1;
    private javax.swing.JButton b_select_a;
    private javax.swing.JButton b_select_f;
    private javax.swing.JButton b_select_r;
    private javax.swing.JButton b_viz_actori;
    private javax.swing.JButton b_viz_actori1;
    private javax.swing.JButton b_viz_filme;
    private javax.swing.JButton b_viz_filme1;
    private javax.swing.JButton b_viz_regizori;
    private javax.swing.JButton b_viz_regizori1;
    private javax.swing.JPanel background_addA;
    private javax.swing.JPanel background_addR;
    private javax.swing.JPanel background_addfilm;
    private javax.swing.JPanel background_addra;
    private javax.swing.JPanel background_cg;
    private javax.swing.JPanel background_rez;
    private javax.swing.JPanel background_rez1;
    private javax.swing.JPanel background_selectare_date;
    private javax.swing.JPanel background_vd;
    private javax.swing.JPanel background_vg;
    private javax.swing.JPanel background_vza;
    private javax.swing.JPanel background_vza1;
    private javax.swing.JPanel background_vza2;
    private java.awt.Panel baraXMinMax;
    private javax.swing.JTextField bara_cautare_f;
    private javax.swing.JPanel bara_panel;
    private javax.swing.JPanel bara_panel1;
    private java.awt.Panel butonMax;
    private java.awt.Panel butonMeniu;
    private java.awt.Panel butonMin;
    private java.awt.Panel butonProfil;
    private java.awt.Panel butonX;
    private javax.swing.JButton buton_abonamente;
    private javax.swing.JButton buton_acasa;
    private javax.swing.JButton buton_cauta;
    private javax.swing.JButton buton_filme;
    private javax.swing.JButton buton_setari;
    private javax.swing.JButton buton_utilizatori;
    private javax.swing.JButton buton_viz_filme;
    private javax.swing.JButton caut_actor_b;
    private javax.swing.JButton caut_date_film_b;
    private javax.swing.JButton caut_film_b;
    private javax.swing.JButton caut_film_b1;
    private javax.swing.JTextField cautare_a;
    private javax.swing.JTextField cautare_f;
    private javax.swing.JTextField cautare_r;
    private javax.swing.JComboBox<String> cb_a1;
    private javax.swing.JComboBox<String> cb_a2;
    private javax.swing.JComboBox<String> cb_a3;
    private javax.swing.JComboBox<String> cb_regizor;
    private javax.swing.JComboBox<String> combobox_abonamente;
    private javax.swing.JPanel dashbord;
    private javax.swing.JPanel date_abonamente;
    private javax.swing.JPanel date_filme;
    private javax.swing.JPanel date_utilizator;
    public static javax.swing.JLabel deconectareA;
    private javax.swing.JTextField del_a;
    private javax.swing.JTextField del_f;
    private javax.swing.JTextField del_r;
    private javax.swing.JTextField descriere;
    private javax.swing.JTextField duratafilm;
    private javax.swing.JPanel extensie_meniu;
    private javax.swing.JPanel extensie_profil;
    private javax.swing.JPanel filme_panel;
    private javax.swing.JPanel filme_panel1;
    private javax.swing.JTextField gen;
    private java.awt.Panel iconXMinMax;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane_actori;
    private javax.swing.JScrollPane jScrollPane_actori1;
    private javax.swing.JScrollPane jScrollPane_filme;
    private javax.swing.JScrollPane jScrollPane_filme1;
    private javax.swing.JScrollPane jScrollPane_lista_cautare_filme;
    private javax.swing.JScrollPane jScrollPane_regizori;
    private javax.swing.JScrollPane jScrollPane_regizori1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel l_actiune;
    private javax.swing.JLabel l_an_f;
    private javax.swing.JLabel l_aventura;
    private javax.swing.JLabel l_biografic;
    private javax.swing.JLabel l_comedie;
    private javax.swing.JTextArea l_descriere_f;
    private javax.swing.JTextArea l_distributie;
    private javax.swing.JLabel l_drama;
    private javax.swing.JLabel l_durata_f;
    private javax.swing.JLabel l_film;
    private javax.swing.JLabel l_gen_f;
    private javax.swing.JLabel l_istoric;
    private javax.swing.JLabel l_regizor_f;
    private javax.swing.JLabel l_sf;
    private javax.swing.JLabel l_tara_f;
    private javax.swing.JLabel label_abonament;
    private javax.swing.JLabel label_abonamente;
    private javax.swing.JLabel label_actor;
    private javax.swing.JLabel label_ad_film;
    private javax.swing.JLabel label_ad_film1;
    private javax.swing.JLabel label_cautare_a;
    private javax.swing.JLabel label_cautare_r;
    private javax.swing.JLabel label_cautare_r2;
    private javax.swing.JLabel label_del_a;
    private javax.swing.JLabel label_del_f;
    private javax.swing.JLabel label_del_r;
    private javax.swing.JLabel label_descriere_abonament;
    private javax.swing.JLabel label_regizor;
    private java.awt.Panel linieMeniu;
    private java.awt.Panel linieProfil;
    private javax.swing.JList<String> list_cautare_filme;
    private javax.swing.JScrollPane lista_filme;
    private javax.swing.JButton m_buton_actor;
    private javax.swing.JButton m_buton_film;
    private javax.swing.JButton m_buton_regizor;
    private javax.swing.JLabel m_label_nume_actor;
    private javax.swing.JLabel m_label_nume_film;
    private javax.swing.JLabel m_label_nume_regizor;
    private javax.swing.JTextField m_nat_actor;
    private javax.swing.JTextField m_nat_regizor;
    private javax.swing.JTextField m_nr_premii;
    private javax.swing.JTextField m_rol_actor;
    private javax.swing.JPanel meniu;
    private javax.swing.JLabel meniuImg;
    private javax.swing.JLabel mesaj_err_A;
    private javax.swing.JLabel mesaj_err_R;
    private javax.swing.JLabel mesaj_err_f;
    private javax.swing.JLabel mesaj_err_ra;
    private javax.swing.JLabel mesaj_modificare_actor;
    private javax.swing.JLabel mesaj_modificare_film;
    private javax.swing.JLabel mesaj_modificare_regizor;
    private javax.swing.JLabel mesaj_modificare_succes;
    private javax.swing.JList<String> myList_a;
    private javax.swing.JList<String> myList_da;
    private javax.swing.JList<String> myList_df;
    private javax.swing.JList<String> myList_dr;
    private javax.swing.JList<String> myList_f;
    private javax.swing.JList<String> myList_r;
    private javax.swing.JTextField nationalitateactor;
    private javax.swing.JTextField nationalitateregizor;
    private javax.swing.JLabel nr_actori_r;
    private javax.swing.JLabel nr_filme_r;
    private javax.swing.JTextField nrpremii;
    public static javax.swing.JLabel nume_admin;
    private javax.swing.JTextField numeactor;
    private javax.swing.JTextField numefilm;
    private javax.swing.JTextField numeregizor;
    private javax.swing.JPanel optiuni_tabel;
    private javax.swing.JPanel optiuni_tabel1;
    private javax.swing.JPanel panel_rt;
    private javax.swing.JPanel panel_rt1;
    private javax.swing.JPanel panou_info;
    private javax.swing.JPanel poster_film;
    private javax.swing.JLabel profilImg;
    private javax.swing.JPanel refresh_panel;
    private javax.swing.JPanel refresh_panel1;
    private javax.swing.JPanel regizor_panel;
    private javax.swing.JPanel regizor_panel1;
    private javax.swing.JButton rez_b;
    private javax.swing.JLabel rf_nume;
    private javax.swing.JTextField rol_memorabil;
    private javax.swing.JScrollPane scroll_actiune;
    private javax.swing.JScrollPane scroll_add_film;
    private javax.swing.JScrollPane scroll_aventura;
    private javax.swing.JScrollPane scroll_biografic;
    private javax.swing.JScrollPane scroll_ca_filme;
    private javax.swing.JScrollPane scroll_comedie;
    private javax.swing.JScrollPane scroll_cr_actori;
    private javax.swing.JScrollPane scroll_cr_filme;
    private javax.swing.JScrollPane scroll_date_film;
    private javax.swing.JScrollPane scroll_date_utilizator;
    private javax.swing.JScrollPane scroll_drama;
    private javax.swing.JPanel scroll_f_actiune;
    private javax.swing.JPanel scroll_f_aventura;
    private javax.swing.JPanel scroll_f_biografic;
    private javax.swing.JPanel scroll_f_comedie;
    private javax.swing.JPanel scroll_f_drama;
    private javax.swing.JPanel scroll_f_istoric;
    private javax.swing.JPanel scroll_f_sf;
    private javax.swing.JScrollPane scroll_filme_populare;
    private javax.swing.JScrollPane scroll_istoric;
    private javax.swing.JScrollPane scroll_mylist_a;
    private javax.swing.JScrollPane scroll_mylist_da;
    private javax.swing.JScrollPane scroll_mylist_df;
    private javax.swing.JScrollPane scroll_mylist_dr;
    private javax.swing.JScrollPane scroll_mylist_f;
    private javax.swing.JScrollPane scroll_mylist_r;
    private javax.swing.JPanel scroll_panel;
    private javax.swing.JScrollPane scroll_sf;
    private javax.swing.JButton selectare_date_abonament;
    private javax.swing.JButton selectare_date_b;
    private javax.swing.JSeparator sem_numefilm;
    private javax.swing.JSeparator sep_anaparitie;
    private javax.swing.JSeparator sep_descriere;
    private javax.swing.JSeparator sep_duratafilm;
    private javax.swing.JSeparator sep_gen;
    private javax.swing.JSeparator sep_nationalitateactor;
    private javax.swing.JSeparator sep_nationalitateregizor;
    private javax.swing.JSeparator sep_nrpremii;
    private javax.swing.JSeparator sep_numeactor;
    private javax.swing.JSeparator sep_numeregizor;
    private javax.swing.JSeparator sep_rol_memorabil;
    private javax.swing.JSeparator sep_tara;
    private java.awt.Panel shortMeniu;
    private javax.swing.JTable tabel_abonament;
    private javax.swing.JTable tabel_actori;
    private javax.swing.JTable tabel_actori1;
    private javax.swing.JTable tabel_au;
    private javax.swing.JTable tabel_cr_actori;
    private javax.swing.JTable tabel_filme;
    private javax.swing.JTable tabel_filme1;
    private javax.swing.JTable tabel_filme_ca;
    private javax.swing.JTable tabel_filme_cr;
    private javax.swing.JTable tabel_filme_populare;
    private javax.swing.JTable tabel_filme_recomandate;
    private javax.swing.JTable tabel_filme_vizionate;
    private javax.swing.JTable tabel_gen_u;
    private javax.swing.JPanel tabel_panel;
    private javax.swing.JPanel tabel_panel1;
    private javax.swing.JTable tabel_regizori;
    private javax.swing.JTable tabel_regizori1;
    private javax.swing.JTable tabel_restantieri;
    private javax.swing.JTable tabel_utilizator;
    private javax.swing.JTextField tara;
    private javax.swing.JTextField text_adresa;
    private javax.swing.JTextField text_an_f;
    private javax.swing.JTextArea text_descriere;
    private javax.swing.JTextArea text_descriere_film;
    private javax.swing.JTextField text_gen;
    private javax.swing.JTextField text_nume;
    private javax.swing.JTextField text_prenume;
    private javax.swing.JTextField text_pret;
    private javax.swing.JButton viz_date_b;
    private javax.swing.JButton viz_gen_b;
    private javax.swing.JButton vza_b;
    // End of variables declaration//GEN-END:variables

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
}
