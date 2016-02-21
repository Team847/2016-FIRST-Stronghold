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
				
		double reach_S = -gamePad.rightStickY();
		
		if(pShoulderE >= MAX_S /*|| shoulderEFWD == true*/ && reach_S > 0){
			ShoulderE.set(0);
			return;
		}
		else if(pShoulderE > MAX_S && reach_S < 0){
			ShoulderE.set(reach_S);
		}
		
		if(pShoulderE <= MIN_S /*|| shoulderEREV == true*/ && reach_S < 0){
			ShoulderE.set(0);
			return;
		}
		ShoulderE.set(reach_S);
		//System.out.println(pShoulderE);
		return;
		
		}
		
	public void elbowJoint(){
		
		double reach_E = gamePad.leftStickY();
		
		if(pElbow >= MAX_S /*|| elbowFWD == true*/ && reach_E > 0){
			Elbow.set(0);
			return;
		}
		if(pElbow <= MIN_S /*|| elbowREV == true*/ && reach_E < 0){
			Elbow.set(0);
			return;
		}
		Elbow.set(reach_E);
		//System.out.println( pElbow);
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
				ShoulderE.set(shoulderSpeed);
			}
			if(pElbow < ELBOW_STEP_ONE){
				Elbow.set(elbowSpeed);
			}
			if(pShoulderE > SHOULDER_STEP_TWO){
				ShoulderE.set(-shoulderSpeed);
			}
			if(pElbow > ELBOW_STEP_TWO){
				Elbow.set(-elbowSpeed);
			}
			
			else{
				ShoulderE.set(0);
				Elbow.set(0);
			}
			
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
 *         \   \   \   \_______________________________________________\                                                            
 *          \   \   \  |                                                |         
 *           \   \   \ |                                                |            
 *            \   \   \|________________________________________________|                                                                
 *             \   \                                                                                                             
 *              \   \___________________________________________________
 *               \  |                                                   |
 *                \ |                                                   |
 *                 \|___________________________________________________|
 */

