package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.Jobs;
import com.dto.Registration;

public class UserDao {
	public static final String QUERY_8 = "SELECT * FROM JOBPORTAL_JOBVIEWDETAILS WHERE CATEGORY=? AND LOCATION=?";

	private Connection conn;

	public UserDao(Connection conn) {

		this.conn = conn;
	}

	public List<Jobs> filterJobs(Jobs j) {
		List<Jobs> list = new ArrayList<Jobs>();

		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_8);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j = new Jobs();
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				//j.setPdate(rs.getTimestamp(7) + "");
				list.add(j);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}



//CREATE TABLE "SYSTEM"."JOBPORTAL_REGISTRATIONDETAILS" 
// (	"ID" NUMBER NOT NULL ENABLE, 
//	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"EMAIL" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
//	"PASSWORD" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"QUALIFICATION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"DESIGNATION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"ROLE" VARCHAR2(20 BYTE) NOT NULL ENABLE
// ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
//NOCOMPRESS LOGGING
//STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
//PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
//BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
//TABLESPACE "SYSTEM" ;

