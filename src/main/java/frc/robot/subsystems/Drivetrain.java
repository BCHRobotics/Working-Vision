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
public class Drivetrain extends SubsystemBase {

  //Setup CANSparkMax Devices
  private CANSparkMax SPARK_FRONTLEFT = new CANSparkMax(RobotMap.SPARK_FRONTLEFT, MotorType.kBrushless);
  private CANSparkMax SPARK_FRONTRIGHT = new CANSparkMax(RobotMap.SPARK_FRONTRIGHT, MotorType.kBrushless);
  private CANSparkMax SPARK_BACKLEFT = new CANSparkMax(RobotMap.SPARK_BACKLEFT, MotorType.kBrushless);
  private CANSparkMax SPARK_BACKRIGHT = new CANSparkMax(RobotMap.SPARK_BACKRIGHT, MotorType.kBrushless);

  //Setup CANSparkMax Encoders
  private CANEncoder encoderFL = new CANEncoder(SPARK_FRONTLEFT);
  private CANEncoder encoderFR = new CANEncoder(SPARK_FRONTRIGHT);
  private CANEncoder encoderBL = new CANEncoder(SPARK_BACKLEFT);
  private CANEncoder encoderBR = new CANEncoder(SPARK_BACKRIGHT);

  private double rampRate = 5;
  private double encoderCal = 1.875;

  /**
   * Creates a new DriveTrain.
   */
  public Drivetrain(Boolean newBot) {

    //Changes which motors are inverted depending on which robot is in use
    if(newBot){

      SPARK_FRONTLEFT.setInverted(false);
      SPARK_FRONTRIGHT.setInverted(false);
      SPARK_BACKLEFT.setInverted(false);
      SPARK_BACKRIGHT.setInverted(false);

    } else {

      SPARK_FRONTLEFT.setInverted(true);
      SPARK_FRONTRIGHT.setInverted(false);
      SPARK_BACKLEFT.setInverted(true);
      SPARK_BACKRIGHT.setInverted(false);

    }

    //Sets ramprate for drive train
    SPARK_FRONTLEFT.setClosedLoopRampRate(rampRate);
    SPARK_FRONTRIGHT.setClosedLoopRampRate(rampRate);
    SPARK_BACKLEFT.setClosedLoopRampRate(rampRate);
    SPARK_BACKRIGHT.setClosedLoopRampRate(rampRate);

  }

  /**
   * Control the robot using one variable to drive forward and one to turn
   * 
   * @param speedY y-axis speed between -1.0 and 1.0
   * @param speedTurn turn speed bewteen -1.0 and 1.0
   */
  public void arcade(double speedY, double speedTurn){

    SPARK_FRONTLEFT.set(speedY - speedTurn);
    SPARK_BACKLEFT.set(speedY - speedTurn);

    SPARK_FRONTRIGHT.set(speedY + speedTurn);
    SPARK_BACKRIGHT.set(speedY + speedTurn);
    
  }

  //Tank drive for Vision Test

  public void tank(double moveL, double moveR){

    SPARK_BACKLEFT.set(moveL);
    SPARK_FRONTLEFT.set(moveL);
    
    SPARK_FRONTRIGHT.set(moveR);
    SPARK_BACKRIGHT.set(moveR);

  }

  /**
   * Get's the encoder value of the front left drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderFL(){
    return encoderFL.getPosition() * encoderCal;
  }

  /**
   * Get's the encoder value of the front right drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderFR(){
    return encoderFR.getPosition() * encoderCal;
  }


  /**
   * Get's the encoder value of the back left drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderBL(){
    return encoderBL.getPosition() * -encoderCal;
  }

  /**
   * Get's the encoder value of the back right drive motor
   * 
   * @return Distance wheel moved in inches
   */
  public double getEncoderBR(){
    return encoderBR.getPosition() * encoderCal;
  }

  /**
   * Reset all drive encoders to 0
   */
  public void resetEncoders(){
    encoderFL.setPosition(0);
    encoderFR.setPosition(0);
    encoderBL.setPosition(0);
    encoderBR.setPosition(0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
