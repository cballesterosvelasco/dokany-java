/* Copyright (c) 2007 Timothy Wall, All Rights Reserved
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

/**
 * This module defines the 32-Bit Windows types and constants that are defined
 * by NT, but exposed through the Win32 API. Ported from WinNT.h Microsoft
 * Windows SDK 6.0A. Avoid including any NIO Buffer mappings here; put them in a
 * DLL-derived interface (e.g. kernel32, user32, etc) instead.
 *
 * @author dblock[at]dblock.org
 */
public interface WinNT extends com.sun.jna.platform.win32.WinError, com.sun.jna.platform.win32.WinBase {

    int MINCHAR = 0x80;
    int MAXCHAR = 0x7f;
    int MINSHORT = 0x8000;
    int MAXSHORT = 0x7fff;
    int MINLONG = 0x80000000;
    int MAXLONG = 0x7fffffff;
    int MAXBYTE = 0xff;
    int MAXWORD = 0xffff;
    int MAXDWORD = 0xffffffff;
    //
    // The following are masks for the predefined standard access types
    //

    int DELETE = 0x00010000;
    int READ_CONTROL = 0x00020000;
    int WRITE_DAC = 0x00040000;
    int WRITE_OWNER = 0x00080000;
    int SYNCHRONIZE = 0x00100000;

    int STANDARD_RIGHTS_REQUIRED = 0x000F0000;
    int STANDARD_RIGHTS_READ = READ_CONTROL;
    int STANDARD_RIGHTS_WRITE = READ_CONTROL;
    int STANDARD_RIGHTS_EXECUTE = READ_CONTROL;
    int STANDARD_RIGHTS_ALL = 0x001F0000;

    int SPECIFIC_RIGHTS_ALL = 0x0000FFFF;

    //
    // Token Specific Access Rights.
    //

    /**
     * Required to attach a primary token to a process. The
     * SE_ASSIGNPRIMARYTOKEN_NAME privilege is also required to accomplish this
     * task.
     */
    int TOKEN_ASSIGN_PRIMARY = 0x0001;

    /**
     * Required to duplicate an access token.
     */
    int TOKEN_DUPLICATE = 0x0002;

    /**
     * Required to attach an impersonation access token to a process.
     */
    int TOKEN_IMPERSONATE = 0x0004;

    /**
     * Required to query an access token.
     */
    int TOKEN_QUERY = 0x0008;

    /**
     * Required to query the source of an access token.
     */
    int TOKEN_QUERY_SOURCE = 0x0010;

    /**
     * Required to enable or disable the privileges in an access token.
     */
    int TOKEN_ADJUST_PRIVILEGES = 0x0020;

    /**
     * Required to adjust the attributes of the groups in an access token.
     */
    int TOKEN_ADJUST_GROUPS = 0x0040;

    /**
     * Required to change the default owner, primary group, or DACL of an access
     * token.
     */
    int TOKEN_ADJUST_DEFAULT = 0x0080;

    /**
     * Required to adjust the session ID of an access token. The SE_TCB_NAME
     * privilege is required.
     */
    int TOKEN_ADJUST_SESSIONID = 0x0100;

    int TOKEN_ALL_ACCESS_P = STANDARD_RIGHTS_REQUIRED | TOKEN_ASSIGN_PRIMARY
            | TOKEN_DUPLICATE | TOKEN_IMPERSONATE | TOKEN_QUERY
            | TOKEN_QUERY_SOURCE | TOKEN_ADJUST_PRIVILEGES
            | TOKEN_ADJUST_GROUPS | TOKEN_ADJUST_DEFAULT;

    /**
     * Combines all possible access rights for a token.
     */
    int TOKEN_ALL_ACCESS = TOKEN_ALL_ACCESS_P | TOKEN_ADJUST_SESSIONID;

    /**
     * Combines STANDARD_RIGHTS_READ and TOKEN_QUERY.
     */
    int TOKEN_READ = STANDARD_RIGHTS_READ | TOKEN_QUERY;

    /**
     * Combines STANDARD_RIGHTS_WRITE, TOKEN_ADJUST_PRIVILEGES,
     * TOKEN_ADJUST_GROUPS, and TOKEN_ADJUST_DEFAULT.
     */
    int TOKEN_WRITE = STANDARD_RIGHTS_WRITE | TOKEN_ADJUST_PRIVILEGES
            | TOKEN_ADJUST_GROUPS | TOKEN_ADJUST_DEFAULT;

    /**
     * Combines STANDARD_RIGHTS_EXECUTE and TOKEN_IMPERSONATE.
     */
    int TOKEN_EXECUTE = STANDARD_RIGHTS_EXECUTE;

    int THREAD_TERMINATE = 0x0001;
    int THREAD_SUSPEND_RESUME = 0x0002;
    int THREAD_GET_CONTEXT = 0x0008;
    int THREAD_SET_CONTEXT = 0x0010;
    int THREAD_QUERY_INFORMATION = 0x0040;
    int THREAD_SET_INFORMATION = 0x0020;
    int THREAD_SET_THREAD_TOKEN = 0x0080;
    int THREAD_IMPERSONATE = 0x0100;
    int THREAD_DIRECT_IMPERSONATION = 0x0200;
    int THREAD_SET_LIMITED_INFORMATION = 0x0400;
    int THREAD_QUERY_LIMITED_INFORMATION = 0x0800;
    int THREAD_ALL_ACCESS = STANDARD_RIGHTS_REQUIRED | SYNCHRONIZE | 0x3FF;

    /* File access rights */
    int FILE_READ_DATA = 0x00000001;
    int FILE_LIST_DIRECTORY = 0x00000001;
    int FILE_WRITE_DATA = 0x00000002;
    int FILE_ADD_FILE = 0x00000002;
    int FILE_APPEND_DATA = 0x00000004;
    int FILE_ADD_SUBDIRECTORY = 0x00000004;
    int FILE_CREATE_PIPE_INSTANCE = 0x00000004;
    int FILE_READ_EA = 0x00000008;
    int FILE_WRITE_EA = 0x00000010;
    int FILE_EXECUTE = 0x00000020;
    int FILE_TRAVERSE = 0x00000020;
    int FILE_DELETE_CHILD = 0x00000040;
    int FILE_READ_ATTRIBUTES = 0x00000080;
    int FILE_WRITE_ATTRIBUTES = 0x00000100;

    int FILE_ALL_ACCESS = STANDARD_RIGHTS_REQUIRED | SYNCHRONIZE | 0x000001FF;

    int FILE_GENERIC_READ = STANDARD_RIGHTS_READ | SYNCHRONIZE | FILE_READ_DATA
            | FILE_READ_ATTRIBUTES | FILE_READ_EA;

    int FILE_GENERIC_WRITE = STANDARD_RIGHTS_WRITE | SYNCHRONIZE
            | FILE_WRITE_DATA | FILE_WRITE_ATTRIBUTES | FILE_WRITE_EA
            | FILE_APPEND_DATA;

    int FILE_GENERIC_EXECUTE = STANDARD_RIGHTS_EXECUTE | SYNCHRONIZE
            | FILE_READ_ATTRIBUTES | FILE_EXECUTE;

    int CREATE_NEW = 1;
    int CREATE_ALWAYS = 2;
    int OPEN_EXISTING = 3;
    int OPEN_ALWAYS = 4;
    int TRUNCATE_EXISTING = 5;

