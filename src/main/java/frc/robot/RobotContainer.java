// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.LoadAndShootCommand;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

  CommandXboxController joystick = new CommandXboxController(0);

  String shooterKey = "Shooter Speed";
  String loaderKey = "Loader Speed";
  String hopperKey = "Agitation Speed";
  String intakeKey = "Intake Speed";
  String p = "p";
  String i = "i";
  String d = "d";

  Shooter shooter = new Shooter();
  Loader loader = new Loader();
  Hopper hopper = new Hopper();
  Intake intake = new Intake();
  
  public RobotContainer() {
    configureBindings();
    setDefaultCommands();
    setUpElastic();
  }

  private void setDefaultCommands() {
    hopper.setDefaultCommand(
      Commands.run(hopper.agitate(0), hopper)
    );

    intake.setDefaultCommand(
      Commands.run(intake.intake(0), intake)
    );

    loader.setDefaultCommand(
      Commands.run(loader.load(0), loader)
    );

    shooter.setDefaultCommand(
      Commands.run(shooter.rev(0), shooter)
    );
  }

  private void configureBindings() {
    joystick.b().whileTrue(
      new InstantCommand(() -> {})
    // new LoadAndShootCommand(
      //   loader, 
      //   shooter, 
      //   () -> joystick.getHID().getXButtonPressed(), 
      //   () -> SmartDashboard.getNumber(shooterKey, 0.0), 
      //   () -> SmartDashboard.getNumber(loaderKey, 0.0)
      // )
    );

    joystick.a().whileTrue(
      Commands.run(
        shooter.rev(
          () -> SmartDashboard.getNumber(shooterKey, 0)
        ),
        shooter
      )
      // new LoadAndShootCommand(
      //   loader, 
      //   shooter, 
      //   () -> joystick.getHID().getXButtonPressed(), 
      //   () -> SmartDashboard.getNumber(shooterKey, 0.0), 
      //   () -> SmartDashboard.getNumber(loaderKey, 0.0)
      // ).repeatedly()
    );

    joystick.x().whileTrue(
      Commands.run(
        loader.load(
          () -> SmartDashboard.getNumber(loaderKey, 0)
        ), 
        loader)
    );

    joystick.leftBumper().whileTrue(
      Commands.run(
        hopper.agitate()
      ).repeatedly()
    );

    joystick.leftTrigger().whileTrue(
      Commands.run(
        intake.intake(
          () -> SmartDashboard.getNumber(intakeKey, 0.1)
        )
      ).repeatedly()
    );  
  }

  private void setUpElastic() {
    SmartDashboard.setDefaultNumber(shooterKey, 30);
    SmartDashboard.setDefaultNumber(loaderKey, 30);
    SmartDashboard.setDefaultString(hopperKey, "Already Configured");
    SmartDashboard.setDefaultNumber(intakeKey, 0.32);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
