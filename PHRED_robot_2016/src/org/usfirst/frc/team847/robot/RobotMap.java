package org.usfirst.frc.team847.robot;


import edu.wpi.first.wpilibj.*;

public interface RobotMap {

//==========================DRIVE TRAIN======================//	
	
	public final static double FRAME_LENGTH = 23;
	public final static double FRAME_WIDTH  = 23;
	
	
	 
	//==================sensors=============================\\
	

	
	
	
	
	
	
	//============================Ball shooter===========//
	 
	 public final static int SHOOT = 1;
	 public final static int INTAKE = 3;
	 public final static int EXPELL = 2 ;
     public final static double TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE = -1.0;	  // shooting routine for top motor
     public final static double BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE = 1.0; //shooting routine for bottom motor
	    
	    
	//=================================ARM=================================//
	
	public final static boolean ELBOW_REV = false;
	public final static boolean ELBOW_FWD = false;
	public final static boolean SHOULDER_FWD = false;
	public final static boolean SHOULDER_REV = false;
	public final static boolean BUTTON_MASH = false;
	public final static double P_READER = 0; 
	
	//=======================Robot.java================================//
	
	public final static int OBJ_MANIP_GAMEPAD = 2;
	public final static int DRIVE_GAMEPAD = 1;
}


