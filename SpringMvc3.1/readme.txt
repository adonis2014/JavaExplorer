1)ant运行参数
-Divy.win.dir.path=g: -Dant-build-space=g: -DspreadConf=";sources"
2)启动参数(三种环境production,dev,test。默认production)
如开发环境使用如下参数
-Dspring.profiles.active="dev" -javaagent:"D:\.ivy2\cache\org.springframework\spring-instrument\jars\spring-instrument-4.1.3.RELEASE.jar"
生产环境不许要加任何参数，但需要名为"dataSource"的一个JNDI数据源