    int FILE_FLAG_WRITE_THROUGH = 0x80000000;
    int FILE_FLAG_OVERLAPPED = 0x40000000;
    int FILE_FLAG_NO_BUFFERING = 0x20000000;
    int FILE_FLAG_RANDOM_ACCESS = 0x10000000;
    int FILE_FLAG_SEQUENTIAL_SCAN = 0x08000000;
    int FILE_FLAG_DELETE_ON_CLOSE = 0x04000000;
    int FILE_FLAG_BACKUP_SEMANTICS = 0x02000000;
    int FILE_FLAG_POSIX_SEMANTICS = 0x01000000;
    int FILE_FLAG_OPEN_REPARSE_POINT = 0x00200000;
    int FILE_FLAG_OPEN_NO_RECALL = 0x00100000;

    //
    // These are the generic rights.
    //

    int GENERIC_READ = 0x80000000;
    int GENERIC_WRITE = 0x40000000;
    int GENERIC_EXECUTE = 0x20000000;
    int GENERIC_ALL = 0x10000000;

    //
    // AccessSystemAcl access type
    //

    int ACCESS_SYSTEM_SECURITY = 0x01000000;

    /**
     * Pages in the region become guard pages. <br>
     * Any attempt to access a guard page causes the system to raise a
     * STATUS_GUARD_PAGE_VIOLATION exception and turn off the guard page status.
     * <br>
     * Guard pages thus act as a one-time access alarm. <br>
     * For more information, see Creating Guard Pages. <br>
     * When an access attempt leads the system to turn off guard page status,
     * the underlying page protection takes over.<br>
     * If a guard page exception occurs during a system service, the service
     * typically returns a failure status indicator. <br>
     * This value cannot be used with PAGE_NOACCESS. This flag is not supported
     * by the CreateFileMapping function.
     *
     * @see <a href=
     * "https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">
     * MSDN</a>
     */
    int PAGE_GUARD = 0x100;

    /**
     * Disables all access to the committed region of pages.<br>
     * An attempt to read from, write to, or execute the committed region
     * results in an access violation.<br>
     * This flag is not supported by the CreateFileMapping function.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_NOACCESS = 0x01;

    /**
     * Enables read-only access to the committed region of pages.<br>
     * An attempt to write to the committed region results in an access
     * violation. <br>
     * If Data Execution Prevention is enabled, an attempt to execute code in
     * the committed region results in an access violation.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_READONLY = 0x02;

    /**
     * Enables read-only or read/write access to the committed region of pages. <br>
     * If Data Execution Prevention is enabled, attempting to execute code in
     * the committed region results in an access violation.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_READWRITE = 0x04;

    /**
     * Enables read-only or copy-on-write access to a mapped view of a file
     * mapping object. An attempt to write to a committed copy-on-write page
     * results in a private copy of the page being made for the process. The
     * private page is marked as PAGE_READWRITE, and the change is written to
     * the new page. If Data Execution Prevention is enabled, attempting to
     * execute code in the committed region results in an access violation.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx"> MSDN</a>
     */
    int PAGE_WRITECOPY = 0x08;

    /**
     * Enables execute access to the committed region of pages. An attempt to
     * write to the committed region results in an access violation. This flag
     * is not supported by the CreateFileMapping function.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_EXECUTE = 0x10;

    /**
     * Enables execute or read-only access to the committed region of pages. An
     * attempt to write to the committed region results in an access violation.
     * Windows Server 2003 and Windows XP: This attribute is not supported by
     * the CreateFileMapping function until Windows XP with SP2 and Windows
     * Server 2003 with SP1.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_EXECUTE_READ = 0x20;

    /**
     * Enables execute, read-only, or read/write access to the committed region
     * of pages. Windows Server 2003 and Windows XP: This attribute is not
     * supported by the CreateFileMapping function until Windows XP with SP2 and
     * Windows Server 2003 with SP1.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/windows/desktop/aa366786(v=vs.85).aspx">MSDN</a>
     */
    int PAGE_EXECUTE_READWRITE = 0x40;

    int SECTION_QUERY = 0x0001;
    int SECTION_MAP_WRITE = 0x0002;
    int SECTION_MAP_READ = 0x0004;
    int SECTION_MAP_EXECUTE = 0x0008;
    int SECTION_EXTEND_SIZE = 0x0010;

    int FILE_SHARE_READ = 0x00000001;
    int FILE_SHARE_WRITE = 0x00000002;
    int FILE_SHARE_DELETE = 0x00000004;
    int FILE_TYPE_CHAR = 0x0002;
    int FILE_TYPE_DISK = 0x0001;
    int FILE_TYPE_PIPE = 0x0003;
    int FILE_TYPE_REMOTE = 0x8000;
    int FILE_TYPE_UNKNOWN = 0x0000;
    int FILE_ATTRIBUTE_READONLY = 0x00000001;
    int FILE_ATTRIBUTE_HIDDEN = 0x00000002;
    int FILE_ATTRIBUTE_SYSTEM = 0x00000004;
    int FILE_ATTRIBUTE_DIRECTORY = 0x00000010;
    int FILE_ATTRIBUTE_ARCHIVE = 0x00000020;
    int FILE_ATTRIBUTE_DEVICE = 0x00000040;
    int FILE_ATTRIBUTE_NORMAL = 0x00000080;
    int FILE_ATTRIBUTE_TEMPORARY = 0x00000100;
    int FILE_ATTRIBUTE_SPARSE_FILE = 0x00000200;
    int FILE_ATTRIBUTE_REPARSE_POINT = 0x00000400;
    int FILE_ATTRIBUTE_COMPRESSED = 0x00000800;
    int FILE_ATTRIBUTE_OFFLINE = 0x00001000;
    int FILE_ATTRIBUTE_NOT_CONTENT_INDEXED = 0x00002000;
    int FILE_ATTRIBUTE_ENCRYPTED = 0x00004000;
    int FILE_ATTRIBUTE_VIRTUAL = 0x00010000;
    int FILE_NOTIFY_CHANGE_FILE_NAME = 0x00000001;
    int FILE_NOTIFY_CHANGE_DIR_NAME = 0x00000002;
    int FILE_NOTIFY_CHANGE_NAME = 0x00000003;
    int FILE_NOTIFY_CHANGE_ATTRIBUTES = 0x00000004;
    int FILE_NOTIFY_CHANGE_SIZE = 0x00000008;
    int FILE_NOTIFY_CHANGE_LAST_WRITE = 0x00000010;
    int FILE_NOTIFY_CHANGE_LAST_ACCESS = 0x00000020;
    int FILE_NOTIFY_CHANGE_CREATION = 0x00000040;
    int FILE_NOTIFY_CHANGE_SECURITY = 0x00000100;
    int FILE_ACTION_ADDED = 0x00000001;
    int FILE_ACTION_REMOVED = 0x00000002;
    int FILE_ACTION_MODIFIED = 0x00000003;
    int FILE_ACTION_RENAMED_OLD_NAME = 0x00000004;
    int FILE_ACTION_RENAMED_NEW_NAME = 0x00000005;
    int FILE_CASE_SENSITIVE_SEARCH = 0x00000001;
    int FILE_CASE_PRESERVED_NAMES = 0x00000002;
    int FILE_UNICODE_ON_DISK = 0x00000004;
    int FILE_PERSISTENT_ACLS = 0x00000008;
    int FILE_FILE_COMPRESSION = 0x00000010;
    int FILE_VOLUME_QUOTAS = 0x00000020;
    int FILE_SUPPORTS_SPARSE_FILES = 0x00000040;
    int FILE_SUPPORTS_REPARSE_POINTS = 0x00000080;
    int FILE_SUPPORTS_REMOTE_STORAGE = 0x00000100;
    int FILE_VOLUME_IS_COMPRESSED = 0x00008000;
    int FILE_SUPPORTS_OBJECT_IDS = 0x00010000;
    int FILE_SUPPORTS_ENCRYPTION = 0x00020000;
    int FILE_NAMED_STREAMS = 0x00040000;
    int FILE_READ_ONLY_VOLUME = 0x00080000;
    int FILE_SEQUENTIAL_WRITE_ONCE = 0x00100000;
    int FILE_SUPPORTS_TRANSACTIONS = 0x00200000;
    // NOTE: These values are not supported until Windows Server 2008 R2 and Windows 7
    int FILE_SUPPORTS_HARD_LINKS = 0x00400000;
    int FILE_SUPPORTS_EXTENDED_ATTRIBUTES = 0x00800000;
    int FILE_SUPPORTS_OPEN_BY_FILE_ID = 0x01000000;
    int FILE_SUPPORTS_USN_JOURNAL = 0x02000000;

