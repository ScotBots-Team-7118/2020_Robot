package frc.robot;

// Imports
import java.util.HashMap;
import frc.controlstation.JoystickWrapper;

public class ControlStation
{
    HashMap<String, JoystickWrapper> controllers;

    public ControlStation()
    {
        controllers = new HashMap<String, JoystickWrapper>();
    }

    private JoystickWrapper joystick(String joy)
    {
        return controllers.get(joy);
    }

    public void addJoystick(String name, int port)
    {
        controllers.put(name, new JoystickWrapper(port));
    }

    public void assignButton(String function, String joy, int number)
    {
        joystick(joy).addButton(function, number);
    }

    public void assignAxis(String function, String joy, int number)
    {
        joystick(joy).addAxis(function, number);
    }

    public boolean getButton(String button, String joy)
    {
        return joystick(joy).getButton(button);
    }

    public double getAxis(String axis, String joy)
    {
        return joystick(joy).getAxis(axis);
    }

    public void invertAxis(String axis, String joy)
    {
        joystick(joy).invertAxis(axis);
    }

    public void setDeadzone(String axis, String joy, double zone)
    {
        joystick(joy).setAxisDeadzone(axis, zone);
    }
}