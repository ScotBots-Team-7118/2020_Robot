package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
// /* Imports */
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

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
        XBOX_TANK //...
    }

    DriveType drive;
    Timer timer;
    ControlStation controlStation;

    /**
     * Object declarations
     */
    TalonSRX talRM, talRF, talLM, talLF;
    Joystick xbox;
    
    /**
     * Non-constant variables
     */
    HashMap<String, Integer> motorControllerPorts;
    HashMap<String, TalonSRX> motorControllers;
    // HashMap<String, Joystick> joysticks;


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
        // xbox = new Joystick(0);
        timer = new Timer();
        controlStation = Robot.controlStation;

        // Manoli will fix later, don't worry :D
        drive = type;   // Initialize Drivetype enum as [type].

        motorControllerPorts = new HashMap<String, Integer>();
        motorControllers = new HashMap<String, TalonSRX>();
        // joysticks = new HashMap<String, Joystick>();

        switch(drive)
        {
            case XBOX_TANK:
            motorControllerPorts.put("LM", /* LM Port Here */ 0);
            motorControllerPorts.put("LF", /* LF Port Here */ 0);
            motorControllerPorts.put("RM", /* RM Port Here */ 0);
            motorControllerPorts.put("RF", /* RF Port Here */ 0);

            motorControllers.put("LM", new TalonSRX(motorControllerPorts.get("LM")));
            motorControllers.put("LF", new TalonSRX(motorControllerPorts.get("LF")));
            motorControllers.put("RM", new TalonSRX(motorControllerPorts.get("RM")));
            motorControllers.put("RF", new TalonSRX(motorControllerPorts.get("RF")));

            invertController("RM");
            invertController("RF");

            controlStation.addJoystick("Xbox", /* Xbox Controller Port Here */ 0);
            controlStation.assignAxis("Left Axis", "Xbox", /* Left Side Xbox Axis Here */ 1);
            controlStation.assignAxis("Right Axis", "Xbox", /* Right Side Xbox Axis Here */ 5);

            break;

            default:
            System.out.println("Drive Base not initialized - No drive mode given for DriveBase.java.");
            
            break;
        }
    }

    public void init(RunMode mode)
    {
        timer.reset();
        switch(mode)
        {
            case Auto:
            reset();
            
            break;

            case Teleop:
            break;

            case Test:
            timer.start();
            break;
        }
    }

    public void update(RunMode mode)
    {
        // if(mode == RunMode.Test)
        // {
        //     joystickDrive(xbox.getRawAxis(1), -xbox.getRawAxis(5));
        // }

        switch(mode)
        {
            case Auto:

            break;

            case Teleop:
            joystickDrive(-xbox.getRawAxis(1), -xbox.getRawAxis(5));
            break;

            case Test:
            if (timer.get() < 2)
            {
                joystickDrive(1/2, 1/2);
            }
            else
            {
                joystickDrive(0, 0);
                timer.stop();
            }
            break;
        }
    }

    public void reset()
    {
        
    }

    public void joystickDrive(double axisL, double axisR)
    {
        switch(drive)
        {
            case XBOX_TANK:

            break;
        }
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

        switch(drive)
        {
            case XBOX_TANK:
            
        }
    }

    private void invertController(String c)
    {
        motorControllers.get(c).setInverted(!motorControllers.get(c).getInverted());
    }
}
