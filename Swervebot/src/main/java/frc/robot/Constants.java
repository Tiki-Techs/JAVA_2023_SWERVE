package frc.robot;

import edu.wpi.first.math.Pair;

public class Constants {
    
    public static final class swerveConstants{
        public static final int frontLeftDriveMotor = 1;
        public static final int frontLeftTurningMotor = 2;
        public static final int frontRightDriveMotor = 3;
        public static final int frontRightTurningMotor = 4;
        public static final int backLeftDriveMotor = 5;
        public static final int backLeftTurningMotor = 6;
        public static final int backRightDriveMotor = 7;
        public static final int backRightTurningMotor = 8;

        public static final Pair<Integer, Integer> frontLeftTurnEncoder = new Pair<>(0,1);
        public static final Pair<Integer, Integer> frontRightTurnEncoder  = new Pair<>(2,3);
        public static final Pair<Integer, Integer> backLeftTurnEncoder = new Pair<>(4,5);
        public static final Pair<Integer, Integer> backRightTurnEncoder = new Pair<>(6,7);

        public static final double kWheelRadius = 0.0508;
        public static final double kDriveMotorGearRatio = 1 / 5.5;
        public static final double kTurningMotorGearRatio = 1/10.29;
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI *(kWheelRadius * 2);
        public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;

        public static final double MOTOR_DISTANCE_FROM_CENTER_METERS = 0.3683;

        public static final double kMaxSpeed = 3.0; // 3 meters per second
        public static final double kMaxAngularSpeed = Math.PI; // 1/2 rotation per second

     }


}
