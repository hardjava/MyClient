import airbnb.network.SClient;

public class ClientApp {
    public static void main(String[] args) {
//        SClient sClient = new SClient("127.0.0.1", 5432);
        SClient sClient = new SClient("172.30.88.120", 5432);

        System.out.printf("-------------------------%n");
        sClient.run();
        System.out.printf("-------------------------%n");

    }
}
