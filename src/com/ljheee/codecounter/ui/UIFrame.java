package com.ljheee.codecounter.ui;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import com.ljheee.codecounter.core.ScanFiles;
import com.ljheee.codecounter.core.SourceFileInfo;
import com.ljheee.codecounter.core.SourceFileList;
import com.ljheee.codecounter.ui.about.About;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


/**
 * 
 * @author ljheee
 *
 */
public class UIFrame {

	private JFrame jf = null;
	private JLabel leftInfo = new JLabel("状态栏:");
	private JLabel pathInfo = new JLabel("  ");
	private JLabel timeInfo = new JLabel("  ");
	JLabel total,code,comment,blank;

	private JMenuItem openFileItem, exitItem, findFileItem, viewLogItem, delLogItem, aboutItem;
	private JMenuItem switchSuanfa;


	ActionHandle handle = new ActionHandle();
	
	private JTextField srcFiles,fileType;

	JButton viewFileBtn,analyseBtn;
	JFileChooser fileChooser = new JFileChooser();
	
	
	File src = null;

	public UIFrame() {
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//可选择目录

		jf = new JFrame("SourceFileTool");
		jf.setSize(750,510 );
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);

		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(new GridLayout(2, 1));

		JRootPane rootPane = new JRootPane(); // 此panel，添加菜单
		rootPane.setBackground(Color.gray);
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("文件(F)");
		JMenu commandMenu = new JMenu("命令(C)");
		JMenu toolMenu = new JMenu("工具(S)");
		JMenu optionMenu = new JMenu("选项(N)");
		JMenu helpMenu = new JMenu("帮助(H)");

		rootPane.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(commandMenu);
		menuBar.add(toolMenu);
		menuBar.add(optionMenu);
		menuBar.add(helpMenu);

		findFileItem = new JMenuItem("查找文件");
		switchSuanfa = new JMenuItem("转换格式");
		findFileItem.addActionListener(handle);
		switchSuanfa.addActionListener(handle);
		toolMenu.add(findFileItem);
		toolMenu.add(switchSuanfa);

		viewLogItem = new JMenuItem("查看日志");
		delLogItem = new JMenuItem("清除日志");
		viewLogItem.addActionListener(handle);
		delLogItem.addActionListener(handle);
		optionMenu.add(viewLogItem);
		optionMenu.add(delLogItem);

		openFileItem = new JMenuItem("打开文件");
		exitItem = new JMenuItem("退出");
		openFileItem.addActionListener(handle);
		exitItem.addActionListener(handle);

		aboutItem = new JMenuItem("关于");
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(handle);

		// 给菜单 添加菜单项
		fileMenu.add(openFileItem);
		fileMenu.add(exitItem);


		topJPanel.add(rootPane);// 工具panel :文件、编辑、查看
		jf.getContentPane().add(topJPanel, BorderLayout.NORTH);
		
		

		// center
		JPanel centerP = new JPanel(new GridLayout(2, 1));
		jf.getContentPane().add(centerP, BorderLayout.CENTER);
		
		JPanel c1 = new JPanel();
		JLabel label = new JLabel("\u6587\u4EF6\u6E90\uFF1A");//文件源
		
		srcFiles = new JTextField();
		fileType = new JTextField("源文件类型，如.java");
		srcFiles.setColumns(10);
		//浏览srcFiles
		viewFileBtn = new JButton("浏览");
		//统计
		analyseBtn = new JButton("统计");
		c1.add(label);
		c1.add(srcFiles);
		c1.add(viewFileBtn);
		c1.add(fileType);
		c1.add(analyseBtn);
		
		
		viewFileBtn.addActionListener(handle);
		analyseBtn.addActionListener(handle);
		
		JPanel showResult = new JPanel();
		centerP.add(c1);
		centerP.add(showResult);
		
		JLabel label_1 = new JLabel("\u603B\u4EE3\u7801\u884C\uFF1A");
		
		JLabel label_2 = new JLabel("\u4EE3\u7801\u884C\uFF1A");
		
		JLabel label_3 = new JLabel("\u6CE8\u91CA\u884C\uFF1A");
		
		JLabel label_4 = new JLabel("\u7A7A\u884C\uFF1A");
		
