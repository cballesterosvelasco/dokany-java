package com.dokany.java;

import com.dokany.java.constants.NtStatus;
import com.dokany.java.structure.DokanyFileInfo;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.annotations.Delegate;
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

    // @Override
    //TODO: re-enable?
    /*-
    protected List<String> getFieldOrder() {
        return Arrays.asList(
                "ZwCreateFile",
                "Cleanup",
                "CloseFile",
                "ReadFile",
                "WriteFile",
                "FlushFileBuffers",
                "GetFileInformation",
                "FindFiles",
                "FindFilesWithPattern",
                "SetFileAttributes",
                "SetFileTime",
                "DeleteFile",
                "DeleteDirectory",
                "MoveFile",
                "SetEndOfFile",
                "SetAllocationSize",
                "LockFile",
                "UnlockFile",
                "GetDiskFreeSpace",
                "GetVolumeInformation",
                "Mounted",
                "Unmounted",
                "GetFileSecurity",
                "SetFileSecurity",
                "FindStreams");
    }
    */
/*
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
    */
    @NonNull
    public Mounted Mounted;
    @NonNull
    public Unmounted Unmounted;
    /*
    @NonNull
    public GetFileSecurity GetFileSecurity;
    @NonNull
    public SetFileSecurity SetFileSecurity;
    @NonNull
    public FindStreams FindStreams;
*/

    /**
     * Is called when Dokany succeeded mounting the volume.
     */
    @FunctionalInterface
    interface Mounted {
        @Delegate
        long mounted(
                @NonNull DokanyFileInfo dokanyFileInfo);
    }

    /**
     * Is called when Dokany succeeded unmounting the volume.
     */
    @FunctionalInterface
    interface Unmounted {
        @Delegate
        long unmounted(
                @NonNull final DokanyFileInfo dokanyFileInfo);
    }


}
