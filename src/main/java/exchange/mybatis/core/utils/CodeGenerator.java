package exchange.mybatis.core.utils;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import exchange.mybatis.core.mapper.BaseMapperX;
import exchange.mybatis.dataobject.BaseDO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Types;
import java.util.Collections;

/*
 * @description
 * @author hb
 * @since 2024/2/28 17:04
 */
public class CodeGenerator {

    // 作者名
    private static final String AUTHOR = "hb";
    // 需要生成的表名，多个表名用逗号隔开
    private static final String[] TABLE_NAME = {
            "UserInfo", "ExchangeLog", "ExchangePointApply", "ItemInfo", "ReturnPointApply", "UserExpand", "UserItems", "UserLikeWeakRecord", "UserPrivacyInfo"
    };
    // 表前缀
    private static final String TABLE_PRE = "";

    // 模块名
    private static final String MODULE_NAME = "/exchange";
    // 包名
    private static final String PACKAGE_NAME = "exchange";
    // 链接地址
    private static final String CON_URL = "jdbc:mysql://10.10.55.134:3306/hbTest?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai";
    // 用户名
    private static final String USER_NAME = "root";
    // 密码
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String outPutDir = projectPath + "/src/main/java" + MODULE_NAME;
        FastAutoGenerator.create(CON_URL, USER_NAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(outPutDir)
                    ; // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME) // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outPutDir + "/" + PACKAGE_NAME.replace(".", "/") + "/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                            .addTablePrefix(TABLE_PRE)
                            .entityBuilder()
                            .superClass(BaseDO.class)
                            .addSuperEntityColumns("id", "creator_id", "create_time", "updater_id", "update_time")
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .mapperBuilder()
                            .superClass(BaseMapperX.class)
                            .mapperAnnotation(Mapper.class)
                            .serviceBuilder()
                            .superServiceClass(IService.class)
                            .controllerBuilder().enableRestStyle()
                    ; // 设置过滤表前缀

                })
                // 不生成 controller\service\serviceImpl
//                .templateConfig(builder -> builder.controller(""))
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
