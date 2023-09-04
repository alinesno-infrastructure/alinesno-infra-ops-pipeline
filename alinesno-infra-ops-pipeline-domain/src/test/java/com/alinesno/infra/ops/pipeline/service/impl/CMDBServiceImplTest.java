package com.alinesno.infra.ops.pipeline.service.impl;

import com.jcraft.jsch.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 单元测试类：CMDBServiceImpl
 */
@DisplayName("CMDBServiceImpl 单元测试")
class CMDBServiceImplTest {

    @Test
    @DisplayName("分发服务器密钥 - 成功分发密钥")
    void distributeServerKeys_SuccessfullyDistributesKeys() throws JSchException, SftpException, IOException {
        // 创建 CMDBServiceImpl 实例
        CMDBServiceImpl cmdbService = new CMDBServiceImpl();

        // 创建 Mock 对象并设置预期行为
        JSch jschMock = mock(JSch.class);
        Session sessionMock = mock(Session.class);
        ChannelSftp channelSftpMock = mock(ChannelSftp.class);
        doNothing().when(channelSftpMock).connect();
        when(sessionMock.openChannel("sftp")).thenReturn(channelSftpMock);
        when(jschMock.getSession(anyString(), anyString(), anyInt())).thenReturn(sessionMock);

        // 将 Mock 对象注入到 CMDBServiceImpl 中
        // cmdbService.setJsch(jschMock);

        // 创建临时的 CSV 文件用于测试
        String csvFilePath = createTempCSVFile();

        // 执行分发服务器密钥操作
        boolean result = cmdbService.distributeServerKeys(csvFilePath);

        // 验证预期行为
        verify(jschMock, times(2)).getSession(anyString(), anyString(), anyInt());
        verify(channelSftpMock, times(2)).connect();

        // 验证结果是否符合预期
        assertTrue(result);

        // 删除临时的 CSV 文件
        deleteTempCSVFile(csvFilePath);
    }

    @Test
    @DisplayName("分发服务器密钥 - 读取的服务器列表为空")
    void distributeServerKeys_ServerListIsEmpty() throws JSchException, SftpException, IOException {
        // 创建 CMDBServiceImpl 实例
        CMDBServiceImpl cmdbService = new CMDBServiceImpl();

        // 创建 Mock 对象并设置预期行为
        JSch jschMock = mock(JSch.class);
        Session sessionMock = mock(Session.class);
        ChannelSftp channelSftpMock = mock(ChannelSftp.class);
        doNothing().when(channelSftpMock).connect();
        when(sessionMock.openChannel("sftp")).thenReturn(channelSftpMock);
        when(jschMock.getSession(anyString(), anyString(), anyInt())).thenReturn(sessionMock);

        // 将 Mock 对象注入到 CMDBServiceImpl 中
        // cmdbService.setJsch(jschMock);

        // 创建临时的空 CSV 文件用于测试
        String csvFilePath = createEmptyTempCSVFile();

        // 执行分发服务器密钥操作
        boolean result = cmdbService.distributeServerKeys(csvFilePath);

        // 验证预期行为
        verify(jschMock, never()).getSession(anyString(), anyString(), anyInt());

        // 验证结果是否符合预期
        assertFalse(result);

        // 删除临时的 CSV 文件
        deleteTempCSVFile(csvFilePath);
    }

    @Test
    @DisplayName("读取服务器列表从CSV文件 - 成功读取服务器列表")
    void readServerListFromCSV_SuccessfullyReadsServerList() throws IOException {
        // 创建 CMDBServiceImpl 实例
        CMDBServiceImpl cmdbService = new CMDBServiceImpl();

        // 创建临时的 CSV 文件用于测试
        String csvFilePath = createTempCSVFile();

        // 执行读取服务器列表操作
        List<CMDBServiceImpl.ServerInfo> serverList = cmdbService.readServerListFromCSV(csvFilePath);

        // 验证结果是否符合预期
        assertEquals(2, serverList.size());
        CMDBServiceImpl.ServerInfo server1 = serverList.get(0);
        assertEquals("hostname1", server1.getHostname());
        assertEquals("ip1", server1.getIp());
        assertEquals("username1", server1.getUsername());
        assertEquals("password1", server1.getPassword());
        assertEquals(22, server1.getSshPort());
        CMDBServiceImpl.ServerInfo server2 = serverList.get(1);
        assertEquals("hostname2", server2.getHostname());
        assertEquals("ip2", server2.getIp());
        assertEquals("username2", server2.getUsername());
        assertEquals("password2", server2.getPassword());
        assertEquals(2222, server2.getSshPort());

        // 删除临时的 CSV 文件
        deleteTempCSVFile(csvFilePath);
    }

    // 创建临时的 CSV 文件
    private String createTempCSVFile() throws IOException {
        String csvFilePath = "temp.csv";
        String csvContent = "hostname1,ip1,username1,password1,22\n" +
                "hostname2,ip2,username2,password2,2222";
        Files.write(Paths.get(csvFilePath), csvContent.getBytes());
        return csvFilePath;
    }

    // 创建临时的空 CSV 文件
    private String createEmptyTempCSVFile() throws IOException {
        String csvFilePath = "temp.csv";
        Files.createFile(Paths.get(csvFilePath));
        return csvFilePath;
    }

    // 删除临时的 CSV 文件
    private void deleteTempCSVFile(String csvFilePath) throws IOException {
        Files.deleteIfExists(Paths.get(csvFilePath));
    }
}

