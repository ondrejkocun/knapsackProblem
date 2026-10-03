package semester.knapsack;

public final class Item {
    private final int index;
    private final int weight;
    private final int value;

    public Item(int index, int weight, int value) {
        this.index = index;
        this.weight = weight;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
