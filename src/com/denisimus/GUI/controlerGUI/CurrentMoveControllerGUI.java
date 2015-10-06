package com.denisimus.GUI.controlerGUI;

import com.denisimus.GUI.modelGUI.FiledGUI;
import com.denisimus.CLI.modelCLI.Figure;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 04.08.15.
 */

public class CurrentMoveControllerGUI {

    public Figure currentMove(final FiledGUI filedGUI) {
        int countFigure = 0;
        for (int x = 0; x < filedGUI.getSize(); x++) {
            countFigure += currentFiguresInTheRow(filedGUI, x);
        }

        if (countFigure == filedGUI.getSize() * filedGUI.getSize()) {
            return null;
        }

        if (countFigure % 2 == 0) {
            return Figure.X;
        }

        return Figure.O;


    }

    private int currentFiguresInTheRow(final FiledGUI filedGUI, final int row) {
        int countFigure = 0;

        for (int x = 0; x < filedGUI.getSize(); x++) {
            if (filedGUI.getFigure(row) != null) {
                countFigure++;

            }

        }
        return countFigure;

    }
}
