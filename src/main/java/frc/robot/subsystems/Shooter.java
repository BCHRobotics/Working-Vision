/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * 
 * 
 * @author Luc Suzuki, Kyla Rowan, Noah Tomkins
 */
public class Shooter extends SubsystemBase {

  private CANSparkMax SPARK_SHOOTERTURRET = new CANSparkMax(RobotMap.SPARK_SHOOTERTURRET, MotorType.kBrushless);
  private CANSparkMax SPARK_SHOOTERWHEEL = new CANSparkMax(RobotMap.SPARK_SHOOTERWHEEL, MotorType.kBrushless);
  private CANSparkMax SPARK_SHOOTERHOOD = new CANSparkMax(RobotMap.SPARK_SHOOTERHOOD, MotorType.kBrushless);

  private CANEncoder encoderTurret = new CANEncoder(SPARK_SHOOTERTURRET);
  private CANEncoder encoderWheel = new CANEncoder(SPARK_SHOOTERWHEEL);
  private CANEncoder encoderHood = new CANEncoder(SPARK_SHOOTERHOOD);

  private double rampRateTurret = 0;
  private double rampRateWheel = 0;
  private double rampRateHood = 0;
  
  private final double encoderCalTurret = 1;
  private final double encoderCalWheel = 1;
  private final double encoderCalHood = 1;

  private final int minTurret = 0;
  private final int maxTurret = 0;

  private final int minHood = 0;
  private final int maxHood = 0;

  private double kP = 0, kI = 0, kD = 0;
  private double error = 0, derivative = 0, integral = 0, rcw = 0;
  private double endTime = 0, inRange = 0, inRangeSet = 0;

  private double kP2 = 0, kI2 = 0, kD2 = 0;
  private double error2 = 0, derivative2 = 0, integral2 = 0, rcw2 = 0;

  private double kP3 = 0, kI3 = 0, kD3 = 0;
  private double error3 = 0, derivative3 = 0, integral3 = 0, rcw3 = 0;


  private double previous_error = 0;
  private double previous_error2 = 0;
  private double previous_error3 = 0;
  
  /**
   * Creates a new Shooter.
   */
  public Shooter() {

    SPARK_SHOOTERTURRET.setInverted(false);
    SPARK_SHOOTERWHEEL.setInverted(false);
    SPARK_SHOOTERHOOD.setInverted(false);

    SPARK_SHOOTERTURRET.setClosedLoopRampRate(rampRateTurret);
    SPARK_SHOOTERWHEEL.setClosedLoopRampRate(rampRateWheel);
    SPARK_SHOOTERHOOD.setClosedLoopRampRate(rampRateHood);

  }

  /**
   * Sets the speed of the turret
   * 
   * @param speed The speed at which the turret spins at
   */
  public void turretSpeed(double speed){

    if((getEncoderTurret() <= minTurret) && speed > 0){
      SPARK_SHOOTERTURRET.set(speed);
    } else if((getEncoderTurret() >= maxTurret) && speed < 0){
      SPARK_SHOOTERTURRET.set(speed);
    } else {
      SPARK_SHOOTERTURRET.set(0);
    }
  }

  /**
   * Sets the speed of the wheel
   * 
   * @param speed The speed of the shooter wheel 
   */
  public void wheelSpeed(double speed){
    SPARK_SHOOTERWHEEL.set(speed);
  }

  /**
   * Sets the speed of the hood
   * 
   * @param speed The speed at which the hood angles itself
   */
  public void hoodSpeed(double speed){

    if((getEncoderHood() <= minHood) && speed > 0){
      SPARK_SHOOTERHOOD.set(speed);
    } else if((getEncoderHood() >= maxHood) && speed < 0){
      SPARK_SHOOTERHOOD.set(speed); 
    } else {
      SPARK_SHOOTERHOOD.set(0);
    }

  }

  /**
   * Sets the angle of the turret WIP
   *  
   * @param degrees Angle of the turret
   * @param speed The speed of the turret
   */
  public void setTurret(double degrees, double speed){

    error = degrees - getEncoderTurret();
    integral = integral + (error * 0.02);
    derivative = (error - previous_error) / 0.02;
    rcw = ( kP * error) + ( kI * integral) + ( kD * derivative);
    previous_error = error;

    turretSpeed(rcw * speed);

  }

  /**
   * Sets the angle of the hood.
   * 
   * @param degrees Angle of the hood
   * @param speed Speed of the hood
   */
  public void setHood(double degrees, double speed){

    error2 = degrees - getEncoderHood();
    integral2 = integral2 + (error2 * 0.02);
    derivative2 = (error2 - previous_error2) / 0.02;
    rcw2 = ( kP2 * error2) + ( kI2 * integral2) + ( kD2 * derivative2);
    previous_error2 = error2;

    hoodSpeed(rcw2 * speed);

  }

 
  /**
   * Sets the speed of the wheel
   * 
   * @param rpm The speed of the wheel
   */
  public void setWheelSpeed(double rpm){

    /*
    error = setpoint - mDrivetrain.getEncoderBL();
    integral = integral + (error * 0.02);
    derivative = (error - previous_error) / 0.02;
    rcw = ( kP * error) + ( kI * integral) + ( kD * derivative);
    previous_error = error;
    */

  }

  double startMills = 0;
  double endMills = 0;
  double startTicks = 0;
  double endTicks = 0;
  double rpm = 0;

  private double getWheelRpm(){

    //Get current time and wheel ticks
    endMills = System.currentTimeMillis();
    endTicks = getEncoderWheel();

    rpm = ((endTicks - startTicks) / 42) / ((endMills - startMills) / 60000);

    //Save for next time this function runs
    endTime = System.currentTimeMillis();
    endTicks = getEncoderWheel();

    return rpm;

  }

  /**
   * gets the value of calabrate value of the turret encoder 
   * 
   * @return the angle of the turret 
   */
  public double getEncoderTurret(){
    return encoderTurret.getPosition() * encoderCalTurret;
  }

  /**
   * gets the value of calabrate value of the wheel encoder 
   * 
   * @return the encoder value of wheel 
   */
  public double getEncoderWheel(){
    return encoderWheel.getPosition() * encoderCalWheel;
  }

  /**
   * gets the value of calabrate value of the hood encoder 
   * 
   * @return the encoder value of the hood 
   */
  public double getEncoderHood(){
    return encoderHood.getPosition() * encoderCalHood;
  }

  /**
   * reset the encoder for the turret
   */
  public void resetEncoderTurret(){
    encoderTurret.setPosition(0);
  }

  /**
   * reset the encoder for the wheel
   */
  public void resetEncoderWheel(){
    encoderWheel.setPosition(0);
  }

  /**
   * reset the encoder for the hood
   */
  public void resetEncoderHood(){
    encoderHood.setPosition(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
