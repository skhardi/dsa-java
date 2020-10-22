package edu.emory.cs.trie;

import java.util.List;
import java.util.ArrayList;

public class TrieQuiz extends Trie<Integer> {

    /**
     * getEntities() finds the country entities that pre-exist in this trie in a given string
     * @param input input string to search for country entities
     * @return list of entities (each country's beginning index (inclusive), ending index (exclusive) and country ID (or
     * value in trie)
     */
    List<Entity> getEntities(String input) {
        List<Entity> entities = new ArrayList<>();

        int beginIndex, endIndex;
        char[] array = input.toCharArray();
        String s = "";

        for (int i = 0; i < input.length(); i++) {

            s += array[i];

            for (int j = i + 1; j < input.length(); j++) {

                s += array[j];

                if (find(s) != null) {

                    beginIndex = input.indexOf(s);

                    if (contains(s)) {

                        Integer countryID = get(s);
                        endIndex = beginIndex + s.length();
                        entities.add(new Entity(beginIndex, endIndex, countryID));

                    }

                }

                else {

                    s = "";
                    break;

                }

            }

        }

        return entities;

    }
}
