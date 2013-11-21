package example5;

import java.util.ArrayList;

import common.IStack;
import common.Optional;
import common.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IStackTest {
    @Test
    public void simple() {
        IStack<Integer> stack = ImmutableStack.empty();

        for (int i=0; i<10; i++)
            stack = stack.push(i);

        IStack<Integer> reversed = stack.reversed();
        ArrayList<Integer> extracted = new ArrayList<>();

        while (true) {
            Pair<Optional<Integer>, IStack<Integer>> pair = reversed.pop();
            if (pair.value().isAbsent())
                break;

            reversed = pair.collection();
            extracted.add(pair.value().get());
        }

        Assert.assertEquals(10, extracted.size());
        for (Integer i=0; i<10; i++)
            Assert.assertEquals(i, extracted.get(i));
    }
}
