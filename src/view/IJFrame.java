package hw4.view;

import hw4.model.EditorModel;
import hw4.model.Layers.Layer;
import hw4.model.filters.Filter;


/**
 * an interface for visual representations of the ppm files being manipulated by Collage.
 */
public interface IJFrame {

  /**
   * Action performed will take the two string values from the drop-down as ints and give u a new
   * project that uses these as the dimensions.
   *
   * @return an array of two ints that are the dimensions with width first and height second.
   */
  int[] createNewProject();

  /**
   * Sends a message as a popup box to the GUI so that the user can see it.
   *
   * @param message the message that the user is going to be able to see.
   */
  void renderMessage(String message);

  /**
   * Open up a file chooser to get the path that the user is looking for, will work for Project
   * and Image.
   *
   * @return a String that is the path to the file that was chosen.
   */
  public String getPath();

  /**
   * Paint the buffered image onto the imagePanel whenever called by loading the project.
   */
  void loadBufferedImage();

  /**
   * Set the offset of the image adding similar to the create new project method.
   *
   * @return an int array where 0 is the first offset (x) and 1 is the second offset(y).
   */
  int[] offSetProject();

  /**
   * Populate with buttons for changing the layer based on the model and then let you click one.
   * will change the current layer that we are working on.
   *
   * @return an int to represent the index of the current layer we are working on.
   */
  int affectCurrLayer();

  /**
   * Creates a new filter from one of the three "darkness" filters using the information it is
   * given and the button pressed.
   *
   * @return a Filter that is either darken-value, luma or intensity.
   */
  Filter darknessFilter();

  /**
   * Creates a new filter from one of the three "brightness" filters using the information it is
   * given and the button pressed.
   *
   * @return a Filter that is either brighten-value, luma or intensity.
   */
  Filter brightnessFilter();

  /**
   * Creates a new filter from one of the three "component" filters using the information it is
   * given and the button pressed.
   *
   * @return a Filter that is blue, red or green.
   */
  Filter componentFilter();

  /**
   * Creates a new filter from one of the three "blending" filters using the information it is
   * given and the button pressed.
   *
   * @return a Filter that is multiplied, screen, or difference.
   */
  Filter blendingFilter();

  /**
   * Will allow the user to set a filter of the currently selected layer by making a popup that
   * prompts for a String and optional choice and turning those two things into a filter.
   *
   * @return a new Layer with the prompted information.
   */
  Layer addLayerToModel();

  /**
   * Set the current views model which it uses for display to the given one.
   *
   * @param model the new AbstractEditorModel that is going to be the this.model of the view.
   */
  void setModel(EditorModel model);

  /**
   * Save something as a project.
   *
   * @return a string that is the path we are going to save the project to.
   */
  String saveProject();

}

