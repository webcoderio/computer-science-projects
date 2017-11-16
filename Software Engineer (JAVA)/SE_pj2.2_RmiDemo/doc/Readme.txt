Client side:

C:\rmi_demo\client>java -Djava.security.policy=java_grant_all_policy.txt RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt

** demo.txt is the source file on remote RMI_BioAPI_AsteriskJava_server side **

** 1688 is the socket listener port for the RMI_BioAPI_AsteriskJava_client side **

** localhost is the IP location of the RMI server "RMI_BioAPI_AsteriskJava_Server **


OR

C:\rmi_demo\client>java RMI_BioAPI_Demo demo16.txt 1688  localhost my_transact demo.txt




