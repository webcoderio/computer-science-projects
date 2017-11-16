cd "C:\Documents and Settings\ang\My Documents\Eclipse\SE_pj2.1_Rmi\bin"
SET /P rmiserver_ip=[Please enter the server IP you are connect to: e.g. 127.0.0.1 ]
SET /P rmiclient_file=[Please enter the file name on server to get, e.g. file.txt ]
java RmiClientFile %rmiserver_ip% 1 "%rmiclient_file%"
pause