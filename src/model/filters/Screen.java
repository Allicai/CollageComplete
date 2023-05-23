package hw4.model.filters;

import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.RepresentationConverter;

/**
 * Implements a mathematical equation using the previous layer and this layer so that it changes
 * all the pixel values.
 */
public class Screen implements Filter {

  String name = "screen";


  @Override
  public void apply(Layer current, List<Layer> below) {
    Image comp = current.compressLayers(below).getImage();
    Image base = current.getImage();

    for (int i = 0; i < comp.getHeight(); i++) {
      for (int j = 0; j < comp.getWidth(); j++) {
        // pulling the rgba of each pixel in the composite image and the current layer's image
        int[] dRgba = comp.getRow(i).get(j).getRgba();
        int[] rgba = base.getRow(i).get(j).getRgba();

        // storing the current layer's rgba values as r, g, b
        double r = rgba[0];
        double g = rgba[1];
        double b = rgba[2];
        int a = rgba[3];

        // storing the composite layer's rgba values as dr, dg, db
        double cr = dRgba[0];
        double cg = dRgba[1];
        double cb = dRgba[2];

        // converting both to HSL
        double[] hSL = RepresentationConverter.convertRGBtoHSL(r, g, b);
        double[] dHSL = RepresentationConverter.convertRGBtoHSL(cr, cg, cb);

        // calculating the new Lightness value given by the filter
        double nL = (1 - (1 - hSL[2]) * (1 - dHSL[2]));

        // storing the new HSL of the pixel
        double[] nHSL = new double[]{hSL[1], hSL[2], nL};

        // converting the HSL back to RGB
        int[] nRGB = RepresentationConverter.convertHSLtoRGB(nHSL[0], nHSL[1], nHSL[2]);

        // setting the pixels rgb to the wanted values
        base.getRow(i).get(j).setRgba(new int[]{nRGB[0], nRGB[1], nRGB[2], a});
      }
    }
  }


  @Override
  public String getName() {
    return this.name;
  }
}