		total = new JLabel("0");
		code = new JLabel("0");
		comment = new JLabel("0");
		blank = new JLabel("0");
		
		GroupLayout gl_showResult = new GroupLayout(showResult);
		gl_showResult.setHorizontalGroup(
			gl_showResult.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_showResult.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_showResult.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1)
						.addComponent(label_3)
						.addComponent(label_2)
						.addComponent(label_4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_showResult.createParallelGroup(Alignment.LEADING)
						.addComponent(total, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_showResult.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(blank, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comment, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(code, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
					.addContainerGap(590, Short.MAX_VALUE))
		);
		gl_showResult.setVerticalGroup(
			gl_showResult.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_showResult.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_showResult.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(total))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_showResult.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(code))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_showResult.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(comment))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_showResult.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(blank))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		showResult.setLayout(gl_showResult);

		// south--状态栏
		JToolBar bottomToolBar = new JToolBar();
		bottomToolBar.setFloatable(false);// 设置JToolBar不可拖动

		bottomToolBar.setPreferredSize(new Dimension(jf.getWidth(), 20));
		bottomToolBar.add(leftInfo);

		// bottomToolBar.addSeparator(); //此方法添加分隔符 无效
		JSeparator jsSeparator = new JSeparator(SwingConstants.VERTICAL);
		bottomToolBar.add(jsSeparator);// 添加分隔符

		leftInfo.setPreferredSize(new Dimension(200, 20));
		leftInfo.setHorizontalTextPosition(SwingConstants.LEFT);

		bottomToolBar.add(pathInfo);
		pathInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		bottomToolBar.add(new JSeparator(SwingConstants.VERTICAL));// 添加分隔符

		bottomToolBar.add(timeInfo);
		timeInfo.setPreferredSize(new Dimension(70, 20));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		timeInfo.setText(sdf.format(new Date()));

		jf.getContentPane().add(bottomToolBar, BorderLayout.SOUTH);// 下面--放“状态栏”

//		jf.pack();
		jf.setVisible(true);
	}

	
	/**
	 * 主界面菜单--事件监听
	 * @author ljheee
	 *
	 */
	class ActionHandle implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == openFileItem) { // 
				
			}

			if (e.getSource() == exitItem) {// exit application
				System.exit(0);
			}

			if (e.getSource() == aboutItem) {// show about
//				JOptionPane.showMessageDialog(jf, "@Author:ljheee \n 2016");
				new About(jf,"关于",true);
			}

			if (e.getSource() == findFileItem) {// viewItem 
				jf.repaint();
			}

			if (e.getSource() == switchSuanfa) {// 切换压缩算法
												// 
			}

			if (e.getSource() == viewLogItem) {// 查看日志

			}

			if (e.getSource() == delLogItem) {// 删除日志
				
			}
			
			if (e.getSource() == viewFileBtn){//选择yao压缩的文件路径
				fileChooser.showOpenDialog(jf);
				src = fileChooser.getSelectedFile();
				System.out.println(src);
				srcFiles.setText(src.getAbsolutePath());
			}
			
			if (e.getSource() == analyseBtn){//统计文件
				String filetype = fileType.getText().trim();
				if(src == null | filetype.equals("")){
					JOptionPane.showMessageDialog(jf, "请选择源文件目录和类型");
					return;
				}
				SourceFileList  list = new ScanFiles(src, filetype).scan();;
				
				int a[] = count(list);
				total.setText(a[0]+"");
				code.setText(a[1]+"");
				comment.setText(a[2]+"");
				blank.setText(a[3]+"");
				
				JOptionPane.showMessageDialog(jf, "统计完毕");
			}

		}

		/**
		 * 统计结果
		 */
		private int[] count(SourceFileList list) {
			int ttotal = 0,ccode = 0,ccomment = 0,bblack = 0;
			
			for (int i = 0; i < list.size(); i++) {
				SourceFileInfo sf  = list.get(i);
				ttotal += sf.totalLine;
				ccode += sf.codeLine;
				ccomment += sf.commentLine;
				bblack += sf.blankLine;
			}
			int result[] = {ttotal,ccode ,ccomment ,bblack};
			return result;
		}
	}
	
}
