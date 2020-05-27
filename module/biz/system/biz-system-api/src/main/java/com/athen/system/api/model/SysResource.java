package com.athen.system.api.model;
import com.athen.system.api.model.enums.State;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 资源菜单 --> SYS_RESOURCE */
@Data
public class SysResource implements Serializable, Comparable<SysResource> {
	private static final long serialVersionUID = 1L;

	private Long id;

	/** 资源名称 --> NAME */
	private String name;

	/** 资源编号 --> CODE */
	private String code;

	/** 父菜单ID --> SUP_ID */
	private Long supId;

	/** 菜单路径(id组成) --> PATH */
	private String path;

	/** 菜单级别 --> DEPTH */
	private Integer depth;

	/** 菜单URL --> URL */
	private String url;

	/** 菜单排序 --> SEQ */
	private Integer seq;

	/** 菜单图标 --> ICON */
	private String icon;

	/** 菜单类型 --> TYPE */
	private ResourceType type;

	/** 为 "createTime" 提供查询的起始值 */
	private Date createTimeStart;
	/** 为 "createTime" 提供查询的结束值 */
	private Date createTimeEnd;
	/** 创建时间 --> CREATE_TIME */
	private Date createTime;

	/** 创建人 --> CREATE_USER */
	private Long createUser;

	/** 为 "updateTime" 提供查询的起始值 */
	private Date updateTimeStart;
	/** 为 "updateTime" 提供查询的结束值 */
	private Date updateTimeEnd;
	/** 更新时间 --> UPDATE_TIME */
	private Date updateTime;

	/** 更新人 --> UPDATE_USER */
	private Long updateUser;

	/** 状态(1 启用 0禁用) --> STATE */
	 private State state;

	/** 资源操作方法(GET,POST,PUT,DELETE), 多个用逗号分隔, 支持 * 通配 --> METHOD */
	private String method;

	private List<String> methods;

	@Override
	public int compareTo(SysResource o) {
		return this.getSeq().compareTo(o.getSeq());
	}

	public enum ResourceType {
		MENU, // 菜单
		URL, // ajax地址
		BUTTON, // 按钮
		MOBILE, API, MODULE, FUNCTION
	}

	public enum MethodType {
		GET, POST, PUT, DELETE
	}
}