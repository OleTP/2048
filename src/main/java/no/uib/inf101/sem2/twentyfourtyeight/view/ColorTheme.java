package no.uib.inf101.sem2.twentyfourtyeight.view;

import java.awt.Color;

/**
 * ColorTheme interface defines methods used for customizing the appearance of the game.
 */
public interface ColorTheme {
   
/**
 * 
 * @param number
 * @return
 */
Color getTileColor(int number);

/**
 * Gets the color of the gameboards frame.
 * @return The color representing the frame.
 */
Color getFrameColor();

/**
 * Gets the color of the gameboards background.
 * @return The color representing the background.
 */
Color getBackgroundColor();

/**
 * Gets the color of the font.
 * @return The color representing the font.
 */
Color getFontColor();

/**
 * Gets the color of the Game Over screen overlay.
 * @return The color representing the game over screen color.
 */
Color getGameOverColor();
}
