package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Drivetrain subsytem. Contains all methods for controlling the robot's
 * drivetrain. Also has in instance of RobotDrive (wpiDrive) if you want to use
 * that.
 *
 * @since 0.0.0
 */
public class Drivetrain extends Subsystem {
    private TalonSRX drive_left_motor1;
    private TalonSRX drive_left_motor2;
    private TalonSRX drive_left_motor3;
    
    private TalonSRX drive_right_motor1;
    private TalonSRX drive_right_motor2;
    private TalonSRX drive_right_motor3;

    private static Drivetrain INSTANCE = new Drivetrain();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Drivetrain() {
        super("Drivetrain");

        drive_left_motor1 = new TalonSRX(RobotMap.drive_left_motor1);
        drive_left_motor2 = new TalonSRX(RobotMap.drive_left_motor2);
        drive_left_motor3 = new TalonSRX(RobotMap.drive_left_motor3);
    
        drive_right_motor1 = new TalonSRX(RobotMap.drive_right_motor1);
        drive_right_motor2 = new TalonSRX(RobotMap.drive_right_motor2);
        drive_right_motor3 = new TalonSRX(RobotMap.drive_right_motor3);

        drive_right_motor1.setInverted(true);
        drive_right_motor2.setInverted(true);
        drive_right_motor3.setInverted(true);
    }

    public static Drivetrain getInstance() {
        return INSTANCE;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }

    /**
     * Drives the drivetrain tank drive style. The drivetrain will continue to
     * run until stopped with a method like {@link Drivetrain#StopDrive()}.
     *
     * @param left  The power to drive the left side. Should be a {@code double}
     *              between 0 and 1.
     * @param right The power to drive the right side. Should be a {@code double}
     *              between 0 and 1.
     */
    public void TankDrive(double left, double right) {
        drive_left_motor1.set(ControlMode.PercentOutput, left);
        drive_left_motor2.set(ControlMode.PercentOutput, left);
        drive_left_motor3.set(ControlMode.PercentOutput, left);

        drive_right_motor1.set(ControlMode.PercentOutput, right);
        drive_right_motor2.set(ControlMode.PercentOutput, right);
        drive_right_motor3.set(ControlMode.PercentOutput, right);
    }

    /**
     * Arcade drive method for differential drive platform.
     *
     * @param xSpeed        The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
     * @param zRotation     The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is
     *                      positive.
     * @param squaredInputs If set, decreases the input sensitivity at low speeds.
     */
    @SuppressWarnings("ParameterName")
    public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {
        xSpeed = limit(xSpeed);
        xSpeed = applyDeadband(xSpeed, 0.02);

        zRotation = limit(zRotation);
        zRotation = applyDeadband(zRotation, 0.02);

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squaredInputs) {
            xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
            zRotation = Math.copySign(zRotation * zRotation, zRotation);
        }

        double leftMotorOutput;
        double rightMotorOutput;

        double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

        if (xSpeed >= 0.0) {
            // First quadrant, else second quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            } else {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            }
        } else {
            // Third quadrant, else fourth quadrant
            if (zRotation >= 0.0) {
                leftMotorOutput = xSpeed + zRotation;
                rightMotorOutput = maxInput;
            } else {
                leftMotorOutput = maxInput;
                rightMotorOutput = xSpeed - zRotation;
            }
        }
        this.TankDrive(limit(leftMotorOutput), limit(rightMotorOutput));
    }

    /**
     * Stops all drive motors. Does not require re-enabling motors after use.
     *
     * @since 0.0.0
     */
    public void StopDrive() {
        this.TankDrive(0, 0);
    }

    private static double limit(double value) {
        if (value > 1.0) {
            return 1.0;
        }
        if (value < -1.0) {
            return -1.0;
        }
        return value;
    }

    private static double applyDeadband(double value, double deadband) {
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
}
