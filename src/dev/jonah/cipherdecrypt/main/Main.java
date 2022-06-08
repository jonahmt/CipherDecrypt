package dev.jonah.cipherdecrypt.main;

import dev.jonah.cipherdecrypt.fitness.FrequencyExpectation;
import dev.jonah.cipherdecrypt.fitness.Scorer;

/** Contains the main method. Handles input.
 *
 * @author Jonah Tharakan
 */
public class Main {

    public static void main(String[] args) {

        FrequencyExpectation baseline = new FrequencyExpectation();

        String realMessage = "HELLOMYNAMEISJONAHHOWAREYOUDOINGTODAYITHINKITSAGREATTIMETO" +
                "GOTOTHEBEACHANDPLAYCLASHOFCLANSITCOULDALSOBEFUNTOGOINTOTHEMOUNTAINSANDSEETHE" +
                "CASTLEMAYBEWECOULDCLIMBONTHEROCKSAFTERWARDSANDTHENGOANDGRABSOMETHINGTOEATDOWN" +
                "BYTHEHARBORAFTERTHATWECANGOTOTHEAMUESMENTPARKITWILLBEAFUNTIME";

        String fakeMessage = "HHFGWSHOHSFOPHIOHSFPOIHSFPOIHSPOFIHFHHAOIHJAOISJFOHWFOIHS" +
                "SNFZMMXHAHQOOIIEHFDFOAOOONSFBGGETWSKNAPPDLALMWNASERTYYFJQNDJJDJANBDLAKDOPDJFNF" +
                "JFJDJQKDNNNCKFHYYYTPRPPKLLAMENEEEEITTITITIFDSSDFFONTIOTNTISDFJSOJFJWIJJIIJNFIOFNHA" +
                "JFJJWNKJWNSKMSOKMXCFPOOIWRUIZNDHGIEOJFNTUSIOLCNFHDIROTNNQKSOPLEEURNDKLJNJRJ";

        Scorer scorer = new Scorer(baseline);

        System.out.println(scorer.score(fakeMessage));
        System.out.println(scorer.score(realMessage));

    }

}
