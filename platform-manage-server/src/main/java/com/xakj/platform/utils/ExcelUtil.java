package com.xakj.platform.utils;/*
package com.xakj.ant.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.extern.slf4j.Slf4j;

import com.github.abel533.easyxls.EasyXls;
import com.github.abel533.easyxls.bean.ExcelConfig;
import com.github.abel533.easyxls.bean.Field;
import com.github.abel533.easyxls.common.DateUtil;
import com.github.abel533.easyxls.common.FieldUtil;

*/
/**
 * @Description: TODO
 * @Author: Hardy
 * @DateTime: 2019/5/8 18:22
 * @Verstion 1.0
 *//*

@Slf4j
public class ExcelUtil {

    public static final String EXCEL = ".xls";
    */
/**
     * 导出excel下载文件的公用方法
     * @author zhaoy
     * @param config excel配置
     * @param list 载入的数据
     * @param downLoadPath 下载地址
     * @param fileName 文件名
     * @param response 响应
     * @return
     *//*

    public static boolean expotExcel(ExcelConfig config,List<?> list,String downLoadPath,String fileName,HttpServletResponse response){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File file = null;
        boolean success = false;
        try {
            EasyXls.list2Xls(config, list, downLoadPath, fileName);
            file = new File(downLoadPath + File.separator + fileName);
            long fileLength =file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            success = true;
        } catch (Exception e) {
            log.error("execute code occurred exception: ", e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if(file!=null){
                    file.delete();
                }
            } catch (IOException e) {
                log.error("execute code occurred exception: ", e);
            }
        }
        return success;
    }

    */
/**
     * 导出excel公用方法,支持同文件导出多个sheet
     * @author liuxiangfeng758
     * @param configs excel配置
     * @param list 载入的数据
     * @param fileName 文件名
     * @param response 响应
     * @return
     *//*

    public static boolean expotExcelWithMultipleSheets(List<ExcelConfig> configs,List<?> list,String filePath,String fileName,HttpServletResponse response){
        boolean success = false;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File file = null;
        try {
            list2Xls(configs, list, filePath, fileName);
            file = new File(filePath + File.separator + fileName);
            long fileLength =file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            success = true;
        } catch (Exception e) {
            log.error("execute code occurred exception: ", e);
        } finally {
            try {
                if (bis != null){
                    bis.close();
                }
                if (bos != null){
                    bos.close();
                }
                if(file!=null){
                    file.delete();
                }
            } catch (IOException e) {
                log.error("execute code occurred exception: ", e);
                return false;
            }
        }
        return success;
    }
    */
/**
     * 导出list对象到excel
     * @author liuxiangfeng758
     * @param list 导出的list
     * @param filePath 保存xls路径
     * @param fileName 保存xls文件名
     * @return 处理结果，true成功，false失败
     * @throws Exception
     *//*

    private static boolean list2Xls(List<ExcelConfig> configs, List<?> list, String filePath, String fileName) throws Exception {
//创建目录
        File file = new File(filePath);
        if (!(file.exists())) {
            if (!file.mkdirs()) {
                throw new RuntimeException("创建导出目录失败!");
            }
        }
        try (OutputStream outputStream = new FileOutputStream(filePath + "/" + fileName)) {
            if (!fileName.toLowerCase(Locale.SIMPLIFIED_CHINESE).endsWith(EXCEL)) {
                fileName += EXCEL;
            }
            return list2Xls(configs, list, outputStream);
        } catch (Exception e1) {
            return false;
        }
    }
    */
/**
     * 导出list对象到excel
     * @author liuxiangfeng758
     * @param outputStream 输出流
     * @return 处理结果，true成功，false失败
     * @throws Exception
     *//*

    @SuppressWarnings("unchecked")
    private static boolean list2Xls(List<ExcelConfig> configs, List<?> listParam, OutputStream outputStream) throws Exception {
        try {
            WritableWorkbook wb = Workbook.createWorkbook(outputStream);
            ExcelConfig config= null;
            List<Map<String,Object>> list = null;
            for (int i = 0;i < configs.size(); i ++)
            {
                config = configs.get(i);
                list = (List<Map<String,Object>>)listParam.get(i);
                String[] header = config.getHeaders();
                String[] names = config.getNames();
                String[] types = config.getTypes();
                String[] values;
                String sheetName = (config.getSheet() != null && !config.getSheet().equals("")) ? config.getSheet() : ("sheet" + config.getSheetNum());
                WritableSheet sheet = wb.createSheet(sheetName, config.getSheetNum());

                int row = 0;
                int column = 0;
                int rowadd = 0;
//写入标题
                if (config.getHeader()) {
                    for (column = 0; column < header.length; column++) {
                        sheet.addCell(new Label(column, row + rowadd, header[column]));
                        if (config.getColumn(column).getWidth() != null) {
                            sheet.setColumnView(column, config.getColumn(column).getWidth() / 7);
                        }
                    }
                    rowadd++;
                }
//写入内容//行
                for (row = 0; row < list.size(); row++) {
                    Object rowData = list.get(row);
                    values = getObjValues(rowData, names, types);
//列
                    for (column = 0; column < values.length; column++) {
                        sheet.addCell(new Label(column, row + rowadd, values[column]));
                    }
                }
            }
            wb.write();
            wb.close();
        } catch (Exception e1) {
            return false;
        }
        return true;
    }
    */
/**
     * 获取对象指定字段值
     *
     * @param source 对象
     * @param fieldnames 属性名数组
     * @return 对象的字符串数组，和属性名对应
     * @throws Exception
     *//*

    private static String[] getObjValues(Object source, String[] fieldnames,String[] types) throws Exception {
        String[] results = new String[fieldnames.length];
        Field field;
        String value;
        Object obj;
        for (int i = 0; i < fieldnames.length; i++) {
            field = FieldUtil.getField(source, fieldnames[i]);
            if (field == null) {
                continue;
            }
            obj = field.get(source);
            if (obj == null) {
                if("java.lang.String".equals(types[i]))
                {
                    value = "";
                }
                else
                {
                    value = "0";
                }
            } else if (obj instanceof Date) {
                value = DateUtil.smartFormat((Date) obj);
            } else {
                value = String.valueOf(obj);
            }
            results[i] = value;
        }
        return results;
    }
}
*/
