import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Assignment2 {
    public static Shape createYingYang()
    {
        //Create full circle
            Area drawing = new Area(new Ellipse2D.Double(-100, -100, 200, 200));
        //Remove left side of the circle
            Area leftRemoval = new Area(new Rectangle2D.Double(-100,-100,100,400));
        //Remove Circle from the botton to create tail of black side
            Area blackTail = new Area(new Ellipse2D.Double(-50,0,100,100));
        //Draw black circle on top to create top of black side and tail for white
            Area whiteTail = new Area(new Ellipse2D.Double(-50,-100,100,100));
        //create black dot in white section
	        Area blackDot = new Area(new Ellipse2D.Double(-10, 45, 20, 20));
        //create white dot in black section
	        Area whiteDot = new Area(new Ellipse2D.Double(-10, -65, 20, 20));

        //Add outline to circle
            Area outline=new Area(new Ellipse2D.Double(-100,-100,200,200));
        //Slight tuning of inverse so that outline would work properly
            Area inverse=new Area(new Ellipse2D.Double(-98,-99,202,198));
        //remove the inverse from the outline to provide the border to white part
            outline.subtract(inverse);

        //Constructive Area Geometry (Forming the actual yinYang)
            drawing.subtract(leftRemoval); //removes left half of circle
            drawing.subtract(blackTail); //Removes the area to create the black tail and white head
            drawing.add(whiteTail); //Add area of black head. This also creates the white tail
            drawing.add(blackDot); //Add black dot in middle of white head
            drawing.subtract(whiteDot); //Remove area so there is white dot on black head
            drawing.add(outline); //Add the outline so white section is visible

        //Flip the image so it matches the example one with white head on top
            AffineTransform at = new AffineTransform(new double[] {1.0,0.0,0.0,-1.0});
            Shape newShape = at.createTransformedShape(drawing);

        return newShape;
    }

    public static Shape createSpirograph()
    {
        //create variables that were given
            double r1=30;
            double r2=40;
            double p=60;
            double x,y;

        //Create Path2D object that we will tell where to draw
            Path2D.Double line=new Path2D.Double();
            line.setWindingRule(0);//even-odd winding rule (Fills in one section and leaves the next white)
        //Line starts near middle of screen
            line.moveTo(100,100);
        //Loop that goes through all values of tand draws         
            for(double t=8*Math.PI;t>=0;t-=0.01) //subtracting 0.02 would make 1000 line segments. However it makes a missing line in shape
            {
                //Formula to calculate where the line needs to go
                    x = (r1+r2)*Math.cos(t) - p*Math.cos((r1+r2)*t/r2);
                    y = (r1+r2)*Math.sin(t) - p*Math.sin((r1+r2)*t/r2);
                //Draw a line to those coordinates
                    line.lineTo(x,y);
            } 

        return line;
    }
}