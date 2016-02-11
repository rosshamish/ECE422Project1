public class Main {
    public static void main(String[] args) {
        DataGenerator dg = new DataGenerator();
        dg.generateData();

        DataSorter dataSorter = new DataSorterPrimary();
        try {
            dataSorter.sortData();
        } catch (MemoryFailureException e) {
            e.printStackTrace(); // TODO remove
            // TODO print error message?
            dataSorter = new DataSorterBackup();
            try {
                dataSorter.sortData();
            } catch (MemoryFailureException e1) {
                e1.printStackTrace(); // TODO remove
                // TODO do something?
            }
        }
    }
}