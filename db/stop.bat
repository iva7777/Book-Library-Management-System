@java -cp h2-2.2.224.jar org.h2.tools.Server -tcpShutdown tcp://localhost:9092 -tcpPassword 123456
@if errorlevel 1 pause