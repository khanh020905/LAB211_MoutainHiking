
package Model;


public class Moutain {
    private int code;
    private String name;
    private String provice;
    private String description;

    public Moutain(int code, String name, String provice, String description) {
        this.code = code;
        this.name = name;
        this.provice = provice;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return code + ", " + name + ", " + provice + ", " + description;
    }
}
