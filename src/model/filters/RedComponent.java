package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

/**
 * A class for a red component filter which changes the component of the blue and green to zero
 * and keeps the red.
 */
public class RedComponent implements Filter {

  String name = "red-component";


  @Override
  public void apply(Layer current, List<Layer> below) {
    // getting the image of the current layer
    Image base = current.getImage();
    for (int i = 0; i < base.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < base.getWidth(); j++) { // getting a pixel within the row
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          int[] bRgba = base.getRow(i).get(j).getRgba();
          // storing the rgba of the pixel for reference
          base.getRow(i).get(j).setRgba(new int[]{bRgba[0], 0, 0, bRgba[3]});
          // setting the rgba to only have the red component of the rgb and the same alpha value
        }
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }
}
