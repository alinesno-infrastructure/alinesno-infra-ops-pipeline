package com.alinesno.infra.ops.pipeline.command;

import com.alinesno.infra.ops.pipeline.command.runner.LogListener;

import java.util.HashMap;
import java.util.Map;


/**
 * 文件读写管理器,用户监听任务是否完成读写
 * @author luoxiaodong
 * @version 1.0.0
 */
public class RunningListenerManager {

	/**
	 * 用于存放文件读写监听器,前一个放置的是任务的id
	 */
	private static Map<String , LogListener> map = new HashMap<String , LogListener>() ;

	/**
	 * 添加监听器
	 * @param taskId
	 * @param log
	 */
	public static void addListener(String taskId , LogListener log) {
		removeListener(taskId);
		map.put(taskId, log) ; 
	}

	/**
	 * 获取监听器
	 * @param taskId
	 */
	public static LogListener getListener(String taskId) {
		return map.get(taskId) ; 
	}
	
	/**
	 * 删除监听器
	 * @param taskId
	 */
	public static void removeListener(String taskId) {
		map.remove(taskId) ; 
	}
}
