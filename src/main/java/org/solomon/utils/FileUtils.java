package org.solomon.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * Title: 文件工具类
 * </p>
 * <p>
 * Description: 文件工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * 
 */
public class FileUtils {

    /**
     * 拷贝文件
     * 
     * @param src
     *            源文件
     * @param dest
     *            目标文件
     * @param bufferSize
     *            每次读取的字节数
     * @throws IOException
     */
    public static void copyFile(String src, String dest, int bufferSize) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        byte[] buffer = new byte[bufferSize];
        int length;

        while ((length = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, length);
        }
        fis.close();
        fos.close();
    }

    /**
     * 删除文件
     * 
     * @param src
     *            源文件
     */
    public static void deleteFile(String src) {
        new File(src).delete();
    }

    /**
     * 删除多个文件
     * 
     * @param src
     *            源文件数组
     */
    public static void deleteFiles(String... src) {
        for (String s : src) {
            FileUtils.deleteFile(s);
        }
    }

    /**
     * 根据路径删除文件
     * 
     * @param dir
     *            目录
     * @throws Exception
     */
    public static void deleteByDir(String dir) throws Exception {
        File d = new File(dir);
        if (!d.isDirectory())
            throw new Exception("\"" + dir + "\"" + "不是一个目录");
        String[] fileNameArray = d.list();
        FileUtils.deleteFiles(fileNameArray);
    }

    /**
     * 将内容写入文件
     * 
     * @param content
     *            写入的内容
     * @param dest
     *            写入的文件
     * @param append
     *            是否追加
     * @param newLine
     *            是否换行
     * @throws IOException
     */
    public static void writeToFile(String content, String dest, boolean append, boolean newLine) throws IOException {
        FileWriter writer = new FileWriter(dest, append);
        writer.write(content + (newLine == true ? System.getProperty("line.separator") : ""));
        writer.close();
    }

    /**
     * 获取文件内容
     * 
     * @param src
     *            源文件
     * @return String[] 文件内容数组，每行占一个数组空间
     * @throws IOException
     */
    public static String[] readContent(String src, Charset charset) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        String[] array = new String[FileUtils.readLineNumber(src)];
        String line;
        int lineNumber = 0;
        while ((line = br.readLine()) != null) {
            array[lineNumber] = line;
            lineNumber++;
        }
        reader.close();
        br.close();
        return array;
    }

    /**
     * 获取文件内容
     * 
     * @param src
     *            源文件
     * @return String[] 文件内容数组，每行占一个数组空间
     * @throws IOException
     */
    public static String readStringContent(String src) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        br.close();
        return sb.toString();
    }

    /**
     * 获取文件行数
     * 
     * @param src
     *            源文件
     * @return int 内容行数
     * @throws IOException
     */
    public static int readLineNumber(String src) throws IOException {
        FileReader reader = new FileReader(src);
        BufferedReader br = new BufferedReader(reader);
        int lineNumber = 0;
        while (br.readLine() != null) {
            lineNumber++;
        }
        reader.close();
        br.close();
        return lineNumber;
    }

    /**
     * 复制一个目录及其子目录、文件到另外一个目录
     * 
     * @param src
     * @param dest
     * @throws IOException
     */
    public static void copyDir(String _src, String _target) throws IOException {

        File src = new File(_src);
        File dest = new File(_target);

        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                // 递归复制
                copyDir(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    /**
     * 
     * 获取目录下所有文件(包括子文件夹) 递归调用
     * 
     * @param filePath
     * @return List<File>
     */
    public static List<File> getFiles(String filePath) {
        List<File> lstFiles = new ArrayList<File>();
        File sourceFile = new File(filePath);
        File[] files = sourceFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                lstFiles.add(file);
                lstFiles.addAll(getFiles(file.getAbsolutePath()));
            } else {
                lstFiles.add(file);
            }
        }
        return lstFiles;
    }

    /**
     * 获取目录下所有文件(按修改时间排序)
     * 
     * @param path
     * @return List<File>
     */
    public static List<File> getFileSort(String path) {

        List<File> list = getFiles(path);

        if (list != null && list.size() > 0) {

            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }

                }
            });
        }

        return list;
    }

    /**
     * 获取文件后缀名
     * 
     * @param file
     * @return
     */
    public static String getFileSuffix(File file) {
        if (!file.isFile() || !file.exists())
            return null;

        String name = file.getName();
        int pos = name.lastIndexOf(".");
        if (pos == -1) {
            return null;
        }
        return name.substring(pos + 1, name.length());
    }

    public static void main(String[] args) throws MalformedURLException, IOException {

    }

}
