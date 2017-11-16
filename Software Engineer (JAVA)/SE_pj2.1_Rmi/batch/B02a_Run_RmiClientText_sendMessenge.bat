cd "C:\Documents and Settings\ang\My Documents\Eclipse\SE_pj2.1_Rmi\bin"
SET /P rmiserver_ip=[Please enter the server IP you are connect to: e.g. 127.0.0.1 ]
java RmiClientText %rmiserver_ip% 2 "Hi, Server! (This is a message from Client to Server)"
pause