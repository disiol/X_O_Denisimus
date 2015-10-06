package com.denisimus.GUI.controlerGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.GUI.modelGUI.FiledGUI;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class WinnerControllerGUI {

    public Figure getWinner(final FiledGUI filed) {

        checkOfARow1(filed);
        checkOfARow2(filed);
        checkOfARow3(filed);
        checkOfAColumns1(filed);
        checkOfAColumns2(filed);
        checkOfAColumns3(filed);
        checkOfADiagonal(filed);
        checkOfADiagonal2(filed);

        return null;
    }


    private boolean check(final FiledGUI filed,
                          final int currentPoint,
                          final int nextPoint) {
        try {


            final Figure currentFigure;
            final Figure nextFigure;


            currentFigure = filed.getFigure(currentPoint);
            if (currentFigure == null)
                return false;
            nextFigure = filed.getFigure(nextPoint);


            if (currentFigure != nextFigure)
                return false;


            return check(filed, currentPoint, nextPoint);
        } catch (StackOverflowError e) {

        }

        return false;
    }

    /**
     * @param filed
     * @return
     */
    private Figure checkOfARow1(final FiledGUI filed) {

        for (int i = 1; i < 2; i++) {
            if (check(filed, 0, i)) {

                return filed.getFigure(i);

            }

        }


        return null;
    }

    private Figure checkOfARow2(final FiledGUI filed) {

        for (int i = 4; i < 5; i++) {
            if (check(filed, 3, i)) {

                return filed.getFigure(i);

            }

        }

        return null;
    }

    private Figure checkOfARow3(final FiledGUI filed) {

        for (int i = 7; i < 8; i++) {

            if (check(filed, 6, i)) {

                return filed.getFigure(i);

            }

        }

        return null;
    }


    private Figure checkOfAColumns1(final FiledGUI filed) {

        for (int i = 3; i < 6; i += 3) {
            if (check(filed, 0, i)) {

                return filed.getFigure(i);

            }

        }


        return null;
    }

    private Figure checkOfAColumns2(final FiledGUI filed) {
        for (int i = 4; i < 7; i += 3) {

            if (check(filed, 1, i)) {

                return filed.getFigure(i);

            }

        }


        return null;
    }

    private Figure checkOfAColumns3(final FiledGUI filed) {
        for (int i = 5; i < 8; i += 3) {

            if (check(filed, 2, i)) {

                return filed.getFigure(i);

            }

        }

        return null;
    }

    private Figure checkOfADiagonal(final FiledGUI filed) {
        for (int i = 4; i <= 8; i += 4) {


            if (check(filed, 0, i)) {

                return filed.getFigure(0);

            }
        }

        return null;
    }

    private Figure checkOfADiagonal2(final FiledGUI filed) {

        for (int i = 4; i < 6; i += 2) {
            if (check(filed, 2, i)) {

                return filed.getFigure(2);

            }
        }

        return null;
    }

}





