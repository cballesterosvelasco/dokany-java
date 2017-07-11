package com.dokany.java.structure;

import com.dokany.java.Win32FindStreamData;
import com.dokany.java.constants.NtStatus;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.annotations.In;
import jnr.ffi.byref.IntByReference;
import jnr.ffi.byref.LongLongByReference;
import jnr.posix.FileTime;
import jnr.posix.WString;
import jnr.posix.WindowsSecurityAttributes;
import jnr.posix.windows.WindowsFindData;
import lombok.NonNull;

/**
 * Dokany API callbacks interface. This is an internal class and should not used directly by code outside com.dokany.java.
 * <p>
 * A struct of callbacks that describe all Dokany API operation that will be called when Windows accesses the file system.
 * <p>
 * If an error occurs, return {@link NtStatus}.
 * <p>
 * All these callbacks can be set to <i>null</i> or return {@link NtStatus#NOT_IMPLEMENTED} if you don't want to support one of them. Be aware that returning such a value to
 * important callbacks such as {@link DokanyOperations.ZwCreateFile} or {@link DokanyOperations.ReadFile} would make the file system not working or unstable.
 * <p>
 * This is the same struct as <i>_DOKAN_OPERATIONS</i> (dokan.h) in the C++ version of Dokany.</remarks>
 */
public class DokanyOperations extends Struct {

    public DokanyOperations(final Runtime runtime) {
        super(runtime);
    }

    @NonNull
    public ZwCreateFile ZwCreateFile;
    @NonNull
    public Cleanup Cleanup;
    @NonNull
    public CloseFile CloseFile;
    @NonNull
    public ReadFile ReadFile;
    @NonNull
    public WriteFile WriteFile;
    @NonNull
    public FlushFileBuffers FlushFileBuffers;
    @NonNull
    public GetFileInformation GetFileInformation;
    @NonNull
    public FindFiles FindFiles;
    @NonNull
    public FindFilesWithPattern FindFilesWithPattern;
    @NonNull
    public SetFileAttributes SetFileAttributes;
    @NonNull
    public SetFileTime SetFileTime;
    @NonNull
    public DeleteFile DeleteFile;
    @NonNull
    public DeleteDirectory DeleteDirectory;
    @NonNull
    public MoveFile MoveFile;
    @NonNull
    public SetEndOfFile SetEndOfFile;
    @NonNull
    public SetAllocationSize SetAllocationSize;
    @NonNull
    public LockFile LockFile;
    @NonNull
    public UnlockFile UnlockFile;
    @NonNull
    public GetDiskFreeSpace GetDiskFreeSpace;
    @NonNull
    public GetVolumeInformation GetVolumeInformation;
    @NonNull
    public Mounted Mounted;
    @NonNull
    public Unmounted Unmounted;
    @NonNull
    public GetFileSecurity GetFileSecurity;
    @NonNull
    public SetFileSecurity SetFileSecurity;
    @NonNull
    public FindStreams FindStreams;

