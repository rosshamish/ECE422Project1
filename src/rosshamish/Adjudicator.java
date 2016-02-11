package rosshamish;

import rosshamish.exceptions.IllegalIntegersFileException;
import rosshamish.primary.DataReader;

import java.io.IOException;
import java.util.List;

public class Adjudicator {
    private String message = "";

    public Boolean passesAcceptanceTest(String sortedFilename) throws IOException {
        List<Integer> integers = null;
        try {
            integers = DataReader.read(sortedFilename);
        } catch (IllegalIntegersFileException e) {
            message = "Illegal sorted file, does not follow format";
            return Boolean.FALSE;
        }

        Integer latest = integers.get(0);
        for (Integer i: integers) {
            if (latest > i) {
                message = "Values are not in sorted order";
                return Boolean.FALSE;
            } else {
                latest = i;
            }
        }
        return Boolean.TRUE;
    }

    public String getMessage() {
        return message;
    }
}
