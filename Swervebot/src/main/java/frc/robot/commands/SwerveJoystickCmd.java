package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.chassisConstants;
import frc.robot.subsystems.SwerveModule;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.Constants;
/** An example command that uses an example subsystem. */
public class SwerveJoystickCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private double rotation;
  private Translation2d translation;
  private boolean fieldRelative;
  private boolean overrideJS;
  private double m_setSpeed;
  private double yAxis;
  private double xAxis;
  private SwerveSubsystem swerve;
  private CommandXboxController driver;
  private SlewRateLimiter yLim = new SlewRateLimiter(1);
  private SlewRateLimiter xLim = new SlewRateLimiter(1);
  private SlewRateLimiter rotLim = new SlewRateLimiter(3);

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SwerveJoystickCmd(SwerveSubsystem swerve, CommandXboxController driver, boolean fieldRelative) {
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
    if (driver.getLeftY() < Constants.deadBand && driver.getLeftY() > Constants.deadBand*(-1.0)) {
      yAxis = 0;
    }
    else {
      yAxis = 0.4*driver.getLeftY() + 0.6* Math.pow(driver.getLeftY(), 3);
    }

    if (driver.getLeftX() < Constants.deadBand && driver.getLeftX() > Constants.deadBand*(-1.0)) {
      xAxis = 0;
    }
    else {
      xAxis = 0.4*driver.getLeftX() + 0.6* Math.pow(driver.getLeftX(), 3);
    }

    //double yAxis = driver.getLeftY();
    //double xAxis = driver.getLeftX();
    //double rotAxis = -driver.getRightX();
    
    translation = new Translation2d(yAxis, xAxis).times(100);
    rotation =  driver.getRightX()* 0.33;
    SmartDashboard.putNumber("ROTATIONNNNN", rotation);
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