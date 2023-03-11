package frc.robot.commands;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.armConstants;
import frc.robot.subsystems.Arm;

public class DefaultArm extends CommandBase{
    public static Arm m_Arm;
    public DefaultArm(Arm subsystem) {
        m_Arm = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
      }

     // ArmFeedforward elbowFeedForward = new ArmFeedforward(armConstants.elbowKs, armConstants.elbowKg, armConstants.elbowKv); // also dont need this
      //ArmFeedforward shoulderFeedForward = new ArmFeedforward(0, 0, 0); // we dont need this but lol
      //public double elbowSetPoint = 0.331; // setpoint for the pid, don't need this unless we start using pid in comp :)

      
      // default command for the elbow pid, don't use this unless we use pid again 
      // public void elbowDefaultCommand(double elbowSetPoint){
      //   m_Arm.setElbowSpeed(RobotContainer.m_Arm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), elbowSetPoint));
      //   if (RobotContainer.m_mechController.getRightY() > 0.3){
      //     elbowSetPoint = elbowSetPoint + (RobotContainer.m_mechController.getRightY() / 1000);
      //   }
      //   if (RobotContainer.m_mechController.getRightY() < -0.3){
      //     elbowSetPoint = elbowSetPoint + (RobotContainer.m_mechController.getRightY() /1000);
      //   }
      // }

      // default command for the shoulder pid, don't use unless we are pid again
      // public void shoulderDefaultCommand(double shoulderSetPoint){
      //  // m_Arm.setShoulderSpeed(shoulderPID.calculate(Arm.shoulderEncoder.getAbsolutePosition(), shoulderSetPoint));
      //   if (RobotContainer.m_mechController.getLeftY() > 0.3){
      //     shoulderSetPoint = shoulderSetPoint + (RobotContainer.m_mechController.getLeftY() / 1000);
      //   }
      //   if (RobotContainer.m_mechController.getLeftY() < -0.3){
      //     shoulderSetPoint = shoulderSetPoint + (RobotContainer.m_mechController.getLeftY() / 1000);
      //   }
      // }
      
      public void shoulderDefaultCommand(){
        // if(m_Arm.shoulderLimit.get() == true && RobotContainer.m_mechController.getLeftY() > 0){
        //   m_Arm.setShoulderSpeed(0);
        // }
        // else{
          double stickValue = RobotContainer.m_mechController.getLeftY();
          double m_setSpeed = 0.4*stickValue + 0.6* Math.pow(stickValue, 3);
          m_Arm.setShoulderSpeed(m_setSpeed);
        //}
      }

      public void elbowDefaultCommand(){
        // if(m_Arm.elbowLimit.get() == true && RobotContainer.m_mechController.getRightY() > 0){
        //   m_Arm.setElbowSpeed(0);
        // }
        // else{
          double stickValue = RobotContainer.m_mechController.getLeftY();
          double m_setSpeed = 0.4*stickValue + 0.6* Math.pow(stickValue, 3);
          m_Arm.setElbowSpeed(m_setSpeed/2);
        //}
      }
      

      // any commands that are running atm
      @Override
      public void execute() { 
       // m_Arm.setElbowSpeed(RobotContainer.m_mechController.getRightY()/5);//-RobotContainer.m_Arm.elbowPID.calculate(Arm.elbowEncoder.getAbsolutePosition(), elbowSetPoint));
        //elbowSetPoint = elbowSetPoint + RobotContainer.m_mechController.getRightY()/15;
        //m_Arm.setElbowSpeed(RobotContainer.m_Arm.elbowPID.calculate(Arm.elbowEncoder.getAbsolutePosition(), elbowSetPoint));
       
        //m_Arm.setShoulderSpeed(RobotContainer.m_mechController.getLeftY()/2); // runs the shoulder off of the controller
       // elbowDefaultCommand(.325);
        //SmartDashboard.putNumber("Elbow PID", m_Arm.elbowPID.calculate(Arm.elbowEncoder.getAbsolutePosition(), elbowSetPoint));
        //m_Arm.setElbowSpeed(-RobotContainer.m_mechController.getRightY()/2); // runs the elbow off of the controller
        //m_Arm.setElbowSpeed(RobotContainer.m_mechController.getRightY()/5);
        elbowDefaultCommand();
        shoulderDefaultCommand();
        
      }
}
