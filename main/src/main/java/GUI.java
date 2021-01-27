import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;


public class GUI {



    JFrame frame;
    JPanel panel;
    JTextField nameField;
    JTextField authorField;
    JTextField artistField;
    JSlider ratingSlider;
    JComboBox<String> languageBox;
    JComboBox<String> demographicBox;
    JComboBox<String> pubStatusBox;
    JTextField tagField;
    JButton mangaButton;

    Query query;

    public GUI() {
        query = new Query();
        initialize();
    }

    @SuppressWarnings("MagicConstant")
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Search Manga");
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setBounds(200,0,500,700);
        frame.setResizable(true);

        panel = new JPanel(new GridLayout(8,2,5,5));
        //frame.setLayout(new GridLayout(10,2));
        panel.setBounds(0,0,750,800);
        panel.setBackground(Color.black);
        frame.add(panel);

        JLabel[] labels = {
                new JLabel(" Manga name:"),
                new JLabel(" Author:"),
                new JLabel(" Artist:"),
                new JLabel(" Rating (Minimum):"),
                new JLabel(" Original language:"),
                new JLabel(" Demographic:"),
                new JLabel(" Publication status:"),
                new JLabel(" Tags:"),
        };
        for (JLabel label : labels) {
            label.setOpaque(true);
            label.setFont(new Font("Verdana", 1, 30));
            label.setForeground(Color.WHITE);
            label.setBackground(Color.darkGray);
        }
        panel.add(labels[0]);
        panel.add(nameField = new JTextField(""));
        nameField.setFont(new Font("Verdana",0,30));
        nameField.setForeground(Color.WHITE);
        nameField.setBackground(Color.DARK_GRAY);

        panel.add(labels[1]);
        panel.add(authorField = new JTextField(""));
        authorField.setFont(new Font("Verdana",0,30));
        authorField.setForeground(Color.WHITE);
        authorField.setBackground(Color.DARK_GRAY);

        panel.add(labels[2]);
        panel.add(artistField = new JTextField(""));
        artistField.setFont(new Font("Verdana",0,30));
        artistField.setForeground(Color.WHITE);
        artistField.setBackground(Color.DARK_GRAY);

        panel.add(labels[3]);
        panel.add(ratingSlider = new JSlider(0,10,0));
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintTrack(false);
        ratingSlider.setMajorTickSpacing(5);
        ratingSlider.setMinorTickSpacing(1);
        ratingSlider.setFont(new Font("Verdana",0,30));
        ratingSlider.setForeground(Color.WHITE);
        ratingSlider.setBackground(Color.DARK_GRAY);

        panel.add(labels[4]);
        String[] languages = {
                "","Japanese","English","Polish","Russian","German",
                "French","Vietnamese","Chinese","Indonesian","Korean",
                "Spanish","Thai","Filipino","Chinese (Traditional)"};
        panel.add(languageBox = new JComboBox<>(languages));
        languageBox.setFont(new Font("Verdana",0,30));
        languageBox.setForeground(Color.WHITE);
        languageBox.setBackground(Color.DARK_GRAY);

        panel.add(labels[5]);
        String[] demographics = {"","Not Specified","Shounen","Shoujo","Seinen","Josei"};
        panel.add(demographicBox = new JComboBox<>(demographics));
        demographicBox.setFont(new Font("Verdana",0,30));
        demographicBox.setForeground(Color.WHITE);
        demographicBox.setBackground(Color.DARK_GRAY);

        panel.add(labels[6]);
        String[] pubStatus = {"","Not Specified","Ongoing","Completed","Cancelled","Hiatus"};
        panel.add(pubStatusBox = new JComboBox<>(pubStatus));
        pubStatusBox.setFont(new Font("Verdana",0,30));
        pubStatusBox.setForeground(Color.WHITE);
        pubStatusBox.setBackground(Color.DARK_GRAY);

        panel.add(labels[7]);
        panel.add(tagField = new JTextField(""));
        tagField.setFont(new Font("Verdana",0,30));
        tagField.setForeground(Color.WHITE);
        tagField.setBackground(Color.DARK_GRAY);


        frame.add(mangaButton = new JButton("Search"));
        mangaButton.setBounds(5,805,738,150);
        mangaButton.setFont(new Font("Verdana",1,50));
        mangaButton.setForeground(Color.WHITE);
        mangaButton.setBackground(Color.DARK_GRAY);
        mangaButton.addActionListener(e -> {
            if (e.getSource().equals(mangaButton)) {
                query.newQuery(
                        nameField.getText(),authorField.getText(),artistField.getText(), ratingSlider.getValue(),
                        (String)languageBox.getSelectedItem(), (String)demographicBox.getSelectedItem(),
                        (String)pubStatusBox.getSelectedItem(), tagField.getText());
            }

        });

        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.setBounds(0,0,frame.getWidth()-16,(int)(frame.getHeight()*0.8));
                mangaButton.setBounds(5,(int)(frame.getHeight()*0.8)+5,frame.getWidth()-27,(int)(frame.getHeight()*0.2)-50);
                for (JLabel label : labels) {
                    label.setFont(new Font("Verdana",1,(int)((label.getWidth()+label.getHeight())*0.05)));
                }
                nameField.setFont		(new Font("Verdana",0,(int)((nameField.getWidth()		+nameField.getHeight())*0.05)));
                authorField.setFont		(new Font("Verdana",0,(int)((authorField.getWidth()		+authorField.getHeight())*0.05)));
                artistField.setFont		(new Font("Verdana",0,(int)((artistField.getWidth()		+artistField.getHeight())*0.05)));
                ratingSlider.setFont	(new Font("Verdana",0,(int)((ratingSlider.getWidth()	+ratingSlider.getHeight())*0.05)));
                languageBox.setFont		(new Font("Verdana",0,(int)((languageBox.getWidth()		+languageBox.getHeight())*0.05)));
                demographicBox.setFont	(new Font("Verdana",0,(int)((demographicBox.getWidth()	+demographicBox.getHeight())*0.05)));
                pubStatusBox.setFont	(new Font("Verdana",0,(int)((pubStatusBox.getWidth()	+pubStatusBox.getHeight())*0.05)));
                tagField.setFont		(new Font("Verdana",0,(int)((tagField.getWidth()		+tagField.getHeight())*0.05)));
                mangaButton.setFont		(new Font("Verdana",1,(int)((mangaButton.getWidth()		+mangaButton.getHeight())*0.06)));
            }
            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        frame.setVisible(true);




    }

}

