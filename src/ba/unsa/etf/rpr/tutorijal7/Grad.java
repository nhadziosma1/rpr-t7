package ba.unsa.etf.rpr.tutorijal7;

public class Grad
{
    private String naziv;
    private int brojStanovnika;
    private double[] temperature;
    ///////////////////////////////////////////
    //KONSTRUKTOR BEZ PARAMETARA:
    public Grad()
    {
    }

    //GETTERI I SETERI
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public double[] getTemperature() {
        return temperature;
    }

    public void setTemperature(double[] temperature) {
        this.temperature = temperature;
    }


}
