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
package edu.emory.cs.trie;

import org.junit.Test;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;

/** @author Jinho D. Choi */
public class TrieQuizTest {
    @Test
    public void test1GetEntities() {
        final List<String> L = List.of("United States", "South Korea");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "I was born in South Korea and raised in the United States";
        List<Entity> entities = List.of(new Entity(44, 57, 0), new Entity(14, 25, 1));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    @Test
    public void test2GetEntities() {
        final List<String> L = List.of("China", "Chile", "Colombia", "Comoros", "Congo", "Costa Rica");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "I visited Colombia and  Chile, and next I want to visit Comoros and the Congo.";
        List<Entity> entities = List.of(new Entity(input.indexOf("Chile"), input.indexOf("Chile") + "Chile".length(), 1),
                new Entity(input.indexOf("Colombia"), input.indexOf("Colombia") + "Colombia".length(), 2),
                new Entity(input.indexOf("Comoros"), input.indexOf("Comoros") + "Comoros".length(), 3),
                new Entity(input.indexOf("Congo"), input.indexOf("Congo") + "Congo".length(), 4));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    @Test
    public void test3GetEntities() {
        final List<String> L = List.of("United States", "South Korea", "America");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "I was born in South Korea and raised in the United States of America";
        List<Entity> entities = List.of(new Entity(44, 57, 0), new Entity(14, 25, 1), new Entity(61, 68, 2));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    @Test
    public void test4GetEntities() {
        final List<String> L = List.of("Czech Republic", "Republic of Korea", "North Korea", "South Korea", "Korea", "Democratic Republic of Congo");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "I would guess South Korea, or the Republic of Korea, is closer to the Democratic Republic of Congo than to the Czech Republic. Of course, North Korea is closest.";
        List<Entity> entities = List.of(new Entity(111, 125, 0), new Entity(34,51,1), new Entity(138, 149, 2), new Entity(14, 25, 3), new Entity(20, 25, 4), new Entity(70,98, 5));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    @Test
    public void test5GetEntities() {
        final List<String> L = List.of("America", "Australia");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = "Are you American or Australian?";
        List<Entity> entities = List.of(new Entity(8,15,0), new Entity(20, 29,1));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

    @Test
    public void test6GetEntities() {
        final List<String> L = List.of("canada", "mexico");
        TrieQuiz trie = new TrieQuiz();
        for (int i = 0; i < L.size(); i++)
            trie.put(L.get(i), i);
        String input = " ad?baskjcanadaasd.almexicoqdas";
        List<Entity> entities = List.of(new Entity(9,15,0), new Entity(21,27, 1));
        Set<String> expected = entities.stream().map(Entity::toString).collect(Collectors.toSet());
        Set<String> actual = trie.getEntities(input).stream().map(Entity::toString).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }
}
