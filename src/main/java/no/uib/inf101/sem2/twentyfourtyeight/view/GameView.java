package no.uib.inf101.sem2.twentyfourtyeight.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.twentyfourtyeight.model.GameModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;


public class GameView extends JPanel {
    ViewableGameModel model;
    ColorTheme colorTheme;
    double INNER_MARGIN = 20;
    double OUTER_MARGIN = 20;

    private JPanel gamePanel;
    
    public GameView(GameModel model) {
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(400, 400));

        colorTheme = new DefaultColorTheme();
        Color backgroundColor = colorTheme.getBackgroundColor();
        this.setBackground(backgroundColor);

        setLayout(new BorderLayout());

        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };

        gamePanel.setVisible(false);
        add(gamePanel, BorderLayout.CENTER);

        
        JPanel startPanel = new JPanel(new GridBagLayout());
        startPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 20, 0);


        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(true);
                startPanel.setVisible(false);
            }
        });
        

        gbc.gridy = 0;
        startPanel.add(startButton, gbc);

        JLabel instructionLabel = new JLabel("Use arrow-keys to move");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        startPanel.add(instructionLabel, gbc);

        add(startPanel, BorderLayout.SOUTH);
    }

    private void drawGame(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        double boxDimX = this.getWidth()-2*INNER_MARGIN;
        double boxDimY = this.getHeight()-2*INNER_MARGIN;
        // Rectangle for game area
        Rectangle2D gameArea = new Rectangle2D.Double(OUTER_MARGIN, OUTER_MARGIN, boxDimX, boxDimY);
        Shape shape = gameArea;
    
        // Fill the rectangle with the colour to the outline of the grid
        g2.setColor(colorTheme.getFrameColor());
        g2.fill(shape);
        g2.draw(shape);
        
        drawScore(g2);
      
        
        // Create a CellPositionToPixelConverter-object based on the rectangle
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(gameArea, model.getDimension(), INNER_MARGIN);
      
    
       
        // Draw every cell in the grid by using the drawCell-method
        drawCells(g2, model.getTilesOnBoard(), converter, colorTheme);
        //drawCells(g2, model.GetTilesOfValue(), converter, colorTheme)
        
        if (model.getGameState() == GameState.GAME_OVER){
            drawGameOverMessage(g2);
        }
        
    }

    private static void drawCells(Graphics2D g2, Iterable<GridCell<Integer>> cells, CellPositionToPixelConverter converter, ColorTheme colorTheme) {
        // Set font color and size for tile values
        g2.setColor(colorTheme.getFontColor());
        g2.setFont(g2.getFont().deriveFont(20f));

        for (GridCell<Integer> cell : cells) {
            Rectangle2D rect = converter.getBoundsForCell(cell.pos());
            Color color = colorTheme.getTileColor(cell.value());

            Shape shape = rect;
            g2.setColor(color);
            g2.fill(shape);
            g2.draw(shape);

            // Draw tile value
            if (cell.value() != 0) {
                String value = String.valueOf(cell.value());
                int stringWidth = g2.getFontMetrics().stringWidth(value);
                int stringHeight = g2.getFontMetrics().getAscent();
                int x = (int) (rect.getX() + rect.getWidth() / 2 - stringWidth / 2);
                int y = (int) (rect.getY() + rect.getHeight() / 2 + stringHeight / 2);
                g2.setColor(colorTheme.getFontColor());
                g2.drawString(value, x, y);
            }
        }
    }
    

    private void drawScore(Graphics2D g2) {
        String scoreText = "Score: " + model.getScore();
        g2.setColor(colorTheme.getFontColor());
        g2.setFont(g2.getFont().deriveFont(18f));
        int stringWidth = g2.getFontMetrics().stringWidth(scoreText);
        int x = (int) (this.getWidth() - stringWidth - OUTER_MARGIN);
        int y = (int) (OUTER_MARGIN - 5);
        g2.drawString(scoreText, x, y);
    }

    private void drawGameOverMessage(Graphics2D g2) {
        g2.setColor(colorTheme.getGameOverColor());
        g2.setFont(g2.getFont().deriveFont(50f));
        String message = "Game Over";
        int stringWidth = g2.getFontMetrics().stringWidth(message);
        int stringHeight = g2.getFontMetrics().getAscent();
        int x = (int) (this.getWidth() / 2 - stringWidth / 2);
        int y = (int) (this.getHeight() / 2 + stringHeight / 2);
        g2.drawString(message, x, y);
    }

}