package hw4.model.Pixels;

import java.awt.Color;
import java.util.Arrays;
/**
 * A hw4.model.Pixel class to represent a pixel in an image by maintaining a location, color
 * and brightness.
 */
public class Pixel implements IPixel {
  private int[] rgba;
  private int value;
  private int intensity;
  private int luma;


  /**
   * Basic constructor for the hw4.model.Pixel class.
   *
   * @param rgba the colors, red, green, blue and alpha value in order to generate a color.
   */
  public Pixel(int[] rgba) {
    for (int i = 0; i < rgba.length; i++) {
      if (rgba[i] > 255 || rgba[i] < 0) {
        throw new IllegalArgumentException("One of your rgba values is out of range");
      }
    }
    this.rgba = new int[4];
    for (int i = 0; i < rgba.length; i++) {
      this.rgba[i] = rgba[i];
    }
    this.setVil(rgba);
  }

  /**
   * A constructor that allows for the determining of the max value, is 255 is not.
   */
  public Pixel(int[] rgba, int max) {
    for (int i = 0; i < rgba.length; i++) {
      if (rgba[i] > 255) {
        throw new IllegalArgumentException("One of your rgba values is out of range");
      }
    }
    this.rgba = new int[4];
    for (int i = 0; i < rgba.length; i++) {
      this.rgba[i] = rgba[i];
    }
    this.setVil(rgba);
  }

  /**
   * Set the VIL of the given pixel post constructor.
   */
  public void setVil() {
    this.setVil(this.rgba);
  }


  /**
   * Set the value luma and intensity using the colors.
   *
   * @param rgba the colors given.
   */
  public void setVil(int[] rgba) {
    int[] average = new int[3];
    for (int i = 0; i < 3; i++) {
      average[i] = rgba[i];
    }
    value = Arrays.stream(average).max().getAsInt();
    intensity = (int) Math.ceil(Arrays.stream(Arrays.copyOf(rgba, 3)).average().orElse(Double.NaN));
    luma = (int) Math.ceil((.2126 * rgba[0]) + .7152 * rgba[1] + .0722 * rgba[2]);
  }

  /**
   * clamps the value from 0 to 255.
   *
   * @param value the given r g b or a value
   * @return a new int that has been clamped between 0 and 255
   */
  private int clamp(int value) {
    return Math.max(0, Math.min(255, value));
  }

  /**
   * Set the RGBA values for this pixel.
   */
  public void setRgba(int[] rgba) {
    this.rgba[0] = clamp(rgba[0]);
    this.rgba[1] = clamp(rgba[1]);
    this.rgba[2] = clamp(rgba[2]);
    this.rgba[3] = clamp(rgba[3]);
  }


  /**
   * Get the luma.
   *
   * @return an int to represent the luma.
   */
  public int getLumosity() {
    return luma;
  }

  /**
   * Gets the value of the pixels.
   *
   * @return the value of the pixels.
   */
  public int getValue() {
    return value;
  }

  /**
   * Gets the intensity of the pixel.
   *
   * @return an int to represent the intensity.
   */
  public int getIntensity() {
    return intensity;
  }

  /**
   * Return true if the two pixels have the same rgba values.
   *
   * @param other a given hw4.model.Pixel to be compared.
   * @return a boolean true if the two pixels rgba are the same and a boolean false if they are not.
   */
  public boolean compareRgba(Pixel other) {
    Boolean flag = true;
    if (other.rgba.length == this.rgba.length) {
      for (int i = 0; i < this.rgba.length; i++) {
        if (this.rgba[i] != other.rgba[i]) {
          flag = false;
        }  // Do nothing because this is what we want

      }
    } else {
      return false;
    }
    return flag;
  }

  /**
   * gets the rgba at the given index.
   *
   * @param index the index value to get the rgba at/
   * @return an int which is the rgba at the given index.
   */
  public int getRgba(int index) {
    return this.rgba[index];
  }


  /**
   * Give the list of rgba values from the fields of this rgba values.
   *
   * @return an Array where the first index value is red value, second green, and so on for a size.
   */
  public int[] getRgba() {
    int[] temp = new int[4];
    for (int i = 0; i < this.rgba.length; i++) {
      temp[i] = rgba[i];
    }
    return temp;
  }

  /**
   * Compares the numbers in regard to the brightness and intensity.
   *
   * @param other the other hw4.model.Pixel object being compared.
   * @return a boolean to indicate if the VIL are the same for both.
   */
  public boolean compareVIL(Pixel other) {
    return this.intensity == other.intensity && this.value == other.value
            && this.luma == other.luma;
  }


  /**
   * method that compares all the fields of a given pixel to the current pixel.
   *
   * @param other the pixel being given
   * @return a boolean value, true if the pixels have the same values, otherwise false.
   */
  public boolean comparePixels(Pixel other) {
    return this.compareVIL(other) && this.compareRgba(other);
  }

  /**
   * method that checks if a pixel is transparent to see if a filter should be applied to it.
   *
   * @return true if the pixels alpha value is 0.
   */
  public boolean isTransparent() {
    return this.getRgba()[3] == 0;
  }


  /**
   * takes a pixel and turns it into a string.
   *
   * @return a string representation of a pixel
   */

  public String pixelToString() {
    String base = "";
    int[] rgba = this.getRgba();
    for (int i = 0; i < rgba.length - 1; i++) {
      base = base + rgba[i];
      if (i != rgba.length - 1) {
        base = base + " ";
      }

    }
    return base;
  }

  /**
   * Converts a pixel to a string for view. but includes the alpha value.
   *
   * @return a String that is the pixel in the format "r g b a".
   */
  public String pixelToStringA() {
    String base = "";
    int[] rgba = this.getRgba();
    for (int i = 0; i < rgba.length; i++) {
      base = base + rgba[i];
      if (i != rgba.length - 1) {
        base = base + " ";
      }

    }
    return base;
  }

  /**
   * Changes the pixel value to the alpha calculated value using the background given.
   *
   * @param ba the background of the pixel that is being worked on.
   * @return a new pixel which has been "flattened"
   */
  public Pixel alphaTize(Pixel ba) {
    int[] newRgba = this.alphaTizePixel(ba.getRgba());
    return new Pixel(newRgba);
  }

  /**
   * Uses the mathematical formula to adjust a pixel values based on the background.
   *
   * @param ba a list of ints that is the background pixels in order rgba.
   * @return a new list of ints that are adjusted pixels.
   */
  public int[] alphaTizePixel(int[] ba) {
    int a = this.getRgba(3);
    int r = this.getRgba(0);
    int b = this.getRgba(2);
    int g = this.getRgba(1);
    int da = ba[3];
    int dr = ba[0];
    int dg = ba[1];
    int db = ba[2];
    int a2prime = (a / 255 + da / 255 * (1 - (a / 255)));
    int aPrime = a2prime * 255;
    int rPrime = (a / 255 * r + dr * (da / 255) * (1 - a / 255)) * (1 / a2prime);
    int gPrime = (a / 255 * g + dg * (da / 255) * (1 - a / 255)) * (1 / a2prime);
    int bPrime = (a / 255 * b + db * (da / 255) * (1 - a / 255)) * (1 / a2prime);
    return new int[]{rPrime, gPrime, bPrime, aPrime};

  }

  /**
   * creates an int rgb from the color of the pixel, ignoring alpha value.
   *
   * @return an int for the rgb value.
   */
  public int returnRGB() {
    int r = this.getRgba(0);
    int g = this.getRgba(1);
    int b = this.getRgba(2);

    Color color = new Color(r, g, b);
    return color.getRGB();
  }

}
