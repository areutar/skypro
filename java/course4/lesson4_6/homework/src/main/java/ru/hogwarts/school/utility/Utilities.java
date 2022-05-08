package ru.hogwarts.school.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Utilities {
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static String getCurrentMethodName() {
        return StackWalker.getInstance()
                .walk(stackFrameStream -> stackFrameStream.skip(1).findFirst())
                .get()
                .getMethodName();
    }
}
