package org.usfirst.frc.team847.robot;

//import edu.wpi.first.wpilibj.*;

public class ObstacleArm{
	
//	CANTalon Shoulder = new Shoulder(CANTALON_SHOULDER);
	boolean shoulderFWD;
	boolean shoulderREV;
	boolean buttonMash;
	double pShoulder;
	
	public ObstacleArm(){
		
//		Shoulder.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogPot);
//		pShoulder = Shoulder.getAnalogInPosition();
	}
	
	public void elbowJoint(boolean reachUP, boolean reachDOWN, double minE, double maxE){
		
	}
}