package com.mugsun.core.tool.tree;

import java.util.List;

/**
 * 树节点接口
 *
 * @param <T> 节点类型
 */
public interface INode<T> {

	Long getId();

	Long getParentId();

	List<T> getChildren();

	void setChildren(List<T> children);
}
