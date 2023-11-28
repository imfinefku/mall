package top.sunoxit.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.school.mall.common.result.ConstantsFields;
import com.school.mall.common.utils.ExcelExportMyStylerImpl;
//import com.school.mall.common.utils.MyExcelExportUtil;
import com.school.mall.common.utils.SnowflakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;


//@RunWith(value = SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes={SchoolMallApplication.class})
    @Slf4j
public class MainAoo {


    @Test
    public void crawler() {
        String body = HttpRequest.post("http://106.15.179.105/api/product/getAllProduct").body("categoryID: []\n" +
                "currentPage: 1\n" +
                "pageSize: 50").execute().body();
        String product = JSONUtil.parseObj(body).get("Product").toString();

       List<TheCrawlerTest> theCrawlerTests = new ArrayList<>();
        Map<String, String> headers=new HashMap<>();
        headers.put("Accept"," application/json, text/plain, */*");
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Accept-Language","zh-CN,zh;q=0.9");
        headers.put("Connection","keep-alive");
        headers.put("Content-Length","17");
        headers.put("Content-Type","application/json;charset=UTF-8");
        headers.put("Host","106.15.179.105");
        headers.put("Origin","http://106.15.179.105");
        headers.put("Referer","http://106.15.179.105/");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");





        JSONArray objects = JSONUtil.parseArray(product);
        List<List<String>> strings = new ArrayList<>();
        List<List<String>> stringsMain = new ArrayList<>();
        for (int i = 0; i < objects.size();i++) {
            TheCrawlerTest theCrawlerTest = objects.get(i, TheCrawlerTest.class);
            theCrawlerTest.setId(SnowflakeUtil.randomLong()+"");
            String s = System.getProperty("user.dir") + "/src/main/resources/img/" + theCrawlerTest.getId();
            String bodyTow = HttpRequest.post("http://106.15.179.105/api/product/getDetailsPicture").headerMap(headers,true).body("{\"productID\": "+theCrawlerTest.getProduct_id()+"}").execute().body();
            JSONArray productPicture = (JSONArray) JSONUtil.parseObj(bodyTow).get("ProductPicture");
            FileUtil.mkdir(s);
            String product_picture1 = theCrawlerTest.getProduct_picture();
            HttpUtil.downloadFile("http://47.115.85.237:3000/"+product_picture1, FileUtil.file(s));
            stringsMain.add(Arrays.asList(ConstantsFields.BASEPIC+ConstantsFields.SURROUNDING_BUSINESS_IMG_PATH+"/"+theCrawlerTest.getId()
                    +product_picture1.substring(product_picture1.lastIndexOf("/")),theCrawlerTest.getId()
            ));
            for (Object o : productPicture) {

                String product_picture = (String)((JSONObject) o).get("product_picture");
                HttpUtil.downloadFile("http://47.115.85.237:3000/"+product_picture, FileUtil.file(s));


               strings.add(Arrays.asList(ConstantsFields.BASEPIC+ConstantsFields.SURROUNDING_BUSINESS_IMG_PATH+"/"+theCrawlerTest.getId()
                        +product_picture.substring(product_picture.lastIndexOf("/")),theCrawlerTest.getId()
                ));

            }

            log.info("第"+theCrawlerTest.getProduct_id()+"下载完成");
            theCrawlerTests.add(theCrawlerTest);
        }
        ExcelWriter writer = ExcelUtil.getWriter(System.getProperty("user.dir") + "/src/main/resources/img/"+"/writeBeanTest.xlsx");
        writer.write(strings, true);
        //关闭writer，释放内存
        writer.close();


        ExcelWriter writerMain = ExcelUtil.getWriter(System.getProperty("user.dir") + "/src/main/resources/img/"+"/writeBeanTestMain.xlsx");
        writerMain.write(stringsMain, true);
        //关闭writer，释放内存
        writerMain.close();

        exportExcel(theCrawlerTests,TheCrawlerTest.class,"测试数据","测试数据",System.getProperty("user.dir") + "/src/main/resources/img/exportCompanyImg.xls");
    }

    public static void exportExcel(Collection<?> listData, Class<?> pojoClass, String headTitle, String sheetName,String path) {
        ExportParams params = new ExportParams(headTitle, sheetName);
        params.setHeight((short) 8);
        params.setStyle(ExcelExportMyStylerImpl.class);
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, listData);
            String fileName = headTitle + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            OutputStream out=new FileOutputStream(path);

            workbook.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void s2(){
        File[] ls = FileUtil.ls("E:\\I\\home\\data\\schoolMall\\MALL_GOODS_IMG");
        for (File l : ls) {
            File[] files = l.listFiles();
            for (File file : files) {
                FileUtil.rename(file,file.getName().replace("%20"," "),false, true);
            }
        }


    }


    public static void main(String[] args) {
        String imgUrl = "http://127.0.0.1:8001/schoolMall/MALL_GOODS_IMG/1348882114184855554/1358252157281857536/d005fbdf0ea04f6a8576fa049d94867320210208.jpg";
        String i=imgUrl.contains("http://")?"http://":"https://";
        String substring =imgUrl.substring(imgUrl.indexOf(i) + i.length());
        String substringAll =substring.substring(substring.indexOf("/"));
        System.out.println(substringAll);
    }
}
