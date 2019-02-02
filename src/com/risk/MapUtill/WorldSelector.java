package com.risk.MapUtill;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorldSelector extends MouseAdapter {

    private World world;

    public WorldSelector(World w)
    {
        world = w;
    }

    public void mousePressed(MouseEvent e)
    {
        Point p = e.getPoint();
        for (MAPObject obj : world.returnWorld())
        {

            if (obj.contains(p))
            {
                obj.addArmy(Color.yellow);
                world.repaint();
                break;
            }

        }
    }

}
