package com.atguigu.gulimall.thirdparty;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    public void fileUpload() throws ClientException, IOException {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String bucketName = "gulimall-zhuzhongtao";
        String objectName = "朱忠涛-简历.docx";
        String accessKeyId = "LTAI5t79zQBcvqYcEeuKZsqg";
        String secretAccessKey = "qdZy90xtgDs5g3QHxl47Lfca8GGYEy";
        OSS ossClient = (new OSSClientBuilder()).build(endpoint, accessKeyId, secretAccessKey);
        File file = new File("C:\\Users\\zzt\\Desktop\\需求修改记录\\朱忠涛-简历.docx");
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            ossClient.putObject(bucketName, objectName, fileInputStream);
        } catch (OSSException var15) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + var15.getErrorMessage());
            System.out.println("Error Code:" + var15.getErrorCode());
            System.out.println("Request ID:" + var15.getRequestId());
            System.out.println("Host ID:" + var15.getHostId());
        } catch (com.aliyun.oss.ClientException var16) {
            System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
            System.out.println("Error Message:" + var16.getMessage());
        } catch (FileNotFoundException var17) {
            var17.printStackTrace();
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
        this.ossClient.putObject(bucketName, "朱忠涛_个人简历1.xlsx", new FileInputStream("C:\\Users\\zzt\\Desktop\\需求修改记录\\朱忠涛_个人简历.xlsx"));

        ossClient.shutdown();
    }


}
