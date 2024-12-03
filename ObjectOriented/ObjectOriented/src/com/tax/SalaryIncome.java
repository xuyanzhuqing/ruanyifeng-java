package com.tax;

/* 工资收入 */
final public class SalaryIncome extends Income {
    public SalaryIncome(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        double minPoint = 8000;

        if (income < minPoint) {
            return 0;
        }
        return (income - minPoint) * 0.2;
    }
}
