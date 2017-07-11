/* Copyright (c) 2010 Daniel Doubrovkine, All Rights Reserved
 *
 * The contents of this file is dual-licensed under 2 
 * alternative Open Source/Free licenses: LGPL 2.1 or later and 
 * Apache License 2.0. (starting with JNA version 4.0.0).
 * 
 * You can freely decide which license you want to apply to 
 * the project.
 * 
 * You may obtain a copy of the LGPL License at:
 * 
 * http://www.gnu.org/licenses/licenses.html
 * 
 * A copy is also included in the downloadable source code package
 * containing JNA, in file "LGPL2.1".
 * 
 * You may obtain a copy of the Apache License at:
 * 
 * http://www.apache.org/licenses/
 * 
 * A copy is also included in the downloadable source code package
 * containing JNA, in file "AL2.0".
 */
package com.sun.jna.platform.win32;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;

import java.util.Arrays;
import java.util.List;

/**
 * Ported from Winbase.h (kernel32.dll/kernel services).
 * Microsoft Windows SDK 6.0A.
 *
 * @author dblock[at]dblock.org
 */
public interface WinBase {


    /**
     * This logon type is intended for users who will be interactively using the computer, such
     * as a user being logged on by a terminal server, remote shell, or similar process. This
     * logon type has the additional expense of caching logon information for disconnected operations;
     * therefore, it is inappropriate for some client/server applications, such as a mail server.
     */
    int LOGON32_LOGON_INTERACTIVE = 2;
    /**
     * This logon type is intended for high performance servers to authenticate plaintext passwords.
     * The LogonUser function does not cache credentials for this logon type.
     */
    int LOGON32_LOGON_NETWORK = 3;
    /**
     * This logon type is intended for batch servers, where processes may be executing on behalf
     * of a user without their direct intervention. This type is also for higher performance servers
     * that process many plaintext authentication attempts at a time, such as mail or Web servers.
     * The LogonUser function does not cache credentials for this logon type.
     */
    int LOGON32_LOGON_BATCH = 4;
    /**
     * Indicates a service-type logon. The account provided must have the service privilege enabled.
     */
    int LOGON32_LOGON_SERVICE = 5;
    /**
     * This logon type is for GINA DLLs that log on users who will be interactively using the computer.
     * This logon type can generate a unique audit record that shows when the workstation was unlocked.
     */
    int LOGON32_LOGON_UNLOCK = 7;
    /**
     * This logon type preserves the name and password in the authentication package, which allows the
     * server to make connections to other network servers while impersonating the client. A server can
     * accept plaintext credentials from a client, call LogonUser, verify that the user can access the
     * system across the network, and still communicate with other servers.
     */
    int LOGON32_LOGON_NETWORK_CLEARTEXT = 8;
    /**
     * This logon type allows the caller to clone its current token and specify new credentials for
     * outbound connections. The new logon session has the same local identifier but uses different
     * credentials for other network connections. This logon type is supported only by the
     * LOGON32_PROVIDER_WINNT50 logon provider.
     */
    int LOGON32_LOGON_NEW_CREDENTIALS = 9;

    /**
     * Use the standard logon provider for the system. The default security provider is negotiate,
     * unless you pass NULL for the domain name and the user name is not in UPN format. In this case,
     * the default provider is NTLM.
     */
    int LOGON32_PROVIDER_DEFAULT = 0;

    /**
     * Use the Windows NT 3.5 logon provider.
     */
    int LOGON32_PROVIDER_WINNT35 = 1;
    /**
     * Use the NTLM logon provider.
     */
    int LOGON32_PROVIDER_WINNT40 = 2;
    /**
     * Use the negotiate logon provider.
     */
    int LOGON32_PROVIDER_WINNT50 = 3;

    /**
     * If this flag is set, a child process created with the bInheritHandles parameter of
     * CreateProcess set to TRUE will inherit the object handle.
     */
    int HANDLE_FLAG_INHERIT = 1;

