/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.Drivetrain;

/**
 * Takes the user input from the controllers and applies it to each of the subsystems
 */
public class Teleop {

    private OI mOi;
    private Drivetrain mDrivetrain;

    /**
     * Creates a new Teleop`
     * 
     * @param mOi Robot.java mOi instance
     * @param mDrivetrain Robot.java mDrivetrain instance
     */
    public Teleop(OI mOi, Drivetrain mDrivetrain){
        this.mOi = mOi;
        this.mDrivetrain = mDrivetrain;
    }

    // initiate drive stick variables
    public double y = 0, turn = 0, speed = 0;
    
    /**
     * Drivestick teleop control. Once called it will let you drive.
     */
    public void driveStick(){

        // sets the default speed to 70%
        speed = 0.7;
        
        // Change the speed depending on snail 40%, turbo 100%
        if(mOi.buttonSnail.get()) speed = 0.4;
        if(mOi.buttonTurbo.get()) speed = 1.0;

        //Get the y and turn speed after applying the deadzone
        y = deadzone(mOi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY), 0.07, 0.07);
        turn = deadzone(mOi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_TURN), 0.07, 0.07);
        
        mDrivetrain.arcade(y * speed, turn * speed * 0.7);
    }


    /**
     * If the value is within the deazone then the robot will not move
     * 
     * @param input Raw joystick value
     * @param deadzonePos positive deadzone of the joystick
     * @param deadzoneNeg negative deadzone of the joystick
     * @return Corrected value of the input so there is no false negatives
     */
    private double deadzone(double input, double deadzonePos, double deadzoneNeg){

        if(input < deadzonePos && input > -deadzoneNeg) input = 0;
        return input;
    }

}