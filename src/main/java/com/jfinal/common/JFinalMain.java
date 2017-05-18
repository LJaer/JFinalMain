package com.jfinal.common;

import java.util.List;

import javax.sql.DataSource;

import com.jfinal.common.model.Book;
import com.jfinal.common.model._MappingKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

public class JFinalMain {

	static {
		PropKit.use("a_little_config.txt");

		// 配置C3p0数据库连接池插件
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"),
				PropKit.get("user"), PropKit.get("password").trim());
		druidPlugin.start();

		DataSource dataSource = druidPlugin.getDataSource();

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dataSource);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);

		arp.start();
	}

	public static void main(String[] args) {

		System.out.println(Book.dao.findById(1));

	}
}
