package control;

import model.Image;
import java.util.List;
import view.ImageDisplay;
import view.ImageDisplay.Shift;

public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.display(images.get(0).getName());
        this.imageDisplay.on(shift());
    }

    private Shift shift() {
        return new Shift() {
            @Override
            public String left() {
                return images.get((index()+1)%images.size()).getName();
            }

            @Override
            public String right() {
                return images.get((index()-1+images.size())%images.size()).getName();
            }

            
        };
    }
    
    private int index() {
        return find(imageDisplay.currentName());
    }

    private int find(String currentName) {
        for (int i = 0; i < images.size(); i++) {
            if(images.get(i).getName().equals(currentName)) return i;
        }
        return -1;
    }     
}