    /**
     * If this flag is set, calling the {@link Kernel32#CloseHandle} function will not
     * close the object handle.
     */
    int HANDLE_FLAG_PROTECT_FROM_CLOSE = 2;

    // STARTUPINFO flags
    int STARTF_USESHOWWINDOW = 0x001;
    int STARTF_USESIZE = 0x002;
    int STARTF_USEPOSITION = 0x004;
    int STARTF_USECOUNTCHARS = 0x008;
    int STARTF_USEFILLATTRIBUTE = 0x010;
    int STARTF_RUNFULLSCREEN = 0x020;
    int STARTF_FORCEONFEEDBACK = 0x040;
    int STARTF_FORCEOFFFEEDBACK = 0x080;
    int STARTF_USESTDHANDLES = 0x100;

    // Process Creation flags
    int DEBUG_PROCESS = 0x00000001;
    int DEBUG_ONLY_THIS_PROCESS = 0x00000002;
    int CREATE_SUSPENDED = 0x00000004;
    int DETACHED_PROCESS = 0x00000008;
    int CREATE_NEW_CONSOLE = 0x00000010;
    int CREATE_NEW_PROCESS_GROUP = 0x00000200;
    int CREATE_UNICODE_ENVIRONMENT = 0x00000400;
    int CREATE_SEPARATE_WOW_VDM = 0x00000800;
    int CREATE_SHARED_WOW_VDM = 0x00001000;
    int CREATE_FORCEDOS = 0x00002000;
    int INHERIT_PARENT_AFFINITY = 0x00010000;
    int CREATE_PROTECTED_PROCESS = 0x00040000;
    int EXTENDED_STARTUPINFO_PRESENT = 0x00080000;
    int CREATE_BREAKAWAY_FROM_JOB = 0x01000000;
    int CREATE_PRESERVE_CODE_AUTHZ_LEVEL = 0x02000000;
    int CREATE_DEFAULT_ERROR_MODE = 0x04000000;
    int CREATE_NO_WINDOW = 0x08000000;

    /* File encryption status */
    int FILE_ENCRYPTABLE = 0;
    int FILE_IS_ENCRYPTED = 1;
    int FILE_SYSTEM_ATTR = 2;
    int FILE_ROOT_DIR = 3;
    int FILE_SYSTEM_DIR = 4;
    int FILE_UNKNOWN = 5;
    int FILE_SYSTEM_NOT_SUPPORT = 6;
    int FILE_USER_DISALLOWED = 7;
    int FILE_READ_ONLY = 8;
    int FILE_DIR_DISALOWED = 9;

    /* Open encrypted files raw flags */
    int CREATE_FOR_IMPORT = 1;
    int CREATE_FOR_DIR = 2;
    int OVERWRITE_HIDDEN = 4;

    /* Invalid return values */
    int INVALID_FILE_SIZE = 0xFFFFFFFF;
    int INVALID_SET_FILE_POINTER = 0xFFFFFFFF;
    int INVALID_FILE_ATTRIBUTES = 0xFFFFFFFF;

    /**
     * Return code for a process still active.
     */
    int STILL_ACTIVE = WinNT.STATUS_PENDING;

    // Codes for FILE_INFO_BY_HANDLE_CLASS taken from Winbase.h
    int FileBasicInfo = 0;
    int FileStandardInfo = 1;
    int FileNameInfo = 2;
    int FileRenameInfo = 3;
    int FileDispositionInfo = 4;
    int FileAllocationInfo = 5;
    int FileEndOfFileInfo = 6;
    int FileStreamInfo = 7;
    int FileCompressionInfo = 8;
    int FileAttributeTagInfo = 9;
    int FileIdBothDirectoryInfo = 10; // 0xA
    int FileIdBothDirectoryRestartInfo = 11; // 0xB
    int FileIoPriorityHintInfo = 12; // 0xC
    int FileRemoteProtocolInfo = 13; // 0xD
    int FileFullDirectoryInfo = 14; // 0xE
    int FileFullDirectoryRestartInfo = 15; // 0xF
    int FileStorageInfo = 16; // 0x10
    int FileAlignmentInfo = 17; // 0x11
    int FileIdInfo = 18; // 0x12
    int FileIdExtdDirectoryInfo = 19; // 0x13
    int FileIdExtdDirectoryRestartInfo = 20; // 0x14


