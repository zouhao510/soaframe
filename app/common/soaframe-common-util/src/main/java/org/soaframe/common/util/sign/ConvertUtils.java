package org.soaframe.common.util.sign;

/**
 * Created by haiyang.song on 15/10/22.
 */

public abstract class ConvertUtils {
    public static String toHex(byte[] input) {
        if (input == null) {
            return null;
        } else {
            StringBuilder output = new StringBuilder(input.length * 2);

            for (byte anInput : input) {
                int current = anInput & 255;
                if (current < 16) {
                    output.append("0");
                }

                output.append(Integer.toString(current, 16));
            }
            return output.toString();
        }
    }


}