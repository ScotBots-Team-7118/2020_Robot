package frc.controlstation;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Wrapper class for an axis on a specified joystick.
 * 
 * @author Manoli Tramountanas
 */
public class JoyAxis
{
    final int number;
    Joystick joy;
    double deadzone = 0;
    boolean isInverted = false;

    /**
     * 
     */
    public JoyAxis(int axisNumber, Joystick joy)
    {
        number = axisNumber;
        this.joy = joy;
    }

    public void invert()
    {
        isInverted = true;
    }

    public void setDeadzone(double zone)
    {
        deadzone = zone;
    }

    public double get()
    {
        if (Math.abs(joy.getRawAxis(number)) < deadzone)
        {
            return 0;
        }
        if (isInverted)
        {
            return -joy.getRawAxis(number);
        }
        return joy.getRawAxis(number);
    }
}