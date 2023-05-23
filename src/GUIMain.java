package hw4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import hw4.controller.PPMController;
import hw4.model.AbstractEditorModel;
import hw4.model.ProjectModel;
import hw4.view.JFrameImpl;
import hw4.view.ProjectModelView;

/**
 * A main class for a gui that lets the user run and edit an image using a JSwing.
 */
public class GUIMain {

  /**
   * The main method which will take in arguments and allow the user to either run in a terminal
   * with a script/text or run in a gui.
   *
   * @param args the arguments of the command line.
   * @throws IOException throws an exception if the File reading or writing fails.
   */
  public static void main(String[] args) throws IOException {

    if (args.length == 0) { //because there was no argument it just starts GUI
      AbstractEditorModel model = new ProjectModel(1, 1);
      JFrameImpl jFrame = new JFrameImpl(model);
      jFrame.setVisible(true);
      //IController controller = new GUIController(model, jFrame);
    } else if (args.length == 1 && args[0].equals("-text")) {
      ProjectModel model = new ProjectModel(1, 1);
      ProjectModelView view = new ProjectModelView(model);
      Readable rd = new InputStreamReader(System.in);
      PPMController pController = new PPMController(model, view, rd);
      pController.runEditor();
    } else if (args.length == 2 && args[0].equals("-text")) {
      //open file that is at arg[2]
      //pass the contents of this file into the readable
      //start the processor with these contents
      FileInputStream inStream = new FileInputStream(args[1]);
      Readable read = new InputStreamReader(inStream);
      ProjectModel modelScript = new ProjectModel(1, 1);
      ProjectModelView viewScript = new ProjectModelView(modelScript);
      PPMController pControllerScript = new PPMController(modelScript, viewScript, read);
      pControllerScript.runEditor();

    }


  }
}