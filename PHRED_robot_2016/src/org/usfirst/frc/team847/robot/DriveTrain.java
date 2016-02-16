package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain {

Victor backMotor;
Victor leftMotor;
Victor rightMotor;
CANTalon turnMotor;
Encoder turnCounter;


double speed1;
double speed2;
double speed3;

double frameLength = 23;
double frameWidth = 23;
double speedLeft;
double speedRight;
double speedBack;
double radianDirection;
double longRadius;
double middleRadius;
double shortRadius;
int ninetyClicks;

public void TriDrive() {
	
	backMotor = new Victor(1);
	leftMotor = new Victor(2);
	rightMotor = new Victor(4);
	turnMotor = new CANTalon(3);
	
	backMotor.set(0);
	leftMotor.set(0);
	rightMotor.set(0);
	turnMotor.set(0);
	turnCounter.reset();
}	

public void turnWheel(double direction){
	
	int turn = (int)direction*ninetyClicks;
	int position = turnMotor.getEncPosition();
	
	if(position>ninetyClicks){
		turnMotor.set(0);
	}
	if(position<-ninetyClicks){
		turnMotor.set(0);
	}
	if(position>turn){
		turnMotor.set(-.25);
	}
	if(position<turn){
		turnMotor.set(.25);
	}
	if(position==turn){
		turnMotor.set(0);
	}
	

}
public void driveWheels(double speed){


radianDirection = turnMotor.getEncPosition()*Math.PI/(ninetyClicks*2);
//this checks to make it go straight
if (radianDirection > -0.01 && radianDirection <0.1){
speedLeft = speed;
speedRight = speed;
speedBack = speed;
}
if (radianDirection >= 0.01 && radianDirection <= Math.PI/2 ) {
middleRadius = frameLength/(Math.sin(radianDirection));
shortRadius = (middleRadius*Math.cos(radianDirection))-(frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedRight = speed;
speedLeft = speed * shortRadius/middleRadius;
//Rg I'm A Pirate (An Orca PIRATE)
speedBack = speed * middleRadius/longRadius;
if (speedBack>speedRight){
speed1 = speedBack;
speedBack = speedRight;
speedRight = speed1;

}
}
backMotor.set(speedBack);
leftMotor.set(speedLeft);
rightMotor.set(speedRight);





if (radianDirection <= -0.01 && radianDirection >= Math.PI/-2) {
middleRadius = frameLength/(Math.sin(radianDirection));
shortRadius = (middleRadius*Math.cos(radianDirection))-(frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedRight = speed;
speedLeft = speed * shortRadius/middleRadius;
//Rg I'm A Pirate (An Orca PIRATE)
speedBack = speed * middleRadius/longRadius;
if (speedBack>speedRight){
speed1 = speedBack;
speedBack = speedLeft;
speedLeft = speed1;

}
}
backMotor.set(speedBack);
leftMotor.set(speedRight);
rightMotor.set(speedLeft);
}
}