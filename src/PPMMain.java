package hw4;

import java.io.IOException;
import java.io.InputStreamReader;

import hw4.controller.PPMController;
import hw4.model.ProjectModel;
import hw4.view.ProjectModelView;

/**
 * class that runs a program where the user can input commands and see a ppm in text format.
 */
public class PPMMain {

  /**
   * method to run the ppm controller for the Collage.
   * Takes in text inputs from the user and returns the ppm txt format.
   * @param args the inputs from the user.
   * @throws IOException Exception for invalid commands or illegal arguments.
   */
  public static void main(String[] args) throws IOException {
    ProjectModel model = new ProjectModel(1, 1);
    ProjectModelView view = new ProjectModelView(model);
    Readable rd = new InputStreamReader(System.in);
    PPMController controller = new PPMController(model, view, rd);
    controller.runEditor();

  }

}
