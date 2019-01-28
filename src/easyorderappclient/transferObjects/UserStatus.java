/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyorderappclient.transferObjects;

import java.io.Serializable;

/**
 * This enumeration is for users status. Includes the values ENABLED and
 * DISABLED.
 * @author Leticia Garcia
 */
public enum UserStatus implements Serializable {
	/**
	 * If user is in date base is ENABLED.
	 */
	ENABLED,
	/**
	 * If user isn't in date base is DISABLED .
	 */
	DISABLED;
}