    // Reparse point tags
    int IO_REPARSE_TAG_MOUNT_POINT = 0xA0000003;
    int IO_REPARSE_TAG_HSM = 0xC0000004;
    int IO_REPARSE_TAG_HSM2 = 0x80000006;
    int IO_REPARSE_TAG_SIS = 0x80000007;
    int IO_REPARSE_TAG_WIM = 0x80000008;
    int IO_REPARSE_TAG_CSV = 0x80000009;
    int IO_REPARSE_TAG_DFS = 0x8000000A;
    int IO_REPARSE_TAG_SYMLINK = 0xA000000C;
    int IO_REPARSE_TAG_DFSR = 0x80000012;

    // The controllable aspects of the DefineDosDevice function.
    // see https://msdn.microsoft.com/en-us/library/windows/desktop/aa363904(v=vs.85).aspx
    int DDD_RAW_TARGET_PATH = 0x00000001;
    int DDD_REMOVE_DEFINITION = 0x00000002;
    int DDD_EXACT_MATCH_ON_REMOVE = 0x00000004;
    int DDD_NO_BROADCAST_SYSTEM = 0x00000008;

    int COMPRESSION_FORMAT_NONE = 0x0000;
    int COMPRESSION_FORMAT_DEFAULT = 0x0001;
    int COMPRESSION_FORMAT_LZNT1 = 0x0002;
    int COMPRESSION_FORMAT_XPRESS = 0x0003;
    int COMPRESSION_FORMAT_XPRESS_HUFF = 0x0004;
    int COMPRESSION_ENGINE_STANDARD = 0x0000;
    int COMPRESSION_ENGINE_MAXIMUM = 0x0100;
    int COMPRESSION_ENGINE_HIBER = 0x0200;

    /**
     * Registry options.
     */
    int KEY_QUERY_VALUE = 0x0001;
    int KEY_SET_VALUE = 0x0002;
    int KEY_CREATE_SUB_KEY = 0x0004;
    int KEY_ENUMERATE_SUB_KEYS = 0x0008;
    int KEY_NOTIFY = 0x0010;
    int KEY_CREATE_LINK = 0x0020;
    int KEY_WOW64_32KEY = 0x0200;
    int KEY_WOW64_64KEY = 0x0100;
    int KEY_WOW64_RES = 0x0300;

    int KEY_READ = STANDARD_RIGHTS_READ | KEY_QUERY_VALUE
            | KEY_ENUMERATE_SUB_KEYS | KEY_NOTIFY & (~SYNCHRONIZE);

    int KEY_WRITE = STANDARD_RIGHTS_WRITE | KEY_SET_VALUE | KEY_CREATE_SUB_KEY
            & (~SYNCHRONIZE);

    int KEY_EXECUTE = KEY_READ & (~SYNCHRONIZE);

    int KEY_ALL_ACCESS = STANDARD_RIGHTS_ALL | KEY_QUERY_VALUE | KEY_SET_VALUE
            | KEY_CREATE_SUB_KEY | KEY_ENUMERATE_SUB_KEYS | KEY_NOTIFY
            | KEY_CREATE_LINK & (~SYNCHRONIZE);

    //
    // Open/Create Options
    //

    /**
     * Parameter is reserved.
     */
    int REG_OPTION_RESERVED = 0x00000000;

    /**
     * Key is preserved when system is rebooted.
     */
    int REG_OPTION_NON_VOLATILE = 0x00000000;

    /**
     * Key is not preserved when system is rebooted.
     */
    int REG_OPTION_VOLATILE = 0x00000001;

    /**
     * Created key is a symbolic link.
     */
    int REG_OPTION_CREATE_LINK = 0x00000002;

    /**
     * Open for backup or restore special access rules privilege required.
     */
    int REG_OPTION_BACKUP_RESTORE = 0x00000004;

    /**
     * Open symbolic link.
     */
    int REG_OPTION_OPEN_LINK = 0x00000008;

    int REG_LEGAL_OPTION = REG_OPTION_RESERVED | REG_OPTION_NON_VOLATILE
            | REG_OPTION_VOLATILE | REG_OPTION_CREATE_LINK
            | REG_OPTION_BACKUP_RESTORE | REG_OPTION_OPEN_LINK;

    //
    // Key creation/open disposition
    //

    /**
     * New Registry Key created.
     */
    int REG_CREATED_NEW_KEY = 0x00000001;

    /**
     * Existing Key opened.
     */
    int REG_OPENED_EXISTING_KEY = 0x00000002;

    int REG_STANDARD_FORMAT = 1;
    int REG_LATEST_FORMAT = 2;
    int REG_NO_COMPRESSION = 4;

    //
    // Key restore & hive load flags
    //

    /**
     * Restore whole hive volatile.
     */
    int REG_WHOLE_HIVE_VOLATILE = 0x00000001;

    /**
     * Unwind changes to last flush.
     */
    int REG_REFRESH_HIVE = 0x00000002;

    /**
     * Never lazy flush this hive.
     */
    int REG_NO_LAZY_FLUSH = 0x00000004;

    /**
     * Force the restore process even when we have open handles on subkeys.
     */
    int REG_FORCE_RESTORE = 0x00000008;

    /**
     * Loads the hive visible to the calling process.
     */
    int REG_APP_HIVE = 0x00000010;

    /**
     * Hive cannot be mounted by any other process while in use.
     */
    int REG_PROCESS_PRIVATE = 0x00000020;

    /**
     * Starts Hive Journal.
     */
    int REG_START_JOURNAL = 0x00000040;

    /**
     * Grow hive file in exact 4k increments.
     */
    int REG_HIVE_EXACT_FILE_GROWTH = 0x00000080;

    /**
     * No RM is started for this hive = no transactions.
     */
    int REG_HIVE_NO_RM = 0x00000100;

    /**
     * Legacy single logging is used for this hive.
     */
    int REG_HIVE_SINGLE_LOG = 0x00000200;

