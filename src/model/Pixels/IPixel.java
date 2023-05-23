package hw4.model.Pixels;

public interface IPixel {

  /**
   * Set the VIL of the given pixel post constructor.
   */
  public void setVil();


  /**
   * Set the value luma and intensity using the colors.
   *
   * @param rgba the colors given.
   */
  void setVil(int[] rgba);

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
  void setRgba(int[] rgba);

  /**
   * Get the luma.
   *
   * @return an int to represent the luma.
   */
  int getLumosity();

  /**
   * Gets the value of the pixels.
   *
   * @return the value of the pixels.
   */
  int getValue();

  /**
   * Gets the intensity of the pixel.
   *
   * @return an int to represent the intensity.
   */
  int getIntensity();

  /**
   * gets the rgba at the given index.
   *
   * @param index the index value to get the rgba at/
   * @return an int which is the rgba at the given index.
   */
  public int getRgba(int index);


  /**
   * Give the list of rgba values from the fields of this rgba values.
   *
   * @return an Array where the first index value is red value, second green, and so on for a size.
   */
  int[] getRgba();

  /**
   * Compares the numbers in regard to the brightness and intensity.
   *
   * @param other the other hw4.model.Pixel object being compared.
   * @return a boolean to indicate if the VIL are the same for both.
   */
  boolean compareVIL(Pixel other);


  /**
   * method that compares all the fields of a given pixel to the current pixel.
   *
   * @param other the pixel being given
   * @return a boolean value, true if the pixels have the same values, otherwise false.
   */
  boolean comparePixels(Pixel other);

  /**
   * method that checks if a pixel is transparent to see if a filter should be applied to it.
   *
   * @return true if the pixels alpha value is 0.
   */
  boolean isTransparent();


  /**
   * takes a pixel and turns it into a string.
   *
   * @return a string representation of a pixel
   */

  String pixelToString();

  /**
   * Converts a pixel to a string for view. but includes the alpha value.
   *
   * @return a String that is the pixel in the format "r g b a".
   */
  String pixelToStringA();

  /**
   * Changes the pixel value to the alpha calculated value using the background given.
   *
   * @param ba the background of the pixel that is being worked on.
   * @return a new pixel which has been "flattened"
   */
  Pixel alphaTize(Pixel ba);

  /**
   * Uses the mathematical formula to adjust a pixel values based on the background.
   *
   * @param ba a list of ints that is the background pixels in order rgba.
   * @return a new list of ints that are adjusted pixels.
   */
  int[] alphaTizePixel(int[] ba);

  /**
   * creates an int rgb from the color of the pixel, ignoring alpha value.
   *
   * @return an int for the rgb value.
   */
  int returnRGB();
}
