package airbnb.view;

import airbnb.network.MyIOStream;

public class StartView {

    public void showView() throws Exception {
        for (; ; ) {
            int enter = getCommand();

            if (enter == 3) {
                break;
            }

            switch (enter) {
                case 1:
                    LoginView loginView = new LoginView(); // 응답 받으면 로그인 뷰 실행
                    loginView.showView();
                    break;
                case 2:
                    SignView signView = new SignView();
                    signView.showView();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│   Please Select (1) ~ (3)    │");
                    System.out.println("└──────────────────────────────┘");
                    break;
            }
        }
    }

    private int getCommand() {
        System.out.format("                                                    ┌───────────────────────────────────────────────────────────────────────────┐%n");
        System.out.format("                                                    │                               <Initial Menu>                              │%n");
        System.out.format("                                                    ├───────────────────────────────────────────────────────────────────────────┤%n");
        System.out.format("                                                    │                              [ 1. Login     ]                             │%n");
        System.out.format("                                                    │                              [ 2. Sign in   ]                             │%n");
        System.out.format("                                                    │                              [ 3. Terminate ]                             │%n");
        System.out.format("                                                    └───────────────────────────────────────────────────────────────────────────┘%n");
        System.out.print("                                                                                    Enter Command: ");

        return MyIOStream.sc.nextInt();
    }
}
