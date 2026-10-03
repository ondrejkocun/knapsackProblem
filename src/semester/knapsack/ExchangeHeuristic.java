package semester.knapsack;

import java.util.List;

public final class ExchangeHeuristic {
    public Solution improve(KnapsackProblem problem, Solution initialSolution) {
        Solution current = initialSolution.copy();
        boolean improved = true;
        while (improved) {
            improved = false;
            List<Item> items = problem.getItems();
            for (Item selected : items) {
                if (!current.isSelected(selected)) {
                    continue;
                }
                for (Item unselected : items) {
                    if (current.isSelected(unselected)) {
                        continue;
                    }

                    Solution candidate = current.copy();
                    candidate.remove(selected);
                    candidate.add(unselected);

                    if (!problem.isFeasible(candidate) || !problem.isImprovement(candidate, current)) {
                        continue;
                    }

                    current = candidate;
                    improved = true;
                    break;
                }
                if (improved) {
                    break;
                }
            }
        }
        return current;
    }
}
