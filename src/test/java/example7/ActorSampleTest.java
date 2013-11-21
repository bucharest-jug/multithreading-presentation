package example7;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ActorSampleTest {
    @Test
    public void simple() throws ExecutionException, InterruptedException {
        ActorSample actor = new ActorSample();

        ArrayList<Future<Object>> results = new ArrayList<>();

        for (int i=0; i<100; i++)
            results.add(actor.send("increment"));

        for (Integer i=0; i<100; i++)
            assertEquals(i  + 1, results.get(i).get());

        try {
            Future<Object> r = actor.send("unknown");
            r.get();
            fail("Should throw exception");
        }
        catch (UnsupportedOperationException ignored) {}

        actor.shutdown();
    }
}
