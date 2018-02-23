package com.tetrasoft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingTriangles {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Error - specify input folder. Program will exit");
            System.exit(0);
        }
        long startTime = System.currentTimeMillis();
        String folderStr = args[0];
        File folder = new File(folderStr);
        if (!folder.isDirectory()) {
            System.err.println(folderStr + " is not a folder!");
            System.exit(0);
        }
        List<Triangle> triangles = new ArrayList<Triangle>(28000);

        for (File file : folder.listFiles()) {
            RandomAccessFile rfile = new RandomAccessFile(file, "r");
            byte[] bytes = new byte[(int) rfile.length()];
            rfile.readFully(bytes);
            if (bytes.length % 4 != 0) {
                System.err.println("file is broken: " + file.getAbsolutePath());
                //todo should we skip broken file or handle partially?
            }

            for (int i = 0; i < bytes.length - 3; i = i + 4) {
                Triangle triangle = new Triangle(bytes[i + 1], bytes[i + 2], bytes[i + 3]);
                triangles.add(triangle);
            }

        }

        Collections.sort(triangles, new Comparator<Triangle>() {
            public int compare(Triangle o1, Triangle o2) {
                if (o1.angle1 != o2.angle1)
                    return o1.angle1 - o2.angle1;
                else if (o1.angle2 != o2.angle2)
                    return o1.angle2 - o2.angle2;
                else if (o1.angle3 != o2.angle3)
                    return o1.angle3 - o2.angle3;
                return 0;
            }
        });

        try (FileOutputStream fos = new FileOutputStream("output.txt", false)) {
            for (Triangle triangle : triangles) {
                fos.write(("Triangle: " + triangle.angle1 + ", " + triangle.angle2 + ", " + triangle.angle3 + "\n").getBytes());

            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time: " + totalTime + " ms");

        long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println("heapSize: " + heapSize / 1024 + " KB");
    }

}

