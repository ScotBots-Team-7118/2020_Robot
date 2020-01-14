package frc.robot;

/* Imports */

/**
 * An interface meant to provide a basic layout for the wrapper classes used in Robot.java.
 */
public interface RobotWrapper
{

    /**
     * A set of enums used to define what mode the robot is in.
     * Used in conjunction with the init and update methods.
    */
    enum RunMode
    {
        Auto, Teleop, Test
    }


    /**
     * Abstract method representing the initalization process for this wrapper class.
     * @param mode The mode for which the class is being initialized.
     */
    public abstract void init(RunMode mode);
    
    /**
     * Abstract method representing the iterative program for this wrapper class.
     * @param mode The mode for which the program is being run.
     */
    public abstract void update(RunMode mode);
    
    /**
     * The reset process for this wrapper class after running the iterative program.
     */
    public abstract void reset();
}