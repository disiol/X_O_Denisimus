package com.denisimus.GUI.controlerGUI;

import com.denisimus.GUI.modelGUI.FiledGUI;
import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;


/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class MoveControllerGUI {


    public void applyFigure(FiledGUI filed,
                            int point,
                            Figure figure) throws InvalidPointException,
            AlreadyOccupantException {

        if (filed.getFigure(point) != null) {
            throw new AlreadyOccupantException();
        }

        filed.setFigure(point, figure);


    }
}
