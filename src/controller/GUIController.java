package hw4.controller;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import hw4.model.EditorModel;
import hw4.model.Layers.Layer;
import hw4.model.filters.Filter;
import hw4.view.IJFrame;

/**
 * A controller for the given GUI, allows the user to run an image processor with a visual GUI so
 * that it is easier to edit and understand by looking at a composite image.
 * Handles directing commands to the model and refreshing the view while guiding users.
 */
public class GUIController implements IController {

  private EditorModel model;
  private IJFrame view;
  private Readable readable;


  /**
   * A basic constructor for this controller.
   * @param model    the actual game being passed into it. part of the MVC.
   * @param view     the part of the MVC that allows for viewing of the game through transmission
   *                 Essentially the visual representation of the GUI.
   * @param readable a passed in Readable to be able to input into the controller.
   */
  public GUIController(EditorModel model, IJFrame view, Readable readable) {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Can't have null fields in constructor");
    } else {
      this.model = model;
      this.view = view;
      this.readable = readable;
    }
  }

  /**
   * controller that doesn't take in a readable but same as above otherwise.
   *
   * @param model the model of the controller we will use.
   * @param view  the view of the controller we will use.
   */
  public GUIController(EditorModel model, IJFrame view) {
    if ((model == null) || (view == null)) {
      throw new IllegalArgumentException("Can't have null fields in constructor");
    } else {
      this.model = model;
      this.view = view;
    }
  }

  /**
   * A massive function that looks for the key pressed and applies all the given functionality of
   * an image processor according to previous specification.
   *
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    int counter = 0;
    boolean quit = false;
    String command = e.getActionCommand();

    switch (command) {
      //------------------------------------------------------------------------------------------//

      case "new-project":
        try {
          if (this.model.getHeight() != 1) { // warns you about new after load or new
            view.renderMessage("Warning you are overwriting your current project");
          } else {
            int[] newProject = view.createNewProject(); // creates two ints for size x and y
            this.model.initProject(newProject[0], newProject[1]);
            view.setModel(this.model); //sets views model
            view.renderMessage("You have successfully created a new project");
            view.loadBufferedImage();
          }
        } catch (IllegalArgumentException ex) {
          view.renderMessage("Please enter inputs greater than 0");
        }
        break;
      //This should do nothing if a project is already loaded

      //---------------------------------------------------------------------------------------//
      case "load-project":
        if (this.model.getHeight() != 1) {
          view.renderMessage("Warning you are overwriting your current project");
        } else {
          String path = view.getPath(); //gets the path
          try {
            this.model = model.loadProject(path); //sets the model = to the new model
          } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
          }
        }


        this.view.setModel(model);
        this.view.loadBufferedImage(); //loads the image
        break;
      //---------------------------------------------------------------------------------------//
      case "add-image-to-layer":
        view.setModel(model);
        if (this.model.getLayers().size() == 0) { //if we haven't started
          view.renderMessage("Error: Load a project first ");
        } else {
          String iPath = view.getPath(); //use get path to gert path from file chooser
          int[] offset = view.offSetProject(); //return two numbers that are offset
          try {
            model.addImageToLayer(model.getLayerByIndex(model.getCurrLayer()), iPath,
                    offset[0], offset[1]); //add image
            view.renderMessage("You have added an image to the layer!");
          } catch (IOException ex) {
            view.renderMessage("Error: We could not find this image and or layer, try again?");
          }
        }
        view.loadBufferedImage();
        break;
      //---------------------------------------------------------------------------------------//
      case "layers":
        view.setModel(model);
        if (this.isLoaded()) {
          //get the current layer
          int currLayer = 0; //init a currLayer value which will change.
          try {
            currLayer = view.affectCurrLayer(); //get the current layer of the model as well as
            // other stuff, refer to method javadoc.
          } catch (ArrayIndexOutOfBoundsException ex) { //throws out of bounds if the current
            // layer has an issue/
            view.renderMessage("Select a layer to continue");
          }
          this.model.setCurrLayer(currLayer); //set this models currLayer to that layer
          view.renderMessage("You have changed the layer");
          view.setModel(model);
          view.loadBufferedImage(); //loads the image onto the view, updating & repainting.
        }

        break;
      //---------------------------------------------------------------------------------------//
      case "set-darkness": //for all three cases (Maybe could've used same action) will do filter
        // pressed.
        view.setModel(model);
        if (isLoaded()) {
          Filter filter1 = view.darknessFilter();
          String name1 = this.model.getLayerByIndex(this.model.getCurrLayer());
          model.setFilter(filter1, name1);
          view.renderMessage("You have changed the filter!");
          view.setModel(this.model);
          view.loadBufferedImage();
        } else {
          this.errorMessage();
        }
        break;
      case "set-brightness":
        view.setModel(model);
        if (isLoaded()) {
          Filter filter1 = view.brightnessFilter();
          String name1 = this.model.getLayerByIndex(this.model.getCurrLayer());
          model.setFilter(filter1, name1);
          view.renderMessage("You have changed the filter!");
          view.setModel(this.model);
          view.loadBufferedImage();
        } else {
          this.errorMessage();
        }
        break;
      case "set-component":
        view.setModel(model);

        if (isLoaded()) {
          Filter filter1 = view.componentFilter();
          String name1 = this.model.getLayerByIndex(this.model.getCurrLayer());
          model.setFilter(filter1, name1);
          view.renderMessage("You have changed the filter!");
          view.setModel(this.model);
          view.loadBufferedImage();
        } else {
          this.errorMessage();
        }
        break;
      case "set-blend":
        view.setModel(model);
        if (isLoaded()) {
          Filter filter1 = view.blendingFilter();
          String name1 = this.model.getLayerByIndex(this.model.getCurrLayer());
          model.setFilter(filter1, name1);
          view.renderMessage("You have changed the filter!");
          view.setModel(this.model);
          view.loadBufferedImage();
        } else {
          this.errorMessage();
        }
        break;
      //---------------------------------------------------------------------------------------//
      case "add-layer":
        view.setModel(model);
        if (this.isLoaded()) {
          view.setModel(this.model); //sets model beforehand
          Layer tLayer = view.addLayerToModel(); //uses view method to get the new layer
          if (!model.hasSameName(tLayer.getName())) { //if the layer already exists or doesn't
            this.model.addLayerByLayer(tLayer); //Good? Add Layer
            view.setModel(this.model); //update view
          } else {
            view.renderMessage("You already have a layer with this name"); //Bad? Update
          }
        } else {
          this.errorMessage();
        }

        break;
      //---------------------------------------------------------------------------------------//
      case "save-project":
        view.setModel(model);
        if (this.isLoaded()) {
          String savePath = view.saveProject(); //the path from JFileChooser
          this.model.saveProjectFinal(savePath); //file writing
          view.renderMessage("You have saved the project as an image");
        } else {
          this.errorMessage();
        }
        break;
      case "save-projectAsProject":
        view.setModel(model);
        if (this.isLoaded()) {
          String savePath1 = view.saveProject(); //the path from JFileChooser
          try {
            this.model.saveCollageFormat(savePath1); //file writing
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
          view.renderMessage("You have saved the project as an image");
        } else {
          this.errorMessage();
        }
        break;
      default:
        view.renderMessage("You did nothing");
        break;
    }


  }

  /**
   * If the thing has been loaded give true if it doesn't give false.
   *
   * @return a boolean to represent if the thing has loaded.
   */
  public boolean isLoaded() {
    return this.model.getHeight() != 1;
  }

  /**
   * Tells the user to load a game before they can continue to edit something.
   */
  public void errorMessage() {
    view.renderMessage("Load or Create a projected before continuing");
  }

}



