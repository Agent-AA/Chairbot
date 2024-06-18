// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_frontRobotDrive;
  private DifferentialDrive m_backRobotDrive;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  // TODO Update device IDs either on the robot or here
  private final CANSparkMax m_frontLeftMotor = new CANSparkMax(0, MotorType.kBrushless);
  private final CANSparkMax m_frontRightMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax m_backRightMotor = new CANSparkMax(3, MotorType.kBrushless);

  @Override
  public void robotInit() {
    SendableRegistry.addChild(m_frontRobotDrive, m_frontLeftMotor);
    SendableRegistry.addChild(m_frontRobotDrive, m_frontRightMotor);

    SendableRegistry.addChild(m_backRobotDrive, m_backLeftMotor);
    SendableRegistry.addChild(m_backRobotDrive, m_backRightMotor);

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_frontRightMotor.setInverted(true);
    m_backRightMotor.setInverted(true);

    m_frontRobotDrive = new DifferentialDrive(m_frontLeftMotor::set, m_frontRightMotor::set);
    m_backRobotDrive = new DifferentialDrive(m_backLeftMotor::set, m_backRightMotor::set);

    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_frontRobotDrive.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
    m_backRobotDrive.tankDrive(-m_leftStick.getY(), -m_rightStick.getY());
  }
}
