cd "C:\Documents and Settings\ang\My Documents\Eclipse\SE_pj2.2_RmiDemo\bin"
SET /P rmiserver_ip=[Please enter the server ip you are connect to: e.g. 127.0.0.1 ]
SET /P rmiserver_port=[Please enter the remote BSP server port you are connect to: e.g. 1078, DO NOT USE the IP of RMiRegisty & local RmiServer ]
java -Djava.security.policy=policy.txt RMI_BioAPI_Demo demo2.txt %rmiserver_port% %rmiserver_ip% my_transact demo.txt
pause