package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	CANTalon Shoulder = new CANTalon(CANTALON_SHOULDER);
	
	double ePosition;
	double sPosition;
	boolean elbowQuadrantOne = true;
	boolean is15broke = false;
	boolean isHeightBroke = false;
	
	AnalogInput sPot = new AnalogInput(ANALOG_IN_SHOULDER);
	AnalogInput ePot = new AnalogInput(ANALOG_IN_ELBOW);
		
	public ObstacleArmElbow(GamePad xbox){
		gamePad = xbox;
		
		Shoulder.set(0);
		Elbow.set(0);
	}
	
	public void moveArm (){

		ePosition = ePot.getVoltage();
		sPosition = sPot.getVoltage();

		//MaxReachCheck();
		shoulderJoint();
		elbowJoint();
	}
	
	public void shoulderJoint(){
				
		double reach_S = -gamePad.rightStickY() * 0.5;

		if(sPosition >= MAX_S && reach_S > 0)
		    reach_S = 0;
		
		else if(sPosition <= MIN_S && reach_S < 0)
			reach_S = 0;
		
/*		if(is15broke && reach_S > 0)
			reach_S = 0;

		if(isHeightBroke && reach_S < 0)
			reach_S = 0;
		
		if(reach_S > 0){
			if(sPosition >= MAX_S && reach_S > 0)
				reach_S = 0;
			else if(sPosition <= MIN_S && reach_S < 0)
				reach_S = 0;
		}
*/
		//System.out.println("Shdlr:" + reach_S);
		//System.out.println("ShdlrPot: " + sPosition);

		Shoulder.set(reach_S);
	}
		
	public void elbowJoint(){
		
		double reach_E = gamePad.leftStickY() * 0.5;

		if( ePosition <= MIN_E  && reach_E < 0)
			reach_E = 0;
		
/*		if(is15broke){
			if(elbowQuadrantOne && reach_E > 0)
				reach_E = 0;
			else if(!elbowQuadrantOne && reach_E < 0)
				reach_E = 0;
		}
		
		if(isHeightBroke && elbowQuadrantOne && reach_E < 0)
			reach_E = 0;
		
		if(reach_E > 0){
			if(ePosition <= MIN_E && reach_E < 0) 
				reach_E = 0;
			else if(ePosition >= MAX_E && reach_E > 0)
				reach_E = 0;
		}
*/
		
		//System.out.println("Elbow:" + reach_E);
		//System.out.println("ElbowPot: " + ePosition);
		Elbow.set(reach_E);
	}
	
 	public void MaxReachCheck(){
		
/*		double alpha = Elbow.getAnalogInPosition();		// |some math to convert to degrees|
		double beta = ShoulderE.getAnalogInPosition();	// |some math to convert to degrees|
		
		double angleOne = 90 - beta;
		double angleTwo = alpha - angleOne;
		double lengthOne = ARM_BICEP*Math.sin(angleOne);
		double lengthTwo = ARM_TRIICEP*Math.sin(angleTwo);

		
		if((lengthOne + lengthTwo) >= MAX_REACH){
			if(lengthOne >= lengthTwo){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			else if(lengthTwo > lengthOne){
				Elbow.set(-ELBOW_SPEED);
			}
		}
		else if(lengthOne+lengthTwo < MAX_REACH){
			shoulderJoint();
			elbowJoint();
		}
		return is15broke;
*/
		double VoltToAngle = Math.PI/4;   //Multiplier to convert volts to an angle, radians/volt
		double ninetyDegrees = Math.PI/2; //Radians in 90 degrees

		double angleOne = (sPosition - MIN_S) * VoltToAngle;
		double lengthOne = ARM_BICEP * Math.sin(angleOne);
		double heightOne = ARM_BICEP * Math.cos(angleOne);
		
		double angleTwo = (ePosition - MIN_E) * VoltToAngle;
		double heightTwo;
		
		if(angleTwo > ninetyDegrees){
			heightTwo = 0;
			elbowQuadrantOne = false;
			angleTwo = Math.PI - angleTwo;
		} else {
			heightTwo = ARM_TRICEP * Math.cos(angleTwo);
			elbowQuadrantOne = true;
		}
		
		double lengthTwo = ARM_TRICEP * Math.sin(angleTwo);
		
		if(lengthOne + lengthTwo >= MAX_REACH)
			is15broke = true;
		else
			is15broke = false;
		
		if(heightOne + heightTwo > MAX_HEIGHT)
			isHeightBroke = true;
		else
			isHeightBroke = false;

		return;
		
 	}
}