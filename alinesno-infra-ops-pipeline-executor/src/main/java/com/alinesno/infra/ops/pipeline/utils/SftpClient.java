package com.alinesno.infra.ops.pipeline.utils;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * SftpClient 类用于实现 SFTP 客户端功能。
 * 该类提供了连接、上传文件、创建目录等操作的方法。
 *
 * 作者：luoxiaodong
 * 版本：1.0.0
 */
public class SftpClient {
    private String host;
    private String username;
    private String password;
    private Session session;
    private ChannelSftp channelSftp;

    /**
     * 获取主机地址。
     *
     * @return 主机地址
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置主机地址。
     *
     * @param host 主机地址
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取用户名。
     *
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名。
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码。
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码。
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取会话对象。
     *
     * @return 会话对象
     */
    public Session getSession() {
        return session;
    }

    /**
     * 设置会话对象。
     *
     * @param session 会话对象
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 获取 SFTP 通道对象。
     *
     * @return SFTP 通道对象
     */
    public ChannelSftp getChannelSftp() {
        return channelSftp;
    }

    /**
     * 设置 SFTP 通道对象。
     *
     * @param channelSftp SFTP 通道对象
     */
    public void setChannelSftp(ChannelSftp channelSftp) {
        this.channelSftp = channelSftp;
    }

    /**
     * 构造方法，创建一个 SftpClient 实例。
     *
     * @param host 主机地址
     * @param username 用户名
     * @param password 密码
     */
    public SftpClient(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    /**
     * 连接到 SFTP 服务器。
     *
     * @throws JSchException 连接异常
     */
    public void connect() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        channelSftp = (ChannelSftp) channel;
    }

    /**
     * 上传文件到远程服务器。
     *
     * @param localFilePath 本地文件路径
     * @param remoteFilePath 远程文件路径
     * @throws SftpException SFTP 异常
     */
    public void uploadFile(String localFilePath, String remoteFilePath) throws SftpException {
        File uploadFile = new File(remoteFilePath) ;

        System.out.println(">>>>>>>>>> uploadPath = " + uploadFile.getParentFile().getAbsoluteFile() + " , --->>>> " + uploadFile.getName() + " , pwd = " + channelSftp.pwd());

        channelSftp.cd(channelSftp.pwd());
        channelSftp.put(localFilePath, uploadFile.getName());
    }

    /**
     * 断开与服务器的连接。
     */
    public void disconnect() {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * 在远程服务器上创建目录，支持多层目录。
     *
     * @param path 目录路径，例如 "a/b/c/d/e"
     * @throws SftpException SFTP 异常
     */
    public void mkdir(String path) throws SftpException {
        String[] directories = path.split("/");
        for (String directory : directories) {
            if (!directory.isEmpty()) {
                try {
                    channelSftp.cd(directory);
                } catch (SftpException e) {
                    channelSftp.mkdir(directory);
                    channelSftp.cd(directory);
                }
            }
        }
    }


    /**
     * 切换远程服务器的工作目录。
     *
     * @param path 目录路径
     * @throws SftpException SFTP 异常
     */
    public void cd(String path) throws SftpException {
        channelSftp.cd(path);
    }

    /**
     * 执行远程Shell命令，并返回执行结果和日志。
     *
     * @param command Shell命令
     * @return 执行结果和日志
     * @throws JSchException JSch异常
     * @throws IOException IO异常
     */
    public String executeShellCommand(String command) throws JSchException, IOException {
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        InputStream inputStream = channelExec.getInputStream();
        channelExec.connect();

        StringBuilder output = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.append(new String(buffer, 0, bytesRead));
        }

        channelExec.disconnect();
        return output.toString();
    }

}
