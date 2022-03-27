import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
 
/**
 * GUI und Spielsimulation in einem
 * 
 * @author Toni 
 * @version 1.0/27.3.22 
 */
public class GUI extends JFrame implements ActionListener{
    private JTextArea konsole, spieler, vermoegen, topf, wuerfelausgabe;
    private JButton zugBeenden, abbruch, wuerfeln;
    private Spieler spieler1, spieler2;
    private JOptionPane frame, frame2, einsatzFrame, sieg;
    private String spielername1, spielername2;
    private boolean werIstDran; //true = Spieler 1, false = Spieler 2
    private Topf spieltopf;
    
    /**
     * Main. Startet alles
     */
    public static void main(String[] args) {
     GUI neueGUI = new GUI();
     neueGUI.createGUI();
     neueGUI.starteSpiel();
    }
    
    /**
     * Erstellt die GUI
     * Panels:
     * Panel
     * topPanel
     * sidePanel
     */
    public void createGUI() {
      JFrame meinJFrame = new JFrame();
      meinJFrame.setTitle("Verflixte Sieben");
      meinJFrame.setSize(1000, 800);
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      JPanel topPanel = new JPanel();
      JPanel sidePanel = new JPanel();
      sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
      
      //20-zeiliges und 50-spaltiges Textfeld wird erzeugt
      konsole = new JTextArea(20, 50);
 
      //Text für das Textfeld wird gesetzt
      konsole.setText("Willkommen zu Verflixte Sieben");
      //Zeilenumbruch wird eingeschaltet
      konsole.setLineWrap(true);
 
      //Zeilenumbrüche erfolgen nur nach ganzen Wörtern
      konsole.setWrapStyleWord(true);
 
      //Ein JScrollPane, der das Textfeld beinhaltet, wird erzeugt
      JScrollPane scrollpane = new JScrollPane(konsole);       
 
      //Scrollpane wird unserem Panel hinzugefügt
      panel.add(scrollpane, BorderLayout.CENTER);
      
      
      //Panels zum Panel hinzufügen
      panel.add(topPanel, BorderLayout.PAGE_START);
      panel.add(sidePanel, BorderLayout.LINE_END);
      
      //Textfeld und Buttons hinzufügen
      spieler = new JTextArea(1,20);
      spieler.setText("Spieler");
      
      zugBeenden = new JButton("Zug beenden");
      zugBeenden.addActionListener(this);
      
      abbruch = new JButton("Beenden");
      abbruch.addActionListener(this);
      
      wuerfeln = new JButton();
      try {
            Image img = ImageIO.read(new File("wuerfel.png"));
            wuerfeln.setIcon(new ImageIcon(img));
      } catch(Exception e) {
            wuerfeln.setText("Wuerfeln");
      }
      wuerfeln.addActionListener(this);
      
      vermoegen = new JTextArea(1,20);
      vermoegen.setText("Vermoegen: \n  100");
      
      topf = new JTextArea(1,20);
      topf.setText("Topf: \n 0");
      
      wuerfelausgabe = new JTextArea(1,20);
      wuerfelausgabe.setText("Wuerfelausgabe: \n ");
      
      
      //add everything to the panels
      topPanel.add(spieler);
      spieler.setAlignmentY( Component.CENTER_ALIGNMENT );
      
      topPanel.add(zugBeenden);
      zugBeenden.setAlignmentY( Component.CENTER_ALIGNMENT );
      
      topPanel.add(abbruch);
      abbruch.setAlignmentY( Component.CENTER_ALIGNMENT );
      
      Dimension minSize = new Dimension(0,100);
      Dimension prefSize = new Dimension(0, 100);
      Dimension maxSize = new Dimension(0, Short.MAX_VALUE);
      
      sidePanel.add(vermoegen);
      
      sidePanel.add(topf);
      
      sidePanel.add(new Box.Filler(minSize, prefSize, maxSize));
      
      sidePanel.add(new Box.Filler(minSize, prefSize, maxSize));
      
      sidePanel.add(wuerfeln);
      
      sidePanel.add(new Box.Filler(minSize, prefSize, maxSize));
      
      sidePanel.add(wuerfelausgabe);
      
      sidePanel.add(new Box.Filler(minSize, prefSize, maxSize));
      
      sidePanel.add(new Box.Filler(minSize, prefSize, maxSize));
      
      // make it pretty
      topPanel.setBackground(new Color(46,64,83));
      sidePanel.setBackground(new Color(46,64,83));
      
      konsole.setBackground(Color.black);
      konsole.setForeground(Color.white);
      
      // Font beauty = new Font("Linux Libertine G", Font.PLAIN, 15);
      // textfeld.setFont(beauty);
      //wow so pretty
      
      //dem Frame hinzufügen
      meinJFrame.add(panel);
      meinJFrame.setVisible(true);
      }
      