    // FINDEX_INFO_LEVELS values defines values that are used with the FindFirstFileEx function to specify the information level of the returned data.

    /**
     * The FindFirstFileEx function retrieves a standard set of attribute information. The data is returned
     * in a WIN32_FIND_DATA structure.
     */
    int FindExInfoStandard = 0;

    /**
     * The FindFirstFileEx function does not query the short file name, improving overall enumeration speed. The data is
     * returned in a WIN32_FIND_DATA structure, and the cAlternateFileName member is always a NULL string.
     */
    int FindExInfoBasic = 1;
    /**
     * This value is used for validation. Supported values are less than this value.
     */
    int FindExInfoMaxInfoLevel = 2;

    // FINDEX_SEARCH_OPS values defines values that are used with the FindFirstFileEx function to specify the type of filtering to perform.
    /**
     * The search for a file that matches a specified file name. The lpSearchFilter parameter of FindFirstFileEx
     * must be NULL when this search operation is used.
     */
    int FindExSearchNameMatch = 0;

    /**
     * This is an advisory flag. If the file system supports directory filtering, the function searches for a file that
     * matches the specified name and is also a directory. If the file system does not support directory filtering,
     * this flag is silently ignored.
     * The lpSearchFilter parameter of the FindFirstFileEx function must be NULL when this search value is used.
     * If directory filtering is desired, this flag can be used on all file systems, but because it is an advisory
     * flag and only affects file systems that support it, the application must examine the file attribute data stored
     * in the lpFindFileData parameter of the FindFirstFileEx function to determine whether the function has returned
     * a handle to a directory.
     */
    int FindExSearchLimitToDirectories = 1;

    /**
     * This filtering type is not available. For more information, see Device Interface Classes.
     */
    int FindExSearchLimitToDevices = 2;


    /* Local Memory Flags */
    int LMEM_FIXED = 0x0000;
    int LMEM_MOVEABLE = 0x0002;
    int LMEM_NOCOMPACT = 0x0010;
    int LMEM_NODISCARD = 0x0020;
    int LMEM_ZEROINIT = 0x0040;
    int LMEM_MODIFY = 0x0080;
    int LMEM_DISCARDABLE = 0x0F00;
    int LMEM_VALID_FLAGS = 0x0F72;
    int LMEM_INVALID_HANDLE = 0x8000;

    int LHND = (LMEM_MOVEABLE | LMEM_ZEROINIT);
    int LPTR = (LMEM_FIXED | LMEM_ZEROINIT);

    /* Flags returned by LocalFlags (in addition to LMEM_DISCARDABLE) */
    int LMEM_DISCARDED = 0x4000;
    int LMEM_LOCKCOUNT = 0x00FF;


