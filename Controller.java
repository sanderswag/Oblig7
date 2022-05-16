import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
class Controller{
    private GUI gui;
    private Modell model;
    private boolean erOpp, erNed, erHoyre, erVenstre;
    Controller () {
        gui = new GUI(this);
        model = new Modell(gui, this);
        //model = new Model(gui);
    }

    public void startSpill() throws InterruptedException{
        model.startGame();
        spillLoop();
    }

    public void spillLoop() throws InterruptedException{
        while(model.runGame()){
            Thread.sleep(500);
            update();
            if (model.runGame() == false){
                break;
            }
            gui.tegnNyttBrett();
        }
    }

    public void tegnBrett(){
        gui.tegnStartGame();
    }
    
    public void avsluttSpillet(){
        System.exit(-1);
    }
    public ArrayList<Slange> hentSlange(){
        return model.hentSlange();
    }
    public Skatt[] hentSkatter(){
        return model.hentSkatter();
    }

    public ArrayList<Slange> slange(){
        return model.hentSlange();
    }
   
    public void leggTilSkatt(int pos, Skatt skatt){
        model.leggTilSkatt(pos, skatt);
    }
    public int hentLengde(){
        return model.hentLengde();
    }


    public void voks(Slange del){
        model.voks(del);
    }
    public void bevegSlange(){
        if (erOpp){
            model.beveg("OPP");
        }
        if (erNed){
            model.beveg("NED");
        }
        if (erHoyre){
            model.beveg("HOYRE");
        }
        if (erVenstre){
            model.beveg("VENSTRE");
        }
    }

    public void spisHale(){
        model.spisHale();
    }

    public void update(){
        bevegSlange();
        spisHale();
    }

    //Sjekker og oppdaterer slange bevegelse.
    public void oppdaterBevegelse(String retning){
        switch (retning) {
            case "OPP":
                if (erNed){
                    return;
                }
                erOpp = true;
                erHoyre = false;
                erVenstre = false;
                erNed = false;
                break;
        
            case "NED":
                if (erOpp){
                    return;
                }
                erOpp = false;
                erNed = true;
                erVenstre = false;
                erHoyre = false;
                break;
            case "VENSTRE":
                if (erHoyre){
                    return;
                }
                erVenstre = true;
                erHoyre = false;
                erNed = false;
                erOpp = false;
                break;

            
            case "HOYRE":
                if (erVenstre){
                    return;
                }
                erVenstre = false;
                erHoyre = true;
                erOpp = false;
                erNed = false;
                break;
        }
    }
}