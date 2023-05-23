package hw4.model;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.imageio.ImageIO;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.filters.BlueComponent;
import hw4.model.filters.BrightenIntensity;
import hw4.model.filters.BrightenLuma;
import hw4.model.filters.BrightenValue;
import hw4.model.filters.DarkenIntensity;
import hw4.model.filters.DarkenLuma;
import hw4.model.filters.DarkenValue;
import hw4.model.filters.Difference;
import hw4.model.filters.Filter;
import hw4.model.filters.GreenComponent;
import hw4.model.filters.Multiply;
import hw4.model.filters.NormalFilter;
import hw4.model.filters.RedComponent;
import hw4.model.filters.Screen;


/**
 * The abstract editor model which is an abstract version of an editor model for editing an image.
 * Handles general methodology for the model.
 */
public abstract class

AbstractEditorModel implements EditorModel {
  protected List<hw4.model.Layers.Layer> layers;
  protected int height;
  protected int width;
  protected int currLayer;
  protected int max;

  /**
   * A basic constructor that takes in a layer int and int.
   *
   * @param layers the list of layers that will be this models layers.
   * @param height the height.
   * @param width  the width.
   */
  public AbstractEditorModel(List<hw4.model.Layers.Layer> layers, int height, int width) {
    if (height == 0 || width == 0) {
      throw new IllegalArgumentException("can't have a game with no dimension");
    }
    this.layers = layers;
    this.height = height;
    this.width = width;
    this.max = this.maxValueInt();
  }

  /**
   * A basic constructor that will be used when the project is completely new.
   *
   * @param height the height as an int.
   * @param width  the width as an int.
   */

  public AbstractEditorModel(int height, int width) {
    if (height == 0 || width == 0) {
      throw new IllegalArgumentException("can't have a game with no dimension");
    }
    this.layers = createBackgroundLayer(height, width, 255);
    this.height = height;
    this.width = width;
  }


  /**
   * Give the max value of the pixels.
   *
   * @return the max value of the pixels.
   */
  public int maxValueInt() {
    int inFuncHolder = 0;
    for (int i = 0; i < layers.size(); i++) { // for every layer
      int layerMax = layers.get(i).maxLayerValue(); //get the max value of the layer
      if (layerMax > inFuncHolder) {
        inFuncHolder = layerMax;
      } else {
        //Don't do anything
      }
    }
    return inFuncHolder;
  }

  /**
   * Creates an empty/white background for a project.
   *
   * @param height the int to represent the amount of rows.
   * @param width  the int to represent the amount of columns.
   * @param alpha  the alpha value or level or transparency.
   * @return a List with a single layer to be the background.
   */

  public List<Layer> createBackgroundLayer(int height, int width, int alpha) {
    Image image = new Image(this.createBackgroundPixels(height, width, 255));
    Layer layer = new hw4.model.Layers.Layer(image, "Background");
    List<hw4.model.Layers.Layer> layers = new ArrayList<hw4.model.Layers.Layer>();
    return layers;
  }


  /**
   * Create a background for an image. A set of white pixels with the given height and width.
   *
   * @param height the height.
   * @param width  the width.
   * @param alpha  the alpha for the pixels.
   * @return a List of Lists of pixels.
   */
  public List<List<Pixel>> createBackgroundPixels(int height, int width, int alpha) {

    int[] rgba = {255, 255, 255, alpha};
    List<List<Pixel>> background = new ArrayList<List<Pixel>>(height);
    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> tempRow = new ArrayList<Pixel>(width);
      for (int j = 0; j < width; j++) {
        tempRow.add(new Pixel(rgba));
      }
      background.add(tempRow);
    }
    return background;

  }


  /**
   * Takes the given filter and layer and sets the layer with the given name to that filter,
   * if no layer with that name exists throw an IllegalArgument exception.
   *
   * @param filter the filter that is going to be set.
   * @param lName  the name of the layer which the filter will be applied to.
   */

  public void setFilter(Filter filter, String lName) {
    for (hw4.model.Layers.Layer layer : layers) {
      if (Objects.equals(layer.getName(), lName)) {
        layer.setFilter(filter);
        break;
      }
    }
  }

  /**
   * A setter for the current layer pointer that we are on which references the index we are on
   * for altering filter.
   *
   * @param newInt the int that the currLayer is being set to.
   */
  public void setCurrLayer(int newInt) {
    this.currLayer = newInt;
  }

  /**
   * Get the currLayer which is an int for the index value in the List of layers we are working on.
   *
   * @return an int to represent the index.
   */
  public int getCurrLayer() {
    return this.currLayer;
  }

  /**
   * Returns a list of layers where the rgb have been affected by the filters.
   *
   * @return a new List of Layers with affected rgb values in the pixel class.
   */
  public List<hw4.model.Layers.Layer> applyFilter() {
    List<hw4.model.Layers.Layer> tempLayers = new ArrayList<>();
    for (hw4.model.Layers.Layer layer : layers) { //for each layer
      tempLayers.add(layer.applyFilter(this.layers, this.giveLayerByName(layer.getName())));
    }

    return tempLayers;
  }

  /**
   * Gets the height of the project.
   *
   * @return an int for the height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the width of the project.
   *
   * @return an int for the width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * adds a new completely white background layer to the list of layers.
   *
   * @param name the name of the layer that is being added.
   * @throws IllegalArgumentException if there is no layer by the given name
   */
  public void addLayer(String name) throws IllegalArgumentException {
    for (Layer layer : layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("You cannot make two layers with the same name");
      }
    }
    Layer temp = new Layer(new Image(this.createBackgroundPixels(this.height,
            this.width, 0)),
            name,
            new NormalFilter());
    this.max = this.maxValueInt();
    this.layers.add(temp);
  }

  /**
   * Tells if there is any layer with the same name.
   *
   * @param name the name of the layer that is being checked.
   * @return a boolean true if the same name does exist or false if it doesn't.
   * We want it to return false so that a new layer can be created.
   */
  public boolean hasSameName(String name) {
    boolean flag = false;
    for (hw4.model.Layers.Layer layer : layers) {
      if (layer.getName().equals(name)) {
        flag = true;
      }
    }
    return flag;
  }

  /**
   * Loads an image using the path so that the user can load an image onto their model or layer.
   *
   * @param path the path we are getting a list of bytes from.
   * @return a new image that is an instance of the image translated from the file on the system.
   */
  public Image loadImage(String path) {
    FileInputStream inStream = null;
    BufferedImage bufImage;
    try {
      inStream = new FileInputStream(path);
    } catch (IOException e) {
      //It failed to load properly
    }

    if (path.contains(".ppm")) {
      List<List<Pixel>> pixels = null;
      Scanner br = new Scanner(inStream);
      //InputStreamReader isr = new InputStreamReader(inStream);
      //BufferedReader br = new BufferedReader(isr);


      // Read the header
      br.nextLine();
      String[] hw = br.nextLine().split(" ");
      int width = Integer.parseInt(hw[0]);
      int height = Integer.parseInt(hw[1]);
      int maxVal = Integer.parseInt(br.nextLine());

      // Turn the numbers into a pixel
      pixels = new ArrayList<List<Pixel>>(height * width);
      for (int i = 0; i < height; i++) {
        List<Pixel> row = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int r = br.nextInt();
          int g = br.nextInt();
          int b = br.nextInt();
          int[] rgba = {r, g, b, 255};
          row.add(j, new Pixel((rgba)));
        }

        pixels.add(i, row);


      }

      return new Image(pixels);
    } else {
      try {
        bufImage = ImageIO.read(inStream);
      } catch (IOException e) {
        throw new IllegalArgumentException("No file at the given location");
      }
      return this.bufImagetoImage(bufImage);
    }
  }

  /**
   * Turns a buffered image into a list of lists of pixels which it converts into our format of
   * an image.
   *
   * @param buf the buffered image which we are going to alter into our image format.
   * @return a new instance of the Image class which is a List of Lists of Pixels.
   */

  public hw4.model.Images.Image bufImagetoImage(BufferedImage buf) {
    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < buf.getHeight(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < buf.getWidth(); j++) {
        // bit shifting should be the fastest way to render the buffered image.
        int rgb = buf.getRGB(j, i);
        int r = (rgb >> 16) & 0xFF; //use the bit squeezing from buffered image in reverse, cast
        // to 255
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        int[] pixelRgb = new int[]{r, g, b, 255};
        row.add(new Pixel(pixelRgb));
      }
      pixels.add(row);
    }
    return new hw4.model.Images.Image(pixels);
  }

  /**
   * Loads a project from the system and converts it into projectModel format.
   *
   * @param path the string that is the path on the system to the project in a text file.
   * @return a new projectModel which is the project loaded from the text file.
   * @throws FileNotFoundException if there is no file at the given path.
   */
  public AbstractEditorModel loadProject(String path) throws FileNotFoundException {
    FileInputStream inStream = new FileInputStream(path);
    Scanner sc = new Scanner(inStream);
    StringBuilder br1 = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        br1.append(s + System.lineSeparator());
      }
    }
    Scanner br = new Scanner(br1.toString());
    //header
    br.nextLine(); //<-ignore first thing
    String[] hw = br.nextLine().split(" ");
    int width = Integer.parseInt(hw[0]);
    int height = Integer.parseInt(hw[1]);
    int maxVal = Integer.parseInt(br.nextLine());
    List<hw4.model.Layers.Layer> layers = new ArrayList<>();

    while (br.hasNext()) {

      String var1 = br.nextLine();
      if (var1.equals("") && !(br.hasNext())) {
        break;
      } else if (var1.equals("")) {
        var1 = br.nextLine();
      }
      //-----makes a new layer -------///
      String[] layerInfo = var1.split(" ");//br.nextLine().split(" ");
      String lName = layerInfo[0];
      String filter = layerInfo[1];
      Image tempI = new Image(this.createBackgroundPixels(0, 0, 0));
      //If it is an integer add it to the list, everytime it's not making a new layer?
      //---- Makes a new image
      List<List<Pixel>> pixels = new ArrayList<List<Pixel>>(height * width);
      for (int i = 0; i < height; i++) {
        List<Pixel> row = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int r = br.nextInt();
          int g = br.nextInt();
          int b = br.nextInt();
          br.nextInt();
          int[] rgba = {r, g, b, 255};
          row.add(j, new Pixel(rgba, maxVal));
        }

        pixels.add(i, row);
      }
      tempI = new Image(pixels);
      //--------

      hw4.model.Layers.Layer temp = new hw4.model.Layers.Layer(tempI, lName, this.giveFilter(filter));
      layers.add(temp);
      //br.nextLine();
      //-------------///
    }
    return new ProjectModel(layers, height, width);
  }

  /**
   * adds an image to the image field of the given layer.
   *
   * @param lName the layer that the image is being added to.
   * @param iPath the path in the system to the image that will be loaded and added.
   * @param offX  the horizontal offset of the image.
   * @param offY  the vertical offset of the image.
   * @throws IOException if the loading of an image fails.
   */
  public void addImageToLayer(String lName, String iPath, int offX, int offY)
          throws IOException {
    hw4.model.Images.Image tempImage = this.loadImage(iPath);
    int index = this.giveLayerByName(lName);
    hw4.model.Images.Image image = this.layers.get(index).getImage();
    hw4.model.Images.Image newImage = tempImage.compressImage(image);
    this.layers.get(this.giveLayerByName(lName)).replaceImage(newImage,
            offX, offY);
  }

  /**
   * Returns an image by getting the content from the filepath.
   *
   * @param path the path we are retrieving the contents from.
   * @return an image representation of the image stored at the given filepath
   */
  public hw4.model.Images.Image imageLoad(String path) {
    try {
      FileInputStream in = new FileInputStream(path);
      byte[] pixels = new byte[in.available()];
      in.read(pixels);
      String data = new String(pixels);
      in.close();
    } catch (IOException e) {
      //Do nothing because this shouldn't happen and is handled later.
    }

    return new Image(this.createBackgroundPixels(0, 0, 0));
  }

  /**
   * gives the index value that is that layers location in this List of layer or layers field.
   *
   * @param name the name that we are looking for.
   * @return an index value represented as an int in the layer.
   */
  public int giveLayerByName(String name) {
    hw4.model.Layers.Layer temp = null;
    int correctInt = 0;

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(name)) {
        temp = layers.get(i);
        correctInt = i;
      }
    }

    if (temp == null) {
      throw new IllegalArgumentException("There is no layer by that name");
    } else {
      return correctInt;
    }

  }

  /**
   * Returns a new instance of whatever filter the id is the same as the getName() by.
   *
   * @param id the string that is the users input for the filter I want.
   * @return a new Filter that is anything that inherits the filter method.
   * @throws IllegalArgumentException if there is no filter by the given name.
   */
  public Filter giveFilter(String id) throws IllegalArgumentException {
    System.out.println("here" + id);
    switch (id) {
      case "normal":
        return new NormalFilter();
      case "brighten-value":
        return new BrightenValue();
      case "brighten-intensity":
        return new BrightenIntensity();
      case "brighten-luma":
        return new BrightenLuma();
      case "darken-value":
        return new DarkenValue();
      case "darken-intensity":
        return new DarkenIntensity();
      case "darken-luma":
        return new DarkenLuma();
      case "red-component":
        return new RedComponent();
      case "green-component":
        return new GreenComponent();
      case "blue-component":
        return new BlueComponent();
      case "multiply":
        return new Multiply();
      case "difference":
        return new Difference();
      case "screen":
        return new Screen();
      default:
        throw new IllegalArgumentException("Not a valid filter");

    }
  }

  /**
   * An abstract method which is defined later but will essentially save the project to the given
   * file path on the system.
   *
   * @param path the path to the project that is being saved.
   */
  @Override
  public abstract void saveProjectFinal(String path);

  /**
   * Give the max value of the pixels.
   *
   * @return the max value of the pixels.
   */
  public String maxValue() {
    int inFuncHolder = 1;
    for (int i = 0; i < layers.size(); i++) { // for every layer
      int layerMax = layers.get(i).maxLayerValue(); //get the max value of the layer
      if (layerMax > inFuncHolder) {
        inFuncHolder = layerMax;
      } else {
        //Don't do anything
      }
    }
    return "" + inFuncHolder;
  }

  /**
   * Prints a string of the model in the final image format.
   *
   * @return a string that is the ppm format.
   */
  public String imageFormat() {
    String ppm =
            "" + "P3" + "\n" + this.getWidth() + " " + this.getHeight() + "\n" + this.maxValue()
                    + "\n";
    String base = "";
    List<hw4.model.Layers.Layer> tempLayers = this.applyFilter();
    //add all the layers of the applied list to the new list after being compressed
    List<hw4.model.Layers.Layer> updatedLayers = this.compressLayers(tempLayers);
    base += updatedLayers.get(updatedLayers.size() - 1).finalImageFormat();

    return ppm + base;
  }

  /**
   * Create a new list that has used the alpha values for each of the previous layers to compress.
   *
   * @param other a List of layers that we are going to be compressing.
   * @return a new list of layers that have all been compressed.
   */
  public List<hw4.model.Layers.Layer> compressLayers(List<hw4.model.Layers.Layer> other) {
    List<hw4.model.Layers.Layer> updatedLayers = new ArrayList<>();
    for (int i = 0; i < other.size(); i++) {
      if (i == 0) {
        //Do nothing because we are in the background layer
        updatedLayers.add(other.get(i));
      } else {
        hw4.model.Layers.Layer tempLayer = other.get(i);
        //layers      add      compressed one layer using another layer
        updatedLayers.add(tempLayer.compress(updatedLayers.get(i - 1)));
      }

    }
    return updatedLayers;
  }

  /**
   * Return the layers of the model so that we could get layers.
   *
   * @return a List of layers that is the layers of this model.
   */
  public List<hw4.model.Layers.Layer> getLayers() {
    return this.layers;
  }

  /**
   * Prints a string of the model in the collage layout format.
   *
   * @return a string to represent the image using the bits.
   */
  public abstract String collageContentFormat();

  /**
   * Prints an unfinished file to a file using the given file path & the collage format.
   *
   * @param s a string ot represent the location this file will be.
   * @throws IOException is thrown if the file cannot be written to or the path is incorrect.
   */
  public abstract void saveCollageFormat(String s) throws IOException;

  /**
   * Turns a project into a buffered image by compressing into a composite and then transferring
   * all the values and bit shifting.
   *
   * @return a new Buffered Image that can be painted on a gui.
   */
  public BufferedImage projectToImage() {
    // Image image = this.layers.get(this.layers.size() - 1).getImage();
    Image image = this.compressLayers(this.applyFilter()).get(this.layers.size() - 1).getImage();
    BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    // layer  layer at index    image   width
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int rgb = image.getRow(i).get(j).getRgba(0) << 16;
        rgb |= image.getRow(i).get(j).getRgba(1) << 8;
        rgb |= image.getRow(i).get(j).getRgba(2);
        img.setRGB(j, i, rgb);
        // img.setRGB(j, i, image.getRow(i).get(j).returnRGB());
      }
    }
    return img;
  }


  /**
   * Gets the layer by index value from the List of layers field of the model.
   *
   * @param index the index value we are trying to get the layer of.
   * @return a String which represents the name of the layer that we have retrieved.
   */
  public String getLayerByIndex(int index) {
    hw4.model.Layers.Layer layer = this.getLayers().get(index);
    return layer.getName();
  }

  /**
   * Add a new layer to the list of layers in the project.
   *
   * @param layer the layer that is being added including a name, size, and filter.
   */
  public void addLayerByLayer(Layer layer) {
    this.layers.add(layer);
  }

  /**
   * sets the height of the current model.
   * @param height the int that is going ot be used to set the height.
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Sets the width of the current grid to the given number.
   * @param width the wdith or the amount of rows of pixels.
   */
  public void setWidth(int width){
    this.width = width;
  }
  /**
   * Allows the user to set the models values to the new width and height as well as creates a
   * background layer essentially initializing a new model. Or triggering the initiliaztion of a
   * new model.
   */
  public void initProject(int height, int width) {
    this.setHeight(height);
    this.setWidth(width);
    this.layers = createBackgroundLayer(height, width, 255);
  }

}


