package com.utn.TP4Monitor;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserAgent {

    @Id
    @GeneratedValue
    private Long id;

    private String browserName;
    private String osName;


    /**
     * This Method will get the pure user-agent from a request header, parse it
     * and create an instance of the UserAgent class
     * @param ua
     * @return UserAgent obj
     */
    public static UserAgent parseUserAgent(String ua){
        eu.bitwalker.useragentutils.UserAgent parser;
        // This will create an UserAgent object of the dependency
        parser =  eu.bitwalker.useragentutils.UserAgent.parseUserAgentString(ua);
        // This will create an UserAgent object of the local class
        UserAgent userAgent = new UserAgent();
        userAgent.setBrowserName(parser.getBrowser().getName());
        userAgent.setOsName(parser.getOperatingSystem().getName());
        return userAgent;
    }

}
