package agent;

import components.agent.Agent;
import components.agent.Bear;
import components.gear.Axe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgentTest {

    @Test
    public void TestGetAndSetDuration(){
        Agent agent = new Bear(10);

        assertEquals(10, agent.getDuration());

        agent.setDuration(20);
        assertEquals(20, agent.getDuration());
    }

    @Test
    public void TestStep(){
        Agent agent = new Bear(10);
        agent.step();

        assertEquals(9, agent.getDuration());
    }
}
