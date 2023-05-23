package hw4.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import hw4.model.AbstractEditorModel;
import hw4.model.ProjectModel;
import hw4.model.filters.Filter;
import hw4.view.ProjectModelView;

/**
 * A text interface controller that will allow the user to type in put and edit an image solely
 * based on a text platform.
 */
public class PPMController {

  private AbstractEditorModel model;
  private ProjectModelView view;
  private Readable readable;


  /**
   * A basic constructor for this controller.
   *
   * @param model    the actual game being passed into it. part of the MVC.
   * @param view     the part of the MVC that allows for viewing of the game through transmission.
   * @param readable a passed in Readable to be able to input into the controller.
   */
  public PPMController(AbstractEditorModel model, ProjectModelView view, Readable readable) {
    if ((model == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Can't have null fields in constructor");
    } else {
      this.model = model;
      this.view = view;
      this.readable = readable;
    }
  }

  String argument = "Something is not working properly. hmmmm";


  /**
   * The entirety of the controller, allows for all features seen below.
   *
   * @throws IllegalStateException throws an illegal state exception if you try to do something
   *                               out of order.
   * @throws IOException           throws an io exception if you attempt to load or save a file
   *                               if it fails.
   */
  public void runEditor() throws IllegalStateException, IOException {
    Scanner scan = new Scanner(this.readable); //instantiate scanner
    boolean quit = false;
    boolean init = false;
    boolean help = false;

    while (!quit) {
      while (!help) {
        // the help message as an intro to the program
        this.renderMessageHelper("Welcome to the project editor... "
                + "below are all the commands, hit "
                + "--help to see them again at any time\n");
        try {
          view.helpMessage();
        } catch (IOException e) {
          throw new IllegalStateException("Hmm");
        }
        help = true;
      }
      String ogCommand = this.scanNext(scan);
      while (!init) {
        // handling the commands
        switch (ogCommand) {
          case "--help":
            try {
              view.helpMessage();
            } catch (IOException e) {
              throw new IllegalStateException();
            }
            ogCommand = this.scanNext(scan);
            break;
          case "new-project":
            // scanning the given values as ints, height and width
            this.model = new ProjectModel(stringToInt(scan, this.scanNext(scan)),
                    stringToInt(scan, this.scanNext(scan)));
            this.renderMessageHelper(
                    "You have successfully created a new project. Remember to save.\n");
            init = true;
            ogCommand = this.scanNext(scan);
            break;
          case "load-project":
            init = true;
            String path = "";
            try {
              path = this.scanNext(scan);
              model = model.loadProject(path);
            } catch (IOException e) {
              this.renderMessageHelper("This didn't work try again\n");
            }
            this.renderMessageHelper("You are now editing" + path + "\n");
            break;
          case "quit":
            quit = false;
            init = true;
            this.renderMessageHelper("Sad to see you go\n");
            break;
          default:
            this.renderMessageHelper("Please either load something or make a new project.\n");
            ogCommand = this.scanNext(scan);
        }

      }
      switch (ogCommand) {
        case "--help":
          try {
            view.helpMessage();
          } catch (IOException e) {
            throw new IllegalStateException();
          }
          break;
        case "display-project":
          this.renderMessageHelper(model.collageContentFormat());
          break;
        case "save-project":
          String temp = this.scanNext(scan);
          try {
            model.saveCollageFormat(temp);
            this.renderMessageHelper("You have saved your project @ " + temp + "\n");
          } catch (IOException e) {
            this.renderMessageHelper("There was a failure saving your project. Sorry!\n");
          }
          break;
        case "add-layer":
          String temp1 = this.scanNext(scan);
          try {
            this.model.addLayer(temp1);
            this.renderMessageHelper("You have successfully added a layer\n");
          } catch (IllegalArgumentException e) {
            this.renderMessageHelper("That layer already exists \n");
          }
          break;
        case "add-image-to-layer":
          String lName = this.scanNext(scan);
          String iPath = this.scanNext(scan);
          int height = stringToInt(scan, this.scanNext(scan));
          int width = stringToInt(scan, this.scanNext(scan));
          try {
            model.addImageToLayer(lName, iPath, height, width);
            this.renderMessageHelper("The image has been added to the " + lName + " layer\n");
          } catch (IOException e) {
            this.renderMessageHelper("Unable to add image for some reason\n");
          }

          break;
        case "set-filter":
          String lName1 = this.scanNext(scan);
          Filter filter = model.giveFilter(this.scanNext(scan));
          this.renderMessageHelper(filter.getName());

          model.setFilter(filter, lName1);
          this.renderMessageHelper("The filter has been changed!\n");
          break;
        case "save-image":
          String path = this.scanNext(scan);
          model.saveProjectFinal(path);
          this.renderMessageHelper("your image has been saved\n");
          init = false;
          break;
        case "quit":
          quit = true;
          this.renderMessageHelper("Project quit\n");
          break;
        default:
          this.renderMessageHelper("Wasn't one of the listed commands, type --help for a list\n");
          break;

      }

    }

  }

  /**
   * Returns a new int that is a valid input.
   *
   * @param scanner A scanner that takes in the readable from the controller.
   * @param value   the initial string that is being checked.
   * @return an int that is a valid int according to our parameters.
   */
  private int stringToInt(Scanner scanner, String value) {
    boolean whileLoopOver = false;
    String newValue = value;
    while (!whileLoopOver) {
      try {
        Integer.parseInt(newValue);

        if (Integer.parseInt(newValue) < 0) {

          throw new NumberFormatException("HAHA");
          // break;
        }
        whileLoopOver = true;

      } catch (NumberFormatException e) {
        newValue = this.scanNext(scanner);
      }
    }
    return Integer.parseInt(newValue);
  }


  /**
   * Helper method to throw an exception when the scanner isn't valid.
   *
   * @param scan scans a value from the readable.
   * @return a String which is a valid scanner.
   */
  private String scanNext(Scanner scan) {
    String temp;
    try {
      temp = scan.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStateException(argument);
    }

    /*if(!scan.hasNext()) {
      try {
          temp = scan.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("AHH");
      }
    } else {
      temp = scan.next();
    }*/
    return temp;
  }

  /**
   * Handles IO Exception for render message.
   *
   * @param message a string to be printed
   */

  public void renderMessageHelper(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("IDK");
    }
  }
}
