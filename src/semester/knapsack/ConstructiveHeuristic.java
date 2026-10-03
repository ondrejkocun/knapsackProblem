package semester.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class ConstructiveHeuristic {
    public Solution solve(KnapsackProblem problem) {
        Solution solution = new Solution(problem);
        List<Item> items = new ArrayList<Item>(problem.getItems());
        Collections.sort(items, Comparator.comparingInt(Item::getWeight).thenComparingInt(Item::getIndex));
        runAddVariant(problem, solution, items);

        if (!problem.isFeasible(solution)) {
            throw new IllegalStateException("Constructive heuristic did not produce a feasible solution.");
        }
        return solution;
    }

    private void runAddVariant(KnapsackProblem problem, Solution solution, List<Item> orderedItems) {
        for (Item item : orderedItems) {
            if (solution.isSelected(item)) {
                continue;
            }
            Solution candidate = solution.copy();
            candidate.add(item);
            if (problem.isFeasible(candidate)) {
                solution.add(item);
            }
        }
    }
}
