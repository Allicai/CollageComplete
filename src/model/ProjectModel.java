package hw4.model;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import hw4.model.Layers.Layer;

/**
 * A project class which has a map of layers.
 */
public class ProjectModel extends AbstractEditorModel {

  public ProjectModel(List<Layer> layers, int height, int width) {
    super(layers, height, width);
  }

  public ProjectModel(int height, int width) {
    super(height, width);
  }

  /**
   * Save a project using the path to your thing.
   *
   * @param path the path to the project that is being saved.
   */
  public void saveProjectFinal(String path) {
    FileOutputStream fileOut = null;
    //Turn the file into something that is usable
    try {
      fileOut = new FileOutputStream(path);
    } catch (IOException e) {
      System.out.println("Writing to file error1");
    }

    //if the given path is a ppm then save it as a ppm like normal
    if (path.contains(".ppm")) {
      String content = this.imageFormat();
      byte[] bContents = content.getBytes();
      try {
        fileOut.write(bContents);
      } catch (IOException ex) {
        System.out.println("writing to file error");
      }
    } else {
      //The header of the file
      String format = path.split("\\.")[1];
      //The new buffered image
      BufferedImage bufImage = this.projectToImage();
      //if the given path is not a ppm then save it using ImageIO writing? -convert to buffered
      // image first
      try {
        ImageIO.write(bufImage, format, fileOut);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }

  }

  /**
   * Prints an unfinished file to a file using the given file path & the collage format.
   *
   * @param path a string ot represent the location this file will be.
   * @throws IOException is thrown if the file cannot be written to or the path is incorrect.
   */
  public void saveCollageFormat(String path) throws IOException {
    //--------------------------------------------------------//
    FileOutputStream fileOut = new FileOutputStream(path);
    String content = this.collageContentFormat();
    byte[] bContents = content.getBytes();
    fileOut.write(bContents);
    fileOut.close();
  }

  /**
   * Prints a string of the model in the collage layout format.
   *
   * @return a string to represent the image using the bits.
   */
  public String collageContentFormat() {
    //create a string of the beginning and height & width, etc
    int max = this.maxValueInt();
    String c1 = "" + "c1" + "\n" + this.getHeight() + " " + this.getWidth() + "\n"
            + max + "\n";
    String base = "";
    for (int i = 0; i < layers.size(); i++) {
      base += layers.get(i).layerContentFormat();
    }
    return c1 + base;
  }
}
