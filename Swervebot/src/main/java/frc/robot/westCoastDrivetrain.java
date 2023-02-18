package frc.robot;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.swerveConstants;

public class westCoastDrivetrain extends SubsystemBase{
    
    private CANSparkMax m_frontLeft = new CANSparkMax(swerveConstants.frontLeftDriveMotor, MotorType.kBrushless);
    private CANSparkMax m_frontRight = new CANSparkMax(swerveConstants.frontRightDriveMotor, MotorType.kBrushless);
    private CANSparkMax m_backLeft = new CANSparkMax(swerveConstants.backLeftDriveMotor, MotorType.kBrushless);
    private CANSparkMax m_backRight = new CANSparkMax(swerveConstants.backRightDriveMotor, MotorType.kBrushless);

    public westCoastDrivetrain (){
        m_backLeft.follow(m_frontLeft, false);
        m_backRight.follow(m_frontLeft, false);
    }
    
    private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);

    public void arcadeDrive(double speed, double angle){
        m_drive.arcadeDrive(speed, angle);
      }
}
