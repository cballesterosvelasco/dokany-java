package com.dokany.java;

import com.dokany.java.constants.MountError;
import com.dokany.java.structure.DeviceOptions;
import com.dokany.java.structure.DokanyFileInfo;
import com.dokany.java.structure.DokanyOperations;
import com.dokany.java.structure.DokanyOperationsProxy;
import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.posix.WString;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * Main class to start and stop Dokany file system.
 */
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class DokanyDriver {
    @NonNull
    DeviceOptions deviceOptions;
    @NonNull
    DokanyFileSystem fileSystem;

    @NonNull
    private static final String DOKAN_DLL = "dokan1";

    public static NativeLibrary getLibrary() {
        return dokanyNativeLib;
    }

    public static Runtime getRuntime() {
        return Runtime.getRuntime(dokanyNativeLib);
    }

    @NonNull
    private static NativeLibrary dokanyNativeLib = LibraryLoader.create(NativeLibrary.class).load(DOKAN_DLL);

    public DokanyDriver(@NonNull final DeviceOptions deviceOptions, @NonNull final DokanyFileSystem fileSystem) {

        this.deviceOptions = deviceOptions;
        this.fileSystem = fileSystem;

        log.info("Dokany version: {}", getVersion());
        log.info("Dokany driver version: {}", getDriverVersion());
    }

    /**
     * Get driver version.
     *
     * @return
     * @see NativeLibrary#DokanDriverVersion()
     */
    public long getDriverVersion() {
        return dokanyNativeLib.DokanDriverVersion();
    }

    /**
     * Get Dokany version.
     *
     * @return
     * @see NativeLibrary#DokanVersion()
     */
    public long getVersion() {
        return dokanyNativeLib.DokanVersion();
    }

    /**
     * Get file system.
     *
     * @return
     */
    @NonNull
    public DokanyFileSystem getFileSystem() {
        return fileSystem;
    }

    /**
     * Calls {@link NativeLibrary#DokanMain(DeviceOptions, DokanyOperations)}. Has {@link java.lang.Runtime#addShutdownHook(Thread)} which calls {@link #shutdown()}
     */
    public void start() {
        try {
            val mountStatus = dokanyNativeLib.DokanMain(deviceOptions, new DokanyOperationsProxy(fileSystem));
            System.out.println("version: " + deviceOptions.Version);
            System.out.println(mountStatus);

            if (mountStatus < 0) {
                throw new IllegalStateException(MountError.fromInt(mountStatus).getDescription());
            }

            java.lang.Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    shutdown();
                }
            });
        } catch (final Throwable t) {
            log.warn("Error mounting", t);
            throw t;
        }
    }

    /**
     * Calls {@link #stop(String)}.
     */
    public void shutdown() {
        stop(deviceOptions.MountPoint.toString());
    }

    /**
     * Calls {@link NativeLibrary#DokanUnmount(char)} and {@link NativeLibrary#DokanRemoveMountPoint(WString)}
     *
     * @param mountPoint
     */
    public static void stop(@NonNull final String mountPoint) {
        log.info("Unmount and shutdown: {}", mountPoint);
        dokanyNativeLib.DokanUnmount(mountPoint.getBytes()[0]);
        dokanyNativeLib.DokanRemoveMountPoint(mountPoint);
    }

    /**
     * Native API to the kernel Dokany driver.
     */
    public static interface NativeLibrary {
        /**
         * Mount a new Dokany Volume. This function block until the device is unmount. If the mount fail, it will directly return {@link com.dokany.java.constants.MountError}.
         *
         * @param options    A {@link com.dokany.java.structure.DeviceOptions} that describe the mount.
         * @param operations Instance of {@link DokanyOperations} that will be called for each request made by the kernel.
         * @return {@link com.dokany.java.constants.MountError}.
         */
        int DokanMain(@NonNull DeviceOptions options, @NonNull DokanyOperations operations);

        /**
         * Get the version of Dokan.
         *
         * @return The version number without the dots.
         */
        long DokanVersion();

        /**
         * Get the version of the Dokany driver.
         *
         * @return The version number without the dots.
         */
        long DokanDriverVersion();

        /**
         * Unmount a Dokany device from a driver letter.
         *
         * @param driveLetter Driver letter to unmount.
         * @return True if device was unmounted or false (in case of failure or device not found).
         */

        boolean DokanUnmount(byte driveLetter);

        /**
         * Unmount a Dokany device from a mount point
         *
         * @param mountPoint Mount point to unmount
         *                   <ul>
         *                   <li>Z</li>
         *                   <li>Z:</li>
         *                   <li>Z:\\</li>
         *                   <li>Z:\MyMountPoint</li>
         *                   </ul>
         * @return if successfully unmounted mount point or not.
         */
        boolean DokanRemoveMountPoint(String mountPoint);

        /**
         * Extends the time out of the current IO operation in driver.
         *
         * @param timeout        Extended time in milliseconds requested.
         * @param dokanyFileInfo {@link com.dokany.java.structure.DokanyFileInfo} of the operation to extend.
         * @return if the operation was successful or not.
         */
        boolean DokanResetTimeout(long timeout, @NonNull DokanyFileInfo dokanyFileInfo);

        /**
         * Get the handle to Access Token.
         *
         * @param dokanyFileInfo {@link com.dokany.java.structure.DokanyFileInfo} of the operation.
         * @return A handle to the account token for the user on whose behalf the code is running.
         */
        Pointer DokanOpenRequestorToken(@NonNull DokanyFileInfo dokanyFileInfo);

        /**
         * Convert {@link DokanyOperations.ZwCreateFile} parameters to CreateFile parameters.
         *
         * @param fileAttributes            FileAttributes
         * @param createOptions             CreateOptions
         * @param createDisposition         CreateDisposition
         * @param outFileAttributesAndFlags
         * @param outCreationDisposition
         */
        void DokanMapKernelToUserCreateFileFlags(
                long fileAttributes,
                long createOptions,
                long createDisposition,
                int outFileAttributesAndFlags,
                int outCreationDisposition);

        /**
         * Convert IRP_MJ_CREATE DesiredAccess to generic rights.
         *
         * @param desiredAccess Standard rights to convert
         * @return New DesiredAccess with generic rights.
         * @see https://msdn.microsoft.com/windows/hardware/drivers/ifs/access-mask
         */
        // TODO: change return type and method parameter type to FileAccess
        long DokanMapStandardToGenericAccess(long desiredAccess);

        /**
         * Checks whether Name can match Expression.
         *
         * @param expression - Expression can contain wildcard characters (? and *)
         * @param name       - Name to check
         * @param ignoreCase - Case sensitive or not
         * @return result if name matches the expression
         */
        boolean DokanIsNameInExpression(@NonNull String expression, String name, boolean ignoreCase);

        /**
         * @param serviceName
         * @param serviceType
         * @param serviceFullPath
         * @return
         */
        boolean DokanServiceInstall(@NonNull String serviceName, int serviceType, @NonNull String serviceFullPath);

        /**
         * @param serviceName
         * @return
         */
        boolean DokanServiceDelete(@NonNull String serviceName);

        /**
         * @return
         */
        boolean DokanNetworkProviderInstall();

        /**
         * @return
         */
        boolean DokanNetworkProviderUninstall();

        /**
         * Determine if Dokany debug mode is enabled.
         *
         * @param mode
         * @return true if Dokany debug mode is enabled
         */
        boolean DokanSetDebugMode(int mode);

        /**
         * Enable or disable standard error output for Dokany.
         *
         * @param status
         */
        void DokanUseStdErr(boolean status);

        /**
         * Enable or disable Dokany debug mode.
         *
         * @param status
         */
        void DokanDebugMode(boolean status);

        /**
         * Get active Dokany mount points.
         *
         * @param fileAttributes - //TODO: ?? Allocate array of DOKAN_CONTROL
         * @param length         - Number of DOKAN_CONTROL instance in list.
         * @param uncOnly        - Get only instances that have UNC Name.
         * @param nbRead-        Number of instances successfully retrieved
         * @return List retrieved or not.
         */
        // TODO: Does this have proper params?
        boolean DokanGetMountPointList(long fileAttributes, long length, boolean uncOnly, long nbRead);

        /**
         * Convert Win32 error to NTStatus
         *
         * @param error - Win32 error to convert
         * @return NTStatus associated to the error
         * @see https://support.microsoft.com/en-us/kb/113996
         */
        // TODO: Switch to NTStatus return type
        long DokanNtStatusFromWin32(int error);
    }
}
