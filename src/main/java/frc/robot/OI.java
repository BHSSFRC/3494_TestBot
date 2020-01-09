package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

public class OI {
    private static OI INSTANCE = new OI();

    private XboxController xbox;

    private OI() {
        xbox = new XboxController(0);
    }

    public static double removeDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    public double getXboxLeftX() {
        return removeDeadband(xbox.getX(GenericHID.Hand.kLeft), 0.3);
    }

    public double getXboxLeftY() {
        return removeDeadband(xbox.getY(GenericHID.Hand.kLeft), 0.3);
    }

    public double getXboxRightX() {
        return removeDeadband(xbox.getX(GenericHID.Hand.kRight), 0.3);
    }

    public double getXboxRightY() {
        return removeDeadband(xbox.getY(GenericHID.Hand.kRight), 0.3);
    }

    public double getXboxLeftTrigger() {
        return xbox.getTriggerAxis(GenericHID.Hand.kLeft);
    }

    public double getXboxRightTrigger() {
        return xbox.getTriggerAxis(GenericHID.Hand.kRight);
    }

    public boolean getXboxRightBumper() {
        return xbox.getBumper(GenericHID.Hand.kRight);
    }

    public boolean getXboxLeftBumper() {
        return xbox.getBumper(GenericHID.Hand.kLeft);
    }

    public static OI getInstance() {
        return INSTANCE;
    }
}
