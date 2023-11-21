package airbnb.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyObjectIOStream {
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;

    public MyObjectIOStream(ObjectOutputStream oos, ObjectInputStream ois) {
        MyObjectIOStream.oos = oos;
        MyObjectIOStream.ois = ois;
    }
}
