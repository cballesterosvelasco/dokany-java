package com.dokany.java.structure;

import com.dokany.java.DokanyDriver;
import com.dokany.java.DokanyUtils;
import com.dokany.java.constants.FileAttribute;
import jetbrains.exodus.ArrayByteIterable;
import jetbrains.exodus.ByteIterable;
import jetbrains.exodus.bindings.IntegerBinding;
import jetbrains.exodus.bindings.LongBinding;
import jetbrains.exodus.util.LightOutputStream;
import jnr.posix.windows.WindowsFileTime;
import jnr.posix.windows.WindowsFindData;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * Combines {@link FullFileInfo} and {@link jnr.posix.windows.WindowsFindData}. This object will be stored in the Xodus fileInfo store.
 */
@Slf4j
@ToString
public class FullFileInfo extends ByHandleFileInfo {

    /**
     * If the dwFileAttributes member includes the FILE_ATTRIBUTE_REPARSE_POINT attribute, this member specifies the reparse point tag. Otherwise, this value is undefined and
     * should not be used. For more information see Reparse Point Tags.
     * <p>
     * IO_REPARSE_TAG_CSV (0x80000009) IO_REPARSE_TAG_DEDUP (0x80000013) IO_REPARSE_TAG_DFS (0x8000000A) IO_REPARSE_TAG_DFSR (0x80000012) IO_REPARSE_TAG_HSM (0xC0000004)
     * IO_REPARSE_TAG_HSM2 (0x80000006) IO_REPARSE_TAG_MOUNT_POINT (0xA0000003) IO_REPARSE_TAG_NFS (0x80000014) IO_REPARSE_TAG_SIS (0x80000007) IO_REPARSE_TAG_SYMLINK (0xA000000C)
     * IO_REPARSE_TAG_WIM (0x80000008)
     */
    private int dwReserved0;

    /**
     * Reserved for future use.
     */
    private int dwReserved1;

    public FullFileInfo(
            @NonNull final java.lang.String path,
            final long index,
            final EnumIntegerSet<FileAttribute> attributes,
            final int volumeSerialNumber) throws FileNotFoundException {

        // times automatically set to now by ByHandleFileInfo constructors
        this(path, index, attributes, volumeSerialNumber, null, null, null);
    }

    public FullFileInfo(
            @NonNull final java.lang.String path,
            final long index,
            final EnumIntegerSet<FileAttribute> attributes,
            final int volumeSerialNumber,
            final WindowsFileTime creationTime,
            final WindowsFileTime lastAccessTime,
            final WindowsFileTime lastWriteTime) throws FileNotFoundException {

        super(creationTime, lastAccessTime, lastWriteTime);

        if (Objects.isNull(path)) {
            throw new FileNotFoundException("path was null and thus file info could not be created");
        }

        filePath = new UTF8String(4);
        filePath.set(path);

        //TODO: re-enable
        //setIndex(index);
        setAttributes(attributes);
        dwVolumeSerialNumber = volumeSerialNumber;
        log.trace(super.toString());
    }

    public FullFileInfo(@NonNull final java.lang.String path, @NonNull final ByteIterable iterable) throws FileNotFoundException {
        if (Objects.isNull(path) || Objects.isNull(iterable)) {
            throw new FileNotFoundException("path or iterable was null and thus file info could not be created");
        }

        val iterator = iterable.iterator();

        log.debug("Creating FullFileInfo from infoStore: {}", path);

        filePath = new UTF8String(4);
        filePath.set(path);

        //TODO: re-enable
        //setSize(LongBinding.readCompressed(iterator), IntegerBinding.readCompressed(iterator), IntegerBinding.readCompressed(iterator));
        //setIndex(LongBinding.readCompressed(iterator), IntegerBinding.readCompressed(iterator), IntegerBinding.readCompressed(iterator));

        dwFileAttributes = IntegerBinding.readCompressed(iterator);

        setTimes(LongBinding.readCompressed(iterator), LongBinding.readCompressed(iterator), LongBinding.readCompressed(iterator));

        // always needs to be at least 1 for the file to show up
        dwNumberOfLinks = IntegerBinding.readCompressed(iterator);
        if (dwNumberOfLinks == 0) {
            dwNumberOfLinks = 1;
        }
        dwVolumeSerialNumber = IntegerBinding.readCompressed(iterator);

        dwReserved0 = IntegerBinding.readCompressed(iterator);
        dwReserved1 = IntegerBinding.readCompressed(iterator);
    }

    public ArrayByteIterable toByteIterable() {
        val output = new LightOutputStream();

        LongBinding.writeCompressed(output, fileSize);
        IntegerBinding.writeCompressed(output, nFileSizeHigh);
        IntegerBinding.writeCompressed(output, nFileSizeLow);

        LongBinding.writeCompressed(output, fileIndex);
        IntegerBinding.writeCompressed(output, nFileIndexHigh);
        IntegerBinding.writeCompressed(output, nFileIndexLow);

        IntegerBinding.writeCompressed(output, dwFileAttributes);

        LongBinding.writeCompressed(output, ftCreationTime.getLongValue());
        LongBinding.writeCompressed(output, ftLastAccessTime.getLongValue());
        LongBinding.writeCompressed(output, ftLastWriteTime.getLongValue());

        IntegerBinding.writeCompressed(output, dwNumberOfLinks);
        IntegerBinding.writeCompressed(output, dwVolumeSerialNumber);

        IntegerBinding.writeCompressed(output, dwReserved0);
        IntegerBinding.writeCompressed(output, dwReserved1);

        // do not store char[] for cFileName and cAlternateFileName since they do not have native bindings and it seems to be as efficient to recalculate every time via the String
        // path than to covert to store as a StringBinding and then back again

        return output.asArrayByteIterable();
    }

    /**
     * Simply casts this object to ByHandleFileInfo
     *
     * @return this (cast as ByHandleFileInfo)
     */
    @NonNull
    public ByHandleFileInfo toByHandleFileInfo() {
        return this;
    }

    /**
     * @return WIN32_FIND_DATA
     */
    public WindowsFindData toWin32FindData() {
        val cFileName = DokanyUtils.trimFrontSeparator(DokanyUtils.trimStrToSize(filePath.get(), 260)).toCharArray();
        val cAlternateFileName = new char[1];
        // val cAlternateFileName = Utils.trimFrontSlash(Utils.trimStrToSize(path, 14)).toCharArray();
        // TODO: Why does setting alternate name cause file name to show up twice??

        final WindowsFindData wfd = new WindowsFindData(DokanyDriver.getRuntime());

        return wfd;

        //TODO: fix
        /*
        return new WindowsFindData(
                dwFileAttributes,
                ftCreationTime, ftLastAccessTime, ftLastWriteTime,
                nFileSizeHigh, nFileSizeLow,
                dwReserved0, dwReserved1,
                cFileName, cAlternateFileName);
*/
    }
}