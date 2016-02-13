package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain {

Victor backMotor;
Victor leftMotor;
Victor rightMotor;
CANTalon turnMotor;

//int flag;
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
boolean leftSwitch;
boolean rightSwitch;


public DriveTrain() {
	
	backMotor = new Victor(1);
	leftMotor = new Victor(4);
	rightMotor = new Victor(2);
	turnMotor = new CANTalon(3);
	leftMotor.setInverted(true);
	
	backMotor.set(0);
	leftMotor.set(0);
	rightMotor.set(0);
	ninetyClicks = 600;
	turnMotor.changeControlMode (CANTalon.TalonControlMode.Position);
	turnMotor.setPosition(0);
	turnMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	turnMotor.reverseSensor(true);
	turnMotor.setPID(7.0, 0.03, 0.0);
}	

public void testMotor(){
/*	leftSwitch = turnMotor.isFwdLimitSwitchClosed();
	rightSwitch = turnMotor.isRevLimitSwitchClosed();
	if(leftSwitch)
		leftMotor.set(0.5);
	*/
	//	System.out.println("leftSwitch: " + leftSwitch);
//	System.out.println("rightSwitch: " + rightSwitch);
return;
}

public void turnWheel(double direction){
	int turn = (int)direction*ninetyClicks;
	System.out.println("direction: " + direction);
	System.out.println("forward: " + turnMotor.isFwdLimitSwitchClosed());
	System.out.println("reverse: " + turnMotor.isRevLimitSwitchClosed());
	System.out.println("turn: " + turn);
	int position = turnMotor.getEncPosition();
	
	/*if(turnMotor.isFwdLimitSwitchClosed() && direction<0) {
		flag = 2;
	}
	else if(turnMotor.isRevLimitSwitchClosed() && direction > 0){
		flag = 1;
	}
	else
		flag = 3;
		

	switch(flag) {
		case 1:
			
			if(direction > 0)
			 	turnMotor.set(0.1);
			else {
				turn = 0;
				break;
			}
		case 2:
			
			if(direction < 0)
				turnMotor.set(-0.1);
			else {
				turn = 0;
				break;
			}
		case 3:
			turnMotor.set(turn);
			break;
	}*/
	/*if(turnMotor.isFwdLimitSwitchClosed() && direction<=0){	
		System.out.println(" Clockwise");
	}

	else if(turnMotor.isRevLimitSwitchClosed() && direction>=0){
		System.out.println(" counterClockwise");
	}
	else*/
	if (position>-ninetyClicks && position<ninetyClicks)
	turnMotor.set(turn);
	
	/*if(turnMotor.isRevLimitSwitchClosed() && direction<0){
		turnMotor.set(0);
	}
	
	else{
	turnMotor.set(turn);
	}
	
	if(turnMotor.isFwdLimitSwitchClosed() && direction>0){
		turnMotor.set(0);
	}
	
	else{
	turnMotor.set(turn);
	}*/
	
    return; 
}

public void driveWheels(double speed){

radianDirection = turnMotor.getEncPosition()*Math.PI/(ninetyClicks*2);
//this checks to make it goes straight
if (radianDirection > -0.01 && radianDirection <0.1){
speedLeft = speed;
speedRight = speed;
speedBack = speed;
}

//Right Turn Code
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
// backMotor.set(speedBack);
// leftMotor.set(speedLeft);
// rightMotor.set(speedRight);

// Left turn Code
if (radianDirection >= 0.01 && radianDirection <= Math.PI/2) {

middleRadius = frameLength/(Math.sin(Math.abs(radianDirection)));
shortRadius = (middleRadius*Math.cos(Math.abs(radianDirection)))-(frameWidth/2);
longRadius = (shortRadius+frameWidth);
speedRight = speed;
speedLeft = speed * shortRadius/middleRadius;
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