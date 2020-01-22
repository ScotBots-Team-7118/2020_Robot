package frc.robot;

// Imports
import java.util.HashMap;
import frc.controlstation.JoystickWrapper;

public class ControlStation
{
    public enum JoyName {
        XBOX, //
    }

    HashMap<JoyName, JoystickWrapper> controllers;

    public ControlStation()
    {
        controllers = new HashMap<JoyName, JoystickWrapper>();
    }

    private JoystickWrapper joystick(JoyName joy)
    {
        return controllers.get(joy);
    }

    public void addJoystick(JoyName name, int port)
    {
        controllers.put(name, new JoystickWrapper(port));
    }

    public void assignButton(String function, JoyName joy, int number)
    {
        joystick(joy).addButton(function, number);
    }

    public void assignAxis(String function, JoyName joy, int number)
    {
        joystick(joy).addAxis(function, number);
    }

    public boolean getButton(String button, JoyName joy)
    {
        return joystick(joy).getButton(button);
    }

    public double getAxis(String axis, JoyName joy)
    {
        return joystick(joy).getAxis(axis);
    }

    public void invertAxis(String axis, JoyName joy)
    {
        joystick(joy).invertAxis(axis);
    }

    public void setDeadzone(String axis, JoyName joy, double zone)
    {
        joystick(joy).setAxisDeadzone(axis, zone);
    }
}