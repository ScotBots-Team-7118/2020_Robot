package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
// /* Imports */
// import java.util.HashMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class DriveBase implements RobotWrapper
{

    /**
     * Constant variables
     */

    final int TAL_RM_PORT = 5;
    final int TAL_RF_PORT = 4;
    final int TAL_LM_PORT = 3;
    final int TAL_LF_PORT = 2;

     /**
     * A set of enums to describe what style of drive system is being used.
     */
    public enum DriveType
    {   // Manoli will fix later, don't worry :D
        TANK, //...
    }

    DriveType drive;

    /**
     * Object declarations
     */
    TalonSRX talRM, talRF, talLM, talLF;
    Joystick xbox;
    
    /**
     * Non-constant variables
     */
    // HashMap<String, TalonSRX> motorControllers;
    // HashMap<String, Integer> motorControllerPorts = new HashMap<String, Integer>();


    /**
     * Constructs a new DriveBase object.
     * @param type of DriveBase that the robot is using.
     */
    public DriveBase(DriveType type)
    {
        talRM = new TalonSRX(TAL_RM_PORT);
        talRF = new TalonSRX(TAL_RF_PORT);
        talLM = new TalonSRX(TAL_LM_PORT);
        talLF = new TalonSRX(TAL_LF_PORT);
        xbox = new Joystick(0);

        // Manoli will fix later, don't worry :D
        // drive = type;   //Initialize Drivetype enum as [type].
        // motorControllers = new HashMap<String, TalonSRX>();

        // switch(drive)
        // {
        //     case TANK:
        //     motorControllers.put("LM", new TalonSRX(0));
        //     motorControllers.put("LF", new TalonSRX(0));
        //     motorControllers.put("RM", new TalonSRX(0));
        //     motorControllers.put("RF", new TalonSRX(0));
        // }
        // setDrive();    //Set DriveType to given configuration
    }

    public void init(RunMode mode)
    {
        
    }

    public void update(RunMode mode)
    {
        if(mode == RunMode.Test)
        {
            joystickDrive(xbox.getRawAxis(1), -xbox.getRawAxis(5));
        }
    }

    public void reset()
    {
        
    }

    public void joystickDrive(double axisL, double axisR)
    {
        if(Math.abs(axisL) > 0.9)
        {
            talLM.set(ControlMode.PercentOutput, (0.9 / 2) * (axisL/Math.abs(axisL)));
            talLF.set(ControlMode.Follower, TAL_LM_PORT);
        }
        else if (Math.abs(axisL) < 0.1)
        {
            talLM.set(ControlMode.PercentOutput, 0);
            talLF.set(ControlMode.Follower, TAL_LM_PORT);
        }
        else
        {
            talLM.set(ControlMode.PercentOutput, axisL / 2);
            talLF.set(ControlMode.Follower, TAL_LM_PORT);
        }
        if(Math.abs(axisR) > 0.9)
        {
            talRM.set(ControlMode.PercentOutput, (0.9 / 2) * (axisR/Math.abs(axisR)));
            talRF.set(ControlMode.Follower, TAL_RM_PORT);
        }
        else if (Math.abs(axisR) < 0.1)
        {
            talRM.set(ControlMode.PercentOutput, 0);
            talRF.set(ControlMode.Follower, TAL_RM_PORT);
        }
        else
        {
            talRM.set(ControlMode.PercentOutput, axisR / 2);
            talRF.set(ControlMode.Follower, TAL_RM_PORT);
        }
    }
}
