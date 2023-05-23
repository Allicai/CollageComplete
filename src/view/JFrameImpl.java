package hw4.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import hw4.controller.GUIController;
import hw4.controller.IController;
import hw4.model.EditorModel;
import hw4.model.Images.Image;
import hw4.model.Layers.Layer;
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
 * Class to instantiate the GUI for the program.
 */
public class JFrameImpl extends JFrame implements IJFrame {

  private final JPanel mainPanel = new JPanel();
  private final ImagePanelClass imagePanel;
  private JButton layers;
  private final JLabel layerLabel;
  private JButton darkness;
  private JButton addLayer;

  protected EditorModel model;
  private JLabel imageLabel;
  private JButton component;
  private JButton blending;
  private JButton brightness;
  private JDialog filterPopup;
  private JComboBox<String> filterPopup1;
  private JTextField filterName;
  private String layer;

  /**
   * Constructor for JFrameViewImpl (GUI). This will create a GUI that is visible and allows for
   * editing of an image.
   */
  public JFrameImpl(EditorModel model) {
    super();
    this.model = model;
    // = new GUIController (model, this);
    IController controller = new GUIController(this.model, this);


    //--------------------------------------------Header-----------------------------------------//
    this.setSize(500, 500); //dimensions
    this.setTitle("Image Processor GUI"); // title
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // not sure what this does
    this.setLocation(500, 800); //location; not sure what this does


    //----------------------------------------Main Panel-----------------------------------------//
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //<-border that is just a line? per the specifications an image panel -> will be able to scroll
    // if the image is too large
    mainPanel.setBorder(BorderFactory.createCompoundBorder());
    this.add(mainPanel, BorderLayout.NORTH);

    //----------------------------------------Image Panel-----------------------------------------//
    //---------------------Create an image panel and add everything to the image for that//

    imagePanel = new ImagePanelClass();
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    // Make the panel scrollable

    layerLabel = new JLabel("Initial Text");
    mainPanel.add(layerLabel, BorderLayout.NORTH);
    layerLabel.setText("The current layer you are affecting is: " + "null");
    //---------------------------------File Bar(Load, Save, Quit)--------------------------------//

    //Menu Bar
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem quit = new JMenuItem("Quit");

    //------------------------------------Create New project-------------------------------------//
    JMenuItem newProject = new JMenuItem("New");
    newProject.setActionCommand("new-project");
    newProject.addActionListener(controller);

    //-------------------------------------- Add New Image to Current Layer----------------------//
    JMenuItem addImage = new JMenuItem("Add Image To Layer");
    addImage.setActionCommand("add-image-to-layer");
    addImage.addActionListener(controller);

    //---------------------------------------------Load------------------------------------------//

    JMenuItem load = new JMenuItem("Load");

    load.setActionCommand("load-project");
    load.addActionListener(controller);
    this.revalidate();
    this.repaint();

    //-------------------------------------Save-------------------------------------------//
    JMenuItem save = new JMenuItem("Save as Image");

    save.setActionCommand("save-project");
    save.addActionListener(controller);

    JMenuItem saveAsProject = new JMenuItem("Save As Project");
    saveAsProject.setActionCommand("save-projectAsProject");
    saveAsProject.addActionListener(controller);

    //-----------Add Everything Together------//
    menu.add(addImage);
    menu.add(newProject);
    menu.add(load);
    menu.add(save);
    menu.add(quit);
    menu.add(saveAsProject);
    menuBar.add(menu);
    this.add(menuBar);
    this.setJMenuBar(menuBar);
    this.setContentPane(mainPanel);

    //-------------------------------------Bottom Panel-----------------------------------------//

    // Create the filter options and add the three buttons to it
    Box filterDisplay = Box.createHorizontalBox();
    brightness = new JButton("Brightness");

    //----------------------------------Drop Down for brightness,darkness, components------------//
    brightness.setActionCommand("set-brightness");
    brightness.addActionListener(controller);
    filterDisplay.add(brightness);
    darkness = new JButton("Darkness");
    darkness.setActionCommand("set-darkness");
    darkness.addActionListener(controller);
    filterDisplay.add(darkness);

    component = new JButton("Component");
    component.setActionCommand("set-component");
    component.addActionListener(controller);
    filterDisplay.add(component);

    blending = new JButton("Blending");
    blending.setActionCommand("set-blend");
    blending.addActionListener(controller);
    filterDisplay.add(blending);

    layers = new JButton("Layers");
    layers.setActionCommand("layers");
    layers.addActionListener(controller);
    filterDisplay.add(layers);

    //--------------------------------Add Filter/Set Filter---------------------------------------//
    //Going to want to make this the layer that is on the page onces its selected
    addLayer = new JButton("Add Layer");

    //Create a popup where information can be filed -> these strings should be later fed to
    // addFilter in model
    filterPopup = new JDialog(this, "Creating a new Layer", true);
    addLayer.setActionCommand("add-layer");
    addLayer.addActionListener(controller);
    filterDisplay.add(addLayer); //<- add to where we want the filters
    mainPanel.add(filterDisplay, BorderLayout.CENTER);

    //------------------------------------------------------------------------------------------//
    setLocationRelativeTo(null);

    setVisible(true);
  }

