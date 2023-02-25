// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivebaseConstants;
public class Drivebase extends SubsystemBase {
 
  private CANSparkMax FL_drive = new CANSparkMax(DrivebaseConstants.FL_DRIVE, MotorType.kBrushless);
  private CANSparkMax FR_drive = new CANSparkMax(DrivebaseConstants.FR_DRIVE, MotorType.kBrushless);
  private CANSparkMax BL_drive = new CANSparkMax(DrivebaseConstants.BL_DRIVE, MotorType.kBrushless);
  private CANSparkMax BR_drive = new CANSparkMax(DrivebaseConstants.BR_DRIVE, MotorType.kBrushless);

  MotorControllerGroup lGroup = new MotorControllerGroup(FL_drive, BL_drive);
  MotorControllerGroup RGroup = new MotorControllerGroup(FR_drive, BR_drive);

  DifferentialDrive drive = new DifferentialDrive(lGroup, RGroup);

  public Drivebase() {}

  public void DoDrivingFR(double speed, double angle){
    drive.arcadeDrive(speed, angle);
  }


}
