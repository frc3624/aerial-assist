/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Smacker;

public class Smack extends CommandBase {
  
  private final Smacker smacker;
  private double speed;

  public Smack(Smacker smacker, double speed) {
    this.smacker = smacker;
    addRequirements(this.smacker);
    this.speed = speed;
  }

  @Override
  public void initialize() {
    smacker.set(speed);
  }

  @Override
  public void end(boolean interrupted) {
    smacker.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
}
