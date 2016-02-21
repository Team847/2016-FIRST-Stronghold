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
	
	public ObstacleArmElbow(GamePad xbox){
		
		gamePad = xbox;
		
		ShoulderE.set(0);
		Elbow.set(0);
		
		shoulderEFWD = ShoulderE.isFwdLimitSwitchClosed();
		shoulderEREV = ShoulderE.isRevLimitSwitchClosed();
		
		elbowFWD = Elbow.isFwdLimitSwitchClosed();
		elbowREV = Elbow.isRevLimitSwitchClosed();
		
		pShoulderE = ShoulderE.getAnalogInRaw();
		pElbow = Elbow.getAnalogInRaw();
	}
	
	public void armTest(){

		ShoulderE.set(0);
		Elbow.set(0);
		
		double sPot = pShoulderE;
		double ePot = pElbow;

		System.out.println(sPot);
		System.out.println(ePot);
	}
	
	public void shoulderJoint(){
				
		double reach_S = -gamePad.rightStickY();
		
		if(pShoulderE >= MAX_S && reach_S > 0){
			ShoulderE.set(0);
		}
		else if(pShoulderE <= MIN_S && reach_S < 0){
			ShoulderE.set(0);
		}
		else ShoulderE.set(reach_S);
		
		return;
		}
		
	public void elbowJoint(){
		
		double reach_E = gamePad.leftStickY();
		
		if(pElbow >= MAX_S && reach_E > 0){
			Elbow.set(0);
		}
		else if(pElbow <= MIN_S && reach_E < 0){
				Elbow.set(0);
		}
		else Elbow.set(reach_E);
		
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

