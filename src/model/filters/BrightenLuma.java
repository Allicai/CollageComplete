package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;


/**
 * a filter class which brightnes by the luma of the pixels. Each pixel has a luma which will
 * change according to this filter by adding its luma value to the pixel and returning a new pixel.
 */
public class BrightenLuma implements Filter {

  String name = "brighten-luma";

  @Override
  public void apply(Layer current, List<Layer> below) {
    // getting the image of the current layer
    Image base = current.getImage();
    for (int i = 0; i < base.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < base.getWidth(); j++) { // getting a specific pixel
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] rgba = base.getRow(i).get(j).getRgba();
          // storing the rgba for reference
          int luma = base.getRow(i).get(j).getLumosity();
          // storing the luma value for reference
          base.getRow(i).get(j).setRgba(new int[]{rgba[0] + luma, rgba[1] + luma,
                  rgba[2] + luma, rgba[3]});
          // adding the luma to each rgb value
        }
      }
    }
  }


  @Override
  public String getName() {
    return this.name;
  }
}
