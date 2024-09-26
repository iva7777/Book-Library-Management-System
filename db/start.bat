@java -cp h2-2.2.224.jar org.h2.tools.Server -tcp -ifNotExists -baseDir . -tcpPassword 123456
@if errorlevel 1 pause