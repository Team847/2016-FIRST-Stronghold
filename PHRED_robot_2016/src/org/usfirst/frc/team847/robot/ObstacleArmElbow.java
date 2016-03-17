package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	CANTalon Shoulder = new CANTalon(CANTALON_SHOULDER);
	
	AnalogInput sPot;
	AnalogInput ePot;

	double ePosition;
	double sPosition;
	
	boolean elbowQuadrantOne = true;
	boolean is15broke = false;
	boolean isHeightBroke = false;
		
	public ObstacleArmElbow(GamePad xbox){
		
		sPot = new AnalogInput(ANALOG_IN_SHOULDER);
		ePot = new AnalogInput(ANALOG_IN_ELBOW);
		
		gamePad = xbox;
	
		Shoulder.set(0);
		Elbow.set(0);
	}
	
	public void armOverride(){
		double reach_S = -gamePad.rightStickY() * SHOULDER_SPEED_ADJ;
		double reach_E = gamePad.leftStickY() * ELBOW_SPEED_ADJ;
		Shoulder.set(reach_S);
		Elbow.set(reach_E);
	}
	
	public void moveArm (){

		ePosition = ePot.getAverageVoltage();
		sPosition = sPot.getAverageVoltage();

		MaxReachCheck();
		shoulderJoint();
		elbowJoint();
	}
	
	public void shoulderJoint(){
				
		double reach_S = -gamePad.rightStickY() * SHOULDER_SPEED_ADJ;

/*		if(sPosition >= MAX_S && reach_S > 0)
		    reach_S = 0;
		
		else if(sPosition <= MIN_S && reach_S < 0)
			reach_S = 0;
*/		
		if(is15broke && reach_S > 0)
			reach_S = 0;

		if(isHeightBroke && reach_S < 0)
			reach_S = 0;
		
		if(Math.abs(reach_S) > 0){
			if(sPosition >= MAX_S && reach_S > 0)
				reach_S = 0;
			else if(sPosition <= MIN_S && reach_S < 0)
				reach_S = 0;
		}

		//System.out.println("Shdlr:" + reach_S);
		//System.out.println("ShdlrPot: " + sPosition);

		Shoulder.set(reach_S);
	}
		
	public void elbowJoint(){
		
		double reach_E = -gamePad.leftStickY() * ELBOW_SPEED_ADJ;

/*		if( ePosition <= MIN_E  && reach_E < 0)
			reach_E = 0;
*/		
		if(is15broke){
			System.out.println("reach");
			if(elbowQuadrantOne && reach_E < 0)
				reach_E = 0;
			else if(!elbowQuadrantOne && reach_E > 0)
				reach_E = 0;
		}
		
		//if(isHeightBroke) System.out.println("height");
		//if(elbowQuadrantOne) System.out.println("Q1");
		//else System.out.println("Q4");
		//System.out.println("Elbow:" + reach_E);
		
		if(isHeightBroke && elbowQuadrantOne && reach_E > 0)
			reach_E = 0;
/*		
		if(Math.abs(reach_E) > 0){
			if(ePosition <= MIN_E && reach_E < 0) 
				reach_E = 0;
			else if(ePosition >= MAX_E && reach_E > 0)
				reach_E = 0;
		}
*/		
		//System.out.println("ElbowPot: " + ePosition);
		
		Elbow.set(reach_E);
	}
	
 	public void MaxReachCheck(){
		
		double ninetyDegrees = Math.PI/2; //Radians in 90 degrees
		double VoltToAngle = 1.02;//ninetyDegrees / EVERTICAL - EHORIZONTAL; //Multiplier to convert volts to an angle, radians/volt

		double angleOne = (sPosition - SVERTICAL) * VoltToAngle;
		double lengthOne = ARM_BICEP * Math.sin(angleOne);
		double heightOne = ARM_BICEP * Math.cos(angleOne);
		
		double angleTwo = (EVERTICAL - ePosition) * VoltToAngle;
		double heightTwo;
		
		if(angleTwo >= ninetyDegrees){
			heightTwo = 0;
			elbowQuadrantOne = false;
			angleTwo = Math.PI - angleTwo;
		} else {
			heightTwo = ARM_TRICEP * Math.cos(angleTwo);
			elbowQuadrantOne = true;
		}
		
		double lengthTwo = ARM_TRICEP * Math.sin(angleTwo);
		
		//angleOne = (180 *angleOne)/3.14;
		//System.out.println("sa: " + angleOne);
		//System.out.println("sl: " + lengthOne);
		//System.out.println("sh: " + heightOne);
		
		//System.out.println("el: " + lengthTwo);
		
		if(lengthOne + lengthTwo >= MAX_REACH)
			is15broke = true;
		else
			is15broke = false;
		
		if(heightOne + heightTwo >= MAX_HEIGHT)
			isHeightBroke = true;
		else
			isHeightBroke = false;
 	}
}