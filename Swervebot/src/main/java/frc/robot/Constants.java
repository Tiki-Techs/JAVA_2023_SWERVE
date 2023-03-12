// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int DriverController = 0;
  public static final int MechController = 1;
  public static final int ButtonBoard = 2;
  public static final double deadBand = 0.05; 

  public static final int PCH_ID = 18;

  public static class DrivebaseConstants {
    public static final int FL_DRIVE = 1;
    public static final int FL_STEER = 2;

    public static final int FR_DRIVE = 3;
    public static final int FR_STEER = 4;

    public static final int BL_DRIVE = 5;
    public static final int BL_STEER = 6;

    public static final int BR_DRIVE = 7;
    public static final int BR_STEER = 8;
  }
  
  public static final class chassisConstants{

    public static double deadband = 0.30;

    public static double kS = (0.667 / 12);//Test and revise if need
    public static double kV = (2.44 / 12);//Test and revise if need
    public static double kA = (0.27 / 12);//Test and revise if need

    public static double driveMotorGearRat = (6.54 / 1);
    public static double turnMotorGearRat = (11.8 / 1); 
    public static final double wheelDiameter = Units.inchesToMeters(4);
    public static double circumference = wheelDiameter * Math.PI;

    public static double maxSpeedMPS = 6.5;
    public static double maxTurnSpeed = 1.0;
    
    //Distance between the front and back wheels in inches
    public static final double wheelBase = (25);
    //Distance between left and right wheels in inches
    public static final double trackWidth = (25);

    public static PIDController xController = new PIDController(0, 0, 0);
    public static PIDController yController = new PIDController(0, 0, 0);
    public static final TrapezoidProfile.Constraints thetaConstraints = new TrapezoidProfile.Constraints(Math.PI, Math.PI);
    public static ProfiledPIDController thetaController = new ProfiledPIDController(0, 0, 0, thetaConstraints);

    public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
        new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
        new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));
  }

  public static final class chassisSetUp{
    //Front Left Module
    public static final int fLeftDriveMotorPort = 1;
    public static final int fLeftTurnMotorPort = 2;
    public static final boolean isFrontLeftDriveMotorReverse = true;
    public static final boolean isFrontLeftTurnMotorReverse = true;
    public static final int fLeftAbsoluteEncoder = 2;
    public static final double frontLAngle = Units.radiansToDegrees(5.67260846);
    public static final double frontLKP = 0.7;
    public static final double frontLKI = 0.0;
    public static final double frontLKD = 0.5;
    

    //Front Right Module
    public static final int fRightDriveMotorPort = 3;
    public static final int fRightTurnMotorPort = 4;
    public static final boolean isFrontRightDriveMotorReverse = true;
    public static final boolean isFrontRightTurnMotorReverse = true;
    public static final int fRightAbsoluteEncoder = 0;
    public static final double frontRAngle = Units.radiansToDegrees(0.1138786);
    public static final double frontRKP = 0.6;
    public static final double frontRKI = 0.0;
    public static final double frontRKD = 0.5;
  //Back Left Module
  public static final int bLeftDriveMotorPort = 5;
  public static final int bLeftTurnMotorPort = 6;
  public static final boolean isBackLeftDriveMotorReverse = true;
  public static final boolean isBackLeftTurnMotorReverse = true;
  public static final int bLeftAbsoluteEncoder = 1;
  public static final double backLAngle = Units.radiansToDegrees(0.2482969);
  public static final double backLKP = 0.6;
  public static final double backLKI = 0.0;
  public static final double backLKD = 0.5;

    //Back Right Module
    public static final int bRightDriveMotorPort = 7;
    public static final int bRightTurnMotorPort = 8;
    public static final boolean isBackRightDriveMotorReverse = true;
    public static final boolean isBackRightTurnMotorReverse = true;
    public static final int bRightAbsoluteEncoder = 3;
    public static final double backRAngle = Units.radiansToDegrees(1.81295729);
    public static final double backRKP = 0.7;
    public static final double backRKI = 0.0;
    public static final double backRKD = 0.5;

  

    //Gyro
    public static final boolean invertedGyro = false;
}

  public static final class TurretConstants {
    //public static final int EncoderPort = 0;
    public static final int turnerMotor = 9;
    public static final Pair<Integer, Integer> turretEncoder = new Pair<>(9, 8);
  }

  public static final class armConstants {
    public static final int shoulderMotor1 = 10;
    public static final int shoulderMotor2 = 11;
    public static final int elbowMotor1 = 12;
    public static final int elbowMotor2 = 13;
    public static final int wristMotor = 14;

    public static final Pair<Integer, Integer> shoulderEncoder = new Pair<>(7, 6);
    public static final Pair<Integer, Integer> elbowEncoder = new Pair<>(5, 4);

    // public static final int shoulderEncoder = 7;
    // public static final int elbowEncoder = 6;
    // public static final int wristEncoder = 2;

    public static final int elbowLimitSwitch = 22;
    public static final int shoulderLimitSwitch = 23;
  
  }

  public static final class intakeConstants {
    public static final int intakeForward = 8;
    public static final int intakeReverse = 9; 
  }
}