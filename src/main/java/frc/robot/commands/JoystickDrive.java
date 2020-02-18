/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.MotionSensor;

public class JoystickDrive extends CommandBase {

  private final Drive drive;
  private final MotionSensor motionSensor;
  private final Joystick joystick;

  public JoystickDrive(Drive drive, MotionSensor motionSensor, Joystick joystick) {
    this.drive = drive;
    addRequirements(this.drive);
    this.motionSensor = motionSensor;
    this.joystick = joystick;
  }

  @Override
  public void execute() {
    drive.driveCartesian(joystick.getX(), -joystick.getY(), joystick.getZ(), -motionSensor.getAngle());
  }

  @Override
  public void end(boolean interrupted) {
    drive.driveCartesian(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
