
package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.chassisConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.SwerveModule;
import frc.robot.subsystems.SwerveSubsystem;

/** An example command that uses an example subsystem. */
public class SimpleAuto extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private double rotation;
  private Translation2d translation;
  private boolean fieldRelative;
  private boolean overrideJS;
  
  private SwerveSubsystem swerve;
  private Arm arm;
  private Intake intake;
  private SlewRateLimiter yLim = new SlewRateLimiter(1);
  private SlewRateLimiter xLim = new SlewRateLimiter(1);
  private SlewRateLimiter rotLim = new SlewRateLimiter(3);
  private final Timer m_timer = new Timer();

  /**
   * Creates a new ExampleCommand.
   *
   * @param swerve The subsystem used by this command.
   * @param fieldRelative Field Relative Boolean
   */
  
  public SimpleAuto(SwerveSubsystem swerve, Arm arm, Intake intake, boolean fieldRelative) {
    this.arm = arm;
    this.intake = intake;
    this.swerve = swerve;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
    addRequirements(arm);
    addRequirements(intake);
    //this.driver = driver;
    this.fieldRelative = fieldRelative;
    
  }

  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("timer", m_timer.get());
    double autoyAxis = -7.0;
    double autoxAxis = 0.0;
    double rotAxis = 0.0;
    double driveTime = 2.5; // drive time is duration of auto time

    
    Translation2d translation = new Translation2d(autoyAxis, autoxAxis);
    Translation2d stop = new Translation2d(0.0,0.0);
    while (m_timer.get() < 0.3) {
      arm.setShoulderSpeed(-0.4);
      arm.setElbowSpeed(-0.4);
    }
    intake.extendIntake();
    arm.setShoulderSpeed(0.0);
    arm.setElbowSpeed(0.0);
    while (m_timer.get() < 1.0) {

    }
    if (m_timer.get() < driveTime) {
        swerve.drive(translation, rotAxis, fieldRelative);
    }
    else {
        swerve.drive(stop, rotAxis, fieldRelative);
        m_timer.stop();
    }

    // if(xAxis == 0 && yAxis == 0 && rotAxis == 0){
    //   swerve.zeroModules();
    //   swerve.stopModules();
    // }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
