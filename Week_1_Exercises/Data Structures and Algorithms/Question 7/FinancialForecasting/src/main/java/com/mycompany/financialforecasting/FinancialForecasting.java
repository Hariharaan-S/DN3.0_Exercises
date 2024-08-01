/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.financialforecasting;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author shari
 */
public class FinancialForecasting {

    private static final Map<Integer, Double> memoizedMap = new HashMap<>();

    
    public static double calculateFutureValue(double initialValue, double growthRate, int periods) {
        if (periods == 0) {
            return initialValue;
        }
        if (memoizedMap.containsKey(periods)) {
            return memoizedMap.get(periods);
        }
        double result = calculateFutureValue(initialValue, growthRate, periods - 1) * (1 + growthRate);
        memoizedMap.put(periods, result);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== FINANCIAL FORECASRT ============");
        System.out.println("Enter the Initial value: ");
        double initialValue = sc.nextDouble();
        System.out.println("Enter the growth rate: ");
        double growthRate = sc.nextDouble();
        System.out.println("Enter the period: ");
        int periods = sc.nextInt();
        
        double futureValue = calculateFutureValue(initialValue, growthRate, periods);
        System.out.println("Future Value after " + periods + " periods: " + futureValue);
    }
}
