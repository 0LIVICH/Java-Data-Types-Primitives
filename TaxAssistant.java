
import java.util.Scanner;

public class TaxAssistant {

    private static int totalIncome = 0;
    private static int totalExpense = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа-помощник ИП для выбора системы налогообложения");

        while (true) {
            System.out.println("Введите команду (add, calculate, end):");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("add")) {
                addIncomeAndExpense(scanner);
            } else if (command.equalsIgnoreCase("calculate")) {
                calculateBestTaxSystem();
            } else if (command.equalsIgnoreCase("end")) {
                System.out.println("Завершение программы.");
                break;
            } else {
                System.out.println("Неизвестная команда. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void addIncomeAndExpense(Scanner scanner) {
        System.out.print("Введите сумму доходов: ");
        int income = Integer.parseInt(scanner.nextLine().trim());
        totalIncome += income;

        System.out.print("Введите сумму расходов: ");
        int expense = Integer.parseInt(scanner.nextLine().trim());
        totalExpense += expense;

        System.out.println("Данные обновлены. Общий доход: " + totalIncome + ", Общий расход: " + totalExpense);
    }

    private static void calculateBestTaxSystem() {
        int taxIncome = calculateIncomeTax(totalIncome);
        int taxIncomeMinusExpense = calculateIncomeMinusExpenseTax(totalIncome, totalExpense);

        System.out.println("Налоги по системе УСН доходы: " + taxIncome + " рублей.");
        System.out.println("Налоги по системе УСН доходы минус расходы: " + taxIncomeMinusExpense + " рублей.");

        if (taxIncome < taxIncomeMinusExpense) {
            System.out.println("Рекомендуемая система: УСН доходы.");
            System.out.println("Экономия: " + (taxIncomeMinusExpense - taxIncome) + " рублей.");
        } else if (taxIncome > taxIncomeMinusExpense) {
            System.out.println("Рекомендуемая система: УСН доходы минус расходы.");
            System.out.println("Экономия: " + (taxIncome - taxIncomeMinusExpense) + " рублей.");
        } else {
            System.out.println("Можете выбрать любую систему налогообложения. Налоги равны.");
        }
    }

    private static int calculateIncomeTax(int income) {
        return (int) Math.max(0, income * 0.06);
    }

    private static int calculateIncomeMinusExpenseTax(int income, int expense) {
        int taxableAmount = Math.max(0, income - expense);
        return (int) Math.max(0, taxableAmount * 0.15);
    }
}