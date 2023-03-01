// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.TurretTurner;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Turret;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivebase m_Drivebase = new Drivebase();
  private final Turret m_Turret = new Turret();
  private final Arm m_Arm = new Arm();

  


  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static final CommandXboxController m_driverController =
      new CommandXboxController(Constants.DriverController);

  public static final CommandXboxController m_mechController = 
    new CommandXboxController(Constants.MechController);

  public RobotContainer() {
    // Configure the trigger bindings
    m_Drivebase.setDefaultCommand(
       new RunCommand(
         () -> m_Drivebase.DoDrivingFR(m_driverController.getLeftY(), -m_driverController.getRightX()), m_Drivebase));
    
         m_Turret.setDefaultCommand(new TurretTurner(m_Turret));

    m_Arm.setDefaultCommand(
      new RunCommand(() -> m_Arm.setArmSpeeds(-m_mechController.getLeftY()), m_Arm));

         configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
   
  }

  
}
