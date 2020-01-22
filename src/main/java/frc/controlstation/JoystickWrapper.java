package frc.controlstation;

// Imports
import edu.wpi.first.wpilibj.Joystick;
import java.util.HashMap;

public class JoystickWrapper
{
    Joystick joy;
    HashMap<String, JoyAxis> axes;
    HashMap<String, JoyButton> buttons;

    public JoystickWrapper(int port)
    {
        joy = new Joystick(port);
        axes = new HashMap<String, JoyAxis>();
        buttons = new HashMap<String, JoyButton>();
    }

    public void addAxis(String axis, int number)
    {
        axes.put(axis, new JoyAxis(number, joy));
    }

    public double getAxis(String axis)
    {
        return axes.get(axis).get();
    }

    public void addButton(String button, int number)
    {
        buttons.put(button, new JoyButton(number, joy));
    }

    public boolean getButton(String button)
    {
        return buttons.get(button).get();
    }

    public void invertAxis(String axis)
    {
        axes.get(axis).invert();
    }

    public void setAxisDeadzone(String axis, double zone)
    {
        axes.get(axis).setDeadzone(zone);
    }
}