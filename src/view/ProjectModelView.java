package hw4.view;

import java.io.IOException;

import hw4.model.EditorModel;

/**
 * The project model view class to see things.
 */
public class ProjectModelView implements IModelView {

  private EditorModel gameModel; // error?

  private Appendable out;

  /**
   * The basic constructor.
   *
   * @param gameModel the state of the model.
   */
  public ProjectModelView(EditorModel gameModel) {
    this.gameModel = gameModel;
    this.out = System.out;
  }

  /**
   * another constructor that takes in an appendable as well.
   *
   * @param gameModel the state of the model.
   * @param out       the place where the view is being printed.
   * @throws IllegalArgumentException handles null values.
   */
  public ProjectModelView(EditorModel gameModel, Appendable out) throws IllegalArgumentException {
    if (gameModel == null || out == null) {
      throw new IllegalArgumentException("Either the game model or the appendable are null");
    } else {
      this.gameModel = gameModel;
      this.out = out;
    }

  }

  /**
   * Prints a message to the appendable of the view class.
   *
   * @param message the string that is to be printed.
   * @throws IOException if it fails to send properly.
   */
  public void renderMessage(String message) throws IOException {
    out.append(message);
  }

  /**
   * returns the help message so the user can know what to do.
   *
   * @throws IOException if out rendering doesn't work.
   */
  public void helpMessage() throws IOException {
    String base = "--help \n" + "usage: [new-project [width] [height]] [load-project <path>]\n"
            + "[add-layer [layer]] [add-image-to-layer [layer] [<path> to image] [offset x] [offset"
            + " y]\n" + "[set-filter [layer] [filter]] [display-project]\n"
            + "[save-project <path>]" + "[save-image <path>]";

    String descriptions = "\n\nnew-project: Create a new project using the given height and width"
            + "\nload-project: load an old project using the given path"
            + "\nadd-layer: adds a new layer to the project you are working in"
            + "\nadd-image-to-layer: adds an image to the specific layer using the offsets"
            + "\nset-filter: sets the specified layers filter to the new specified filter"
            + "\ndisplay-project: displays the collage Project Format"
            + "\n save-project: saves the project in the collage format"
            + "\nsave-image: saves the project as a ppm file\n\n";
    this.renderMessage(base + descriptions);
  }

}