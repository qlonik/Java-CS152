class Data {
    private String name, comment;

    public Data(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }
    
    public Data(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}