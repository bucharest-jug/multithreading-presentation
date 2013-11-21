package example4;

import java.util.ArrayList;

import common.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SafeMutableStackTest {
    @Test
    public void simple() {
        SafeMutableStack<Integer> stack = new SafeMutableStack<>();

        for (int i=0; i<10; i++)
            stack.push(i);

        stack.reverse();

        ArrayList<Integer> extracted = new ArrayList<>();
        while (true) {
            Optional<Integer> value = stack.pop();
            if (value.isAbsent())
                break;

            extracted.add(value.get());
        }

        Assert.assertEquals(10, extracted.size());
        for (Integer i=0; i<10; i++)
            Assert.assertEquals(i, extracted.get(i));
    }
}
