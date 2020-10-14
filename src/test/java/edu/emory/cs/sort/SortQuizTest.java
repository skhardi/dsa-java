/*
 * Copyright 2020 Emory University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.cs.sort;

import edu.emory.cs.sort.comparison.ShellSortKnuth;
import edu.emory.cs.sort.comparison.ShellSortQuiz;
import edu.emory.cs.sort.distribution.LSDRadixSort;
import edu.emory.cs.sort.distribution.RadixSortQuiz;

/** @author Jinho D. Choi */
public class SortQuizTest extends SortTest {
    public void testRobustness() {
        testRobustness(new ShellSortQuiz<>());
        testRobustness(new RadixSortQuiz());
    }

    public void testRuntime() {
//        System.out.println("Knuth vs. Hibbard Shell Sort (Runtimes, Comparisons, Assignments)");
//        System.out.println("Input Ascending Sort");
//        testRuntime(InputNature.ASCENDING, new ShellSortKnuth<>(), new ShellSortQuiz<>());
//        System.out.println("Input Random Sort");
//        testRuntime(InputNature.RANDOM, new ShellSortKnuth<>(), new ShellSortQuiz<>());
//        System.out.println("Input Descending Sort");
//        testRuntime(InputNature.DESCENDING, new ShellSortKnuth<>(), new ShellSortQuiz<>());

        System.out.println("LSD vs. MSD Radix Sort (Runtimes)");
        testRuntime(InputNature.RANDOM, new LSDRadixSort(), new RadixSortQuiz());
    }
}
