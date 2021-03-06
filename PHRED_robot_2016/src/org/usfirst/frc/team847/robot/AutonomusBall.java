package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class AutonomusBall implements RobotMap  {
	Victor backMotor;
	Victor leftMotor;
	Victor rightMotor;
    Relay rollerMotor;
    Victor bottomShooterMotor;
    Victor topShooterMotor;
    double timer;
    
    public void reset(){
 timer = 0;   
}
public void Autonomus(){
	
	bottomShooterMotor = new Victor(2);
	topShooterMotor = new Victor(1);
	rollerMotor = new Relay(3);
	backMotor = new Victor(BACK_MOTOR);
	leftMotor = new Victor(LEFT_MOTOR);
	rightMotor = new Victor(RIGHT_MOTOR);
//50 on the timer = 1 secconds. so 100 = 2, 150 = 3, 200 = 4, and 250 = 5
//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
if(timer <= 300){
	backMotor.set(0.75);
	leftMotor.set(0.75);
	rightMotor.set(0.75);
	timer ++;
}
if(timer >= 300){
    topShooterMotor.set(TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE);
    bottomShooterMotor.set(BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE);
    timer++;
    if(timer <= 400) {
    
        rollerMotor.set(Relay.Value.kOff);
    }else{
        rollerMotor.set(Relay.Value.kForward);
    }
    


}
	}
		}