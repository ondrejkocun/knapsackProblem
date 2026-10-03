package semester.knapsack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KnapsackProblem {
    private final int capacityK;
    private final int itemLimitR;
    private final List<Item> items;

    public KnapsackProblem(int capacityK, int itemLimitR, List<Item> items) {
        this.capacityK = capacityK;
        this.itemLimitR = itemLimitR;
        this.items = Collections.unmodifiableList(new ArrayList<Item>(items));
    }

    public int getCapacityK() {
        return capacityK;
    }

    public int getItemLimitR() {
        return itemLimitR;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isFeasible(Solution solution) {
        return solution.getTotalWeight() <= capacityK && solution.getSelectedCount() <= itemLimitR;
    }

    public boolean isImprovement(Solution candidate, Solution current) {
        return candidate.getObjectiveValue() > current.getObjectiveValue();
    }
}
