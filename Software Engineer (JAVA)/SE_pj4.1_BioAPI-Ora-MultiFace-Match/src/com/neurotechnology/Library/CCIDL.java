package com.neurotechnology.Library;

import java.util.EnumSet;

public enum CCIDL {
	CSIDL_DESKTOP					((0x0000), "DESKTOP"),		// <desktop>
	CSIDL_INTERNET                  ((0x0001), "INTERNET"),		// Internet Explorer icon on desktop)
	CSIDL_PROGRAMS                  ((0x0002), "PROGRAMS"),		// Start Menu\Programs
	CSIDL_CONTROLS                  ((0x0003), "CONTROLS"),		// My Computer\Control Panel
	CSIDL_PRINTERS                  ((0x0004), "PRINTERS"),		// My Computer\Printers
	CSIDL_PERSONAL                  ((0x0005), "PERSONAL"),		// My Documents
	CSIDL_FAVORITES                 ((0x0006), "FAVORITES"),		// <user name>\Favorites
	CSIDL_STARTUP                   ((0x0007), "STARTUP"),		// Start Menu\Programs\Startup
	CSIDL_RECENT                    ((0x0008), "RECENT"),		// <user name>\Recent
	CSIDL_SENDTO                    ((0x0009), "SENDTO"),		// <user name>\SendTo
	CSIDL_BITBUCKET                 ((0x000a), "BITBUCKET"),		// <desktop>\Recycle Bin
	CSIDL_STARTMENU                 ((0x000b), "STARTMENU"),		// <user name>\Start Menu
	CSIDL_MYDOCUMENTS               ((0x0005), "MYDOCUMENTS"),//  Personal was just a silly name for My Documents
	CSIDL_MYMUSIC                   ((0x000d), "MYMUSIC"),		// "My Music" folder
	CSIDL_MYVIDEO                   ((0x000e), "MYVIDEO"),		// "My Videos" folder
	CSIDL_DESKTOPDIRECTORY          ((0x0010), "DESKTOPDIRECTORY"),		// <user name>\Desktop
	CSIDL_DRIVES                    ((0x0011), "DRIVES"),		// My Computer
	CSIDL_NETWORK                   ((0x0012), "NETWORK"),		// Network Neighborhood My Network Places)
	CSIDL_NETHOOD                   ((0x0013), "NETHOOD"),		// <user name>\nethood
	CSIDL_FONTS                     ((0x0014), "FONTS"),		// windows\fonts
	CSIDL_TEMPLATES                 ((0x0015), "TEMPLATES"),
	CSIDL_COMMON_STARTMENU          ((0x0016), "COMMON_STARTMENU"),		// All Users\Start Menu
	CSIDL_COMMON_PROGRAMS           ((0X0017), "COMMON_PROGRAMS"),		// All Users\Start Menu\Programs
	CSIDL_COMMON_STARTUP            ((0x0018), "COMMON_STARTUP"),		// All Users\Startup
	CSIDL_COMMON_DESKTOPDIRECTORY   ((0x0019), "COMMON_DESKTOPDIRECTORY"),		// All Users\Desktop
	CSIDL_APPDATA                   ((0x001a), "APPDATA"),		// <user name>\Application Data
	CSIDL_PRINTHOOD                 ((0x001b), "PRINTHOOD"),		// <user name>\PrintHood
	CSIDL_LOCAL_APPDATA             ((0x001c), "LOCAL_APPDATA"),		// <user name>\Local Settings\Applicaiton Data non roaming)
	CSIDL_ALTSTARTUP                ((0x001d), "ALTSTARTUP"),		// non localized startup
	CSIDL_COMMON_ALTSTARTUP         ((0x001e), "COMMON_ALTSTARTUP"),		// non localized common startup
	CSIDL_COMMON_FAVORITES          ((0x001f), "COMMON_FAVORITES"),
	CSIDL_INTERNET_CACHE            ((0x0020), "INTERNET_CACHE"),
	CSIDL_COOKIES                   ((0x0021), "COOKIES"),
	CSIDL_HISTORY                   ((0x0022), "HISTORY"),
	CSIDL_COMMON_APPDATA            ((0x0023), "COMMON_APPDATA"),		// All Users\Application Data
	CSIDL_WINDOWS                   ((0x0024), "WINDOWS"),		// GetWindowsDirectory)
	CSIDL_SYSTEM                    ((0x0025), "SYSTEM"),		// GetSystemDirectory)
	CSIDL_PROGRAM_FILES             ((0x0026), "PROGRAM_FILES"),		// C:\Program Files
	CSIDL_MYPICTURES                ((0x0027), "MYPICTURES"),		// C:\Program Files\My Pictures
	CSIDL_PROFILE                   ((0x0028), "PROFILE"),		// USERPROFILE
	CSIDL_SYSTEMX86                 ((0x0029), "SYSTEMX86"),		// x86 system directory on RISC
	CSIDL_PROGRAM_FILESX86          ((0x002a), "PROGRAM_FILESX86"),		// x86 C:\Program Files on RISC
	CSIDL_PROGRAM_FILES_COMMON      ((0x002b), "PROGRAM_FILES_COMMON"),		// C:\Program Files\Common
	CSIDL_PROGRAM_FILES_COMMONX86   ((0x002c), "PROGRAM_FILES_COMMONX86"),		// x86 Program Files\Common on RISC
	CSIDL_COMMON_TEMPLATES          ((0x002d), "COMMON_TEMPLATES"),		// All Users\Templates
	CSIDL_COMMON_DOCUMENTS          ((0x002e), "COMMON_DOCUMENTS"),		// All Users\Documents
	CSIDL_COMMON_ADMINTOOLS         ((0x002f), "COMMON_ADMINTOOLS"),		// All Users\Start Menu\Programs\Administrative Tools
	CSIDL_ADMINTOOLS                ((0x0030), "ADMINTOOLS"),		// <user name>\Start Menu\Programs\Administrative Tools
	CSIDL_CONNECTIONS               ((0x0031), "CONNECTIONS"),		// Network and Dial-up Connections
	CSIDL_COMMON_MUSIC              ((0x0035), "COMMON_MUSIC"),		// All Users\My Music
	CSIDL_COMMON_PICTURES           ((0x0036), "COMMON_PICTURES"),		// All Users\My Pictures
	CSIDL_COMMON_VIDEO              ((0x0037), "COMMON_VIDEO"),		// All Users\My Video
	CSIDL_RESOURCES                 ((0x0038), "RESOURCES"),		// Resource Direcotry
	CSIDL_RESOURCES_LOCALIZED       ((0x0039), "RESOURCES_LOCALIZED"),		// Localized Resource Direcotry
	CSIDL_COMMON_OEM_LINKS          ((0x003a), "COMMON_OEM_LINKS"),		// Links to All Users OEM specific apps
	CSIDL_CDBURN_AREA               ((0x003b), "CDBURN_AREA"),		// USERPROFILE\Local Settings\Application Data\Microsoft\CD Burning
	CSIDL_COMPUTERSNEARME           ((0x003d), "COMPUTERSNEARME"),		// Computers Near Me computered from Workgroup membership)
	CSIDL_FLAG_CREATE               ((0x8000), "FLAG_CREATE"),		// combine with CSIDL_ value to force folder creation in SHGetFolderPath)
	CSIDL_FLAG_DONT_VERIFY          ((0x4000), "FLAG_DONT_VERIFY"),		// combine with CSIDL_ value to return an unverified folder path
	CSIDL_FLAG_DONT_UNEXPAND        ((0x2000), "FLAG_DONT_UNEXPAND"),		// combine with CSIDL_ value to avoid unexpanding environment variables
	CSIDL_FLAG_NO_ALIAS             ((0x1000), "FLAG_NO_ALIAS"),		// combine with CSIDL_ value to insure non-alias versions of the pidl
	CSIDL_FLAG_PER_USER_INIT        ((0x0800), "FLAG_PER_USER_INIT"),		// combine with CSIDL_ value to indicate per-user init eg. upgrade)
	CSIDL_FLAG_MASK                 ((0xFF00), "FLAG_MASK");		// mask for all possible flag values

	private final int index;
	private final String desc;

	CCIDL(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

	public int eval() {
		return index;
	}
	
	public String toString() {
		return desc;
	}
	
	public static CCIDL parse(int value) throws Exception {
		for (CCIDL it : EnumSet.allOf(CCIDL.class)) {
			if (it.index == value) {
				return it;
			}
		}
		throw new Exception("value out of bounds");
	}
}
