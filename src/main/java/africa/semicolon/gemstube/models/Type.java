package africa.semicolon.gemstube.models;

public enum Type {

    VIDEO("video"),
    IMAGE("image");

    private final String value;

    Type(String value){
        this.value=value;
    }

    @Override
    public String toString(){
        return value;
    }
}
