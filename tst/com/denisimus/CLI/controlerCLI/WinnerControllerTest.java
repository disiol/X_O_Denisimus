package com.denisimus.CLI.controlerCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 03.08.15.
 */

public class WinnerControllerTest {

    //в идеале сделать гене ратор случайного поля
    @Test
    public void testGetWinnerRow() throws Exception {
        final WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(i, 0), Figure.X);
            filed.setFigure(new Point(i, 1), Figure.X);
            filed.setFigure(new Point(i, 2), Figure.X);

            assertEquals(Figure.X, winnerController.getWinner(filed));
        }

    }


    @Test
    public void testGetNoWinnerRow() throws Exception {

        final WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(i, 0), Figure.X);
            filed.setFigure(new Point(i, 1), Figure.X);
            filed.setFigure(new Point(i, 2), Figure.O);
            assertNull(winnerController.getWinner(filed));
        }

    }

    @Test
    public void testGetNoWinnerRow2() throws Exception {

        final WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(i, 0), Figure.O);
            filed.setFigure(new Point(i, 1), Figure.X);
            filed.setFigure(new Point(i, 2), Figure.X);
            assertNull(winnerController.getWinner(filed));
        }

    }

    @Test
    public void testGetWinnerColumn() throws Exception {

        final WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(0, i), Figure.X);
            filed.setFigure(new Point(1, i), Figure.X);
            filed.setFigure(new Point(2, i), Figure.X);
            assertEquals(Figure.X, winnerController.getWinner(filed));
        }

    }

    @Test
    public void testGetNoWinnerColumn() throws Exception {

        final WinnerController winnerController = new WinnerController();
        for (int i = 0; i < 3; i++) {
            final Filed filed = new Filed(3);
            filed.setFigure(new Point(0, i), Figure.X);
            filed.setFigure(new Point(1, i), Figure.X);
            filed.setFigure(new Point(2, i), Figure.O);
            assertNull(winnerController.getWinner(filed));
        }

    }

    @Test
    public void testGetWinnerDiag1() throws Exception {

        final WinnerController winnerController = new WinnerController();

        final Filed filed = new Filed(3);
        filed.setFigure(new Point(0, 0), Figure.X);
        filed.setFigure(new Point(1, 1), Figure.X);
        filed.setFigure(new Point(2, 2), Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerDiag1() throws Exception {

        final WinnerController winnerController = new WinnerController();

        final Filed filed = new Filed(3);
        filed.setFigure(new Point(0, 0), Figure.X);
        filed.setFigure(new Point(1, 1), Figure.X);
        filed.setFigure(new Point(2, 2), Figure.O);
        assertNull(winnerController.getWinner(filed));


    }

    @Test
    public void testGetWinnerDiag2() throws Exception {

        final WinnerController winnerController = new WinnerController();

        final Filed filed = new Filed(3);
        filed.setFigure(new Point(0, 2), Figure.X);
        filed.setFigure(new Point(1, 1), Figure.X);
        filed.setFigure(new Point(2, 0), Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerDiag2() throws Exception {

        final WinnerController winnerController = new WinnerController();

        final Filed filed = new Filed(3);
        filed.setFigure(new Point(0, 2), Figure.X);
        filed.setFigure(new Point(1, 1), Figure.X);
        filed.setFigure(new Point(2, 0), Figure.O);
        assertNull(winnerController.getWinner(filed));


    }
}