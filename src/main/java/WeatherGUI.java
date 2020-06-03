import org.jsoup.nodes.Element;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherGUI extends JFrame {

    Font font = new Font("",Font.BOLD + Font.ITALIC,20);
    Border border = new EmptyBorder(30,75,25,75);

    Parser parser = new Parser();

    JPanel mainPanel;
    JPanel panel;

    private void CreateGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000,875);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        mainPanel = new Panel(new FlowLayout(FlowLayout.LEFT));

        for(int i=0; i<7; i++){
            CreateBox();
        }

        setContentPane(mainPanel);
    }

    private void CreateBox() {
        panel = new JPanel();

        Element e = parser.getDates().first();
        JLabel label = new JLabel(e.text());
        label.setFont(font); label.setBorder(border);
        panel.add(label);
        parser.dates.remove(e);

        e = parser.getTempMax().first();
        label = new JLabel(e.text());
        label.setFont(font); label.setBorder(border);
        panel.add(label);
        parser.tempMax.remove(e);

        e = parser.getTempMin().first();
        label = new JLabel(e.text());
        label.setFont(font); label.setBorder(border);
        panel.add(label);
        parser.tempMin.remove(e);

        e = parser.getSky().first();
        label = new JLabel();
        if(e.text().equals("Переменная облачность")){
            label.setIcon(new ImageIcon("images/CloudSunIcon.png"));
        }
        else if(e.text().equals("Слабый дождь") || e.text().equals("Сильный дождь")){
            label.setIcon(new ImageIcon("images/RainIcon.png"));
        }
        else if(e.text().equals("Солнечно")){
            label.setIcon(new ImageIcon("images/SunIcon.png"));
        }
        else if(e.text().equals("Пасмурно")){
            label.setIcon(new ImageIcon("images/CloudsIcon.png"));
        }
        else {
            label.setText(e.text());
            label.setFont(font);
        }
        label.setBorder(border);
        panel.add(label);
        parser.sky.remove(e);

        panel.setOpaque(false);
        mainPanel.add(panel);
    }

    WeatherGUI() throws IOException {
        CreateGUI();
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new WeatherGUI();
        frame.setVisible(true);
    }
}

class Panel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(".\\images\\weather.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image,0,0,this);
    }

    public Panel(LayoutManager layout) {
        super(layout);
    }
}