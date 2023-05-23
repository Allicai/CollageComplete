package hw4.model.filters;

import java.util.List;

import hw4.model.Layers.Layer;

/**
 * A normal which is the default and does nothing when applied to a layer later on.
 */
public class NormalFilter implements Filter {

  String name = "normal";

  @Override
  public void apply(Layer current, List<Layer> below) {
    //Does nothing because we are in the normal filter.
  }

  @Override
  public String getName() {
    return this.name;
  }

}
