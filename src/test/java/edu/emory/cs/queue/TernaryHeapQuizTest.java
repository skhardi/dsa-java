package edu.emory.cs.queue;

import org.junit.Test;
import java.util.List;
import java.util.Comparator;

public class TernaryHeapQuizTest extends PQTest {
    @Test
    public void testRobustness() {
        Comparator<Integer> natural = Comparator.naturalOrder();
        Comparator<Integer> reverse = Comparator.reverseOrder();

        List<Integer> keys = List.of(4, 1, 3, 2, 5, 6, 8, 3, 4, 7, 5, 9, 7);

        testRobustnessAux(new TernaryHeapQuiz<>(), keys, reverse);
        testRobustnessAux(new TernaryHeapQuiz<>(reverse), keys, natural);
    }

    @Test
    public void testRuntime() {
        testRuntimeAux(new BinaryHeap<>(), new TernaryHeapQuiz<>());
    }
}
