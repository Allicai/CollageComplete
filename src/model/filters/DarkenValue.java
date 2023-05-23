package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

/**
 * Functional object which subtracts the rgba from value so that you get a slightly darker image.
 */
public class DarkenValue implements Filter {
  String name = "darken-value";

  @Override
  public void apply(Layer current, List<Layer> below) {
    // getting the image of the current layer
    Image base = current.getRealImage();
    for (int i = 0; i < current.getRealImage().getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < current.getRealImage().getWidth(); j++) { // getting a specific pixel
        // from the row
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] rgba = current.getRealImage().getRow(i).get(j).getRgba();
          // storing the rgba for reference
          int value = current.getRealImage().getRow(i).get(j).getValue();
          System.out.println("Value" + value);
          System.out.println("Red" + rgba[0]);
          System.out.println("Green" + rgba[1]);
          System.out.println("Blue" + rgba[2]);
          // storing the luma value for reference
          current.getRealImage().getRow(i).get(j).setRgba(new int[]{rgba[0]
                  - value, rgba[1] - value, rgba[2] - value, rgba[3]});
          System.out.println("changed red "
                  + current.getRealImage().getRow(i).get(j).getRgba(0));
          // darkening each rgb value by the value
        }
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }
}