    //
    // Unload Flags
    //

    int REG_FORCE_UNLOAD = 1;

    //
    // Notify filter values
    //

    int REG_NOTIFY_CHANGE_NAME = 0x00000001;
    int REG_NOTIFY_CHANGE_ATTRIBUTES = 0x00000002;
    int REG_NOTIFY_CHANGE_LAST_SET = 0x00000004;
    int REG_NOTIFY_CHANGE_SECURITY = 0x00000008;

    int REG_LEGAL_CHANGE_FILTER = REG_NOTIFY_CHANGE_NAME
            | REG_NOTIFY_CHANGE_ATTRIBUTES | REG_NOTIFY_CHANGE_LAST_SET
            | REG_NOTIFY_CHANGE_SECURITY;

    //
    // Predefined Value Types.
    //

    /**
     * No value type.
     */
    int REG_NONE = 0;

    /**
     * Unicode null-terminated string.
     */
    int REG_SZ = 1;

    /**
     * Unicode null-terminated string with environment variable references.
     */
    int REG_EXPAND_SZ = 2;

    /**
     * Free-formed binary.
     */
    int REG_BINARY = 3;

    /**
     * 32-bit number.
     */
    int REG_DWORD = 4;

    /**
     * 32-bit number, same as REG_DWORD.
     */
    int REG_DWORD_LITTLE_ENDIAN = 4;

    /**
     * 32-bit number.
     */
    int REG_DWORD_BIG_ENDIAN = 5;

    /**
     * Symbolic link (unicode).
     */
    int REG_LINK = 6;

    /**
     * Multiple unicode strings.
     */
    int REG_MULTI_SZ = 7;

    /**
     * Resource list in the resource map.
     */
    int REG_RESOURCE_LIST = 8;

    /**
     * Resource list in the hardware description.
     */
    int REG_FULL_RESOURCE_DESCRIPTOR = 9;

    /**
     *
     */
    int REG_RESOURCE_REQUIREMENTS_LIST = 10;

    /**
     * 64-bit number.
     */
    int REG_QWORD = 11;

    /**
     * 64-bit number, same as REG_QWORD.
     */
    int REG_QWORD_LITTLE_ENDIAN = 11;

    /**
     * Current SID revision level.
     */
    int SID_REVISION = 1;
    int SID_MAX_SUB_AUTHORITIES = 15;
    int SID_RECOMMENDED_SUB_AUTHORITIES = 1;

    /**
     * Maximum bytes used by a SID. (sizeof(SID) - sizeof(DWORD) +
     * (SID_MAX_SUB_AUTHORITIES * sizeof(DWORD)))
     */
    int SECURITY_MAX_SID_SIZE = 68;

    int VER_EQUAL = 1;
    int VER_GREATER = 2;
    int VER_GREATER_EQUAL = 3;
    int VER_LESS = 4;
    int VER_LESS_EQUAL = 5;
    int VER_AND = 6;
    int VER_OR = 7;

    int VER_CONDITION_MASK = 7;
    int VER_NUM_BITS_PER_CONDITION_MASK = 3;

    int VER_MINORVERSION = 0x0000001;
    int VER_MAJORVERSION = 0x0000002;
    int VER_BUILDNUMBER = 0x0000004;
    int VER_PLATFORMID = 0x0000008;
    int VER_SERVICEPACKMINOR = 0x0000010;
    int VER_SERVICEPACKMAJOR = 0x0000020;
    int VER_SUITENAME = 0x0000040;
    int VER_PRODUCT_TYPE = 0x0000080;

    int VER_NT_WORKSTATION = 0x0000001;
    int VER_NT_DOMAIN_CONTROLLER = 0x0000002;
    int VER_NT_SERVER = 0x0000003;

    int VER_PLATFORM_WIN32s = 0;
    int VER_PLATFORM_WIN32_WINDOWS = 1;
    int VER_PLATFORM_WIN32_NT = 2;

    /**
     * Read the records sequentially. If this is the first read operation, the
     * EVENTLOG_FORWARDS_READ EVENTLOG_BACKWARDS_READ flags determines which
     * record is read first.
     */
    int EVENTLOG_SEQUENTIAL_READ = 0x0001;

    /**
     * Begin reading from the record specified in the dwRecordOffset parameter.
     * This option may not work with large log files if the function cannot
     * determine the log file's size. For details, see Knowledge Base article,
     * 177199.
     */
    int EVENTLOG_SEEK_READ = 0x0002;

    /**
     * The log is read in chronological order (oldest to newest). The default.
     */
    int EVENTLOG_FORWARDS_READ = 0x0004;

    /**
     * The log is read in reverse chronological order (newest to oldest).
     */
    int EVENTLOG_BACKWARDS_READ = 0x0008;

    /**
     * Information event
     */
    int EVENTLOG_SUCCESS = 0x0000;

    /**
     * Error event
     */
    int EVENTLOG_ERROR_TYPE = 0x0001;

    /**
     * Warning event
     */
    int EVENTLOG_WARNING_TYPE = 0x0002;

    /**
     * Information event
     */
    int EVENTLOG_INFORMATION_TYPE = 0x0004;

    /**
     * Success Audit event
     */
    int EVENTLOG_AUDIT_SUCCESS = 0x0008;

    /**
     * Failure Audit event
     */
    int EVENTLOG_AUDIT_FAILURE = 0x0010;

    //
    // Service Types (Bit Mask)
    //
    int SERVICE_KERNEL_DRIVER = 0x00000001;
    int SERVICE_FILE_SYSTEM_DRIVER = 0x00000002;
    int SERVICE_ADAPTER = 0x00000004;
    int SERVICE_RECOGNIZER_DRIVER = 0x00000008;
    int SERVICE_DRIVER = SERVICE_KERNEL_DRIVER | SERVICE_FILE_SYSTEM_DRIVER
            | SERVICE_RECOGNIZER_DRIVER;
    int SERVICE_WIN32_OWN_PROCESS = 0x00000010;
    int SERVICE_WIN32_SHARE_PROCESS = 0x00000020;
    int SERVICE_WIN32 = SERVICE_WIN32_OWN_PROCESS | SERVICE_WIN32_SHARE_PROCESS;
    int SERVICE_INTERACTIVE_PROCESS = 0x00000100;
    int SERVICE_TYPE_ALL = SERVICE_WIN32 | SERVICE_ADAPTER | SERVICE_DRIVER
            | SERVICE_INTERACTIVE_PROCESS;

    //
    // Start Type
    //

    int SERVICE_BOOT_START = 0x00000000;
    int SERVICE_SYSTEM_START = 0x00000001;
    int SERVICE_AUTO_START = 0x00000002;
    int SERVICE_DEMAND_START = 0x00000003;
    int SERVICE_DISABLED = 0x00000004;

    //
    // Error control type
    //
    int SERVICE_ERROR_IGNORE = 0x00000000;
    int SERVICE_ERROR_NORMAL = 0x00000001;
    int SERVICE_ERROR_SEVERE = 0x00000002;
    int SERVICE_ERROR_CRITICAL = 0x00000003;

    int STATUS_PENDING = 0x00000103;

