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
 * @version 0.5/20.3.22
 */
public class GUI extends JFrame implements ActionListener{
    private JTextArea konsole, spieler, vermoegen, topf, wuerfelausgabe;
    private JButton neuesSpiel, abbruch, wuerfeln;
    private Spieler spieler1, spieler2;
    private JOptionPane frame, frame2;
    private String spielername1, spielername2;
    
    /**
     * Main
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
      
      neuesSpiel = new JButton("Neues Spiel");
      neuesSpiel.addActionListener(this);
      
      abbruch = new JButton("Beenden");
      abbruch.addActionListener(this);
      
      wuerfeln = new JButton();
      try {
            Image img = ImageIO.read(new File("Landshark.jpg"));
            wuerfeln.setIcon(new ImageIcon(img));
      } catch(Exception e) {
            wuerfeln.setText("Wuerfeln");
      }
      abbruch.addActionListener(this);
      
      vermoegen = new JTextArea(1,20);
      vermoegen.setText("Vermoegen: \n  100");
      
      topf = new JTextArea(1,20);
      topf.setText("Topf: \n 0");
      
      wuerfelausgabe = new JTextArea(1,20);
      wuerfelausgabe.setText("Wuerfelausgabe: \n ");
      
      
      //add everything to the panels
      topPanel.add(spieler);
      spieler.setAlignmentY( Component.CENTER_ALIGNMENT );
      
      topPanel.add(neuesSpiel);
      neuesSpiel.setAlignmentY( Component.CENTER_ALIGNMENT );
      
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
      
    public void starteSpiel()
    {
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
        //setLabel("Come on, finish the sentence!");
        
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
        //setLabel("Come on, finish the sentence!");
    }
    
      public void actionPerformed(ActionEvent e){
           
        if(e.getSource() == this.neuesSpiel){
            //alles weg, neustart
        }
        else if (e.getSource() == this.abbruch){
            System.exit(0);
        }
            
    }
    
    public void addText(String newText) {
      konsole.append(newText);
    }
    
     public void deleteText() {
      konsole.selectAll();
      konsole.cut();
    }
    
     public void newLine() {
      konsole.append("\n");
    }
}

