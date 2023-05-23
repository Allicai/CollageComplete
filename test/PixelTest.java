import org.junit.Test;

import hw4.model.Pixels.Pixel;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * test class for the pixel class.
 */
public class PixelTest {

  int[] blue = {0, 0, 255, 255};
  int[] red = {255, 0, 0, 255};
  int[] green = {0, 255, 0, 255};
  int[] transparent = {200, 153, 152, 255};
  int[] nicePurple = {120, 109, 222, 0};
  Pixel pixelGreen;
  Pixel pixelRed;
  Pixel pixelBlue;
  Pixel pixelTransparent;
  Pixel nicePurplePixel;

  @Test
  public void testValidConstruction() { // tests the construction of a pixel
    try {
      pixelGreen = new Pixel(green);
    } catch (IllegalArgumentException e) {
      //
    }
    // testing setVil as well b/c it is called when constructing a pixel
    assertEquals(183, pixelGreen.getLumosity());
    assertEquals(85, pixelGreen.getIntensity());
    assertEquals(255, pixelGreen.getValue());

    try {
      pixelRed = new Pixel(red);
    } catch (IllegalArgumentException e) {
      //
    }
    // testing setVil as well b/c it is called when constructing a pixel
    assertEquals(55, pixelRed.getLumosity());
    assertEquals(85, pixelRed.getIntensity());
    assertEquals(255, pixelRed.getValue());

    try {

      nicePurplePixel = new Pixel(nicePurple);
    } catch (IllegalArgumentException e) {
      //
    }
    // testing setVil as well b/c it is called when constructing a pixel
    assertEquals(120, nicePurplePixel.getLumosity());
    assertEquals(151, nicePurplePixel.getIntensity());
    assertEquals(222, nicePurplePixel.getValue());

  }


  @Test
  public void testSetRgba() {
    // initializes a pixel, changes the rgba, and then checks the rgba value
    pixelBlue = new Pixel(red);
    pixelBlue.setRgba(blue);
    assertArrayEquals(blue, pixelBlue.getRgba());

    pixelRed = new Pixel(green);
    pixelRed.setRgba(red);
    assertArrayEquals(red, pixelRed.getRgba());

    pixelGreen = new Pixel(blue);
    pixelGreen.setRgba(green);
    assertArrayEquals(green, pixelGreen.getRgba());
  }

  @Test
  public void testGetRgba() {
    // checks getRgba on the original rgba value instead of a changed one

    pixelGreen = new Pixel(green);
    assertArrayEquals(green, pixelGreen.getRgba());

    pixelRed = new Pixel(red);
    assertArrayEquals(red, pixelRed.getRgba());

    pixelBlue = new Pixel(blue);
    assertArrayEquals(blue, pixelBlue.getRgba());
  }

  @Test
  public void testCompareRgba() {
    pixelBlue = new Pixel(red);
    pixelRed = new Pixel(red);
    // checks that two pixels with the same rgba return true
    assertTrue(pixelBlue.compareRgba(pixelRed));
    // change the blue pixels rgba and makes sure the method is now false
    pixelBlue.setRgba(blue);
    assertFalse(pixelBlue.compareRgba(pixelRed));
    // all three possibilities we defined
    pixelGreen = new Pixel(green);
    assertFalse(pixelGreen.compareRgba(pixelBlue));
    assertFalse(pixelRed.compareRgba(pixelGreen));
  }


  @Test
  public void testCompareVIL() {
    pixelRed = new Pixel(red);
    pixelBlue = new Pixel(red);
    assertTrue(pixelRed.compareVIL(pixelBlue));

    pixelBlue = new Pixel(blue);
    pixelGreen = new Pixel(blue);
    assertTrue(pixelGreen.compareVIL(pixelBlue));

    pixelGreen = new Pixel(green);
    pixelRed = new Pixel(green);
    assertTrue(pixelGreen.compareVIL(pixelRed));
  }

  @Test
  public void testComparePixels() {
    pixelRed = new Pixel(red);
    pixelRed.setVil();
    pixelBlue = new Pixel(blue);
    pixelBlue.setVil();
    assertFalse(pixelRed.comparePixels(pixelBlue));
    pixelRed.setRgba(blue);
    pixelRed.setVil();
    assertTrue(pixelRed.comparePixels(pixelBlue));
  }

  @Test
  public void testIsTransparent() {
    pixelTransparent = new Pixel(transparent);
    assertFalse(pixelTransparent.isTransparent());
    pixelGreen = new Pixel(green);
    assertFalse(pixelGreen.isTransparent());
  }

  @Test
  public void testPixelToString() {
    pixelBlue = new Pixel(blue);
    assertEquals("0 0 255 ", pixelBlue.pixelToString());
    pixelGreen = new Pixel(green);
    assertEquals("0 255 0 ", pixelGreen.pixelToString());
    pixelRed = new Pixel(red);
    assertEquals("255 0 0 ", pixelRed.pixelToString());
  }
}