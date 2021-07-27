package mybatisPlus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class AutoGender {

    public static void main(String[] args) {

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true)
                .setAuthor("魔法少女")
                .setOutputDir("D:\\JAVA\\Project\\EnergyProject\\src\\main\\java")
                .setFileOverride(false)
                .setIdType(IdType.AUTO)
                .setServiceName("%Service")
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.SQL_SERVER)
                .setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .setUrl("jdbc:sqlserver://localhost:1433;database=KEPServerEX 6 ")
                .setUsername("root")
                .setPassword("820606");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)  //全局大写命名
                .setColumnNaming(NamingStrategy.no_change) //数据库表映射到实体命名策略
                .setInclude("EnergyUsername");

        //包名策略
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.EnergyProject")
                .setMapper("dao")
                .setService("server")
                .setController("controller")
                .setXml("dao")
                .setEntity("pojo");

        //整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        autoGenerator.execute();
    }
}
