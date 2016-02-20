package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	CANTalon ShoulderE = new CANTalon(CANTALON_SHOULDER);
	double pShoulderE;
	double pElbow;
	boolean shoulderEFWD;
	boolean shoulderEREV;
	boolean elbowFWD;
	boolean elbowREV;
	boolean shoulderMash;
	boolean elbowMash;
	double shoulderSpeed = SHOULDER_SPEED;
	double elbowSpeed = ELBOW_SPEED;

	public ObstacleArmElbow(GamePad xbox){
		
		gamePad = xbox;
		
		ShoulderE.set(0);
		Elbow.set(0);
		shoulderEFWD = ShoulderE.isFwdLimitSwitchClosed();
		shoulderEREV = ShoulderE.isRevLimitSwitchClosed();
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
//		ShoulderE.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
//		Elbow.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
		pShoulderE = ShoulderE.getAnalogInRaw();
		pElbow = Elbow.getAnalogInRaw();
	}
	public void shoulderJoint(){
		
		/*  The Robot Framework program should send this method a handful of 
		 *  button values in the form of booleans to decide which way to move
		 * The code below will stop the motors from doing anything if BOTH 
		 * buttons are pressed, cuz who knows what would happen if both were pressed
		 * at the same time...?? I have no clue. don't be a buttonmasher!*/
		
//		double reachSDOWN = gamePad.yButton();
		double reachSUP = -gamePad.rightStickY();
		
/*		if(reachSUP == true && reachSDOWN == true){
			ShoulderE.set(0);
			return;
		}
		
		if(pShoulderE == MAX_S || shoulderEFWD == true){
			ShoulderE.set(0);
			return;
		}
		
		if(pShoulderE == MIN_S || shoulderEREV == true){
			ShoulderE.set(0);
			return;
		}
		
		if(reachSUP == true){
			ShoulderE.set(shoulderSpeed);
		}
		else if(reachSDOWN == true){
					ShoulderE.set(-shoulderSpeed);
			}
		else ShoulderE.set(0); */
		
		ShoulderE.set(reachSUP);
	}
		


	public void elbowJoint(){
		
		double reachEUP = gamePad.leftStickY();
	/*	boolean reachEDOWN = gamePad.rStickPressed();
		
		if(reachEUP == true && reachEDOWN == true){
			Elbow.set(0);
			return;
		}
		
		if(pElbow == MAX_S || elbowFWD == true){
			Elbow.set(0);
			return;
		}
		
		if(pElbow == MIN_S || elbowREV == true){
			Elbow.set(0);
			return;
		}
		
		if(reachEUP == true){
			Elbow.set(elbowSpeed);
		}
		else if(reachEDOWN == true){
					Elbow.set(-elbowSpeed);
			}
		else Elbow.set(0);*/
		
		Elbow.set(reachEUP);
		
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
			
			break;
		case 2: //Set up position
			if(pShoulderE < SHOULDER_UP_PRESET){
				ShoulderE.set(shoulderSpeed);
			}
			if(pShoulderE > SHOULDER_UP_PRESET){
				ShoulderE.set(-shoulderSpeed);
			}
			else ShoulderE.set(0);
			
			if(pElbow < ELBOW_UP_PRESET){
				Elbow.set(elbowSpeed);
			}
			if(pElbow > ELBOW_UP_PRESET){
				Elbow.set(-elbowSpeed);
			}
			else Elbow.set(0);
			
			break;
		case 3: //Set down position
			
			if(pShoulderE < SHOULDER_DOWN_PRESET){
				ShoulderE.set(shoulderSpeed);
			}
			if(pShoulderE > SHOULDER_DOWN_PRESET){
				ShoulderE.set(-shoulderSpeed);
			}
			else ShoulderE.set(0);
			
			if(pElbow < ELBOW_DOWN_PRESET){
				Elbow.set(elbowSpeed);
			}
			if(pElbow > ELBOW_DOWN_PRESET){
				Elbow.set(-elbowSpeed);
			}
			else Elbow.set(0);

			
			break;
		}
		
		/*
		if(upPosButton == true && pShoulderE < SupPosition){
			ShoulderE.set(elbowSpeed);
		}
		else{
			if(drivePosButton == true && pShoulderE > SdrivePosition){
				ShoulderE.set(-elbowSpeed);
			}
			else{
				ShoulderE.set(0);
			}
		}
		
		if(upPosButton == true && pElbow < EupPosition){
			Elbow.set(elbowSpeed);
		}
		else{
			if(drivePosButton == true && pElbow > EdrivePosition){
				Elbow.set(-elbowSpeed);
			}
			else{
				Elbow.set(0);
				return;
			}
		}*/
	}
}





/*  
 *  
 *  __-  
 * |\  _~`|                                                   
 * | \  -~`|                                                              
 * |  \  _~`|                                                            
 * |   \  -~`|                                                              
 *  \   \  -~`|                                                               
 *   \   \  _~`|                                                             
 *    \   \  _~`|                                                              
 *     \   \  -~`|                                                           
 *      \   \  -~`|`                                                               
 *       \   \  -~`|                                                              
 *        \   \  _~`|                                                               
 *         \   \  -~`|                                                                 
 *          \   \  _~`|                                                            
 *           \   \  _~`|                                                              
 *            \   \  -~`|                                                                
 *             \   \  _~``|  |  |  |  |  |  |  |  |  |  |  |  |  |  |  ||                                                         
 *              \   \  -~ ~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`\                                                     
 *               \   \  _ -- _ - - _ _ - _ __ -- _ - - _ _ - _ __ -- _ - - \
 *                \   \_____________________________________________________\
 *                 \  |                                                     |
 *                  \ |                                                     |
 *                   \|_____________________________________________________|
 */

