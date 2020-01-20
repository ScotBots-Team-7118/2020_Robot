package frc.robot;

//Imports
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake implements RobotWrapper{

    /**
     * Constants
     */
    final int TAL_I = 0;
    //final int --> solenoid ports

    /**
     * Objects
     */
    TalonSRX intakeMotor;

    public Intake(){
        intakeMotor = new TalonSRX(TAL_I);
    }


    public void init(RunMode mode){
        //run electric solenoid
    }

    public void update(RunMode mode){
        
    }
    
    public void reset(){

    }
}