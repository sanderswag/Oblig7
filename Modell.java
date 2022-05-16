import java.util.ArrayList;

public class Modell {
    private GUI gui;
    private String[][] brett;
    private Controller controller;
    private final int GRID = 12;
    private int halelengde = 1;
    private ArrayList<Slange> slange = new ArrayList<>();
    private Skatt[] skatt = new Skatt[10];
    private boolean run = true;
    

    public Modell(GUI gui, Controller controller) {
        this.gui = gui;
        this.brett = brett;
        this.controller = controller;
    } 

    public void startGame(){
        run = true;
        controller.tegnBrett();
    }

    public ArrayList<Slange> hentSlange(){
        return slange;
    }
    public Skatt[] hentSkatter(){
        return skatt;
    }

    public void leggTilSkatt(int pos, Skatt skatter){
        skatt[pos] = skatter;
    }

    public int hentLengde(){
        return slange.size();
    }

    public Slange hentHode(){
        for (Slange del : slange){
            if (del.erHode()){
                return del;
            }
        }
        return null;
    }

    public void spisHale(){
        if (slange.size() > 1){
            for (Slange del : slange){
                if (hentHode().hentRad() == del.hentRad() && hentHode().hentKolonne() == del.hentKolonne() && !del.erHode()){
                    avslutt();
                }
            }
        }
    }

    public void fjernDel(Slange del){
        slange.remove(del);
    }

    public void voks(Slange del){
        slange.add(del);
    }

    public boolean avslutt(){
        return run = false;
    }

    public boolean runGame(){
        return run;
    }


    public void beveg(String retning){
        Slange nyttHode;
        switch (retning) {
            case "HOYRE":
            if (hentHode().hentKolonne() + 1 > 11) {
                avslutt();
                }

            if (slange.size() == 1) {
                nyttHode = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true);
                if (!spisSkatt()){
                    slange.remove(halelengde-1);
                    }
                for (Slange del : slange){
                 del.fjernHode();
                    }
                slange.add(nyttHode);
                return;
            }

            nyttHode = new Slange(hentHode().hentRad(), hentHode().hentKolonne() + 1, true); 
            for (Slange del : slange){
                del.fjernHode();
            }
            if (!spisSkatt()){
                slange.remove(halelengde-1);
                }

            slange.add(nyttHode);
            break;
        
            case "VENSTRE":
            if (hentHode().hentKolonne() - 1 < 0){
                avslutt();
                }

            if (slange.size() == 1) {
                nyttHode = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true);
                if (!spisSkatt()){
                    slange.remove(halelengde-1);
                    }
                for (Slange del : slange){
                 del.fjernHode();
                    }
                slange.add(nyttHode);
                return;
            }

            nyttHode = new Slange(hentHode().hentRad(), hentHode().hentKolonne() - 1, true); 
            for (Slange del : slange){
                del.fjernHode();
            }
            if (!spisSkatt()){
                slange.remove(halelengde-1);
                }

            slange.add(nyttHode);
            break;
            
            case "OPP":
            if (hentHode().hentRad() - 1 < 0){
                avslutt();
                }

            if (slange.size() == 1) {
                nyttHode = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true);
                if (!spisSkatt()){
                    slange.remove(halelengde-1);
                    }
                for (Slange del : slange){
                    del.fjernHode();
                    }
                slange.add(nyttHode);
                return;
            }

            nyttHode = new Slange(hentHode().hentRad() - 1, hentHode().hentKolonne(), true); 
            for (Slange del : slange){
                del.fjernHode();
                }

            if (!spisSkatt()){
                slange.remove(halelengde-1);
                }
            slange.add(nyttHode);
            break;


            case "NED":
        
            if (hentHode().hentRad() + 1 > 11){
                avslutt();
                }

            if (slange.size() == 1) {
                nyttHode = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true);
                if (!spisSkatt()){
                    slange.remove(halelengde-1);
                    }
                for (Slange del : slange){
                 del.fjernHode();
                    }
                slange.add(nyttHode);
                return;
            }

            nyttHode = new Slange(hentHode().hentRad() + 1, hentHode().hentKolonne(), true); 
            for (Slange del : slange){
                del.fjernHode();
            }
            if (!spisSkatt()){
                slange.remove(halelengde-1);
                }

            slange.add(nyttHode);
            break;
        }
    }

    public void leggTilSkatt(int pos){
        boolean ikke_treff = false;
        int skatt_kol = 0;
        int skatt_rad = 0;
        

        while (true){
            for (Slange del : slange){
                for (Skatt bit : skatt){
                    if (bit != null){
                        skatt_rad = Skatt.trekk(0, 11);
                        skatt_kol = Skatt.trekk(0,11);
                        if (skatt_rad != del.hentRad() && skatt_rad != bit.hentRad() && skatt_kol != del.hentKolonne() && skatt_kol != bit.hentKolonne()){
                            ikke_treff = true;
                        }else {
                            ikke_treff = false;
                        }
                    }
                }
                if (ikke_treff){
                    break;
                }
            }
            skatt[pos] = new Skatt(skatt_rad, skatt_kol);
        }
    }

    public void fjernSkatt(int pos){
        skatt[pos] = null;
        
    }

    public boolean spisSkatt(){
        for (int i = 0; i < skatt.length; i++){
            for (Slange del : slange){
                if (skatt[i] != null){
                    if (skatt[i].hentRad() == del.hentRad() && skatt[i].hentKolonne() == del.hentKolonne() ){
                        fjernSkatt(i);
                        leggTilSkatt(i);
                        return true;
                    }
                }
            }
            
        }
        return false;

    }
}
