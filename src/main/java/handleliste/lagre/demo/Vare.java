package handleliste.lagre.demo;

public class Vare {
    private int id;
    private String vare;
    private String antall;

    public Vare(int id, String vare, String antall) {
        this.id = id;
        this.vare = vare;
        this.antall = antall;
    }

    public Vare( ) { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVare() {
        return vare;
    }

    public void setVare(String vare) {
        this.vare = vare;
    }

    public String getAntall() {
        return antall;
    }

    public void setAntall(String antall) {
        this.antall = antall;
    }
}

