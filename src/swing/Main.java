package swing;

import control.ImagePresenter;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main().execute();
    }
    private ImageDisplay imageDisplay;
    private ImagePresenter imagePresenter;
    
    public Main(){
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        List<Image> images = new FileImageLoader(new File("C:\\Users\\Usuario\\Desktop\\fotos")).load();
        imagePresenter = new ImagePresenter(images, imageDisplay);
    }

    private void execute() {
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }
    
}
