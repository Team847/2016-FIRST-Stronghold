package org.usfirst.frc.team847.robot;
public interface RobotMap {

	//==========================DRIVE TRAIN======================//	
	
	public final static double FRAME_LENGTH = 23;
	public final static double FRAME_WIDTH  = 23;
	
	
	 
	//==================Robot.Java=============================\\
	
	public final static int DRIVE_GAMEPAD = 1;
	public final static int OBJ_MANIP_GAMEPAD = 2;
	
	//============================Ball shooter===========//
	 
	 public final static double BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE = 1.0;
	 public final static double TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE = -1.0;
	 public final static int SHOOT = 1;
	 public final static int INTAKE = 3;
	 public final static int EXPELL = 2;	    
	    
	//=================================ARM=================================//
	
	public final static int CANTALON_ELBOW = 1;
	public final static int CANTALON_SHOULDER =2;
	public final static double MIN_S = 0;
	public final static double MAX_S = 5;
	public final static double MIN_E = 0;
	public final static double MAX_E = 5;
	public final static double P_READER = 0; 
}
