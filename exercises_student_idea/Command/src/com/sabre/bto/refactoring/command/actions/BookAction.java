package com.sabre.bto.refactoring.command.actions;

import com.sabre.bto.refactoring.command.HandlerResponse;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Kudo
 * Date: 16.05.13
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public interface BookAction {
    HandlerResponse execute(Map<String, String> parameters);
}
