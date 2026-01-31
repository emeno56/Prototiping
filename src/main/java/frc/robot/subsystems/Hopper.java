package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.hardware.KrakenBuilder;

import static frc.robot.constants.SubsystemConstants.HopperConstants.*;
import static frc.robot.constants.SubsystemConstants.*;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
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
      .withSlot0PID(0.4,0,0.00000000001)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();

    upperMotor = KrakenBuilder.create(HOPPER_UPPER_MOTOR_ID, CAN_BUS)
      .withCurrentLimit(80)
      .withIdleMode(NeutralModeValue.Coast)
      .withSlot0PID(0.4, 0, 0.00000000001)
      .withInversion(InvertedValue.Clockwise_Positive)
      .build();
  }

  public Runnable agitate(double agitateSpeed) {
    return () -> { 
      lowerMotor.set(agitateSpeed);
      upperMotor.set(agitateSpeed);
    };
  }

  public Runnable agitate(DoubleSupplier speed) {
    return () -> {
      lowerMotor.setControl(new VelocityVoltage(speed.getAsDouble()).withEnableFOC(true));
      upperMotor.setControl(new VelocityVoltage(speed.getAsDouble()).withEnableFOC(true));
    };
  }

  @Override
  public void periodic() {}
}
