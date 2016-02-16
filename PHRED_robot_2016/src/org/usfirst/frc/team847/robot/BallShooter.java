/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class BallShooter extends RobotMap{
    Relay rollerMotor;
    Victor tiltMotor;
    Victor bottomShooterMotor;
    Victor topShooterMotor;
    Joystick intakeJoystick;
    DigitalInput lazer;
    int flag = 0;
    int shoot = 1;
    int intake = 3;
    int expell = 2 ;
    int i = 0;
    
    
    public BallShooter() {
		tiltMotor = new Victor(3);
		bottomShooterMotor = new Victor(2);
		topShooterMotor = new Victor(1);
		intakeJoystick = new Joystick(1);
		rollerMotor = new Relay(1);
		lazer = new DigitalInput(12);
    
    }
    
    public void shootingMethod() {

        System.out.println("lazer = " + lazer.get());
        if(intakeJoystick.getRawButton(5)) {
            flag = intake;
        }else if(intakeJoystick.getRawButton(6)) {
            flag = expell;
        }else if(intakeJoystick.getRawButton(2)) {
            flag = shoot;
        }else{
            flag = 0;
        }
        
        switch(flag) {
            case 1:    //shoot:
                topShooterMotor.set(-1.0);
                bottomShooterMotor.set(1.0);
                
                if(i <= 25) {
                    i++;
                    rollerMotor.set(Relay.Value.kOff);
                } else {
                    rollerMotor.set(Relay.Value.kForward);                
                }
                break;
            case 2: //expell: 
                rollerMotor.set(Relay.Value.kReverse);
                    
                break;
                //break;
            case 3: //intake: 
                  if(!lazer.get()) {
                    rollerMotor.set(Relay.Value.kForward);
                    break;
                }    

            default:
                rollerMotor.set(Relay.Value.kOff);
                bottomShooterMotor.set(0.0);
                topShooterMotor.set(0.0);
                i = 0;
                break;
        }
    }
}