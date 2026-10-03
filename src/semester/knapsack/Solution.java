package semester.knapsack;

import java.util.ArrayList;
import java.util.List;

public final class Solution {
    private final boolean[] selected;
    private int totalWeight;
    private int totalValue;
    private int selectedCount;

    public Solution(KnapsackProblem problem) {
        this.selected = new boolean[problem.getItems().size()];
    }

    private Solution(boolean[] selected, int totalWeight, int totalValue, int selectedCount) {
        this.selected = selected;
        this.totalWeight = totalWeight;
        this.totalValue = totalValue;
        this.selectedCount = selectedCount;
    }

    public Solution copy() {
        boolean[] clone = new boolean[selected.length];
        System.arraycopy(selected, 0, clone, 0, selected.length);
        return new Solution(clone, totalWeight, totalValue, selectedCount);
    }

    public boolean isSelected(Item item) {
        return selected[item.getIndex() - 1];
    }

    public void add(Item item) {
        int idx = item.getIndex() - 1;
        if (selected[idx]) {
            return;
        }
        selected[idx] = true;
        totalWeight += item.getWeight();
        totalValue += item.getValue();
        selectedCount++;
    }

    public void remove(Item item) {
        int idx = item.getIndex() - 1;
        if (!selected[idx]) {
            return;
        }
        selected[idx] = false;
        totalWeight -= item.getWeight();
        totalValue -= item.getValue();
        selectedCount--;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getObjectiveValue() {
        return totalValue;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public List<Integer> getSelectedIndices() {
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                indices.add(i + 1);
            }
        }
        return indices;
    }
}
