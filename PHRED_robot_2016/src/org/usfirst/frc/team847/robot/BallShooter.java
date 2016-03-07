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
 * 
 */
public class BallShooter implements RobotMap { 
    Relay rollerMotor;
    Victor tiltMotor;
    Victor bottomShooterMotor;
    Victor topShooterMotor;
    DigitalInput lazer;
    
    boolean buttonUp = false;
    boolean buttonDown = false;
    boolean upTrue = false;
    boolean downTrue = false;
    boolean bUp = false;
    boolean bDown = false;
    
    int flag = 0;
    int i = 0;
    
    GamePad shooter;
    GamePad sharedgamePad;
    
    Victor Elevator;
    DigitalInput elevatorUp;
    DigitalInput elevatorDown;
    
    public BallShooter(GamePad objectManipulation, GamePad sharedPad) {
		shooter = objectManipulation;
		sharedgamePad = sharedPad;
//   	tiltMotor = new Victor(3); Elevator
		bottomShooterMotor = new Victor(2);
		topShooterMotor = new Victor(1);
		rollerMotor = new Relay(3);
		lazer = new DigitalInput(0);
		Elevator = new Victor(3);
		elevatorUp = new DigitalInput(1);
		elevatorDown = new DigitalInput(2);
    }
    public void runShooter(){
    	
    	elevator();
    	
    	if(elevatorDown.get())
    		shootingMethod();
    // runs the elevator you check the button you used for the shooter Elevator
    return;
    }
    
    public void shootingMethod() {

        //System.out.println("lazer = " + lazer.get(An Elevator));

        if(shooter.lBumper()) {
            flag = INTAKE;
        }else if(shooter.rBumper()) {
            flag = EXPELL;
        }else if(shooter.bButton()) {
            flag = SHOOT;
        }else{
            flag = 0;
        }
   /**Hello
   */
   /*
    */
  
        switch(flag) {
            case 1:    //shoot:
                topShooterMotor.set(TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE);
                bottomShooterMotor.set(BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE);
                
                if(i <= 25) {
                    i++;
                    rollerMotor.set(Relay.Value.kOff);
                }else{
                    rollerMotor.set(Relay.Value.kForward);
                }
                break;

			case 2: //expell: 
                rollerMotor.set(Relay.Value.kReverse);
                    
                break;

			case 3: //intake:
				if(lazer.get()) {
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
    public void elevator() {
    	
    	buttonUp = sharedgamePad.lBumper();
    	buttonDown = sharedgamePad.xButton();
    	
    if(shooter.leftTrigger() >= 0.5 || buttonUp == true)
        upTrue = true;
    else upTrue = false;
        
    if(shooter.rightTrigger() >= 0.5 || buttonDown == true)
        downTrue = true;
    else downTrue = false;
    	
    if(upTrue && !elevatorUp.get()) {
    Elevator.set(0.75);
    }
    else if(downTrue && !elevatorDown.get()) {
    Elevator.set(-0.75);
    }
    else {
    Elevator.set(0);
    }
    
    return;
    
    }

    public void elevatorDown(){
    	
    	if(!elevatorDown.get())
    		Elevator.set(-.75);
    	else Elevator.set(0);
    	
    	topShooterMotor.set(TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE);
        bottomShooterMotor.set(BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE);
        
        if(i <= 25) {
            i++;
            rollerMotor.set(Relay.Value.kOff);
        }else{
            rollerMotor.set(Relay.Value.kForward);
        }
        
    	return;
    }

}