  /**
   * method to create a new project in the GUI.
   *
   * @return an array of ints containing the width and height of the project.
   */
  public int[] createNewProject() {
    Dialog dimensionsPopup = new JDialog(this, "Enter your dimensions", true);
    JTextField num1Field = new JTextField();
    JTextField num2Field = new JTextField();

    Object[] message = {
            "Enter the width:", num1Field,
            "Enter the height:", num2Field
    };

    int option = JOptionPane.showConfirmDialog(null, message,
            "Enter your dimensions", JOptionPane.OK_CANCEL_OPTION);

    dimensionsPopup.add(num1Field);
    dimensionsPopup.add(num2Field);

    return new int[]{Integer.parseInt(num1Field.getText()), Integer.parseInt(num2Field.getText())};
  }

  /**
   * method to offset the project on the background of the GUI.
   *
   * @return an array of ints that contain the x and y offsets.
   */
  public int[] offSetProject() {
    Dialog dimensionsPopup = new JDialog(this,
            "Enter the offset from the current image", true);
    JTextField num1Field = new JTextField();
    JTextField num2Field = new JTextField();

    Object[] message = {
            "Enter the width:", num1Field,
            "Enter the height:", num2Field
    };

    int option = JOptionPane.showConfirmDialog(null, message, "Enter two numbers"
                    + " to offset",
            JOptionPane.OK_CANCEL_OPTION);

    dimensionsPopup.add(num1Field);
    dimensionsPopup.add(num2Field);

    return new int[]{Integer.parseInt(num1Field.getText()), Integer.parseInt(num2Field.getText())};
  }

