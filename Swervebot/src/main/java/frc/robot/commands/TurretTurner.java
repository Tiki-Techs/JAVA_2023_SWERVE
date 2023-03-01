package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Turret;

public class TurretTurner extends CommandBase{

    public Turret m_Turret;
    public TurretTurner(Turret t) {
        m_Turret = t;
        addRequirements(m_Turret);
    }

    @Override 
    public void execute() {
        if(RobotContainer.m_mechController.getLeftTriggerAxis() > 0.05)
        {
            m_Turret.TurnTurret(RobotContainer.m_mechController.getLeftTriggerAxis());
        }
        else if(RobotContainer.m_mechController.getRightTriggerAxis() > 0.05)
        {
            m_Turret.TurnTurret(-RobotContainer.m_mechController.getRightTriggerAxis());
        }
        else
        {
            m_Turret.TurnTurret(0);
        }

    } 
}
