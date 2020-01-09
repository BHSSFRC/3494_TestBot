package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TestingCode;

/**
 * Drivetrain subsytem. Contains all methods for controlling the robot's
 * drivetrain. Also has in instance of RobotDrive (wpiDrive) if you want to use
 * that.
 *
 * @since 0.0.0
 */
public class Testing extends Subsystem {
    private TalonSRX intake_motor;

    private static Testing INSTANCE = new Testing();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Testing() {
        super("Testing");

        intake_motor = new TalonSRX(RobotMap.TESTING.intake_channel);
    }

    public static Testing getInstance() {
        return INSTANCE;
    }

    public void runIntake(double speed) {
        intake_motor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TestingCode());
    }
}
