package com.service.service.utils;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.service.service.service.converter.JsonConverter;
import java.io.InputStream;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class QiniuUtils {

  @Value("application.qiniu.ak")
  private String ACCESS_KEY;

  @Value("application.qiniu.ak")
  private String SECRET_KEY;

  @Value("application.qiniu.bucket")
  private String BUCKET;

  @Value("application.qiniu.path")
  private String PATH;

  /** 将图片上传到七牛云 */
  public String uploadImg(InputStream stream) {
    // 构造一个带指定Zone对象的配置类
    Configuration cfg = new Configuration(Zone.zone2());
    // ...其他参数参考类注释
    UploadManager uploadManager = new UploadManager(cfg);
    // ...生成上传凭证，然后准备上传
    // 默认不指定key的情况下，以文件内容的hash值作为文件名
    try {
      Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
      String upToken = auth.uploadToken(BUCKET);

      Response response = uploadManager.put(stream, null, upToken, null, null);
      // 解析上传成功的结果
      DefaultPutRet putRet = JsonConverter.readJSON(response.bodyString(), DefaultPutRet.class);
      return PATH + "/" + (putRet != null ? putRet.key : null);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
