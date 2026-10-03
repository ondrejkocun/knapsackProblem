# Knapsack Problem

Java implementation of a capacitated knapsack problem solver. The project compares a constructive heuristic with an exchange heuristic that improves the initial solution.

## Features

- Loads item weights and values from text files.
- Builds an initial solution using a constructive heuristic.
- Improves the solution using an exchange heuristic.
- Measures the execution time of both heuristics.
- Writes a summary report and an optional bonus experiment in CSV format.

## Project structure

```text
src/semester/knapsack/   Java source files
data/                    Input files
output/                  Generated reports
```

The input files are:

- `data/H4_a.txt` - item weights
- `data/H4_c.txt` - item values

## Requirements

- Java Development Kit (JDK) 11 or newer

## Compile and run

From the project root, run these commands in PowerShell:

```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Path out | Out-Null
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
java -cp out semester.knapsack.Main
```

To run the additional bonus experiment:

```powershell
java -cp out semester.knapsack.Main --bonus
```

## Output

The standard run creates `output/vystup_h11_first.txt`. The bonus run additionally creates `output/bonus_h11_first.csv` with results for different capacity and item-limit settings.

## Configuration

The main parameters are defined in `src/semester/knapsack/Main.java`, including the number of items, item limit, and knapsack capacity.