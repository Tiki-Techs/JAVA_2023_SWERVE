// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.Pair;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int DriverController = 0;
  public static final int MechController = 1;
  public static class DrivebaseConstants {
    public static final int FL_DRIVE = 1;
    public static final int FL_STEER = 2;

    public static final int FR_DRIVE = 3;
    public static final int FR_STEER = 4;

    public static final int BL_DRIVE = 5;
    public static final int BL_STEER = 6;

    public static final int BR_DRIVE = 7;
    public static final int BR_STEER = 8;
  }

  public static final class TurretConstants {
    //public static final int EncoderPort = 0;
    public static final int turnerMotor = 9;
    public static final Pair<Integer, Integer> turretEncoder = new Pair<>(9, 8);
  }

  public static final class armConstants {
    public static final int shoulderMotor1 = 10;
    public static final int shoulderMotor2 = 11;
  }
}
