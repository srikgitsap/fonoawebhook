namespace com.sap.apple.srv;


// using {com.sap.apple.db as db} from '../db/model';

service WHService @(path:'invoice'){
    // entity tmpsrv as projection on db.tmp;
    action webhook(data: whbody) returns Boolean;
}

type whbody{
    event_type : String;
    delivered_at : String;
    resource_id: String;
    resource_url: String;
    webhook_id: String;
}