    // Privilege Constants
    String SE_CREATE_TOKEN_NAME = "SeCreateTokenPrivilege";
    String SE_ASSIGNPRIMARYTOKEN_NAME = "SeAssignPrimaryTokenPrivilege";
    String SE_LOCK_MEMORY_NAME = "SeLockMemoryPrivilege";
    String SE_INCREASE_QUOTA_NAME = "SeIncreaseQuotaPrivilege";
    String SE_UNSOLICITED_INPUT_NAME = "SeUnsolicitedInputPrivilege";
    String SE_MACHINE_ACCOUNT_NAME = "SeMachineAccountPrivilege";
    String SE_TCB_NAME = "SeTcbPrivilege";
    String SE_SECURITY_NAME = "SeSecurityPrivilege";
    String SE_TAKE_OWNERSHIP_NAME = "SeTakeOwnershipPrivilege";
    String SE_LOAD_DRIVER_NAME = "SeLoadDriverPrivilege";
    String SE_SYSTEM_PROFILE_NAME = "SeSystemProfilePrivilege";
    String SE_SYSTEMTIME_NAME = "SeSystemtimePrivilege";
    String SE_PROF_SINGLE_PROCESS_NAME = "SeProfileSingleProcessPrivilege";
    String SE_INC_BASE_PRIORITY_NAME = "SeIncreaseBasePriorityPrivilege";
    String SE_CREATE_PAGEFILE_NAME = "SeCreatePagefilePrivilege";
    String SE_CREATE_PERMANENT_NAME = "SeCreatePermanentPrivilege";
    String SE_BACKUP_NAME = "SeBackupPrivilege";
    String SE_RESTORE_NAME = "SeRestorePrivilege";
    String SE_SHUTDOWN_NAME = "SeShutdownPrivilege";
    String SE_DEBUG_NAME = "SeDebugPrivilege";
    String SE_AUDIT_NAME = "SeAuditPrivilege";
    String SE_SYSTEM_ENVIRONMENT_NAME = "SeSystemEnvironmentPrivilege";
    String SE_CHANGE_NOTIFY_NAME = "SeChangeNotifyPrivilege";
    String SE_REMOTE_SHUTDOWN_NAME = "SeRemoteShutdownPrivilege";
    String SE_UNDOCK_NAME = "SeUndockPrivilege";
    String SE_SYNC_AGENT_NAME = "SeSyncAgentPrivilege";
    String SE_ENABLE_DELEGATION_NAME = "SeEnableDelegationPrivilege";
    String SE_MANAGE_VOLUME_NAME = "SeManageVolumePrivilege";
    String SE_IMPERSONATE_NAME = "SeImpersonatePrivilege";
    String SE_CREATE_GLOBAL_NAME = "SeCreateGlobalPrivilege";

    int SE_PRIVILEGE_ENABLED_BY_DEFAULT = 0x00000001;
    int SE_PRIVILEGE_ENABLED = 0x00000002;
    int SE_PRIVILEGE_REMOVED = 0X00000004;
    int SE_PRIVILEGE_USED_FOR_ACCESS = 0x80000000;

    /**
     * Required to create a process.
     */
    int PROCESS_CREATE_PROCESS = 0x0080;

    /**
     * Required to create a thread.
     */
    int PROCESS_CREATE_THREAD = 0x0002;

    /**
     * Required to duplicate a handle using
     * {@link Kernel32#DuplicateHandle}
     * .
     */
    int PROCESS_DUP_HANDLE = 0x0040;

    /**
     * All possible access rights for a process object. Windows Server 2003 and
     * Windows XP: The size of the PROCESS_ALL_ACCESS flag increased on Windows
     * Server 2008 and Windows Vista. <br>
     * If an application compiled for Windows Server 2008 and Windows Vista is
     * run on Windows Server 2003 or Windows XP, the PROCESS_ALL_ACCESS flag is
     * too large and the function specifying this flag fails with
     * ERROR_ACCESS_DENIED.<br>
     * To avoid this problem, specify the minimum set of access rights required
     * for the operation.<br>
     * If PROCESS_ALL_ACCESS must be used, set _WIN32_WINNT to the minimum
     * operating system targeted by your application (for example, #define
     * _WIN32_WINNT _WIN32_WINNT_WINXP).<br>
     * For more information, see Using the Windows Headers.
     *
     * @see <a href="https://msdn.microsoft.com/en-us/library/ms684880(v=VS.85).aspx">MSDN</a>
     */
    int PROCESS_ALL_ACCESS = WinNT.PROCESS_CREATE_PROCESS
            | WinNT.PROCESS_CREATE_THREAD
            | WinNT.PROCESS_DUP_HANDLE
            | WinNT.PROCESS_QUERY_INFORMATION
            | WinNT.PROCESS_QUERY_LIMITED_INFORMATION
            | WinNT.PROCESS_SET_INFORMATION
            | WinNT.PROCESS_SET_QUOTA
            | WinNT.PROCESS_SUSPEND_RESUME
            | WinNT.PROCESS_SYNCHRONIZE
            | WinNT.PROCESS_TERMINATE
            | WinNT.PROCESS_VM_OPERATION
            | WinNT.PROCESS_VM_READ
            | WinNT.PROCESS_VM_WRITE
            | WinNT.DELETE
            | WinNT.READ_CONTROL
            | WinNT.WRITE_DAC
            | WinNT.WRITE_OWNER
            | WinNT.SYNCHRONIZE;

    /**
     * Required to retrieve certain information about a process, such as its
     * token, exit code, and priority class (see
     * {@link Advapi32#OpenProcessToken}).
     */
    int PROCESS_QUERY_INFORMATION = 0x0400;

    /**
     * Required to retrieve certain information about a process (see
     * {@link Kernel32#GetExitCodeProcess}
     * , {@code Kernel32#GetPriorityClass}, {@code Kernel32#IsProcessInJob},
     * {@code Kernel32.QueryFullProcessImageName}). A handle that has the
     * {@link #PROCESS_QUERY_INFORMATION} access right is automatically granted
     * {@link #PROCESS_QUERY_LIMITED_INFORMATION}.
     * <p>
     * Windows Server 2003 and Windows XP: This access right is not supported.
     */
    int PROCESS_QUERY_LIMITED_INFORMATION = 0x1000;

    /**
     * Required to set certain information about a process, such as its priority
     * class (see {@code Kernel32#SetPriorityClass}).
     */
    int PROCESS_SET_INFORMATION = 0x0200;

    /**
     * Required to set memory limits using
     * {@code Kernel32.SetProcessWorkingSetSize()}.
     */
    int PROCESS_SET_QUOTA = 0x0100;

    /**
     * Required to suspend or resume a process.
     */
    int PROCESS_SUSPEND_RESUME = 0x0800;

    /**
     * Required to terminate a process using
     * {@link Kernel32#TerminateProcess}.
     */
    int PROCESS_TERMINATE = 0x00000001;

    /**
     * Required for getting process exe path in native system path format
     * {@code Kernel32.QueryFullProcessImageName()}.
     */
    int PROCESS_NAME_NATIVE = 0x00000001;

    /**
     * Required to perform an operation on the address space of a process (see
     * {@code Kernel32.VirtualProtectEx()} and
     * {@link Kernel32#WriteProcessMemory}
     * ).
     */
    int PROCESS_VM_OPERATION = 0x0008;

    /**
     * Required to read memory in a process using
     * {@link Kernel32#ReadProcessMemory}
     * .
     */
    int PROCESS_VM_READ = 0x0010;

