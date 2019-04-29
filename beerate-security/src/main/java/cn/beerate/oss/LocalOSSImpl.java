package cn.beerate.oss;

import cn.beerate.common.Message;
import cn.beerate.utils.PathUtil;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.UUID;

/**
 * 本地文件存储实现
 */
public class LocalOSSImpl implements OSS {


    private FileInfo getFileInfo(){
        //文件名
        String fileName = UUID.randomUUID().toString();
        return new FileInfo(null, fileName,PathUtil.getTempPath()+fileName,3600,null);
    }

    @Override
    public Message<FileInfo> uploadFile(File file) throws IOException{
        FileInfo fileInfo = getFileInfo();
        FileCopyUtils.copy(file, new File(getFileInfo().getUri()));

        return Message.success(fileInfo);
    }

    @Override
    public Message<FileInfo> uploadFile(InputStream in) throws IOException {
        FileInfo fileInfo = getFileInfo();
        FileCopyUtils.copy(in, new FileOutputStream(new File(fileInfo.getUri())));

        return Message.success(fileInfo);
    }

    @Override
    public Message<FileInfo> uploadFile(byte[] bytes) throws IOException{
        FileInfo fileInfo = getFileInfo();
        FileCopyUtils.copy(bytes,new File(fileInfo.getUri()));

        return Message.success(fileInfo);
    }

    @Override
    public Message<FileInfo> downLoadFile(String uri) {
        return null;
    }
}
