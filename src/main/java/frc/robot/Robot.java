// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  private Spark leftMotorFront = new Spark(3);
  private Spark leftMotorBack = new Spark(4);
  private Spark rightMotorFront = new Spark(1);
  private Spark rightMotorBack = new Spark(2);

  private PneumaticsControlModule pcm = new PneumaticsControlModule(1);

  // 0 is forward and 7 is reverse
  private DoubleSolenoid gearbox = pcm.makeDoubleSolenoid(0, 7);

  private Joystick joy1 = new Joystick(0);

  @Override
  public void robotInit() {
    pcm.enableCompressorDigital();
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.9;
    double turn = joy1.getRawAxis(2) * 0.9;

    double left = speed + turn;
    double right = speed - turn;

    leftMotorFront.set(left);
    leftMotorBack.set(left);
    rightMotorFront.set(-right);
    rightMotorBack.set(-right);

    if(joy1.getRawButton(2))
      gearbox.set(Value.kForward);
    else if(joy1.getRawButton(3))
      gearbox.set(Value.kReverse);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
