package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;

public class Arm extends SubsystemBase{
    private CANSparkMax shoulderMotor1 = new CANSparkMax(armConstants.shoulderMotor1, MotorType.kBrushless);
    private CANSparkMax shoulderMotor2 = new CANSparkMax(armConstants.shoulderMotor2, MotorType.kBrushless);
    private CANSparkMax elbowMotor1 = new CANSparkMax(armConstants.elbowMotor1, MotorType.kBrushless);
    private CANSparkMax elbowMotor2 = new CANSparkMax(armConstants.elbowMotor2, MotorType.kBrushless);
    private CANSparkMax wristMotor = new CANSparkMax(armConstants.wristMotor, MotorType.kBrushless);

    public static Encoder shoulderEncoder = new Encoder(armConstants.shoulderEncoder.getFirst(),
        armConstants.shoulderEncoder.getSecond());
    public static Encoder elbowEncoder = new Encoder(armConstants.elbowEncoder.getFirst(),
        armConstants.elbowEncoder.getSecond());
    public static Encoder wristEncoder = new Encoder(armConstants.wristEncoder.getFirst(), 
        armConstants.wristEncoder.getSecond());
    
    public Arm(){
        shoulderMotor2.follow(shoulderMotor1, true);
        elbowMotor2.follow(elbowMotor1, true);
    }

    public void setShoulderSpeed(double speed){
        shoulderMotor1.set(speed);
    }

    public void setElbowSpeed(double speed){
        elbowMotor1.set(speed);

    }

    public void setWristSpeed(double speed, boolean reverse){
        if (reverse == false){
            wristMotor.set(speed);
        }
        else{
            wristMotor.set(-speed);    
        }
    }
}
