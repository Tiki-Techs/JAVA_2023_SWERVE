package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Wrist;

public class WristTurner extends CommandBase{

    Wrist wrist;
    public WristTurner(Wrist _Wrist)
    {
        wrist = _Wrist;
        addRequirements(_Wrist);
    }

    @Override
    public void execute() {
        if(RobotContainer.m_mechController.getLeftTriggerAxis() > 0.05) {
            wrist.setWristSpeed(RobotContainer.m_mechController.getLeftTriggerAxis()/8, false);
        }
        else if(RobotContainer.m_mechController.getRightTriggerAxis() > 0.05) {
            wrist.setWristSpeed(RobotContainer.m_mechController.getRightTriggerAxis()/8, true);
        }
        else {
            wrist.setWristSpeed(0, false);
        }
    }

}
