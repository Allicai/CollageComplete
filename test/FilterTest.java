import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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

import static org.junit.Assert.assertEquals;


/**
 * class for testing filters.
 */

public class FilterTest {

  // this class has a lot of fields in exchange for the tests being very easy to read.

  //--------------------------------------- all the RGB arrays ---------------------------------//
  int[] blue = {0, 0, 255, 255};
  int[] red = {255, 0, 0, 255};
  int[] green = {0, 255, 0, 255};
  int[] transparent = {200, 153, 152, 0};
  int[] purple = {120, 109, 222, 255};
  int[] weird = {213, 125, 53, 255};
  int[] purpleRed = {120, 0, 0, 255};
  int[] purpleGreen = {0, 109, 0, 255};
  int[] purpleBlue = {0, 0, 222, 255};
  int[] redBL = {255, 55, 55, 255};
  int[] greenBL = {183, 255, 183, 255};
  int[] blueBL = {19, 19, 255, 255};
  int[] purpleBL = {240, 229, 255, 255};
  int[] redBI = {255, 85, 85, 255};
  int[] blueBI = {85, 85, 255, 255};
  int[] greenBI = {85, 255, 85, 255};
  int[] purpleBI = {255, 255, 255, 255};
  int[] max = {255, 255, 255, 255};
  int[] reddL = {200, 0, 0, 255};
  int[] greenDL = {0, 72, 0, 255};
  int[] blueDL = {0, 0, 236, 255};
  int[] purpleDL = {0, 0, 102, 255};
  int[] redDI = {170, 0, 0, 255};
  int[] blueDI = {0, 0, 170, 255};
  int[] greenDI = {0, 170, 0, 255};
  int[] purpleDI = {0, 0, 71, 255};
  int[] min = {0, 0, 0, 255};
  int[] m1 = {200, 0, 0, 255};
  int[] m2 = {0, 72, 0, 255};
  int[] m3 = {0, 0, 236, 255};
  int[] m4 = {0, 0, 102, 255};
  //----------------------------------------- all the Pixels -----------------------------------//

  // base pixels
  Pixel pixelGreen = new Pixel(green);
  Pixel pixelRed = new Pixel(red);
  Pixel pixelBlue = new Pixel(blue);
  Pixel pixelTransparent = new Pixel(transparent);
  Pixel pixelPurple = new Pixel(purple);
  Pixel pixelWeird = new Pixel(weird);
  Pixel pixelBlack = new Pixel(min);

  // purples unique comp pixels
  Pixel purpleRedComp = new Pixel(purpleRed);
  Pixel purpleGreenComp = new Pixel(purpleGreen);
  Pixel purpleBlueComp = new Pixel(purpleBlue);

  // brighten/darken effect pixels for every color
  Pixel pixelRedBL = new Pixel(redBL);
  Pixel pixelRedBV = new Pixel(max);
  Pixel pixelRedBI = new Pixel(redBI);
  Pixel pixelRedDL = new Pixel(reddL);
  Pixel pixelRedDV = new Pixel(min);
  Pixel pixelRedDI = new Pixel(redDI);

  Pixel pixelGreenBL = new Pixel(greenBL);
  Pixel pixelGreenBV = new Pixel(max);
  Pixel pixelGreenBI = new Pixel(greenBI);
  Pixel pixelGreenDL = new Pixel(greenDL);
  Pixel pixelGreenDV = new Pixel(min);
  Pixel pixelGreenDI = new Pixel(greenDI);

  Pixel pixelBlueBL = new Pixel(blueBL);
  Pixel pixelBlueBV = new Pixel(max);
  Pixel pixelBlueBI = new Pixel(blueBI);
  Pixel pixelBluedL = new Pixel(blueDL);
  Pixel pixelBluedV = new Pixel(min);
  Pixel pixelBlueDI = new Pixel(blueDI);

  Pixel pixelPurpleBL = new Pixel(purpleBL);
  Pixel pixelPurpleBV = new Pixel(max);
  Pixel pixelPurpleBI = new Pixel(purpleBI);
  Pixel pixelPurpleDL = new Pixel(purpleDL);
  Pixel pixelPurpleDV = new Pixel(min);
  Pixel pixelPurpleDI = new Pixel(purpleDI);
  Pixel pixelM1 = new Pixel(m1);
  Pixel pixelM2 = new Pixel(m2);
  Pixel pixelM3 = new Pixel(m3);
  Pixel pixelM4 = new Pixel(m4);


