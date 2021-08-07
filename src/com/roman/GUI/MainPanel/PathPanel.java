package com.roman.GUI.MainPanel;

import com.roman.GUI.MainFrame;
import com.roman.Metro.Change;
import com.roman.Metro.MetroStation;
import com.roman.util.Table;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.util.Map;

public class PathPanel extends JPanel {
    public final static int HEIGHT = MainFrame.HEIGHT / 2;
    public static final String TIME_TILL_ARRIVAL_LABEL = "Время в пути %d мин.";
    public static final String ROUTE_DETAILS_LABEL = "Здесь отображаются детали маршрута";
    public static final Font MAIN_LABEL_FONT = new Font("Times New Roman", Font.BOLD, 14);
    public static final int ARROW_START_X = 10;
    public static final int ARROW_WIDTH = 5;
    public static final int ARROW_HEIGHT = 10;
    public static final int STATION_COMMENT_AND_ARROW_HEIGHT = 2 * ARROW_HEIGHT + 15;
    public static final int SPACE_AFTER_STATION_COMMENT_LABEL = 5;
    private static PathPanel Singleton;

    private int centerYCoord = 20;
    private int currentListHeight;
    private Table<MetroStation, Change> pathTable;
    private MetroStation startStation;
    private MetroStation finishStation;
    private JScrollBar scrollBar;
    private int time;
    private BufferedImage panelImage;
    private Graphics imageGraphics;

    private PathPanel() {
        scrollBar = new JScrollBar(JScrollBar.VERTICAL, 20, 1, 20, 21);
        scrollBar.addAdjustmentListener(new ScrollerListener());
        this.setPreferredSize(new Dimension(Settings.WIDTH, MainFrame.HEIGHT / 2));
        this.panelImage = new BufferedImage(Settings.WIDTH, MainFrame.HEIGHT / 2, BufferedImage.TYPE_INT_RGB);
        this.imageGraphics = panelImage.getGraphics();
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.add(scrollBar, BorderLayout.EAST);
    }

    public static PathPanel getInstance() {
        if (Singleton == null) Singleton = new PathPanel();
        return Singleton;
    }

    public void setPath(Table<MetroStation, Change> temp, MetroStation start, MetroStation finish, int time) {
        pathTable = temp;
        startStation = start;
        finishStation = finish;
        this.time = time;
        scrollBar.setMaximum(21);
        scrollBar.setValue(20);
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        updatePanelImage();
        g.drawImage(panelImage, 0, 0, null);
    }

    private void updatePanelImage() {
        fillImageBackground();
        if (pathTable != null) {
            drawPathComments();
            drawTimeTillArrivalLabel();
        } else {
            drawNoRouteLabel();
        }
    }

    private void fillImageBackground() {
        imageGraphics.setColor(Color.WHITE);
        imageGraphics.fillRect(0, 0, Settings.WIDTH, MainFrame.HEIGHT / 2);
    }

    private void drawPathComments() {
        currentListHeight = 30;
        MetroStation currentStation = startStation;
        MetroStation lastStation = null;
        while (!currentStation.equals(finishStation)) {
            drawStationNameLabel(currentStation);
            currentListHeight += SPACE_AFTER_STATION_COMMENT_LABEL;
            for (Map.Entry<MetroStation, Change> temp : pathTable.getColumn(currentStation).entrySet()) {
                if (!temp.getKey().equals(lastStation)) {
                    lastStation = currentStation;
                    drawArrowForStations(currentStation.getColor(), temp.getKey().getColor());
                    currentListHeight += STATION_COMMENT_AND_ARROW_HEIGHT;
                    currentStation = temp.getKey();
                    break;
                }
            }
        }
        drawStationNameLabel(finishStation);
        if (HEIGHT < currentListHeight) {
            scrollBar.setMaximum((currentListHeight - HEIGHT) + 30);
        }
    }

    private void drawStationNameLabel(MetroStation tempStation) {
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.setColor(tempStation.getColor());
        imageGraphics.drawString(tempStation.toString(), 10, currentListHeight + centerYCoord);
    }

    private void drawArrowForStations(Color startColor, Color finishColor) {
        imageGraphics.setColor(startColor);
        imageGraphics.fillRect(ARROW_START_X, currentListHeight + centerYCoord, ARROW_WIDTH, ARROW_HEIGHT);
        imageGraphics.setColor(finishColor);
        imageGraphics.fillRect(ARROW_START_X, currentListHeight + centerYCoord + ARROW_HEIGHT, ARROW_WIDTH, ARROW_HEIGHT);
    }

    private void drawTimeTillArrivalLabel() {
        imageGraphics.setFont(MAIN_LABEL_FONT);
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.drawString(getTimeTillArrivalString(), ARROW_START_X, getTimeTillArrivalLabelY());
    }

    private void drawNoRouteLabel() {
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.drawString(ROUTE_DETAILS_LABEL, 0, 10);
    }

    private String getTimeTillArrivalString() {
        return String.format(TIME_TILL_ARRIVAL_LABEL, time);
    }

    private int getTimeTillArrivalLabelY() {
        return centerYCoord + currentListHeight + 20;
    }

    private class ScrollerListener implements AdjustmentListener {
        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            JScrollBar temp = (JScrollBar) e.getSource();
            centerYCoord = -temp.getValue();
            repaint();
        }
    }
}
