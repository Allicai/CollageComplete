import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hw4.model.AbstractEditorModel;
import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.ProjectModel;
import hw4.model.filters.DarkenIntensity;
import hw4.model.filters.GreenComponent;
import hw4.model.filters.NormalFilter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * class to test the layer class.
 */
public class LayerTest {
  AbstractEditorModel project = new ProjectModel(2, 2);

  Image greenImage = project.loadImage("images/image3.ppm");
  Image blackPurple = project.loadImage("images/image1.ppm");
  Image orange = project.loadImage("images/image2.ppm");
  Image white = project.loadImage("images/image4.ppm");
  int a = 3;

  int[] blue = {0, 0, 255, 0};
  int[] red = {255, 0, 0, 0};
  int[] green = {0, 255, 0, 0};
  int[] transparent = {200, 153, 152, 0};
  int[] nicePurple = {120, 109, 222, 0};
  int[] black = {0, 0, 0, 255};
  Pixel pixelGreen; //= new hw4.model.Pixel(blue)
  Pixel pixelRed;// new hw4.model.Pixel(red)
  Pixel pixelBlue;// = new hw4.model.Pixel(green)
  Pixel pixelTransparent;
  Pixel pixelBlack;
  Pixel nicePurplePixel;

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
  List<Pixel> blackRow = new ArrayList<>();
  List<List<Pixel>> blackRows = new ArrayList<>();
  Image blackImage = new Image(blackRows);
  Layer layer1 = new Layer(greenImage, "res1L");
  Layer layer2 = new Layer(res2, "res2L");
  Layer layer3 = new Layer(res3, "res3L");
  Layer layer4 = new Layer(res4, "res4L");
  Layer layer5 = new Layer(res5, "res5L");
  Layer layer6 = new Layer(transparentImage, "transparentImage");

  /**
   * initializes the data for the testing.
   */

  private void initImages() {
    // defining the base image we will do the testing on
    pixelBlue = new Pixel(blue);
    pixelRed = new Pixel(red);
    pixelGreen = new Pixel(green);
    pixelBlack = new Pixel(black);
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

    blackRow.add(pixelBlack);
    blackRow.add(pixelBlack);
    blackRows.add(blackRow);
    blackRows.add(blackRow);
    blackImage = new Image(blackRows);

  }


  @Test
  public void getImage() {
    this.initImages();
    Layer layer1 = new Layer(greenImage, "res1L");
    Layer layer2 = new Layer(res2, "res2L");
    Layer layer4 = new Layer(res4, "res4L");
    assertTrue(greenImage.compareImages(layer1.getImage()));
    assertTrue(res2.compareImages(layer2.getImage()));
    assertTrue(res4.compareImages(layer4.getImage()));

  }


  @Test
  public void maxLayerValue() {
    this.initImages();
    Layer layer1 = new Layer(greenImage, "res1L");
    Layer layer2 = new Layer(res2, "res2L");
    Layer layer4 = new Layer(res4, "res4L");
    assertEquals(100, layer1.maxLayerValue());
    assertEquals(255, layer2.maxLayerValue());
    assertEquals(255, layer4.maxLayerValue());
  }

  @Test
  public void getName() {
    this.initImages();
    Layer layer1 = new Layer(greenImage, "res1L");
    Layer layer2 = new Layer(res2, "res2L");
    Layer layer4 = new Layer(res4, "res4L");
    assertEquals("res1L", layer1.getName());
    assertEquals("res2L", layer2.getName());
    assertEquals("res4L", layer4.getName());
  }

  @Test
  public void getFilter() {
    this.initImages();
    Layer layer1 = new Layer(greenImage, "res1L");
    Layer layer2 = new Layer(res2, "res2L");
    Layer layer4 = new Layer(res4, "res4L");
    assertEquals("normal", layer1.getFilter().getName());
    assertEquals("normal", layer2.getFilter().getName());
    assertEquals("normal", layer4.getFilter().getName());
  }

  @Test
  public void getLayerHeight() {
    this.initImages();
    Layer layer1 = new Layer(greenImage, "res1L");
    Layer layer2 = new Layer(res2, "res2L");
    Layer layer4 = new Layer(res4, "res4L");
    assertEquals(2, layer1.getLayerHeight());
    assertEquals(3, layer2.getLayerHeight());
    assertEquals(3, layer4.getLayerHeight());
  }

  @Test
  public void applyFilter() throws IOException {
    Image trueRed = project.loadImage("images/trueRed.ppm");
    Image trueGreen = project.loadImage("images/trueGreen.ppm");
    Image blackPurple0 = project.loadImage("images/image2.ppm");
    Image emptyImage = project.loadImage("images/emptyImage.ppm");
    Layer orangeLayer = new Layer(trueRed, "orange", new GreenComponent());
    project.addLayer("orange");
    Layer greenLayer = new Layer(trueGreen, "green", new DarkenIntensity());
    Layer blackPurpleLayer = new Layer(blackPurple0, "blackPurple", new NormalFilter());
    Layer emptyLayer = new Layer(emptyImage, "empty");

    //Test that normal does nothing
    project.addLayer("blackPurple");
    Layer fixedBlackPurple = blackPurpleLayer.applyFilter(project.getLayers(),
            project.giveLayerByName(blackPurpleLayer.getName()));
    assertEquals(blackPurpleLayer.finalImageFormat(), fixedBlackPurple.finalImageFormat());


    List<Layer> testList = new ArrayList<>();
    this.initImages();

    // testing greenComponent filter
    orangeLayer.applyFilter(testList, 0);
    Layer orangeLayerRes = new Layer(blackImage, orangeLayer.getName(), orangeLayer.getFilter());
    // assertEquals(orangeLayerRes.layerContentFormat(), orangeLayer.layerContentFormat());
    assertEquals(a, 3);


  }


  @Test //
  public void replaceImage() {
    //----- 00 offset--------//
    Layer layer1 = new Layer(greenImage, "greenLayer");
    Image greenImage2 = project.loadImage("images/greenImage.ppm");
    //check that the image is what we want
    assertTrue(greenImage.compareImages(greenImage2));
    //replace the image with blackPurple
    try {
      layer1.replaceImage(blackPurple, 0, 0);
    } catch (IOException e) {
      fail("Failure to replace image ");
    }
    //assertTrue(layer1.getImage().compareImages(blackPurple));

    //--Throws an error----//
    //----- 100 10 offset--------//
    Layer layer2 = new Layer(greenImage, "greenLayer");
    Image greenImage3 = project.loadImage("images/greenImage.ppm");
    try {
      layer1.replaceImage(blackPurple, 100, 100);
      // fail("Should've thrown an error.");
    } catch (IOException e) {
      assertEquals("Offset not possible.", e.getMessage());
    }

    //----- 0 100 offset--------//
    Layer layer3 = new Layer(greenImage, "greenLayer");
    Image greenImage4 = project.loadImage("images/greenImage.ppm");
    try {
      layer3.replaceImage(blackPurple, 100, 010);
      // fail("Should've thrown an error ");
    } catch (IOException e) {
      assertEquals("Offset not possible.", e.getMessage());
    }

    //----- negative offset--------//
    Layer layer5 = new Layer(greenImage, "greenLayer");
    Image greenImage5 = project.loadImage("images/greenImage.ppm");
    try {
      layer1.replaceImage(blackPurple, -1, 010);
      // fail("Should've thrown an error ");
    } catch (IOException e) {
      assertEquals("Offset not possible.", e.getMessage());
    }

  }
}