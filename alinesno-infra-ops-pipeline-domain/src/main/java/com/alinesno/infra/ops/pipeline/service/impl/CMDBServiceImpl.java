package com.alinesno.infra.ops.pipeline.service.impl;

import com.alinesno.infra.ops.pipeline.entity.ServerEntity;
import com.alinesno.infra.ops.pipeline.service.ICMDBService;
import com.alinesno.infra.ops.pipeline.service.IServerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CMDBServiceImpl 类是 ICMDBService 接口的实现类。
 * 该类用于分发服务器密钥。
 *
 * @author luoxiaodong
 * @version  1.0.0
 */
@Service
public class CMDBServiceImpl implements ICMDBService {

    private static final Logger log = LoggerFactory.getLogger(CMDBServiceImpl.class);

    @Autowired
    private IServerService serverService ;

    /**
     * distributeServerKeys 方法用于分发服务器密钥。
     *
     * @param csvFilePath CSV 文件的路径
     * @return 分发是否成功的布尔值
     */
    @Override
    public boolean distributeServerKeys(String csvFilePath) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            // 读取 CSV 文件
            List<ServerInfo> serverList = readServerListFromCSV(csvFilePath);
            if (serverList.isEmpty()) {
                log.error("Server list is empty");
                return false;
            }

            for (ServerInfo serverInfo : serverList) {
                // 创建 SSH 会话
                session = jsch.getSession(serverInfo.getUsername(), serverInfo.getHostname(), serverInfo.getSshPort());

                // 设置登录密码（可选，如果使用密钥登录，则不需要设置密码）
                session.setPassword(serverInfo.getPassword());

                // 关闭主机密钥检查（可选）
                session.setConfig("StrictHostKeyChecking", "no");

                // 连接到远程主机
                session.connect();

                // 打开 SFTP 通道
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();

                // 在此处使用 channelSftp.put() 方法将密钥文件上传到服务器
            }

            return true;
        } catch (JSchException e) {
            log.error("Failed to distribute server keys: {}", e.getMessage());
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭 SFTP 通道和 SSH 会话
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    @Override
    public List<ServerEntity> queryServerByTag(String tag) {
        LambdaQueryWrapper<ServerEntity> queryWrapper = new LambdaQueryWrapper<>() ;
        return serverService.list(queryWrapper.eq(ServerEntity::getFieldProp , tag)) ;
    }

    /**
     * readServerListFromCSV 方法从 CSV 文件中读取服务器列表。
     *
     * @param csvFilePath CSV 文件的路径
     * @return 服务器列表
     * @throws IOException 如果读取文件时发生错误
     */
    List<ServerInfo> readServerListFromCSV(String csvFilePath) throws IOException {
        List<ServerInfo> serverList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 5) {
                    String hostname = fields[0].trim();
                    String ip = fields[1].trim();
                    String username = fields[2].trim();
                    String password = fields[3].trim();
                    int sshPort = Integer.parseInt(fields[4].trim());
                    serverList.add(new ServerInfo(hostname, ip, username, password, sshPort));
                }
            }
        }

        return serverList;
    }

    /**
     * ServerInfo 类是一个内部类，用于封装服务器信息。
     */
    static class ServerInfo {
        private String hostname;
        private String ip;
        private String username;
        private String password;
        private int sshPort;

        /**
         * ServerInfo 的构造函数。
         *
         * @param hostname 服务器主机名
         * @param ip 服务器 IP 地址
         * @param username 登录用户名
         * @param password 登录密码
         * @param sshPort SSH 端口号
         */
        public ServerInfo(String hostname, String ip, String username, String password, int sshPort) {
            this.hostname = hostname;
            this.ip = ip;
            this.username = username;
            this.password = password;
            this.sshPort = sshPort;
        }

        // Getters and setters

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSshPort() {
            return sshPort;
        }

        public void setSshPort(int sshPort) {
            this.sshPort = sshPort;
        }
    }
}
