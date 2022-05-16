public class Skatt extends Rute {


    public Skatt(int rad, int kolonne){
        super(rad,kolonne);
    }
 
    static int trekk (int minst, int mest) {
        // Trekk et tilfeldig heltall i intervallet [a..b];
        return (int)(Math.random()*(mest-minst+1))+minst;
        }
    
}
