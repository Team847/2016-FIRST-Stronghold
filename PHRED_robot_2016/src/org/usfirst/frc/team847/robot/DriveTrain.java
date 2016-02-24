package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain {

Victor backMotor;
Victor leftMotor;
Victor rightMotor;
CANTalon turnMotor;

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
double ninetyClicks;
boolean leftSwitch;
boolean rightSwitch;
double rawMagnitude;
double preMagnitude;
GamePad gamePad1;

public DriveTrain(GamePad driverPad) {
	
	driverPad = gamePad1;
	backMotor = new Victor(4);
	leftMotor = new Victor(6);
	rightMotor = new Victor(5);
	turnMotor = new CANTalon(3);
	leftMotor.setInverted(true);
	
	backMotor.set(0);
	leftMotor.set(0);
	rightMotor.set(0);
	ninetyClicks = 1400;
	turnMotor.changeControlMode (CANTalon.TalonControlMode.Position);
	turnMotor.setPosition(0);
	turnMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	turnMotor.reverseSensor(true);
	turnMotor.setPID(0.9, 0.004, 0.0);
}	

public void testMotor(){
//This was for testing stuff but it now is dead X |D
//ORCAS WILL NEVER DIE                          X |
// NOT This part under it though
return;
}
//"Software Turn" stuff for the Back wheel changing angle  
public void turnWheel(double direction){
	double turn2 = direction*ninetyClicks;
	int turn = (int)turn2;
	System.out.println("direction: " + direction);
	System.out.println("forward: " + turnMotor.isFwdLimitSwitchClosed());
	System.out.println("reverse: " + turnMotor.isRevLimitSwitchClosed());
	System.out.println("turn: " + turn);
	int position = turnMotor.getEncPosition();

	if (position<-ninetyClicks && direction == -1)
		turnMotor.setPosition(-ninetyClicks);
	else if(position>ninetyClicks && direction == 1)
		turnMotor.setPosition(ninetyClicks);
	
	
	else
	turnMotor.set(turn);
	

    return; 
}
public void driveWheels(double speed){
	
//"Speed Ramping" This is The Part where It ramps the speed up to a faster speed the longer you hold the joystick
	preMagnitude = rawMagnitude;
	rawMagnitude = gamePad1.getMagnitude() ;
	
	rawMagnitude = preMagnitude + ((rawMagnitude - preMagnitude)/50);
	
	speed = rawMagnitude;
	
			radianDirection = turnMotor.getEncPosition()*Math.PI/(ninetyClicks*2);
//"Speed" This sets the speed of the wheels also makes it go straight when going forwards 
if (radianDirection > -0.01 && radianDirection <0.1){
speedLeft = speed;
speedRight = speed;
speedBack = speed;
}

//"Right Turn Code" gets the wheel speed to change dependingon how fast and sharp of a turn you want while turning right
if (radianDirection <= -0.01 && radianDirection >= Math.PI/-2 ) {
middleRadius = frameLength/(Math.sin(Math.abs(radianDirection)));
shortRadius = (middleRadius*Math.cos(Math.abs(radianDirection)))- (frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedRight = speed;
speedLeft = speed * shortRadius/middleRadius;
speedBack = speed * middleRadius/longRadius;
if (speedBack>speedRight){
speed1 = speedBack;
speedBack = speedRight;
speedRight = speed1;

}
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
if (speedBack>speedRight){
speed1 = speedBack;
speedBack = speedLeft;
speedLeft = speed1;
}
}
backMotor.set(speedBack);
leftMotor.set(speedRight);
rightMotor.set(speedLeft);
return;
}
}