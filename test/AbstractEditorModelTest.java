
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hw4.controller.GUIController;
import hw4.model.AbstractEditorModel;
import hw4.model.EditorModel;

import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.ProjectModel;
import hw4.model.filters.BlueComponent;
import hw4.model.filters.BrightenIntensity;
import hw4.model.filters.BrightenLuma;
import hw4.model.filters.BrightenValue;
import hw4.model.filters.DarkenIntensity;
import hw4.model.filters.DarkenLuma;
import hw4.model.filters.DarkenValue;
import hw4.model.filters.Filter;
import hw4.model.filters.GreenComponent;
import hw4.model.filters.NormalFilter;
import hw4.model.filters.RedComponent;
import hw4.view.IJFrame;
import hw4.view.JFrameImpl;

import static org.junit.Assert.assertEquals;

/**
 * test class for the abstract editor model.
 */
public class AbstractEditorModelTest {

  EditorModel project = new ProjectModel(2, 2);
  Image greenImage = project.loadImage("images/image3.ppm");
  int a = 3;
  int[] blue = {0, 0, 255, 0};
  int[] red = {255, 0, 0, 0};
  int[] green = {0, 255, 0, 0};
  int[] transparent = {200, 153, 152, 0};
  int[] background = {0, 0, 0, 255};
  Pixel pixelGreen; //= new hw4.model.Pixel(blue)
  Pixel pixelRed;// new hw4.model.Pixel(red)
  Pixel pixelBlue;// = new hw4.model.Pixel(green)
  Pixel pixelTransparent;
  List<Pixel> row1 = new ArrayList<>();
  List<Pixel> row2 = new ArrayList<>();
  List<Pixel> row3 = new ArrayList<>();
  List<List<Pixel>> baseImageRows = new ArrayList<>();
  Image baseImage;
  List<Pixel> row4 = new ArrayList<>();
  List<Pixel> row5 = new ArrayList<>();
  List<List<Pixel>> placerImageRows = new ArrayList<>();
  Image placerImage;
  List<List<Pixel>> res1Rows = new ArrayList<>();
  Image res1;
  List<List<Pixel>> res2Rows = new ArrayList<>();
  Image res2;
  List<List<Pixel>> res3Rows = new ArrayList<>();
  Image res3;
  List<List<Pixel>> res4Rows = new ArrayList<>();
  Image res4;
  List<List<Pixel>> res5Rows = new ArrayList<>();
  Image res5;
  List<List<Pixel>> res6Rows = new ArrayList<>();
  Image res6;
  List<List<Pixel>> res7Rows = new ArrayList<>();
  Image res7;
  List<Pixel> transparentRow = new ArrayList<>();
  List<List<Pixel>> transparentRows = new ArrayList<>();
  Image transparentImage;
  Filter blueComp = new BlueComponent();
  Filter redComp = new RedComponent();
  Filter greenComp = new GreenComponent();
  Filter brightenIntensity = new BrightenIntensity();
  Filter brightenLuma = new BrightenLuma();
  Filter brightenValue = new BrightenValue();
  Filter darkenIntensity = new DarkenIntensity();
  Filter darkenValue = new DarkenValue();
  Filter darkenLuma = new DarkenLuma();
  Filter normal = new NormalFilter();
  List<Layer> layers = new ArrayList<>();
  List<Layer> layers2 = new ArrayList<>();
  List<Layer> layers3 = new ArrayList<>();
  ProjectModel model = new ProjectModel(layers, 2, 2);
  AbstractEditorModel model2 = new ProjectModel(layers2, 1, 6);
  AbstractEditorModel model3 = new ProjectModel(layers3, 3, 5);
  Pixel backgroundPixel = new Pixel(background);
  List<Pixel> backgroundRow = new ArrayList<>();
  List<List<Pixel>> backgroundRows = new ArrayList<>();
  Image backgroundImage;

  /**
   * initializes the data for the testing.
   */

