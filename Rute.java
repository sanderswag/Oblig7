public class Rute {
    protected int rad;
    protected int kolonne;

    public Rute(int rad, int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;

    }

    public int hentRad(){
        return rad;
    }

    public int hentKolonne(){
        return kolonne;
    }

    public void endreRad(int rad){
        this.rad = rad;
    }

    public void endreKolonne(int kol){
        this.kolonne = kol;
    }
    
}
