package com.knongdai.tinh.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.knongdai.tinh.entities.User;

@Repository
public interface UserRopository {
	final String c_user = "insert into phsar_user (username, password, role, description , user_hash ,user_image ) values(#{username},#{password},'ROLE_USER',#{description} , #{user_hash} , #{user_image})";
	final String r_user = "select * from phsar_user";
	final String r_id_user = "select * from phsar_user where user_pk_id = #{userid}";
	final String u_user = "update phsar_user set username = #{username}, password = #{password}, role = #{role}, description = #{description} where user_pk_id = #{userid}";
	final String d_user =  "delete from phsar_user where user_pk_id = #{userid}";
	
	@Insert(c_user)
	public int createUser(User users);
	
	@Results(value={
			@Result(property="userid", column="user_pk_id"),
			@Result(property="username", column="username"),
			@Result(property="password", column="password"),
			@Result(property="status", column="status"),
			@Result(property="description", column="description")
	})
	@Select(r_user)
	public ArrayList<User> getAllUsers();	
	
	@Results(value={
			@Result(property="userid", column="userid"),
			@Result(property="username", column="username"),
			@Result(property="password", column="password"),
			@Result(property="status", column="status"),
			@Result(property="description", column="description")
	})
	@Select(r_id_user)
	public ArrayList<User> getUserById(int userid);	
	
	@Update(u_user)
	public int updateUser(User users);
	
	@Delete(d_user)
	public int deleteUserById(int userid);
	
	@Select("SELECT user_pk_id, username, password, role FROM phsar_user WHERE username = #{username}")
	@Results(value={
			@Result(property="userid", column="user_pk_id")
	})
	public ArrayList<User> findUserByName(@Param("username") String username);
	
	@Select("SELECT COUNT(*) FROM phsar_user WHERE user_hash=#{userHash}")
	public int countUserByUserHash(String userHash);
	
	@Select("select * from phsar_user WHERE user_hash=#{userHash}")
	@Results(value={
			@Result(property="userid", column="user_pk_id")
	})
	public User findUserByUserHash(String userHash);
}
