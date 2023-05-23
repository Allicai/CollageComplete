package hw4.model.Images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hw4.model.Pixels.Pixel;


/**
 * An image class which is just a grid of pixels.
 */
public class Image implements IImage {

  private List<List<Pixel>> image;

  /**
   * A new basic constructor that lets you set the images.
   *
   * @param image the gird of pixels by pixels that represents the image.
   */
  public Image(List<List<Pixel>> image) {
    this.image = image;
  }

  /**
   * a method to get the height of an image.
   *
   * @return an int representing the height
   */
  public int getHeight() {
    return this.image.size();
  }

  /**
   * method to get the width of an image.
   *
   * @return an int representing the width.
   */
  public int getWidth() {
    return this.getRow(0).size();
  }

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
  public void replacePixels(Image other, int horizontal, int vertical) throws IOException {
    for (int r = horizontal; r < horizontal + other.getWidth(); r++) {
      for (int c = vertical; c < vertical + other.getHeight(); c++) {
        if (r > this.getWidth() || r < 0 || c > this.getHeight() || c < 0) {
          continue;
        } else {
          this.getRow(r).get(c).setRgba(other.getRow(r - horizontal)
                  .get(c - vertical).getRgba());
        }
      }
    }
  }


  /**
   * Return the largest pixel value from the image.
   *
   * @return the pixel value with the largest int.
   */
  public int maxImageValue() {
    int maxValue = 0;
    for (int i = 0; i < this.getHeight(); i++) {
      ArrayList<Pixel> row = (ArrayList<Pixel>) image.get(i);
      for (int j = 0; j < this.getWidth(); j++) {
        int placeHolder = row.get(j).getValue();
        if (maxValue < placeHolder) {
          maxValue = placeHolder;
        } else {
          continue;
        }
      }
    }
    return maxValue;
  }

  /**
   * Set the vil of every single pixel based on its values.
   */
  public void setVIL() {
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        this.image.get(i).get(j).setVil();
      }
    }
  }

  /**
   * Returns the image in the image content format.
   *
   * @return a String containing the details of the image in the image content format.
   */
  public String imageContentFormat() {
    String base = "";
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        if (i == this.getHeight() - 1 && j == this.getWidth() - 1) {
          base += this.image.get(i).get(j).pixelToString() + " ";
        } else {
          base += this.image.get(i).get(j).pixelToString() + " \n";
        }
      }
    }
    return base;
  }

  /**
   * Returns the image in the image content format.
   *
   * @return a String containing the details of the image in the image content format.
   */
  public String imageContentFormatA() {
    String base = "";
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        if (i == this.getHeight() - 1 && j == this.getWidth() - 1) {
          base += this.image.get(i).get(j).pixelToStringA() + "";
        } else {
          base += this.image.get(i).get(j).pixelToStringA() + "\n";
        }
      }
    }
    return base;
  }


  /**
   * gets the row at the given index.
   *
   * @param i the given index.
   * @return a List of Pixels that is the row at the given index.
   */
  public List<Pixel> getRow(int i) {
    return this.image.get(i);
  }

  /**
   * compares two images.
   *
   * @param other the other image to compare to.
   * @return a boolean value representing if the images are the same or not.
   */
  public boolean compareImages(Image other) {
    boolean same = true;

    // Create deep copies of the two images
    List<List<Pixel>> thisCopy = new ArrayList<>();
    for (int i = 0; i < this.getHeight(); i++) {
      List<Pixel> rowCopy = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) {
        Pixel pixelCopy = new Pixel(this.getRow(i).get(j).getRgba().clone());
        rowCopy.add(pixelCopy);
      }
      thisCopy.add(rowCopy);
    }

    List<List<Pixel>> otherCopy = new ArrayList<>();
    for (int i = 0; i < other.getHeight(); i++) {
      List<Pixel> rowCopy = new ArrayList<>();
      for (int j = 0; j < other.getWidth(); j++) {
        Pixel pixelCopy = new Pixel(other.getRow(i).get(j).getRgba().clone());
        rowCopy.add(pixelCopy);
      }
      otherCopy.add(rowCopy);
    }

    // Compare the pixels in the two images
    for (int i = 0; i < thisCopy.size(); i++) {
      for (int j = 0; j < thisCopy.get(i).size(); j++) {
        if (!thisCopy.get(i).get(j).comparePixels(otherCopy.get(i).get(j))) {
          same = false;
          break;
        }
      }
      if (!same) {
        break;
      }
    }
    return same;
  }

  /**
   * A method to get a deep copy of the current image to manipulate as needed.
   *
   * @return a list of lists of pixels that comprise an image.
   */
  public List<List<Pixel>> getImage() {
    List<List<Pixel>> temp = new ArrayList<List<Pixel>>();
    for (int i = 0; i < image.size(); i++) {
      List<Pixel> tempRow = new ArrayList<>();
      for (int j = 0; j < image.get(i).size(); j++) {
        tempRow.add(this.image.get(i).get(j));
      }
      temp.add(tempRow);
    }
    return temp;
  }
  /**/

  /**
   * method to compress the image for when you save a project.
   *
   * @param prev the previous image (i.e. the one below the current one).
   * @return returns a compressed image.
   */
  public Image compressImage(Image prev) {

    List<List<Pixel>> temp = new ArrayList<List<Pixel>>();
    for (int i = 0; i < this.getHeight(); i++) { //For every row of pixels
      List<Pixel> tempRow = new ArrayList<>();
      for (int j = 0; j < this.getWidth(); j++) { //for every pixel in that row
        Pixel og = this.getImage().get(i).get(j); //get the pixel we are adjusting
        Pixel ba = prev.getImage().get(i).get(j); //get the background pixel
        Pixel adjusted = og.alphaTize(ba);

        //   image  get i  get j set j to the new adjusted pixel
        tempRow.add(adjusted);
      }
      temp.add(tempRow);
    }
    return new Image(temp);
  }
}