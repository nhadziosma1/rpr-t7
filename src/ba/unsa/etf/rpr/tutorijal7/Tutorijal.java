package ba.unsa.etf.rpr.tutorijal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Tutorijal
{
    public static void main(String  []arg)
    {
        ArrayList<Grad> ar_gr = ucitajGradove();
        UN un = ucitajXml(ar_gr);

        for(int i=0; i<un.getuUNu().size(); i++)
        {
            System.out.println("naziv drzave: "+ un.getuUNu().get(i).getNaziv() );
            System.out.println("broj stanovnika drzave: "+ un.getuUNu().get(i).getBrojStanovnika() );
            System.out.println("jedinica za povrsinu: "+ un.getuUNu().get(i).getJedinicaZaPovrstinu() );
            System.out.println("povrsina: "+ un.getuUNu().get(i).getPovrsina() );
            System.out.println("glavni grad: "+ un.getuUNu().get(i).getGlavniGrad().getNaziv() );

            System.out.print("Temperature: ");
            for(int j=0; j<1000; j++)
            {
                if(un.getuUNu().get(i).getGlavniGrad().getTemperature()[j]!=0)
                {
                    System.out.print(un.getuUNu().get(i).getGlavniGrad().getTemperature()[j]+" ");
                }

            }
            System.out.println("");
        }

    }

    static ArrayList<Grad> ucitajGradove()
    {
        Scanner citajIzDatoteke= null;

        try
        {
            citajIzDatoteke =new Scanner( new FileReader("mjerenja.txt"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Datoteka mjerenja.txt ne postoji ili se ne moze otvoriti.");
        }

        ArrayList<Grad> ucitaniGradovi=new ArrayList<>();

        try
        {
            while(citajIzDatoteke.hasNext() )
            {
                int brojac=0;
                Grad gr = new Grad();
                String naziv, linijaIzFajla;

                linijaIzFajla = citajIzDatoteke.nextLine();

                String [] linijaRazdvojenihPodataka = linijaIzFajla.split(",");
                // Metoda "split" klase string kada prima jedan parametar, jedan veliki string razdvaja na
                // niz malih stringova na osnovu unesenog parametra u metodi koji se zanemaruje ???????????????????????

                naziv = linijaRazdvojenihPodataka[brojac];
                brojac++;

                double[] temperature = new double[1000];
                for(int i=0; i<linijaRazdvojenihPodataka.length-1; i++)
                {
                    if(i<=1000)
                        temperature[i] = Double.parseDouble( linijaRazdvojenihPodataka[brojac] );

                    brojac++;
                }

                if(brojac>1001)
                    System.out.println("U datoteci se nalazilo vise od 1000 mjerenja temperature");

                gr.setNaziv(naziv);
                gr.setBrojStanovnika(0);
                gr.setTemperature(temperature);

                ucitaniGradovi.add(gr);
            }

        }
        catch(Exception e)
        {
            System.out.println("Problem pri citanju datoteke");
        }
        finally
        {
            citajIzDatoteke.close();
        }

        return ucitaniGradovi;
    }

    static void zapisiXml(UN u)
    {
        XMLEncoder izlaz=null;
        try
        {
            izlaz = new XMLEncoder(new FileOutputStream("un.xml"));
            izlaz.writeObject(u);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        finally
        {
            izlaz.close();
        }

    }

    static UN ucitajXml(ArrayList<Grad> ucitaniGradovi)
    {
        Document xml_dokument=null;

        UN un = new UN();
        int br_u_gradu=0, br_u_drzavi=0;
        Drzava drz = new Drzava();
        String naziv_drzave= new String();
        String jedinica = new String();
        String naziv_monete = new String();
        String naziv_glavnog_grada = new String();
        double povrsina=0;
        Grad gr= new Grad();

        try
        {
            DocumentBuilder xml_pisac = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            xml_dokument = xml_pisac.parse(new File("C:\\Users\\nedza\\IdeaProjects\\rpr-t7\\src\\ba\\unsa\\etf\\rpr\\tutorijal7\\drzava.xml"));
            //DOM je rezultat parsiaranja, a DOM

            NodeList drzave_nodovi = xml_dokument.getElementsByTagName("drzava");
            // "NodeList" sada sadrzi tagove koji pocinju sa <drazva> ... </drzava> u XML fajlu, sto znaci da ih moze biti vise,
            // a tip elemenata koji se drze u ovoj kolekciji je "Node"

            for(int i=0; i<drzave_nodovi.getLength(); i++)
            {
                Node jedna_drzava = drzave_nodovi.item(i);

                if(jedna_drzava instanceof Element)
                {
                    //"EMELEMNT_NODE" označava da se radi o nekom tagu  ?????????????????????
                    Element drzava = (Element) jedna_drzava;

                    String stanovnika = drzava.getAttribute("stanovnika");
                    br_u_drzavi = Integer.parseInt(stanovnika);
                    drz.setBrojStanovnika(br_u_drzavi);
                    //"metoda "getAttrinute" vraca string, tj. VRIJEDNOST atributa u vidu stringa

                    NodeList djeca_drzave = drzava.getChildNodes();
                    //Da bi dobili od specificiranog noda svu njegovu djecu, tj sve nodove ekoji se nalaze unutar tog noda
                    //koristimo metodu "getChildrenNodes()" koja vraca NodeList

                    for(int j=0; j<djeca_drzave.getLength(); j++)
                    {
                        Node jedno_djete_drzave = djeca_drzave.item(j);

                        if(jedno_djete_drzave instanceof Element)
                        {
                            Element e_jedno_dijete_drzave = (Element) jedno_djete_drzave;
                            //System.out.println( "!!!!!!!!!!!"+((Element) jedno_djete_drzave).getTagName() );

                            if(((Element) jedno_djete_drzave).getTagName().equals("naziv"))
                            {
                                naziv_drzave = jedno_djete_drzave.getTextContent();

                                drz.setNaziv(naziv_drzave);
                            }
                            else if(((Element) jedno_djete_drzave).getTagName().equals("glavnigrad"))
                            {
                                naziv_glavnog_grada = ((Element) jedno_djete_drzave).getElementsByTagName("naziv").item(0).getTextContent();

                                br_u_drzavi = Integer.parseInt(((Element) jedno_djete_drzave).getAttribute("stanovnika"));

                                gr.setNaziv(naziv_glavnog_grada.trim());
                                gr.setBrojStanovnika(br_u_drzavi);

                                for(int k=0; k<ucitaniGradovi.size(); k++)
                                {
                                    if(gr.getNaziv().equals( ucitaniGradovi.get(k).getNaziv() ))
                                        gr.setTemperature(ucitaniGradovi.get(k).getTemperature());
                                }
                            }
                            else if(((Element) jedno_djete_drzave).getTagName().equals("povrsina"))
                            {
                                jedinica = ((Element) jedno_djete_drzave).getAttribute("jedinica");
                                povrsina = Integer.parseInt( jedno_djete_drzave.getTextContent() );

                                drz.setPovrsina(povrsina);
                                drz.setJedinicaZaPovrstinu(jedinica);
                            }
                            else if(((Element) jedno_djete_drzave).getTagName().equals("moneta"))
                            {
                                naziv_monete = jedno_djete_drzave.getTextContent();
                            }

                        }
                    }
                    drz.setGlavniGrad(gr);
                    un.getuUNu().add(drz);
                    drz = new Drzava();
                    gr = new Grad();
                    // bez ovoga bi se svi miejnjai clanovi svake drzvae, jer su promjenjive koje su napisane na pocetku reference na iste objekte
                }

            }

        }
        catch(Exception e)
        {
            //CastClassException
            System.out.println("drzava.xml nije validan XML dokument, ili problemi pri citanju");
        }

        return un;
    }


}
