import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hw4.model.Images.Image;
import hw4.model.Pixels.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * class to test the image class.
 */
public class ImageTest {

  int[] blue = {0, 0, 255, 0};
  int[] red = {255, 0, 0, 0};
  int[] green = {0, 255, 0, 0};
  int[] transparent = {200, 153, 152, 0};
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
  }


  @Test
  public void testGetWidth() {
    this.initImages();
    assertEquals(baseImage.getWidth(), 3);
    assertEquals(placerImage.getWidth(), 2);
    assertEquals(transparentImage.getWidth(), 4);
  }

  @Test
  public void testGetHeight() {
    this.initImages();
    assertEquals(baseImage.getHeight(), 3);
    assertEquals(placerImage.getHeight(), 2);
    assertEquals(transparentImage.getHeight(), 4);
  }

  @Test
  public void testCompareImages() {
    this.initImages();
    assertTrue(baseImage.compareImages(baseImage));
    assertTrue(res6.compareImages(res6));
    assertFalse(baseImage.compareImages(res1));
    assertFalse(transparentImage.compareImages(res2));
  }

  @Test
  public void testReplacePixels() throws IOException {
    this.initImages();

    // this method changes the baseImage given, but when it is called on a layer the method is
    // applied to a copy of the layer, not the layer itself

    baseImage.replacePixels(placerImage, 0, 0);
    assertTrue(baseImage.compareImages(res1));

    this.initImages();
    baseImage.replacePixels(placerImage, 2, 0);
    assertTrue(baseImage.compareImages(res2));

    this.initImages();
    baseImage.replacePixels(placerImage, 0, 2);
    assertTrue(baseImage.compareImages(res3));

    this.initImages();
    baseImage.replacePixels(placerImage, 2, 2);
    assertTrue(baseImage.compareImages(res4));

    baseImage.replacePixels(placerImage, -1, 0);
    assertTrue(baseImage.compareImages(res5));

    baseImage.replacePixels(placerImage, 0, -1);
    assertTrue(baseImage.compareImages(res6));

    baseImage.replacePixels(placerImage, -1, -1);
    assertTrue(baseImage.compareImages(res7));

  }

  @Test
  public void testMaxImageValue() {
    this.initImages();
    assertEquals(baseImage.maxImageValue(), 255);
    assertEquals(placerImage.maxImageValue(), 255);
    assertEquals(transparentImage.maxImageValue(), 200);
  }

  @Test
  public void testImageContentFormat() {
    this.initImages();
    assertEquals("255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  \n" +
            "255 0 0  ", baseImage.imageContentFormat());

    assertEquals("0 255 0  \n" +
            "0 255 0  \n" +
            "0 255 0  \n" +
            "0 255 0  ", placerImage.imageContentFormat());
    assertEquals("200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  \n" +
            "200 153 152  ", transparentImage.imageContentFormat());
  }

  @Test
  public void testGetRow() {
    this.initImages();
    assertEquals(baseImage.getRow(0), row1);
    assertEquals(placerImage.getRow(1), row2);
    assertEquals(transparentImage.getRow(0), transparentRow);
  }

  @Test
  public void testSetVIL() {
    this.initImages();
    baseImage.setVIL();
    assertEquals(255, baseImage.getRow(0).get(0).getValue());
    assertEquals(55, baseImage.getRow(1).get(2).getLumosity());
    assertEquals(85, baseImage.getRow(2).get(2).getIntensity());
    placerImage.setVIL();
    assertEquals(255, placerImage.getRow(0).get(0).getValue());
    assertEquals(183, placerImage.getRow(1).get(0).getLumosity());
    assertEquals(85, placerImage.getRow(1).get(1).getIntensity());
  }
}