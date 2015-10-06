package com.denisimus.CLI.modelCLI;

import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 30.07.15.
 */

public class Filed {
    private static final int MIN_COORDINATE = 0;
    private final int filedSize;
    private final Figure[][] filed;

    public Filed(final int filedSize) {
        this.filedSize = filedSize;
        filed = new Figure[filedSize][filedSize];
    }

    public int getSize() {

        return filedSize;
    }

    public Figure getFigure(final Point point) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }

        return filed[point.x][point.y];

    }

    public void setFigure(final Point point, final Figure figure) throws InvalidPointException {

        if (!checkPoint(point)) {
            throw new InvalidPointException();
        }


        filed[point.x][point.y] = figure;
    }


    private boolean checkPoint(final Point point) {

        return chekCordinate(point.x, filed.length) && chekCordinate(point.y, filed[point.x].length);

    }

    private boolean chekCordinate(final int cordinate, final int maxCoordinate) {
        return cordinate >= MIN_COORDINATE && cordinate < maxCoordinate;
    }
}

