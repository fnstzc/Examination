package com.fnst.examination.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 导出试卷
 * @author wulikun
 *
 */

@SuppressWarnings("all")
public class ExtPDF {

	public static String extPDF2(ArrayList<String> selectList, ArrayList<String> fillBlankList, ArrayList<String> shortAnswerList,String path,String type){
		// 第一步：创建一个document对象。
		Document document = new Document();
		//为路径添加以时间戳命名的文件名
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		String str = sdf.format(date);
		String filePath = "";
		if (type.equals("exam")) {
			filePath = path + str + ".pdf";
		} else if (type.equals("answer")) {
			filePath = path + str + "_answer" + ".pdf"; 
		}
		try {
			// 第二步：
			// 创建一个PdfWriter实例，
			// 将文件输出流指向一个文件。
			PdfWriter pdf = PdfWriter.getInstance(document,new FileOutputStream(filePath));
			// 第三步：打开文档。
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese, 10, Font.NORMAL);
            Font FontChinese2 = new Font(bfChinese,18, Font.BOLD);
            Font FontChinese3 = new Font(bfChinese,14,Font.BOLD);
            document.open();

            //设置标题
            Paragraph title = new Paragraph("2016富士通南大软件技术有限公司笔试卷",FontChinese2);
            title.setAlignment(1);
            document.add(title);

            
            //填空题标题
            Paragraph fillBlankTitle = new Paragraph("一、填空题",FontChinese3);
            //设置段前距离
            fillBlankTitle.setSpacingBefore(30);
            //设置段后距离
            fillBlankTitle.setSpacingAfter(20);
            document.add(fillBlankTitle);

            //加入填空题
            for(String fillBlank:fillBlankList){
            	Paragraph fb = new Paragraph(fillBlank,FontChinese);
            	//设置段后距离
            	fb.setSpacingAfter(10);
            	document.add(fb);
            }

            //选择题标题
            Paragraph selectTitle = new Paragraph("二、选择题",FontChinese3);
            //设置段前距离
            selectTitle.setSpacingBefore(30);
            //设置段后距离
            selectTitle.setSpacingAfter(20);
            document.add(selectTitle);

            //加入选择题
            for(String select:selectList){
            	Paragraph sel = new Paragraph(select,FontChinese);
            	//设置段后距离
            	sel.setSpacingAfter(10);
            	document.add(sel);
            }

            //简答题标题
            Paragraph shortAnswerTitle = new Paragraph("三、简答题",FontChinese3);
            //设置段前距离
            shortAnswerTitle.setSpacingBefore(30);
            //设置段后距离
            shortAnswerTitle.setSpacingAfter(20);
            document.add(shortAnswerTitle);

            //加入简答题
            for(String shortAnswer:shortAnswerList){
            	Paragraph sa = new Paragraph(shortAnswer,FontChinese);
                //设置段后距离
                sa.setSpacingAfter(10);
            	document.add(sa);
            }


		} catch (DocumentException de) {
			System.err.println(de.getMessage());
			return null;
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			return null;
		}
		document.close();
		System.out.println("试卷打印成功");
		return filePath;
	}
}
