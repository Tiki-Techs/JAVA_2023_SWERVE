package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class ArmPositioner extends CommandBase {
    Arm m_Arm;
    double elbowPos;
    double shoulderPos;
    public ArmPositioner(Arm _arm, double _shoulderPos, double _elbowPos) {
        m_Arm = _arm;
        elbowPos = _elbowPos;
        shoulderPos = _shoulderPos;

        DefaultArm.setpoint = elbowPos;
        addRequirements(m_Arm);
    }

    @Override
    public void execute() {
        m_Arm.setElbowSpeed(DefaultArm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), DefaultArm.setpoint));

        if (Arm.shoulderEncoder.getDistance() < shoulderPos) 
        {
            m_Arm.setShoulderSpeed(0.3);
        }
        else if (Arm.shoulderEncoder.getDistance() > shoulderPos){
            m_Arm.setShoulderSpeed(-0.3);
        }
        else{
            m_Arm.setShoulderSpeed(0);
        }

    }

    @Override
    public boolean isFinished() {
        if(Math.abs(RobotContainer.m_mechController.getLeftY()) > 0.07 || Math.abs(RobotContainer.m_mechController.getRightY()) > 0.07)
        {
            m_Arm.setShoulderSpeed(0); 
            return true;
        }
        if(DefaultArm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), DefaultArm.setpoint) < 0.05
        && (Arm.shoulderEncoder.getDistance() < shoulderPos - 15 && Arm.shoulderEncoder.getDistance() > shoulderPos + 15))
        {
            m_Arm.setShoulderSpeed(0);
            return true;
        }
        return false;
    }
}
