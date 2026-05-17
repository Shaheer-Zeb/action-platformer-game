package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ShaheerZK
 */
public class Menu extends JPanel implements ActionListener
{
    private ImageIcon continueImage;
    private ImageIcon newGameImage;
    private ImageIcon exitImage;
    private Image menuBackground;
    
    private JButton continueBtn;
    private JButton newGameBtn;
    private JButton exitBtn;
    
    private final int imageWidth = 300, imageHeight = 100;
    
    Menu()
    {
        continueImage = new ImageIcon(getClass().getResource("/Assets/MenuButtons/ContinueButton.png"));
        newGameImage = new ImageIcon(getClass().getResource("/Assets/MenuButtons/NewGameButton.png"));
        exitImage = new ImageIcon(getClass().getResource("/Assets/MenuButtons/ExitButton.png"));
        menuBackground = new ImageIcon(getClass().getResource("/Assets/Backgrounds/MenuImage.png")).getImage();
        
        continueBtn = new JButton(new ImageIcon(continueImage.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)));
        newGameBtn = new JButton(new ImageIcon(newGameImage.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)));
        exitBtn = new JButton(new ImageIcon(exitImage.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)));
        
        continueBtn.addActionListener(this);
        continueBtn.setBorderPainted(false);
        continueBtn.setContentAreaFilled(false);
        continueBtn.setFocusPainted(false);
        continueBtn.setMargin(new Insets(0, 0, 0, 0));
        
        newGameBtn.addActionListener(this);
        newGameBtn.setBorderPainted(false);
        newGameBtn.setContentAreaFilled(false);
        newGameBtn.setFocusPainted(false);
        newGameBtn.setMargin(new Insets(0, 0, 0, 0));
        
        exitBtn.addActionListener(this);
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);
        exitBtn.setMargin(new Insets(0, 0, 0, 0));
        
        setLayout(new BorderLayout());
        
        add(continueBtn, BorderLayout.NORTH);
        add(newGameBtn, BorderLayout.CENTER);
        add(exitBtn, BorderLayout.SOUTH);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(menuBackground, 0, 0, getWidth(), getHeight(), null);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (ae.getSource() == continueBtn)
        {
            Main.frame.add(new GamePanel());
            Main.frame.remove(this);
            
            Main.frame.revalidate();
            Main.frame.repaint();
        }
        else if (ae.getSource() == newGameBtn)
        {
            Managers.LevelManager.resetLevel();
            
            Main.frame.add(new GamePanel());
            Main.frame.remove(this);
            
            Main.frame.revalidate();
            Main.frame.repaint();
        }
        else if (ae.getSource() == exitBtn)
        {
            System.exit(0);
        }
    }
}
