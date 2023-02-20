// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants.swerveConstants;

public class SwerveModule {

  private static final double kModuleMaxAngularAcceleration =
      2 * Math.PI; // radians per second squared

  private final CANSparkMax m_driveMotor;
  private final CANSparkMax m_turningMotor;

  private final RelativeEncoder m_driveEncoder;
  private final RelativeEncoder m_turningEncoder;

  private boolean turningReversed;

  // Gains are for example purposes only - must be determined for your own robot!
  private final PIDController m_drivePIDController = new PIDController(1, 0, 0);

  // Gains are for example purposes only - must be determined for your own robot!
  private final SparkMaxPIDController m_turningPIDController ;

  // Gains are for example purposes only - must be determined for your own robot!
  private final SimpleMotorFeedforward m_driveFeedforward = new SimpleMotorFeedforward(1, 3);
  private final SimpleMotorFeedforward m_turnFeedforward = new SimpleMotorFeedforward(1, 0.5);

  /**
   * Constructs a SwerveModule with a drive motor, turning motor, drive encoder and turning encoder.
   *
   * @param driveMotorChannel PWM output for the drive motor.
   * @param turningMotorChannel PWM output for the turning motor.
   * @param driveEncoderChannelA DIO input for the drive encoder channel A
   * @param driveEncoderChannelB DIO input for the drive encoder channel B
   * @param turningEncoderChannelA DIO input for the turning encoder channel A
   * @param turningEncoderChannelB DIO input for the turning encoder channel B
   */
  public SwerveModule(
      int driveMotorChannel,
      int turningMotorChannel,
      int turningEncoderChannelA,
      int turningEncoderChannelB,
      boolean turningMotorReversed,
      boolean driveMotorReversed
      ) {
    m_driveMotor = new CANSparkMax(driveMotorChannel, MotorType.kBrushless);
    m_turningMotor = new CANSparkMax(turningMotorChannel, MotorType.kBrushless);

    m_driveEncoder = ((CANSparkMax)m_driveMotor).getEncoder();
    
    m_turningEncoder = ((CANSparkMax)m_turningMotor).getEncoder();

    // Set the distance per pulse for the drive encoder. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    m_driveEncoder.setVelocityConversionFactor(swerveConstants.kDriveEncoderRPM2MeterPerSec);
    m_driveEncoder.setPositionConversionFactor(swerveConstants.kDriveEncoderRot2Meter);

    // Set the distance (in this case, angle) in radians per pulse for the turning encoder.
    // This is the the angle through an entire rotation (2 * pi) divided by the
    // encoder resolution.
    m_turningEncoder.setVelocityConversionFactor(swerveConstants.kTurningEncoderRPM2RadPerSec);
    m_turningEncoder.setPositionConversionFactor(swerveConstants.kTurningEncoderRot2Rad);

    // Limit the PID Controller's input range between -pi and pi and set the input
    // to be continuous.
    m_turningPIDController = m_turningMotor.getPIDController();
    configureMotor(m_driveMotor, driveMotorReversed);
    configureMotor(m_turningMotor, driveMotorReversed);
  }

  private void configureMotor(CANSparkMax motor, Boolean inverted) {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setInverted(inverted);
    motor.setSmartCurrentLimit(40);
    motor.burnFlash();
}

  /**
   * Returns the current state of the module.
   *
   * @return The current state of the module.
   */
  public SwerveModulePosition getPosition() {
      if(turningReversed){
        return new SwerveModulePosition(m_driveEncoder.getPosition(), Rotation2d.fromDegrees(-getAbsoluteEncoderRad()));
      }

        return new SwerveModulePosition(m_driveEncoder.getPosition(), Rotation2d.fromDegrees(getAbsoluteEncoderRad()));
    }

    public double getDriveVelocity() {
        return m_driveEncoder.getVelocity();
    }

    public double getAbsoluteEncoderRad() {
        return m_turningEncoder.getPosition();
    }

    public void resetEncoders() {
        m_driveEncoder.setPosition(0);
        m_turningEncoder.setPosition(0);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            m_driveEncoder.getVelocity() + m_turningEncoder.getVelocity() * swerveConstants.kWheelRadius / 2,
            new Rotation2d(m_turningEncoder.getPosition()));
      }
    

    public void setDesiredState(SwerveModuleState state) {
     
    double desiredDrive = state.speedMetersPerSecond / swerveConstants.kMaxAngularSpeed;

    if (Math.abs(desiredDrive) < 0.05) {
      m_driveMotor.set(0);
      return;
    }
    double desiredSteering = state.angle.getRadians();
    double currentSteering = m_turningEncoder.getPosition();

    // calculate shortest path to angle with forward drive (error -pi to pi)
    double steeringError = Math.IEEEremainder(desiredSteering - currentSteering, 2 * Math.PI);

    // reverse drive if error is larger than 90 degrees
    if (steeringError > Math.PI / 2) {
      steeringError -= Math.PI;
      desiredDrive *= -1;
    } else if (steeringError < -Math.PI / 2) {
      steeringError += Math.PI;
      desiredDrive *= -1;
    }

    double steeringSetpoint = currentSteering + steeringError;

    // m_driveMotor.set(desiredDrive + Math.cos(steeringError));
    m_driveMotor.set(desiredDrive);
    m_turningPIDController.set
    }

    public void stop() {
        m_driveMotor.set(0);
        m_turningMotor.set(0);
    }
}
