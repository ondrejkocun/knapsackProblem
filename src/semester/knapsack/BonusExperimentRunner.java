package semester.knapsack;

import java.util.Locale;

public final class BonusExperimentRunner {
    private final KnapsackSolver solver = new KnapsackSolver();

    public String run(KnapsackProblem baseProblem) {
        StringBuilder sb = new StringBuilder();
        sb.append("K multiplier;R delta;Objective value;Constructive time [ms];Exchange time [ms]").append(System.lineSeparator());

        for (int kPercent = -25; kPercent <= 25; kPercent += 5) {
            int adjustedK = (int) Math.round(baseProblem.getCapacityK() * (1.0d + (kPercent / 100.0d)));
            for (int rDelta = -50; rDelta <= 50; rDelta += 10) {
                KnapsackProblem variant = new KnapsackProblem(
                        adjustedK,
                        baseProblem.getItemLimitR() + rDelta,
                        baseProblem.getItems());
                SolverResult result = solver.solve(variant);
                sb.append(kPercent)
                        .append("%;")
                        .append(rDelta)
                        .append(";")
                        .append(result.getExchangeSolution().getObjectiveValue())
                        .append(";")
                        .append(String.format(Locale.US, "%.3f", result.getConstructiveTimeNanos() / 1_000_000.0d))
                        .append(";")
                        .append(String.format(Locale.US, "%.3f", result.getExchangeTimeNanos() / 1_000_000.0d))
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
