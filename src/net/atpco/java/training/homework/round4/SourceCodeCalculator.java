package net.atpco.java.training.homework.round4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SourceCodeCalculator {
    public static void main(String[] args) {
        WinFrame();
    }

    private static void WinFrame() {
        JFrame winFrame = new JFrame("SourceCodeCalculator");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int frameWidth = 400;                     // 主窗口宽度
        final int frameHeight = 500;                    // 主窗口高度
        final int basicWidth = frameHeight / 20;        // 20
        final int basicHeight = frameHeight / 20;       // 25
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
        calculateResult.setEditable(false);

        winFrame.add(filePath);
        winFrame.add(fileChoose);
        winFrame.add(fileCalculate);
        winFrame.add(JCB_Java);
        winFrame.add(JCB_C);
        winFrame.add(JCB_CC);
        winFrame.add(JCB_Python);
        winFrame.add(calculateResult);
        winFrame.setVisible(true);

        // 监听
        fileChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new Label(), "Choose files");
                File file = jfc.getSelectedFile();
                if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                } else if(file.isFile()) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
            }
        });
    }
}
