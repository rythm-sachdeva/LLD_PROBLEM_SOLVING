package org.example.model;

import org.example.enums.GateType;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public abstract class Gate {
    protected final String id;
    public abstract GateType getType();
}