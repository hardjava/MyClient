package airbnb.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MyIOStream {
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static Scanner sc;

    public MyIOStream(ObjectOutputStream oos, ObjectInputStream ois, Scanner sc) {
        MyIOStream.oos = oos;
        MyIOStream.ois = ois;
        MyIOStream.sc = sc;
    }
}
