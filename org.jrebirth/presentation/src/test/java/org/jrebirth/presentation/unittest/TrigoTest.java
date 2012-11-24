package org.jrebirth.presentation.unittest;

import javafx.geometry.Point2D;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * The class <strong>TrigoTest</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 */
public class TrigoTest {

    private Point2D bottomLeft;
    private Point2D bottomRight;
    private Point2D topLeft;
    private Point2D topRight;

    protected float getAngle(final double latitudeOrigine, final double longitudeOrigne, final double latitudeDest, final double longitudeDest) {
        final double longDelta = longitudeDest - longitudeOrigne;
        final double y = Math.sin(longDelta) * Math.cos(latitudeDest);
        final double x = Math.cos(latitudeOrigine) * Math.sin(latitudeDest) -
                Math.sin(latitudeOrigine) * Math.cos(latitudeDest) * Math.cos(longDelta);
        double angle = Math.toDegrees(Math.atan2(y, x));

        while (angle < 0) {
            angle += 360;
        }

        return (float) angle % 360;
    }

    private double findAngle(final Point2D from, final Point2D to) {
        return getAngle(from.getX(), from.getY(), to.getX(), to.getY());

        // double angle;
        // // if (from.getX() == to.getX()) {
        // // angle = from.getY() < to.getY() ? 270 : 90;
        // // } else if (from.getY() == to.getY()) {
        // // angle = from.getX() < to.getX() ? 0 : 180;
        // // } else {
        //
        // // Ce 1er article va utiliser une formule simple pour trouver un angle entre 2 points.
        //
        // // angleRadian = Math.atan2(px1 - px2, py1 - py2);
        // // angleDegree = angleRadian * (180 / Math.PI);
        // //
        // // Et une autre formule qui permet de trouver la distance entre 2 points.
        // // distance= Math.sqrt(px1*px2 + py1*py2);
        //
        // angle = Math.toDegrees(Math.atan2(from.getX() - to.getX(), from.getY() - to.getY()));
        // angle += 90;
        // while (angle < 0) {
        // angle += 360;
        // }
        //
        // // final double fromSquare = from.getX() - to.getX();
        // // final double toSquare = to.getY() - from.getY();
        // // final double fromTo = Math.sqrt(Math.pow(fromSquare, 2) + Math.pow(toSquare, 2));
        // // angle = Math.acos(fromSquare / fromTo);
        // // }
        // return angle;
    }

    @Before
    public void definePoints() {
        this.bottomLeft = new Point2D(0, 0);
        this.bottomRight = new Point2D(100, 0);
        this.topLeft = new Point2D(0, 100);
        this.topRight = new Point2D(100, 100);
    }

    @Test
    public void testLeftToRight() {

        Assert.assertEquals("Right to Left", 0.0, findAngle(this.bottomLeft, this.bottomRight)); // -90
        Assert.assertEquals("Left to Right", 180.0, findAngle(this.bottomRight, this.bottomLeft)); // 90

        Assert.assertEquals("Top to Bottom", 270.0, findAngle(this.bottomLeft, this.topLeft)); // 180
        Assert.assertEquals("Bottom to Top", 90.0, findAngle(this.topLeft, this.bottomLeft)); // 0

        Assert.assertEquals("/ test", 135.0, findAngle(this.bottomLeft, this.topRight)); // -135
        Assert.assertEquals("/ test", 315.0, findAngle(this.topRight, this.bottomLeft)); // 45

        Assert.assertEquals("\\ test", 225.0, findAngle(this.topLeft, this.bottomRight)); // -45
        Assert.assertEquals("\\ test", 45.0, findAngle(this.bottomRight, this.topLeft)); // 135
    }

}
