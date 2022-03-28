
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Würfel.
 * 
 * @author (BetaShin) 
 * @version (1.1)
 * 
 */
public class Wuerfel
{
    // Instanzvariablen - punkte für Augen Zahl und zufallsAugenAnzahl für eine Zufällige zahl
    private int punkte;
    private Random zufallsZahl;

    /**
     * Konstruktor für Objekte der Klasse Würfel
     */
    public Wuerfel()
    {
        zufallsZahl = new Random();
    }

    public void testen()
    {
        zufallsZahlAusgeben();
        System.out.println(punkte);
    }
    
    /**
     * 
     * Methode, um Punkte anzugeben
     */
    public int punkteZahlAngeben()
    {
         return punkte;
    }
    
    /**
     * Methode zur Ausgabe einer Zufälligen Zahl zwischen 1 und 6
     */
    public int zufallsZahlAusgeben()
    {
        //erstellt eine Zufällige Zahl zwischen 1 und 6//
        int pMaximum = 5;
        punkte = 1 + zufallsZahl.nextInt(pMaximum);// zufalls Zahl + 1, da man von 0 anfängt zu zählen
        return punkte; 
    }

    /**
     * Rollen() Methode für eine Zufällige Augen Zahl
     */
    public void rollen()
    {
        zufallsZahlAusgeben();
    }
    
}
