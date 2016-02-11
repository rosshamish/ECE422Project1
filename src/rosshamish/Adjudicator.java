package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.primary.DataReader;

import java.util.List;

public class Adjudicator {
    public static Boolean passesAcceptanceTest(String sortedFilename) {
        List<Integer> integers = null;
        try {
            integers = DataReader.read(sortedFilename);
        } catch (IllegalIntegersFileException e) {
            return Boolean.FALSE;
        }

        Integer latest = integers.get(0);
        for (Integer i: integers) {
            if (latest > i) {
                return Boolean.FALSE;
            } else {
                latest = i;
            }
        }
        return Boolean.TRUE;
    }
}
