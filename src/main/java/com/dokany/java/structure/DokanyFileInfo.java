package com.dokany.java.structure;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import lombok.ToString;

/**
 * From dokan.h
 *
 * @see https://github.com/dokan-dev/dokany/blob/master/dokan/dokan.h
 */
@ToString
public class DokanyFileInfo extends Struct {

    public DokanyFileInfo(final Runtime runtime) {
        super(runtime);
    }

    /**
     * This can be used to carry information between operation. HANDLE This can be whatever type such as {@link com.sun.jna.platform.win32.WinNT.HANDLE},
     * {@link com.sun.jna.Structure}, {@link com.sun.jna.ptr.IntByReference}, {@link com.sun.jna.Pointer} that will help the implementation understand the request context of the
     * event.
     */
    public final UnsignedLong Context = new UnsignedLong();

    /**
     * Reserved. Used internally by Dokany library. Never modify.
     */
    public final UnsignedLong DokanContext = new UnsignedLong();

    /**
     * A pointer to {@link DeviceOptions} which was passed to {@link com.dokany.java.DokanyDriver.NativeLibrary#DokanMain}.
     */
    public DeviceOptions DokanOptions;

    /**
     * Process id for the thread that originally requested a given I/O operation.
     */
    public final UnsignedLong ProcessId = new UnsignedLong();

    /**
     * Requesting a directory file.
     * <p>
     * Must be set in {@link DokanyOperations#ZwCreateFile} if the file object appears to be a directory.
     */
    public final BYTE IsDirectory = new BYTE();

    /**
     * Flag if the file has to be delete during {@link DokanyOperations#Cleanup} event.
     */
    public final BYTE DeleteOnClose = new BYTE();

    /**
     * Read or write is paging IO.
     */
    public final BYTE PagingIo = new BYTE();

    /**
     * Read or write is synchronous IO.
     */
    public final BYTE SynchronousIo = new BYTE();

    /**
     * Read or write directly from data source without cache.
     */
    public final BYTE Nocache = new BYTE();

    /**
     * If true, write to the current end of file instead of using the Offset parameter.
     */
    public final BYTE WriteToEndOfFile = new BYTE();

    public final boolean isDirectory() {
        return IsDirectory.byteValue() != 0;
    }

    public final boolean deleteOnClose() {
        return DeleteOnClose.byteValue() != 0;
    }

    public final boolean pagingIo() {
        return PagingIo.byteValue() != 0;
    }

    public final boolean synchronousIo() {
        return SynchronousIo.byteValue() != 0;
    }

    public final boolean noCache() {
        return Nocache.byteValue() != 0;
    }

    public final boolean writeToEndOfFile() {
        return WriteToEndOfFile.byteValue() != 0;
    }
}