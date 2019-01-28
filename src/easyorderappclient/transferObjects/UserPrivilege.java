/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import java.io.Serializable;

/**
 * This enumeration is for users profiles. Includes the values USER and ADMIN.
 *
 * @author Leticia
 */
public enum UserPrivilege implements Serializable {

	/**
	 * The user is a regular user.
	 */
	USER,
	/**
	 * The user is a privileged user.
	 */
	ADMIN;
}