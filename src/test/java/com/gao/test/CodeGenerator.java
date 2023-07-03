package com.gao.test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author gao
 * @time 2020/10/23 16:32:21
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 1. 创建代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 2. 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "\\src\\main\\java");
        globalConfig.setAuthor("Kim");
        globalConfig.setOpen(false);                    // 生成后是否打开资源管理器
        globalConfig.setFileOverride(false);            // 重新生成文件时，是否覆盖旧文件
        // globalConfig.setServiceName("%sService");    // 去掉Service接口的首字母"I"
        globalConfig.setIdType(IdType.ASSIGN_ID);       // 设置主键生成策略
        globalConfig.setDateType(DateType.ONLY_DATE);   // 定义生成的实体类中日期的类型
        globalConfig.setSwagger2(false);                // 开启Swagger2模式
        generator.setGlobalConfig(globalConfig);
        // 3. 配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3307/bayern?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("128622");
        dsc.setDbType(DbType.MYSQL);
        generator.setDataSource(dsc);
        // 4. 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.kimmich");
        // pc.setModuleName("mp");                              // 设置模块名
        pc.setController("controller");
        pc.setEntity("domain");
        pc.setService("service");
        pc.setMapper("mapper");
        generator.setPackageInfo(pc);
        // 5. 策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setInclude("bayernn");                                  // 指定表名
        sc.setNaming(NamingStrategy.underline_to_camel);        // 数据库表映射到实体的命名策略
        sc.setTablePrefix(pc.getModuleName() + "_");
        sc.setColumnNaming(NamingStrategy.underline_to_camel);  // 数据库表字段映射到实体的命名策略
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);                        // restful api风格控制器
        sc.setControllerMappingHyphenStyle(true);               // url中驼峰转连字符
        generator.setStrategy(sc);

        // 6. 执行
        generator.execute();
    }

}
