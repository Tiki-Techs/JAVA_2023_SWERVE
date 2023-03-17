package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.intakeConstants;

public class Intake extends SubsystemBase {
    private DoubleSolenoid intake = 
    new DoubleSolenoid(Constants.PCH_ID, PneumaticsModuleType.REVPH, intakeConstants.intakeForward, intakeConstants.intakeReverse);

    public void extendIntake(){    
       intake.set(Value.kForward);
    }

    public void retractIntake(){
        intake.set(Value.kReverse);
    }
}
