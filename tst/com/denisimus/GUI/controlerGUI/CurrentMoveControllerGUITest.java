package com.denisimus.GUI.controlerGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.GUI.modelGUI.FiledGUI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 06.10.15.
 */

public class CurrentMoveControllerGUITest {

    @Test
    public void testCurrentMoveWenNexMoveIsO() throws Exception {
        final CurrentMoveControllerGUI currentMoveController = new CurrentMoveControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(0, Figure.X);
        filed.setFigure(1, Figure.O);
        filed.setFigure(2, Figure.X);

        assertEquals(Figure.O, currentMoveController.currentMove(filed));


    }

    @Test
    public void testCurrentMoveWenNexMoveIsX() throws Exception {
        final CurrentMoveControllerGUI currentMoveController = new CurrentMoveControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(1, Figure.O);
        filed.setFigure(2, Figure.X);

        assertEquals(Figure.X, currentMoveController.currentMove(filed));

    }


    @Test
    public void testCurrentMoveWenNoNexMove() throws Exception {
        //сделать цыклами

        final CurrentMoveControllerGUI currentMoveController = new CurrentMoveControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(0, Figure.O);
        filed.setFigure(1, Figure.X);
        filed.setFigure(2, Figure.O);
        filed.setFigure(3, Figure.O);
        filed.setFigure(4, Figure.X);
        filed.setFigure(5, Figure.O);
        filed.setFigure(6, Figure.O);
        filed.setFigure(7, Figure.X);
        filed.setFigure(8, Figure.O);


        assertNull(currentMoveController.currentMove(filed));


    }
}
