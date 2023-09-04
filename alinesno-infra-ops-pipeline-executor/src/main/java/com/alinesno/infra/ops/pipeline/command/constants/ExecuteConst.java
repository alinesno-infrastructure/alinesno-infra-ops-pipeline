package com.alinesno.infra.ops.pipeline.command.constants;

/**
 * 常用变量
 * @author luoxiaodong
 * @version 1.0.0
 */
public interface ExecuteConst {
	
	String HOME_PATH = ".aip-operation" ;
	
	/**
	 * 每个运行任务的相关信息
	 */
	String running_logger_fileName = "running.log" ;  // 每个任务运行的日志信息
	
	String running_script_groovy_fileName = "job_script.groovy" ;  // 每个任务运行的日志信息
	String running_script_shell_fileName = "job_shell.sh" ;  // 每个任务运行的日志信息
	String running_script_python_fileName = "job_python.py" ;  // 每个任务运行的日志信息
	String running_script_ansible_fileName = "job_ansible.yaml" ;  // 每个任务运行的日志信息
	String running_hosts_ansible_fileName = "host_ansible.properties" ;  // 每个任务运行的日志信息
	
	String running_infoJson_fileName = "info.json" ; // 每个任务运行的json信息

	/**
	 * 文件路径
	 */
	String FILE_MANAGER = "file-manager";

	/**
	 * groovy脚本
	 */
	String GROOVY_SCRIPT = "groovy" ; 
	
	/**
	 * ansible脚本
	 */
	String ANSIBLE_SCRIPT = "ansible" ; 
	
}
