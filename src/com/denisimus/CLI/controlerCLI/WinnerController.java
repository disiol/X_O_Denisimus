package com.denisimus.CLI.controlerCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class WinnerController {

    public Figure getWinner(final Filed filed) {

        try {

            //проверка рядов
            for (int i = 0; i < 3; i++) {
                if (check(filed, new Point(i, 0), p -> new Point(p.x, p.y + 1))) {

                    return filed.getFigure(new Point(i, 0));

                }

            }

            //проверка колонок
            for (int i = 0; i < 3; i++) {
                if (check(filed, new Point(0, i), p -> new Point(p.x + 1, p.y))) {

                    return filed.getFigure(new Point(0, i));

                }

            }

            // Проверка первой диагонали
            if (check(filed, new Point(0, 0), p -> new Point(p.x + 1, p.y + 1))) {

                return filed.getFigure(new Point(0, 0));

            }

            // Проверка второй диагонали
            if (check(filed, new Point(0, 2), p -> new Point(p.x + 1, p.y - 1))) {

                return filed.getFigure(new Point(1, 1));

            }

        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

        return null;
    }


    private boolean check(final Filed filed,
                          final Point currentPoint,
                          final IPointGenerator pointGenerator) {
        final Figure currentFigure;
        final Figure nextFigure;
        final Point nextPoint = pointGenerator.next(currentPoint);

        try {
            currentFigure = filed.getFigure(currentPoint);
            if (currentFigure == null)
                return false;
            nextFigure = filed.getFigure(nextPoint);
        } catch (InvalidPointException e) {
            return true;
        }


        if (currentFigure != nextFigure)
            return false;


        return check(filed, nextPoint, pointGenerator);
    }

    private interface IPointGenerator {

        public Point next(final Point point);
    }

}

