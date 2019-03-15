/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.DriveCommand;


public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem here
  

  @Override
  public void initDefaultCommand() {
// This sets the default command.
    setDefaultCommand(new DriveCommand());

   
  }
  public void TankDrive(double SpeedL, double SpeedR, double constant){
    //This is the code that actually makes the robot drive! It tells the 
    //proper motor controllers to set the motors to desired speed.
    if(Robot.climbMode = true)
      {
      constant = .75;
      }
      else
      {
      constant = .65;
      }
      
    Robot.robotmap.MotorL1Control.set(-SpeedL * constant);
		Robot.robotmap.MotorL2Control.set(-SpeedL * constant);
		Robot.robotmap.MotorR1Control.set(SpeedR * constant);
    Robot.robotmap.MotorR2Control.set(SpeedR * constant);

    //System.out.println(Robot.robotmap.encoderL1.getVelocity());

    
    
  }

  public void getEncoderRot() {


  }

  public double getEncoderVel(String side) {

    //get the average of the two encoders on each side for a more accurate value
    double rightEncVel = (Robot.robotmap.encoderR1.getVelocity() + Robot.robotmap.encoderR2.getVelocity()) / 2;
    double leftEncVel = (Robot.robotmap.encoderL1.getVelocity() + Robot.robotmap.encoderL2.getVelocity()) / 2;
    //if the "side" variable delivered equals right, return the right encoder velocity value
    if (side.equals("right")) {
      return rightEncVel;
    //if the "side" variable delivered equals left, return the left encoder velocity value
    } else if (side.equals("left")) {
      return leftEncVel;
      //if the "side" variable delivered does not equal any of the needed values, 
    } else {
      return 1234;
    }
  }

  public void ArcadeDrive(double DrivForward, double DriveTurn)
  {

    double LeftSide;
    double RightSide;

    if(DrivForward > 0)
    {
      LeftSide = DrivForward + DriveTurn;
      RightSide = DrivForward - DriveTurn;
    }
    else
    {
      LeftSide = DrivForward - DriveTurn;
      RightSide = DrivForward + DriveTurn;
    }

    Robot.robotmap.MotorL1Control.set(-LeftSide * 0.65);
		Robot.robotmap.MotorL2Control.set(-LeftSide * 0.65);
		Robot.robotmap.MotorR1Control.set(RightSide * 0.65);
    Robot.robotmap.MotorR2Control.set(RightSide * 0.65);

  }

 //This bit of code is trying to make it so that the acceleration is smooth WARNING:
  //IT DOES NOT WORK YET.
  public double[] AccelSmoothing(Double JoySpeedL, Double JoySpeedR)
  {

    //The time it takes to get to target speed
    int x = 370;

    //The target speeds, which are the top speeds in the equation
    double TargetL = JoySpeedL;
    double TargetR = JoySpeedR;

    // this is the formula: f(x) = L/1 + e^-1*k*(x-x0)  , k = 0.04 
   double exponent = 1 + Math.exp( -1 * 0.04 * x);

    //This is the the result of the formula
    double TargetCL = TargetL/exponent;
    double TargetCR = TargetR/exponent;

    double[] arraySpeeds = {TargetCL, TargetCR};

    return arraySpeeds;

  }

}
