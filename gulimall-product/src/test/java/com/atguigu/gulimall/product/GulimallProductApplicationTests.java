package com.atguigu.gulimall.product;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;
    @Autowired
    OSSClient ossClient;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("zzt");
        brandEntity.setLogo("朱忠涛");
        brandEntity.setDescript("朱氏描述");
        brandEntity.setShowStatus(1);
        brandEntity.setFirstLetter("z");
        brandEntity.setSort(1);
        brandService.save(brandEntity);
        System.out.println("123");
    }

    @Test
    public void fileUpload() throws com.aliyuncs.exceptions.ClientException, IOException {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "gulimall-zhuzhongtao";
        // 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
        String objectName = "朱忠涛-简历.docx";

        //1.用环境变量创建连接
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        //2.用账号密码创建连接
        String accessKeyId = "LTAI5t79zQBcvqYcEeuKZsqg";
        String secretAccessKey = "qdZy90xtgDs5g3QHxl47Lfca8GGYEy";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
        File file = new File("C:\\Users\\zzt\\Desktop\\需求修改记录\\朱忠涛-简历.docx");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            ossClient.putObject(bucketName, objectName, fileInputStream);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

    }

    @Test
    public void testFileUpload2() throws FileNotFoundException {
        String bucketName = "gulimall-zhuzhongtao";
        ossClient.putObject(bucketName,"朱忠涛_个人简历.xlsx",
                new FileInputStream("C:\\Users\\zzt\\Desktop\\需求修改记录\\朱忠涛_个人简历.xlsx"));
    }

}