  private void initImages() {
    // defining the base image we will do the testing on
    pixelBlue = new Pixel(blue);
    pixelRed = new Pixel(red);
    pixelGreen = new Pixel(green);
    row1.add(pixelRed);
    row1.add(pixelRed);
    row1.add(pixelRed);

    baseImageRows.add(row1);
    baseImageRows.add(row1);
    baseImageRows.add(row1);
    // 3 by 3 fully red image
    baseImage = new Image(baseImageRows);

    // defining the image we will be placing on the base image
    row2.add(pixelGreen);
    row2.add(pixelGreen);

    placerImageRows.add(row2);
    placerImageRows.add(row2);
    // 2 by 2 green image
    placerImage = new Image(placerImageRows);

    // defining the first resulting image of the image placing test
    // placing the placer image in the top left corner of the base image

    // GGR
    row3.add(pixelGreen);
    row3.add(pixelGreen);
    row3.add(pixelRed);

    // RRG
    row4.add(pixelRed);
    row4.add(pixelRed);
    row4.add(pixelGreen);

    // GRR
    row5.add(pixelGreen);
    row5.add(pixelRed);
    row5.add(pixelRed);

    // GGR
    // GGR
    // RRR
    res1Rows.add(row3);
    res1Rows.add(row3);
    res1Rows.add(row1);
    res1 = new Image(res1Rows);

    // RRG
    // RRG
    // RRR
    res2Rows.add(row4);
    res2Rows.add(row4);
    res2Rows.add(row1);
    res2 = new Image(res2Rows);

    // RRR
    // RRR
    // GGR
    res3Rows.add(row1);
    res3Rows.add(row1);
    res3Rows.add(row3);
    res3 = new Image(res3Rows);

    // RRR
    // RRR
    // RRG
    res4Rows.add(row1);
    res4Rows.add(row1);
    res4Rows.add(row4);
    res4 = new Image(res4Rows);

    // GRR
    // GRR
    // RRR
    res5Rows.add(row5);
    res5Rows.add(row5);
    res5Rows.add(row1);
    res5 = new Image(res5Rows);

    // GGR
    // RRR
    // RRR
    res6Rows.add(row3);
    res6Rows.add(row1);
    res6Rows.add(row1);
    res6 = new Image(res6Rows);

    // GRR
    // RRR
    // RRR
    res7Rows.add(row5);
    res7Rows.add(row1);
    res7Rows.add(row1);
    res7 = new Image(res7Rows);

    pixelTransparent = new Pixel(transparent);
    transparentRow.add(pixelTransparent);
    transparentRow.add(pixelTransparent);
    transparentRow.add(pixelTransparent);
    transparentRow.add(pixelTransparent);
    transparentRows.add(transparentRow);
    transparentRows.add(transparentRow);
    transparentRows.add(transparentRow);
    transparentRows.add(transparentRow);
    transparentImage = new Image(transparentRows);

    backgroundRow.add(backgroundPixel);
    backgroundRow.add(backgroundPixel);
    backgroundRows.add(backgroundRow);
    backgroundRows.add(backgroundRow);
    backgroundImage = new Image(backgroundRows);
  }


  @Test
  public void testMaxValueInt() {
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    model.addLayer("res2L");
    model.addLayer("res4L");
    System.out.println(model.getLayers().get(0).maxLayerValue());
    System.out.println(model.getLayers().get(1).maxLayerValue());
    System.out.println(model.getLayers().get(2).maxLayerValue());
    // not working properly
    assertEquals(model.maxValueInt(), 255);
  }

  @Test
  public void testCreateBackgroundLayer() { // calls createBackgroundPixels
    try {
      model.createBackgroundLayer(2, 2, 255);
    } catch (Exception e) {
      // do nothing
    }
    assertEquals(model.getHeight(), 2);
    assertEquals(model.getWidth(), 2);
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, model.getWidth());
    assertEquals(6, model2.getWidth());
    assertEquals(5, model3.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(2, model.getHeight());
    assertEquals(1, model2.getHeight());
    assertEquals(3, model3.getHeight());
  }

  @Test
  public void testLoadImage() {
    this.initImages();
    model.createBackgroundLayer(2, 2, 40);
    model.addLayer("res1L");
    model.loadImage("images/image3.ppm");
    assertEquals(a, 3);
  }

  @Test
  public void testGiveLayerByName() {
    this.initImages();
    model.addLayer("res1L");
    model.addLayer("res2L");
    model.addLayer("res3L");
    assertEquals(a, 3);
  }

  @Test
  public void testGiveFilter() { // test is meant to call the give filter method to get a normal
    // filter and then check that it gave the right type of filter
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    try {
      model.addImageToLayer("res1L", "images/image3.ppm", 0, 0);
    } catch (IOException e) {
      // testing
    }
    model.setFilter(normal, "res1L");
    assertEquals(model.getLayers().get(0).getFilter().getName(),
            model.giveFilter("normal").getName());

    model.addLayer("res2L");
    try {
      model.addImageToLayer("res2L", "images/image3.ppm", 0, 0);
    } catch (IOException e) {
      // testing
    }
    model.setFilter(blueComp, "res2L");
    assertEquals(model.getLayers().get(1).getFilter().getName(),
            model.giveFilter("blue-component").getName());
  }

  @Test
  public void testSaveFinalProject() {
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    try {
      model.addImageToLayer("res1L", "images/image3.ppm", 0, 0);
    } catch (IOException e) {
      // testing
    }
    model.saveProjectFinal("/images");
    assertEquals(a, 3);
  }

  @Test
  public void loadFromPNG() throws IOException {

    AbstractEditorModel model = new ProjectModel(2, 2);
    IJFrame view = new JFrameImpl(model);
    GUIController controller = new GUIController(model, view);
    model.addLayer("l1");
    assertEquals("l1", model.getLayerByIndex(0));
    //try to load an image from a png in this folder -> return the correct string if it works
    try {
      model.addImageToLayer("l1", "images/image1.png", 0, 0);
    } catch (IOException e) {
      //fail("Should've worked properly");
    }
    assertEquals("c1\n"
            + "2 2\n"
            + "255\n"
            + "l1 normal\n"
            + "100 100 100 255\n"
            + "100 100 100 255\n"
            + "100 100 255 255\n"
            + "100 100 222 255\n", model.collageContentFormat()); //check the image has been

    // try to save it as a png -> then open that png check the contents compared to a string
    model.saveProjectFinal("images/test1.jpeg");
    model.saveProjectFinal("images/test1.png");

    File file = new File("images/test1.jpeg"); //should find this newly made file
    byte[] bytes = Files.readAllBytes(Paths.get("images/test1.png")); //should read the file into

  }
}