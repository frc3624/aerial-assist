/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arms;

public class RotateArms extends CommandBase {

  private final Arms arms;
  private double speed;

  public RotateArms(Arms arms, double speed) {
    this.arms = arms;
    addRequirements(arms);
    this.speed = speed;
  }

  @Override
  public void initialize() {
    arms.setArmRotator(speed);
  }

  @Override
  public void end(boolean interrupted) {
    arms.setArmRotator(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
