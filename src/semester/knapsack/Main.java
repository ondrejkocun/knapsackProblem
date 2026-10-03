package semester.knapsack;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main {
    private static final Path WEIGHTS_FILE = Paths.get("data", "H4_a.txt");
    private static final Path VALUES_FILE = Paths.get("data", "H4_c.txt");
    private static final Path OUTPUT_DIR = Paths.get("output");
    private static final int ITEM_COUNT = 500;
    private static final int ITEM_LIMIT = 150;
    private static final int CAPACITY = 10000;

    private Main() {
    }

    public static void main(String[] args) throws Exception {
        boolean bonus = args.length > 0 && "--bonus".equalsIgnoreCase(args[0]);

        InputDataLoader loader = new InputDataLoader();
        KnapsackProblem problem = loader.load(WEIGHTS_FILE, VALUES_FILE, ITEM_COUNT, ITEM_LIMIT, CAPACITY);

        KnapsackSolver solver = new KnapsackSolver();
        SolverResult result = solver.solve(problem);

        OutputWriter outputWriter = new OutputWriter();
        Path summaryFile = OUTPUT_DIR.resolve("vystup_h11_first.txt");
        outputWriter.writeSummary(result, summaryFile);

        System.out.println(outputWriter.formatSummary(result));
        System.out.println("Textovy vystup bol ulozeny do: " + summaryFile.toAbsolutePath());

        if (bonus) {
            BonusExperimentRunner runner = new BonusExperimentRunner();
            String report = runner.run(problem);
            Path bonusFile = OUTPUT_DIR.resolve("bonus_h11_first.csv");
            outputWriter.writeBonusReport(report, bonusFile);
            System.out.println("Bonusovy vystup bol ulozeny do: " + bonusFile.toAbsolutePath());
        }
    }
}
