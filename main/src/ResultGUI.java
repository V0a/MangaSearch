
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class ResultGUI {

    JFrame frame;
    JTextArea textArea;
    JLabel imageLabel;
    ImageIcon image;

    ResultSet data;

    public ResultGUI(ResultSet data){
        this.data=data;
        try {
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws SQLException {

        frame = new JFrame();
        frame.setTitle("Search Manga");
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setBounds(
                (int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.1),(int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.05),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.8),(int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.9));
        frame.setResizable(false);
        frame.add(textArea=new JTextArea());
        textArea.setBounds((int)(frame.getWidth()*0.4)+5,0,(int)(frame.getWidth()*0.6)-5, frame.getHeight());
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        //noinspection MagicConstant
        textArea.setFont(new Font(Font.MONOSPACED,0,18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        String[] descriptors = {
                "MangaID:            ","Name:               ","Rating:             ","Views:              ",
                "Follows:            ","Total Chapters:     ","Link:               ","Image Link:         ",
                "Description:        ","Language:           ","Demographic:        ","Publication Status: ",
                "Alternative Names:  ","Genres:             ","Themes:             ","Format:             ",
                "Content:            ","Authors:            ","Artists:            "};
        if (data.next()) {
            try {
                image=new ImageIcon(new URL(data.getString(8)));
                image.setImage(image.getImage().getScaledInstance((int)(frame.getWidth()*0.4), frame.getHeight(),java.awt.Image.SCALE_SMOOTH));
                frame.add(imageLabel=new JLabel(image));
                imageLabel.setBounds(0,0,(int)(frame.getWidth()*0.4), frame.getHeight());
                imageLabel.setVisible(true);
            } catch (MalformedURLException | SQLException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < data.getMetaData().getColumnCount(); i++) {
                if(i!=7) {
                    try {
                        if (data.getString(i+1).length()>500) {
                            textArea.append(descriptors[i]+"\r\n"+data.getString(i+1).substring(0,500)+"...\r\n");
                        } else {
                            textArea.append(descriptors[i]+data.getString(i+1)+"\r\n");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        frame.setVisible(true);
    }
}