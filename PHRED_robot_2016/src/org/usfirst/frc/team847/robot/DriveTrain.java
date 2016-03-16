package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Victor;

public class DriveTrain implements RobotMap {

	Victor backMotor;
	Victor leftMotor;
	Victor rightMotor;
	CANTalon turnMotor;
	GamePad gamePad1;
	GamePad ferrariWheel;

	double speedLeft;
	double speedRight;
	double speedBack;
	double radianDirection;
	double rightRadius;
	double backRadius;
	double leftRadius;

	boolean driveToggleAlreadyPressed;
	boolean driveTwoWheelForward;

	int posDif;

	public DriveTrain(GamePad driverPad, GamePad steeringWheel) {
		gamePad1 = driverPad;
		ferrariWheel = steeringWheel;
		backMotor = new Victor(BACK_MOTOR);
		leftMotor = new Victor(LEFT_MOTOR);
		rightMotor = new Victor(RIGHT_MOTOR);
		turnMotor = new CANTalon(TURN_MOTOR);
		leftMotor.setInverted(true);
			
		backMotor.set(0);
		leftMotor.set(0);
		rightMotor.set(0);
		
		driveToggleAlreadyPressed = false;
		driveTwoWheelForward = true;
	}

	public void driveController() {

    	double feedsd  = gamePad1.quadraticLY();
    	double feeddir = ferrariWheel.quadraticLX();
    	
    	//System.out.println("wheel: "+ feeddir);

    	// Flip which end of the robot is forward
    	if(ferrariWheel.lBumper()){
    		if(!driveToggleAlreadyPressed){
    			if(driveTwoWheelForward)
    				driveTwoWheelForward = false;
    			else
    				driveTwoWheelForward = true;
    		}
    		driveToggleAlreadyPressed = true;
    	} else
    		driveToggleAlreadyPressed = false;

    	if(!driveTwoWheelForward){
    		feedsd  *= -1;
    		feeddir *= -1;
    	}
    	
    	//System.out.println("wheel: "+ feeddir);
   		
    	turnWheel(feeddir);
		driveWheels(feedsd);
	}
	
	public void testMotor(){
		turnMotor.set(0.5);
	}
	
	
//This was for testing stuff but it now is dead X |D
//ORCAS WILL NEVER DIE    :)     ORCAS!!!!!!!!!!
// NOT This part under it though

//"Software Turn" stuff for the Back wheel changing angle  
	public void turnWheel(double direction){

		if(direction <= 0)
			posDif = START_POSITION - MIN_POSITION;
		else
			posDif = MAX_POSITION - START_POSITION;
	
		int turn = (int)(direction * posDif) + START_POSITION;
		System.out.println("turnpot: " + turnMotor.getAnalogInRaw());

		moveToTarget(turn);
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
		//System.out.println("tspeed: " + speed);
		turnMotor.set(speed);
	}

	public void driveWheels(double speed){

		radianDirection = (turnMotor.getAnalogInRaw() - START_POSITION) * Math.PI / (posDif * 2);

		// Drive Straight code
		if(Math.abs(radianDirection) <= 0.004){
			speedLeft = speed;
			speedRight = speed;
			speedBack = speed;
		}

		//"Right Turn Code" 
		// gets the wheel speed to change depending on how fast and sharp of a turn you want while turning right
		else if( radianDirection < 0){
			backRadius = FRAME_LENGTH / (Math.sin(Math.abs(radianDirection)));
			leftRadius = (backRadius * Math.cos(Math.abs(radianDirection))) - (FRAME_WIDTH / 2);
			rightRadius = (leftRadius + FRAME_WIDTH);
		}

		//"Left turn Code" 
		//It dosent change much from the Right turn code 
		else { 
			backRadius = FRAME_LENGTH / (Math.sin(Math.abs(radianDirection)));
			rightRadius = (backRadius * Math.cos(Math.abs(radianDirection))) - (FRAME_WIDTH / 2);
			leftRadius = (rightRadius + FRAME_WIDTH);
		}

		// Find the maximum radius.  This should be the fastest wheel
		double maxRadius = backRadius;
		if(Math.abs(maxRadius) < Math.abs(leftRadius))
			maxRadius = leftRadius;
		if(Math.abs(maxRadius) < Math.abs(rightRadius))
			maxRadius = rightRadius;
		
		// Scale the slower wheels based on the fastest
		speedBack  = speed * (backRadius / maxRadius);
		speedLeft  = speed * (leftRadius / maxRadius);
		speedRight = speed * (rightRadius / maxRadius);
		
		backMotor.set(speedBack);
		leftMotor.set(speedLeft);
		rightMotor.set(speedRight);
	}

	public void pivot() {
		if(gamePad1.backB()) {
			//backMotor.setPosition(-1400);
		}
		else if(gamePad1.startB()) {
			//backMotor.setPosition(1400);

		}	
	}
}