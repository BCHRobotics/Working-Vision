/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.subsystems.Drivetrain;

    /***** 
     * @author Ayrton Mercer. 
     */
    public class Ayrton {

    private AutoCommands mAutoCommands;
    private AHRS ahrs;
    private Drivetrain mDrivetrain;

    public Ayrton(AutoCommands mAutoCommands, AHRS ahrs, Drivetrain mDrivetrain){
        this.mAutoCommands = mAutoCommands;
        this.ahrs = ahrs;
        this.mDrivetrain = mDrivetrain;
    }

    /******
     * @param Start at starting point 1.
     * @param Shooting low.
     * @param Head back home. 
     */
    public void start1LowHome(){
        
        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.encoderDrive(0, 0, 0, 0, 0);

        mAutoCommands.gyroTurn(11.8, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(242.6, -0.004, 0.005, 2, 200, 1500);
        mAutoCommands.gyroTurn(-11.8, 0.005, 2, 125, 1500);
        //Shoot Now

        try{
            Thread.sleep(2000);
        } catch(Exception e){
            //Don't sleep
        }

        mAutoCommands.gyroTurn(156.6, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(48, -0.004, 0.005, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 0.005, 1, 125, 500);
        mAutoCommands.straightDrive(240, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(138.12, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(100, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 0.005, 2, 125, 1000);
    }

    /******
     * @param Start at starting point 2.
     * @param Shooting high.
     * @param Head back home. 
     */
    public void start2HighHome(){

        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.encoderDrive(0, 0, 0, 0, 0);

        mAutoCommands.straightDrive(48, -0.004, 0.005, 2, 200, 2500);
        //Shoot Now
        mAutoCommands.gyroTurn(156.6, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(48, -0.004, 0.005, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 0.005, 1, 125, 500);
        mAutoCommands.straightDrive(240, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(138.12, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(100, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 0.005, 2, 125, 1000);
    }

    /******
     * Start at starting point 2.
     * Shooting low.
     * Head to trench run. 
     */
    public void start2LowTrench(){

        mAutoCommands.encoderDrive(0, 0, 0, 0, 0);

        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.straightDrive(80, -0.004, 0.005, 2, 200, 2500);
        //Shoot Now
        mAutoCommands.gyroTurn(156.6, 0.005, 2, 125, 2000);
        mAutoCommands.straightDrive(168.49, -0.004, 0.005, 2, 200, 2500);
    }

    /******
     * @param Start at starting point 2.
     * @param Shooting low.
     * @param Head back home. 
     */
    public void start2LowHome(){

        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.encoderDrive(0, 0, 0, 0, 0);

        mAutoCommands.straightDrive(80, -0.004, 0.005, 2, 200, 2500);
        //Shoot Now
        mAutoCommands.gyroTurn(156.6, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(48, -0.004, 0.005, 2, 200, 1500);
        mAutoCommands.gyroTurn(28, 0.005, 1, 125, 500);
        mAutoCommands.straightDrive(240, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(138.12, 0.005, 2, 125, 1500);
        mAutoCommands.straightDrive(100, -0.004, 0.005, 125, 200, 1000);
        mAutoCommands.gyroTurn(48, 0.005, 2, 125, 1000);
    }

    /******
     * @param Start at starting point 3.
     * @param Shooting low.
     * @param Head to rendez-vous point. 
     */
    public void start3LowRendezVous(){
       
        ahrs.reset();
        mDrivetrain.resetEncoders();

        mAutoCommands.encoderDrive(0, 0, 0, 0, 0);

        mAutoCommands.gyroTurn(43.235, -0.004, 0.005, 0.5, 1000);
        mAutoCommands.straightDrive(129.429, 0.005, 0.5, 2, 200, 1500);
        mAutoCommands.gyroTurn(-43.235, -0.004, 0.005, 0.5, 1000);
        //Shoot Now
        mAutoCommands.gyroTurn(61.129, 0.005, 3, 200, 1000);
        mAutoCommands.straightDrive(187.963, -0.004, 0.005, 2, 200, 2000);
    }
}