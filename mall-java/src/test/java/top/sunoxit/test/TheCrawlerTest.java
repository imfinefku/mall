package top.sunoxit.test;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @program: school-java-mall
 * @description:
 * @author: hujie
 * @create: 2021-02-05 12:47
 **/
@Data
public class TheCrawlerTest {

    @Excel(name = "姓名1", width = 20)
    private String category_id;
    @Excel(name = "姓名2", width = 20)
    private String id;


    @Excel(name = "姓名3", width = 20)
    private String product_id;

    @Excel(name="姓名4" ,width = 20)
    private String product_intro;

    @Excel(name="姓名5" ,width = 20)
    private String product_name;

    @Excel(name="姓名6" ,width = 20)
    private String product_num;


    @Excel(name="姓名7" ,width = 20)
    private String product_picture;


    @Excel(name="姓名7" ,width = 20)
    private String product_price;


    @Excel(name="姓名8" ,width = 20)
    private String product_sales;

    @Excel(name="姓名9" ,width = 20)
    private String product_selling_price;

    @Excel(name="姓名10" ,width = 20)
    private String product_title;

}
