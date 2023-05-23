package hw4.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface for the controllers that need ActionListeners.
 */
public interface IController extends ActionListener {

  /**
   * A massive function that looks for the key pressed and applies all the given functionality of
   * an image processor according to previous specification.
   *
   * @param e the event to be processed.
   */
  void actionPerformed(ActionEvent e);

  /**
   * If the thing has been loaded give true if it doesn't give false.
   *
   * @return a boolean to represent if the thing has loaded.
   */
  boolean isLoaded();

  /**
   * Tells the user to load a game before they can continue to edit something.
   */
  void errorMessage();
}
