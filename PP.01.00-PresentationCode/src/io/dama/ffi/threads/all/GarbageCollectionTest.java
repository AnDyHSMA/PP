package io.dama.ffi.threads.all;

import org.apache.commons.lang3.ThreadUtils;

class GarbageCollectionTest {
    String id;

    public GarbageCollectionTest(String id) {
        this.id = id;
    }

    @Override
    protected void finalize() {
        var self = Thread.currentThread();
        System.out.println(this.id + " finalized by Thread: " + self.getName()
                + " (" + self.getId() + ")");
    }

    public static void main(String... args) throws InterruptedException {
        var obj1 = new GarbageCollectionTest("obj1");
        var obj2 = new GarbageCollectionTest("obj2");

        System.out.println("Garbage Collection - 1 begin");
        System.gc();
        System.out.println("Garbage Collection - 1 end");

        obj1 = null;

        System.out.println("Garbage Collection - 2 begin");
        System.gc();
        System.out.println("Garbage Collection - 2 end");

        obj2 = null;

        for (var i = 0; i <= 10; i++) {
            for (var f : ThreadUtils.findThreadsByName("Reference Handler")) {
                System.out.println(
                        f.getName() + "(" + f.getId() + "): " + f.getState());
            }
            for (var f : ThreadUtils.findThreadsByName("Finalizer")) {
                System.out.println(
                        f.getName() + "(" + f.getId() + "): " + f.getState());
            }
            for (var f : ThreadUtils.findThreadsByName("Common-Cleaner")) {
                System.out.println(
                        f.getName() + "(" + f.getId() + "): " + f.getState());
            }
            Thread.sleep(1000);
        }

        System.out.println("Garbage Collection - 3 begin");
        System.gc();
        System.out.println("Garbage Collection - 3 end");
    }
}