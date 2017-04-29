package org.solomon.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <p>
 * Title: 加、减、乘、除科学计算。
 * </p>
 * <p>
 * Description: 加、减、乘、除科学计算。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * 
 */
public class MathUtils {

    /**
     * 返回科学计算后的加法结果
     * 
     * @param val1
     * @param val2
     * @param mc
     *            精度
     * @return
     */
    public static double add(double val1, double val2, int mc) {
        BigDecimal bg1 = new BigDecimal(val1);
        BigDecimal bg2 = new BigDecimal(val2);
        BigDecimal result = new BigDecimal(0);

        result = bg1.add(bg2, new MathContext(mc, RoundingMode.HALF_UP));

        return result.doubleValue();
    }

    /**
     * 返回科学计算后的减法结果
     * 
     * @param val1
     * @param val2
     * @param mc
     *            精度
     * @return
     */
    public static double subtract(double val1, double val2, int mc) {
        if (val2 == 0)
            return val1;
        BigDecimal bg1 = new BigDecimal(val1);
        BigDecimal bg2 = new BigDecimal(val2);
        BigDecimal result = new BigDecimal(0);

        result = bg1.subtract(bg2, new MathContext(mc, RoundingMode.HALF_UP));

        return result.doubleValue();
    }

    /**
     * 返回科学计算后的乘法结果
     * 
     * @param val1
     * @param val2
     * @param mc
     *            精度
     * @return
     */
    public static double multiply(double val1, double val2, int mc) {
        if (val1 == 0 || val2 == 0)
            return 0;
        BigDecimal bg1 = new BigDecimal(val1);
        BigDecimal bg2 = new BigDecimal(val2);
        BigDecimal result = new BigDecimal(0);
        result = bg1.multiply(bg2, new MathContext(mc, RoundingMode.HALF_UP));

        return result.doubleValue();
    }

    /**
     * 返回科学计算后的除法结果
     * 
     * @param fz
     *            分子
     * @param fm
     *            分母
     * @param mc
     *            精度
     * @return
     */
    public static double divide(double fz, double fm, int mc) {
        if (fz == 0 || fm == 0)
            return 0;
        BigDecimal bg1 = new BigDecimal(fz);
        BigDecimal bg2 = new BigDecimal(fm);
        BigDecimal result = new BigDecimal(0);

        result = bg1.divide(bg2, new MathContext(mc, RoundingMode.HALF_UP));

        return result.doubleValue();
    }

    public static void main(String[] args) {
        double v1 = 23456;
        double v2 = 123;
        System.out.println(MathUtils.divide(v2, v1, 4));
    }

}
