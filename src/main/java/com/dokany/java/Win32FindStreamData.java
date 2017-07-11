package com.dokany.java;

import com.dokany.java.structure.DokanyOperations;
import com.dokany.java.structure.DokanyOperationsProxy;

public class Win32FindStreamData implements DokanyOperations.Win32FindStreamDataInterface {
    public long length;
    public char[] cFileName = new char[DokanyOperationsProxy.MAX_PATH + 36];

    @Override
    public void length(final long val) {
        length = val;
    }

    @Override
    public char[] cFileName() {
        return cFileName;
    }
}