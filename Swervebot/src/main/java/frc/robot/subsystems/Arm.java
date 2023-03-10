package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DutyCycle;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;
import frc.robot.commands.ArmPosition;

public class Arm extends SubsystemBase{
    
    public CANSparkMax shoulderMotor1 = new CANSparkMax(armConstants.shoulderMotor1, MotorType.kBrushless);
    private CANSparkMax shoulderMotor2 = new CANSparkMax(armConstants.shoulderMotor2, MotorType.kBrushless);
    public CANSparkMax elbowMotor1 = new CANSparkMax(armConstants.elbowMotor1, MotorType.kBrushless);
    private CANSparkMax elbowMotor2 = new CANSparkMax(armConstants.elbowMotor2, MotorType.kBrushless);


    public static Encoder shoulderEncoder = new Encoder(armConstants.shoulderEncoder.getFirst(), armConstants.shoulderEncoder.getSecond());
    public static Encoder elbowEncoder = new Encoder(armConstants.elbowEncoder.getFirst(), armConstants.elbowEncoder.getSecond());

    public PIDController elbowPID = new PIDController(.5, 0, 0.0);
    public PIDController shoulderPID = new PIDController(0, 0, 0);

    public DigitalInput shoulderLimit = new DigitalInput(armConstants.shoulderLimitSwitch);
    public DigitalInput elbowLimit = new DigitalInput(armConstants.elbowLimitSwitch);
    
    ArmPosition currentArmPosition;

    // setting motors to follow the leaders
    public Arm(){
        shoulderMotor2.follow(shoulderMotor1, true);
        elbowMotor2.follow(elbowMotor1, true);
    }

    // setting shoulder speed
    public void setShoulderSpeed(double speed){
        if(shoulderLimit.get() == false && speed > 0) {
            shoulderMotor1.set(0);
        }
        else 
        {
            shoulderMotor1.set(speed);
        }
    }

    // setting elbow speed
    public void setElbowSpeed(double speed){
        if(elbowLimit.get() == false && speed < 0) {
            elbowMotor1.set(0);
        }
        else 
        {
            elbowMotor1.set(speed);
        }

    }

    
    public void resetEncoders(){
        // if(!elbowLimit.get()){
        //     elbowEncoder.reset();
        // }
        // if(!shoulderLimit.get()){
        //     shoulderEncoder.reset();
        // }
    }

    public void moveToPosition(ArmPosition pos){
        boolean hold = false;
        while(!hold) {
          hold = currentArmPosition.moveToHold();
        }
        
        boolean targetPos = false;
        while(!targetPos) {
          targetPos = pos.moveToPosition();
        }

        currentArmPosition = pos;
      }


    @Override
    public void periodic(){
        SmartDashboard.putNumber("shoulder encoder", shoulderEncoder.getDistance());
        SmartDashboard.putNumber("elbow encoder", elbowEncoder.getDistance());
       SmartDashboard.putBoolean("shoulder limit switch", shoulderLimit.get());
        SmartDashboard.putBoolean("elbow limit switch", elbowLimit.get());
        SmartDashboard.putNumber("shoulder motor output", shoulderMotor1.getAppliedOutput());
    }

}
