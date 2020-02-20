/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

  private final WPI_TalonSRX frontLeftMotor = new WPI_TalonSRX(FRONT_LEFT_ID);
  private final WPI_TalonSRX rearLeftMotor = new WPI_TalonSRX(REAR_LEFT_ID);
  private final WPI_TalonSRX frontRightMotor = new WPI_TalonSRX(FRONT_RIGHT_ID);
  private final WPI_TalonSRX rearRightMotor = new WPI_TalonSRX(REAR_RIGHT_ID);
  // https://www.youtube.com/watch?v=O7FbDy-gE70
  private final MecanumDrive mechaDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  public Drive() {

  }

  // Read the documentation carefully - while there is an x and y axis, it's not the axes you would associate with on the joystick
  /**
   * @param ySpeed The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
   */
  public void driveCartesian(double ySpeed, double xSpeed, double zRotation) {
    mechaDrive.driveCartesian(ySpeed, xSpeed, zRotation);
  }

  /**
   * Giving the gyro angle makes driving the robot field oriented.
   * https://www.youtube.com/watch?v=7Cxj1JpKDhc
   * 
   * @param ySpeed The robot's speed along the Y axis [-1.0..1.0]. Right is positive.
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Clockwise is positive.
   * @param gyroAngle The current angle reading from the gyro in degrees around the Z axis. Use this to implement field-oriented controls
   */
  public void driveCartesian(double ySpeed, double xSpeed, double zRotation, double gyroAngle) {
    mechaDrive.driveCartesian(ySpeed, xSpeed, zRotation, gyroAngle);
  }

}
