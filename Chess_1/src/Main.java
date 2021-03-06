import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    //Variable Declaration
    private static final int Height = 500;
    private static final int Width = 1110;

    private JPanel board = new JPanel(new GridLayout(8, 6));
    private JPanel wdetails = new JPanel(new GridLayout(3, 1));
    private JPanel bdetails = new JPanel(new GridLayout(3, 1));
    private JPanel wcombopanel = new JPanel();
    private JPanel bcombopanel = new JPanel();

    private JPanel controlPanel, WhitePlayer, BlackPlayer, temp, showPlayer, time;
    private JSplitPane split;
    public static Main Mainboard;
    private Container content;
    private JComboBox<String> wcombo, bcombo;
    private String wname = null, bname = null;
    private BufferedImage image;
    private Button start;
    private String whiteName="";
    private String blackName="";    
    public static void main(String[] args) {

        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    //Constructor
    private Main() {
        wdetails = new JPanel(new GridLayout(1, 1));
        bdetails = new JPanel(new GridLayout(1, 1));
        bcombopanel = new JPanel();
        wcombopanel = new JPanel();

        ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
        this.setIconImage(img.getImage());

        board.setBorder(BorderFactory.createLoweredBevelBorder());
        content = getContentPane();
        setSize(Width, Height);
        setTitle("Chess");

        controlPanel = new JPanel();

        content.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(3, 1));
        controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Defining the Player Box in Control Panel
        WhitePlayer = new JPanel();
        WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.RED));
        WhitePlayer.setLayout(new BorderLayout());

        BlackPlayer = new JPanel();
        BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("times new roman", Font.BOLD, 18), Color.BLUE));
        BlackPlayer.setLayout(new BorderLayout());

        JPanel whitestats = new JPanel(new GridLayout(3, 3));
        JPanel blackstats = new JPanel(new GridLayout(3, 3));

        wcombopanel.setLayout(new FlowLayout());
        bcombopanel.setLayout(new FlowLayout());
        
        JButton white_name = new JButton("Enter White Player name");
        white_name.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
        white_name.setHorizontalAlignment(JTextField.CENTER);  // Text alignment
        white_name.addActionListener(new START());
        white_name.setBackground(Color.red);
        white_name.setForeground(Color.white);
        white_name.setPreferredSize(new Dimension(230, 40));

        JButton black_name = new JButton("Enter Black Player name");
        black_name.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 13));
        black_name.setHorizontalAlignment(JTextField.CENTER);  // Text alignment
        black_name.setBackground(Color.blue);
        black_name.setForeground(Color.white);
        black_name.addActionListener(new START());
        black_name.setPreferredSize(new Dimension(230, 40));
        wcombopanel.add(white_name);

        bcombopanel.add(black_name);

        WhitePlayer.add(wcombopanel, BorderLayout.NORTH);
        BlackPlayer.add(bcombopanel, BorderLayout.NORTH);
        whitestats.add(new JLabel("Name   :"));
        blackstats.add(new JLabel("Name   :"));

        WhitePlayer.add(whitestats, BorderLayout.WEST);
        BlackPlayer.add(blackstats, BorderLayout.WEST);
        controlPanel.add(WhitePlayer);
        controlPanel.add(BlackPlayer);

        //Defining all the Cells
        showPlayer = new JPanel(new FlowLayout());
        start = new Button("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.setPreferredSize(new Dimension(120, 40));
        start.addActionListener(new START());
        time = new JPanel(new GridLayout(3, 1));
        time.add(showPlayer);
        time.add(start);
        controlPanel.add(time);

        //The Left Layout When Game is inactive
        temp = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                try {
                    image = ImageIO.read(this.getClass().getResource("clash.jpg"));
                } catch (IOException ex) {
                    System.out.println("not found");
                }
                g.drawImage(image, 0, 0, null);
            }
        };
        temp.setMinimumSize(new Dimension(800, 700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);
        content.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class START implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {

            if (arg0.getActionCommand().equalsIgnoreCase("start")) 
            {
                if(whiteName!="" && blackName!="")
                {
                    split.remove(temp);
                    ChessBoard test = null;
                    try {
                        test = new ChessBoard();
                    } catch (NullPointerException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    test.setResizable(false);
                    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    test.setVisible(true);
                    test.setLocationRelativeTo(null);
                    test.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }
            } 
            else if (arg0.getActionCommand().equalsIgnoreCase("Enter White Player name")) 
            {
                JPanel temp = new JPanel();
                JPanel joint = WhitePlayer;
                String name = JOptionPane.showInputDialog("Enter White Player's name");
                if(name.length()!=0)
                {
                    whiteName=name;
                    temp.removeAll();
                    temp.add(new JLabel(" " + name));
                    joint.revalidate();
                    joint.repaint();
                    joint.add(temp);                    
                }            
            }
            else if (arg0.getActionCommand().equalsIgnoreCase("Enter Black Player name")) 
            {
                JPanel temp = new JPanel();
                JPanel joint = BlackPlayer;
                String name = JOptionPane.showInputDialog("Enter Black Player's name");
                if(name.length()!=0)
                {
                    blackName=name;
                    temp.removeAll();
                    temp.add(new JLabel(" " + name));
                    joint.revalidate();
                    joint.repaint();
                    joint.add(temp);                    
                }

            }
        }
    }
}
