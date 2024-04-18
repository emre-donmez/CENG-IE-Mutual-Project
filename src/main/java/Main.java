import org.apache.commons.math3.distribution.NormalDistribution;

public class Main {
    private final static double SENSITIVITY = 0.00000001;
    private static final NormalDistribution normalDistribution = new NormalDistribution();
    private static final double unitCost = 20;
    private static final double annualInterestRate = 0.25;
    private static final double orderingCost = 100;
    private static final double leadTime = 1.0 / 3.0;
    private static final double lossOfGoodWillCost = 20;
    private static final double average = 500;
    private static final double standartDeviation = 100;
    private static double annualDemand, holdingCost, qOld, fr, z, lz, nr, qNew, rOld, rNew, safetyStock,
                   averageAnnualHoldingCost, setupCost,penaltyCost, averageTimeOrders, proportionDemand;

    public static void main(String[] args) {
        calculate();
    }

    public static void calculate() {
        annualDemand = average / leadTime;
        holdingCost = annualInterestRate * unitCost;
        qOld = q0();
        fr = fr(qOld);
        z = getStandardValue(fr);
        lz = getStandLossValue(z);
        rOld = r();
        nr = nr();
        safetyStock = safetyStock(rOld);
        averageAnnualHoldingCost = averageAnnualHoldingCost(qOld);
        setupCost = setupCost(qOld);
        penaltyCost = penaltyCost(qOld);
        averageTimeOrders = averageTimeOrders(qOld);
        proportionDemand = proportionDemand(qOld);

        printResults(0, qOld, rOld, safetyStock, averageAnnualHoldingCost, setupCost,penaltyCost, averageTimeOrders,proportionDemand, fr, z, lz, nr);

        int i = 1;

        while (true) {
            qNew = q();
            fr = fr(qNew);
            z = getStandardValue(fr);
            lz = getStandLossValue(z);
            rNew = r();
            nr = nr();
            safetyStock = safetyStock(rNew);
            averageAnnualHoldingCost = averageAnnualHoldingCost(qNew);
            setupCost = setupCost(qNew);
            penaltyCost = penaltyCost(qNew);
            averageTimeOrders = averageTimeOrders(qNew);
            proportionDemand = proportionDemand(qNew);

            printResults(i, qNew, rNew, safetyStock, averageAnnualHoldingCost, setupCost,penaltyCost,averageTimeOrders,proportionDemand, fr, z, lz, nr);

            if (checkConverge()) break;

            i++;
            qOld = qNew;
            rOld = rNew;
        }
    }

    private static void printResults(int i, double q, double r, double safetyStock, double averageAnnualHoldingCost, double setupCost, double penaltyCost, double averageTimeOrders, double proportionDemand, double fr, double z, double lz, double nr) {
        System.out.println(
                        "i = " + i + " | " +
                        "\tq = " + formatDouble(q) + " | " +
                        "\tr = " + formatDouble(r) + " | " +
                        "\tSafety Stock = " + formatDouble(safetyStock) + " | " +
                        "\tAverage Annual Holding Cost = " + formatDouble(averageAnnualHoldingCost) + " | " +
                        "\tSetup Cost = " + formatDouble(setupCost) + " | " +
                        "\tPenalty Cost = " + formatDouble(penaltyCost) + " | " +
                        "\tAverage Time Orders In Month = " + formatDouble(averageTimeOrders) + " | " +
                        "\tProportion of Demand  = " + formatDouble(proportionDemand) + " %" + formatDouble((1.0 - proportionDemand) * 100) + " | " +
                        "\tF(R) = " + formatDouble(fr) + " | " +
                        "\tz = " + formatDouble(z) + " | " +
                        "\tlz = " + formatDouble(lz) + " | " +
                        "\tnr = " + formatDouble(nr));
    }

    private static String formatDouble(double number) {
        String formatted = String.format("%.16f", number).replace(",", ".");
        if (formatted.indexOf(".") < 15) {
            formatted = String.format("%016.16f", number).replace(",", ".");
        }
        return formatted;
    }

    private static double q0() {
        return Math.sqrt((2 * orderingCost * annualDemand) / holdingCost);
    }

    private static double averageAnnualHoldingCost(double q) {
        return holdingCost * (q / 2.0 + safetyStock);
    }

    private static double averageTimeOrders(double q) {
        return q / annualDemand * 12;
    }

    private static double penaltyCost(double q) {
        return lossOfGoodWillCost * annualDemand * nr / q;
    }

    private static double setupCost(double q) {
        return orderingCost * annualDemand / q;
    }

    private static double safetyStock(double r) {
        return r - average;
    }

    private static double proportionDemand(double q) {
        return nr / q;
    }

    private static boolean checkConverge() {
        return Math.abs(qOld - qNew) < SENSITIVITY && Math.abs(rOld - rNew) < SENSITIVITY;
    }

    private static double fr(double q) {
        return 1.0 - (q * holdingCost) / (lossOfGoodWillCost * annualDemand);
    }

    private static double getStandardValue(double zScore) {
        return normalDistribution.inverseCumulativeProbability(zScore);
    }

    private static double getStandLossValue(double zScore) {
        double fz = normalDistribution.cumulativeProbability(zScore);
        double density = normalDistribution.density(zScore);

        return density - zScore * (1 - fz);
    }

    private static double r() {
        return average + (standartDeviation * z);
    }

    private static double nr() {
        return standartDeviation * lz;
    }

    private static double q() {
        return Math.sqrt((2 * annualDemand * (orderingCost + lossOfGoodWillCost * nr)) / holdingCost);
    }
}