    /**
     * The lpBuffer parameter is a pointer to a PVOID pointer, and that the nSize
     * parameter specifies the minimum number of TCHARs to allocate for an output
     * message buffer. The function allocates a buffer large enough to hold the
     * formatted message, and places a pointer to the allocated buffer at the address
     * specified by lpBuffer. The caller should use the LocalFree function to free
     * the buffer when it is no longer needed.
     */
    int FORMAT_MESSAGE_ALLOCATE_BUFFER = 0x00000100;
    /**
     * Insert sequences in the message definition are to be ignored and passed through
     * to the output buffer unchanged. This flag is useful for fetching a message for
     * later formatting. If this flag is set, the Arguments parameter is ignored.
     */
    int FORMAT_MESSAGE_IGNORE_INSERTS = 0x00000200;
    /**
     * The lpSource parameter is a pointer to a null-terminated message definition.
     * The message definition may contain insert sequences, just as the message text
     * in a message table resource may. Cannot be used with FORMAT_MESSAGE_FROM_HMODULE
     * or FORMAT_MESSAGE_FROM_SYSTEM.
     */
    int FORMAT_MESSAGE_FROM_STRING = 0x00000400;
    /**
     * The lpSource parameter is a module handle containing the message-table
     * resource(s) to search. If this lpSource handle is NULL, the current process's
     * application image file will be searched. Cannot be used with
     * FORMAT_MESSAGE_FROM_STRING.
     */
    int FORMAT_MESSAGE_FROM_HMODULE = 0x00000800;
    /**
     * The function should search the system message-table resource(s) for the
     * requested message. If this flag is specified with FORMAT_MESSAGE_FROM_HMODULE,
     * the function searches the system message table if the message is not found in
     * the module specified by lpSource. Cannot be used with FORMAT_MESSAGE_FROM_STRING.
     * If this flag is specified, an application can pass the result of the
     * GetLastError function to retrieve the message text for a system-defined error.
     */
    int FORMAT_MESSAGE_FROM_SYSTEM = 0x00001000;
    /**
     * The Arguments parameter is not a va_list structure, but is a pointer to an array
     * of values that represent the arguments. This flag cannot be used with 64-bit
     * argument values. If you are using 64-bit values, you must use the va_list
     * structure.
     */
    int FORMAT_MESSAGE_ARGUMENT_ARRAY = 0x00002000;

    /**
     * The drive type cannot be determined.
     */
    int DRIVE_UNKNOWN = 0;
    /**
     * The root path is invalid, for example, no volume is mounted at the path.
     */
    int DRIVE_NO_ROOT_DIR = 1;
    /**
     * The drive is a type that has removable media, for example, a floppy drive
     * or removable hard disk.
     */
    int DRIVE_REMOVABLE = 2;
    /**
     * The drive is a type that cannot be removed, for example, a fixed hard drive.
     */
    int DRIVE_FIXED = 3;
    /**
     * The drive is a remote (network) drive.
     */
    int DRIVE_REMOTE = 4;
    /**
     * The drive is a CD-ROM drive.
     */
    int DRIVE_CDROM = 5;
    /**
     * The drive is a RAM disk.
     */
    int DRIVE_RAMDISK = 6;


    int INFINITE = 0xFFFFFFFF;


    /**
     * If the file is to be moved to a different volume, the function simulates the move by using the CopyFile and DeleteFile functions.
     * <p>
     * This value cannot be used with MOVEFILE_DELAY_UNTIL_REBOOT.
     */
    int MOVEFILE_COPY_ALLOWED = 0x2;

    /**
     * Reserved for future use.
     */
    int MOVEFILE_CREATE_HARDLINK = 0x10;

    /**
     * The system does not move the file until the operating system is restarted. The system moves the file immediately
     * after AUTOCHK is executed, but before creating any paging files. Consequently, this parameter enables the
     * function to delete paging files from previous startups.
     * <p>
     * This value can be used only if the process is in the context of a user who belongs to the administrators group or
     * the LocalSystem account.
     * <p>
     * This value cannot be used with MOVEFILE_COPY_ALLOWED.
     * <p>
     * Windows Server 2003 and Windows XP:  For information about special situations where this functionality can fail,
     * and a suggested workaround solution, see Files are not exchanged when Windows Server 2003 restarts if you use the
     * MoveFileEx function to schedule a replacement for some files in the Help and Support Knowledge Base.
     * <p>
     * Windows 2000:  If you specify the MOVEFILE_DELAY_UNTIL_REBOOT flag for dwFlags, you cannot also prepend the file
     * name that is specified by lpExistingFileName with "\\?".
     */
    int MOVEFILE_DELAY_UNTIL_REBOOT = 0x4;

