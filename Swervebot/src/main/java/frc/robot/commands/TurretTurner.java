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

    // spinning turret command 
    public void turnTurret(){
        if(RobotContainer.m_driverController.getLeftTriggerAxis() > 0.05)
        {
            m_Turret.TurnTurret(RobotContainer.m_driverController.getLeftTriggerAxis()/10);
        }
        else if(RobotContainer.m_driverController.getRightTriggerAxis() > 0.05)
        {
            m_Turret.TurnTurret(-RobotContainer.m_driverController.getRightTriggerAxis()/10);
        }
        else
        {
            m_Turret.TurnTurret(0);
        }
    }
    @Override 
    public void execute() {
        turnTurret();
    } 
}
