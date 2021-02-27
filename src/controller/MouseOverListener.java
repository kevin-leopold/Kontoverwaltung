package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Abstrakte Klasse für das Mouse Event. Siehe {@link controller.Controller#mouseEntered(MouseEvent)}
 */

public abstract class MouseOverListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public abstract void mouseEntered(MouseEvent e);

    @Override
    public void mouseExited(MouseEvent e){}

}
