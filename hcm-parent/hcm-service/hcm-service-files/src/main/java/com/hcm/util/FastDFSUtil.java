package com.hcm.util;

import com.hcm.file.FastDFSFile;
import entity.Result;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 实现FastDFS文件管理
 * 文件上传
 * 文件删除
 * 文件下载
 * 文件信息获取
 * Storage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {
    /**
     * 加载Tracker链接信息
     */
    static {
        try {
            //查找classpath下的文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            //加载Tracker链接信息
            ClientGlobal.init(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        TrackerServer trackerServer = getTrackeServer();

        //通过TrackerServer的链接信息获取Storage的链接信息，创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //附件参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        //通过StorageClient访问Storage, 实现文件上传，获取文件上传后的信息
        /**
         * 1:上传文件的字节数组
         * 2:文件的扩展名,例如 JPG
         * 3:附加参数
         *
         * uploads[]
         *      uploads[0]:文件上传所存储的Storage的组的名字 group1
         *      uploads[1]:文件存储到Storage上的文件名字
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);

        return uploads;
    }

    /**
     * 获取文件信息
     * @param groupName:文件的组名 group1s
     * @param remoteFileName:文件的存储路径名字 MOO/00/00/xxx.jpg
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 文件下载
     * @param groupName:文件的组名 group1
     * @param remoteFileName:文件的存储路径名字 MOO/00/00/xxx.jpg
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);

        byte[] buffer = storageClient.download_file(groupName, remoteFileName);

        return new ByteArrayInputStream(buffer);
    }

    /**
     * 文件删除
     * @param groupName:文件的组名 group1
     * @param remoteFileName:文件的存储路径名字 MOO/00/00/xxx.jpg
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取Storage信息，创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        //删除文件
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取Storage信息
     */
    public static StorageServer getStorages() throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 获取Storage IP和端口信息
     */
    public static ServerInfo[] getServerInfo(String group1, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage IP和端口信息
        return trackerClient.getFetchStorages(trackerServer, group1, remoteFileName);
    }

    /**
     * 获取Tracker返回信息
     */
    public static String getTrackerInfo() throws Exception{
        TrackerServer trackerServer = getTrackeServer();
        //Tracker的IP,HTTP端口
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int tracker_http_port = ClientGlobal.getG_tracker_http_port();
        String url = "http://" + ip + ":" +tracker_http_port;
        return url;
    }

    /**
     * 获取TrackerServer
     */
    public static TrackerServer getTrackeServer() throws  Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对对象
        TrackerServer trackerServer = trackerClient.getConnection();

        return trackerServer;
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception{
//        //文件下载
//        InputStream is = downloadFile("group1", "M00/00/00/wKgf_l8oSC-ANOJuACCkbbzo0SU787.jpg");
//        // 将文件写入到本地磁盘
//        FileOutputStream os = new FileOutputStream("D:/test.jpg");
//        //定义一个缓冲区
//        byte[] buffer = new byte[1024];
//        while (is.read(buffer) != 1) {
//            os.write(buffer);
//        }
//        os.flush();
//        os.close();
//        is.close();
//        System.out.println("下载成功");
//
//        //文件删除
//        //deleteFile("group1", "M00/00/00/wKgf_l8oSC-ANOJuACCkbbzo0SU787.jpg");
//        //获取Storage信息
//        StorageServer storageServer = getStorages();
//        System.out.println(storageServer.getStorePathIndex());
//        System.out.println(storageServer.getInetSocketAddress().getHostString());//IP信息

        //获取Storage IP和端口信息
//        ServerInfo[] groups = getServerInfo("group1", "M00/00/00/wKgf_l8oSC-ANOJuACCkbbzo0SU787.jpg");
//        for (ServerInfo group : groups) {
//            System.out.println(group.getIpAddr());
//            System.out.println(group.getPort());
//        }
        //获取Tracker IP和端口信息
        System.out.println(getTrackerInfo());
    }
}
