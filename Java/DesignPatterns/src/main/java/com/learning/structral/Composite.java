package com.learning.structral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有时又叫作整体-部分（Part-Whole）模式，它是一种将对象组合成树状的层次结构的模式，用来表示“整体-部分”的关系，使用户对单个对象和组合对象具有一致的访问性，属于结构型设计模式。
 */
public class Composite {
    public static void main(String[] args) {
        Folder folderA = new Folder("A");
        Folder folderB = new Folder("B");
        Folder folderC = new Folder("C");

        File fileA = new TextFile("a");
        File fileB = new VideoFile("b");
        File fileC = new ImageFile("c");
        File fileD = new TextFile("d");

        /**
         * --A--a
         * --+--B
         * -----+--b
         * -----+--c
         * -----+--C
         * --------+--d
         */

        folderA.add(fileA, folderB);
        folderB.add(fileB, fileC, folderC);
        folderC.add(fileD);

        folderA.show();
    }
    static abstract class File {
        public String name;

        public File(String name) {
            this.name = name;
        }

        public abstract void show();
    }

    static class Folder extends File {
        private List<File> files;

        public Folder(String name) {
            super(name);
            files = new ArrayList<>();
        }

        @Override
        public void show() {
            System.out.println("现在在目录 " + name);
            for (File file : files) {
                file.show();
            }
        }

        public void add(File... files) {
            this.files.addAll(Arrays.asList(files));
        }

        public void remove(File... files) {
            this.files.removeAll(Arrays.asList(files));
        }
    }

    static class TextFile extends File {
        public TextFile(String name) {
            super(name);
        }

        @Override
        public void show() {
            System.out.println("显示文本文件 " + name);
        }
    }

    static class VideoFile extends File {
        public VideoFile(String name) {
            super(name);
        }

        @Override
        public void show() {
            System.out.println("显示视频文件 " + name);
        }
    }

    static class ImageFile extends File {
        public ImageFile(String name) {
            super(name);
        }

        @Override
        public void show() {
            System.out.println("显示图像文件 " + name);
        }
    }
}
