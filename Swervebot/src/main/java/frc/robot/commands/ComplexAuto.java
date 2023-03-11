package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.SwerveSubsystem;

public class ComplexAuto extends SequentialCommandGroup {
    public ComplexAuto(SwerveSubsystem swerve){
        addCommands(
            new SwerveDriveAuto(swerve, 10.0,  0.0, 0.5),
            new SwerveDriveAuto(swerve, -7.0, 0.0, 2)


        );
    }
}
