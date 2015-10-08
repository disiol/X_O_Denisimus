package com.denisimus.GUI.modelGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com) on 30.07.15.
 */
public class FiledGUI {

    private static final int MIN_COORDINATE = 0;
    private final int filedSize;
    private final Figure[] filed;

    public FiledGUI(final int filedSize) {
        this.filedSize = filedSize;
        filed = new Figure[filedSize];
    }

    public int getSize() {

        return filedSize;
    }

    public Figure getFigure(final int point) {
//        if (!checkPoint(point)) {
//            throw new InvalidPointException();
//        }

        return filed[point];

    }

    public void setFigure(final int point, final Figure figure) {

//        if (!checkPoint(point)) {        return filed[point][];

//            throw new InvalidPointException();
//        }

        filed[point] = figure;
    }

//    private boolean checkPoint(final int point) {
//
//        return chekCordinate(point.x, filed.length) && chekCordinate(point.y, filed[point.x].length);
//
//    }
//
//    private boolean chekCordinate(final int cordinate, final int maxCoordinate) {
//        return cordinate >= MIN_COORDINATE && cordinate < maxCoordinate;
//    }
}
