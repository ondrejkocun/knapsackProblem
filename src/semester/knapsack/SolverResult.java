 package semester.knapsack;

public final class SolverResult {
    private final KnapsackProblem problem;
    private final Solution constructiveSolution;
    private final Solution exchangeSolution;
    private final long constructiveTimeNanos;
    private final long exchangeTimeNanos;

    public SolverResult(KnapsackProblem problem, Solution constructiveSolution, Solution exchangeSolution,
                        long constructiveTimeNanos, long exchangeTimeNanos) {
        this.problem = problem;
        this.constructiveSolution = constructiveSolution;
        this.exchangeSolution = exchangeSolution;
        this.constructiveTimeNanos = constructiveTimeNanos;
        this.exchangeTimeNanos = exchangeTimeNanos;
    }

    public KnapsackProblem getProblem() {
        return problem;
    }

    public Solution getConstructiveSolution() {
        return constructiveSolution;
    }

    public Solution getExchangeSolution() {
        return exchangeSolution;
    }

    public long getConstructiveTimeNanos() {
        return constructiveTimeNanos;
    }

    public long getExchangeTimeNanos() {
        return exchangeTimeNanos;
    }

    public long getConstructiveTimeMillis() {
        return constructiveTimeNanos / 1_000_000L;
    }

    public long getExchangeTimeMillis() {
        return exchangeTimeNanos / 1_000_000L;
    }

    public String getProblemTitle() {
        return "Uloha 1 - klasicka uloha o batohu";
    }

    public String getConstructiveLabel() {
        return "H11";
    }

    public String getExchangeLabel() {
        return "first admissible";
    }

    public int getImprovementValue() {
        return exchangeSolution.getObjectiveValue() - constructiveSolution.getObjectiveValue();
    }
}
