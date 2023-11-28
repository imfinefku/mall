//package top.sunoxit.generate;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.school.mall.common.utils.MD5Util;
//
//import java.io.IOException;
//
///**
// * @program: school-java-mall
// * @description: 代码生成器
// * @author: hujie
// * @create: 2021-01-11 12:58
// **/
//public class MysqlCodeGenerator {
//
//    public static void main(String[] args) throws IOException {
//        String[] arr={"goods_send_user"};
//
//        //参数配置
//        ConfigBean configBean = ConfigBean.getConfigBean().setAuthor("hujie")
//                .setSqlSetDriverName("com.mysql.cj.jdbc.Driver")
//                .setSqlSetPassword("root")
//                .setSqlSetUrl("jdbc:mysql://127.0.0.1:3306/school_mall?serverTimezone=UTC")
//                .setSqlSetUsername("root")
//                //包名
//                .setDefaultParentPack("com.school.mall")
//                .setModuleName("goods")
//                .setSwagger2(true)
//                .setIdType(IdType.ASSIGN_ID)
//                .setTableToBeProduced(arr);
//
//        //基本配置
//        SystemConfig systemConfig = new SystemConfig();
//        ReturnParameter returnParameter = systemConfig.initSystemConfig(configBean);
//        systemConfig.runConfig(returnParameter);
//
//
//        //生成
//        QueryVoGenerate queryVoGenerate = new QueryVoGenerate();
//        queryVoGenerate.produceViewObject(returnParameter);
//
//        //生成vue页面
//        VueGenerate vueGenerate = new VueGenerate();
//        vueGenerate.produceViewObject(returnParameter);
//
//        //生成统一结果集合以及全局异常处理
////        CommonGenerate commonGenerate = new CommonGenerate();
////        commonGenerate.produceViewObject(returnParameter);
////        commonGenerate.produceViewObjectR(returnParameter);
//
//        //生成跨域配置
////        GlobalCorsGenerate globalCorsGenerate = new GlobalCorsGenerate("token", "Token", "x-auth-token", "x-total-count");
////        globalCorsGenerate.produceViewObject(returnParameter);
//
//        //生成启动类
////        ApplicationGenerator applicationGenerator=new ApplicationGenerator("SchoolMall");
////        applicationGenerator.produceViewObject(returnParameter);
//
////        ApplicationPropeGenerator applicationPropeGenerator=new ApplicationPropeGenerator("8001");
////        applicationPropeGenerator.produceViewObject(returnParameter);
//
////        Swagger2Generate swagger2Generate=new Swagger2Generate(new SwaggerConfigEntity());
////        swagger2Generate.produceViewObject(returnParameter);
//    }
////    public static void main(String[] args) {
////        System.out.println(MD5Util.encryptionPass("123456"));
////    }
//
////    public static void main(String[] args) {
////        System.out.println( MD5Util.encryptionPass("123456"));
////    }
//
//}
