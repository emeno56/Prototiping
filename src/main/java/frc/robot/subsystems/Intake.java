package frc.robot.subsystems;

import static frc.robot.constants.SubsystemConstants.IntakeConstants.INTAKE_MOTOR_ID;
import static frc.robot.constants.SubsystemConstants.CAN_BUS;
import static frc.robot.constants.SubsystemConstants.HopperConstants.*;


import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.KrakenBuilder;

public class Intake extends SubsystemBase {
  TalonFX motor;
  public Intake() {
    motor = KrakenBuilder.create(INTAKE_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withInversion(InvertedValue.CounterClockwise_Positive)
      .build();
  }

public Runnable intake(double speed) {
  return () -> motor.set(speed);//new DutyCycleOut(speed).withEnableFOC(true);
} 

public Runnable intake(DoubleSupplier speed) {
  return () -> motor.set(speed.getAsDouble());//new DutyCycleOut(speed).withEnableFOC(true);
} 

  @Override
  public void periodic() {}
}
