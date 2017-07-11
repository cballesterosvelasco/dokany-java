package com.dokany.java.structure;

import com.dokany.java.DokanyDriver;
import com.dokany.java.constants.MountOption;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Dokany mount options used to describe Dokany device behavior. This is the same structure as PDOKAN_OPTIONS (dokan.h) in the C++ version of Dokany.
 */
@ToString
public class DeviceOptions extends Struct {

    /**
     * Version of the Dokany features requested (version "123" is equal to Dokany version 1.2.3). Currently is 100.
     */
    public Signed16 Version = new Signed16();

    /**
     * Number of threads to be used internally by Dokany library. More thread will handle more event at the same time.
     */
    public Signed16 ThreadCount = new Signed16();

    /**
     * Features enable for the mount.
     *
     * @see {@link com.dokany.java.constants.MountOption}
     */
    public int Options;
    private EnumIntegerSet<MountOption> mountOptions;

    /**
     * FileSystem can store anything here
     */
    public long GlobalContext = 0L;

    /**
     * Mount point. Can be M:\\ (drive letter) or C:\\mount\\dokany (path in NTFS).
     */
    public String MountPoint = new UTF8String(4);

    /**
     * UNC name used for network volume.
     */
    public String UNCName = new UTF8String(4);

    /**
     * Max timeout in milliseconds of each request before Dokany gives up.
     */
    public UnsignedLong Timeout = new UnsignedLong();

    /**
     * Allocation Unit Size of the volume.
     */
    public UnsignedLong AllocationUnitSize = new UnsignedLong();

    /**
     * Sector Size of the volume.
     */
    public UnsignedLong SectorSize = new UnsignedLong();

    public DeviceOptions(final Runtime runtime) {
        super(runtime);
    }

    public DeviceOptions(
            final java.lang.String mountPoint,
            final short threadCount,
            final EnumIntegerSet<MountOption> mountOptions,
            final java.lang.String uncName,
            final long timeout,
            final long allocationUnitSize,
            final long sectorSize) {
        super(DokanyDriver.getRuntime());
        Version.set(100);
        MountPoint.set(mountPoint);
        ThreadCount.set(1);
        this.mountOptions = mountOptions;
        Options = mountOptions.toInt();
        if (Objects.nonNull(uncName)) {
            UNCName.set(uncName);
        } else {
            UNCName = null;
        }
        Timeout.set(timeout);
        AllocationUnitSize.set(allocationUnitSize);
        SectorSize.set(sectorSize);
    }

    public EnumIntegerSet<MountOption> getMountOptions() {
        return mountOptions;
    }

    //TODO: re-enable?
    protected List<java.lang.String> getFieldOrder() {
        return Arrays.asList(
                "Version",
                "ThreadCount",
                "Options",
                "GlobalContext",
                "MountPoint",
                "UNCName",
                "Timeout",
                "AllocationUnitSize",
                "SectorSize");
    }
}
