package semester.knapsack;

public final class KnapsackSolver {
    private final ConstructiveHeuristic constructiveHeuristic = new ConstructiveHeuristic();
    private final ExchangeHeuristic exchangeHeuristic = new ExchangeHeuristic();

    public SolverResult solve(KnapsackProblem problem) {
        long constructiveStart = System.nanoTime();
        Solution constructive = constructiveHeuristic.solve(problem);
        long constructiveDuration = System.nanoTime() - constructiveStart;

        long exchangeStart = System.nanoTime();
        Solution exchange = exchangeHeuristic.improve(problem, constructive);
        long exchangeDuration = System.nanoTime() - exchangeStart;

        return new SolverResult(problem, constructive, exchange, constructiveDuration, exchangeDuration);
    }
}
