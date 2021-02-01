package swing;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay{

    private BufferedImage image;
    private BufferedImage futureImage;
    private int offset = 0;
    private Shift shift = new Shift.Null();
    private String current;
    private String future;

    public ImagePanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, offset, 0, null);
        if(offset == 0) return;
        if(futureImage == null) return;
        g.drawImage(futureImage, offset > 0 ? -(futureImage.getWidth()-offset) : offset+image.getWidth(), 0, null);
    }

    @Override
    public void display(String name) {
        this.current = name;
        this.image = load(name);
    }
    
    private void setFuture(String name) {
        if(name.equals(future)) return;
        this.future = name;
        this.futureImage = load(name);
    }

    private static BufferedImage load(String name) {
        try {
           return ImageIO.read(new File(name));
        } catch (IOException ex) {
           return null;
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    @Override
    public String currentName() {
        return this.current;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{
        private int initial;
        
        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            if(Math.abs(offset) > getWidth() / 2) {
                current = future;
                image = futureImage;
            }
            offset = 0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            offset = event.getX() - initial;
            if(offset < 0) setFuture(shift.right());
            if(offset > 0) setFuture(shift.left());
            futureImage = load(future);
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
    }
    
}
