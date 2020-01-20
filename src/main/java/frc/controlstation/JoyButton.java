package frc.controlstation;

// Imports
import edu.wpi.first.wpilibj.Joystick;

public class JoyButton
{
    final int number;
    Joystick joy;

    public JoyButton(int buttonNumber, Joystick joy)
    {
        number = buttonNumber;
        this.joy = joy;
    }

    public boolean get()
    {
        return joy.getRawButton(number);
    }
}