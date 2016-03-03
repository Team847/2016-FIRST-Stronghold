package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain implements RobotMap {

Victor backMotor;
Victor leftMotor;
Victor rightMotor;
CANTalon turnMotor;

double speed1;
double speed2;
double speed3;
double frameLength = FRAME_LENGTH;
double frameWidth = FRAME_WIDTH;
double speedLeft;
double speedRight;
double speedBack;
double radianDirection;
double longRadius;
double middleRadius;
double shortRadius;
boolean leftSwitch;
boolean rightSwitch;
double rawMagnitude;
double preMagnitude;
GamePad gamePad1;
GamePad ferrariWheel;
int startPos;
int minPos;
int maxPos;
int posDif;

public DriveTrain(GamePad driverPad, GamePad steeringWheel) {
	
	gamePad1 = driverPad;
    ferrariWheel = steeringWheel;
	startPos = 506;
	minPos = 148;
	maxPos = 836;
	backMotor = new Victor(BACK_MOTOR);
	leftMotor = new Victor(LEFT_MOTOR);
	rightMotor = new Victor(RIGHT_MOTOR);
	turnMotor = new CANTalon(TURN_MOTOR);
	leftMotor.setInverted(true);
	
	backMotor.set(0);
	leftMotor.set(0);
	rightMotor.set(0);
}

public void driveController() {

	//if(!gamePad1.startB() && !gamePad1.backB()) {
    	double feedsd = gamePad1.quadraticLY();
    	double feeddir = ferrariWheel.quadraticLX(); 

    	turnWheel(feeddir);
		driveWheels(feedsd);
	//}
	/*else {
		pivot();
	}*/
return;
}
public void testMotor(){
turnMotor.set(0.5);
return;
	
//This was for testing stuff but it now is dead X |D
//ORCAS WILL NEVER DIE    :)     ORCAS!!!!!!!!!!
// NOT This part under it though
}
//"Software Turn" stuff for the Back wheel changing angle  
public void turnWheel(double direction){

	if(direction <= 0)
		posDif = startPos - minPos;
	else
		posDif = maxPos - startPos;
	
	int turn = (int)(direction * posDif) + startPos;

	moveToTarget(turn);
	
    return; 
}

private void moveToTarget(int targetPosition){
	
	double speed = 0;
	
	int currentPosition = turnMotor.getAnalogInRaw();
	int distanceToTarget = Math.abs(targetPosition - currentPosition);
	
	if(distanceToTarget > 100)
		speed = 0.5;
	else if(distanceToTarget > 50)
		speed = 0.4;
	else if(distanceToTarget > 30)
		speed = 0.3;
	else if(distanceToTarget > 15)
		speed = 0.2;
	else if(distanceToTarget > 2)
		speed = 0.1;
	else
		speed = 0;
	
	if (currentPosition > targetPosition)
		speed *= -1;

	turnMotor.set(speed);

}

public void driveWheels(double speed){

radianDirection = (-(turnMotor.getAnalogInRaw()-startPos))*Math.PI/(posDif*2);
System.out.println("radianDirection:   " + radianDirection);
//"Speed" This sets the speed of the wheels also makes it go straight when going forwards 
if (radianDirection > -0.01 && radianDirection <0.1){
speedLeft = speed;
speedRight = speed;
speedBack = speed;
}

//"Right Turn Code" gets the wheel speed to change depending on how fast and sharp of a turn you want while turning right
if (radianDirection <= -0.01 && radianDirection >= Math.PI/-2 ) {
middleRadius = frameLength/(Math.sin(Math.abs(radianDirection)));
shortRadius = (middleRadius*Math.cos(Math.abs(radianDirection)))- (frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedRight = speed;
speedLeft = speed * shortRadius/middleRadius;
speedBack = speed * middleRadius/longRadius;
}

//"Left turn Code" 
//It dosent change much from the Right turn code just line 113 is difrent from line 97 
//Also  lines 127-129 change from lined 105-107 
if (radianDirection >= 0.01 && radianDirection <= Math.PI/2) {

middleRadius = frameLength/(Math.sin(Math.abs(radianDirection)));
shortRadius = (middleRadius*Math.cos(Math.abs(radianDirection)))-(frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedLeft = speed;
speedRight = speed * shortRadius/middleRadius;
speedBack = speed * middleRadius/longRadius;
}
backMotor.set(speedBack);
leftMotor.set(speedRight);
rightMotor.set(speedLeft);
return;
}
public void pivot() {
	//turnMotor.setPID(0.0, 0.0, 0.0);
	if(gamePad1.backB()) {
		//backMotor.setPosition(-1400);
	}
	else if(gamePad1.startB()) {
	//backMotor.setPosition(1400);
	}	
}
	}