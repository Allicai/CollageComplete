package hw4.model.filters;

import java.util.List;

import hw4.model.Layers.Layer;
import hw4.model.RepresentationConverter;

/**
 * Multiplies the lightness of the composite image by the original image after coverting to HSL
 * and then converts back to RGB.
 */
public class Multiply implements Filter {

  String name = "multiply";

  @Override
  public void apply(Layer current, List<Layer> below) { // the idea I'm talking about


    // compress all the layers below into a single layer
    Layer comp = current.compressLayers(below);

    for (int i = 0; i < comp.getLayerHeight(); i++) {
      for (int j = 0; j < comp.getLayerWidth(); j++) {
        // pulling the rGba of each pixel in the composite image and the current layer's image
        int[] dRgba = comp.getImage().getRow(i).get(j).getRgba();
        int[] rGba = current.getImage().getRow(i).get(j).getRgba();

        // storing the current layer's rGba values as r, g, b
        int r = rGba[0];
        int g = rGba[1];
        int b = rGba[2];
        int a = rGba[3];

        // storing the composite layer's rGba values as cr, cg, cb
        int cr = dRgba[0];
        int cg = dRgba[1];
        int cb = dRgba[2];

        // converting both to hSL
        double[] hSL = RepresentationConverter.convertRGBtoHSL((double) r / 255,
                (double) g / 255, (double) b / 255);
        double[] dHSL = RepresentationConverter.convertRGBtoHSL((double) cr / 255,
                (double) cg / 255, (double) cb / 255);

        // defining the hSL the multiply filter is meant to give
        double[] nHSL = new double[]{hSL[0], hSL[1], hSL[2] * dHSL[2]};

        // storing the values of the hSL after the filter effect
        double nr = nHSL[0];
        double ng = nHSL[1];
        double nb = nHSL[2];

        // converting hSL to RGB after changes
        int[] nRgba = RepresentationConverter.convertHSLtoRGB(nr, ng, nb);

        int newR = nRgba[0];
        int newG = nRgba[1];
        int newB = nRgba[2];

        // setting new pixels rGba equal to the rgb after the filter effect
        current.getImage().getRow(i).get(j).setRgba(new int[]{newR, newG, newB, a});

      }
    }
  }

  @Override
  public String getName() {
    return name;
  }
}
