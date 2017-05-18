## JFinalMain

[Demo下载](https://github.com/LJaer/JFinalMain)

环境：Eclipse + Maven + Mysql + Druid

使用 JFinal 框架 Main 方法来直接访问数据库

test.sql 为数据库文件

### 1、maven jar

```
<dependency>
	<groupId>com.jfinal</groupId>
	<artifactId>jfinal</artifactId>
	<version>3.1</version>
</dependency>
<dependency>
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.16</version>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.20</version>
</dependency>
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid</artifactId>
	<version>1.0.29</version>
</dependency>
```

### 2、_JFinalDemoGenerator.java

自动生成代码

### 3、JFinalMain.java

Main 类

```java
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
```

执行该类的 Main 方法可以获取数据库数据

	{price:100, isbn:1001, id:1, book_name:Java, stock:7}


注意事项：

- 插件需要 start
- ActiveRecordPlugin 需要初始化 dataSource 参数




