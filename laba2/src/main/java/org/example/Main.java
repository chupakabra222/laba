package org.example;

import functions.FunctionPoint;
import functions.TabulatedFunction;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TabulatedFunction tf = new TabulatedFunction(-5, 5, new double[]{25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25});
        tf.deletePoint(0);
        for(int i = 0; i < tf.getPointsCount(); i++)
        {
            System.out.print(tf.getPointX(i));
            System.out.print(' ');
            System.out.print(tf.getPointY(i));
            System.out.println();
        }
        System.out.println(tf.getFunctionValue(1.5));
    }
}