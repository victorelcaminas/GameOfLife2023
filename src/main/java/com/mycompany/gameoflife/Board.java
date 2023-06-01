package com.mycompany.gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 *
 * @author victor
 */
public class Board extends javax.swing.JPanel {
    
    class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            RowCol rc = getRowCol(x, y);
            if (viewModelMatrix.isAlive(rc.row, rc.col)) {
                viewModelMatrix.kill(rc.row, rc.col);
            } else {
                viewModelMatrix.born(rc.row, rc.col);
            }
            repaint();
        }
    }
    
    class Dimensions {
        int width = getWidth();
        int height = getHeight();
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        int squareWidth = width / numCols;
        int squareHeight = height / numRows;
        int realWidth = squareWidth * numCols;
        int realHeight = squareHeight * numRows;
        int leftMargin = (width - realWidth) / 2;
        int upperMargin = (height - realHeight) / 2;
    }
    
    private ViewModelMatrix viewModelMatrix;
    private Timer timer;
    public static final int DELTA_TIME_TO_STOP = 1000;

    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        enableMouseListener();
        timer = new Timer(calculateDeltaTime(50), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
        int numRows = ConfigData.getInstance().getNumRows();
        int numCols = ConfigData.getInstance().getNumCols();
        viewModelMatrix = new ViewModelMatrix(numRows, numCols);
        viewModelMatrix.fillRandom(0.25f);
    }
    
    public int calculateDeltaTime(int value) {
        int max_slider_value = 100;
        int epsilon = 5;
        int multiplier = 5;
        return (max_slider_value + epsilon - value) * multiplier; 
    }
    
    public void setDeltaTime(int value) {
        if (value == 0) {            
            timer.stop();
            timer.setDelay(DELTA_TIME_TO_STOP);
        } else {
            int delta = calculateDeltaTime(value);
            timer.setDelay(delta);
            if (!timer.isRunning()) {
                timer.start();
            }
        }
    }
    
    private void enableMouseListener() {
        addMouseListener(new MyMouseAdapter());
    }
    
    public void initGame() {
        if (!timer.isRunning() && timer.getDelay() != DELTA_TIME_TO_STOP) {
            timer.start();
        }
    }
    
    public void stopGame() {
        timer.stop();
    }
    
    private void tick() {
        viewModelMatrix.calculateNextGen();
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        Dimensions dimensions = new Dimensions();
        paintBackground(g, dimensions);
        paintCells(g, dimensions);
    }
    
    private void paintCells(Graphics g, Dimensions d) {
        g.setColor(Color.BLACK);
        for (int row = 0; row < d.numRows; row++) {
            for (int col = 0; col < d.numCols; col++) {
                if (viewModelMatrix.isAlive(row, col)) {
                    g.fillRect(d.leftMargin + col * d.squareWidth, 
                            d.upperMargin + row * d.squareHeight,
                            d.squareWidth, d.squareHeight);
                }
            }
        }
    }
    
    private void paintBackground(Graphics g, Dimensions d) {
        
        g.setColor(Color.WHITE);
        g.fillRect(d.leftMargin, d.upperMargin, d.realWidth, d.realHeight);
        
        // Paint grid
        g.setColor(Color.LIGHT_GRAY);
        for (int row = 0; row < d.numRows; row ++) {
            g.drawLine(d.leftMargin, d.upperMargin + row * d.squareHeight ,
                    d.realWidth + d.leftMargin,  d.upperMargin + row * d.squareHeight );
        }
        for (int col = 0; col < d.numCols; col ++) {
            g.drawLine(d.leftMargin + col * d.squareWidth, d.upperMargin, 
                    d.leftMargin + col * d.squareWidth, d.upperMargin + d.realHeight);
        }
        
        // Paint frame
        g.setColor(Color.BLACK);
        g.drawRect(d.leftMargin, d.upperMargin, d.realWidth, d.realHeight);
 
    }
    
    public RowCol getRowCol(int x, int y) {
        Dimensions d = new Dimensions();
        int row = (y - d.upperMargin) / d.squareHeight;
        int col = (x - d.leftMargin) / d.squareWidth;
        return new RowCol(row, col);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
