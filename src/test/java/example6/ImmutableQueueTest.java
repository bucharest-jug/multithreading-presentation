package example6;

import common.Optional;
import common.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ImmutableQueueTest {
    @Test
    public void simple() {
        ImmutableQueue<Integer> queue = new ImmutableQueue<>();

        for (int i=0; i<100; i++)
            queue = queue.enqueue(i);

        assertEquals(100, queue.size());

        for (Integer i=0; i<100; i++) {
            Pair<Optional<Integer>, ImmutableQueue<Integer>> pair = queue.dequeue();
            queue = pair.collection();

            assertTrue(pair.value().isPresent());
            assertEquals(i, pair.value().get());
        }
    }
}
