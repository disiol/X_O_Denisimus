package com.denisimus.CLI.modelCLI;

import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 30.07.15.
 */

public class FiledTest {

    @Test
    public void testGetSize() throws Exception {
        final Filed filed = new Filed(3);

        assertEquals(3, filed.getSize());
    }

    @Test
    public void testSetFigure() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(0, 0);
        final Figure inputFigure = Figure.O;

        filed.setFigure(inputPoint, inputFigure);
        final Figure actualFigure = filed.getFigure(inputPoint);

        assertEquals(inputFigure, actualFigure);

    }


    @Test
    public void testSetFigureVenFigureIsNotSet() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(0, 0);


        final Figure actualFigure = filed.getFigure(inputPoint);

        assertNull(actualFigure);

    }


    @Test
    public void testSetFigureVenXIsLessThenZero() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(-1, 0);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }

    @Test
    public void testSetFigureVenYIsLessThenZero() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(0, -1);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }

    @Test
    public void testSetFigureVenXIsMoreThenSize() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(filed.getSize() + 1, 0);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }

    @Test
    public void testSetFigureVenYIsThenSize() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(0, filed.getSize() + 1);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }

    @Test
    public void testSetFigureVenXYIsLessThenZero() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(-1, -1);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }


    @Test
    public void testSetFigureVenXYIsThenSize() throws Exception {
        final Filed filed = new Filed(3);
        final Point inputPoint = new Point(filed.getSize() + 1, filed.getSize() + 1);


        try {
            filed.getFigure(inputPoint);
            fail();
        } catch (final InvalidPointException e) {
        }


    }

}