  //-------------------------------------- the Filters -------------------------------------------//
  Filter blueComp;
  Filter redComp;
  Filter greenComp;
  Filter brightenIntensity;
  Filter brightenLuma;
  Filter brightenValue;
  Filter darkenIntensity;
  Filter darkenValue;
  Filter darkenLuma;
  Filter multiply;
  Filter screen;
  Filter difference;
  Filter normal;

  //------------------------------------Rows of Pixels--------------------------------------------//
  List<Pixel> baseRow1;
  List<Pixel> baseRow2;
  List<Pixel> row1dL;
  List<Pixel> row2dL;
  List<Pixel> row1dV;
  List<Pixel> row2dV;
  List<Pixel> row1DI;
  List<Pixel> row2DI;
  List<Pixel> row1bI;
  List<Pixel> row2bI;
  List<Pixel> row1bV;
  List<Pixel> row2bV;
  List<Pixel> row1BL;
  List<Pixel> row2BL;
  List<Pixel> row1RC;
  List<Pixel> row2RC;
  List<Pixel> row1GC;
  List<Pixel> row2GC;
  List<Pixel> row1BC;
  List<Pixel> row2BC;
  List<Pixel> transparentRow;
  List<Pixel> purpleRow;
  List<Pixel> mR1;
  List<Pixel> mR2;
  List<Pixel> weirdRow;

  //----------------------------------List of Lists of Pixels-------------------------------------//
  List<List<Pixel>> transparentRows;
  List<List<Pixel>> rowsDL;
  List<List<Pixel>> rowsDV;
  List<List<Pixel>> rowsDI;
  List<List<Pixel>> rowsBL;
  List<List<Pixel>> rowsBV;
  List<List<Pixel>> rowsBI;
  List<List<Pixel>> rowsRC;
  List<List<Pixel>> rowsGC;
  List<List<Pixel>> rowsBC;
  List<List<Pixel>> baseRows;
  List<List<Pixel>> purpleRows;
  List<List<Pixel>> mRS;
  List<List<Pixel>> weirdRows;

  //-------------------------------------------- Images ------------------------------------------//
  Image baseImage;
  Image transparentImage;
  Image dLImage;
  Image dVImage;
  Image dIImage;
  Image bCImage;
  Image rCImage;
  Image gCImage;
  Image bLImage;
  Image bVImage;
  Image bIImage;
  Image purpleImage;
  Image mRImage;
  Image weirdImage;

  //------------------------------------------ Layers --------------------------------------------//
  String name = "original";
  Layer original;
  Layer original2;
  Layer rC;
  Layer bC;
  Layer gC;
  Layer bI;
  Layer bV;
  Layer bL;
  Layer dI;
  Layer dL;
  Layer dV;
  Layer transparentL;
  Layer transparentC;
  Layer purpleL;
  Layer mR;
  Layer weirdL;
  List<Layer> noLayers = new ArrayList<>();
  int a = 3;


