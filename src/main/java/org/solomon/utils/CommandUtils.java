package org.solomon.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * <p>
 * Title: 执行批处理工具类
 * </p>
 * <p>
 * Description: 执行批处理工具类。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * 
 */
public class CommandUtils {

    /**
     * 执行命令
     * 
     * @param commandLine
     * @return String 执行结果
     * @throws Exception
     */
    public static String execute(String commandLine) throws Exception {

        String[] cmd = new String[3];
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name").toLowerCase();
        String charset = null;
        String result = "";

        if (osName.startsWith("windows")) {
            cmd[0] = "cmd.exe";
            cmd[1] = "/C";
            cmd[2] = commandLine;
            charset = "GBK";
        } else if (osName.startsWith("linux")) {
            cmd[0] = "sh";
            charset = "UTF-8";
        }

        Process ps = Runtime.getRuntime().exec(cmd);
        String line = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(ps.getInputStream(), charset));
        while ((line = input.readLine()) != null) {
            result += line + "\n";
        }
        input.close();
        ps.destroy();
        return result;
    }
}
