package com.theironyard.command;

/**
 * Created by vasantia on 8/3/16.
 */
public class PostingCommand {

    private String title;

    private String description;

    private Integer salaryStart;

    private Integer salaryEnd;

    public PostingCommand() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(Integer salaryStart) {
        this.salaryStart = salaryStart;
    }

    public Integer getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(Integer salaryEnd) {
        this.salaryEnd = salaryEnd;
    }
}
