package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Testing;

public class TestingCode extends Command {
  public TestingCode() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Testing.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (OI.getInstance().getXboxLeftTrigger() == 0) {
        Testing.getInstance().runIntake(-OI.getInstance().getXboxRightTrigger());
    } else {
        Testing.getInstance().runIntake(OI.getInstance().getXboxLeftTrigger());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
