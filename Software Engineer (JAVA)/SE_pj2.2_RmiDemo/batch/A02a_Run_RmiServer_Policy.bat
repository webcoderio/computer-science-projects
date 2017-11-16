SET /P rmiserver_port=[Please enter the server port you are connect to: e.g. 1077 ]
cd "C:\Documents and Settings\ang\My Documents\Eclipse\SE_pj2.2_RmiDemo\bin"
java -Djava.security.policy=policy.txt RMI_BioAPI_AsteriskJava_Server %rmiserver_port%
Pause