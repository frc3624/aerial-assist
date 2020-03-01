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
   * The name doesn't help at all, but this is for using the NavX
   * https://www.andymark.com/products/navx-mxp-robotics-navigation-sensor
   * It's the purple circuit board that gets put directly on the RoboRIO
   * It's a gyroscope and accelerometer in one.
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

  /*
   * The AHRS is an accelerometer and gyroscope, so it can only tell you what angle it
   * is oriented and how fast it is accelerating, so how can it tell you its displacement?
   * 
   * TLDR or you did not take physics:
   * You can find displacement from acceleration, but the calculation has a very large margin of error
   * 
   * Calc/Physics C children:
   * You integrate acceleration with respect to time twice, but this is a Riemann sum so it is bad.
   * 
   * Physics H, R, or 1&2 children:
   * Δv = at
   * Δx = vi*t + 1/2*at^2
   * Note that these are delta, or changes in the values, not the actual values.
   * These formulas only works if acceleration is constant, which it is not.
   * This NavX updates at 200Hz, or 200 times a second, or every .005 seconds. This is what will be used as
   * t in the upcoming calculations.
   * x = 0 and v = 0 when it is turned on, which represent the total displacement and velocity.
   * Every time it updates, it gets the current acceleration and we'll say it is a.
   * it finds a * t and adds that to v.
   * It then uses the current value of v in vt + 1/2at^2 and adds this value to x. 
   * While .005s may seem small, a lot can happen in .005s. These calculations treat acceleration
   * like it only changes every .005s, even though it would be changing between one value and the next.
   * This small change adds up quickly since you're adding it 200 times a second.
   */
  public float getX() {
    return ahrs.getDisplacementX();
  }
  public float getY() {
    return ahrs.getDisplacementY();
  }
  public float getXVelocity() {
    return ahrs.getVelocityX();
  }
  public float getYVelocity() {
    return ahrs.getVelocityY();
  }

}
