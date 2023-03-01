package frc.robot.commands;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.armConstants;
import frc.robot.subsystems.Arm;

public class DefaultArm extends CommandBase{
    Arm m_Arm;
    public DefaultArm(Arm subsystem) {
        m_Arm = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
      }

      ArmFeedforward elbowFeedForward = new ArmFeedforward(armConstants.elbowKs, armConstants.elbowKg, armConstants.elbowKv);
      ArmFeedforward shoulderFeedForward = new ArmFeedforward(0, 0, 0);
      PIDController elbowPID = new PIDController(0.001, 0, 0.000);
      PIDController shoulderPID = new PIDController(0, 0, 0);

      public double ElbowSetPoint = -400;
      public double ShoulderSetPoint = -400;

      public void elbowDefaultCommand(double elbowSetPoint){
        m_Arm.setElbowSpeed(elbowPID.calculate(Arm.elbowEncoder.getDistance(), elbowSetPoint));
        if (RobotContainer.m_mechController.getRightY() > 0.3){
          elbowSetPoint = elbowSetPoint + (RobotContainer.m_mechController.getRightY() * 15);
        }
        if (RobotContainer.m_mechController.getRightY() < -0.3){
          elbowSetPoint = elbowSetPoint + (RobotContainer.m_mechController.getRightY() * 15);
        }
      }

      public void shoulderDefaultCommand(double shoulderSetPoint){
        m_Arm.setShoulderSpeed(shoulderPID.calculate(Arm.shoulderEncoder.getDistance(), shoulderSetPoint));
        if (RobotContainer.m_mechController.getLeftY() > 0.3){
          shoulderSetPoint = shoulderSetPoint + (RobotContainer.m_mechController.getLeftY() * 15);
        }
        if (RobotContainer.m_mechController.getLeftY() < -0.3){
          shoulderSetPoint = shoulderSetPoint + (RobotContainer.m_mechController.getLeftY() * 15);
        }
      }

      @Override
      public void execute() { 
        shoulderDefaultCommand(ShoulderSetPoint);
        elbowDefaultCommand(ElbowSetPoint);
      }
}
