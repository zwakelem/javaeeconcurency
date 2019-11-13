package com.app.rest;

import com.app.runnables.URLHealthProcessor;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.concurrent.TimeUnit;

@Path("urlCheck")
public class URLHealthResource {

    @Resource(lookup = "java:comp/DefaultManagedScheduledExecutorService")
    private ManagedScheduledExecutorService scheduledExecutorService;

    @GET
    public String checkHealthOfApp() {
        String message = "";
        scheduledExecutorService.schedule(new URLHealthProcessor(), 5, TimeUnit.SECONDS);
        message = "health check initiated";
        return message;
    }

}
