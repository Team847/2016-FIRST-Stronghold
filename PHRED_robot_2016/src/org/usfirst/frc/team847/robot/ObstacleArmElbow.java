package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class ObstacleArmElbow implements RobotMap{

	GamePad gamePad;
	
	CANTalon Elbow = new CANTalon(CANTALON_ELBOW);
	CANTalon ShoulderE = new CANTalon(CANTALON_SHOULDER);
	
	double pShoulderE;
	double pElbow;
	double elbowCheckOnce = Elbow.getAnalogInPosition();
	double shoulderCheckOnce = ShoulderE.getAnalogInPosition();
	
	boolean shoulderEFWD;
	boolean shoulderEREV;
	boolean elbowFWD;
	boolean elbowREV;
	boolean shoulderMash;
	boolean elbowMash;
	
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

		ShoulderE.set(0);
		Elbow.set(0);
		
		double sPot = ShoulderE.getAnalogInPosition();
		double ePot = Elbow.getAnalogInPosition();
		
		System.out.println("Shoulder");
		System.out.println(sPot);
		System.out.println("Elbow");
		System.out.println(ePot);
		
		return;
	}
	
	public void shoulderJoint(){
				
		double reach_S = -gamePad.rightStickY();
		double sPot = ShoulderE.getAnalogInPosition(); 
		
		if(sPot >= MAX_S && reach_S > 0){
			ShoulderE.set(0);
		}
		else if(sPot <= MIN_S && reach_S < 0){
			ShoulderE.set(0);
		}
		else ShoulderE.set(reach_S);
		
		return;
		}
		
	public void elbowJoint(){
		
		double reach_E = gamePad.leftStickY();
		double ePot = Elbow.getAnalogInPosition();
		
		if(ePot <= MAX_E && reach_E < 0){
			
			Elbow.set(0);
		}
		else if(ePot >= MIN_E && reach_E > 0){
				Elbow.set(0);
		}
		else Elbow.set(reach_E);
		
		return;
		}
	
	public void stallOut(){
		
		double sPot = ShoulderE.getAnalogInPosition();
		double ePot = Elbow.getAnalogInPosition();
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
	
 	public boolean MaxReachCheck(){
		
		double alpha = Elbow.getAnalogInPosition();		// |some math to convert to degrees|
		double beta = ShoulderE.getAnalogInPosition();	// |some math to convert to degrees|
		
		double angleOne = 90 - beta;
		double angleTwo = alpha - angleOne;
		double lengthOne = ARM_BICEP*Math.sin(angleOne);
		double lengthTwo = ARM_TRIICEP*Math.sin(angleTwo);
		boolean is15broke = false;
		
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
			
			if(pShoulderE < SHOULDER_STEP_ONE){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(pElbow < ELBOW_STEP_ONE){
				Elbow.set(SHOULDER_SPEED);
			}
			if(pShoulderE > SHOULDER_STEP_TWO){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			if(pElbow > ELBOW_STEP_TWO){
				Elbow.set(-SHOULDER_SPEED);
			}
			
			else{
				ShoulderE.set(0);
				Elbow.set(0);
			}
			
			break;
		case 2: //Set up position
			if(pShoulderE < SHOULDER_UP_PRESET){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(pShoulderE > SHOULDER_UP_PRESET){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			else ShoulderE.set(0);
			
			if(pElbow < ELBOW_UP_PRESET){
				Elbow.set(ELBOW_SPEED);
			}
			if(pElbow > ELBOW_UP_PRESET){
				Elbow.set(-ELBOW_SPEED);
			}
			else Elbow.set(0);
			
			break;
			
		case 3: //Set down position
			
			if(pShoulderE < SHOULDER_DOWN_PRESET){
				ShoulderE.set(SHOULDER_SPEED);
			}
			if(pShoulderE > SHOULDER_DOWN_PRESET){
				ShoulderE.set(-SHOULDER_SPEED);
			}
			else ShoulderE.set(0);
			
			if(pElbow < ELBOW_DOWN_PRESET){
				Elbow.set(ELBOW_SPEED);
			}
			if(pElbow > ELBOW_DOWN_PRESET){
				Elbow.set(-ELBOW_SPEED);
			}
			else Elbow.set(0);
			
			break;
		}
	}
}
















/* 
 * _________________________________________________
 * |\                                               \             
 * | \                                               \             
 * |  \                                               \             
 * \   \                                               \             
 *  \   \                                               \             
 *   \   \                                               \             
 *    \   \                                               \             
 *  __ \   \                                               \             
 * |\   \   \                                               \    
 * | \   \   \                                               \               
 * |  \   \   \                                               \             
 * |   \   \   \                                               \               
 *  \   \   \   \                                               \                
 *   \   \   \   \                                               \              
 *    \   \   \   \                                               \               
 *     \   \   \   \                                               \        
 *      \   \   \   \                                               \              
 *       \   \   \   \                                               \            
 *        \   \   \   \                                               \           
 *         \   \   \ \_______________________________________________\                                                            
 *          \   \   \|                                                |         
 *           \   \   |                                                |            
 *            \   \  |________________________________________________|                                                                
 *             \   \                                                                                                             
 *              \   \___________________________________________________
 *               \  |                                                   |
 *                \ |                                                   |
 *                 \|___________________________________________________|
 */

