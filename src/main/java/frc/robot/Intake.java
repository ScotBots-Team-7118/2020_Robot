package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
//Imports
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

public class Intake implements RobotWrapper{

    /**
     * Constants
     */
    final int TAL_I = 0;
    final int SOL_I = 0;
    boolean deploy = true;
    final double IN_SPEED = 0;
    final double OUT_SPEED = 0;

    /**
     * Objects
     */
    TalonSRX intakeMotor;
    Solenoid intakeSolenoid;

    public Intake(){
        intakeMotor = new TalonSRX(TAL_I);
        intakeSolenoid = new Solenoid(SOL_I);
    }
 

    public void init(RunMode mode){
        if(deploy == true){
            intakeSolenoid.set(true);
        }
    }

    public void update(RunMode mode){
        //if controller button is true run solenoid
        
        //if controller button is true run motor
        if(/* */){
            intakeMotor.set(ControlMode.PercentOutput, IN_SPEED);
        }else if {
            intakeMotor.set(ControlMode.PercentOutput, OUT_SPEED);
        }
    }
    
    public void reset(){

    }
}