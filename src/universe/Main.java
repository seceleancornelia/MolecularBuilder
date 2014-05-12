package universe;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main extends JFrame implements ActionListener{
    public Main(String title)
    {
        super(title);
        createFrame();
    }
    JButton button=new JButton("Add sphere");
    JButton info=new JButton("Show information");
    JLabel display_data = new JLabel("Ana are mere");

    /**
     * The canvas for the universe
     */
    Canvas3D canvas=new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    EnvironmentConstructor environment=new EnvironmentConstructor(canvas);
    public void createFrame()
    {
        /**
         * The panel containing the universe
         */
        JPanel panel=new JPanel();
        /**
         * Sets a BorderLayout to the panel
         */
        panel.setLayout(new BorderLayout());
         //sets up the button's listener. for testing purposes.
        setButton();
        panel.add(button);
        panel.add(info);
        info.setLocation(100, 0);
        display_data.setOpaque(true);
        display_data.setForeground(Color.RED);
        display_data.setBackground(Color.RED);
        panel.add(display_data);
        /**
         * Adds the canvas to the panel
         */
        panel.add(canvas);
        /**
         * Sets both the frame and the canvas' dimensions to screen size
         */
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        canvas.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        /**
         * Adds the panel to the contentPane of the frame
         */
        getContentPane().add(panel);
        /**
         * Other normal configuration of everything
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        /**
         * Packs the whole environment and ships it to the visual area.
         */
        setJMenuBar(createMenu());
        pack();
    }
    public JMenuBar createMenu()
    {
        JMenuBar menubar = new JMenuBar();
        JMenu menu=new JMenu("File");
        JMenuItem menuitem= new JMenuItem();
        menubar.add(menu);
        return menubar;
    }
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == this.button) {
            environment.addnewElement("Hydrogen");
        }
        if (src == this.info){
            display_data.setLocation(500, 10);
        }
    }
    public void setButton()
    {
        button.addActionListener(this);
        info.addActionListener(this);
        button.setLayout(null);
        info.setLayout(null);
        button.setSize(100, 20);
        info.setSize(100, 20);
    }
    public static void main(String s[])
    {
        Main main=new Main("Molecular Builder");
//        MolecularFile file=new MolecularFile("/home/mihai/mihai.json");
//        HashMap<String,Object> hash=file.fileToHashMap();
//        System.out.println(hash.get("data"));
    }
}
