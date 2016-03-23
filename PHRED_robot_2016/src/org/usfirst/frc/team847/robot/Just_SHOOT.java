package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class Just_SHOOT implements RobotMap  {
    Relay rollerMotor;
    Victor bottomShooterMotor;
    Victor topShooterMotor;
    
    double timer = 0;
    
public void reset(){
 timer = 0;
}
public void Shoot(){
	bottomShooterMotor = new Victor(2);
	topShooterMotor = new Victor(1);
	rollerMotor = new Relay(3);
//50 on the timer = 1 secconds. so 100 = 2, 150 = 3, 200 = 4, and 250 = 5
//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
    topShooterMotor.set(TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE);
    bottomShooterMotor.set(BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE);
    timer++;
    if(timer <= 100) {
    
        rollerMotor.set(Relay.Value.kOff);
    }else{
        rollerMotor.set(Relay.Value.kForward);
}
	}
		}