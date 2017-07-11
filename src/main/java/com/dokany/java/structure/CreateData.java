package com.dokany.java.structure;

import com.dokany.java.constants.CreationDisposition;
import com.dokany.java.constants.FileAttribute;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;

public class CreateData extends Struct {
    public int DesiredAccess;
    public FileAttribute FileAttributes;
    public int ShareAccess;
    public CreationDisposition CreateDisposition;
    public int CreateOptions;

    public CreateData(final Runtime runtime) {
        super(runtime);
    }
}
