package tpo.enums;

public enum MessageType {
    MESSAGE("message"),
    ASSIGN_COMMAND("assign");

    private String type;

    MessageType(String type){
        this.type = type;
    }

    public  String getType(){
        return this.type;
    }
}