    /**
     * The function fails if the source file is a link source, but the file cannot be tracked after the move. This
     * situation can occur if the destination is a volume formatted with the FAT file system.
     */
    int MOVEFILE_FAIL_IF_NOT_TRACKABLE = 0x20;

    /**
     * If a file named lpNewFileName exists, the function replaces its contents with the contents of the
     * lpExistingFileName file, provided that security requirements regarding access control lists (ACLs) are met. For
     * more information, see the Remarks section of this topic.
     * <p>
     * This value cannot be used if lpNewFileName or lpExistingFileName names a directory.
     */
    int MOVEFILE_REPLACE_EXISTING = 0x1;

    /**
     * The function does not return until the file is actually moved on the disk.
     * <p>
     * Setting this value guarantees that a move performed as a copy and delete operation is flushed to disk before the
     * function returns. The flush occurs at the end of the copy operation.
     * <p>
     * This value has no effect if MOVEFILE_DELAY_UNTIL_REBOOT is set.
     */
    int MOVEFILE_WRITE_THROUGH = 0x8;


    int PIPE_CLIENT_END = 0x00000000;
    int PIPE_SERVER_END = 0x00000001;

    /* Pipe open mode values */
    int PIPE_ACCESS_DUPLEX = 0x00000003;
    int PIPE_ACCESS_INBOUND = 0x00000001;
    int PIPE_ACCESS_OUTBOUND = 0x00000002;

    /* Pipe type values */
    int PIPE_TYPE_BYTE = 0x00000000;
    int PIPE_TYPE_MESSAGE = 0x00000004;

    /* Pipe read modes */
    int PIPE_READMODE_BYTE = 0x00000000;
    int PIPE_READMODE_MESSAGE = 0x00000002;

    /* Pipe wait modes */
    int PIPE_WAIT = 0x00000000;
    int PIPE_NOWAIT = 0x00000001;

    int PIPE_ACCEPT_REMOTE_CLIENTS = 0x00000000;
    int PIPE_REJECT_REMOTE_CLIENTS = 0x00000008;

    int PIPE_UNLIMITED_INSTANCES = 255;

    /* Named pipe pre-defined timeout values */
    int NMPWAIT_USE_DEFAULT_WAIT = 0x00000000;
    int NMPWAIT_NOWAIT = 0x00000001;
    int NMPWAIT_WAIT_FOREVER = 0xffffffff;


    /**
     * No parity.
     */
    int NOPARITY = 0;

    /**
     * Odd parity.
     */
    int ODDPARITY = 1;

    /**
     * Even parity.
     */
    int EVENPARITY = 2;

    /**
     * Mark parity.
     */
    int MARKPARITY = 3;

    /**
     * Space parity.
     */
    int SPACEPARITY = 4;

    /**
     * 1 stop bit.
     */
    int ONESTOPBIT = 0;

    /**
     * 1.5 stop bits.
     */
    int ONE5STOPBITS = 1;
    /**
     * 2 stop bits.
     */
    int TWOSTOPBITS = 2;
    /**
     * 110 bps.
     */
    int CBR_110 = 110;
    /**
     * 300 bps.
     */
    int CBR_300 = 300;
    /**
     * 600 bps.
     */
    int CBR_600 = 600;
    /**
     * 1200 bps.
     */
    int CBR_1200 = 1200;
    /**
     * 2400 bps.
     */
    int CBR_2400 = 2400;
    /**
     * 4800 bps.
     */
    int CBR_4800 = 4800;
    /**
     * 9600 bps.
     */
    int CBR_9600 = 9600;
    /**
     * 14400 bps.
     */
    int CBR_14400 = 14400;
    /**
     * 19200 bps.
     */
    int CBR_19200 = 19200;
    /**
     * 38400 bps.
     */
    int CBR_38400 = 38400;
    /**
     * 56000 bps.
     */
    int CBR_56000 = 56000;

