/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

/*
 * Notice how I didn't make the MotionSensor a Subsystem.
 * It doesn't need to be "reserved" by commands like subsystems do because
 * this class simply gives readings from the sensor. Any number of commands
 * can use this simultaneously.
 */
/**
 * Interface to get the linear and rotational movement of chassis of the robot.
 */
public class MotionSensor {

  /*
   * AHRS stands for "Attitude and heading reference system"
   * 
   * You need to import the NavX library to use this
   * How to install:
   * Run the Command "WPILib: Manage Vendor Libraries" and choose "Install New Libraries (Online)"
   * Input this URL: https://www.kauailabs.com/dist/frc/2020/navx_frc.json 
   */
  private final AHRS ahrs = new AHRS();

  /*
   * These methods literally just return the exact same thing the AHRS class
   * gives, so why not just make ahrs public and let other classes use it?
   * What if we change the gyroscope to something else? Then we will have to change
   * code all over the place instead of just here.
   */
  public boolean isConnected() {
    return ahrs.isConnected();
  }

  public double getAngle() {
    return ahrs.getAngle();
  }
  public double getRotationalVelocity() {
    return ahrs.getRate();
  }
  public boolean isRotating() {
    return ahrs.isRotating();
  }

}
