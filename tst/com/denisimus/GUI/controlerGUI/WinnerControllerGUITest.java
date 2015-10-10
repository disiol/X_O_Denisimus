package com.denisimus.GUI.controlerGUI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.GUI.modelGUI.FiledGUI;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 06.10.15.
 */

public class WinnerControllerGUITest {

    @Test
    public void testGetWinnerRow() throws Exception {
        final WinnerControllerGUI winnerController = new WinnerControllerGUI();
        final FiledGUI filed = new FiledGUI(9);

        filed.setFigure(0, Figure.X);
        filed.setFigure(1, Figure.X);
        filed.setFigure(2, Figure.X);

        assertEquals(Figure.X, winnerController.getWinner(filed));


    }


    @Test
    public void testGetNoWinnerRow() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(0, Figure.X);
            filed.setFigure(1, Figure.X);
            filed.setFigure(2, Figure.O);
            assertNull(winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerRow2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(3, Figure.O);
            filed.setFigure(4, Figure.X);
            filed.setFigure(5, Figure.X);
            assertNull(winnerController.getWinner(filed));


    }
    @Test
    public void testGetWinnerRow2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(3, Figure.X);
            filed.setFigure(4, Figure.X);
            filed.setFigure(5, Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetWinnerColumn() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(0, Figure.X);
            filed.setFigure(3, Figure.X);
            filed.setFigure(6, Figure.X);
            assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerColumn() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(0, Figure.X);
            filed.setFigure(3, Figure.X);
            filed.setFigure(6, Figure.O);
            assertNull(winnerController.getWinner(filed));


    }

@Test
    public void testGetWinnerColumn2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(1, Figure.X);
            filed.setFigure(4, Figure.X);
            filed.setFigure(7, Figure.X);
            assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerColumn2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

            final FiledGUI filed = new FiledGUI(9);
            filed.setFigure(1, Figure.X);
            filed.setFigure(4, Figure.X);
            filed.setFigure(7, Figure.O);
            assertNull(winnerController.getWinner(filed));


    }

    @Test
    public void testGetWinnerColumn3() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(2, Figure.X);
        filed.setFigure(5, Figure.X);
        filed.setFigure(8, Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerColumn3() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(2, Figure.X);
        filed.setFigure(5, Figure.X);
        filed.setFigure(8, Figure.O);
        assertNull(winnerController.getWinner(filed));


    }

    @Test
    public void testGetWinnerDiag1() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(0, Figure.X);
        filed.setFigure(4, Figure.X);
        filed.setFigure(8, Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerDiag1() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(0, Figure.X);
        filed.setFigure(4, Figure.X);
        filed.setFigure(8, Figure.O);
        assertNull(winnerController.getWinner(filed));


    }

    @Test
    public void testGetWinnerDiag2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(2, Figure.X);
        filed.setFigure(4, Figure.X);
        filed.setFigure(6, Figure.X);
        assertEquals(Figure.X, winnerController.getWinner(filed));


    }

    @Test
    public void testGetNoWinnerDiag2() throws Exception {

        final WinnerControllerGUI winnerController = new WinnerControllerGUI();

        final FiledGUI filed = new FiledGUI(9);
        filed.setFigure(2, Figure.X);
        filed.setFigure(4, Figure.X);
        filed.setFigure(6, Figure.O);
        assertNull(winnerController.getWinner(filed));


    }

}