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
    public static Encoder m_turretEncoder = new Encoder(TurretConstants.turretEncoder.getFirst(),TurretConstants.turretEncoder.getSecond());

    public void TurnTurret(double speed) {
       if((speed < 0 && m_turretEncoder.getDistance() <= 6400) || (speed > 0 && m_turretEncoder.getDistance() >= -6400)) {
            m_turner.set(speed);
       }
       else {
        m_turner.set(0);
       }
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Turret Encoder", m_turretEncoder.getDistance());
        
    }
}