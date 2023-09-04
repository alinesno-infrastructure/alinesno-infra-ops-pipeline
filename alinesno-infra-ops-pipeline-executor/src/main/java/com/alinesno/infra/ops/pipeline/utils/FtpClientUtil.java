package com.alinesno.infra.ops.pipeline.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;

import com.alinesno.infra.ops.pipeline.exception.ExecutorServiceRuntimeException;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP服务器工具类，用于与FTP服务器进行交互。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class FtpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(FtpClientUtil.class) ;

    private final String host;  // FTP服务器地址
    private final int port;  // 端口
    private final String username;  // 用户名
    private final String password;  // 密码

    /**
     * 构造函数，用于初始化FTP服务器地址、端口、用户名和密码。
     *
     * @param host   FTP服务器地址
     * @param username 用户名
     * @param password 密码
     */
    public FtpClientUtil(String host, String username, String password) {
        this.host = host;
        this.port = 21 ;
        this.username = username;
        this.password = password;
    }

    /**
     * 构造函数，用于初始化FTP服务器地址、端口、用户名和密码。
     *
     * @param host   FTP服务器地址
     * @param port     端口号
     * @param username 用户名
     * @param password 密码
     */
    public FtpClientUtil(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * 上传文件到FTP服务器。
     *
     * @param remoteFileName 远程文件名
     * @param localFileName  本地文件名
     */
    public void upload(String remoteFileName, String localFileName) {
        FTPClient ftp = null;
        try {
            ftp = buildFtpClient();
            connect(ftp);
            uploadFile(ftp, remoteFileName, localFileName);
            disconnect(ftp);
        } catch (Exception e) {
            throw new ExecutorServiceRuntimeException(e.getMessage());
        } finally {
            disconnectFtp(ftp);
        }
    }

    /**
     * 从FTP服务器下载文件。
     *
     * @param remoteFileName 远程文件名
     * @param localFileName  本地文件名
     */
    public void download(String remoteFileName, String localFileName) {
        FTPClient ftp = null;
        try {
            ftp = buildFtpClient();
            connect(ftp);
            downloadFile(ftp, remoteFileName, localFileName);
            disconnect(ftp);
        } catch (Exception e) {
            throw new ExecutorServiceRuntimeException(e.getMessage());
        } finally {
            disconnectFtp(ftp);
        }
    }

    /**
     * 在FTP服务器上创建目录。
     *
     * @param remotePathName 远程目录名
     */
    public void mkdir(String remotePathName) {
        FTPClient ftp = null;
        try {
            ftp = buildFtpClient();
            connect(ftp);
            buildDirectory(ftp, remotePathName);
            disconnect(ftp);
        } catch (Exception e) {
            throw new ExecutorServiceRuntimeException(e.getMessage());
        } finally {
            disconnectFtp(ftp);
        }
    }

    /**
     * 创建一个FTPClient实例，并添加用于调试的命令监听器。
     *
     * @return FTPClient实例
     */
    private FTPClient buildFtpClient() {
        FTPClient ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
        return ftp;
    }

    /**
     * 连接到FTP服务器。
     *
     * @param ftp FTPClient实例
     * @throws Exception 如果连接过程中出现错误
     */
    private void connect(FTPClient ftp) throws Exception {
        ftp.connect(host, port);
        int reply = ftp.getReplyCode();

        ftp.enterLocalPassiveMode();
        ftp.setFileType(FTP.BINARY_FILE_TYPE);

        if (!FTPReply.isPositiveCompletion(reply)) {
            throw new ConnectException(host + " Connect Exception");
        }else if (!ftp.login(username, password)) {
            throw new ConnectException("用户名或密码错误");
        }
    }

    /**
     * 上传文件到FTP服务器。
     *
     * @param ftp            FTPClient实例
     * @param remoteFileName 远程文件名
     * @param localFileName  本地文件名
     * @throws Exception 如果上传过程中出现错误
     */
    private void uploadFile(FTPClient ftp, String remoteFileName, String localFileName) throws Exception {
        try (InputStream input = new FileInputStream(localFileName)) {
            ftp.storeFile(remoteFileName, input);
        }
    }

    /**
     * 从FTP服务器下载文件。
     *
     * @param ftp            FTPClient实例
     * @param remoteFileName 远程文件名
     * @param localFileName  本地文件名
     * @throws Exception 如果下载过程中出现错误
     */
    private void downloadFile(FTPClient ftp, String remoteFileName, String localFileName) throws Exception {
        try (OutputStream output = new FileOutputStream(localFileName)) {
            ftp.retrieveFile(remoteFileName, output);
        }
    }

    /**
     * 在FTP服务器上创建目录。
     *
     * @param ftp             FTPClient实例
     * @param remotePathName 远程目录名
     * @throws Exception 如果创建目录过程中出现错误
     */
    private void buildDirectory(FTPClient ftp, String remotePathName) throws Exception {
        ftp.makeDirectory(remotePathName);
    }

    /**
     * 登出并断开与FTP服务器的连接。
     *
     * @param ftp FTPClient实例
     * @throws Exception 如果登出或断开连接过程中出现错误
     */
    private void disconnect(FTPClient ftp) throws Exception {
        ftp.noop();
        ftp.disconnect();
    }

    /**
     * 断开与FTP服务器的连接。
     *
     * @param ftp FTPClient实例
     */
    private void disconnectFtp(FTPClient ftp) {
        if (ftp != null && ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                // 忽略异常
                log.warn("service error");
            }
        }
    }
}
