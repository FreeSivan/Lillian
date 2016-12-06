package lillian.neural.service.serviceBean;

import java.util.List;

/**
 * Created by xiwen.yxw on 2016/11/14.
 */
public class ResMeta {

    private Integer style;

    private String singleResponse;

    private List<String> randomResponse;

    private String functionName;

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getSingleResponse() {
        return singleResponse;
    }

    public void setSingleResponse(String singleResponse) {
        this.singleResponse = singleResponse;
    }

    public List<String> getRandomResponse() {
        return randomResponse;
    }

    public void setRandomResponse(List<String> randomResponse) {
        this.randomResponse = randomResponse;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }
}
