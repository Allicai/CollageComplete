package hw4.model;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.filters.Filter;

/**
 * an editor model interface which only requires you to be able to save and load an image into it.
 */
public interface EditorModel {


  /**
   * Saves the project as a final image. this is a text file.
   *
   * @param path the path to the project that is being saved.
   */
  void saveProjectFinal(String path);

  /**
   * loading an image based on the path given from the computer using a file input stream.
   *
   * @param path the string which will dive into the computer.
   * @return an Image which is in ppm format so far.
   */
  Image loadImage(String path);

  /**
   * Give the max value of the pixels.
   *
   * @return the max value of the pixels.
   */
  int maxValueInt();

  /**
   * Creates an empty/white background for a project.
   *
   * @param height the int to represent the amount of rows.
   * @param width  the int to represent the amount of columns.
   * @param alpha  the alpha value or level or transparency.
   * @return a List with a single layer to be the background.
   */
  List<Layer> createBackgroundLayer(int height, int width, int alpha);

  /**
   * Create a background for an image. A set of white pixels with the given height and width.
   *
   * @param height the height.
   * @param width  the width.
   * @param alpha the alpha of the pixels for the background
   * @return a List of Lists of pixels.
   */
  List<List<Pixel>> createBackgroundPixels(int height, int width, int alpha);

  /**
   * A method for getting the width of the model.
   * @return the width of the model.
   */
  int getWidth();

  /**
   * A method for getting the height of the model.
   * @return the height of the model.
   */
  int getHeight();

  /**
   * A method to get the list of layers within a model.
   * @return the list of the layers stored in the model.
   */
  List<Layer> getLayers();

  /**
   * A method to set a filter for a layer within the model.
   * @param filter the type of filter you want to assign to the layer.
   * @param name the name of the layer you want to assign the filter to.
   */
  void setFilter(Filter filter, String name);

  /**
   * A method to select a specific layer from the model.
   * @param newInt the index of the layer you are selecting.
   */
  void setCurrLayer(int newInt);

  /**
   * A method to get the index of the current layer selected in the model.
   * @return the integer that is the index of the current layer.
   */
  int getCurrLayer();

  /**
   * A method to get the index of a layer in the model by name.
   * @param name the name of the layer.
   * @return the integer that is the index value of the layer.
   */
  int giveLayerByName(String name);

  /**
   * A method to get a filter by its name.
   * @param name the name of the filter you want to get.
   *             for example, "darken-value" would give you a DarkenValue filter and
   *             "brighten-luma" would give you a BrightenLuma filter.
   * @return the Filter with the given name.
   */
  Filter giveFilter(String name);

  /**
   * A method to turn a project into a buffered image.
   * @return a buffered image that is the representation of the project's composite image.
   */
  BufferedImage projectToImage();

  /**
   * A method to load a project from a filepath.
   * @param path the filepath of the project.
   * @return a model containing all the information relevant to the project being loaded.
   * @throws FileNotFoundException an exception thrown when no file is found at the given filepath.
   */
  AbstractEditorModel loadProject(String path) throws FileNotFoundException;

  /**
   * A method for getting a layer's name from the model by its index.
   * @param index the index value of the layer.
   * @return the name of the layer at the given index.
   */
  String getLayerByIndex(int index);

  /**
   * A method to add an image to a specific layer.
   * @param lName the name of the layer you want to add an image to.
   * @param iPath the filepath of the image you want to add.
   * @param height the vertical offset of the image.
   * @param width the horizontal offset of the image.
   * @throws IOException throws an exception if loading the image fails.
   */
  void addImageToLayer(String lName, String iPath, int height, int width) throws IOException;

  /**
   * A method to check if two layers have the same name.
   * @param name the name of the layer we want to compare to.
   * @return a boolean value, true if the names match, false otherwise.
   * This method is used to make sure that two layers with the same name cannot be created.
   */
  boolean hasSameName(String name);

  /**
   * A method to add a layer to the list of layers in the model.
   * Handles a new layer being created.
   * @param layer the layer being added.
   */
  void addLayerByLayer(Layer layer);

  /**
   * Prints an unfinished file to a file using the given file path & the collage format.
   *
   * @param s a string ot represent the location this file will be.
   * @throws IOException is thrown if the file cannot be written to or the path is incorrect.
   */
  void saveCollageFormat(String s) throws IOException;

  /**
   * Allows the user to set the models values to the new width and height as well as creates a
   * background layer essentially initializing a new model. Or triggering the initiliaztion of a
   * new model.
   */
  void initProject(int height, int width);


}
