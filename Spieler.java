import java.util.Arrays;

/**
Klasse Spieler beeinhaltet die Methoden, die ein Spieler im Spiel "Verflixte 7 haben würde.
* @author (KingKong)
* @version (0_5) Zuletzt bearbeitet am 27.3.2022
*/
public class Spieler
{
  /** 
  * Instanzvariablen für die Klasse Spieler
  */
  private String name;
  private int punkte;
  private int vermoegen;
  private int wurfAnzahl;
  private Topf topf;
  private Wuerfel wuerfel1,wuerfel2;
  private boolean darfWuerfeln;
  private int durchgangsSiege;


  /**
  *  Überladener Konstruktor für die Klasse Spieler
  */
  public Spieler(Topf pTopf)
  {
    wuerfel1 = new Wuerfel();
    wuerfel2 = new Wuerfel();
    topf = pTopf;
    punkte = 0;
    wurfAnzahl = 0;
    darfWuerfeln = true;
    vermoegen = 10;
    durchgangsSiege = 0;
  }
  
  /**
  *Beendet den Spielzug des Spielers, der diesen Befehl ausführ. Würfelrecht de Spielers wird deaktiviert. Das Würfelrecht wird nun weitergegeben
  */
  public void durchgangBeenden()
  {
    darfWuerfeln = false;
  }
  
  /**
  *  Punkte als int zurückgeben
  */
  public int punktestandAngeben()
  {
    
    return punkte;
  }
  
  public void punkteZuruecksetzen()
  {
      punkte = 0;
  }

  /**
  *Einsatz von beiden Spielern (Topf) an den Spieler geben
  */
  public void topfLeeren()
  {

  vermoegen += topf.einsatzAbgeben();
  }
  
  /**    
  * gibt die Anzahl der Würfe zurück
  */
  public int wurfAnzahlAngeben()
  {

      return wurfAnzahl;
  }
  
    /**
    * EINSATZ BZW VERMÖGENALGORITHMUS. SOLANGE DAS VERMOEGEN GROESSER ALS DER EINSATZ IST KANN MAN EINEN EINSATZ SETZEN, SOLANGE MAN AN DER REIHE IST.
    */
    public void einsatzSetzen(int pEinsatz)
  {
      if (vermoegen < pEinsatz)
      {
           pEinsatz = vermoegen;
      }
      vermoegen = vermoegen - pEinsatz;
      darfWuerfeln = true;
      topf.einsatzAufnehmen(pEinsatz);
  }
  
  /**
  *Setzt die Anzahl der Würfe wieder auf 0 zurück
  */
  public void resetWurfAnzahl()
  {
      wurfAnzahl = 0;
  }
    
  /**
    *Spieler ist in der Lage einen eigenen Namen zu erstellen
    */
  public void setName(String neuerName)
  {
     
    this.name = neuerName;       

  }
  
  /**
  *Der Spieler würfelt und bekommt eine Zufallszahl von 1-12/Nur einer der beiden Spieler ist in der Lage würfeln zu können/ beim würfeln einer 7 ist der Zug des Spielers beendet  
  */
  public int[] wuerfeln()
  {
    int[] lol = {0};
    
    if (darfWuerfeln)
    {
        wuerfel1.rollen();
        wuerfel2.rollen();
        wurfAnzahl ++;
        int wurf1 = wuerfel1.punkteZahlAngeben();
        int wurf2 = wuerfel2.punkteZahlAngeben(); 
        int augensumme = wuerfel1.punkteZahlAngeben() + wuerfel2.punkteZahlAngeben();
        
        if (augensumme != 7)
        {
            punkte += augensumme;
            int[] ergebnis = {augensumme, wurfAnzahl, punkte};
            return ergebnis;
        }
        else
        {
            punkte -= augensumme;
            darfWuerfeln = false;
            int[] ergebnis = {augensumme, wurfAnzahl, punkte};
            return ergebnis;
        }
    }
    return lol;
  }
  
  
  /**
  * Auf das aktuelle Vermögen wird zugegriffen
  */
  public int getVermoegen()
  {
      return vermoegen;
  }
  
  /**
  * Das Vermögen wird unter einem Parameter gespeichert
  */
  public void setVermoegen(int pV)
  {
      vermoegen = pV;
  }
  
  /**
  * Auf die aktuellen Siege wird zugegriffen
  */
  public int getSiege()
  {
      return durchgangsSiege;
  }
  
  /**
  * Die Siege werden unter einem Parameter gespeichert
  */
  public void setSiege(int pSiege)
  {
      durchgangsSiege = pSiege;
  }
}
