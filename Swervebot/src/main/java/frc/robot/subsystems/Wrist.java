package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class Wrist extends SubsystemBase {
    private CANSparkMax wristMotor = new CANSparkMax(armConstants.wristMotor, MotorType.kBrushless); 

    public void setWristSpeed(double speed, boolean reverse){
        if (reverse == false){
            wristMotor.set(speed);
        }
        else{
            wristMotor.set(-speed);    
        }
    }
}
