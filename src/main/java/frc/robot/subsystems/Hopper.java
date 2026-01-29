package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.KrakenBuilder;

import static frc.robot.constants.SubsystemConstants.HopperConstants.*;
import static frc.robot.constants.SubsystemConstants.*;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

//Agitaor???
public class Hopper extends SubsystemBase {

  TalonFX lowerMotor;
  TalonFX upperMotor;

  public Hopper() {
    lowerMotor = KrakenBuilder.create(HOPPER_LOWER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();

    upperMotor = KrakenBuilder.create(HOPPER_UPPER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();
  }

  public Runnable agitate(double agitateSpeed) {
    return () -> {
      lowerMotor.set(agitateSpeed);
      upperMotor.set(agitateSpeed);
    };//setControl(new DutyCycleOut(agitateSpeed).withEnableFOC(true));
  }

  public Runnable agitate(DoubleSupplier agitateSpeed) {
    return () -> {
      lowerMotor.set(agitateSpeed.getAsDouble());
      upperMotor.set(agitateSpeed.getAsDouble());
    };//setControl(new DutyCycleOut(agitateSpeed).withEnableFOC(true));
  }

  @Override
  public void periodic() {}
}
