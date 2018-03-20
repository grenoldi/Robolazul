package com.example.ncolas.activitystarter;

/**
 * Created by Faculdade on 18/09/2017.
 */

public class Strategy
{
    private int strategy_id;
    private String strategy_name;
    private String strategy_character;
    private String robot_name;


    public Strategy(int _strategy_id, String _strategy_name, String _strategy_character, String _robotName)
    {
        this.strategy_id = _strategy_id;
        this.strategy_name = _strategy_name;
        this.strategy_character = _strategy_character;
        this.robot_name = _robotName;
    }

    public void setStrategyName(String strategy_name)
    {
        this.strategy_name = strategy_name;
    }

    public void setStrategyCharacter(String strategy_character)
    {
        this.strategy_character = strategy_character;
    }

    public String getStrategyName()
    {
        return strategy_name;
    }

    public String getStrategyCharacter()
    {
        return strategy_character;
    }

    public void setStrategyID(int strategy_id)
    {
        this.strategy_id = strategy_id;
    }

    public Integer getStrategyID()
    {
        return strategy_id;
    }

    public String getRobot_name() {
        return robot_name;
    }

    public void setRobot_name(String robot_name) {
        this.robot_name = robot_name;
    }
}