    /**
     * Required to write to memory in a process using
     * {@link Kernel32#WriteProcessMemory}
     * .
     */
    int PROCESS_VM_WRITE = 0x0020;

    /**
     * Required to wait for the process to terminate using the wait functions.
     */
    int PROCESS_SYNCHRONIZE = 0x00100000;

    /* Security information types */
    int OWNER_SECURITY_INFORMATION = 0x00000001;
    int GROUP_SECURITY_INFORMATION = 0x00000002;
    int DACL_SECURITY_INFORMATION = 0x00000004;
    int SACL_SECURITY_INFORMATION = 0x00000008;
    int LABEL_SECURITY_INFORMATION = 0x00000010;
    int PROTECTED_DACL_SECURITY_INFORMATION = 0x80000000;
    int PROTECTED_SACL_SECURITY_INFORMATION = 0x40000000;
    int UNPROTECTED_DACL_SECURITY_INFORMATION = 0x20000000;
    int UNPROTECTED_SACL_SECURITY_INFORMATION = 0x10000000;

    /* Security control bits */
    int SE_OWNER_DEFAULTED = 0x00000001;
    int SE_GROUP_DEFAULTED = 0x00000002;
    int SE_DACL_PRESENT = 0x00000004;
    int SE_DACL_DEFAULTED = 0x00000008;
    int SE_SACL_PRESENT = 0x00000010;
    int SE_SACL_DEFAULTED = 0x00000020;
    int SE_DACL_AUTO_INHERIT_REQ = 0x00000100;
    int SE_SACL_AUTO_INHERIT_REQ = 0x00000200;
    int SE_DACL_AUTO_INHERITED = 0x00000400;
    int SE_SACL_AUTO_INHERITED = 0x00000800;
    int SE_DACL_PROTECTED = 0x00001000;
    int SE_SACL_PROTECTED = 0x00002000;
    int SE_RM_CONTROL_VALID = 0x00004000;
    int SE_SELF_RELATIVE = 0x00008000;

    int SECURITY_DESCRIPTOR_REVISION = 0x00000001;

    int ACL_REVISION = 2;
    int ACL_REVISION_DS = 4;

    // This is the history of ACL revisions.  Add a new one whenever
    // ACL_REVISION is updated
    int ACL_REVISION1 = 1;
    int ACL_REVISION2 = 2;
    int ACL_REVISION3 = 3;
    int ACL_REVISION4 = 4;
    int MIN_ACL_REVISION = ACL_REVISION2;
    int MAX_ACL_REVISION = ACL_REVISION4;

    /* ACE types */
    byte ACCESS_ALLOWED_ACE_TYPE = 0x00;
    byte ACCESS_DENIED_ACE_TYPE = 0x01;
    byte SYSTEM_AUDIT_ACE_TYPE = 0x02;
    byte SYSTEM_ALARM_ACE_TYPE = 0x03;
    byte ACCESS_ALLOWED_COMPOUND_ACE_TYPE = 0x04;
    byte ACCESS_ALLOWED_OBJECT_ACE_TYPE = 0x05;
    byte ACCESS_DENIED_OBJECT_ACE_TYPE = 0x06;
    byte SYSTEM_AUDIT_OBJECT_ACE_TYPE = 0x07;
    byte SYSTEM_ALARM_OBJECT_ACE_TYPE = 0x08;
    byte ACCESS_ALLOWED_CALLBACK_ACE_TYPE = 0x09;
    byte ACCESS_DENIED_CALLBACK_ACE_TYPE = 0x0A;
    byte ACCESS_ALLOWED_CALLBACK_OBJECT_ACE_TYPE = 0x0B;
    byte ACCESS_DENIED_CALLBACK_OBJECT_ACE_TYPE = 0x0C;
    byte SYSTEM_AUDIT_CALLBACK_ACE_TYPE = 0x0D;
    byte SYSTEM_ALARM_CALLBACK_ACE_TYPE = 0x0E;
    byte SYSTEM_AUDIT_CALLBACK_OBJECT_ACE_TYPE = 0x0F;
    byte SYSTEM_ALARM_CALLBACK_OBJECT_ACE_TYPE = 0x10;
    byte SYSTEM_MANDATORY_LABEL_ACE_TYPE = 0x11;

    /* ACE inherit flags */
    byte OBJECT_INHERIT_ACE = 0x01;
    byte CONTAINER_INHERIT_ACE = 0x02;
    byte NO_PROPAGATE_INHERIT_ACE = 0x04;
    byte INHERIT_ONLY_ACE = 0x08;
    byte INHERITED_ACE = 0x10;
    byte VALID_INHERIT_FLAGS = 0x1F;


    byte CACHE_FULLY_ASSOCIATIVE = (byte) 0xFF;

    /**
     * Indicates committed pages for which physical storage has been allocated, either in memory or in the paging file on disk.
     */
    int MEM_COMMIT = 0x1000;

    /**
     * Indicates free pages not accessible to the calling process and available to be allocated.
     * For free pages, the information in the AllocationBase, AllocationProtect, Protect, and Type members is undefined.
     */
    int MEM_FREE = 0x10000;

    /**
     * Indicates reserved pages where a range of the process's virtual address space is reserved without any physical storage being allocated.
     * For reserved pages, the information in the Protect member is undefined.
     */
    int MEM_RESERVE = 0x2000;

    /**
     * Indicates that the memory pages within the region are mapped into the view of an image section.
     */
    int MEM_IMAGE = 0x1000000;

    /**
     * Indicates that the memory pages within the region are mapped into the view of a section.
     */
    int MEM_MAPPED = 0x40000;

    /**
     * Indicates that the memory pages within the region are private (that is, not shared by other processes).
     */
    int MEM_PRIVATE = 0x20000;

    byte SECURITY_DYNAMIC_TRACKING = (byte) 1;
    byte SECURITY_STATIC_TRACKING = (byte) 0;
    byte BOOLEAN_TRUE = (byte) 1;
    byte BOOLEAN_FALSE = (byte) 0;

    /*
     * Primary language IDs.
     */
    public static final int LANG_NEUTRAL = 0x00;
    public static final int LANG_INVARIANT = 0x7f;

