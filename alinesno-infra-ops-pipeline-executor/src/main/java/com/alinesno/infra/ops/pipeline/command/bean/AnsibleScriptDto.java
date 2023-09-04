package com.alinesno.infra.ops.pipeline.command.bean;

/**
 * 执行脚本
 *
 * @author luoxiaodong
 * @version 1.0.0
 *
 */
public class AnsibleScriptDto extends ScriptDto {

	private boolean colorized; // true
	private String installation; // 'ansible'
	private String inventory; // 'hosts'
	private String playbook; // 'jdk1.8/jdk_install_test.yml'
	private String extras; // '--extra-vars env=${env}'
	private String sudoUser; // null
	private String jobWorkspace ; // 任务运行空间
	
	private String runLogger ; // 运行日志

	public String getRunLogger() {
		return runLogger;
	}

	public void setRunLogger(String runLogger) {
		this.runLogger = runLogger;
	}

	public String getJobWorkspace() {
		return jobWorkspace;
	}

	public void setJobWorkspace(String jobWorkspace) {
		this.jobWorkspace = jobWorkspace;
	}

	public boolean isColorized() {
		return colorized;
	}

	public void setColorized(boolean colorized) {
		this.colorized = colorized;
	}

	public String getInstallation() {
		return installation;
	}

	public void setInstallation(String installation) {
		this.installation = installation;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public String getPlaybook() {
		return playbook;
	}

	public void setPlaybook(String playbook) {
		this.playbook = playbook;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public String getSudoUser() {
		return sudoUser;
	}

	public void setSudoUser(String sudoUser) {
		this.sudoUser = sudoUser;
	}

	@Override
	public String toString() {
		return "AnsibleScriptDto [colorized=" + colorized + ", installation=" + installation + ", inventory="
				+ inventory + ", playbook=" + playbook + ", extras=" + extras + ", sudoUser=" + sudoUser
				+ ", jobWorkspace=" + jobWorkspace + "]";
	}

}
