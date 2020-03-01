/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.JOYSTICK_ID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveToPosition;
import frc.robot.commands.FieldOrientedJoystickDrive;
import frc.robot.commands.JoystickDrive;
import frc.robot.commands.RotateArms;
import frc.robot.commands.Smack;
import frc.robot.commands.SwitchArmState;
import frc.robot.commands.SwitchDriveMode;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.MotionSensor;
import frc.robot.subsystems.Smacker;

public class RobotContainer {
  
  private final Joystick joystick = new Joystick(JOYSTICK_ID);
  private final JoystickButton switchArmStateButton = new JoystickButton(joystick, 1);
  private final JoystickButton switchDriveModeButton = new JoystickButton(joystick, 2);
  private final JoystickButton smackForwardButton = new JoystickButton(joystick, 5);
  private final JoystickButton smackBackwardButton = new JoystickButton(joystick, 3);
  private final JoystickButton rotateArmsUpButton = new JoystickButton(joystick, 6);
  private final JoystickButton rotateArmsDownButton = new JoystickButton(joystick, 4);

  private final Drive drive = new Drive();
  private final Arms arms = new Arms();
  private final Smacker smacker = new Smacker();
  private final MotionSensor motionSensor = new MotionSensor();

  private final JoystickDrive joystickDrive = new JoystickDrive(drive, joystick);
  private final FieldOrientedJoystickDrive fieldOrientedJoystickDrive = new FieldOrientedJoystickDrive(drive, motionSensor, joystick);
  private final SwitchDriveMode switchDriveMode = new SwitchDriveMode(drive, joystickDrive, fieldOrientedJoystickDrive);

  private final SwitchArmState switchArmState = new SwitchArmState(arms);
  private final Smack smackBack = new Smack(smacker, -.5);
  private final Smack smackForward = new Smack(smacker, 1);
  private final RotateArms rotateArmsUp = new RotateArms(arms, -.5);
  private final RotateArms rotateArmsDown = new RotateArms(arms, .5);
  private final Command autoCommand = CommandGroupBase.sequence(new DriveToPosition(drive, motionSensor, 10, 10, 45), 
      new WaitCommand(1).deadlineWith(smackForward));

  public RobotContainer() {
    drive.setDefaultCommand(joystickDrive);
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    switchArmStateButton.whenPressed(switchArmState);
    switchDriveModeButton.whenPressed(switchDriveMode);
    smackForwardButton.whileHeld(smackForward);
    smackBackwardButton.whileHeld(smackBack);
    rotateArmsUpButton.whileHeld(rotateArmsUp);
    rotateArmsDownButton.whileHeld(rotateArmsDown);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoCommand;
  }
  
}
