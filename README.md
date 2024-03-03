# CollageComplete

Worked on by me and my partner in my Object Oriented Designing Class at Northeastern.

Image Processor


#####Description
The program available is a simple image processor that allows the user to load an image and do one of the following features. This processors accepts any format of image including but not limited to a ppm, jpeg, png.  By loading this program you will have the opportunity to load a project in the form of a text file or create a new project. You will then have the ability to load and edit a series of images by applying filters that will either directly affect them or will affect them with information from previous layers. It is important to note that this particular program allows for you to compile and compact a ton of differnet layers into a single compressed image using a formula which takes into account the layer below it for its pixel values. Whether you are running this program with the text UI or the GUI this program includes the same features which are listed both below and in the use me. These features allows the user to create or load a project, add images to layers, add layers or apply one of the following filter; blend, screen, multiply, red, green, blue, brighten (three options), darken (three options). This program does not allow you to crop images or add image thats are outside the parameters of the size of the project. 

#####Dependencies
Java 11
JUnit 4 or higher

---How to use program-
Please find the USEME for this program in this file titled USEME.txt this file will explain each program and how to use it in a text or GUI 

------

FOR OOD 


--------------------------------------KEEP SCROLLING FOR UPDATES SINCE A4 -> INCLUDING GUI ----------
READ THIS FOR INFORMATION ON COLLAGE PROJECT

How it works:

Our design starts with the most basic possible class. A pixel class.

-----Pixel Class--------
This pixel class holds an int[] which is the red value as the 0th index, the green as the 1st, blue as third and alpha value of the pixel as the final. When this initialized it will create a value, lumosity and intensity as per the specifications of a pixel using the mathematical formula provided to us. This is the most basic of our classes but allows for the creation of images, layers, etc which will be talked about later and is most commonly used in a List<List<Pixel>> Format throughout the project. Methods to watch out for in the Pixel class are setVIL(), getRgba, pixelToString and alphaTize. In respective order setVIL will update the value, lumosity, and intensity. The rgba creates a deep copy of the list of rgba. The pixelToString prints the pixel out in x y z format as an int for the values, this method has a versoin which includes the alpha value and a version that doesn't. Finally alphatize which used a given pixel value to create a new pixel that is considered to be flattened. 
------------------------------------------------------------

After the pixel class we move onto the Image class. 
-----Image Class-------
This class like mentioned before is simply going to be a List<List<Pixel>> to represent the pixels in a grid format which is how they would like as an image. Thie class is probably the most important as we are working with image editing and these r simple instances of images. This class has methods such as imageContentFormat which print it out in string format using pixelToString. This image class also has the ability to have its pixels replaced one by one given another image which will be important to editing an image.
-----------------------

Moving on from the image class we have the layer class which uses composition to use the functionality of an image but also extend beyond. This class also has filter and name which can be used later to apply its given filter and create a new image with different rgb according to A4 definitions.

------Layer Class----
The layer class is used within the project class in a list in order to have a bunch of layers which are kind of images stacked on top of each other. The implementation of this class allows for the editing of its image which will eventually be turned into a compressed and final image or a collage project image so that the user can see what they are working on. The importance of the layers fields of filter and name are the functionality of retrieving a layer from a list of them in the model which will be talked about later and applying the filter which was previously mentioned.
---------------------

In between the layer class and the model or really in between the pixel and the layer is the filter class. This filter class will be different instances of the filter interface which requires the implementation of a change filter method and a name within the class so that we can easily add new filters or apply the given filters to the layer class which we talk about.

----Filter interface/class------
The filter interface is used to define filters as function objects. The interface only defines two methods, one is changePixel, which is meant for each filter to override and have its own unique effect on the pixels it is being called on. The other is getName, which simply returns the name of the filter (a String field defined in each individual function object) that we use for the sake of switch cases when parsing arguments
----------------

Now moving on to the most top level we have the editorInterface. This interface will be used for any type of editor model that needs to save, load and maintain functionality for image editing. IT is implemented in the AbstractModel Class. 

-----Abstract Model-----
The abstract model class allows for the manipulation of filters, layers (it has a List<Layer> field as well as size fields), allows for the adding of layers and creation of a project, etc. This class assumes that any type of editor will need any of the above functionality and a few more and therefore says that any editorModel should have these fields, methods and uses. We use this class and its constructors in the projectModel which is the ability to save and load PPM or projects.
----------------


-------The projectModel class--------
This class assumes that it will only be used when it is trying to save something as a collage content format. The functionality of this calls the abstract model and so it can do all of those things but also save in the specific collage content format.
---------


We also have test cases which test all of this functionality. In addition everything was tested through running the main.


Running Our script::

In order to run our script you should run the provided main method and copy and paste the inputs into console. We attempted doing things like taking input directly from a file given the path but had difficulty.

Supports commands 

        [new-project [width] [height]] 
        [load-project <path>]
        [add-layer [layer]]
        [add-image-to-layer [layer] [<path> to image] [offset x] [offset y]
        [set-filter [layer] [filter]] 
        [display-project] 
        [save-project <path>
        [save-image <path>]

          new-project: Create a new project using the given height and width
          load-project: load an old project using the given path
          add-layer: adds a new layer to the project you are working in
          add-image-to-layer: adds an image to the specific layer using the offsets
          set-filter: sets the specificed layers filter to the new specificed filter
          display-project: displays the collage Project Forma
          save-project: saves the project in the collage format
          save-image: saves the project as a ppm file

NOTES: 
Save-image has trouble saving an image that has more than one layer and compressing (issues with Array out of bounds and compression throughout)

-----------------------------------------------------------------------------------------------------

Design Changes since A4:

The largest design change since a4 was the operation of our filters. Previously these had avoid method that took in nothing and did something one by one to every pixel. Instead of that these taken in a layer and a List of Layers that will be compressed and filtered as need be so that for the filtering systems that take in more than one layer or do something to more than one layer we can maintain that capability. Other than that to add the filters we just added them to a new class as function objects (like the rest) and added them to the branch we use to delimit between filters. In addition our filters are called on layers instead of being a completely separate thing or the apply filter to be more specific. Obviously we further changed code to support this implementation.

Things we updated since the last: 

We previously had bugs with saving something with multiple layers, and compressing. Both of these bugs are no longer an issue, the first being a syntax error that our read couldn't handle which was fixed and the second being a lack of a working method which we then implemented for this assignment. 

This program works in a similar way to the last one but instead uses a GUI over a text interface, this gives you the option to select a layer, edit that layers filter, load an image, load a project, create a new project, add a new layer all as separate buttons in the GUI. For how this operates in specific please refer to the USEME included in this folder. 

What we added

We added a JFrame Implementation, a new controller and a Jframe interface. The interface contains all the commands from the Jframe that would be necessary to make a similar gui operating on an image processor. This required the development of a function called projectToImage which returns a buffered image from a ppm. The frame implementation is built using a model and coupling to a controller. Everything is initialized once in the controller and then methods are called through the controller that return view functionality to the user. Our controller acts in a similar way to previous controller but instead it uses action-listeners and action commands to check for strings in the switch case so that it knows what to do. 

Finally we implemented a main that can take in a script or take in user text if the Jar runs. 

Note: Any images we used are generated personally using a text file and typing out the numbers. 

///--------------------------A6-----------------------------------------//

The only changes made was re-configuration of the current files and classes to prevent intense coupling or poorly protected/organized files. We still find a few bugs when it comes to loading large things or compressing. In addition sometimes the jar has issues? 
