import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hw4.controller.PPMController;
import hw4.model.AbstractEditorModel;
import hw4.model.EditorModel;
import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
import hw4.model.Pixels.Pixel;
import hw4.model.ProjectModel;
import hw4.view.ProjectModelView;

/**
 * test class for the ppm controller.
 */
public class PPMControllerTest {
  Scanner scanner;
  EditorModel project = new ProjectModel(2, 2);

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
  ProjectModelView view = new ProjectModelView(model);
  Readable readable;
  PPMController controller = new PPMController((ProjectModel) model, view, readable);

}