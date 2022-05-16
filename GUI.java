import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI {
    private final int PANELBREDDE = 400;
    private final int PANELHOYDE = 500;
    private final int KONTROLLPANELHOYDE = 150;
    private final int GRID = 12;

    private JLabel ruter[][] = new JLabel[GRID][GRID];
    private JFrame vindu;
    private JLabel poeng;

    private JPanel mainpanel,panel, poengpanel, rutenett, avslutt;
    
    private JLabel styringspanel;

    private Controller controll;

    private JButton opp, ned, hoyre, venstre, stoppknapp, clear;

    

    public GUI(Controller c){
        controll = c;
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) { System.exit(1); 
                }

        
        //LAGER SPILLBRETT
        vindu = new JFrame("Snake");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Hovedflate
        mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        vindu.add(mainpanel);
        
        //Underpanel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(PANELBREDDE, KONTROLLPANELHOYDE));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        mainpanel.add(panel, BorderLayout.NORTH);

        //Poengpanel
        poengpanel = new JPanel();
        poengpanel.setLayout(new BorderLayout());
        poengpanel.setPreferredSize(new Dimension(PANELBREDDE / 4, KONTROLLPANELHOYDE));
        poengpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        poeng = new JLabel();
        poengpanel.add(poeng);
        panel.add(poengpanel, BorderLayout.WEST);

        //Avslutt
        avslutt = new JPanel();
        avslutt.setLayout(new BorderLayout());
        avslutt.setPreferredSize(new Dimension(PANELBREDDE / 4, KONTROLLPANELHOYDE));
        avslutt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        stoppknapp = new JButton("Avslutt");
       
        class Avslutt implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.avsluttSpillet();
                
            }        
        }
        stoppknapp.addActionListener(new Avslutt());
        avslutt.add(stoppknapp);
        panel.add(avslutt, BorderLayout.EAST);

        
        //Stryingsmekanismer
        styringspanel = new JLabel();
        styringspanel.setLayout(new BorderLayout());
        styringspanel.setPreferredSize(new Dimension(PANELBREDDE / 2, KONTROLLPANELHOYDE));
        styringspanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //OPP
        opp = new JButton("OPP");
        class Opp implements ActionListener{
                       
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.oppdaterBevegelse("OPP");
            }  
        }
        opp.addActionListener(new Opp());
       

        //NED
        ned = new JButton("NED");
        class Ned implements ActionListener{
                       
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.oppdaterBevegelse("NED");
            }  
        }
        ned.addActionListener(new Ned());
        

        //VENSTRE
        venstre = new JButton("VENSTRE");
        class Venstre implements ActionListener{
                       
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.oppdaterBevegelse("VENSTRE");
            }  
        }
        venstre.addActionListener(new Venstre());
        

        //HOYRE
        hoyre = new JButton("HOYRE");
        class Hoyre implements ActionListener{
                       
            @Override
            public void actionPerformed(ActionEvent e) {
                controll.oppdaterBevegelse("HOYRE");
            }  
        }
        hoyre.addActionListener(new Hoyre());
        
        clear = new JButton("");
        styringspanel.add(clear, BorderLayout.CENTER);
        styringspanel.add(opp, BorderLayout.NORTH);
        styringspanel.add(hoyre, BorderLayout.EAST);
        styringspanel.add(venstre, BorderLayout.WEST);
        styringspanel.add(ned, BorderLayout.SOUTH);
        panel.add(styringspanel, BorderLayout.CENTER);
       

        

        //Rutenett
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(GRID, GRID));
        rutenett.setPreferredSize(new DimensionUIResource(PANELBREDDE, PANELHOYDE));
        rutenett.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        mainpanel.add(rutenett, BorderLayout.SOUTH);

        for (int i = 0; i < GRID; i++){
            for (int j = 0; j < GRID; j++){
                JLabel rute = new JLabel("", SwingConstants.CENTER);
                rute.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                ruter[i][j] = rute;
                rute.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
                
                rutenett.add(rute);
            }
        }



       
        vindu.setSize(PANELBREDDE, PANELHOYDE);
        vindu.setVisible(true);
        vindu.pack();
        vindu.setVisible(true);

    }

    public void tegnSlange(int rad, int kol){
        ArrayList<Slange> slange = controll.slange();
        for(Slange del : slange){
            if (del.erHode()){
                ruter[del.hentRad()][del.hentKolonne()].setText("O");
                ruter[del.hentRad()][del.hentKolonne()].setForeground(Color.BLUE);
            } else{
                    ruter[del.hentRad()][del.hentKolonne()].setText("X");
                    ruter[del.hentRad()][del.hentKolonne()].setForeground(Color.GREEN);
            }
        }
    }

    public void tegnStartGame(){
        for (int i = 0; i < GRID; i++){
            for (int j = 0; j < GRID; j++){
                ruter[i][j].setText("");
            }
        } 
        ruter[6][6].setText("0");
        ruter[6][6].setForeground(Color.BLUE);
        controll.voks(new Slange(6,6,true));

        for (int i = 0; i < 10; i++){
            int skatt_rad = 0;
            int skatt_kol = 0;

            while((skatt_rad != 6 && skatt_kol != 6) || ruter[skatt_rad][skatt_kol].getText().equals("$")){
                skatt_rad = Skatt.trekk(0, 11);
                skatt_kol = Skatt.trekk(0, 11);
                if ((skatt_rad != 6 && skatt_kol != 6) || ruter[skatt_rad][skatt_kol].getText().equals("$")){
                    break;
                }
                ruter[skatt_rad][skatt_kol].setText("$");
                Skatt ny= new Skatt(skatt_rad,skatt_kol);
                controll.leggTilSkatt(i, ny);

            }
        }
    }

    public void tegnNyttBrett(){
        ArrayList<Slange> slange = controll.hentSlange();
        Skatt[] skatt = controll.hentSkatter();

        for (int i = 0; i < GRID; i++){
            for (int j = 0; j < GRID; j++){
                ruter[i][j].setText("");

            }
        }
        for (int i = 0; i < skatt.length; i++){
            if (skatt[i] != null){
                ruter[skatt[i].hentRad()][skatt[i].hentKolonne()].setText("$");
            }
        }
        for (Slange del : slange){
            int slangeRad = del.hentRad();
            int slangeKol = del.hentKolonne();

            if (del.erHode()){
                ruter[slangeRad][slangeKol].setText("0");
                ruter[slangeRad][slangeKol].setForeground(Color.BLUE);
            } else{
                ruter[slangeRad][slangeKol].setText("X");
                ruter[slangeRad][slangeKol].setForeground(Color.GREEN);
            }
            poeng.setText(" "+ controll.hentLengde() + " ");
        }
    }

}

