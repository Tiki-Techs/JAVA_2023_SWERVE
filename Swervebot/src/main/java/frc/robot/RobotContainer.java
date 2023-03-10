// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.DefaultArm;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.TurretTurner;
import frc.robot.commands.WristTurner;
import frc.robot.commands.ArmPositions.HighScore;
import frc.robot.commands.ArmPositions.Hold;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivebase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Wrist;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final SwerveSubsystem m_Drivebase = new SwerveSubsystem();
  private final Turret m_Turret = new Turret();
  public static final Arm m_Arm = new Arm();
  public final Intake m_Intake = new Intake();
  public final Wrist m_Wrist = new Wrist();
  private final Command m_SimpleAuto = new SimpleAuto(m_Drivebase, true);
  


  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static final CommandXboxController m_driverController =
      new CommandXboxController(Constants.DriverController);

  public static final CommandXboxController m_mechController = 
    new CommandXboxController(Constants.MechController);

  public static final GenericHID m_buttonBoard =
    new GenericHID(Constants.ButtonBoard);

  public RobotContainer() {
    // Configure the trigger bindings
    m_Drivebase.setDefaultCommand(new SwerveJoystickCmd(m_Drivebase, m_driverController, true));

    m_Arm.setDefaultCommand(new DefaultArm(m_Arm));
    
    m_Turret.setDefaultCommand(new TurretTurner(m_Turret));

    m_Wrist.setDefaultCommand(new WristTurner(m_Wrist));

    

         configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommndXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // new JoystickButton(m_mechController.getHID(), 1) //A button 
    // .onTrue(new Hold());

    // new JoystickButton(m_buttonBoard, 2)
    // .onTrue(new RunCommand(() -> m_Arm.moveToPosition(new HighScore()), m_Arm)); 

  //new JoystickButton(m_mechController.getHID(), 5)
  //   .whileTrue(new RunCommand(() -> m_Turret.TurnTurret(10), m_Turret));

  //   new JoystickButton(m_mechController.getHID(),6)
  //   .whileTrue(new RunCommand(() -> m_Turret.TurnTurret(-10), m_Turret));

  new JoystickButton(m_mechController.getHID(), 3) // X button
  .onTrue(new InstantCommand(() -> m_Intake.extendIntake(), m_Intake));

  new JoystickButton(m_mechController.getHID(), 2) // B button
  .onTrue(new InstantCommand(() -> m_Intake.retractIntake(), m_Intake));

  // new Trigger(m_mechController.getLeftTriggerAxis(), 4)
  // .onTrue(new InstantCommand(() -> m_Arm.setWristSpeed(10, true)));

  // new JoystickButton(m_mechController.getHID(), 5)
  // .whileTrue(new RunCommand(() -> m_Wrist.setWristSpeed(.2, false), m_Wrist));

  // new JoystickButton(m_mechController.getHID(), 6)
  // .whileTrue(new RunCommand(() -> m_Wrist.setWristSpeed(.2, true), m_Wrist));
  }

  public Command getAutoCommand() {
    return m_SimpleAuto;
  }

//public void SimpleAuto () {
  
  // public Command getAutoCommand() {
  //   return m_SimpleAutoCommand.getSimpleAuto();
  // }


 
  
}
