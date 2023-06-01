/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author victor
 */
public class PatternButton extends JButton {
    
    class Dimensions {
        int width = getWidth();
        int height = getHeight();
        int numRows = 6;
        int numCols = 6;
        int squareWidth = width / numCols;
        int squareHeight = height / numRows;
    }
    
    private boolean[][] cells;
    
    public void setPatternType(PatternType type) {
        cells = new boolean[6][6];
        for (int i = 0; i < cells.length; i ++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = false;
            }
        }
        switch (type) {
            case BLINKER:
                cells[1][2] = true;
                cells[2][2] = true;
                cells[3][2] = true;
                break;
            case TOAD:
                cells[2][2] = true;
                cells[2][3] = true;
                cells[2][4] = true;
                cells[3][1] = true;
                cells[3][2] = true;
                cells[3][3] = true;
                break;
            case BEACON:
                cells[1][1] = true;
                cells[1][2] = true;
                cells[2][1] = true;
                cells[3][4] = true;
                cells[4][4] = true;
                cells[4][3] = true;
                break;
            case GLIDER:
                cells[1][2] = true;
                cells[2][3] = true;
                cells[3][1] = true;
                cells[3][2] = true;
                cells[3][3] = true;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Dimensions d = new Dimensions();
        paintBackground(g, d);
    }
    
    private void paintBackground(Graphics g, Dimensions d) {
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, d.width, d.height);
        
        // Paint grid
        g.setColor(Color.LIGHT_GRAY);
        for (int row = 0; row < d.numRows; row ++) {
            g.drawLine(0, row * d.squareHeight ,
                    d.width,  row * d.squareHeight);
        }
        for (int col = 0; col < d.numCols; col ++) {
            g.drawLine(col * d.squareWidth, 0, 
                    col * d.squareWidth, d.height);
        }
        
        // Paint frame
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, d.width, d.height);
 
    }
    
}
