/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Retriever extends SubsystemBase {

  TalonSRX TALON_ARM = new TalonSRX(RobotMap.TALON_ARM);
  TalonSRX TALON_BAR = new TalonSRX(RobotMap.TALON_BAR);

  /**
   * Creates a new Retriever.
   */
  public Retriever() {

    TALON_ARM.setInverted(false);
    TALON_BAR.setInverted(false);

  }

  /**
   *Sets the speed of the intake
   * 
   * @param speed The speed of the intake
   */
  public void intake(double speed){

    TALON_BAR.set(ControlMode.PercentOutput, speed);

  }

  /**
   * Sets the retriever to the lower position
   * 
   * @param speed sets the speed of the arm motor when lowering
   */
  public void lower(double speed){

    if(TALON_ARM.isFwdLimitSwitchClosed() == 0){
      TALON_ARM.set(ControlMode.PercentOutput, speed);
    } else{
      TALON_ARM.set(ControlMode.PercentOutput, 0);
    }

  }

  /**
   * Sets the retriever to the raised position
   * 
   * @param speed sets the speed of the arm on the way up
   */
  public void raise(double speed){

    if(TALON_ARM.isRevLimitSwitchClosed() == 0){
      TALON_ARM.set(ControlMode.PercentOutput, -speed);
    } else{
      TALON_ARM.set(ControlMode.PercentOutput, 0);
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