  /**
   * method to initialize variables for testing.
   */
  private void init() { // method to initialize all the fields for this test class
    // -------------------------------- defining the rows for the images -------------------------//

    // base image is 2x2 so all results will also be 2x2
    // each image will show the results of the filter on a red, green, blue, and purple pixel
    // and also be run on a transparent image to show it having no effect
    baseRow1 = new ArrayList<>();
    baseRow1.add(pixelRed);
    baseRow1.add(pixelGreen);
    baseRow2 = new ArrayList<>();
    baseRow2.add(pixelBlue);
    baseRow2.add(pixelPurple);

    // dL = Darken Luma
    row1dL = new ArrayList<>();
    row1dL.add(pixelRedDL);
    row1dL.add(pixelGreenDL);
    row2dL = new ArrayList<>();
    row2dL.add(pixelBluedL);
    row2dL.add(pixelPurpleDL);

    // dV = Darken Value
    row1dV = new ArrayList<>();
    row1dV.add(pixelRedDV);
    row1dV.add(pixelGreenDV);
    row2dV = new ArrayList<>();
    row2dV.add(pixelBluedV);
    row2dV.add(pixelPurpleDV);

    // etc.
    row1DI = new ArrayList<>();
    row1DI.add(pixelRedDI);
    row1DI.add(pixelGreenDI);
    row2DI = new ArrayList<>();
    row2DI.add(pixelBlueDI);
    row2DI.add(pixelPurpleDI);


    row1BL = new ArrayList<>();
    row1BL.add(pixelRedBL);
    row1BL.add(pixelGreenBL);
    row2BL = new ArrayList<>();
    row2BL.add(pixelBlueBL);
    row2BL.add(pixelPurpleBL);


    row1bV = new ArrayList<>();
    row1bV.add(pixelRedBV);
    row1bV.add(pixelGreenBV);
    row2bV = new ArrayList<>();
    row2bV.add(pixelBlueBV);
    row2bV.add(pixelPurpleBV);

    row1bI = new ArrayList<>();
    row1bI.add(pixelRedBI);
    row1bI.add(pixelGreenBI);
    row2bI = new ArrayList<>();
    row2bI.add(pixelBlueBI);
    row2bI.add(pixelPurpleBI);

    row1RC = new ArrayList<>();
    row1RC.add(pixelRed);
    row1RC.add(pixelBlack);
    row2RC = new ArrayList<>();
    row2RC.add(pixelBlack);
    row2RC.add(purpleRedComp);

    row1BC = new ArrayList<>();
    row1BC.add(pixelBlack);
    row1BC.add(pixelBlack);
    row2BC = new ArrayList<>();
    row2BC.add(pixelBlue);
    row2BC.add(purpleBlueComp);

    row1GC = new ArrayList<>();
    row1GC.add(pixelBlack);
    row1GC.add(pixelGreen);
    row2GC = new ArrayList<>();
    row2GC.add(pixelBlack);
    row2GC.add(purpleGreenComp);

    transparentRow = new ArrayList<>();
    transparentRow.add(pixelTransparent);
    transparentRow.add(pixelTransparent);

    purpleRow = new ArrayList<>();
    purpleRow.add(pixelPurple);
    purpleRow.add(pixelPurple);

    weirdRow = new ArrayList<>();
    weirdRow.add(pixelWeird);
    weirdRow.add(pixelWeird);

    mR1 = new ArrayList<>();
    mR1.add(pixelM1);
    mR1.add(pixelM2);
    mR2 = new ArrayList<>();
    mR2.add(pixelM3);
    mR2.add(pixelM4);

    //------------------------------list of lists of pixels (for images) ------------------------//
    baseRows = new ArrayList<>();
    baseRows.add(baseRow1);
    baseRows.add(baseRow2);

    rowsDL = new ArrayList<>();
    rowsDL.add(row1dL);
    rowsDL.add(row2dL);

    rowsDV = new ArrayList<>();
    rowsDV.add(row1dV);
    rowsDV.add(row2dV);

    rowsDI = new ArrayList<>();
    rowsDI.add(row1DI);
    rowsDI.add(row2DI);

    rowsBL = new ArrayList<>();
    rowsBL.add(row1BL);
    rowsBL.add(row2BL);

    rowsBV = new ArrayList<>();
    rowsBV.add(row1bV);
    rowsBV.add(row2bV);

    rowsBI = new ArrayList<>();
    rowsBI.add(row1bI);
    rowsBI.add(row2bI);

    rowsRC = new ArrayList<>();
    rowsRC.add(row1RC);
    rowsRC.add(row2RC);

    rowsGC = new ArrayList<>();
    rowsGC.add(row1GC);
    rowsGC.add(row2GC);

    rowsBC = new ArrayList<>();
    rowsBC.add(row1BC);
    rowsBC.add(row2BC);

    transparentRows = new ArrayList<>();
    transparentRows.add(transparentRow);
    transparentRows.add(transparentRow);

    purpleRows = new ArrayList<>();
    purpleRows.add(purpleRow);
    purpleRows.add(purpleRow);

    mRS = new ArrayList<>();
    mRS.add(mR1);
    mRS.add(mR2);

    weirdRows = new ArrayList<>();
    weirdRows.add(weirdRow);
    weirdRows.add(weirdRow);

    //------------------------------- defining the images ----------------------------------------//

    baseImage = new Image(baseRows);
    rCImage = new Image(rowsRC);
    gCImage = new Image(rowsGC);
    bCImage = new Image(rowsBC);
    dLImage = new Image(rowsDL);
    dVImage = new Image(rowsDV);
    dIImage = new Image(rowsDI);
    bLImage = new Image(rowsBL);
    bVImage = new Image(rowsBV);
    bIImage = new Image(rowsBI);
    transparentImage = new Image(transparentRows);
    purpleImage = new Image(purpleRows);
    mRImage = new Image(mRS);
    weirdImage = new Image(weirdRows);

    //-------------------------the layer being tested on + expected resulting layers--------------//

    this.original = new Layer(baseImage, name);
    original2 = new Layer(original.getImage(), original.getName());
    rC = new Layer(rCImage, original.getName(), redComp);
    bC = new Layer(bCImage, original.getName(), blueComp);
    gC = new Layer(gCImage, original.getName(), greenComp);
    dL = new Layer(dLImage, original.getName(), darkenLuma);
    dI = new Layer(dIImage, original.getName(), darkenIntensity);
    dV = new Layer(dVImage, original.getName(), darkenValue);
    bI = new Layer(bIImage, original.getName(), brightenIntensity);
    bV = new Layer(bVImage, original.getName(), brightenValue);
    bL = new Layer(bLImage, original.getName(), brightenLuma);
    transparentL = new Layer(transparentImage, original.getName());
    transparentC = new Layer(transparentImage, original.getName());
    purpleL = new Layer(purpleImage, original.getName());
    mR = new Layer(mRImage, original.getName());
    weirdL = new Layer(weirdImage, original.getName());

    //-------------------------------the filters we are testing-----------------------------------//
    blueComp = new BlueComponent();
    redComp = new RedComponent();
    greenComp = new GreenComponent();
    darkenIntensity = new DarkenIntensity();
    darkenLuma = new DarkenLuma();
    darkenValue = new DarkenValue();
    brightenIntensity = new BrightenIntensity();
    brightenLuma = new BrightenLuma();
    brightenValue = new BrightenValue();
    difference = new Difference();
    multiply = new Multiply();
    screen = new Screen();
    normal = new NormalFilter();

    //------------------------------------- lists of layers --------------------------------------//

    // only our blending filters need layers behind them to work
    // so here's an empty list for the other filters:
    noLayers = new ArrayList<>();
  }