    public static final int LANG_AFRIKAANS = 0x36;
    public static final int LANG_ALBANIAN = 0x1c;
    public static final int LANG_ARABIC = 0x01;
    public static final int LANG_ARMENIAN = 0x2b;
    public static final int LANG_ASSAMESE = 0x4d;
    public static final int LANG_AZERI = 0x2c;
    public static final int LANG_BASQUE = 0x2d;
    public static final int LANG_BELARUSIAN = 0x23;
    public static final int LANG_BENGALI = 0x45;
    public static final int LANG_BULGARIAN = 0x02;
    public static final int LANG_CATALAN = 0x03;
    public static final int LANG_CHINESE = 0x04;
    public static final int LANG_CROATIAN = 0x1a;
    public static final int LANG_CZECH = 0x05;
    public static final int LANG_DANISH = 0x06;
    public static final int LANG_DIVEHI = 0x65;
    public static final int LANG_DUTCH = 0x13;
    public static final int LANG_ENGLISH = 0x09;
    public static final int LANG_ESTONIAN = 0x25;
    public static final int LANG_FAEROESE = 0x38;
    public static final int LANG_FARSI = 0x29;
    public static final int LANG_FINNISH = 0x0b;
    public static final int LANG_FRENCH = 0x0c;
    public static final int LANG_GALICIAN = 0x56;
    public static final int LANG_GEORGIAN = 0x37;
    public static final int LANG_GERMAN = 0x07;
    public static final int LANG_GREEK = 0x08;
    public static final int LANG_GUJARATI = 0x47;
    public static final int LANG_HEBREW = 0x0d;
    public static final int LANG_HINDI = 0x39;
    public static final int LANG_HUNGARIAN = 0x0e;
    public static final int LANG_ICELANDIC = 0x0f;
    public static final int LANG_INDONESIAN = 0x21;
    public static final int LANG_ITALIAN = 0x10;
    public static final int LANG_JAPANESE = 0x11;
    public static final int LANG_KANNADA = 0x4b;
    public static final int LANG_KASHMIRI = 0x60;
    public static final int LANG_KAZAK = 0x3f;
    public static final int LANG_KONKANI = 0x57;
    public static final int LANG_KOREAN = 0x12;
    public static final int LANG_KYRGYZ = 0x40;
    public static final int LANG_LATVIAN = 0x26;
    public static final int LANG_LITHUANIAN = 0x27;
    public static final int LANG_MACEDONIAN = 0x2f;   // the Former Yugoslav Republic of Macedonia
    public static final int LANG_MALAY = 0x3e;
    public static final int LANG_MALAYALAM = 0x4c;
    public static final int LANG_MANIPURI = 0x58;
    public static final int LANG_MARATHI = 0x4e;
    public static final int LANG_MONGOLIAN = 0x50;
    public static final int LANG_NEPALI = 0x61;
    public static final int LANG_NORWEGIAN = 0x14;
    public static final int LANG_ORIYA = 0x48;
    public static final int LANG_POLISH = 0x15;
    public static final int LANG_PORTUGUESE = 0x16;
    public static final int LANG_PUNJABI = 0x46;
    public static final int LANG_ROMANIAN = 0x18;
    public static final int LANG_RUSSIAN = 0x19;
    public static final int LANG_SANSKRIT = 0x4f;
    public static final int LANG_SERBIAN = 0x1a;
    public static final int LANG_SINDHI = 0x59;
    public static final int LANG_SLOVAK = 0x1b;
    public static final int LANG_SLOVENIAN = 0x24;
    public static final int LANG_SPANISH = 0x0a;
    public static final int LANG_SWAHILI = 0x41;
    public static final int LANG_SWEDISH = 0x1d;
    public static final int LANG_SYRIAC = 0x5a;
    public static final int LANG_TAMIL = 0x49;
    public static final int LANG_TATAR = 0x44;
    public static final int LANG_TELUGU = 0x4a;
    public static final int LANG_THAI = 0x1e;
    public static final int LANG_TURKISH = 0x1f;
    public static final int LANG_UKRAINIAN = 0x22;
    public static final int LANG_URDU = 0x20;
    public static final int LANG_UZBEK = 0x43;
    public static final int LANG_VIETNAMESE = 0x2a;

    /*
     * Sublanguage IDs.
     *
     * The name immediately following SUBLANG_ dictates which primary
     * language ID that sublanguage ID can be combined with to form a
     * valid language ID.
     */
    public static final int SUBLANG_NEUTRAL = 0x00;    // language neutral
    public static final int SUBLANG_DEFAULT = 0x01;    // user default
    public static final int SUBLANG_SYS_DEFAULT = 0x02;    // system default

