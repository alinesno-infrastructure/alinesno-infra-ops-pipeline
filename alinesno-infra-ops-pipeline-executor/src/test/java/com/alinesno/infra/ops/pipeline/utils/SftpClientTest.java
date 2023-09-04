package com.alinesno.infra.ops.pipeline.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SftpClientTest {

    // SftpClient 实例
    private SftpClient sftpClient;

    @AfterEach
    public void cleanup() {
        // 在每个测试方法运行之后断开连接
        sftpClient.disconnect();
    }

    @Test
    @DisplayName("测试连接到 SFTP 服务器")
    public void testConnect() throws JSchException {
        // 调用连接方法
        sftpClient.connect();

        // 断言连接是否成功
        Assertions.assertTrue(sftpClient.getSession().isConnected());
        Assertions.assertTrue(sftpClient.getChannelSftp().isConnected());
    }

    @ParameterizedTest
    @DisplayName("测试上传文件到 SFTP 服务器")
    @CsvFileSource(files = "/Users/luodong/test-data/case/SftpClientTestsetUp.csv" , numLinesToSkip = 1)
    public void testUploadFile(String host , String username , String password) throws Exception {

        // 在每个测试方法运行之前创建 SftpClient 实例
        sftpClient = new SftpClient(host, username, password);

        // 创建一个临时文件，并写入一些内容
        File tempFile = createTempFileWithContent("Hello, SFTP!");

        // 连接到 SFTP 服务器
        sftpClient.connect();

        // 上传文件
        String REMOTE_FILE_PATH = "/root/to/remote/file_2.txt";
        sftpClient.mkdir(new File(REMOTE_FILE_PATH).getParent());

        sftpClient.uploadFile(tempFile.getAbsolutePath(), REMOTE_FILE_PATH);

        // 断言文件是否成功上传
        ChannelSftp channelSftp = sftpClient.getChannelSftp();
        SftpATTRS remoteFileAttrs = channelSftp.stat(REMOTE_FILE_PATH);
        assertNotNull(remoteFileAttrs);
        Assertions.assertEquals(tempFile.length(), remoteFileAttrs.getSize());

        // 清理临时文件
        tempFile.delete();
    }

    @ParameterizedTest
    @DisplayName("测试执行Shell命令")
    @CsvFileSource(files = "/Users/luodong/test-data/case/SftpClientTestsetUp.csv" , numLinesToSkip = 1)
    public void testExecuteShellCommand(String host , String username , String password) throws JSchException, IOException, SftpException {
        // 在每个测试方法运行之前创建 SftpClient 实例
        sftpClient = new SftpClient(host, username, password);

        // 连接到 SFTP 服务器
        sftpClient.connect();

        // 运行命令
        String command = """
                export PROJECT=dubbo
                rm -rf dubbo \s
                echo 'PROJECT = ${ PROJECT }'
                git clone https://github.com/apache/dubbo.git \s
                cd dubbo \s
                /root/apache-maven-3.9.4/bin/mvn clean package\s
                """;

        command = """
                #!/bin/bash
                export PROJECT=dubbo
                echo "_PROJECT = ${PROJECT} __ ".${PROJECT}
                """;
        String result = sftpClient.executeShellCommand(command) ;
        System.out.println(result);

        sftpClient.disconnect();
        assertNotNull(result , "消息返回内容为空") ;
    }

    @Test
    @DisplayName("测试断开与 SFTP 服务器的连接")
    public void testDisconnect() throws JSchException {
        // 连接到 SFTP 服务器
        sftpClient.connect();

        // 断开连接
        sftpClient.disconnect();

        // 断言连接是否已断开
        Assertions.assertFalse(sftpClient.getSession().isConnected());
        Assertions.assertFalse(sftpClient.getChannelSftp().isConnected());
    }

    // 创建一个包含指定内容的临时文件
    private File createTempFileWithContent(String content) throws Exception {
        Path tempFilePath = Files.createTempFile("test", ".txt");
        Files.write(tempFilePath, content.getBytes(), StandardOpenOption.WRITE);
        return tempFilePath.toFile();
    }
}