    /**
     * CreateFile is called each time a request is made on a file system object.
     * <p>
     * If the file is a directory, this method is also called. In this case, the method should return {@link NtStatus#SUCCESS} when that directory can be opened and
     * {@link DokanyFileInfo#isDirectory()}} has to be set to <i>true</i>. {@link com.dokany.java.structure.DokanyFileInfo#Context} can be used to store
     * data FileStream that can be retrieved in all other request related to the context.
     *
     * @see https://msdn.microsoft.com/en-us/library/windows/hardware/ff566424(v=vs.85).aspx for more information about the parameters of this callback.
     */
    @FunctionalInterface
    interface ZwCreateFile {
        /**
         * @param rawPath              Path requested by the Kernel on the File System.
         * @param securityContext      ??
         * @param rawDesiredAccess     ?? Permissions for file or directory.
         * @param rawFileAttributes    Provides attributes for files and directories. @see
         *                             https://msdn.microsoft.com/en-us/library/system.io.fileattributes(v=vs.110).aspx
         * @param rawShareAccess       Type of share access to other threads. Device and intermediate drivers usually set ShareAccess to zero, which gives the caller exclusive access to
         *                             the open file.
         * @param rawCreateDisposition
         * @param rawCreateOptions     Represents advanced options for creating a File object. @see
         * @param dokanyFileInfo       DokanyFileInfo with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull WindowsSecurityAttributes securityContext,
                int rawDesiredAccess,
                int rawFileAttributes,
                int rawShareAccess,
                int rawCreateDisposition,
                int rawCreateOptions,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Receipt of this request indicates that the last handle for a file object that is associated with the target device object has been closed (but, due to outstanding I/O
     * requests, might not have been released).
     * <p>
     * Cleanup is requested before @{link {@link DokanyOperations#CloseFile} is called.
     */
    @FunctionalInterface
    interface Cleanup {
        /**
         * @param rawPath
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         */
        @Delegate
        void callback(
                @NonNull WString rawPath,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * CloseFile is called at the end of the life of the context. Receipt of this request indicates that the last handle of the file object that is associated with the target
     * device object has been closed and released. All outstanding I/O requests have been completed or canceled.
     * <p>
     * CloseFile is requested after {@link DokanyOperations.Cleanup} is called. Anything remaining in {@link com.dokany.java.structure.DokanyFileInfo#Context} has to be cleared
     * before return.
     */
    @FunctionalInterface
    interface CloseFile {
        /**
         * @param rawPath
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         */
        @Delegate
        void callback(
                @NonNull WString rawPath,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * ReadFile callback on the file previously opened in {@link DokanyOperations#ZwCreateFile}. It can be called by different thread at the same time, therefore the read has to be
     * thread safe.
     */
    @FunctionalInterface
    interface ReadFile {
        /**
         * @param rawPath
         * @param rawBuffer
         * @param rawBufferLength
         * @param rawReadLength
         * @param rawOffset
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull Pointer rawBuffer,
                int rawBufferLength,
                @NonNull IntByReference rawReadLength,
                long rawOffset,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * WriteFile callback on the file previously opened in {@link DokanyOperations.ZwCreateFile} It can be called by different thread at the same time, therefore the write/context has to
     * be thread safe.
     */
    @FunctionalInterface
    interface WriteFile {
        /**
         * @param rawPath
         * @param rawBuffer
         * @param rawNumberOfBytesToWrite
         * @param rawNumberOfBytesWritten
         * @param rawOffset
         * @param dokanyFileInfo          {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull Pointer rawBuffer,
                int rawNumberOfBytesToWrite,
                @NonNull IntByReference rawNumberOfBytesWritten,
                long rawOffset,
                @NonNull DokanyFileInfo dokanyFileInfo);

    }

    /**
     * Clears buffers for this context and causes any buffered data to be written to the file.
     */
    @FunctionalInterface
    interface FlushFileBuffers {
        /**
         * @param rawPath
         * @param dokanyFileInfo {@link com.dokany.java.structure.DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Get specific informations on a file.
     */
    @FunctionalInterface
    interface GetFileInformation {
        /**
         * @param fileName
         * @param handleFileInfo
         * @param dokanyFileInfo {@link com.dokany.java.structure.DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString fileName,
                @NonNull ByHandleFileInfo handleFileInfo,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * List all files in the path requested.
     */
    @FunctionalInterface
    interface FindFiles {
        /**
         * @param rawPath
         * @param rawFillFindData
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull FillWin32FindData rawFillFindData,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Same as {@link DokanyOperations.FindFiles} but with a search pattern to filter the result.
     */
    @FunctionalInterface
    interface FindFilesWithPattern {
        /**
         * @param fileName
         * @param searchPattern
         * @param rawFillFindData
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString fileName,
                @NonNull WString searchPattern,
                @NonNull FillWin32FindData rawFillFindData,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Set file attributes on a specific file.
     */
    @FunctionalInterface
    interface SetFileAttributes {
        /**
         * @param rawPath
         * @param rawAttributes
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                int rawAttributes,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Set file times on a specific file.
     */
    @FunctionalInterface
    interface SetFileTime {
        /**
         * @param rawPath           path to file or directory
         * @param rawCreationTime   time of creation
         * @param rawLastAccessTime time of last access
         * @param rawLastWriteTime  time of last modification
         * @param dokanyFileInfo    {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull FileTime rawCreationTime,
                @NonNull FileTime rawLastAccessTime,
                @NonNull FileTime rawLastWriteTime,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Check if it is possible to delete a file.
     * <p>
     * You should NOT delete the file in this method, but instead you must only check whether you can delete the file or not, and return {@link NtStatus#SUCCESS} (when you can
     * delete it) or appropriate error codes such as {@link NtStatus#ACCESS_DENIED}, {@link NtStatus#OBJECT_PATH_NOT_FOUND}, {@link NtStatus#OBJECT_NAME_NOT_FOUND}.
     * <p>
     * {@link DokanyOperations.DeleteFile} will also be called with {@link DokanyFileInfo#DeleteOnClose}} set to <i>false</i> to notify the driver when the file is no longer
     * requested to be deleted.
     * <p>
     * When you return {@link NtStatus#SUCCESS}, you get a {@link DokanyOperations.Cleanup}> call afterwards with {@link DokanyFileInfo#DeleteOnClose} set to <i>true</i> and only
     * then you have to actually delete the file being closed.
     *
     * @see {@link DokanyOperations.DeleteDirectory}
     */
    @FunctionalInterface
    interface DeleteFile {
        /**
         * @param rawPath
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Check if it is possible to delete a directory.
     *
     * @see {@link DokanyOperations.DeleteFile} for more specifics.
     */
    @FunctionalInterface
    interface DeleteDirectory {
        /**
         * @param rawPath
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Move a file or directory to a new location.
     */
    @FunctionalInterface
    interface MoveFile {
        /**
         * @param rawPath
         * @param rawNewFileName
         * @param rawReplaceIfExisting
         * @param dokanyFileInfo       {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                @NonNull WString rawNewFileName,
                boolean rawReplaceIfExisting,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * SetEndOfFile is used to truncate or extend a file (physical file size).
     */
    @FunctionalInterface
    interface SetEndOfFile {
        /**
         * @param rawPath
         * @param rawByteOffset
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        long callback(
                @NonNull WString rawPath,
                long rawByteOffset,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * SetAllocationSize is used to truncate or extend a file.
     */
    @FunctionalInterface
    interface SetAllocationSize {
        /**
         * @param rawPath
         * @param rawLength
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                long rawLength,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Lock file at a specific offset and data length. This is only used if {@link com.dokany.java.constants.MountOption#FILELOCK_USER_MODE} is enabled.
     */
    @FunctionalInterface
    interface LockFile {
        /**
         * @param rawPath
         * @param rawByteOffset
         * @param rawLength
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                long rawByteOffset,
                long rawLength,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Unlock file at a specific offset and data length. This is only used if {@link com.dokany.java.constants.MountOption#FILELOCK_USER_MODE} is enabled.
     */
    @FunctionalInterface
    interface UnlockFile {
        /**
         * @param rawPath
         * @param rawByteOffset
         * @param rawLength
         * @param dokanyFileInfo {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                long rawByteOffset,
                long rawLength,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Retrieves information about the amount of space that is available on a disk volume, which is the total amount of space, the total amount of free space, and the total amount
     * of free space available to the user that is associated with the calling thread.
     * <p>
     * Neither this method nor {@link DokanyOperations.GetVolumeInformation} save the {@link com.dokany.java.structure.DokanyFileInfo#Context}. Before these methods are called,
     * {@link DokanyOperations.ZwCreateFile} may not be called. (ditto @{link DokanyOperations.CloseFile} and @{link DokanyOperations.Cleanup}).
     */
    @FunctionalInterface
    interface GetDiskFreeSpace {
        /**
         * @param freeBytesAvailable
         * @param totalNumberOfBytes
         * @param totalNumberOfFreeBytes
         * @param dokanyFileInfo         {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull LongLongByReference freeBytesAvailable,
                @NonNull LongLongByReference totalNumberOfBytes,
                @NonNull LongLongByReference totalNumberOfFreeBytes,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Retrieves information about the file system and volume associated with the specified root directory.
     * <p>
     * Neither this method nor {@link DokanyOperations.GetVolumeInformation} save the {@link com.dokany.java.structure.DokanyFileInfo#Context}. Before these methods are called,
     * {@link DokanyOperations.Create} may not be called. (ditto @{link DokanyOperations.CloseFile} and @{link DokanyOperations.Cleanup}).
     *
     * @see {@link FileSystemFeature#READ_ONLY_VOLUME} is automatically added to the <paramref name="features"/> if <see cref="DokanOptions.WriteProtection"/> was specified when
     * the volume was mounted.
     * <p>
     * If {@link NtStatus#NOT_IMPLEMENTED} is returned, the Dokany kernel driver use following settings by default:
     * <p>
     * <ul>
     * <li>rawVolumeSerialNumber = 0x19831116</li>
     * <li>rawMaximumComponentLength = 256</li>
     * <li>rawFileSystemFlags = CaseSensitiveSearch, CasePreservedNames, SupportsRemoteStorage, UnicodeOnDisk</li>
     * <li>rawFileSystemNameBuffer = NTFS</li>
     * </ul>
     */
    @FunctionalInterface
    interface GetVolumeInformation {
        /**
         * @param rawVolumeNameBuffer
         * @param rawVolumeNameSize
         * @param rawVolumeSerialNumber
         * @param rawMaximumComponentLength
         * @param rawFileSystemFlags
         * @param rawFileSystemNameBuffer
         * @param rawFileSystemNameSize
         * @param dokanyFileInfo            {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull Pointer rawVolumeNameBuffer,
                int rawVolumeNameSize,
                @NonNull IntByReference rawVolumeSerialNumber,
                @NonNull IntByReference rawMaximumComponentLength,
                @NonNull IntByReference /* FileSystemFeatures */ rawFileSystemFlags,
                @NonNull Pointer rawFileSystemNameBuffer,
                int rawFileSystemNameSize,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Is called when Dokany succeeded mounting the volume.
     */
    @FunctionalInterface
    interface Mounted {
        @Delegate
        long callback(
                @In @NonNull Pointer dokanyFileInfo);
    }

    /**
     * Is called when Dokany succeeded unmounting the volume.
     */
    @FunctionalInterface
    interface Unmounted {
        @Delegate
        long callback(
                @In @NonNull final Pointer dokanyFileInfo);
    }


    /**
     * Get specified information about the security of a file or directory.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link com.dokany.java.structure.DeviceOptions#Version}.
     */
    @FunctionalInterface
    interface GetFileSecurity {
        /**
         * @param rawPath
         * @param rawSecurityInformation
         * @param rawSecurityDescriptor
         * @param rawSecurityDescriptorLength
         * @param rawSecurityDescriptorLengthNeeded
         * @param dokanyFileInfo                    {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                int /* SecurityInformation */ rawSecurityInformation,
                @NonNull Pointer rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                @NonNull IntByReference rawSecurityDescriptorLengthNeeded,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Sets the security of a file or directory object.
     * <p>
     * Supported since version 0.6.0. You must specify the version in {@link com.dokany.java.structure.DeviceOptions#Version}.
     */
    @FunctionalInterface
    interface SetFileSecurity {
        /**
         * @param rawPath
         * @param rawSecurityInformation
         * @param rawSecurityDescriptor
         * @param rawSecurityDescriptorLength
         * @param dokanyFileInfo              {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull WString rawPath,
                int rawSecurityInformation,
                // @TODO: This is a pointer??
                @NonNull Pointer rawSecurityDescriptor,
                int rawSecurityDescriptorLength,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    @FunctionalInterface
    interface FillWin32FindData {
        /**
         * @param rawFillFindData
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         */
        @Delegate
        void fillWin32FindData(
                @NonNull WindowsFindData rawFillFindData,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Retrieve all NTFS Streams informations on the file. This is only called if {@link com.dokany.java.constants.MountOption#ALT_STREAM} is enabled.
     */
    @FunctionalInterface
    interface FindStreams {
        /**
         * @param rawPath
         * @param rawFillFindData
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         * @return {@link com.dokany.java.constants.NtStatus}
         */
        @Delegate
        long callback(
                @NonNull String rawPath,
                @NonNull FillWin32FindStreamData rawFillFindData,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     *
     *
     */
    @FunctionalInterface
    interface FillWin32FindStreamData {
        /**
         * @param rawFillFindData
         * @param dokanyFileInfo  {@link DokanyFileInfo} with information about the file or directory.
         */
        @Delegate
        void callback(
                @NonNull Win32FindStreamData rawFillFindData,
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    public interface Win32FindStreamDataInterface {
        public void length(long val);

        public char[] cFileName();
    }

}
