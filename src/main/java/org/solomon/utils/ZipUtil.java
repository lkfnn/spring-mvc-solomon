package org.solomon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/**
 * <p>
 * Title: ZIP压缩解压缩工具类
 * </p>
 * <p>
 * Description: 邮件发送工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2017
 * </p>
 * <p>
 * Company: 长城数字[www.e-u.cn]
 * </p>
 * 
 */
public class ZipUtil {

    /**
     * zip压缩文件
     * 
     * @param dir
     * @param zippath
     */
    public static void zip(String sourceFilePath, String zipTargetPath) {
        List<File> files = FileUtils.getFiles(sourceFilePath);
        compressFilesZip(sourceFilePath, zipTargetPath, files);
    }

    /**
     * 把指定文件压缩成zip格式
     * 
     * @param sourceFilePath
     *            需要压缩的文件
     * @param zipTargetPath
     *            压缩后的zip文件路径
     */
    private static void compressFilesZip(String sourceFilePath, String zipTargetPath, List<File> files) {
        ZipArchiveOutputStream zaos = null;
        try {
            File zipFile = new File(zipTargetPath);
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            for (File soucefile : files) {
                String pathName = soucefile.getPath().substring(sourceFilePath.lastIndexOf("\\"));
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(soucefile, pathName);
                zaos.putArchiveEntry(zipArchiveEntry);

                if (soucefile.isDirectory()) {
                    continue;
                }

                InputStream is = null;
                try {
                    is = new BufferedInputStream(new FileInputStream(soucefile));
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        zaos.write(buffer, 0, len);
                    }
                    zaos.closeArchiveEntry();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    if (is != null)
                        is.close();
                }

            }
            zaos.finish();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (zaos != null) {
                    zaos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 把ZIP文件解压到指定的文件夹
     * 
     * @param zipFilePath
     *            zip文件路径
     * @param saveFileDir
     *            解压后的文件存放路径
     */
    public static void unzip(String zipFilePath, String saveFileDir) {
        if (!saveFileDir.endsWith("\\") && !saveFileDir.endsWith("/")) {
            saveFileDir += File.separator;
        }
        File dir = new File(saveFileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(zipFilePath);
        if (file.exists()) {
            InputStream is = null;
            ZipArchiveInputStream zais = null;
            try {
                is = new FileInputStream(file);
                zais = new ZipArchiveInputStream(is, "GBK");
                ArchiveEntry archiveEntry = null;
                while ((archiveEntry = zais.getNextEntry()) != null) {
                    // 获取文件名
                    String entryFileName = archiveEntry.getName();
                    // 构造解压出来的文件存放路径
                    String entryFilePath = saveFileDir + entryFileName;
                    OutputStream os = null;
                    try {
                        // 把解压出来的文件写到指定路径
                        File entryFile = new File(entryFilePath);
                        if (entryFileName.endsWith("/")) {
                            entryFile.mkdirs();
                        } else {
                            os = new BufferedOutputStream(new FileOutputStream(entryFile));
                            byte[] buffer = new byte[1024];
                            int len = -1;
                            while ((len = zais.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                        }
                    } catch (IOException e) {
                        throw new IOException(e);
                    } finally {
                        if (os != null) {
                            os.flush();
                            os.close();
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (zais != null) {
                        zais.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        // String unzipdir = "D:\\VSSS\\";
        // String unzipfile = "D:\\VSS.zip";

        // ZipUtil.unzip(unzipfile, unzipdir);

        String sourceFilePath = "D:\\VSSS\\VSS\\";
        String zipTargetPath = "D:\\VSS.zip";

        ZipUtil.zip(sourceFilePath, zipTargetPath);

        long end = System.currentTimeMillis();
        System.out.println("success，total time：" + (end - begin) / 1000);
    }
}
