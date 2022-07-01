package za.co.wethinkcode.robotWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PitTest {
    @Test
    void PitBlocksPosition(){
        Pit pit = new Pit(0, 0);
        Position position = new Position(4, 4);
        assertTrue(pit.blocksPosition(position));
    }

    @Test
    void PitBlocksPath(){
        Pit pit = new Pit(1, 1);
        Position a = new Position(2, 0);
        Position b = new Position(2, 10);
        assertTrue(pit.blocksPath(a, b));
    }

    @Test
    void PitSizeTest(){
        Pit pit = new Pit(1, 1);
        assertEquals(5, pit.getSize());
    }
}
