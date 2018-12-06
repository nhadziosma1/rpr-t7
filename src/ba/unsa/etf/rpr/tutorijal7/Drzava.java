package ba.unsa.etf.rpr.tutorijal7;

public class Drzava
{
    private String naziv;
    private int brojStanovnika;
    private double povrsina;
    private String jedinicaZaPovrsinu;
    private Grad glavniGrad;
    //////////////////////////////////

    public Drzava()
    {
        naziv= new String();
        brojStanovnika=0;
        povrsina=0;
        jedinicaZaPovrsinu = new String();
        glavniGrad= new Grad();
    }


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

    public double getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(double povrsina) {
        this.povrsina = povrsina;
    }

    public String getJedinicaZaPovrstinu() {
        return jedinicaZaPovrsinu;
    }

    public void setJedinicaZaPovrstinu(String jedinicaZaPovrstinu) {
        this.jedinicaZaPovrsinu = jedinicaZaPovrstinu;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }


}
