package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

/**
 * A Functional object that subtracts the rgba from luma in the given layer when called upon.
 */
public class DarkenLuma implements Filter {

  String name = "darken-luma";

  @Override
  public void apply(Layer current, List<Layer> below) {
    // getting the image of the current layer
    Image base = current.getImage();
    for (int i = 0; i < base.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < base.getWidth(); j++) { // getting a specific pixel from the row
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] rgba = base.getRow(i).get(j).getRgba();
          // storing the rgba for reference
          int luma = base.getRow(i).get(j).getLumosity();
          // storing the luma value for reference
          base.getRow(i).get(j).setRgba(new int[]{rgba[0] - luma, rgba[1] - luma,
                  rgba[2] - luma, rgba[3]});
          // darkening each rgb value by the luma
        }
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }
}

