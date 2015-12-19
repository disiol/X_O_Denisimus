package com.denisimus.model;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 30.07.15.
 */

public class PlayerTest {


    @Test
    public void testGetName() throws Exception {

        final String inputValue = "Slava";
        final String expectedValue = inputValue;

        Player player = new Player(inputValue, null);

        final String actualValue = player.getName();

        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void testGetFigure() throws Exception {

        final Figure inputValue = Figure.X;
        final Figure expectedValue = inputValue;

        Player player = new Player(null, inputValue);

        final Figure actualValue = player.getFigure();

        assertEquals(expectedValue, actualValue);

    }
}