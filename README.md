NEW ADD
------------
新增功能如下：
* 支持单文件多模板文件解析的场景
* 解析文件时候所需列数和文件实际的列数不一定要相等，而且有时候为了能够支持扩展性的问题，文件中内容可能会随时增加扩展字段，接收处理方应能向下兼容处理，即增加扩展字段后，对新增字段无需识别处理的用户，不需要升级系统

解决bug如下：
* 解析文件到指定对象bug修复

增加测试用例：
* 增加单文件多模板的测试用例
* 增加解析文件到指定对象而不仅仅是map的测试用例

具体使用规则参见：https://www.jianshu.com/p/54073467c80d

Introduction
------------
Rdf-File is a tool for processing structured text files. It can read, write, split, merge, sort, validate, and it can operate different distributed file systems (nas/oss ...).  Standards or non-standard files can be handled efficiently in a single or distributed environment.

Features
--------
* Personalization file parameter configuration
* File read
* File write
* File split
* File merge
* File multi storage operate
* File validate
* File sort
* File multi protocol (format) define
* Automatic type conversion

Documentation
-------------
https://github.com/alipay/rdf-file/wiki

Contact
-------------
斩秋 hongwei.quhw@antfin.com 	444846366@qq.com  
镭光 alex.yk@antfin.com 			ye.blackleaf@gmail.com  
闫钊 zhao.yan@alipay.com 		2paopaolong@163.com  
