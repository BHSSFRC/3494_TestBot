/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotMap {
    public static final int drive_left_motor1 = 0;
    public static final int drive_left_motor2 = 1;
    public static final int drive_left_motor3 = 2;

    public static final int drive_right_motor1 = 13;
    public static final int drive_right_motor2 = 14;
    public static final int drive_right_motor3 = 15;

    public static final double DRIVE_TOLERANCE = 0.1;

    public static final int solenoid_channel = 0;
    public static final double solenoid_firetime = 0.1; //1.0 = 1 second

    public static final class TESTING {
        public static final int intake_channel = 11;
    }
}
