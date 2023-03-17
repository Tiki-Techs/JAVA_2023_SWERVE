package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class DefaultArm extends CommandBase{
    public static Arm m_Arm;
    public static PIDController elbowPID = new PIDController(0.001, 0, 0);
    public static double setpoint = 0;
    boolean elbowSaved = false;

      public DefaultArm(Arm subsystem) {
        m_Arm = subsystem;
        addRequirements(subsystem);
        setpoint = Arm.elbowEncoder.getDistance();
      }

      public void shoulderDefaultCommand(){
          double m_setSpeed;
          double stickValue = RobotContainer.m_mechController.getLeftY();
          if (stickValue < Constants.deadBand && stickValue > Constants.deadBand*(-1.0)) {
            m_setSpeed = 0;
          }
          else {
            m_setSpeed = 0.4*stickValue + 0.6 * Math.pow(stickValue, 3);
          }
          m_Arm.setShoulderSpeed(m_setSpeed);
      }

      public void elbowDefaultCommand(){
        if(Math.abs(RobotContainer.m_mechController.getRightY()) > 0.05){
          if(m_Arm.elbowLimit.get() == true || RobotContainer.m_mechController.getRightY() < 0)
          {
            setpoint -= RobotContainer.m_mechController.getRightY() * 20;
          }
          elbowSaved = false;
        }
        else if (!elbowSaved){
          setpoint = Arm.elbowEncoder.getDistance();
          elbowSaved = true;
        }
        m_Arm.setElbowSpeed(elbowPID.calculate(Arm.elbowEncoder.getDistance(), setpoint));
      }

      // any commands that are running atm
      @Override
      public void execute() { 

        elbowDefaultCommand();
        shoulderDefaultCommand();
        SmartDashboard.putNumber("Elbow PID",  elbowPID.calculate(Arm.elbowEncoder.getDistance(), setpoint));
        SmartDashboard.putNumber("Setpoint", setpoint);

      }
}
