package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

/**
 * Brigthening value functional object which will change the value of all the pixels in the layer
 * it is called upon by aggind the value which is the average of the three pixels to the pixel.
 */
public class BrightenValue implements Filter {

  String name = "brighten-value";

  @Override
  public void apply(Layer current, List<Layer> below) {
    // getting the image of the current layer
    Image base = current.getImage();
    for (int i = 0; i < base.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < base.getWidth(); j++) { // getting a specific pixel
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] bRgba = base.getRow(i).get(j).getRgba();
          // storing the rgba for reference
          int value = base.getRow(i).get(j).getValue();
          // storing the value for reference
          base.getRow(i).get(j).setRgba(new int[]{bRgba[0] + value, bRgba[1] + value,
                  bRgba[2] + value, bRgba[3]});
          // adding the value to each rgb
        }
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

}
