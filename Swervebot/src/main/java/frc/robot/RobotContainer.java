// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Balance;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.ConeAuto;
import frc.robot.commands.DefaultArm;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.commands.TurretTurner;
import frc.robot.commands.WristTurner;
import frc.robot.commands.shimSham;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final SwerveSubsystem m_Drivebase = new SwerveSubsystem();
  private final Turret m_Turret = new Turret();
  public static final Arm m_Arm = new Arm();
  public final Intake m_Intake = new Intake();
  public final Wrist m_Wrist = new Wrist();
  private final Command m_SimpleAuto = new SimpleAuto(m_Drivebase, m_Arm, m_Intake, true);
  private final Command m_ComplexAuto = new ComplexAuto(m_Drivebase);
  private final Command m_shimSham = new shimSham(m_Drivebase, true);
  private final Command m_ConeAuto = new ConeAuto(m_Drivebase, m_Arm, m_Intake, false);
  private final Command m_Balance = new Balance(m_Drivebase);  

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

  //new JoystickButton(m_mechController.getHID(), 5)
  //   .whileTrue(new RunCommand(() -> m_Turret.TurnTurret(10), m_Turret));

  //   new JoystickButton(m_mechController.getHID(),6)
  //   .whileTrue(new RunCommand(() -> m_Turret.TurnTurret(-10), m_Turret));
  
  new JoystickButton(m_mechController.getHID(), 1) // A button
  .onTrue(new InstantCommand(() -> m_Intake.extendIntake(), m_Intake));

  new JoystickButton(m_mechController.getHID(), 2) // B button
  .onTrue(new InstantCommand(() -> m_Intake.retractIntake(), m_Intake));

  m_mechController.povUp().onTrue(new InstantCommand(() -> m_Arm.moveToPosition(-1139, 709.25)));
  m_mechController.povDown().onTrue(new InstantCommand(() -> m_Arm.moveToPosition(-1702, 1327.75)));
  m_mechController.povLeft().onTrue(new InstantCommand(() -> m_Arm.moveToPosition(0, 0)));
    
  new JoystickButton(m_mechController.getHID(), 8) // start button
  .onTrue(new InstantCommand(() -> CommandScheduler.getInstance().cancelAll()));

  // new JoystickButton(m_mechController.getHID(), 5)
  // .whileTrue(new RunCommand(() -> m_Wrist.setWristSpeed(.2, false), m_Wrist));

  // new JoystickButton(m_mechController.getHID(), 6)
  // .whileTrue(new RunCommand(() -> m_Wrist.setWristSpeed(.2, true), m_Wrist));
  }

  public Command getAutoCommand() {
    SmartDashboard.putBoolean("Hello", true);
    return m_SimpleAuto;
  }
  
}
