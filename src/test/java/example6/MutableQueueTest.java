package example6;

import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MutableQueueTest {
    @Test
    public void simple() throws TimeoutException {
        MutableQueue<Integer> queue = new MutableQueue<>();

        for (int i=0; i<100; i++)
            queue.enqueue(i);

        for (Integer i=0; i<100; i++)
            Assert.assertEquals(i, queue.dequeueAwait());
    }
}
