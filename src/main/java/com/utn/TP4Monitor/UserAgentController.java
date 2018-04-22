package com.utn.TP4Monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserAgentController {

    @Autowired
    UserAgentRepository userAgentRepository;

    /**
     * This method persists the U.A. of the api's visitors
     * @param ua
     * @return the actual user-agent
     */
    @GetMapping(produces = "application/json")
    public UserAgent saveUserAgent(@RequestHeader("User-Agent") String ua){
        UserAgent userAgent = UserAgent.parseUserAgent(ua);
        return userAgentRepository.save(userAgent);
    }

    /**
     *
     * @return all the U.A.
     */
    @GetMapping(value = "/user-agents", produces = "application/json")
    public List<UserAgent> getAll(){
        return userAgentRepository.findAll();
    }

    /**
     *
     * @param id
     * @return U.A. by id (provided by URI)
     */
    @GetMapping(value = "/user-agents/{id}",produces = "application/json")
    public UserAgent getById(@PathVariable(value = "id") Long id){
        return userAgentRepository.findById(id).get();
    }



}
