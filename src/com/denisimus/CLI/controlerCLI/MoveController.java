package com.denisimus.CLI.controlerCLI;

import com.denisimus.CLI.modelCLI.Figure;
import com.denisimus.CLI.modelCLI.Filed;
import com.denisimus.CLI.modelCLI.exeptions.AlreadyOccupantException;
import com.denisimus.CLI.modelCLI.exeptions.InvalidPointException;

import java.awt.*;

/**
 * Author: Olenyk Denis (deoniisii@gmail.com)
 * on 31.07.15.
 */

public class MoveController {


    public void applyFigure(Filed filed,
                            Point point,
                            Figure figure) throws InvalidPointException,
            AlreadyOccupantException {

        if (filed.getFigure(point) != null) {
            throw new AlreadyOccupantException();
        }

        filed.setFigure(point, figure);


    }
}
