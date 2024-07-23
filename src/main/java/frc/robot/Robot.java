// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX m_frontLeftMotor = new WPI_TalonSRX(3);
  private final WPI_VictorSPX m_frontRightMotor = new WPI_VictorSPX(5);
  private final WPI_TalonSRX m_backLeftMotor = new WPI_TalonSRX(2);
  private final WPI_VictorSPX m_backRightMotor = new WPI_VictorSPX(4);
  
  private final DifferentialDrive m_robotFrontDrive =
      new DifferentialDrive(m_frontLeftMotor::set, m_frontRightMotor::set);
  
  private final DifferentialDrive m_robotBackDrive =
      new DifferentialDrive(m_backLeftMotor::set, m_backRightMotor::set);

  private final Joystick m_stick = new Joystick(0);

  public Robot() {
    SendableRegistry.addChild(m_robotFrontDrive, m_frontLeftMotor);
    SendableRegistry.addChild(m_robotFrontDrive, m_frontRightMotor);

    SendableRegistry.addChild(m_robotBackDrive, m_backLeftMotor);
    SendableRegistry.addChild(m_robotBackDrive, m_backRightMotor);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_frontRightMotor.setInverted(true);
    m_backRightMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotFrontDrive.arcadeDrive(-m_stick.getY() / 2, -m_stick.getZ() / 2);
    m_robotBackDrive.arcadeDrive(-m_stick.getY() / 2, -m_stick.getZ() / 2);
  }
}
