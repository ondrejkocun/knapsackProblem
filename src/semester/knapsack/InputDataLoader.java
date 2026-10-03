package semester.knapsack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class InputDataLoader {
    public KnapsackProblem load(Path weightsFile, Path valuesFile, int expectedItemCount, int itemLimit, int capacity)
            throws IOException {
        List<Integer> weights = readIntegers(weightsFile);
        List<Integer> values = readIntegers(valuesFile);
        if (weights.size() != values.size()) {
            throw new IllegalArgumentException("Weights and values files must have the same number of entries.");
        }
        if (weights.size() != expectedItemCount) {
            throw new IllegalArgumentException(
                    "Expected " + expectedItemCount + " items, got " + weights.size() + ".");
        }

        List<Item> items = new ArrayList<Item>(weights.size());
        for (int i = 0; i < weights.size(); i++) {
            items.add(new Item(i + 1, weights.get(i), values.get(i)));
        }
        return new KnapsackProblem(capacity, itemLimit, items);
    }

    private List<Integer> readIntegers(Path path) throws IOException {
        String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        String[] tokens = content.trim().split("\\s+");
        List<Integer> numbers = new ArrayList<Integer>(tokens.length);
        for (String token : tokens) {
            if (!token.isEmpty()) {
                numbers.add(Integer.parseInt(token));
            }
        }
        return numbers;
    }
}
