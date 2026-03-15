package it.unical.inf.ea.lombok.ex6;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RectangleBox extends Rectangle {
  private double height;

}