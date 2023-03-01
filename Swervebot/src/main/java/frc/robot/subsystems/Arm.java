package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class Arm extends SubsystemBase{
    private CANSparkMax shoulderMotor1 = new CANSparkMax(armConstants.shoulderMotor1, MotorType.kBrushless);
    private CANSparkMax shoulderMotor2 = new CANSparkMax(armConstants.shoulderMotor2, MotorType.kBrushless);
    
    public Arm(){
        shoulderMotor2.follow(shoulderMotor1, true);
    }

    public void setArmSpeeds(double shoulderSpeed) {
        shoulderMotor1.set(shoulderSpeed);
    }
}
