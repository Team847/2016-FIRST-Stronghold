package org.usfirst.frc.team847.robot;
public interface RobotMap {
	
	// === Autonomous ===//
	
	public final static String DEFAULT_AUTO = "Default";
	public final static String DRIVE_FORWARD_AUTO = "Drive Forward";
	
	
	
	//==========================DRIVE TRAIN======================//	
	
	public final static double FRAME_LENGTH = 23;
	public final static double FRAME_WIDTH  = 23;
	public final static int NINETY_CLICKS = 700;
	public final static int BACK_MOTOR = 4;
	public final static int LEFT_MOTOR = 6;
	public final static int RIGHT_MOTOR = 5;
	public final static int TURN_MOTOR = 3;
	
	// Pot positioning data for COMPETITION robot
	public final static int START_POSITION = 567;
	public final static int MIN_POSITION = 202;
	public final static int MAX_POSITION = 938;
	 
	// Pot positioning data for PRACTICE robot
	//public final static int START_POSITION = 351;
	//public final static int MIN_POSITION = 8;
	//public final static int MAX_POSITION = 826;

	//==================Robot.Java=============================\\
	
	public final static int TURN_CONTROL = 0;
	public final static int DRIVE_GAMEPAD = 1;
	public final static int OBJ_MANIP_GAMEPAD = 2;
	
	//============================Ball shooter===========//
	 
	 public final static double BOTTOM_SHOOTER_MOTOR_SHOOTING_ROUTINE = -1.0;
	 public final static double TOP_SHOOTER_MOTOR_SHOOTING_ROUTINE = 1.0;
	 public final static int SHOOT = 1;
	 public final static int EXPELL = 2;
	 public final static int INTAKE = 3;
	 
	//=================================ARM=================================//
	

	public final static int CANTALON_ELBOW = 1;
	public final static int CANTALON_SHOULDER = 2;
	public final static int CANTALON_LIGHT = 4;
	
	public final static double ELBOW_SPEED_ADJ = .8;
	public final static double SHOULDER_SPEED_ADJ = .5;

	// Pot positioning data for COMPETITION robot
	// Shoulder
	public final static double MAX_S = 3.97; // "lower limit", "flat", "flat out"
	public final static double MIN_S = 2.90; // "upright", "90 degrees", "standing tall"
	public final static double SVERTICAL =2.98;
	
	// Pot positioning data for COMPETITION robot
	// elbow: 
	public final static double MIN_E = 0.40; // verify?
	public final static double MAX_E = 4.40; // Verticle 90 deg: 2.14
	public final static double EVERTICAL = 4.40;
	public final static double EHORIZONTAL = 2.48; //1.85;
	
	// Pot positioning data for PRACTICE robot
	// Shoulder
	//public final static double MAX_S = 3.20; 
	//public final static double MIN_S = 2.24; 
	//public final static double SVERTICAL = 2.37;
	
	// Pot positioning data for Practice robot
	// Elbow
	//public final static double MIN_E = 0.14; // verify?
	//public final static double MAX_E = 3.34; // Verticle 90 deg: 2.14
	//public final static double EVERTICAL = 3.34;
	//public final static double EHORIZONTAL = 2.05; //1.85;

	public final static double MAX_HEIGHT = 54 - 9.0;
	public final static double MAX_REACH = 28.5;
	public final static double ARM_BICEP = 24.5;
	public final static double ARM_TRICEP = 26.5;
	
	public final static double SHOULDER_STALL = 0;
	public final static double ELBOW_STALL = 0;
	
	public final static double P_READER = 0;
	
	public final static double SHOULDER_STEP_ONE = 50;
	public final static double SHOULDER_STEP_TWO = 75;
	public final static double ELBOW_STEP_ONE = 50;
	public final static double ELBOW_STEP_TWO = 75;
	public final static double ELBOW_DOWN_PRESET = 0;
	public final static double SHOULDER_DOWN_PRESET = 0;
	public final static double ELBOW_UP_PRESET = 100;
	public final static double SHOULDER_UP_PRESET = 100;

	public final static int ANALOG_IN_ELBOW = 1;
	public final static int ANALOG_IN_SHOULDER = 2;
	
	public final static int PORTCULLIS_LIFT = 1;
	public final static int SET_UP = 2;
	public final static int SET_DOWN = 3;
}