    /**
     * 128000 bps.
     */
    int CBR_128000 = 128000;

    /**
     * 256000 bps.
     */
    int CBR_256000 = 256000;

    /**
     * Disables the DTR line when the device is opened and leaves it disabled.
     */
    int DTR_CONTROL_DISABLE = 0;

    /**
     * Enables the DTR line when the device is opened and leaves it on.
     */
    int DTR_CONTROL_ENABLE = 1;

    /**
     * Enables DTR handshaking.<br>
     * If handshaking is enabled, it is an error for the application to adjust
     * the line by using the EscapeCommFunction function.
     */
    int DTR_CONTROL_HANDSHAKE = 2;

    /**
     * Disables the RTS line when the device is opened and leaves it disabled.
     */
    int RTS_CONTROL_DISABLE = 0;

    /**
     * Enables the RTS line when the device is opened and leaves it on.
     */
    int RTS_CONTROL_ENABLE = 1;

    /**
     * Enables RTS handshaking.<br>
     * The driver raises the RTS line when the "type-ahead" (input) buffer is
     * less than one-half full and lowers the RTS line when the buffer is more
     * than three-quarters full.<br>
     * If handshaking is enabled, it is an error for the application to adjust
     * the line by using the EscapeCommFunction function.
     */
    int RTS_CONTROL_HANDSHAKE = 2;

    /**
     * Specifies that the RTS line will be high if bytes are available for
     * transmission.<br>
     * After all buffered bytes have been sent, the RTS line will be low.
     */
    int RTS_CONTROL_TOGGLE = 3;


    /**
     * Enables away mode. This value must be specified with {@link #ES_CONTINUOUS}.
     * <p>
     * Away mode should be used only by media-recording and media-distribution
     * applications that must perform critical background processing on desktop
     * computers while the computer appears to be sleeping. See Remarks.
     */
    int ES_AWAYMODE_REQUIRED = 0x00000040;
    /**
     * Informs the system that the state being set should remain in effect until
     * the next call that uses ES_CONTINUOUS and one of the other state flags is
     * cleared.
     */
    int ES_CONTINUOUS = 0x80000000;
    /**
     * Forces the display to be on by resetting the display idle timer.
     */
    int ES_DISPLAY_REQUIRED = 0x00000002;
    /**
     * Forces the system to be in the working state by resetting the system idle
     * timer.
     */
    int ES_SYSTEM_REQUIRED = 0x00000001;
    /**
     * This value is not supported. If ES_USER_PRESENT is combined with other
     * esFlags values, the call will fail and none of the specified states will
     * be set.
     */
    int ES_USER_PRESENT = 0x00000004;

    /**
     * The SECURITY_ATTRIBUTES structure contains the security descriptor for an
     * object and specifies whether the handle retrieved by specifying this
     * structure is inheritable. This structure provides security settings for
     * objects created by various functions, such as {@link Kernel32#CreateFile},
     * {@link Kernel32#CreatePipe}, or {@link Advapi32#RegCreateKeyEx}.
     */
    public static class SECURITY_ATTRIBUTES extends Struct {
        /**
         * The size of the structure, in bytes.
         */
        public DWORD dwLength;

        /**
         * A pointer to a SECURITY_DESCRIPTOR structure that controls access to the object.
         */
        public Pointer lpSecurityDescriptor;

        /**
         * A Boolean value that specifies whether the returned handle is inherited when
         * a new process is created
         */
        public boolean bInheritHandle;

        protected List<java.lang.String> getFieldOrder() {
            return Arrays.asList("dwLength", "lpSecurityDescriptor", "bInheritHandle");
        }

        public SECURITY_ATTRIBUTES(final Runtime runtime) {
            super(runtime);
            dwLength = new DWORD();
        }
    }
}
