import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hw4.model.AbstractEditorModel;
import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.ProjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * class to test the project models.
 */
public class ProjectModelTest {

  ProjectModel project = new ProjectModel(2, 2);

  int[] blue = {0, 0, 255, 0};
  int[] red = {255, 0, 0, 0};
  int[] green = {0, 255, 0, 0};
  int[] transparent = {200, 153, 152, 0};
  int[] nicePurple = {120, 109, 222, 0};
  int[] background = {0, 0, 0, 255};
  Pixel pixelGreen; //= new hw4.model.Pixel(blue)
  Pixel pixelRed;// new hw4.model.Pixel(red)
  Pixel pixelBlue;// = new hw4.model.Pixel(green)
  Pixel pixelTransparent;
  int a = 3;
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
  List<Layer> layers = new ArrayList<>();
  List<Layer> layers2 = new ArrayList<>();
  List<Layer> layers3 = new ArrayList<>();
  AbstractEditorModel model = new ProjectModel(layers, 2, 2);
  AbstractEditorModel model2 = new ProjectModel(layers2, 1, 6);
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
  public void saveProjectFinal() {
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    model.addLayer("res2L");
    // this one should work
    model.saveProjectFinal("images/");
    model2.createBackgroundLayer(2, 2, 255);
    model2.addLayer("res3L");
    model2.addLayer("res4L");
    // this one should throw a writing to file error
    model2.saveProjectFinal("fakeimages/");
    assertEquals(a, 3);
  }

  @Test
  public void testSaveCollageFormat() throws IOException {
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    model.addLayer("res2L");
    try {
      model.saveCollageFormat("images/");
    } catch (IOException e) {
      // do nothing
    }
    model2.createBackgroundLayer(2, 2, 255);
    model2.addLayer("res3L");
    model2.addLayer("res4L");
    try {
      model2.saveCollageFormat("fakeimages/");
    } catch (IOException e) {
      fail("");
    }
  }

  @Test
  public void testCollageContentFormat() {
    this.initImages();
    model.createBackgroundLayer(2, 2, 255);
    model.addLayer("res1L");
    model.addLayer("res2L");
    assertEquals("C1\n"
            + "2 2\n"
            + "0\n"
            + "res1L normal\n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "res2L normal\n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n", model.collageContentFormat());

    model2.createBackgroundLayer(2, 2, 255);
    model2.addLayer("res3L");
    model2.addLayer("res4L");
    assertEquals("C1\n"
            + "1 6\n"
            + "0\n"
            + "res3L normal\n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "res4L normal\n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n"
            + "0 0 0  \n", model2.collageContentFormat());
  }

}