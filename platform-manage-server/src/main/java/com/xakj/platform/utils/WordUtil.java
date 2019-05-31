package com.xakj.platform.utils;

/**
 * @Description: TODO
 * @Author: Hardy
 * @DateTime: 2019/5/8 18:27
 * @Verstion 1.0
 */


import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
public final class WordUtil {
    private static Configuration configuration = null;

    private WordUtil() {
        throw new AssertionError();
    }

    /**
     * 根据模板生成相应的文件
     * @param root 保存数据的map
     * @param template 模板文件的地址
     * @param path 生成的word文档输出地址
     * @return
     */
    public static synchronized ByteArrayOutputStream process(Map<?, ?> root, String template) {

        if (null == root ) {
            throw new RuntimeException("数据不能为空");
        }

        if (null == template) {
            throw new RuntimeException("模板文件不能为空");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String templatePath = template.substring(template.lastIndexOf("/"));
        String templateName = template.substring(template.lastIndexOf("/") + 1, template.length());

        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);  // 这里Configurantion对象不能有两个，否则多线程访问会报错
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassicCompatible(true);
        }
      //  configuration.setClassForTemplateLoading(WordUtil.class, templatePath);
        System.out.println(WordUtil.class+templatePath);

        Template t = null;
        try {
            //Configuration cfg = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("E:\\tst"));

           // configuration.setServletContextForTemplateLoading(getServletContext(),"templates");
            t = configuration.getTemplate(templateName);
            Writer w = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            t.process(root, w);  // 这里w是一个输出地址，可以输出到任何位置，如控制台，网页等
            w.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return outputStream;
    }

}


//DownloadUtil.java


class DownloadUtil {

    /**
     * @param byteArrayOutputStream 将文件内容写入ByteArrayOutputStream
     * @param response HttpServletResponse  写入response
     * @param returnName 返回的文件名
     */
    public static void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String returnName) throws IOException {
        response.setContentType("application/msword");
        response.setHeader("Content-Disposition", "attachment; filename=" + returnName);
        response.setContentLength(byteArrayOutputStream.size());
        OutputStream outputstream = response.getOutputStream();         //取得输出流
        byteArrayOutputStream.writeTo(outputstream);                    //写到输出流
        byteArrayOutputStream.close();                                  //关闭
        outputstream.flush();                                           //刷数据
    }
}