package io.dama.ffi.threads.all;

/*
 * verwendet getAllThreads() von org.apache.commons.lang3.ThreadUtils
 * 
 * => Apache Commons Lang: http://commons.apache.org/proper/commons-lang/
 *
 * aus der API-Dokumentation von Apache Commons Lang:
 * 
 * public static Collection<Thread> getAllThreads()
 * 
 * Gets all active threads (A thread is active if it has been started
 * and has not yet died).
 * 
 * Returns: all active threads. The collection returned is always
 * unmodifiable.
 * 
 * Throws: SecurityException - if the current thread cannot access the
 * system thread group SecurityException - if the current thread cannot
 * modify thread groups from this thread's thread group up to the system
 * thread group
 * 
 * 
 * http://commons.apache.org/proper/commons-lang/javadocs/api-release/
 * org/apache/commons/lang3/ThreadUtils.html#getAllThreads--
 * 
 */

import org.apache.commons.lang3.ThreadUtils;

public class AllThreads {
    static final int COLWIDTH = 16;

    public static void printFeatures(Object... objects) {
        var first = true;
        for (var o : objects) {
            if (!first) {
                System.out.print("\t");
            } else {
                first = false;
            }
            if (o instanceof String) {
                if (((String) o).trim().length() < ((String) o).length()) {
                    // rechtsbündig, falls String mit Whitespaces am Beginn
                    // (oder Ende)
                    System.out.printf("%" + COLWIDTH + "s", o.toString());
                } else {
                    // linksbündig, falls String ohne Whitespaces am Beginn
                    System.out.printf("%-" + COLWIDTH + "s", o.toString());
                }
            } else if (o instanceof Number) {
                // rechtsbündig, bei Zahlen
                System.out.printf("%" + COLWIDTH + "s", o.toString());
            } else {
                // sonst linksbündig
                System.out.printf("%-" + COLWIDTH + "s", o.toString());
            }
        }
        System.out.println("");
    }

    public static void main(String... args) {
        printFeatures("Name", " ID", " Priority", "isDaemon", "State", "Group");
        printFeatures("====", " ==", " ========", "========", "=====", "=====");
        for (var t : ThreadUtils.getAllThreads()) {
            printFeatures(t.getName(), t.getId(), t.getPriority(), t.isDaemon(),
                    t.getState(), t.getThreadGroup().getName());
        }
    }
}