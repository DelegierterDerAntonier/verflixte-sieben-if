
/**
 * Beschreiben Sie hier die Klasse Topf. Die Klasse Topf soll den Einsatz der
 * Spieler aufnehmen und an den Gewinner der Runde aufnehmen.
 * 
 * @Lukas 
 * 09.03
 */
public class Topf
{
    // 
    private int einsatz;
    

    /**
     * Konstruktor für Objekte der Klasse Topf,einsatz = 0 wird hinzugefügt bei Einsatz aufnehmen
     */
    public Topf()
    {
        einsatz = 0;
        //einsatz = 0 wird hinzugefügt bei Einsatz aufnehmen
    }
    
    /**
     *  einsatz im Topf + Einsatz des Spielers
     */ 
     public void einsatzAufnehmen(int pEinsatz)
    {
        // einsatz im Topf + Einsatz des Spielers
        einsatz += pEinsatz;
       
    }
    
    /**
     * gibt einsatz an Spieler zurück
     */public int einsatzAbgeben()
    {
        //einsatz an Spieler zurück geben
        return einsatz ;
    }
    /**
     * Einsatz wird geleert
     */
      public void leeren()
    {
        einsatz = 0;
    }
}