    /**
     * Startet das Spiel.
     * Fragt Spielernamen ab und gibt Spieler 1 den ersten Zug
     */
    public void starteSpiel()
    {
        spieltopf = new Topf();
        spieler1 = new Spieler(spieltopf);
        spieler2 = new Spieler(spieltopf);
        
        topf.setText("Topf:" + "\n" + spieltopf.einsatzAbgeben());
        
        Object[] possibilities = {null};
        String s = (String)JOptionPane.showInputDialog(
                    frame,
                    "Spieler 1, bitte Namen eingeben",
                    "Namensabfrage",
                    JOptionPane.PLAIN_MESSAGE);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            spielername1 = s;
        }

        //implement some kind of check if a string was actually given
        
        String s2 = (String)JOptionPane.showInputDialog(
                    frame2,
                    "Spieler 2, bitte Namen eingeben",
                    "Namensabfrage 2",
                    JOptionPane.PLAIN_MESSAGE);

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            spielername2 = s2;
        }

        //same here
        
        werIstDran = true;
        SpielzugSpieler1();
    }
    
    /**
     * Ein Spielzug von Spieler 1.
     * Ändert Angaben von Spielername, Vermögen und Topf.
     * Holt den Einsatz
     */
    public void SpielzugSpieler1()
    {
        newLine();
        addText(spielername1);
        addText(", du bist dran!");
        spieler.setText(spielername1);
        vermoegen.setText("Vermoegen:" + "\n" + spieler1.getVermoegen());
        topf.setText("Topf:" + "\n" + spieltopf.einsatzAbgeben());
        einsatzHolen();
        vermoegen.setText("Vermoegen:" + "\n" + spieler1.getVermoegen());
        newLine();
        addText("Jetzt kannst du würfeln (auf die Würfel drücken)");
    }
    
    /**
     * Ein Spielzug von Spieler 2.
     * Ändert Angaben von Spielername, Vermögen und Topf.
     */
    public void SpielzugSpieler2()
    {
        hatGewonnen();
        newLine();
        addText(spielername2);
        addText(", du bist dran!");
        spieler.setText(spielername2);
        vermoegen.setText("Vermoegen:" + "\n" + spieler2.getVermoegen());
        topf.setText("Topf:" + "\n" + spieltopf.einsatzAbgeben());
        newLine();
        addText("Du kannst jetzt würfeln (auf die Würfel drücken)");
    }
    
     /**
     * Was passiert, wenn die Buttons gedrückt werden.
     * Zug beenden: Der Zug wird beendet und der andere Spieler ist dran.
     * Beenden: Das Spiel wird beendet.
     * Würfeln: Der Spieler würfelt, das Ergebnis wird ausgegeben.
     */ 
    public void actionPerformed(ActionEvent e){
           
        if(e.getSource() == this.zugBeenden){
            if(werIstDran)
            {
                werIstDran = false;
                SpielzugSpieler2();
            }
            else
            {
                hatGewonnen();
            }
        }
        else if (e.getSource() == this.abbruch){
            String ende = spielername1 + " hat " + spieler1.getSiege() + " Durchgänge gewonnen. " + spielername2 + " hat " + spieler2.getSiege() + " Durchgänge gewonnen.";
            JOptionPane.showMessageDialog(sieg,
            ende,
            "Ende",
            JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else if (e.getSource() == this.wuerfeln){
            if(werIstDran)
            {
                newLine();
                int[] ergebnisse = spieler1.wuerfeln();  // augensumme, wurfanzahl, punkte
                wuerfelausgabe.setText("Wuerfelausgabe: \n " + ergebnisse[0]);
                if(ergebnisse[0] != 7)
                {
                    addText("Du hast die " + ergebnisse[0] +" gewürfelt. Dein aktueller Punktestand liegt bei " + ergebnisse[2] + ". Dafür hast du " + ergebnisse[1] + 
                    " Würfe gebraucht.Du kannst den Zug beenden oder nochmal würfeln.");
                }
                else
                {
                    addText("Du hast eine 7 gewürfelt! Dein Punktestand liegt bei " + ergebnisse[2] + ". Dein Zug ist beendet.");
                    werIstDran = false;
                    
                    spieltopf.leeren();
                    SpielzugSpieler2();
                }
            }
            else
            {
                newLine();
                int[] ergebnisse = spieler2.wuerfeln();  // augensumme, wurfanzahl, punkte
                wuerfelausgabe.setText("Wuerfelausgabe: \n " + ergebnisse[0]);
                if(ergebnisse[0] != 7)
                {
                    addText("Du hast die " + ergebnisse[0] +" gewürfelt. Dein aktueller Punktestand liegt bei " + ergebnisse[2] + ". Dafür hast du " + ergebnisse[1] + 
                    " Würfe gebraucht.Du kannst den Zug beenden oder nochmal würfeln.");
                    hatGewonnen();
                }
                else
                {
                    addText("Du hast eine 7 gewürfelt! Dein Punktestand liegt bei " + ergebnisse[2] + ". Dein Zug ist beendet.");
                    werIstDran = true;
                    
                    spieltopf.leeren();
                    hatGewonnen();
                }
            }
        }
            
    }
    
    /**
     * Fragt den Spieler, wie viel er setzen möchte und gibt den Einsatz von beiden Spielern in den Topf
     */
    public void einsatzHolen()
    {
        String einsatz = (String)JOptionPane.showInputDialog(
                    einsatzFrame,
                    "Wie viel möchtest du setzen?",
                    "Einsatz",
                    JOptionPane.PLAIN_MESSAGE);

        //Einsatz setzen
        if ((einsatz != null) && (einsatz.length() > 0)) {
            int eInsatz = Integer.parseInt(einsatz);
            spieler1.einsatzSetzen(eInsatz);
            spieler2.einsatzSetzen(eInsatz);
        }
        topf.setText("Topf:" + "\n" + spieltopf.einsatzAbgeben());
    }
    
    /**
     * Checkt, ob jemand gewonnen hat. Falls ja wird das ausgegeben und Punkte und Wurfanzahlen werden resettet für den nächsten Durchgang
     */
    public void hatGewonnen()
    {
        if(spieler2.punktestandAngeben() > spieler1.punktestandAngeben())
        {
            spieler2.topfLeeren();
            String siegesnachricht = spielername2 + " hat gewonnen! Mit " + spieler2.punktestandAngeben() + " Punkten und einem Vermögen von " + spieler2.getVermoegen() + ". Würfe: " + spieler2.wurfAnzahlAngeben();
            JOptionPane.showMessageDialog(sieg,
            siegesnachricht,
            "Sieg",
            JOptionPane.INFORMATION_MESSAGE);
            spieler1.resetWurfAnzahl();
            spieler2.resetWurfAnzahl();
            spieler1.punkteZuruecksetzen();
            spieler2.punkteZuruecksetzen();
            spieler2.setSiege(spieler2.getSiege() + 1);
            SpielzugSpieler1();
        }
        else if((spieler1.punktestandAngeben() >= spieler2.punktestandAngeben()) && (spieler1.wurfAnzahlAngeben() == spieler2.wurfAnzahlAngeben()))
        {
            spieler1.topfLeeren();
            String siegesnachricht = spielername1 + " hat gewonnen! Mit " + spieler1.punktestandAngeben() + " Punkten und einem Vermögen von " + spieler1.getVermoegen() + ". Würfe: " + spieler1.wurfAnzahlAngeben();
            JOptionPane.showMessageDialog(sieg,
            siegesnachricht,
            "Sieg",
            JOptionPane.INFORMATION_MESSAGE);
            spieler1.resetWurfAnzahl();
            spieler2.resetWurfAnzahl();
            spieler1.punkteZuruecksetzen();
            spieler2.punkteZuruecksetzen();
            spieler1.setSiege(spieler1.getSiege() + 1);
            SpielzugSpieler1();
        }
    }
    
    /**
     * Fügt der Konsole Text hinzu
     */
    public void addText(String newText) {
      konsole.append(newText);
    }
    
    /**
     * Löscht allen Text in der Konsole
     */
     public void deleteText() {
      konsole.selectAll();
      konsole.cut();
    }
    
    /**
     * Fügt der Konsole eine neue Zeile hinzu
     */
     public void newLine() {
      konsole.append("\n");
    }
}

