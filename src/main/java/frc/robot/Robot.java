// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
  double Percentage = 0.0; 
  private Spark leftMotorFront = new Spark(3);
  private Spark leftMotorBack = new Spark(4);
  private Spark rightMotorFront = new Spark(1);
  private Spark rightMotorBack = new Spark(2);

  private Joystick joy1 = new Joystick(0);

  private PneumaticsControlModule pcm = new PneumaticsControlModule(1);
  private DoubleSolenoid ds = pcm.makeDoubleSolenoid(0, 7);

  private double startTime;

  @Override
  public void robotInit() {
    pcm.enableCompressorDigital();
    Percentage = 0.91;
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 3);
      leftMotorFront.set(-0.6);
      leftMotorBack.set(-0.6);
      rightMotorFront.set(0.6);
      rightMotorBack.set(0.6);
    } {
      leftMotorFront.set(0);
      leftMotorBack.set(0);
      rightMotorFront.set(0);
      rightMotorBack.set(0);
    }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.9;
    double turn = joy1.getRawAxis(2) * 0.9;

    if(joy1.getRawButton(2)){
      ds.set(Value.kForward);
      Percentage = 0.91;
    }else if(joy1.getRawButton(3)){
      ds.set(Value.kReverse);
      Percentage = 1;
    }

    double left = speed + turn;
    double right = speed - turn;

    leftMotorFront.set(-left * Percentage);
    leftMotorBack.set(-left * Percentage);
    rightMotorFront.set(right);
    rightMotorBack.set(right);
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
