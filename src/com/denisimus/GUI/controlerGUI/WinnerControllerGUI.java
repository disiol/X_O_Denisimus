package com.denisimus.GUI.controlerGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.GUI.modelGUI.FiledGUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 *
 * @version 1.1
 */

public class WinnerControllerGUI {
    private List<Integer> coordinates;

    public Figure getWinner(final FiledGUI filed) {

        // checkOfARow1

        if (check(filed, 0, 1, 2) == true) {

            return filed.getFigure(0);


        }


        // checkOfARow2;


        if (check(filed, 3, 4, 5) == true) {

            return filed.getFigure(3);


        }

        //checkOfARow3

        if (check(filed, 6, 7, 8) == true) {

            return filed.getFigure(6);

        }


        // checkOfAColumns1

        if (check(filed, 0, 3, 6) == true) {

            return filed.getFigure(0);

        }


        //checkOfAColumns2


        if (check(filed, 1, 4, 7) == true) {

            return filed.getFigure(1);

        }


        // checkOfAColumns3;


        if (check(filed, 2, 5, 8) == true) {

            return filed.getFigure(2);

        }


        //checkOfADiagonal;


        if (check(filed, 0, 4, 8) == true) {

            return filed.getFigure(0);

        }


        //checkOfADiagonal2;


        if (check(filed, 2, 4, 6) == true) {

            return filed.getFigure(2);

        }


        return null;
    }


    private boolean check(final FiledGUI filed,
                          final int coordinate1,
                          final int coordinate2,
                          final int coordinate3) {

        final Figure currentFigure;
        final Figure nextFigure;

        setCoordinates(coordinate1, coordinate2, coordinate3);
        currentFigure = filed.getFigure(coordinate1);
        if (currentFigure == null)
            return false;
        nextFigure = filed.getFigure(coordinate2);

        if (currentFigure != nextFigure)
            return false;

        if (nextFigure != filed.getFigure(coordinate3))
            return false;

        return true;


    }

    private void setCoordinates(int win1,
                                int win2,
                                int win3) {

        List<Integer> coordinates = new ArrayList<>();
        coordinates.add(win1);
        coordinates.add(win2);
        coordinates.add(win3);


    }


    public List<Integer> getCoordinates() {
        return coordinates;
    }


}





