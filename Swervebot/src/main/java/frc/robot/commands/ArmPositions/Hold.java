package frc.robot.commands.ArmPositions;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.armConstants;
import frc.robot.subsystems.Arm;
import frc.robot.commands.DefaultArm;

public class Hold extends CommandBase{
    

    public double elbowSetPoint = 0.885;
    public double shoulderSetPoint = 0.221;

    
    @Override
    public void execute(){
        RobotContainer.m_Arm.setElbowSpeed(RobotContainer.m_Arm.elbowPID.calculate(Arm.elbowEncoder.getDistance(), elbowSetPoint));
        RobotContainer.m_Arm.setShoulderSpeed(RobotContainer.m_Arm.shoulderPID.calculate(Arm.shoulderEncoder.getDistance(), shoulderSetPoint));
    }
}
