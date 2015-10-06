package com.denisimus.CLI.controlerCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
// написать генератор полей

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 04.08.15.
 */

public class CurrentMoveControllerTest {

    @Test
    public void testCurrentMoveWenNexMoveIsO() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(i, 0), Figure.X);
            filed.setFigure(new Point(i, 1), Figure.O);
            filed.setFigure(new Point(i, 2), Figure.X);

            assertEquals(Figure.O, currentMoveController.currentMove(filed));

        }
    }

    @Test
    public void testCurrentMoveWenNexMoveIsX() throws Exception {
        final CurrentMoveController currentMoveController = new CurrentMoveController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(i, 1), Figure.O);
            filed.setFigure(new Point(i, 2), Figure.X);

            assertEquals(Figure.X, currentMoveController.currentMove(filed));

        }
    }

    @Test
    public void testCurrentMoveWenNoNexMove() throws Exception {
        //сделать цыклами

        final CurrentMoveController currentMoveController = new CurrentMoveController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(0, 0), Figure.O);
            filed.setFigure(new Point(0, 1), Figure.X);
            filed.setFigure(new Point(0, 2), Figure.O);
            filed.setFigure(new Point(1, 0), Figure.O);
            filed.setFigure(new Point(1, 1), Figure.X);
            filed.setFigure(new Point(1, 2), Figure.O);
            filed.setFigure(new Point(2, 0), Figure.O);
            filed.setFigure(new Point(2, 1), Figure.X);
            filed.setFigure(new Point(2, 2), Figure.O);


            assertNull(currentMoveController.currentMove(filed));

        }
    }
}