package PoolGame;

import javafx.collections.ObservableList;
import javafx.scene.Node;

/** An interface for all the drawable object */
public interface Drawable {
    /**
     * Get the `Node` instance of the object.
     * @return The `Node` instance for the object.
     */
    public Node getNode();
    /**
     * Add the object to the JavaFX group so they can be drawn.
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    public void addToGroup(ObservableList<Node> groupChildren);
}