  @Test
  public void testRC() { // test red component
    this.init();
    redComp.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), rC.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), this.RC.layerContentFormat());
    this.init();
    redComp.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testBC() { // test blue component
    this.init();
    blueComp.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), bC.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), BC.layerContentFormat()); <- null pointer?
    this.init();
    blueComp.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testGC() { // test green component
    this.init();
    greenComp.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), gC.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), GC.layerContentFormat()); <- null pointer?
    this.init();
    greenComp.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testDL() { // test darken luma
    this.init();
    darkenLuma.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), dL.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?
    this.init();
    darkenLuma.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testDV() { // test darken value
    this.init();
    darkenValue.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), dV.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?
    this.init();
    darkenValue.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testDI() { // test darken intensity
    this.init();
    darkenIntensity.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), dI.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?
    this.init();
    darkenIntensity.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testBL() { // test brighten luma
    this.init();
    brightenLuma.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), bL.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?
    this.init();
    brightenLuma.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testBV() { // test brighten value
    this.init();
    brightenValue.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), bV.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?
    this.init();
    brightenValue.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testBI() { // test brighten intensity
    this.init();
    brightenIntensity.apply(original, noLayers);
    assertEquals(original.getImage().imageContentFormatA(), bI.getImage().imageContentFormatA());
    // assertEquals(original.layerContentFormat(), RC.layerContentFormat()); <- null pointer?

    this.init();
    brightenIntensity.apply(transparentL, noLayers);
    assertEquals(transparentL.getImage().imageContentFormatA(),
            transparentC.getImage().imageContentFormatA());
  }

  @Test
  public void testMultiply() { // test multiply
    this.init();
    List<Layer> layers = new ArrayList<>();
    layers.add(purpleL);
    layers.add(dL);

    multiply.apply(original, layers);
    System.out.println(original.getImage().imageContentFormatA());
    assertEquals(a, 3);
  }

  @Test
  public void testScreen() { // test screen
    this.init();
    List<Layer> layers = new ArrayList<>();
    layers.add(purpleL);
    layers.add(dL);
    System.out.println(weirdL.getImage().imageContentFormatA() + "\n\n\n");
    System.out.println("layers size: " + layers.size());
    screen.apply(weirdL, layers);
    weirdL.setFilter(screen);
    System.out.println(weirdL.layerContentFormat());
    assertEquals(a, 3);
  }

  @Test
  public void testDifference() { // test difference
    this.init();
    List<Layer> layers = new ArrayList<>();
    layers.add(dL);
    System.out.print(purpleL.layerContentFormat());
    layers.add(purpleL);
    difference.apply(weirdL, layers);
    System.out.println("\n\nresult: \n" + weirdL.layerContentFormat());
    assertEquals(a, 3);
  }

}
