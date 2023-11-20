import airbnb.network.SClient;

public class ClientApp {
    public static void main(String[] args) {
        SClient sClient = new SClient("127.0.0.1", 5432);
        sClient.run();

        System.out.printf("-------------------------%n");
    }
}
