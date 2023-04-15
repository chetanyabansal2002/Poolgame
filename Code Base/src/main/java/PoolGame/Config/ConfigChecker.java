package PoolGame.Config;

import javafx.scene.paint.Color;

/** Helper class for configuration check */
public class ConfigChecker {
    /**
     * Check that a colour string is recognised by JavaFX
     * @param colour The colour name as `String`
     * @return true if the name can be parsed by JavaFX, false otherwise.
     */
    public static boolean colourChecker(String colour) {
        try {
            Color.valueOf(colour);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
