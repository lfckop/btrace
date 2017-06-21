/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.sun.btrace.samples;

import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnEvent;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * This script traces method entry into every method of every class in
 * javax.swing package! Think before using this script -- this will slow down
 * your app significantly!!
 */
@BTrace
public class AllMethodsLevels {
    /**
     * Capturing only methods invoked from javax.swing.JComponent class.
     */
    @OnMethod(clazz = "javax.swing.JComponent", method = "/.*/", enableAt = @Level("=0"))
    public static void l0(@ProbeMethodName(fqn = true) String probeMethod) {
        println("# " + probeMethod);
    }

    /**
     * This will intercept all the methods from javax.swing.* classes.
     */
    @OnMethod(clazz = "/javax\\.swing\\.*/", method = "/.*/", enableAt = @Level(">=1"))
    public static void l1(@ProbeMethodName(fqn = true) String probeMethod) {
        println("## " + probeMethod);
    }

    /**
     * Switch to level 0.
     */
    @OnEvent("l0")
    public static void setL0() {
        BTraceUtils.setInstrumentationLevel(0);
    }

    /**
     * Swtitch to level 1.
     */
    @OnEvent("l1")
    public static void setL1() {
        BTraceUtils.setInstrumentationLevel(1);
    }
}
