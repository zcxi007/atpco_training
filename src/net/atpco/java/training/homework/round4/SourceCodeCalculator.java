package net.atpco.java.training.homework.round4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SourceCodeCalculator {
    private static String showMessage = "";
    private static int totalRows;
    private static int emptyRows;
    private static int annotationRows;

    public static void main(String[] args) {
        WinFrame();
    }

    private static void WinFrame() {
        final JFrame winFrame = new JFrame("SourceCodeCalculator");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 主窗口宽度
        final int frameWidth = 400;
        // 主窗口高度
        final int frameHeight = 500;
        final int basicWidth = frameHeight / 20;
        final int basicHeight = frameHeight / 20;
        final JTextField filePath = new JTextField();
        final JButton fileChoose = new JButton("Select");
        final JButton fileCalculate = new JButton("Calculate");
        final JCheckBox JCB_Java = new JCheckBox("JAVA");
        final JCheckBox JCB_C = new JCheckBox(" C ");
        final JCheckBox JCB_CC = new JCheckBox(" C# ");
        final JCheckBox JCB_Python = new JCheckBox(" py ");
        final JTextArea calculateResult = new JTextArea();

        // 框体设置
        winFrame.setSize(frameWidth, frameHeight);
        winFrame.setLocation(screenSize.width/2 - frameWidth/2,screenSize.height/2 - frameHeight/2 - (screenSize.height/40));
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setLayout(null);
        winFrame.setResizable(false);

        // 界面元素设计
        filePath.setBounds(basicWidth, basicHeight, basicWidth * 5, basicHeight);
        filePath.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        filePath.setEditable(false);

        fileChoose.setBounds(basicWidth * 7, basicHeight, basicWidth * 3, basicHeight);
        fileChoose.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

        fileCalculate.setBounds(basicWidth * 11, basicHeight, basicWidth * 4, basicHeight);
        fileCalculate.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

        JCB_Java.setBounds(basicWidth * 2, basicHeight * 3, basicWidth * 3, basicHeight);
        JCB_Java.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        JCB_C.setBounds(basicWidth * 5, basicHeight * 3, basicWidth * 3, basicHeight);
        JCB_C.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        JCB_CC.setBounds(basicWidth * 8, basicHeight * 3, basicWidth * 3, basicHeight);
        JCB_CC.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        JCB_Python.setBounds(basicWidth * 11, basicHeight * 3, basicWidth * 3, basicHeight);
        JCB_Python.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

        calculateResult.setBounds(basicWidth * 2, basicHeight * 5, basicWidth * 12, basicHeight * 13);
        calculateResult.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
        // 不可编辑
        calculateResult.setEditable(false);
        // 自动换行
        calculateResult.setLineWrap(true);

        winFrame.add(filePath);
        winFrame.add(fileChoose);
        winFrame.add(fileCalculate);
        winFrame.add(JCB_Java);
        winFrame.add(JCB_C);
        winFrame.add(JCB_CC);
        winFrame.add(JCB_Python);
        winFrame.add(calculateResult);
        winFrame.setVisible(true);

        // 文件选择器监听
        fileChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();

                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.setMultiSelectionEnabled(true);
                jfc.showDialog(new Label(), "Choose files");
                File[] files = jfc.getSelectedFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 文件夹的处理
                        calculateResult.setText("文件夹 : " + file.getAbsolutePath());
                    } else if(file.isFile()) {
                        // 单个文件的处理
                        // 截取文件名
                        String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("\\") + 1);
                        // 截取文件后缀
                        String[] strArray = fileName.split("\\.");
                        int suffixIndex = strArray.length - 1;
                        String fileType = strArray[suffixIndex];
                        String[] fileTypes = {"java", "c", "cs", "py"};
                        if (Arrays.asList(fileTypes).contains(fileType)) {
                            filePath.setText(file.getAbsolutePath());
//                            showMessage = "File: " + file.getAbsolutePath() + "\r\n";
                            readFileByLines(file);
                            calculateResult.setText(showMessage);
                        } else {
                            JOptionPane.showMessageDialog(null, "该文件不是Java、C、C#、Python的代码文件，请重新选择", " 警 告 ： ", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
    }

    /**
     * 以行为单位读取文件
     */
    private static void readFileByLines(File file) {
        try {
            int codingRows;
            int sysRows = 0;
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String lineContents;
            while ((lineContents = reader.readLine()) != null) {
                // 获取系统行数
                if (lineContents.contains("import") || lineContents.contains("package")) {
                    sysRows++;
                }

                // 获取空行数
                if (lineContents.isEmpty()) {
                    emptyRows++;
                }

                // 获取注释行数
                if (lineContents.contains("//") || lineContents.contains("/*") || lineContents.contains("*/") || Pattern.matches("(^\\*).*?", lineContents.trim())) {
                    annotationRows++;
                }

                // 获取所有的行数
                totalRows++;
            }
            sysRows = sysRows - 1;
            codingRows = totalRows - annotationRows - emptyRows - sysRows;
            System.out.println(sysRows);
            System.out.println(emptyRows);
            System.out.println(codingRows);
            System.out.println(annotationRows);
            System.out.println(totalRows);
            showMessage = showMessage + "文件总行数： " + totalRows + "\r\n";
            showMessage = showMessage + "文件空行数： " + emptyRows + "\r\n";
            showMessage = showMessage + "注释行数： " + annotationRows + "\r\n";
            showMessage = showMessage + "代码行数： " + codingRows;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
