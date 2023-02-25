// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.swerveConstants;

/** Represents a swerve drive style drivetrain. */
public class Drivetrain extends SubsystemBase{

  private final Translation2d m_frontLeftLocation = new Translation2d(swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS, swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS);
  private final Translation2d m_frontRightLocation = new Translation2d(swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS, -swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS);
  private final Translation2d m_backLeftLocation = new Translation2d(-swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS, swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS);
  private final Translation2d m_backRightLocation = new Translation2d(-swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS, -swerveConstants.MOTOR_DISTANCE_FROM_CENTER_METERS);

  private final SwerveModule m_frontLeft = new SwerveModule(swerveConstants.frontLeftDriveMotor, swerveConstants.frontLeftTurningMotor, swerveConstants.frontLeftTurnEncoder.getFirst(), swerveConstants.frontLeftTurnEncoder.getSecond(), true, true);
  private final SwerveModule m_frontRight = new SwerveModule(swerveConstants.frontRightDriveMotor, swerveConstants.frontRightTurningMotor, swerveConstants.frontRightTurnEncoder.getFirst(), swerveConstants.frontRightTurnEncoder.getSecond(), true, true);

  private final SwerveModule m_backLeft = new SwerveModule(swerveConstants.backLeftDriveMotor, swerveConstants.backLeftTurningMotor, swerveConstants.backLeftTurnEncoder.getFirst(), swerveConstants.backLeftTurnEncoder.getSecond(), false, true);
  private final SwerveModule m_backRight = new SwerveModule(swerveConstants.backRightDriveMotor, swerveConstants.backRightTurningMotor, swerveConstants.backRightTurnEncoder.getFirst(), swerveConstants.backRightTurnEncoder.getSecond(), false, true);

  private final AHRS m_gyro = new AHRS();

  private final SwerveDriveKinematics m_kinematics =
      new SwerveDriveKinematics(
          m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);

  private final SwerveDriveOdometry m_odometry =
      new SwerveDriveOdometry(
          m_kinematics,
          m_gyro.getRotation2d(),
          new SwerveModulePosition[] {
            m_frontLeft.getPosition(),
            m_frontRight.getPosition(),
            m_backLeft.getPosition(),
            m_backRight.getPosition()
          });

  public Drivetrain() {
    m_gyro.reset();
    m_frontLeft.resetEncoders();
    m_frontRight.resetEncoders();
    m_backLeft.resetEncoders();
    m_backRight.resetEncoders();
  }

  public void reset(){
    m_gyro.reset();
    m_frontLeft.resetEncoders();
    m_frontRight.resetEncoders();
    m_backLeft.resetEncoders();
    m_backRight.resetEncoders();
  }
  /**
   * Method to drive the robot using joystick info.
   *
   * @param xSpeed Speed of the robot in the x direction (forward).
   * @param ySpeed Speed of the robot in the y direction (sideways).
   * @param rot Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the field.
   */
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    var swerveModuleStates =
        m_kinematics.toSwerveModuleStates(
            fieldRelative
                ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, m_gyro.getRotation2d())
                : new ChassisSpeeds(xSpeed, ySpeed, rot));
    SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, swerveConstants.kMaxSpeed);
    m_frontLeft.setDesiredState(swerveModuleStates[0]);
    m_frontRight.setDesiredState(swerveModuleStates[1]);
    m_backLeft.setDesiredState(swerveModuleStates[2]);
    m_backRight.setDesiredState(swerveModuleStates[3]);
  }

  /** Updates the field relative position of the robot. */
  public void updateOdometry() {
    m_odometry.update(
        m_gyro.getRotation2d(),
        new SwerveModulePosition[] {
          m_frontLeft.getPosition(),
          m_frontRight.getPosition(),
          m_backLeft.getPosition(),
          m_backRight.getPosition()
        });
  }

  @Override
  public void periodic() {

         SmartDashboard.putNumber("Front left rotation", m_frontLeft.getPosition().angle.getDegrees());
         SmartDashboard.putNumber("Front left distance", m_frontLeft.getPosition().distanceMeters);
         SmartDashboard.putNumber("Front right rotation", m_frontRight.getPosition().angle.getDegrees());
         SmartDashboard.putNumber("back left rotation", m_backLeft.getPosition().angle.getDegrees());
         SmartDashboard.putNumber("Back right rotation", m_backRight.getPosition().angle.getDegrees());
  }
}