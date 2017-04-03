import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
import java.util.Random;

public class Display 
{
    private JFrame frame;
    private JFrame gameFrame;
    private JFrame gameFrameComp;
    private ImageIconAltered[][] userImages;
    private ImageIconAltered[][] compImages;
    private ImageIconAltered[] images;
    private int correct; 
    private boolean userAnswer;
    private int counter;
    private int bs;

    public Display(int boardSize)
    {
        correct = 0;
        counter = 0;
        userImages = new ImageIconAltered[boardSize][boardSize];
        compImages = new ImageIconAltered[boardSize][boardSize];
        makeImageArray();
        bs = boardSize;
    }

    public void gameMenuDisplay() 
    {
        //Create and set up the window
        frame = new JFrame("PICTURE MATCHING");
        frame.setSize(400,400);
        frame.addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent windowEvent)
                {
                    System.exit(0);
                }        
            });

        //add title screen
        JLabel introStatement = new JLabel("<html>Welcome to our game! <br> If the picture in your box matches the corresponding one in the model, click true. Otherwise, click false.</html>", JLabel.CENTER);
        JButton playButton = new JButton("PLAY");
        playButton.setSize(new Dimension(100, 50));
        playButton.setLocation(150,250);

        //opens frames if player clicks "PLAY"
        playButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {                    

                    displayComputer();  
                    initiateGame("PLAY PICTURES MATCHING :D");

                }          
            });

        frame.add(playButton);
        frame.add(introStatement);
        frame.setVisible(true);

    }

    private void makeImageArray()
    {
        images = new ImageIconAltered[6]; 
        images[0] = new ImageIconAltered(new ImageIcon("rsz_pug.jpg"), "pug");
        images[1] = new ImageIconAltered(new ImageIcon("float.jpg"), "float2"); 
        images[2] = new ImageIconAltered(new ImageIcon("rsz_fluff.jpg"), "fluff"); 
        images[3] = new ImageIconAltered(new ImageIcon("rsz_superman.jpg"), "superman"); 
        images[4] = new ImageIconAltered(new ImageIcon("rsz_mr.jpg"), "mr"); 
        images[5] = new ImageIconAltered(new ImageIcon("rsz_golden.jpg"), "golden");
    }

    private void initiateGame(String title)
    {
        //new frame for player window
        gameFrame = new JFrame(title);
        gameFrame.setPreferredSize(new Dimension(75*bs,75*bs));
        gameFrame.setLocation(75*bs+5,0);
        
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(bs+1,bs+1));

        board.add(new JLabel(""));//initial blank space

        for (int x = 1; x<=bs; x++)
        {
            board.add(new JLabel(Integer.toString(x))); //column names
        }
        for (int s = 1; s<=bs; s++)
        {
            board.add(new JLabel(Integer.toString(s))); //row names
            for (int y = 1; y<=bs; y++)
            {        
                int num = (int) (Math.random()*6);
                JButton butt = new JButton(images[num].getImage()); //Integer.toString(s) + Integer.toString(y),
                board.add(butt);
                userImages[s-1][y-1] = images[num];
                final int a = s;
                final int b = y;

                butt.addActionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e) 
                        {                 

                            JFrame correctTest = new JFrame("Does it match the model?");
                            correctTest.setLayout(new GridLayout(2,1));
                            correctTest.setSize(100,100);
                            correctTest.setLocationRelativeTo(butt);
                            JButton t = new JButton("True");
                            correctTest.add(t);
                            JButton f = new JButton("False");
                            correctTest.add(f);

                            t.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        if (userImages[a-1][b-1].isSameAs(compImages[a-1][b-1]))
                                        {
                                            correct++;
                                        }
                                        butt.setIcon(new ImageIcon("DONE.png"));
                                        counter++;
                                        correctTest.dispatchEvent(new WindowEvent(correctTest, WindowEvent.WINDOW_CLOSING));
                                        if (counter>=bs*bs)
                                        {
                                            finish();
                                        }
                                    }
                                });
                            f.addActionListener(new ActionListener()
                                {
                                    public void actionPerformed(ActionEvent e)
                                    {

                                        if (!(userImages[a-1][b-1].isSameAs(compImages[a-1][b-1])))
                                        {
                                            correct++;
                                        }
                                        butt.setIcon(new ImageIcon("DONE.png"));
                                        counter++;
                                        correctTest.dispatchEvent(new WindowEvent(correctTest, WindowEvent.WINDOW_CLOSING));
                                        if (counter>=bs*bs)
                                        {
                                            finish();
                                        }
                                    }
                                });

                            correctTest.setVisible(true);
                        }          
                    });

            }
        }

        gameFrame.getContentPane().add(board);

        gameFrame.pack();
        gameFrame.setVisible(true);

    }

    private void displayComputer()
    {
        //new frame for computer window
        gameFrameComp = new JFrame("Model");
        gameFrameComp.setPreferredSize(new Dimension(75*bs,75*bs));

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(bs+1,bs+1));

        board.add(new JLabel(""));//initial blank space

        for (int x = 1; x<= bs; x++)
        {
            board.add(new JLabel(Integer.toString(x))); //column names
        }
        for (int s = 1; s<= bs; s++)
        {
            board.add(new JLabel(Integer.toString(s))); //row names
            for (int y = 1; y<= bs; y++)
            {
                int num = (int) (Math.random()*6);
                board.add(new JLabel(images[num].getImage()));
                compImages[s-1][y-1] = images[num];
            }
        }

        gameFrameComp.getContentPane().add(board);
        JPanel instructions = new JPanel();

        gameFrameComp.pack();
        gameFrameComp.setVisible(true);

    }

    private void finish()
    {
        JFrame finish = new JFrame();
        finish.setPreferredSize(new Dimension(500,1000));
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,1));
        board.add(new JLabel("Score is: " + correct + "/" + bs*bs,JLabel.CENTER));
        System.out.println((double)correct/(bs*bs));
        if ((double)correct/(bs*bs)==1)
        {
            board.add(new JLabel("CONGRATS!",JLabel.CENTER));
            board.add(new JLabel(new ImageIcon("shia_congrats.gif")));
        }
        else
        {
            board.add(new JLabel("Try again...",JLabel.CENTER));
            board.add(new JLabel(new ImageIcon("facepalm.gif")));
        }

        finish.getContentPane().add(board);
        finish.pack();
        finish.setVisible(true);
        finish.addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent windowEvent)
                {
                    System.exit(0);
                }        
            });
    }
}

