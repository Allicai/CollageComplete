package hw4.view;

import java.io.IOException;

/**
 * an interface for the text view of ppm files.
 */
public interface IModelView {

  /**
   * Prints a message to the appendable being used.
   * @param message the string to be printed.
   * @throws IOException if it fails to send properly.
   */
  void renderMessage(String message) throws IOException;

  /**
   * returns the help message so the user can know what to do.
   *
   * @throws IOException if out rendering doesn't work.
   */
  void helpMessage() throws IOException;
}
