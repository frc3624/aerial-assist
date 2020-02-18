/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {

  private final DoubleSolenoid pistonDoubleSolenoid = new DoubleSolenoid(PCM_CAN_ID, PISTON_FORWARD_CHANNEL, PISTON_REVERSE_CHANNEL);

  // Positive voltage makes the arms rotate up; Negative voltage makes the arms rotate down
  private final WPI_TalonFX rotationMotor = new WPI_TalonFX(ARM_ROTATE_MOTOR_ID);

  public Arms() {
    closeArms();
  }

  /**
   * Sets the speed of the rotation of the arms
   * @param speed [-1, 1], negative is the down direction
   */
  public void setArmRotator(double speed) {
    rotationMotor.set(ControlMode.PercentOutput, speed);
  }
  @Override
  public void periodic() {
    if (rotationMotor.get() < 0 && atRotationalMaximum())
      rotationMotor.set(0);
    else if (rotationMotor.get() > 0 && atRotationalMinimum())
      rotationMotor.set(0);
  }

  public void switchArmState() {
    if (isOpen())
      closeArms();
    else
      openArms();
  }
  public void openArms() {
    pistonDoubleSolenoid.set(Value.kForward);
  }
  public void closeArms() {
    pistonDoubleSolenoid.set(Value.kReverse);
  }
  public boolean isOpen() {
    return pistonDoubleSolenoid.get() == Value.kForward;
  }

  /*
   * Limit switches are literally buttons that get pressed when something, in this case the arms, move
   * far enough in a certain direction for them to be presssed.
   * 
   * .get() returns false when the limit switch is activated, even though it would make a lot more sense 
   * for it to return true.
   */
  private final DigitalInput maximumLimitSwitch = new DigitalInput(MAXIMUM_LIMIT_SWITCH_DIO_ID);
  private final DigitalInput minimumLimitSwitch = new DigitalInput(MINIMUM_LIMIT_SWITCH_DIO_ID);

  public boolean atRotationalMinimum() {
    return !minimumLimitSwitch.get();
  }
  public boolean atRotationalMaximum() {
    return !maximumLimitSwitch.get();
  }

}
