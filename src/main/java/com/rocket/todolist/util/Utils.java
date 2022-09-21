package com.rocket.todolist.util;

import com.rocket.todolist.protobuf.Common;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    private static final SqlSessionFactory sSqlSessionFactory;

    static {
        final String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession() {
        return sSqlSessionFactory.openSession();
    }

    public static Common.Response getResponse(Common.ResponseCode code, String message) {
        Common.Response.Builder builder = Common.Response.newBuilder();
        builder.setCode(code);
        builder.setMessage(message);
        return builder.build();
    }
}
