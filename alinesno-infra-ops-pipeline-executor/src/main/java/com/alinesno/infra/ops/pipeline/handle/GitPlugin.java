package com.alinesno.infra.ops.pipeline.handle;

import com.alinesno.infra.ops.pipeline.AbstractExecutor;
import com.alinesno.infra.ops.pipeline.dto.ExecutorScriptDto;
import com.alinesno.infra.ops.pipeline.utils.AttributeUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * GitPlugin 类是 AbstractExecutor 的子类，用于执行 Git 相关操作。
 * 该类实现了 run 方法，用于执行 Git 相关操作的逻辑。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class GitPlugin extends AbstractExecutor {

    private static final Logger log = LoggerFactory.getLogger(GitPlugin.class);

    protected static final String PROP_REPOSITORY_URL = "git-repository";
    protected static final String PROP_LOCAL_PATH= "local-path";
    protected static final String PROP_GITHUB_TOKEN = "github-token";

    /**
     * run 方法用于执行 Git 相关操作。
     *
     * @param executorScriptDto 执行器脚本DTO对象
     * @param contextMap 上下文Map对象
     */
    @Override
    public void run(ExecutorScriptDto executorScriptDto, Map<String, Object> contextMap) {
        Map<String, Object> attrs = AttributeUtils.convertAttributesToMap(executorScriptDto.getAttributes());

        String repositoryUrl = (String) attrs.get(PROP_REPOSITORY_URL);
        String localPath = (String) attrs.get(PROP_LOCAL_PATH);
        String username = (String) attrs.get(PROP_USERNAME);
        String password = (String) attrs.get(PROP_PASSWORD);
        String token = (String) attrs.get(PROP_GITHUB_TOKEN);

        String serverId = (String) attrs.get(PROP_SERVER_ID);
        findHostKey(serverId , repositoryUrl , username , password);

        Git git = null;

        try {
            // 使用凭据克隆仓库
            CredentialsProvider credentialsProvider = createCredentialsProvider(username, password, token);
            git = cloneRepository(repositoryUrl, localPath, credentialsProvider);

            // 更新上下文Map，设置成功状态
            contextMap.put("gitStatus", "success");
        } catch (Exception e) {
            // 处理异常并更新上下文Map，设置错误状态
            contextMap.put("gitStatus", "error");
            contextMap.put("errorMessage", e.getMessage());
        } finally {
            if (git != null) {
                git.close();
            }
        }
    }

    Git cloneRepository(String repositoryUrl, String localPath, CredentialsProvider credentialsProvider) throws GitAPIException {

        // TODO 待优化，后面判断并使用pull
        return Git.cloneRepository()
                .setURI(repositoryUrl)
                .setDirectory(new File(localPath))
                .setCredentialsProvider(credentialsProvider)
                .call();
    }

    void createBranch(Git git, String branchName) throws GitAPIException {
        git.checkout()
                .setCreateBranch(true)
                .setName(branchName)
                .call();
    }

    void createTags(Git git, String tagName) throws GitAPIException {
        git.tag()
                .setName(tagName)
                .call();
    }

    void uploadChanges(Git git, CredentialsProvider credentialsProvider) throws GitAPIException {
        Iterable<PushResult> results = git.push()
                .setCredentialsProvider(credentialsProvider)
                .setRemote("origin")
                .call();

        // 如果需要，处理推送结果
    }

    public CredentialsProvider createCredentialsProvider(String username, String password, String token) {
        if (token != null && !token.isEmpty()) {
//            return new OAuth2CredentialsProvider(token);
            return null ;
        } else {
            return new UsernamePasswordCredentialsProvider(username, password);
        }
    }
}
