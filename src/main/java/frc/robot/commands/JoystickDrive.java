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

public class JoystickDrive extends CommandBase {

  private final Drive drive;
  private final Joystick joystick;

  public JoystickDrive(Drive drive, Joystick joystick) {
    this.drive = drive;
    addRequirements(this.drive);
    this.joystick = joystick;
  }

  @Override
  public void execute() {
    drive.driveCartesian(joystick.getX(), -joystick.getY(), joystick.getZ());
  }

  @Override
  public void end(boolean interrupted) {
    drive.driveCartesian(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
