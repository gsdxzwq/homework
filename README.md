# 简易事件管理

## 项目概述
本项目是一个使用Java 17和Spring Boot开发的简单事件管理应用程序，实现了创建、删除、修改以及列出所有事件的功能，并且提供了对应的RESTful API，并且支持容器化部署（通过Docker）。

## 技术选型
- Java 17：作为项目的开发语言，利用其新特性和稳定性。
- Spring Boot：简化Spring框架的搭建和配置，快速构建Web应用程序。
- Lombok：用于简化实体类等代码的编写，自动生成Getter、Setter等常用方法，减少样板代码。
- Docker：将应用程序进行容器化，方便部署到不同环境中，通过定义Dockerfile来构建镜像以及运行容器。

## 如何运行
克隆项目到本地后，进入项目根目录，使用 Maven 命令mvn clean install进行项目打包，会在target目录下生成可执行的 JAR 文件。
可以直接运行java -jar target/*.jar启动应用程序，默认会在8080端口启动，或者通过Docker来运行（参考项目根目录下的Dockerfile构建镜像并运行容器）。

在项目根目录执行：
```
docker build -t incident-app .
docker run -p 8080:8080 incident-app
```

## API说明
### 创建事件
- URL：/api/incident
- 方法：POST
- 请求体：包含事件相关信息（如标题、描述、创建时间等）的JSON数据，示例：{"title":"测试事件","description":"这是一个测试事件描述","createTime":"2024-11-23 15:20"}
- 响应：成功创建返回201 Created状态码以及创建后的事件信息，失败返回500 Internal Server Error等对应错误状态码。
### 删除事件
- URL：/api/incident/{id}（{id}为要删除事件的 ID）
- 方法：DELETE
- 响应：成功删除返回200 OK状态码，若事件不存在返回404 Not Found状态码。
### 修改事件
- URL：/api/incident/{id}
- 方法：PUT
- 请求体：包含完整事件信息（包含 ID 以及要修改的其他属性）的JSON数据
- 响应：成功修改返回200 OK状态码以及修改后的事件信息，若事件不存在返回404 Not Found状态码。
### 列出所有事件
- URL：/api/incident
- 方法：GET
- 响应：成功返回200 OK状态码以及事件列表数据（JSON 格式），失败返回500 Internal Server Error状态码。

## 单元测试
项目中使用JUnit 5编写了单元测试类，对各个API对应的方法进行了测试，确保其功能的稳健性和稳定性，测试类位于src/test/java目录下，可以通过Maven命令mvn test来执行所有单元测试。

## 注意事项
- 目前数据保存在内存中，应用程序重启后数据会丢失，如果需要持久化存储，可以考虑连接真实的数据库并配置相应的数据源等信息，在application.properties（或application.yml）文件中进行数据库相关配置更改。
- 由于时间及自身能力限制，前端页面暂无。