    public static final int SUBLANG_ARABIC_SAUDI_ARABIA = 0x01;    // Arabic (Saudi Arabia)
    public static final int SUBLANG_ARABIC_IRAQ = 0x02;    // Arabic (Iraq)
    public static final int SUBLANG_ARABIC_EGYPT = 0x03;    // Arabic (Egypt)
    public static final int SUBLANG_ARABIC_LIBYA = 0x04;    // Arabic (Libya)
    public static final int SUBLANG_ARABIC_ALGERIA = 0x05;    // Arabic (Algeria)
    public static final int SUBLANG_ARABIC_MOROCCO = 0x06;    // Arabic (Morocco)
    public static final int SUBLANG_ARABIC_TUNISIA = 0x07;    // Arabic (Tunisia)
    public static final int SUBLANG_ARABIC_OMAN = 0x08;    // Arabic (Oman)
    public static final int SUBLANG_ARABIC_YEMEN = 0x09;    // Arabic (Yemen)
    public static final int SUBLANG_ARABIC_SYRIA = 0x0a;    // Arabic (Syria)
    public static final int SUBLANG_ARABIC_JORDAN = 0x0b;    // Arabic (Jordan)
    public static final int SUBLANG_ARABIC_LEBANON = 0x0c;    // Arabic (Lebanon)
    public static final int SUBLANG_ARABIC_KUWAIT = 0x0d;    // Arabic (Kuwait)
    public static final int SUBLANG_ARABIC_UAE = 0x0e;    // Arabic (U.A.E)
    public static final int SUBLANG_ARABIC_BAHRAIN = 0x0f;    // Arabic (Bahrain)
    public static final int SUBLANG_ARABIC_QATAR = 0x10;    // Arabic (Qatar)
    public static final int SUBLANG_AZERI_LATIN = 0x01;    // Azeri (Latin)
    public static final int SUBLANG_AZERI_CYRILLIC = 0x02;    // Azeri (Cyrillic)
    public static final int SUBLANG_CHINESE_TRADITIONAL = 0x01;    // Chinese (Taiwan)
    public static final int SUBLANG_CHINESE_SIMPLIFIED = 0x02;    // Chinese (PR China)
    public static final int SUBLANG_CHINESE_HONGKONG = 0x03;    // Chinese (Hong Kong S.A.R., P.R.C.)
    public static final int SUBLANG_CHINESE_SINGAPORE = 0x04;    // Chinese (Singapore)
    public static final int SUBLANG_CHINESE_MACAU = 0x05;    // Chinese (Macau S.A.R.)
    public static final int SUBLANG_DUTCH = 0x01;    // Dutch
    public static final int SUBLANG_DUTCH_BELGIAN = 0x02;    // Dutch (Belgian)
    public static final int SUBLANG_ENGLISH_US = 0x01;    // English (USA)
    public static final int SUBLANG_ENGLISH_UK = 0x02;    // English (UK)
    public static final int SUBLANG_ENGLISH_AUS = 0x03;    // English (Australian)
    public static final int SUBLANG_ENGLISH_CAN = 0x04;    // English (Canadian)
    public static final int SUBLANG_ENGLISH_NZ = 0x05;    // English (New Zealand)
    public static final int SUBLANG_ENGLISH_EIRE = 0x06;    // English (Irish)
    public static final int SUBLANG_ENGLISH_SOUTH_AFRICA = 0x07;    // English (South Africa)
    public static final int SUBLANG_ENGLISH_JAMAICA = 0x08;    // English (Jamaica)
    public static final int SUBLANG_ENGLISH_CARIBBEAN = 0x09;    // English (Caribbean)
    public static final int SUBLANG_ENGLISH_BELIZE = 0x0a;    // English (Belize)
    public static final int SUBLANG_ENGLISH_TRINIDAD = 0x0b;    // English (Trinidad)
    public static final int SUBLANG_ENGLISH_ZIMBABWE = 0x0c;    // English (Zimbabwe)
    public static final int SUBLANG_ENGLISH_PHILIPPINES = 0x0d;    // English (Philippines)
    public static final int SUBLANG_FRENCH = 0x01;    // French
    public static final int SUBLANG_FRENCH_BELGIAN = 0x02;    // French (Belgian)
    public static final int SUBLANG_FRENCH_CANADIAN = 0x03;    // French (Canadian)
    public static final int SUBLANG_FRENCH_SWISS = 0x04;    // French (Swiss)
    public static final int SUBLANG_FRENCH_LUXEMBOURG = 0x05;    // French (Luxembourg)
    public static final int SUBLANG_FRENCH_MONACO = 0x06;    // French (Monaco)
    public static final int SUBLANG_GERMAN = 0x01;    // German
    public static final int SUBLANG_GERMAN_SWISS = 0x02;    // German (Swiss)
    public static final int SUBLANG_GERMAN_AUSTRIAN = 0x03;    // German (Austrian)
    public static final int SUBLANG_GERMAN_LUXEMBOURG = 0x04;    // German (Luxembourg)
    public static final int SUBLANG_GERMAN_LIECHTENSTEIN = 0x05;    // German (Liechtenstein)
    public static final int SUBLANG_ITALIAN = 0x01;    // Italian
    public static final int SUBLANG_ITALIAN_SWISS = 0x02;    // Italian (Swiss)
    public static final int SUBLANG_KASHMIRI_SASIA = 0x02;    // Kashmiri (South Asia)
    public static final int SUBLANG_KASHMIRI_INDIA = 0x02;    // For app compatibility only
    public static final int SUBLANG_KOREAN = 0x01;    // Korean (Extended Wansung)
    public static final int SUBLANG_LITHUANIAN = 0x01;    // Lithuanian
    public static final int SUBLANG_MALAY_MALAYSIA = 0x01;    // Malay (Malaysia)
    public static final int SUBLANG_MALAY_BRUNEI_DARUSSALAM = 0x02;    // Malay (Brunei Darussalam)
    public static final int SUBLANG_NEPALI_INDIA = 0x02;    // Nepali (India)
    public static final int SUBLANG_NORWEGIAN_BOKMAL = 0x01;    // Norwegian (Bokmal)
    public static final int SUBLANG_NORWEGIAN_NYNORSK = 0x02;    // Norwegian (Nynorsk)
    public static final int SUBLANG_PORTUGUESE = 0x02;    // Portuguese
    public static final int SUBLANG_PORTUGUESE_BRAZILIAN = 0x01;    // Portuguese (Brazilian)
    public static final int SUBLANG_SERBIAN_LATIN = 0x02;    // Serbian (Latin)
    public static final int SUBLANG_SERBIAN_CYRILLIC = 0x03;    // Serbian (Cyrillic)
    public static final int SUBLANG_SPANISH = 0x01;    // Spanish (Castilian)
    public static final int SUBLANG_SPANISH_MEXICAN = 0x02;    // Spanish (Mexican)
    public static final int SUBLANG_SPANISH_MODERN = 0x03;    // Spanish (Spain)
    public static final int SUBLANG_SPANISH_GUATEMALA = 0x04;    // Spanish (Guatemala)
    public static final int SUBLANG_SPANISH_COSTA_RICA = 0x05;    // Spanish (Costa Rica)
    public static final int SUBLANG_SPANISH_PANAMA = 0x06;    // Spanish (Panama)
    public static final int SUBLANG_SPANISH_DOMINICAN_REPUBLIC = 0x07; // Spanish (Dominican Republic)
    public static final int SUBLANG_SPANISH_VENEZUELA = 0x08;    // Spanish (Venezuela)
    public static final int SUBLANG_SPANISH_COLOMBIA = 0x09;    // Spanish (Colombia)
    public static final int SUBLANG_SPANISH_PERU = 0x0a;    // Spanish (Peru)
    public static final int SUBLANG_SPANISH_ARGENTINA = 0x0b;    // Spanish (Argentina)
    public static final int SUBLANG_SPANISH_ECUADOR = 0x0c;    // Spanish (Ecuador)
    public static final int SUBLANG_SPANISH_CHILE = 0x0d;    // Spanish (Chile)
    public static final int SUBLANG_SPANISH_URUGUAY = 0x0e;    // Spanish (Uruguay)
    public static final int SUBLANG_SPANISH_PARAGUAY = 0x0f;    // Spanish (Paraguay)
    public static final int SUBLANG_SPANISH_BOLIVIA = 0x10;    // Spanish (Bolivia)
    public static final int SUBLANG_SPANISH_EL_SALVADOR = 0x11;    // Spanish (El Salvador)
    public static final int SUBLANG_SPANISH_HONDURAS = 0x12;    // Spanish (Honduras)
    public static final int SUBLANG_SPANISH_NICARAGUA = 0x13;    // Spanish (Nicaragua)
    public static final int SUBLANG_SPANISH_PUERTO_RICO = 0x14;    // Spanish (Puerto Rico)
    public static final int SUBLANG_SWEDISH = 0x01;    // Swedish
    public static final int SUBLANG_SWEDISH_FINLAND = 0x02;    // Swedish (Finland)
    public static final int SUBLANG_URDU_PAKISTAN = 0x01;    // Urdu (Pakistan)
    public static final int SUBLANG_URDU_INDIA = 0x02;    // Urdu (India)
    public static final int SUBLANG_UZBEK_LATIN = 0x01;    // Uzbek (Latin)
    public static final int SUBLANG_UZBEK_CYRILLIC = 0x02;    // Uzbek (Cyrillic)

    /*
     * Sorting IDs.
     */
    public static final int SORT_DEFAULT = 0x0;     // sorting default

    public static final int SORT_JAPANESE_XJIS = 0x0;     // Japanese XJIS order
    public static final int SORT_JAPANESE_UNICODE = 0x1;     // Japanese Unicode order

    public static final int SORT_CHINESE_BIG5 = 0x0;     // Chinese BIG5 order
    public static final int SORT_CHINESE_PRCP = 0x0;     // PRC Chinese Phonetic order
    public static final int SORT_CHINESE_UNICODE = 0x1;     // Chinese Unicode order
    public static final int SORT_CHINESE_PRC = 0x2;     // PRC Chinese Stroke Count order
    public static final int SORT_CHINESE_BOPOMOFO = 0x3;     // Traditional Chinese Bopomofo order

    public static final int SORT_KOREAN_KSC = 0x0;     // Korean KSC order
    public static final int SORT_KOREAN_UNICODE = 0x1;     // Korean Unicode order

    public static final int SORT_GERMAN_PHONE_BOOK = 0x1;     // German Phone Book order

    public static final int SORT_HUNGARIAN_DEFAULT = 0x0;     // Hungarian Default order
    public static final int SORT_HUNGARIAN_TECHNICAL = 0x1;     // Hungarian Technical order

    public static final int SORT_GEORGIAN_TRADITIONAL = 0x0;     // Georgian Traditional order
    public static final int SORT_GEORGIAN_MODERN = 0x1;     // Georgian Modern order

    public static final int NLS_VALID_LOCALE_MASK = 0x000fffff;

}
