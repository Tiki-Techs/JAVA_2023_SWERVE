
package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.chassisConstants;
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
  private CommandXboxController driver;
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
  
  public SimpleAuto(SwerveSubsystem swerve, boolean fieldRelative) {

    this.swerve = swerve;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);

    this.driver = driver;
    this.fieldRelative = fieldRelative;
  }

  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yAxis = 0.75;
    double xAxis = 0.0;
    double rotAxis = 0.0;
    double driveTime = 2.0; // drive time is duration of auto time

    
    translation = new Translation2d(yAxis, xAxis).times(10);
    rotation =  driver.getRightX()* 0.33;
    // SmartDashboard.putNumber("ROTATIONNNNN", rotation);
    //
    if (m_timer.get() < driveTime) {
        swerve.drive(translation, rotation, fieldRelative);
    }
    else {
        Translation2d stop = new Translation2d(0,0);
        swerve.drive(stop, rotation, fieldRelative);
    }
    swerve.drive(translation, rotation, fieldRelative);
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
