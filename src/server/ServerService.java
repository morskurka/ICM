package server;

import java.io.Serializable;
import java.util.List;

public class ServerService implements Serializable {

    private DatabaseService databaseService;
    private List params;

    public ServerService(DatabaseService databaseService, List params) {
        this.databaseService = databaseService;
        this.params = params;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public enum DatabaseService {
        Get_All_Requests,
        Insert_Into_Requirement,
        Update_Request_Status,
        Add_New_Request
    }
}