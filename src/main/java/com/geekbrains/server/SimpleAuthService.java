package com.geekbrains.server;

import java.sql.SQLException;


public class SimpleAuthService implements AuthService {

    private final DataBaseAuth dataBaseAuth = DataBaseAuth.getExpress();

    public SimpleAuthService() throws SQLException, ClassNotFoundException {
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) throws SQLException {
            return dataBaseAuth.logPass(login, password);
    }
}
