package frc.robot;

/* Imports */
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.ControlStation.JoyName;

/**
 * Framework to operate the drive train on a robot.
 * Currently implemented for tank drive using an xbox controller.
 */
public class DriveBase implements RobotWrapper
{
    /* Enum Definitions */

     /**
     * A set of enums to describe what style of drive system is being used.
     */
    public enum DriveType
    {
        XBOX_TANK //...
    }
    
    /**
     * A set of enums with the different names of the Talons to be initialized.
     */
    public enum Talon
    {
        LM, LF, RM, RF
    }


    /* Class Variable Declarations */

    DriveType type;
    Timer timer;
    ControlStation controlStation;


    /* HashMap Declarations */

    /**
     * Key (Talon): Name of a Talon as given by the enum list.
     * Value (Integer): The corresponding port number for that Talon.
     */
    HashMap<Talon, Integer> motorControllerPorts;

    /**
     * Key (Talon): Name of a Talon as given by the enum list.
     * Value (TalonSRX): The corresponding instance of the TalonSRX wrapper class.
     */
    HashMap<Talon, TalonSRX> motorControllers;

    /**
     * Constructs a new DriveBase object.
     * @param type of DriveBase that the robot is using.
     */
    public DriveBase(DriveType type)
    {
        /* Class Variable Initialization */
        timer = new Timer();
        controlStation = Robot.controlStation;  // Use the instance of control station from Robot.java
        this.type = type;   // Initialize Drivetype enum as [type].

        /* HashMap Initialization */
        motorControllerPorts = new HashMap<Talon, Integer>();
        motorControllers = new HashMap<Talon, TalonSRX>();

        switch(type)   // Added initialization steps based on the given DriveType
        {
            case XBOX_TANK:
            motorControllerPorts.put(Talon.LM, /* LM Port Here */ 3);   // Add each talon port number to motorControllerPorts
            motorControllerPorts.put(Talon.LF, /* LF Port Here */ 2);
            motorControllerPorts.put(Talon.RM, /* RM Port Here */ 5);
            motorControllerPorts.put(Talon.RF, /* RF Port Here */ 4);

            motorControllers.put(Talon.LM, new TalonSRX(motorControllerPorts.get(Talon.LM)));   // Add each talon object to motorControllers
            motorControllers.put(Talon.LF, new TalonSRX(motorControllerPorts.get(Talon.LF)));
            motorControllers.put(Talon.RM, new TalonSRX(motorControllerPorts.get(Talon.RM)));
            motorControllers.put(Talon.RF, new TalonSRX(motorControllerPorts.get(Talon.RF)));

            invertController(Talon.RM);
            invertController(Talon.RF);

            controlStation.addJoystick(JoyName.XBOX, /* Xbox Controller Port Here */ 0);
            controlStation.assignAxis("Left", JoyName.XBOX, /* Left Side Xbox Axis Here */ 1);
            controlStation.assignAxis("Right", JoyName.XBOX, /* Right Side Xbox Axis Here */ 5);
            
            controlStation.invertAxis("Left", JoyName.XBOX);
            controlStation.invertAxis("Right", JoyName.XBOX);
            controlStation.setDeadzone("Left", JoyName.XBOX, /* Left side deadzone here */ 0.1);
            controlStation.setDeadzone("Right", JoyName.XBOX, /* Right side deadzone here */ 0.1);

            break;

            default:
            System.out.println("Drive Base not initialized - No drive mode given for DriveBase.java.");
            
            break;
        }
    }

    /**
     * Initialization process for DriveBase.java. 
     * @param mode in which the robot is being run.
     */
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
            timer.reset();
            timer.start();
            break;
        }
    }

    /**
     * Periodic function for DriveBase.java.
     * @param mode in which the robot is being run.
     */
    public void update(RunMode mode)
    {
        switch(mode)
        {
            case Auto:

            break;

            case Teleop:
            joystickDrive(controlStation.getAxis("Left", JoyName.XBOX),
            controlStation.getAxis("Right", JoyName.XBOX));
            break;

            case Test:
            if (timer.get() < 4)
            {
                joystickDrive(0.5, 0.5);
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
        switch(type)
        {
            case XBOX_TANK:
            if(Math.abs(axisL) > 0.9)
            {
                motorControllers.get(Talon.LM).set(ControlMode.PercentOutput, (0.9 / 2) * (axisL/Math.abs(axisL)));
                motorControllers.get(Talon.LF).set(ControlMode.Follower, motorControllerPorts.get(Talon.LM));
            }
            else
            {
                motorControllers.get(Talon.LM).set(ControlMode.PercentOutput, axisL / 2);
                motorControllers.get(Talon.LF).set(ControlMode.Follower, motorControllerPorts.get(Talon.LM));
            }
            if(Math.abs(axisR) > 0.9)
            {
                motorControllers.get(Talon.RM).set(ControlMode.PercentOutput, (0.9 / 2) * (axisR/Math.abs(axisR)));
                motorControllers.get(Talon.RF).set(ControlMode.Follower, motorControllerPorts.get(Talon.RM));
            }
            else
            {
                motorControllers.get(Talon.RM).set(ControlMode.PercentOutput, axisR / 2);
                motorControllers.get(Talon.RF).set(ControlMode.Follower, motorControllerPorts.get(Talon.RM));
            }
            break;
        }
    }

    /**
     * Reversing forwards and backwards directions.
     * @param tal is the motor controller you want to invert.
     */
    private void invertController(Talon tal)
    {
        motorControllers.get(tal).setInverted(!motorControllers.get(tal).getInverted());
    }
}
