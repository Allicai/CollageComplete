package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;

import static java.lang.Math.abs;

/**
 * Takes the rgb of the composite image and gets the total value after subtracting it forom the
 * original image.
 */
public class Difference implements Filter {

  String name = "difference";

  @Override
  public void apply(Layer current, List<Layer> below) {

    // compressing the layers below the current layer and getting the composite image
    Image comp = current.compressLayers(below).getImage();
    // getting the image of the current layer
    Image base = current.getImage();

    for (int i = 0; i < comp.getHeight(); i++) { // getting a row of pixels
      for (int j = 0; j < comp.getWidth(); j++) { // getting a specific pixel
        if (!base.getRow(i).get(j).isTransparent()) { // transparency check
          // storing the base images rgba
          System.out.println(base.getRow(0).get(0).getRgba()[0]);
          System.out.println(base.getRow(0).get(1).getRgba()[0]);
          System.out.println(base.getRow(1).get(1).getRgba()[0]);
          int[] rgba = base.getRow(i).get(j).getRgba();
          // storing the composite images rgba
          int[] dRgba = comp.getRow(i).get(j).getRgba();

          // storing the absolute values of the differences in rgb values
          int r = abs(dRgba[0] - rgba[0]);
          int g = abs(dRgba[1] - rgba[1]);
          int b = abs(dRgba[2] - rgba[2]);
          int a = rgba[3];
          int[] pRgba = {r, g, b, a};


          // setting the pixels rgba to the new rgba
          base.getRow(i).get(j).setRgba(pRgba);
        }
      }
    }

  }

  @Override
  public String getName() {
    return this.name;
  }
}
