package semester.knapsack;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class OutputWriter {
    public void writeSummary(SolverResult result, Path outputFile) throws IOException {
        StringBuilder sb = new StringBuilder(formatSummary(result));
        sb.append(System.lineSeparator());
        sb.append("--- Vybrane indexy predmetov ---").append(System.lineSeparator());
        sb.append("Po vsuvacej heuristike: ")
                .append(result.getConstructiveSolution().getSelectedIndices())
                .append(System.lineSeparator());
        sb.append("Po vymennej heuristike: ")
                .append(result.getExchangeSolution().getSelectedIndices())
                .append(System.lineSeparator());
        Files.createDirectories(outputFile.getParent());
        Files.write(outputFile, sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    public void writeBonusReport(String content, Path outputFile) throws IOException {
        Files.createDirectories(outputFile.getParent());
        Files.write(outputFile, content.getBytes(StandardCharsets.UTF_8));
    }

    public String formatSummary(SolverResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PREHLAD RIESENIA - ")
                .append(result.getProblemTitle())
                .append(" ===")
                .append(System.lineSeparator());
        sb.append("Pocet predmetov n = ")
                .append(result.getProblem().getItems().size())
                .append(", limit r = ")
                .append(result.getProblem().getItemLimitR())
                .append(", kapacita K = ")
                .append(result.getProblem().getCapacityK())
                .append(System.lineSeparator())
                .append(System.lineSeparator());

        appendSolutionBlock(
                sb,
                "Konstrukcne riesenie po heuristike " + result.getConstructiveLabel(),
                result.getConstructiveSolution(),
                result.getConstructiveTimeMillis(),
                result.getProblem().isFeasible(result.getConstructiveSolution()));

        sb.append(System.lineSeparator());

        appendSolutionBlock(
                sb,
                "Riesenie po vymennom zlepseni - " + result.getExchangeLabel(),
                result.getExchangeSolution(),
                result.getExchangeTimeMillis(),
                result.getProblem().isFeasible(result.getExchangeSolution()));

        sb.append(System.lineSeparator());
        sb.append("--- Strucne porovnanie ---").append(System.lineSeparator());
        sb.append("Ucelova funkcia po konstrukcii: ")
                .append(result.getConstructiveSolution().getObjectiveValue())
                .append(System.lineSeparator());
        sb.append("Ucelova funkcia po vymene: ")
                .append(result.getExchangeSolution().getObjectiveValue())
                .append(System.lineSeparator());
        sb.append("Rozdiel po zlepseni: ")
                .append(result.getImprovementValue())
                .append(System.lineSeparator());

        return sb.toString();
    }

    private void appendSolutionBlock(StringBuilder sb, String title, Solution solution, long timeMillis, boolean feasible) {
        sb.append(">> ")
                .append(title)
                .append(System.lineSeparator())
                .append(System.lineSeparator());
        sb.append("Sucet cien predmetov: ")
                .append(solution.getObjectiveValue())
                .append(System.lineSeparator());
        sb.append("Pocet vybranych predmetov: ")
                .append(solution.getSelectedCount())
                .append(System.lineSeparator());
        sb.append("Sucet hmotnosti: ")
                .append(solution.getTotalWeight())
                .append(System.lineSeparator());
        sb.append("Trvanie vypoctu: ")
                .append(timeMillis)
                .append(" ms")
                .append(System.lineSeparator());
        sb.append("Kontrola obmedzeni: ")
                .append(feasible ? "vyhovuje" : "nevyhovuje")
                .append(System.lineSeparator());
    }
}
