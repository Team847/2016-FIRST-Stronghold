package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	AnalogInput pShoulder = new AnalogInput(ANALOG_IN_SHOULDER);
	AnalogInput pElbow = new AnalogInput(ANALOG_IN_ELBOW);
	
	CANTalon ShoulderE = new CANTalon(CANTALON_SHOULDER);
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	
	boolean shoulderEFWD;
	boolean shoulderEREV;
	boolean elbowFWD;
	boolean elbowREV;
	boolean shoulderMash;
	boolean elbowMash;
	
	boolean elbow15 = false;
	boolean shoulder15 = false;
	boolean elbow_soft_stop = false;
	boolean shoulder_softstop = false;
	
	double sPot;
	double ePot;
	
	public ObstacleArmElbow(GamePad xbox){
		
		gamePad = xbox;
		
		ShoulderE.set(0);
		Elbow.set(0);
		
		shoulderEFWD = ShoulderE.isFwdLimitSwitchClosed();
		shoulderEREV = ShoulderE.isRevLimitSwitchClosed();
		
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
	}

	public void armTest(){

		ShoulderE.set(gamePad.leftStickY());
		Elbow.set(gamePad.rightStickY());
		
		System.out.println("Shoulder:     " + pShoulder.getVoltage());
		System.out.println("   Elbow:     " + pElbow.getVoltage());
		System.out.println("Joystick:     " + gamePad.rightStickY());
		
		return;
	}
		
	public void MaxReachCheck(){
				
 		//alpha and beta are elbow and shoulder Pot. values transformed into degrees
		double alpha       = Math.toRadians(180 - ((ePot - MIN_E)*45)); // current angle of elbow
		double outer_beta  = Math.toRadians((sPot - MIN_S)*45);
		double beta        = 90 - outer_beta; //current angle of shoulder
		
		double angleOne    = alpha - outer_beta;
		double lengthOne   = ARM_BICEP*(Math.cos(beta));
		double lengthTwo   = ARM_TRICEP*(Math.sin(angleOne));
		double totalLength = lengthOne + lengthTwo;
		
		System.out.println("      alpha:      " + alpha);
		System.out.println("       beta:      " + beta);
		System.out.println(" outer_beta:      " + outer_beta);
		System.out.println("   angleOne:      " + angleOne);
		System.out.println("  lengthOne:      " + lengthOne);
		System.out.println("  lengthTwo:      " + lengthTwo);
		System.out.println("totalLength:      " + totalLength);
		
		if(totalLength >= MAX_REACH){
			shoulder15 = true;
			elbow15 = true;
		}
		else{
			shoulder15 = false;
			elbow15 = false;
		}
			return;
			
 	}
	
	public void shoulderJoint(){

		double reach_S = -gamePad.rightStickY();
		
//		System.out.println(reach_S);
		
		if(shoulder15 && reach_S > 0)
			ShoulderE.set(0);
		
		else if(sPot >= MAX_S && reach_S > 0){
			ShoulderE.set(0);
		}
		else if(sPot <= MIN_S && reach_S < 0){
			ShoulderE.set(0);
		}
		else
			ShoulderE.set(reach_S);
		return;
		}
		
	public void elbowJoint(){
		
		double reach_E = -gamePad.leftStickY();
		
		if(elbow15 && reach_E > 0)
			Elbow.set(0);
		
		if(ePot >= MAX_E && reach_E < 0){
			Elbow.set(0);
		}
		else if(ePot <= MIN_E && reach_E > 0){
			Elbow.set(0);
		}
		else
			Elbow.set(reach_E);
		
		return;
		}
	
	public void stallOut(){

		double S_reach = -gamePad.rightStickY();
		double E_reach = gamePad.leftStickX();
		
		if(sPot <= MAX_S && S_reach == 0){
			ShoulderE.set(SHOULDER_STALL);
		}
		
		if(ePot >= MAX_E && E_reach == 0){
			Elbow.set(ELBOW_STALL);
		}
		return;
	}
	
	public void presets(){
		
		int armFlag = 0;
		boolean liftRoutine = gamePad.xButton();
		boolean upPreset = gamePad.lStickPressed();
		boolean downPreset = gamePad.rStickPressed();
		
		if(liftRoutine){
			armFlag = PORTCULLIS_LIFT;
		}
		else if(upPreset){
			armFlag = SET_UP;
		}
		else if(downPreset){
			armFlag = SET_DOWN;
		}
		else armFlag = 0;
		
		switch(armFlag){
		
		case 1: //Portcullis lift
			
			if(sPot < SHOULDER_STEP_ONE){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(ePot < ELBOW_STEP_ONE){
				Elbow.set(SHOULDER_SPEED);
			}
			if(sPot > SHOULDER_STEP_TWO){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			if(ePot > ELBOW_STEP_TWO){
				Elbow.set(-SHOULDER_SPEED);
			}
			
			else{
				ShoulderE.set(0);
				Elbow.set(0);
			}
			
			break;
		case 2: //Set up position
			if(sPot < SHOULDER_UP_PRESET){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(sPot > SHOULDER_UP_PRESET){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			else ShoulderE.set(0);
			
			if(ePot < ELBOW_UP_PRESET){
				Elbow.set(ELBOW_SPEED);
			}
			if(ePot > ELBOW_UP_PRESET){
				Elbow.set(-ELBOW_SPEED);
			}
			else Elbow.set(0);
			
			break;
			
		case 3: //Set down position
			
			if(sPot < SHOULDER_DOWN_PRESET){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(sPot > SHOULDER_DOWN_PRESET){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			else ShoulderE.set(0);
			
			if(ePot < ELBOW_DOWN_PRESET){
				Elbow.set(ELBOW_SPEED);
			}
			if(ePot > ELBOW_DOWN_PRESET){
				Elbow.set(-ELBOW_SPEED);
			}
			else Elbow.set(0);
			
			break;
		}
	}

	public void armManager(){
		sPot = pShoulder.getVoltage();
		ePot = pElbow.getVoltage();
//		MaxReachCheck();
		shoulderJoint();
		elbowJoint();
		System.out.println("Shoulder:    " + sPot);
		System.out.println("Elbow:    " + ePot);
	}


}