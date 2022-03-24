
/**
 * Beschreiben Sie hier die Klasse Topf.
 * 
 * @Lukas 
 * 09.03
 */
public class Topf
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int einsatz;
    

    /**
     * Konstruktor für Objekte der Klasse Topf
     */
    public Topf()
    {
        einsatz = 0;
        
    }
    
    public void einsatzAufnehmen(int pEinsatz)
    {
       einsatz += pEinsatz;
       
    }
    
    public int einsatzAbgeben()
    {
        
        return einsatz ;
    }
    
}
