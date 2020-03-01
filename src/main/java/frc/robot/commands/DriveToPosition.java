/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.MotionSensor;

public class DriveToPosition extends CommandBase {

  private final Drive drive;
  private final MotionSensor motionSensor;
  private final float xTarget, yTarget;
  private final double angleTarget;

  public DriveToPosition(Drive drive, MotionSensor motionSensor, float xTarget, float yTarget) {
    this(drive, motionSensor, xTarget, yTarget, motionSensor.getAngle());
  }
  public DriveToPosition(Drive drive, MotionSensor motionSensor, double angleTarget) {
    this(drive, motionSensor, motionSensor.getX(), motionSensor.getY(), angleTarget);
  }
  public DriveToPosition(Drive drive, MotionSensor motionSensor, float xTarget, float yTarget, double angleTarget) {
    this.drive = drive;
    addRequirements(drive);
    this.motionSensor = motionSensor;
    this.xTarget = xTarget;
    this.yTarget = yTarget;
    this.angleTarget = angleTarget;
  }

  /*
   * To find out the velocity I need to bring the robot to the correct position, I used the algorithm PID controllers use
   * This code is supposed to be beginner friendly, so I'm excluding the ID part (Integral Derivative), and only using the
   * P part (Proportional).
   * 
   * So basically, if the robot is travelling along a line, and it is at position P1 and you want to get to P2,
   * you would find the difference between these two points and set the velocity to that.
   * 
   * Difference = target position - current position
   * If the target position is greater than the current position, then the velocity will be positive and the
   * robot will move in the positive direction - towards the target. if the target position is less than the 
   * current position, the velocity is negative and the robot will move in the negative direction - again, towards
   * the target.
   * As the current position gets closer to the target position, the difference will approach 0 and the robot
   * will slow down.
   * We may want it to move towards the target faster or slower, so we multiply the difference by kP. If 0<kP<1, then the
   * robot will move towards the target slower. If kP>1, then the robot will move towards the target faster.
   * 
   * I applied this to each of the axes of the robot.
   */
  private final static float CARTESIAN_MARGIN_OF_ERROR = 10;
  private final static double ANGLE_MARGIN_OF_ERROR = .5;
  private final double kP = 1; // I have not tested this value
  @Override
  public void execute() {
    float xError = xTarget - motionSensor.getX();
    if (Math.abs(xError) < CARTESIAN_MARGIN_OF_ERROR)
      xError = 0;
    float yError = yTarget - motionSensor.getY();
    if (Math.abs(yError) < CARTESIAN_MARGIN_OF_ERROR)
      yError = 0;
    double angleError = angleTarget - motionSensor.getAngle();
    if (Math.abs(angleError) < ANGLE_MARGIN_OF_ERROR)
      angleError = 0;

    double xSpeed = kP * xError;
    double ySpeed = kP * yError;
    double angleSpeed = kP * angleError;

    drive.driveCartesian(ySpeed, xSpeed, angleSpeed, motionSensor.getAngle());
  }

  @Override
  public void end(boolean interrupted) {
    drive.driveCartesian(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    float xError = xTarget - motionSensor.getX();
    if (Math.abs(xError) < CARTESIAN_MARGIN_OF_ERROR)
      xError = 0;
    float yError = yTarget - motionSensor.getY();
    if (Math.abs(yError) < CARTESIAN_MARGIN_OF_ERROR)
      yError = 0;
    double angleError = angleTarget - motionSensor.getAngle();
    if (Math.abs(angleError) < ANGLE_MARGIN_OF_ERROR)
      angleError = 0;
    
    return xError == 0 && motionSensor.getXVelocity() == 0 
        && yError == 0 && motionSensor.getYVelocity() == 0
        && angleError == 0 && motionSensor.getRotationalVelocity() == 0;
  }
}
