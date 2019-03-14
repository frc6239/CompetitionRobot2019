/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ReleaseClimberArmsCommand extends Command {

  public ReleaseClimberArmsCommand() {
    requires(Robot.climbersub);
  }

  @Override
  protected void initialize() {
    Robot.climbersub.ReleaseClimberArms();
    System.out.println("Releasing Arms");

  }

  @Override
  protected void execute() {
    //Robot.climbersub.ReleaseClimberArms();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.climbersub.ResetServo();
  }

  @Override
  protected void interrupted() {
    end();
  }
}