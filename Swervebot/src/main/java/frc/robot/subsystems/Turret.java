package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {

    public CANSparkMax m_turner = new CANSparkMax(TurretConstants.turnerMotor, MotorType.kBrushless);
    public Encoder enc = new Encoder(0,1);

    public void TurnTurret(double speed) {
        m_turner.set(speed);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Turret Encoder", enc.getDistance());
    }
}
