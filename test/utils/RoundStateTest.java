package utils;

import components.agent.Bear;
import components.utils.RoundState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoundStateTest {

    @Test
    public void TestDefaultState(){
        RoundState roundState = new RoundState();
        assertEquals(false, roundState.getCraft());
        assertEquals(false, roundState.getMove());
        assertEquals(false, roundState.getRob());
        assertEquals(false, roundState.getTouch());
        assertEquals(false, roundState.getUse());
    }

    @Test
    public void TestSetCraft(){
        RoundState roundState = new RoundState();
        boolean reValue = roundState.setCraft();
        assertEquals(false, reValue);
        assertEquals(true, roundState.getCraft());
    }

    @Test
    public void TestSetMove(){
        RoundState roundState = new RoundState();
        boolean reValue = roundState.setMove();
        assertEquals(false, reValue);
        assertEquals(true, roundState.getMove());
    }

    @Test
    public void TestSetTouch(){
        RoundState roundState = new RoundState();
        boolean reValue = roundState.setTouch();
        assertEquals(false, reValue);
        assertEquals(true, roundState.getTouch());
    }

    @Test
    public void TestSetUse(){
        RoundState roundState = new RoundState();
        boolean reValue = roundState.setUse();
        assertEquals(false, reValue);
        assertEquals(true, roundState.getUse());
    }

    @Test
    public void TestSetRob(){
        RoundState roundState = new RoundState();
        boolean reValue = roundState.setRob();
        assertEquals(false, reValue);
        assertEquals(true, roundState.getRob());
    }

}
