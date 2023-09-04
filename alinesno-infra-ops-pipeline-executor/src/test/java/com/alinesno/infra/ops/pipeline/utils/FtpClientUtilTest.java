package com.alinesno.infra.ops.pipeline.utils;

import com.alinesno.infra.ops.pipeline.exception.ExecutorServiceRuntimeException;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FtpClientUtilTest {

    @Mock
    private FTPClient ftpClientMock;

    private FtpClientUtil ftpClientUtil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @DisplayName("测试上传文件")
    @CsvSource({
            "localhost, 21, ftpuser, aip@local, /aa.jar , /Users/luodong/Desktop/aa.jar",
    })
    public void testUpload(String host, int port, String ftpuser, String password, String remoteFile, String localFile) throws Exception {
        ftpClientUtil = new FtpClientUtil(host, port, ftpuser, password);

        // 模拟FTPClient的方法
//        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
//        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
//        doNothing().when(ftpClientMock).enterLocalPassiveMode();
//        doNothing().when(ftpClientMock).setFileType(anyInt());
//        doNothing().when(ftpClientMock).storeFile(anyString(), any(InputStream.class));
//        doNothing().when(ftpClientMock).noop();
//        doNothing().when(ftpClientMock).logout();
//        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.upload(remoteFile, localFile);

        // 验证FTPClient的方法是否正确调用
//        verify(ftpClientMock).connect(anyString(), anyInt());
//        verify(ftpClientMock).getReplyCode();
//        verify(ftpClientMock).login(anyString(), anyString());
//        verify(ftpClientMock).enterLocalPassiveMode();
//        verify(ftpClientMock).setFileType(anyInt());
//        verify(ftpClientMock).storeFile(anyString(), any(InputStream.class));
//        verify(ftpClientMock).noop();
//        verify(ftpClientMock).logout();
//        verify(ftpClientMock).disconnect();
    }

    @Test
    @DisplayName("测试上传文件时抛出异常")
    public void testUpload_ThrowsException() throws Exception {
        // 模拟FTPClient的方法抛出异常
        doThrow(new Exception("Connection error")).when(ftpClientMock).connect(anyString(), anyInt());

        // 执行测试并验证是否抛出了期望的异常
        assertThrows(ExecutorServiceRuntimeException.class, () -> {
            ftpClientUtil.upload("remoteFileName", "localFileName");
        });

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verifyNoMoreInteractions(ftpClientMock);
    }

    @Test
    @DisplayName("测试下载文件")
    public void testDownload() throws Exception {
        // 模拟FTPClient的方法
        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
        doNothing().when(ftpClientMock).enterLocalPassiveMode();
        doNothing().when(ftpClientMock).setFileType(anyInt());
        doNothing().when(ftpClientMock).retrieveFile(anyString(), any(OutputStream.class));
        doNothing().when(ftpClientMock).noop();
        doNothing().when(ftpClientMock).logout();
        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.download("remoteFileName", "localFileName");

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verify(ftpClientMock).getReplyCode();
        verify(ftpClientMock).login(anyString(), anyString());
        verify(ftpClientMock).enterLocalPassiveMode();
        verify(ftpClientMock).setFileType(anyInt());
        verify(ftpClientMock).retrieveFile(anyString(), any(OutputStream.class));
        verify(ftpClientMock).noop();
        verify(ftpClientMock).logout();
        verify(ftpClientMock).disconnect();
    }

    @Test
    @DisplayName("测试创建目录")
    public void testMkdir() throws Exception {
        // 模拟FTPClient的方法
        doNothing().when(ftpClientMock).connect(anyString(), anyInt());
        when(ftpClientMock.login(anyString(), anyString())).thenReturn(true);
        doNothing().when(ftpClientMock).enterLocalPassiveMode();
        doNothing().when(ftpClientMock).setFileType(anyInt());
        doNothing().when(ftpClientMock).makeDirectory(anyString());
        doNothing().when(ftpClientMock).noop();
        doNothing().when(ftpClientMock).logout();
        doNothing().when(ftpClientMock).disconnect();

        // 执行测试
        ftpClientUtil.mkdir("remotePathName");

        // 验证FTPClient的方法是否正确调用
        verify(ftpClientMock).connect(anyString(), anyInt());
        verify(ftpClientMock).getReplyCode();
        verify(ftpClientMock).login(anyString(), anyString());
        verify(ftpClientMock).enterLocalPassiveMode();
        verify(ftpClientMock).setFileType(anyInt());
        verify(ftpClientMock).makeDirectory(anyString());
        verify(ftpClientMock).noop();
        verify(ftpClientMock).logout();
        verify(ftpClientMock).disconnect();
    }
}
