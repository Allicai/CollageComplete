package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

/**
 * A functional object whose goal is to change the pixels in the layer to the intensity by
 * subtracting the intensity from the rgba value.
 */
public class DarkenIntensity implements Filter {

  String name = "darken-intensity";

  @Override
  public void apply(Layer current, List<Layer> below) {
    // pulling the image of the current layer
    Image base = current.getImage();
    for (int i = 0; i < base.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < base.getWidth(); j++) { // getting a specific pixel
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] rgba = base.getRow(i).get(j).getRgba();
          // storing the rgba for reference
          int intensity = base.getRow(i).get(j).getIntensity();
          // storing the intensity value for reference
          base.getRow(i).get(j).setRgba(new int[]{rgba[0] - intensity, rgba[1] - intensity,
                  rgba[2] - intensity, rgba[3]});
          // subtracting the intensity from each rgb value.
        }
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }
}
