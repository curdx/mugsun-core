package com.mugsun.core.tool.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树构建工具
 */
public class TreeUtil {

	private TreeUtil() {
	}

	/**
	 * 将扁平列表构建为树
	 *
	 * @param nodes        全部节点
	 * @param rootParentId 根节点的父 ID（如顶级为 0）
	 */
	public static <T extends INode<T>> List<T> build(List<T> nodes, Long rootParentId) {
		List<T> roots = new ArrayList<>();
		for (T node : nodes) {
			if (Objects.equals(node.getParentId(), rootParentId)) {
				node.setChildren(build(nodes, node.getId()));
				roots.add(node);
			}
		}
		return roots;
	}
}
