package hw4.model.Images;

import java.io.IOException;
import java.util.List;

import hw4.model.Pixels.Pixel;

public interface IImage {

  /**
   * a method to get the height of an image.
   *
   * @return an int representing the height
   */
  int getHeight();

  /**
   * method to get the width of an image.
   *
   * @return an int representing the width.
   */
  int getWidth();

  // image.size = height
  // image.get(0).size = width

  /**
   * A method to "place" an image "on" another image.
   * What it really does is change the rgba values of the pixels in the location we want to place
   * the image in to the rgba values of the pixels of the image we are "placing".
   * This method is really meant for use with a layer, its functionality is based on the fact that
   * we made our layer contain an image.
   *
   * @param other      the other image being placed on the base image.
   * @param horizontal the number of pixels we are offsetting the image horizontally.
   * @param vertical   the number of pixels we are offsetting the image vertically.
   */
  void replacePixels(Image other, int horizontal, int vertical) throws IOException;


  /**
   * Return the largest pixel value from the image.
   *
   * @return the pixel value with the largest int.
   */
  int maxImageValue();

  /**
   * Set the vil of every single pixel based on its values.
   */
  void setVIL();

  /**
   * Returns the image in the image content format.
   *
   * @return a String containing the details of the image in the image content format.
   */
  String imageContentFormat();

  /**
   * Returns the image in the image content format.
   *
   * @return a String containing the details of the image in the image content format.
   */
  String imageContentFormatA();


  /**
   * gets the row at the given index.
   *
   * @param i the given index.
   * @return a List of Pixels that is the row at the given index.
   */
  List<Pixel> getRow(int i);

  /**
   * compares two images.
   *
   * @param other the other image to compare to.
   * @return a boolean value representing if the images are the same or not.
   */
  boolean compareImages(Image other);

  /**
   * A method to get a deep copy of the current image to manipulate as needed.
   *
   * @return a list of lists of pixels that comprise an image.
   */
  List<List<Pixel>> getImage();
  /**/

  /**
   * method to compress the image for when you save a project.
   *
   * @param prev the previous image (i.e. the one below the current one).
   * @return returns a compressed image.
   */
  Image compressImage(Image prev);
}
