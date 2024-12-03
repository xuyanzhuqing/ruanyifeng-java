package com.tax;

public sealed class Income extends IncomeIpl permits SalaryIncome, RoyaltyIncome /* 允许的合法收入 */ {
    protected double income;

    public Income (double income) {
        this.income = income;
    }

    public double getTax () {
        return income * 0.1;
    }

    /* 定义继承自抽象类的双倍征税方法 */
    @Override
    public double doubleTax() {
        return 0;
    }

    @Override
    public void testInterface() {

    }
}
