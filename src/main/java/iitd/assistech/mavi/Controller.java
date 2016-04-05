package iitd.assistech.mavi;

import iitd.assistech.mavi.processor.MessageProcessor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Nipun Gupta
 * @version 1.0
 * @since 19-Jun-2015
 */
@RequestMapping("/mavi")
@org.springframework.stereotype.Controller
public class Controller {
	
    @RequestMapping(method = RequestMethod.POST, value = "/postMessage")
    @ResponseBody
    public String post(@RequestParam String jsonMessage) {
    	return MessageProcessor.process(jsonMessage);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getMessage")
    @ResponseBody
    public String get(@RequestParam String jsonMessage) {
    	return MessageProcessor.process(jsonMessage);
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/putMessage")
    @ResponseBody
    public String put(@RequestParam String jsonMessage) {
    	return MessageProcessor.process(jsonMessage);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteMessage")
    @ResponseBody
    public String delete(@RequestParam String jsonMessage) {
    	return MessageProcessor.process(jsonMessage);
    }
    
}
