package util;

import bean.FileInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明:
 * 系统版本: 2.4.2.0
 *
 * @author: lyd
 * 开发时间: 2018-10-11
 */
public class FileUtil {

    /**
     * 保存文件。
     * @param parentComponent 提示是否覆盖文件时候需要
     * @param fileInfoList
     * @throws Exception
     */
    public static void saveFile(Component parentComponent, List<FileInfo> fileInfoList) throws Exception {
        if(fileInfoList!=null){
            StringBuilder sb=new StringBuilder();
           for(FileInfo fileInfo : fileInfoList){
               if(saveSingleFile(parentComponent,fileInfo)){
                   sb.append(fileInfo.getFileName()+"保存成功"+"\r\n");
                }
           }
           if(sb.length()>0){
               JOptionPane.showMessageDialog(parentComponent, sb.toString(), "提示", JOptionPane.INFORMATION_MESSAGE);
           }
       }

    }
    /**
     * 保存文件。
     * @param parentComponent
     * @param fileInfo
     * @throws Exception
     */
    public static void saveFile(Component parentComponent, FileInfo fileInfo) throws Exception {
        if(saveSingleFile(parentComponent,fileInfo)){
            JOptionPane.showMessageDialog(parentComponent, fileInfo.getFileName()+"保存成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * 校验多个文件
     * @param parentComponent
     * @param filePathName
     * @return
     * @throws Exception
     */
    private static boolean validFile(Component parentComponent, String[] filePathName) throws Exception {
        List<File> files = new ArrayList<File>();
        for (String name : filePathName) {
            File file = new File(name);
            if (validFile(parentComponent, file)) {
                files.add(file);
            } else {
                return false;
            }
        }
        //删除file
        for (File file : files) {
            if (file.exists()) {
                if (!file.delete()) {
                    throw new Exception("无法覆盖文件：" + file.getName());
                }
            }
        }
        return true;
    }

    /**
     * 校验单个文件
     * @param parentComponent
     * @param file
     * @return
     */
    private static boolean validFile(Component parentComponent, File file) {
        if (file.exists()) {
            int i = JOptionPane.showConfirmDialog(parentComponent, "文件：" + file.getName() + ",已存在，是否覆盖", "警告", JOptionPane.WARNING_MESSAGE);
            if (i == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean saveSingleFile(Component parentComponent, FileInfo fileInfo) throws Exception {
        String filePathNamestr = fileInfo.getFilePath() + File.separator + fileInfo.getFileName();
        if (!validFile(parentComponent, new String[]{filePathNamestr})) {
            //验证不通过
            return false;
        }

        File file = new File(filePathNamestr);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            writer.write(fileInfo.getContent());
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return true;

    }
}
