package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Wrist;

public class Balance extends CommandBase{

    private SwerveSubsystem swerve;
    //private final Timer m_timer = new Timer();
    private final Translation2d forward = new Translation2d(-50.0, 0.0);
    private final Translation2d reverse = new Translation2d(50.0, 0.0);
    private final Translation2d stop = new Translation2d(0.0, 0.0);
    private final Timer m_Timer = new Timer();
    private final Timer balance_Timer = new Timer();
    private double pitch;

    public Balance(SwerveSubsystem swerve)
    {
        this.swerve = swerve;
    }
    @Override
    public void initialize() {
        SmartDashboard.putBoolean("hihi", true);
        m_Timer.start();
        m_Timer.reset();
        balance_Timer.start();
        balance_Timer.reset();
        pitch = swerve.getPitch();
        swerve.zeroHeading();
        swerve.drive(stop, 0.0, true);
    }
    @Override
    public void execute() {
        SmartDashboard.putBoolean("hi", true);
        SmartDashboard.putNumber("Timer", m_Timer.get());
        if (m_Timer.get() < 100.0); {
            // SmartDashboard.putBoolean("hi", true);
            pitch = swerve.getPitch();
            SmartDashboard.putNumber("BALANCE", pitch);
            balance_Timer.reset();
            if(pitch > 1.0) {
                while (balance_Timer.get() < 0.5) {
                    swerve.drive(reverse, 0, true);
                }
            } else if (pitch < -1.0) {
                while (balance_Timer.get() < 0.5) {
                    swerve.drive(forward, 0, true);
                }
                
            } else if (pitch > -1.0 && pitch < 1.0){
                //swerve.drive(stop, 0, true);
            }
        }

    }
    @Override
    public boolean isFinished() {
        return false;
    }

}
