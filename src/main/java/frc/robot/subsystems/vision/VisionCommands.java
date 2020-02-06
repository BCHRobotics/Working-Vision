/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class VisionCommands {

	//Taget Visible, target x, target y, target area
    private double tv, tx, ty, ta;
    private double range = 1;

    private double setpointTurret = 0, setpointHood = 0;

    private double kP = 0, kI = 0, kD = 0;
    private double error = 0, derivative = 0, integral = 0, rcw = 0;
    private double endTime = 0, inRange = 0, inRangeSet = 0;

    private double kP2 = 0, kI2 = 0, kD2 = 0;
    private double error2 = 0, derivative2 = 0, integral2 = 0, rcw2 = 0;

    private double previous_error = 0;
    private double previous_error2 = 0;

    private Drivetrain mDrivetrain;

    public VisionCommands(Drivetrain mDrivetrain){
        this.mDrivetrain = mDrivetrain;

        this.kP = RobotMap.P_TURRET;
        this.kI = RobotMap.I_TURRET;
        this.kD = RobotMap.D_TURRET;

        this.kP2 = RobotMap.P_HOOD;
        this.kI2 = RobotMap.I_HOOD;
        this.kD2 = RobotMap.D_HOOD;
    }

    public void turretFun(double speed){
        
        if(tv == 1){

            if(tx > range){

                mDrivetrain.tank(tx * -0.0175, tx * 0.0175);
            }

            if(tx < -range){

                mDrivetrain.tank(tx * -0.0175, tx * 0.0175);
            }
            if(tx > -1 && tx < 1){

                mDrivetrain.tank(0,0);
          }
        }else{
            mDrivetrain.tank(0,0);
        }


    }

    public void turretPID(double speed){

        error = setpointTurret - tx;
        integral = integral + (error * 0.02);
        derivative = (error - previous_error) / 0.02;
        rcw = ( kP * error) + ( kI * integral) + ( kD * derivative);
        previous_error = error;

        mDrivetrain.arcade(0, rcw*speed);

    }

    public void hoodPID(double speed){

        error2 = setpointHood - ty;
        integral2 = integral2 + (error2 * 0.02);
        derivative2 = (error2 - previous_error2) / 0.02;
        rcw2 = ( kP2 * error2) + ( kI2 * integral2) + ( kD2 * derivative2);
        previous_error2 = error2;

        mDrivetrain.arcade(0, rcw2*speed);

    }

    public void standAloneTurretPID(double speed, double time){

        this.endTime = System.currentTimeMillis() + time;
        this.inRange = 0;
    
        boolean runLoop = true;

        //Run until you reach the time
        while(runLoop){

            if(System.currentTimeMillis() >= this.endTime) runLoop = false;

            turretPID(speed);

        }

        mDrivetrain.arcade(0, 0);

    }

    public void standAloneHoodPID(double speed, double time){

        this.endTime = System.currentTimeMillis() + time;
        this.inRange = 0;
    
        boolean runLoop = true;

        //Run until you reach the time
        while(runLoop){

            if(System.currentTimeMillis() >= this.endTime) runLoop = false;

            hoodPID(speed);

        }

        mDrivetrain.arcade(0, 0);

    }

    public void periodic() {
        tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        SmartDashboard.putNumber("tv", tv);
        SmartDashboard.putNumber("tx", tx);
        SmartDashboard.putNumber("ty", ty);
        SmartDashboard.putNumber("ta", ta);
    }

}
            
