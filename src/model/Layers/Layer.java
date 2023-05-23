package hw4.model.Layers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Pixels.Pixel;
import hw4.model.filters.Filter;
import hw4.model.filters.NormalFilter;

/**
 * An image class made up of an array of arrays of pixels. It also has a filter and a name of
 * itself to be used.
 */
public class Layer implements ILayer {
  private hw4.model.Images.Image image;
  private Filter filter;
  private String name;

  /**
   * A basic constructor to create an image using the given array list of array list of pixels.
   *
   * @param image the matrix of pixels.
   * @param name  the name of the layer.
   */
  public Layer(hw4.model.Images.Image image, String name) {
    this.image = image;
    this.name = name;
    filter = new NormalFilter();
  }

  /**
   * A basic constructor which does not take in anything and creates the background.
   *
   * @param image the Image class which will be this layers image.
   */
  Layer(hw4.model.Images.Image image) {
    this.image = image;
    this.name = "Background";
    this.filter = new NormalFilter();
  }

  /**
   * A basic constructor that lets you set all three of the layer values.
   *
   * @param image  the Image class which will be this layers image.
   * @param name   the name of the layer for future use, is unique.
   * @param filter the filter class that is going to be this filter.
   */
  public Layer(hw4.model.Images.Image image, String name, Filter filter) {
    this.image = image;
    this.name = name;
    this.filter = filter;
  }

  /**
   * get a deep copy of the image which just copies all the pixels into a new image.
   *
   * @return a new Image that is the old image field of the layer.
   */
  public hw4.model.Images.Image getImage() {
    List<List<Pixel>> tempPixels = new ArrayList<List<Pixel>>();
    for (int i = 0; i < this.image.getHeight(); i++) {
      List<Pixel> tempRow = new ArrayList<Pixel>();
      for (int j = 0; j < this.image.getWidth(); j++) {
        tempRow.add(this.image.getImage().get(i).get(j));
      }
      tempPixels.add(tempRow);
    }
    return new hw4.model.Images.Image(tempPixels);
  }

  //get the width by getting the amount

  /**
   * get the max value of the function which is the highest rgb value which exists.
   *
   * @return an int for the max value of the layer and all the pixels in its image.
   */
  public int maxLayerValue() {
    return this.image.maxImageValue();
  }

  /**
   * get this layers name so that the interface can know which layer we are on by string.
   *
   * @return a String that is the name of this layer, will be unique.
   */
  public String getName() {
    return this.name;
  }

  /**
   * get this layers filter which is the integral piece to a layer v. an image.
   *
   * @return an old instance of the Filter that is this layers filter.
   */
  public Filter getFilter() {
    return this.filter;
  }

  /**
   * get the height of this image which are the amount of rows.
   *
   * @return an int for the height of this layers image.
   */
  public int getLayerHeight() {
    return image.getHeight();
  }

  /**
   * get the width of this image which is the amount of pixels in a row.
   *
   * @return an int for the width of this layers image.
   */
  public int getLayerWidth() {
    return image.getWidth();
  }


  /**
   * the layer content format just includes the layers.
   *
   * @return a string that is the above format.
   */

  public String layerContentFormat() {
    return "" + this.getName().toString() + " " + this.getFilter().getName() + "\n"
            + this.getImage().imageContentFormatA() + "\n";
  }


  /**
   * return the name of the layer from the this.name field
   *
   * @return the string that is this.name or jsut the name of the current layer.
   */

  public String toString() {
    return this.name;
  }


  /**
   * Turn the given layer into final image format, basically above but w/o name and filter etc.
   *
   * @return a new string that is the finalImage format as a ppm.
   */

  public String finalImageFormat() {
    return "" + this.getImage().imageContentFormat();
  }

  /**
   * Makes the filter the given filters so that it can be used later.
   *
   * @param filter the filter to be changed from the old one.
   */
  public void setFilter(Filter filter) {
    this.filter = filter;
  }

  /**
   * Takes a list of layers and returns a layer that has been affected by the previous layers
   * which were all compressed.
   *
   * @param layers the list of layers behind it which will be used to compresed the current.
   */

  public Layer compressLayers(List<Layer> layers) {
    List<Layer> updatedLayers = new ArrayList<>();
    for (int i = 0; i < layers.size(); i++) {
      if (i == 0) {
        //Do nothing because we are in the background layer
        updatedLayers.add(layers.get(i));
      } else {
        layers.get(i).compressLayers(layers.subList(0, i));
      }

    }
    return updatedLayers.get(layers.size() - 1);
  }

  /**
   * Applies a filter and returns the new LAyer which had been adjusted.
   *
   * @return a deep copy of the layer with the filter applied.
   */
  public Layer applyFilter(List<Layer> layers, int index) {
    List<List<Pixel>> tempPixels = new ArrayList<>();
    for (int i = 0; i < this.getImage().getHeight(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < this.getImage().getWidth(); j++) {
        Pixel tempPixel = new Pixel(this.getImage().getRow(i).get(j).getRgba());
        row.add(tempPixel);
      }
      tempPixels.add(row);
    }
    // creating a copy of the image and filter.
    hw4.model.Images.Image tempImage = new hw4.model.Images.Image(tempPixels);
    Filter tempFilter = this.getFilter();
    Layer copy = new Layer(tempImage, this.getName(), tempFilter);
    List<Layer> layersCopy = new ArrayList<>();
    for (int i = 0; i < index; i++) {
      Layer tempLayer = new Layer(layers.get(i).getImage(), layers.get(i).getName());
      tempLayer.filter = layers.get(i).getFilter();
      layersCopy.add(tempLayer);
    }
    copy.getFilter().apply(copy, layersCopy);
    copy.image.setVIL();
    return copy;
  }

  /**
   * Changes this layers image to have the same pixels as the given image starting at the given
   * offset.
   *
   * @param other the image we are turning the layer image into.
   * @param offX  the offset x the replacement will start at.
   * @param offY  the offset y the replacement will start at.
   * @throws IOException throws an exception if the offset is outside the design parameters ->
   *                     refer to replacePixels() documentation.
   */
  public void replaceImage(hw4.model.Images.Image other, int offX, int offY) throws IOException {
    this.image.replacePixels(other, offX, offY);
  }


  /**
   * Alter the rgba value of this layer using the mathematical formula and the previous layer.
   *
   * @return a layer that has the composite image in it.
   */
  public Layer compress(Layer prev) {
    return new Layer(this.getImage().compressImage(prev.getImage()));
  }

  /**
   * gets a non-deep copy so that you can use the real image and adjust the values of it, not-used.
   *
   * @return an old Image that is the image of the Layer.
   */
  public Image getRealImage() {
    return this.image;
  }
}