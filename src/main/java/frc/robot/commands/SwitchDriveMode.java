/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drive;

public class SwitchDriveMode extends InstantCommand {
  
  private final Drive drive;
  private final JoystickDrive joystickDrive;
  private final FieldOrientedJoystickDrive fieldOrientedJoystickDrive;

  public SwitchDriveMode(Drive drive, JoystickDrive joystickDrive, FieldOrientedJoystickDrive fieldOrientedJoystickDrive) {
    this.drive = drive;
    this.joystickDrive = joystickDrive;
    this.fieldOrientedJoystickDrive = fieldOrientedJoystickDrive;
  }

  @Override
  public void initialize() {
    if (drive.getCurrentCommand() == joystickDrive) {
      fieldOrientedJoystickDrive.schedule();
      drive.setDefaultCommand(fieldOrientedJoystickDrive);
    } else {
      joystickDrive.schedule();
      drive.setDefaultCommand(joystickDrive);
    }
  }

}
