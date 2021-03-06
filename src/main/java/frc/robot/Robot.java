/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/* Imports */
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.RobotWrapper.RunMode;
import frc.robot.DriveBase.DriveType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
  DriveBase driveBase;
  public static ControlStation controlStation;
  // Gyroscope gyro;
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit()
  {
    controlStation = new ControlStation();
    driveBase = new DriveBase(DriveType.XBOX_TANK);
    // gyro = new Gyroscope();
  }

  /**
   * This function is called during initialization for the autonomous period.
   */
  @Override
  public void autonomousInit()
  {
    // gyro.init(RunMode.Auto);
    driveBase.init(RunMode.Auto);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic()
  {
    // gyro.update(RunMode.Auto);
    driveBase.update(RunMode.Auto);
  }

  /**
   * This function is called during initialization for the teleop period.
   */
  @Override
  public void teleopInit() {
    // gyro.init(RunMode.Teleop);
    driveBase.init(RunMode.Teleop);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic()
  {
    // gyro.update(RunMode.Teleop);
    driveBase.update(RunMode.Teleop);
  }

  /**
   * This function is called during test mode initialization
   */
  @Override
  public void testInit()
  {
    driveBase.init(RunMode.Test);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic()
  {
    // gyro.update(RunMode.Test);
    driveBase.update(RunMode.Test);
  }

  @Override
  public void disabledInit()
  {
    driveBase.timer.stop();
  }
}
