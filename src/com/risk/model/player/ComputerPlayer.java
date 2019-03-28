/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.risk.model.player;

/**
 * Super Class for all Computer Players ( A Player that requires zero user
 * interaction to make decisions.)
 *
 * @author Natheepan
 */
public abstract class ComputerPlayer extends Player {

    public ComputerPlayer(String name)
    {
        super(name);
        setIsComputerPlayer(true);
    }

}
