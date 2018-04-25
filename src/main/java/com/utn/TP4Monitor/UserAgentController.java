package com.utn.TP4Monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
        return userAgentRepository.findById(id).orElseThrow(()->new ResourceNotFounException("user-agent","id",id));
    }

    /**
     *
     * @return the statistics of the monitor
     */
    @GetMapping(value = "/user-agents/statistics", produces = "application/json")
    public String getStatistics(){
        //This is used to create a Json output
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("Most used OS - Browsers combination", getMostUsedOSAndBrowsers().toString());
        json.put("Most used browsers", getMostUsedBrowsers().toString());
        json.put("Most used OS", getMostUsedOS().toString());

        return json.toString();
    }


    /**
     *
     * @return List with most used browsers
     */
    private List<String> getMostUsedBrowsers(){
        List<UserAgent> userAgents = userAgentRepository.findAll();

        Filter filter = (obj)->{
            UserAgent ua = (UserAgent) obj;
            return ua.getBrowserName();
        };

        return getMostAppeared(userAgents,filter);
    }
    /**
     *
     * @return List with most used OS
     */
    private List<String> getMostUsedOS(){
        List<UserAgent> userAgents = userAgentRepository.findAll();

        Filter filter = (obj)->{
            UserAgent ua = (UserAgent) obj;
            return ua.getOsName();
        };

        return getMostAppeared(userAgents,filter);
    }
    /**
     *
     * @return List with most used browsers and OS
     */
    private List<String> getMostUsedOSAndBrowsers(){
        List<UserAgent> userAgents = userAgentRepository.findAll();

        Filter filter = (obj)->{
            UserAgent ua = (UserAgent) obj;
            return ua.getBrowserName() + " - " + ua.getOsName();
        };

        return getMostAppeared(userAgents,filter);
    }

    /**
     * This method call the one in charge of get the occurrences and then
     * get the max, filter the map and return a list
     * Contributors with Collections and Stream : @PabloFino and @MartinBenvenuti
     * @param objects
     * @param filter
     * @return List of String with the 
     */
    private List<String> getMostAppeared(List objects, Filter filter){

        Map<String, Integer> occurrences = getOccurrences(objects,filter);
        // Get the max value
        Integer max = Collections.max(occurrences.values());
        //this line returns a stream
        return occurrences.entrySet().stream()
                // this filter only the entries which has the maximum value
                .filter(map -> map.getValue().equals(max))
                //this get only the key of the entries
                .map(Map.Entry::getKey)
                // and this return the mentioned keys as a list
                .collect(Collectors.toList());
    }

    /**
     * This method count the occurrences of Strings in a list
     * @param objects the objects to be counted
     * @param filter this is a functional interface that gets the desire string from the object
     * @return a map with the data as key and the occurrences as value
     */
    private Map<String,Integer> getOccurrences(List objects, Filter filter){
        Map <String, Integer> dataAndCount = new HashMap<>();

        for(Object obj : objects){
            String data = filter.filter(obj);
            if(dataAndCount.containsKey(data)){
                Integer dataCount = dataAndCount.get(data);
                dataAndCount.put(data,dataCount+1);
            }else{
                dataAndCount.put(data,1);
            }
        }
        return dataAndCount;
    }

    @FunctionalInterface
    public interface Filter {
        String filter(Object obj);
    }

}
