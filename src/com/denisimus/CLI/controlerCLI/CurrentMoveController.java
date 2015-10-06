package com.denisimus.CLI.controlerCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 04.08.15.
 */

public class CurrentMoveController {

    public Figure currentMove(final Filed filed) {
        int countFigure = 0;
        for (int x = 0; x < filed.getSize(); x++) {
            countFigure += currentFiguresInTheRow(filed, x);
        }

        if (countFigure == filed.getSize() * filed.getSize()) {
            return null;
        }

        if (countFigure % 2 == 0) {
            return Figure.X;
        }

        return Figure.O;


    }

    private int currentFiguresInTheRow(final Filed filed, final int row) {
        int countFigure = 0;

        for (int x = 0; x < filed.getSize(); x++) {
            try {
                if (filed.getFigure(new Point(x, row)) != null) {
                    countFigure++;

                }
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }

        }
        return countFigure;

    }


}
