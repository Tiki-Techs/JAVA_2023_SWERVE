package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;

public class SwerveDrivebase extends SubsystemBase {
  private CANSparkMax FL_drive = new CANSparkMax(DrivebaseConstants.FL_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX FL_steer = new WPI_TalonFX(DrivebaseConstants.FL_STEER);

  private CANSparkMax FR_drive = new CANSparkMax(DrivebaseConstants.FR_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX FR_steer = new WPI_TalonFX(DrivebaseConstants.FR_STEER);

  private CANSparkMax BL_drive = new CANSparkMax(DrivebaseConstants.BL_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX BL_steer = new WPI_TalonFX(DrivebaseConstants.BL_STEER);

  private CANSparkMax BR_drive = new CANSparkMax(DrivebaseConstants.BR_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX BR_steer = new WPI_TalonFX(DrivebaseConstants.BR_STEER);

  public static Encoder LEFT_FRONT_DRIVE_DISTANCE_ENCODER;
  public static Encoder LEFT_BACK_DRIVE_DISTANCE_ENCODER;
  public static Encoder RIGHT_FRONT_DRIVE_DISTANCE_ENCODER;
  public static Encoder RIGHT_BACK_DRIVE_DISTANCE_ENCODER;
  //public static MedianPIDSource DRIVE_DISTANCE_ENCODERS;

  public static Encoder LEFT_FRONT_DRIVE_DIRECTION_ENCODER;
  public static Encoder LEFT_BACK_DRIVE_DIRECTION_ENCODER;
  public static Encoder RIGHT_FRONT_DRIVE_DIRECTION_ENCODER;
  public static Encoder RIGHT_BACK_DRIVE_DIRECTION_ENCODER;
}
