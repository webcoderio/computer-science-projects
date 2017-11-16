ITEM 1
======
In the project package VL40.zip, look for pgd.conf under the "Desktop" folder. You need to modify this accordingly as to where the license file(s)
located and put it in whether the neurotechnology activation folder is; e.g., C:\Program Files\Neurotechnology\VeriLook4.0\bin\Win32_x86\Activation\VL_ADDon__license.lic

Then you should stop/restart the activiation wizard.


ITEM 2
======
You should create the following tables and make sure the tables are created successfully before
you proceed further:

*****************************
SQL> describe IMAGES;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 ID                                        NOT NULL NUMBER
 TEMPLATEID                                NOT NULL NUMBER
 BIOMETRICTYPE                             NOT NULL NUMBER
 RECORDINDEX                               NOT NULL NUMBER
 GENINDEX                                  NOT NULL NUMBER
 FRAMEINDEX                                NOT NULL NUMBER
 IMAGE                                     NOT NULL BLOB


drop table IMAGES;

create table IMAGES (
	ID		number	NOT NULL PRIMARY KEY,
	TEMPLATEID	number	NOT NULL,
	BIOMETRICTYPE	number	NOT NULL,
	RECORDINDEX	number	NOT NULL,
	GENINDEX	number	NOT NULL,
	FRAMEINDEX	number	NOT NULL,
	IMAGE		BLOB	NOT NULL
);


*****************************
SQL> describe templates;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 TEMPLATEID                                NOT NULL NUMBER
 TEMPLATECAPTION                           NOT NULL VARCHAR2(1024)
 TEMPLATE                                  NOT NULL BLOB
 THUMBNAIL                                          BLOB

drop table TEMPLATES;

create table TEMPLATES (
	TEMPLATEID	number	NOT NULL PRIMARY KEY,
	TEMPLATECAPTION	varchar2(1024)	NOT NULL,
	TEMPLATE	BLOB	NOT NULL,
	THUMBNAIL	BLOB);


****************************
SQL> describe usertbl;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------

 template                                           BLOB
 dbid                                               VARCHAR2(100)
 finger                                             VARCHAR2(100)
 scan                                               VARCHAR2(100)
 user                                               VARCHAR2(100)
 data                                               DATE
 size                                               NUMBER(10)
 hash_name                                          NUMBER(11)
 id                                        NOT NULL NUMBER(11)

drop table usertbl;

create table USERTBL (
	TEMPLATE	BLOB,
	dbid		varchar2(100),
	finger		varchar2(100),
	scan		varchar2(100),
	user		varchar2(100),
	data		date,
	size		number(10),
	hash_name	number(11),
	id		number(11) NOT NULL PRIMARY KEY
);
	
Remark: If "create table usertbl" fails, rename "user varchar2(100)" as "user123 varchar2(100)" 
and "size number(10)" as "size123 number(10)".


********************************
If everything goes well, query the DB and you should see below:

SQL> select * from tab;

TNAME                          TABTYPE  CLUSTERID
------------------------------ ------- ----------
BIN$58n5TTx/R+i7xjGL6YIZmg==$0 TABLE
BIN$sGo28BCyRbaPeUIqf8LrRw==$0 TABLE
BIOPASSWORD                    TABLE
IMAGES                         TABLE
TEMPLATES                      TABLE
USERTBL                        TABLE

6 rows selected.


ITEM 3
======
Import into Eclipse the "MultiFace Match" project and call it something like "BioAPI-Ora-MultiFace-Match". 
Replace the "MainWindow.java" and add the other two files "Record.java" and "RecordDB.java".

Edit the connection string and user/pass information in the RecordDB.java

You will later create the RMI project(s) that make(s) reference to the project "BioAPI-Ora-MultiFace-Match".

ITEM 4
======
You should adopt the following naming covention when using the BioAPI-Ora-MultiFace-Match utility:

E.g., to upload the image in C:\Documents and Settings\bon\My Documents\My Pictures\Bio-Face\IMG_0244.jpg
Simply adopt the image naming convention below:

Face-<First>-<Last>-001 
Face-<First>-<Last>-002 
....