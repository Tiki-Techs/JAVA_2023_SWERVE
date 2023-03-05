// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;
public class Drivebase extends SubsystemBase {
 
  private CANSparkMax FL_drive = new CANSparkMax(DrivebaseConstants.FL_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX FL_steer = new WPI_TalonFX(DrivebaseConstants.FL_STEER);
  private CANSparkMax FR_drive = new CANSparkMax(DrivebaseConstants.FR_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX FR_steer = new WPI_TalonFX(DrivebaseConstants.FR_STEER);
  private CANSparkMax BL_drive = new CANSparkMax(DrivebaseConstants.BL_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX BL_steer = new WPI_TalonFX(DrivebaseConstants.BL_STEER);
  private CANSparkMax BR_drive = new CANSparkMax(DrivebaseConstants.BR_DRIVE, MotorType.kBrushless);
  public static WPI_TalonFX BR_steer = new WPI_TalonFX(DrivebaseConstants.BR_STEER);

  MotorControllerGroup lGroup = new MotorControllerGroup(FL_drive, BL_drive);
  MotorControllerGroup RGroup = new MotorControllerGroup(FR_drive, BR_drive);

  //public DutyCycleEncoder brRotEncoder = new DutyCycleEncoder(0);

  PIDController blPID = new PIDController(0.00012, 0, 0.0000);
  PIDController brPID = new PIDController(0.00012, 0, 0.0000);
  PIDController flPID = new PIDController(0.00012, 0, 0.0000);
  PIDController frPID = new PIDController(0.00012, 0, 0.0000);

  DutyCycleEncoder frSteerEncoder = new DutyCycleEncoder(0);
  DutyCycleEncoder flSteerEncoder = new DutyCycleEncoder(2);
  DutyCycleEncoder brSteerEncoder = new DutyCycleEncoder(3);
  DutyCycleEncoder blSteerEncoder = new DutyCycleEncoder(1);

  DifferentialDrive drive = new DifferentialDrive(lGroup, RGroup);

  public Drivebase() {
   
  }

  public void DoDrivingFR(double speed, double angle){
    drive.arcadeDrive(speed, angle);
  }

  @Override
  public void periodic(){
    SmartDashboard.putNumber("front right encoder pos",frSteerEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("front left encoder pos",flSteerEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("back right encoder pos",brSteerEncoder.getAbsolutePosition());
    SmartDashboard.putNumber("back left encoder pos",blSteerEncoder.getAbsolutePosition());

    //SmartDashboard.putNumber("PID Front Left FR", swervePID.calculate(FR_steer.getSelectedSensorPosition(), -4500));
    //FR_steer.set(frPID.calculate(FR_steer.getSelectedSensorPosition(), 0));
    //FL_steer.set(flPID.calculate(FL_steer.getSelectedSensorPosition(), 0));
    //BR_steer.set(brPID.calculate(BR_steer.getSelectedSensorPosition(), 0));
    //BL_steer.set(blPID.calculate(BL_steer.getSelectedSensorPosition(), 0));
    //SmartDashboard.putNumber("BR Rotation", brRotEncoder.getAbsolutePosition());
  }

}
