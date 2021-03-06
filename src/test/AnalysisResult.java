package test;

/* Created by AMXPC on 2017/4/5. */

import javax.servlet.http.HttpServletRequest;

public class AnalysisResult {
    private Integer totalUsecase;
    private Integer rightNum;
    private Integer wrongNum;
    private Double percentage;

    AnalysisResult(int tu, int rn, int wn, double pc) {
        this.totalUsecase = tu;
        this.rightNum = rn;
        this.wrongNum = wn;
        this.percentage = pc;
    }

    public void setAttribute(HttpServletRequest request) {
        request.setAttribute("analysisResult", this);
    }

    public Integer getTotalUsecase() {
        return totalUsecase;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public Integer getWrongNum() {
        return wrongNum;
    }

    public Double getPercentage() {
        return percentage;
    }
}
