package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.armConstants;
import frc.robot.commands.DefaultArm;

public class Arm extends SubsystemBase{
    
    public CANSparkMax shoulderMotor1 = new CANSparkMax(armConstants.shoulderMotor1, MotorType.kBrushless);
    private CANSparkMax shoulderMotor2 = new CANSparkMax(armConstants.shoulderMotor2, MotorType.kBrushless);
    public CANSparkMax elbowMotor1 = new CANSparkMax(armConstants.elbowMotor1, MotorType.kBrushless);
    private CANSparkMax elbowMotor2 = new CANSparkMax(armConstants.elbowMotor2, MotorType.kBrushless);


    public static Encoder shoulderEncoder = new Encoder(armConstants.shoulderEncoder.getFirst(), armConstants.shoulderEncoder.getSecond());
    public static Encoder elbowEncoder = new Encoder(armConstants.elbowEncoder.getFirst(), armConstants.elbowEncoder.getSecond());

    public DigitalInput shoulderLimit = new DigitalInput(armConstants.shoulderLimitSwitch);
    public DigitalInput elbowLimit = new DigitalInput(armConstants.elbowLimitSwitch);
    

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

    public void moveToPosition(double shoulderPos, double elbowPos){
    
        DefaultArm.setpoint = elbowPos;
        while(elbowEncoder.getDistance() < elbowPos - 15 || elbowEncoder.getDistance() > elbowPos + 15){
            setElbowSpeed(DefaultArm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), DefaultArm.setpoint));
            if(DefaultArm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), DefaultArm.setpoint) < 0.05)
            {
                break;
            }
        }
        setElbowSpeed(0);
        while(shoulderEncoder.getDistance() < shoulderPos - 15 || shoulderEncoder.getDistance() > shoulderPos + 15)
        {
            if(shoulderEncoder.getDistance() < shoulderPos) 
            {
                setShoulderSpeed(0.3);
            }
            else if (shoulderEncoder.getDistance() > shoulderPos){
                 setShoulderSpeed(-0.3);
            }
            else{
                setShoulderSpeed(0);
                break;
            }
        }
        setShoulderSpeed(0.0);
        CommandScheduler.getInstance().cancelAll();
      }


    @Override
    public void periodic(){
        SmartDashboard.putNumber("shoulder encoder", shoulderEncoder.getDistance());
        SmartDashboard.putNumber("elbow encoder", elbowEncoder.getDistance());
        SmartDashboard.putBoolean("shoulder limit switch", shoulderLimit.get());
        SmartDashboard.putBoolean("elbow limit switch", elbowLimit.get());
        SmartDashboard.putNumber("shoulder motor output", shoulderMotor1.getAppliedOutput());
        
        if(elbowLimit.get() == false)
        {
          elbowEncoder.reset();
        } 
        if(shoulderLimit.get() == false)
        {
          shoulderEncoder.reset();
        } 

    }

}
