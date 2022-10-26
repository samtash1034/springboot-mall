package com.sam.dao.impl;

import com.sam.dao.UserDao;
import com.sam.dto.UserRegisterRequest;
import com.sam.model.User;
import com.sam.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO user(email, password, created_date, last_modified_date) " +
                "values(:email, :password, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, email, password, created_date, last_modified_date FROM user where user_id = :userId";

        Map<String ,Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> list = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(list.size() > 0){
            return list.get(0);
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT user_id, email, password, created_date, " +
                "last_modified_date FROM user WHERE email = :email";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(userList.size() > 0){
            return userList.get(0);
        }

        return null;
    }
}
