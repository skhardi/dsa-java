package edu.emory.cs.trie.autocomplete;

import org.junit.Test;

import java.util.List;

public class AutocompleteHWTest {
    final String dict_file = "src/main/resources/dict.txt";
    final String myDict_file = "src/main/resources/myDict.txt";

    @Test
    public void testHW() {
        final int max = 20;
        Autocomplete<List<String>> ac = new AutocompleteHW(dict_file, max);

        String prefix = "a";
        System.out.println(ac.getCandidates(prefix));
        ac.pickCandidate(prefix, "ships");
        System.out.println(ac.getCandidates(prefix));
        ac.pickCandidate(prefix, "av");
        System.out.println(ac.getCandidates(prefix));
        ac.pickCandidate(prefix, "aa");
        System.out.println(ac.getCandidates(prefix));

        prefix = "!";
        System.out.println(ac.getCandidates(prefix));
        ac.pickCandidate(prefix, "!hi");
        System.out.println(ac.getCandidates(prefix));

        prefix = "";
        System.out.println(ac.getCandidates(prefix));

        prefix = "abas";
        System.out.println(ac.getCandidates(prefix));
        ac.pickCandidate(prefix, "abashed");
        System.out.println(ac.getCandidates(prefix));

        // dictionary file not in alphabetical order
        Autocomplete<List<String>> ac2 = new AutocompleteHW(myDict_file, 10);
        prefix = "d";
        ac2.pickCandidate(prefix, "67");
        System.out.println(ac2.getCandidates(prefix));

    }

    @Test
    public void testHWExtra() {
        final int max = 20;
        Autocomplete<List<Candidate>> ac = new AutocompleteHWExtra(dict_file, max);

        String prefix = "abas";
        List<Candidate> c = ac.getCandidates(prefix);
        System.out.println("c");
        while(!c.isEmpty())
            System.out.print(c.remove(0).word + " ");
        ac.pickCandidate(prefix, "dog");
        ac.pickCandidate(prefix, "dog");
        ac.pickCandidate(prefix, "dotted");
        ac.pickCandidate(prefix, "dog");
        ac.pickCandidate(prefix, "dotted");
        ac.pickCandidate(prefix, "dotted");
        c = ac.getCandidates(prefix);
        System.out.println("c");
        while(!c.isEmpty())
            System.out.print(c.remove(0).word + " ");
    }

}
