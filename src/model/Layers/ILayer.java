package hw4.model.Layers;

import java.io.IOException;
import java.util.List;

import hw4.model.Images.Image;
import hw4.model.filters.Filter;

public interface ILayer {
  /**
   * get a deep copy of the image which just copies all the pixels into a new image.
   *
   * @return a new Image that is the old image field of the layer.
   */
  Image getImage();

  //get the width by getting the amount

  /**
   * get the max value of the function which is the highest rgb value which exists.
   *
   * @return an int for the max value of the layer and all the pixels in its image.
   */
  int maxLayerValue();

  /**
   * get this layers name so that the interface can know which layer we are on by string.
   *
   * @return a String that is the name of this layer, will be unique.
   */
  public String getName();

  /**
   * get this layers filter which is the integral piece to a layer v. an image.
   *
   * @return an old instance of the Filter that is this layers filter.
   */
  Filter getFilter();

  /**
   * get the height of this image which are the amount of rows.
   *
   * @return an int for the height of this layers image.
   */
  int getLayerHeight();

  /**
   * get the width of this image which is the amount of pixels in a row.
   *
   * @return an int for the width of this layers image.
   */
  int getLayerWidth();


  /**
   * the layer content format just includes the layers.
   *
   * @return a string that is the above format.
   */

  String layerContentFormat();

  /**
   * return the name of the layer from the this.name field
   *
   * @return the string that is this.name or jsut the name of the current layer.
   */

  String toString();


  /**
   * Turn the given layer into final image format, basically above but w/o name and filter etc.
   *
   * @return a new string that is the finalImage format as a ppm.
   */

  String finalImageFormat();

  /**
   * Makes the filter the given filters so that it can be used later.
   *
   * @param filter the filter to be changed from the old one.
   */
  void setFilter(Filter filter);

  /**
   * Takes a list of layers and returns a layer that has been affected by the previous layers
   * which were all compressed.
   *
   * @param layers the list of layers behind it which will be used to compresed the current.
   */

  Layer compressLayers(List<Layer> layers);

  /**
   * Applies a filter and returns the new LAyer which had been adjusted.
   *
   * @return a deep copy of the layer with the filter applied.
   */
  Layer applyFilter(List<Layer> layers, int index);

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
  void replaceImage(Image other, int offX, int offY) throws IOException;

  /**
   * Alter the rgba value of this layer using the mathematical formula and the previous layer.
   *
   * @return a layer that has the composite image in it.
   */
  Layer compress(Layer prev);

  /**
   * gets a non-deep copy so that you can use the real image and adjust the values of it, not-used.
   *
   * @return an old Image that is the image of the Layer.
   */
  Image getRealImage();
}
