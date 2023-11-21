package airbnb.view;

import java.util.Scanner;

public class SignView {

    Scanner sc = new Scanner(System.in);

    public void showView() {
        System.out.print("1. Host 2. Guest");
        int roleNum = sc.nextInt();
        String roleType;
        if (roleNum == 1)
            roleType = "HOST";
        else
            roleType = "GUEST";

        System.out.print("Enter New ID : ");
        String newID = sc.next();
        System.out.print("Enter New Passwd : ");
        String newPwd = sc.next();
        System.out.print("Enter Name : ");
        String newName = sc.next();
        System.out.print("Enter Birthday (YYYYMMDD) : ");
        String newBirthDay = sc.next();
        System.out.print("Enter User PhoneNumber");
        String newPhoneNumber = sc.next();

    }
}

/*
    public boolean isIdExist(String newID) {
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        List<UserDTO> userDTOS = userDAO.getAll();

        for (UserDTO userDTO : userDTOS) {
            if (userDTO.getLogin_id().equals(newID)) {
                return true;
            }
        }

        return false;
    }

 */
/*
    public void addUser(RoleType role, String newID, String newPwd, String newName, String newBirthday, String newPhoneNumber) { // return type 고민즁
        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        InsertUserDTO insertUserDTO = InsertUserDTO.builder()
                .login_id(newID)
                .login_pwd(newPwd)
                .user_name(newName)
                .user_birthday(newBirthday)
                .user_phone(newPhoneNumber)
                .role(role).build();
        userDAO.insertUser(insertUserDTO);
    }
 */