  /**
   * a method to show messages from the GUI to the user when certain actions are performed.
   *
   * @param message the message that the user is going to be able to see.
   */
  public void renderMessage(String message) {

    JOptionPane messagePane = new JOptionPane(message);
    final javax.swing.JDialog dialog = messagePane.createDialog("New Info: \n");
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dialog.setVisible(false);
        dialog.dispose();
      }
    };
    Timer timer = new Timer(10, taskPerformer);
    timer.setRepeats(false);
    // Show the JDialog
    dialog.setVisible(true);
  }

  /**
   * a method to return the filepath being selected by the user when saving/loading.
   *
   * @return the filepath as a string.
   */
  public String getPath() {
    JFileChooser fileChooser = new JFileChooser();
    int fs = fileChooser.showOpenDialog(mainPanel);

    String path = null;
    if (fs == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile(); //Pass this path to the model
      path = file.getPath();
    }
    return path;
  }

  /**
   * method to make a ppm or other image format into a buffered image so that it can be displayed.
   */
  public void loadBufferedImage() {
    BufferedImage displayedImage = this.model.projectToImage();
    imagePanel.setImage(displayedImage);
    imagePanel.repaint();
    mainPanel.revalidate();
    mainPanel.repaint();
  }

  /**
   * method to index to the proper layer when an affect is happening.
   *
   * @return the index of the layer.
   */
  public int affectCurrLayer() {
    List<Layer> modelLayers = model.getLayers(); //get layers
    String[] options = new String[modelLayers.size()]; //get size of layers
    String temp = ""; //init temp string
    for (int i = 0; i < modelLayers.size(); i++) {
      options[i] = "" + modelLayers.get(i).toString(); //add each to the options which will
      // populate a button.
    }

    //give the user choices
    int choice = JOptionPane.showOptionDialog(layers, "Choose a layer to alter:",
            "Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
            options[0]);
    layerLabel.setText("The current layer you are affecting is: " + options[choice]);
    model.setCurrLayer(model.giveLayerByName(options[choice]));

    return model.giveLayerByName(options[choice]); //return a Layer gotten by String name from
    // choices.
  }

  /**
   * a handler that returns the proper filter when an option is chosen to darken.
   *
   * @return the filter the user wants to apply.
   */
  public Filter darknessFilter() {
    String[] options = {"Value", "Luma", "Intensity"};
    int choice = JOptionPane.showOptionDialog(darkness, "Choose a way to Darken:",
            "Set Filter To:",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    /*if (choice >= 0) {
      JOptionPane.showMessageDialog(darkness, "You chose " + options[choice]);
    }*/

    switch (choice) {
      case 0: //value
        return new DarkenValue();
      case 1: //luma
        return new DarkenLuma();
      case 2: //intensity
        return new DarkenIntensity();
      default:
        return new NormalFilter();
    }
  }

  /**
   * a handler that returns the proper filter when an option is chosen to brighten.
   *
   * @return the filter the user wants to apply.
   */
  public Filter brightnessFilter() {
    String[] options = {"Value", "Luma", "Intensity"};
    int choice = JOptionPane.showOptionDialog(brightness, "Choose a way to Brighten:",
            "Set Filter To:",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    /*if (choice >= 0) {
      JOptionPane.showMessageDialog(darkness, "You chose " + options[choice]);
    }*/
    switch (choice) {
      case 0: //value
        return new BrightenValue();
      case 1: //luma
        return new BrightenLuma();
      case 2: //intensity
        return new BrightenIntensity();
      default:
        return new NormalFilter();
    }
  }

  /**
   * a handler that returns the proper filter when an option is chosen from the component menu.
   *
   * @return the filter the user wants to apply.
   */
  public Filter componentFilter() {
    String[] options = {"Red", "Green", "Blue"};
    int choice = JOptionPane.showOptionDialog(component, "Choose a Color:",
            "Set Filter To:",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    if (choice >= 0) {
      JOptionPane.showMessageDialog(component, "You chose " + options[choice]);
    }

    switch (choice) {
      case 0: //value
        return new RedComponent();
      case 1: //luma
        return new GreenComponent();
      case 2: //intensity
        return new BlueComponent();
      default:
        return new NormalFilter();
    }

  }

  /**
   * Allows the user to set the view to a blending image.
   *
   * @return a Filter that is a blend Filter of the filter interface for use later.
   */
  public Filter blendingFilter() {
    String[] options = {"Multiply", "Screen", "Difference"};
    int choice = JOptionPane.showOptionDialog(blending, "Choose a Color:",
            "Set Filter To:",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    if (choice >= 0) {
      JOptionPane.showMessageDialog(blending, "You chose " + options[choice]);
    }

    switch (choice) {
      case 0: //value
        return new Multiply();
      case 1: //luma
        return new Screen();
      case 2: //intensity
        return new Difference();
      default:
        return new NormalFilter();
    }

  }

  /**
   * Returns a layer that uses the view to taken in new information.
   *
   * @return a new layer that will be added given a filter and a name
   */
  public Layer addLayerToModel() {
    //only gets all its info if this button is pressed??
    //inits the popup size and location
    filterPopup = new JDialog(this, "Creating a new Layer", true);
    filterPopup.setSize(250, 150);
    filterPopup.setLocationRelativeTo(addLayer);
    filterPopup.setLayout(new GridLayout(3, 2));
    //Name String for filter <- easily applied or no?
    JLabel nameLabel = new JLabel("Name:");
    filterName = new JTextField();
    JScrollPane scrollableFilter = new JScrollPane(filterName);
    filterPopup.add(nameLabel);
    filterPopup.add(scrollableFilter);

    //filter type String  <- going to have to turn this into a filter using giveFilter
    JLabel filterLabel = new JLabel("Filter:");
    String[] filters = {"normal", "red-component", "blue-component", "green-component",
            "darkness-luma", "darkness-value", "darkness-intensity", "brightness-luma",
            "brightness-value", "brightness-intensity", "multiply", "screen", "difference"};
    filterPopup1 = new JComboBox<>(filters);
    filterPopup.add(filterLabel);
    filterPopup.add(filterPopup1);

    //enter/submit
    JButton enterButton = new JButton("Enter");

    Filter filter = new NormalFilter();
    AddLayerAL addLayerEnter = new AddLayerAL();
    enterButton.addActionListener(addLayerEnter);


    filterPopup.add(enterButton);
    filterPopup.setVisible(true); //<-javadoc said to do this, not sure why
    filterPopup.dispose();
    System.out.println(this.model.getHeight());
    System.out.println(this.model.getWidth());
    Image img = new Image(model.createBackgroundPixels(this.model.getHeight(),
            this.model.getWidth(), 255));

    //addLayerPopup.setVisible(false);
    return new Layer(img, addLayerEnter.getName(), addLayerEnter.getFilter());

  }

  /**
   * method to set the model of the GUI to something else.
   *
   * @param model the new AbstractEditorModel that is going to be the this.model of the view.
   */
  public void setModel(EditorModel model) {
    this.model = model;
  }

  /**
   * method to get the filepath to the location the user wants to save in.
   *
   * @return the filepath as a string.
   */
  public String saveProject() {
    JFileChooser saveFileChooser = new JFileChooser();
    int sf = saveFileChooser.showSaveDialog(mainPanel);
    String path = "";
    if (sf == 0) {
      File savedFile = saveFileChooser.getSelectedFile();
      path = savedFile.getAbsolutePath();
      //---------------------------------------------------------------//

    }
    return path;
  }

  /**
   * An instances of a JPanel usd for painting the actual image and overriding the graphics.
   */
  class ImagePanelClass extends JPanel {
    JLabel imageLabel;
    BufferedImage img;

    /**
     * Constructor for the imagePanel Class which builsd an image and a label on that image.
     */
    ImagePanelClass() {
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      this.setBorder(BorderFactory.createTitledBorder("Image Panel"));
      imageLabel = new JLabel();
      imageLabel.setPreferredSize(new Dimension(300, 300));
      JScrollPane scrollPane = new JScrollPane(imageLabel,
              JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      //getContentPane().add(scrollPane, BorderLayout.CENTER);

      this.add(scrollPane, BorderLayout.WEST);
    }

    /**
     * method to set the image being displayed by the GUI.
     *
     * @param img the image being provided.
     */
    public void setImage(BufferedImage img) {
      this.img = img;
    }

    @Override
    protected void paintComponent(Graphics g) {
      if (img == null) {
        //
      } else {
        imageLabel.setIcon(new ImageIcon(img));
      }


    }
  }

  /**
   * An Action Listener class that isn't final which allows for the adding of a new layer to the
   * model.
   */
  class AddLayerAL implements ActionListener {

    private Filter filter;
    private String name;

    /**
     * The action of setting up the filter and name by taking in the buttons input.
     *
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

      //get a filter from the model method giveFilter which uses the String name cast from the GUI.
      filter = model.giveFilter((String) Objects.requireNonNull(filterPopup1.getSelectedItem()));
      name = filterName.getText(); //get the name
      filterPopup.dispose(); //delete for future
      filterName.setText(""); //delete for future
      filterPopup1.removeAll(); //remove everything from the popup, so it can be used again safely.

    }

    /**
     * Get the name of the filter which has been saved in this class.
     *
     * @return a name which is a string to represent a filter name from the model.
     */
    public String getName() {
      return this.name;
    }


    /**
     * Gets the filter as an object from the button which is one of the Filter interface options.
     *
     * @return a new Filter option.
     */
    public Filter getFilter() {
      return this.filter;
    }
  }
}

