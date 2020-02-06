/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class BallHandler extends SubsystemBase {

  TalonSRX TALON_BALLHANDLE1 = new TalonSRX(RobotMap.TALON_BALLHANDLE1);
  TalonSRX TALON_BALLHANDLE2 = new TalonSRX(RobotMap.TALON_BALLHANDLE2);
  TalonSRX TALON_BALLHANDLE3 = new TalonSRX(RobotMap.TALON_BALLHANDLE3);
  TalonSRX TALON_BALLHANDLE4 = new TalonSRX(RobotMap.TALON_BALLHANDLE4);

  DigitalInput BALLSENSE0 = new DigitalInput(RobotMap.DIO_BALLSENSE0);
  DigitalInput BALLSENSE1 = new DigitalInput(RobotMap.DIO_BALLSENSE1);
  DigitalInput BALLSENSE2 = new DigitalInput(RobotMap.DIO_BALLSENSE2);
  DigitalInput BALLSENSE3 = new DigitalInput(RobotMap.DIO_BALLSENSE3);
  DigitalInput BALLSENSE4 = new DigitalInput(RobotMap.DIO_BALLSENSE4);



  private boolean[] balls = {false, false, false, false, false};



  /**
   * Creates a new BallHandler.
   */
  public BallHandler() {

    TALON_BALLHANDLE1.setInverted(false);
    TALON_BALLHANDLE2.setInverted(false);
    TALON_BALLHANDLE3.setInverted(false);
    TALON_BALLHANDLE4.setInverted(false);

  }

  /**
   * intakes the ball into the ball handler
   * 
   */
  public void load(){


  }

  /**
   * Lets out the balls from the ball handler to the shooter
   * 
   */
  public void unload(){

  }

  /**
   * Lets out the balls from the ball handler
   * 
   */
  public void reverseUnload(){

  }

 

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("Ball0", this.balls[0]);
    SmartDashboard.putBoolean("Ball1", this.balls[1]);
    SmartDashboard.putBoolean("Ball2", this.balls[2]);
    SmartDashboard.putBoolean("Ball3", this.balls[3]);
    SmartDashboard.putBoolean("Ball4", this.balls[4]);

    balls[0] = !BALLSENSE0.get();
    balls[1] = !BALLSENSE1.get();
    balls[2] = !BALLSENSE2.get();
    balls[3] = !BALLSENSE3.get();
    balls[4] = !BALLSENSE4.get();

  }
}
