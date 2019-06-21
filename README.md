### 巴雷特业务系统

采用springboot2开发的简洁的后台管理系统，基于bootstrap开发的响应式后台管理系统，前端采用前后端分离适用于web、app，wechat以及其他端统一接入，单模块架构更加易于维护、升级以及部署。

#### 技术栈

主要技术： sping boot + spring data jpa +Ehcache +mysql5.7+thymeleaf+boostrap+jquery

#### 版本说明

| 分支   | 说明   | 地址                                              |
| ------ | ------ | ------------------------------------------------- |
| master | 主分支 | http://gitlab.beerate.cn/minxiang/beerate-project |

#### 主要功能

##### 后台模块：

1. 首页
   - 数据展示
2. 平台用户
   - 用户查看
   - 资料修改
   - 名片审核
3. 项目管理
   - 项目添加（项目融资，大宗交易，preipo，股票质押，老股转让）
   - 项目查看
   - 上下架
   - 项目审核
4. 管理
   - 管理员列表
   - 权限管理
5. 系统设置

#### 功能特点

##### 会话存储：

```
基于java的Ehcache缓存框架作为session管理，用于提高性能、减少目前不必要的中间件，以及适配各端的请求认证处理。
```

##### 持久层操作

```
后台基于JpaRepository，JpaSpecificationExecutor接口进行数据查询，适用于分页处理、单表查询、分页排序，分页搜索。
采用jpa entity实体建模创建数据表，以及表之间的关联。
页面数据采用open-in-view进行数据表的数据字段渲染，后台采用thymeleaf模板引擎几乎不用写sql语句，简单易用。
=======================================================================================
前台数据自定义结果，基于对jpa实现的拓展GenericRepository，采用原生sql进行自定义查询，处理分页，排序。
重点解决：接口数据返回时序列化所产生的一系列问题。
```

#### 前台接口文档

http://gitlab.beerate.cn/minxiang/beerate-project/wikis/%E5%B7%B4%E9%9B%B7%E7%89%B9%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3


























