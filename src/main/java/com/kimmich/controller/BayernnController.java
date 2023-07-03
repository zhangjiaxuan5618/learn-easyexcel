package com.kimmich.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kimmich.domain.Bayernn;
import com.kimmich.service.IBayernnService;
import com.kimmich.util.DemoDataListener;
import com.kimmich.util.EasyExcelCustomCellWriteHandler;
import com.kimmich.util.ResultVO;
import com.kimmich.util.StyleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kim
 * @since 2023-06-29
 */
@RestController
@RequestMapping("/bayern")
public class BayernnController {

    @Autowired
    private IBayernnService bayernnService;


    @PostMapping("/QRCodeGenerator")
    public void QRCodeGenerator(){
        String data = "Hello, this is a QR code example!";
        String filePath = "path/to/outputQRCode.png";
        int width = 300;
        int height = 300;

        generateQRCode(data, filePath, width, height);
    }
    public static void generateQRCode(String data, String filePath, int width, int height) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            Path path = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR code generated successfully!");
        } catch (Exception e) {
            System.err.println("Error while generating QR code: " + e.getMessage());
        }
    }

    @PostMapping("/list")
    public void list(){
        List<Bayernn> list = new ArrayList<>();

        Bayernn bayernn = new Bayernn();
        bayernn.setId(77);
        bayernn.setName("25");
        bayernn.setAge(66);
        list.add(bayernn);
        Bayernn bayernn2 = new Bayernn();
        bayernn2.setId(11);
        bayernn2.setName("11");
        bayernn2.setAge(11);
        list.add(bayernn2);
        bayernnService.saveBatch(list);
    }


    @PostMapping("/listt")
    public void add(@RequestPart(value = "file") MultipartFile file){
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read((File) file, new DemoDataListener()).sheet().doRead();
//        List<Bayernn> userList = EasyExcel.read(new File(file)).head(Bayernn.class).sheet().doReadSync();
    }

    @PostMapping("/exportExcel")
    public void exportUserExcel(HttpServletResponse response){
        try {
            this.setExcelResponseProp(response, "拜仁慕尼黑");
            List<Bayernn> list = bayernnService.list();
            System.out.println("++++"+list);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(StyleUtils.getHeadStyle(), StyleUtils.getContentStyle());

            EasyExcel.write(response.getOutputStream())
                    .head(Bayernn.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("FCB")
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .registerWriteHandler(new EasyExcelCustomCellWriteHandler())
                    .doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/import/user")
    public void importUserExcel(@RequestPart(value = "file") MultipartFile file) {
//        DemoDataListener demoDataListener = new DemoDataListener();
        try {
            List<Bayernn> list = EasyExcel.read(file.getInputStream())
                    .head(Bayernn.class)
                    .sheet()
                    .doReadSync();
            bayernnService.saveOrUpdateBatch(list,1000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置响应结果
     *
     * @param response    响应结果对象
     * @param rawFileName 文件名
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    private void setExcelResponseProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

}

