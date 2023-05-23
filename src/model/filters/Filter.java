package hw4.model.filters;

import java.util.List;

import hw4.model.Layers.Layer;

/**
 * A generalize interface for any Filter which will use the apply method to alter the layer that
 * it is a part of.
 */
public interface Filter { // generalized interface for all the filters

  /**
   * method for all filters to override that applies their own effects to a layer.
   *
   * @param current the layer currently being worked on
   * @param below   the list of layers below the current layer
   */

  void apply(Layer current, List<Layer> below);

  /**
   * returns the name of the filter.
   *
   * @return a string value of the name of the filter.
   */
  String getName();
}
