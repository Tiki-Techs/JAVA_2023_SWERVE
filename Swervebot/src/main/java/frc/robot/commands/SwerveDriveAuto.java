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
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class SwerveDriveAuto extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private double rotation;
  private Translation2d translation;
  private boolean fieldRelative;
  private boolean overrideJS;
  private double m_setSpeed;
  private double yAxis;
  private double xAxis;
  private double driveTime;
  private SwerveSubsystem swerve;
  private CommandXboxController driver;
  private SlewRateLimiter yLim = new SlewRateLimiter(1);
  private SlewRateLimiter xLim = new SlewRateLimiter(1);
  private SlewRateLimiter rotLim = new SlewRateLimiter(3);
  private final Timer m_timer = new Timer();


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   * @param xAxis Xaxis velocity 
   * @param yAxis yAxis velocity, negative is forward
   */
  public SwerveDriveAuto(SwerveSubsystem swerve, double yAxis, double xAxis, double time) {
    this.swerve = swerve;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
    this.xAxis = xAxis; 
    this.yAxis = yAxis;
    this.driveTime = time;
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
    fieldRelative = true;
    //double yAxis = driver.getLeftY();
    //double xAxis = driver.getLeftX();
    double rotAxis = 0.0;
    
    translation = new Translation2d(yAxis, xAxis).times(100);
    Translation2d stop = new Translation2d(0.0,0.0);

    //swerve.drive(translation, rotation, true);
    // if(xAxis == 0 && yAxis == 0 && rotAxis == 0){
    //   swerve.zeroModules();
    //   swerve.stopModules();
    // }
    if (m_timer.get() < driveTime) {
      swerve.drive(translation, rotAxis, fieldRelative);
   }
    else {
      swerve.drive(stop, rotAxis, fieldRelative);
      //m_timer.stop();
      //m_timer.reset();
      isFinished();
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}