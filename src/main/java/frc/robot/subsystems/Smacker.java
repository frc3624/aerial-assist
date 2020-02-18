/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*
 * Name curtesey of Haadi Khan
 */
public class Smacker extends SubsystemBase {

  private final WPI_TalonSRX masterMotor = new WPI_TalonSRX(SMACK_MASTER_MOTOR_ID);
  private final WPI_TalonSRX slaveMotor = new WPI_TalonSRX(SMACK_SLAVE_MOTOR_ID);
  
  public Smacker() {
    slaveMotor.set(ControlMode.Follower, SMACK_MASTER_MOTOR_ID);
  }

  public void set(double speed) {
    masterMotor.set(ControlMode.PercentOutput, speed);
  }

}
