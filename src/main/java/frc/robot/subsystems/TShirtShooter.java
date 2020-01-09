/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Timer;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TShirt;

/**
 * Add your docs here.
 */
public class TShirtShooter extends Subsystem {
  private Solenoid canonTrigger;
  private Timer solenoidTimer;
  private boolean currentFireState;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static TShirtShooter INSTANCE = new TShirtShooter();

  public TShirtShooter() {
    canonTrigger = new Solenoid(0, RobotMap.solenoid_channel);
    solenoidTimer = new Timer();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TShirt());
  }

  public void fire(double time) {
    canonTrigger.set(true);
    currentFireState = true;
    solenoidTimer.schedule( 
      new java.util.TimerTask() {
        @Override
        public void run() {
          canonTrigger.set(false);
          currentFireState = false;
        }
      }, 
      Math.round(time * 1000)
    );
  }

  public void set(boolean set) {
    canonTrigger.set(set);
    currentFireState = set;
  }

  public boolean get() {
    return currentFireState;
  }

  public static TShirtShooter getInstance() {
      return INSTANCE;
  }
}
