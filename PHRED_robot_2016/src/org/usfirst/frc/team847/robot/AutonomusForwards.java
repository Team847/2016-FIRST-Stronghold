package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class AutonomusForwards implements RobotMap  {
	Victor backMotor;
	Victor leftMotor;
	Victor rightMotor;
	

public void Autonomus(){

double timer = 0;
	backMotor = new Victor(BACK_MOTOR);
	leftMotor = new Victor(LEFT_MOTOR);
	rightMotor = new Victor(RIGHT_MOTOR);
//5 on the timer = 2 secconds. so 10 = 4, 15 = 6, 20 = 8, and 25 = 10
//but we don't want to be going streight for more than 8 secconds.  (Probobly?)
if(timer <= 15){
	backMotor.set(0.75);
	leftMotor.set(0.75);
	rightMotor.set(0.75);
	timer ++;
	
}
	}
		}