# mugsun-core

Mugsun 平台核心底座：统一依赖管理（BOM）、核心组件与全部 Starter，为单体版与微服务版提供统一构建基石。

## 技术栈

JDK 21（虚拟线程）· Spring Boot 3.5.x · MyBatis-Flex · Sa-Token · JetCache · LiteFlow · x-file-storage · SMS4J · Hutool · Knife4j

## 模块

| 模块 | 说明 |
| --- | --- |
| `mugsun-bom` | 依赖版本统一管理 |
| `mugsun-core-launch` | 应用启动器、环境校验、上下文传递 |
| `mugsun-core-tool` | 统一响应、异常、字段视图与脱敏 |
| `mugsun-core-auth` | 认证鉴权集成与权限表达式 |
| `mugsun-core-boot` / `mugsun-core-cloud` | 单体 / 微服务形态基座 |
| `mugsun-starter-mybatis` | ORM 基座与通用实体、Mapper |
| `mugsun-starter-web` | 统一异常、XSS 防护、虚拟线程适配 |
| `mugsun-starter-cache` | 多级缓存 |
| `mugsun-starter-oss` | 多云对象存储 |
| `mugsun-starter-sms` | 多厂商短信 |
| `mugsun-starter-log` | 日志与数据审计 |
| `mugsun-starter-swagger` | 接口文档 |
| `mugsun-starter-develop` | 可视化代码生成 |

## 构建

```bash
mvn clean install -DskipTests
```

## 许可

[Apache License 2.0](LICENSE)
