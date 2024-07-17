package com.sap.apple.fonoawebhook.handlers;

import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.request.ParameterInfo;

import cds.gen.com.sap.apple.srv.whservice.WebhookContext;
import cds.gen.com.sap.apple.srv.whservice.WHService_;

@Component
@ServiceName(WHService_.CDS_NAME)
public class WHServiceHandler implements EventHandler {
    
    @Autowired
    ParameterInfo parameterInfo;

    @On(event = WebhookContext.CDS_NAME)
    public void webhook(WebhookContext context){
        sop(context.getData().toString());
        sop(parameterInfo.getHeader("X-Fonoa-Hmac-SHA256"));
        sop(getHMACString(context.getData().toString(), "8b144782eec54b159917c8c4a367d694"));
        context.setResult(true);
        context.setCompleted();
    }

    private String getHMACString(String data, String key){
        return new HmacUtils("HmacSHA256", key).hmacHex(data);
    }
    private void sop(String string){
        System.out.println(string);
    }
}
