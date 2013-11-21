package example3;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class NonSafeStackTest {
    @Test
    public void simple() {
        NonSafeStack<Integer> stack = new NonSafeStack<>();

        for (int i=0; i<10; i++)
            stack.push(i);

        stack.reverse();

        ArrayList<Integer> extracted = new ArrayList<>();
        while (stack.size() > 0)
            extracted.add(stack.pop());

        Assert.assertEquals(10, extracted.size());
        for (Integer i=0; i<10; i++)
            Assert.assertEquals(i, extracted.get(i));
    }
}
