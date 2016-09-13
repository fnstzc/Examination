package com.fnst.examination.entity;

public class Param {
	private int id;
	private String level;
	
	private String choiceNum;
	private String vacantNum;
	private String questNum;
	
	private String choiceScore;
	private String vacantScore;
	private String questScore;
	
	private String repeatRate;
	
	private String javaRate;
	private String javascriptRate;
	private String htmlRate;
	private String cssRate;
	private String pythonRate;
	private String rubyRate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getChoiceNum() {
		return choiceNum;
	}
	public void setChoiceNum(String choiceNum) {
		this.choiceNum = choiceNum;
	}
	public String getVacantNum() {
		return vacantNum;
	}
	public void setVacantNum(String vacantNum) {
		this.vacantNum = vacantNum;
	}
	public String getQuestNum() {
		return questNum;
	}
	public void setQuestNum(String questNum) {
		this.questNum = questNum;
	}
	public String getChoiceScore() {
		return choiceScore;
	}
	public void setChoiceScore(String choiceScore) {
		this.choiceScore = choiceScore;
	}
	public String getVacantScore() {
		return vacantScore;
	}
	public void setVacantScore(String vacantScore) {
		this.vacantScore = vacantScore;
	}
	public String getQuestScore() {
		return questScore;
	}
	public void setQuestScore(String questScore) {
		this.questScore = questScore;
	}
	public String getRepeatRate() {
		return repeatRate;
	}
	public void setRepeatRate(String repeatRate) {
		this.repeatRate = repeatRate;
	}
	public String getJavaRate() {
		return javaRate;
	}
	public void setJavaRate(String javaRate) {
		this.javaRate = javaRate;
	}
	public String getJavascriptRate() {
		return javascriptRate;
	}
	public void setJavascriptRate(String javascriptRate) {
		this.javascriptRate = javascriptRate;
	}
	public String getHtmlRate() {
		return htmlRate;
	}
	public void setHtmlRate(String htmlRate) {
		this.htmlRate = htmlRate;
	}
	public String getCssRate() {
		return cssRate;
	}
	public void setCssRate(String cssRate) {
		this.cssRate = cssRate;
	}
	public String getPythonRate() {
		return pythonRate;
	}
	public void setPythonRate(String pythonRate) {
		this.pythonRate = pythonRate;
	}
	public String getRubyRate() {
		return rubyRate;
	}
	public void setRubyRate(String rubyRate) {
		this.rubyRate = rubyRate;
	}
    public Param(String level, String choiceNum, String vacantNum, String questNum, String choiceScore,
            String vacantScore, String questScore, String repeatRate, String javaRate, String javascriptRate,
            String htmlRate, String cssRate, String pythonRate, String rubyRate) {
        super();
        this.level = level;
        this.choiceNum = choiceNum;
        this.vacantNum = vacantNum;
        this.questNum = questNum;
        this.choiceScore = choiceScore;
        this.vacantScore = vacantScore;
        this.questScore = questScore;
        this.repeatRate = repeatRate;
        this.javaRate = javaRate;
        this.javascriptRate = javascriptRate;
        this.htmlRate = htmlRate;
        this.cssRate = cssRate;
        this.pythonRate = pythonRate;
        this.rubyRate = rubyRate;
    }
    public Param() {
    }
    @Override
    public String toString() {
        return "Param [id=" + id + ", level=" + level + ", choiceNum="
                + choiceNum + ", vacantNum=" + vacantNum + ", questNum=" + questNum + ", choiceScore="
                + choiceScore + ", vacantScore=" + vacantScore + ", questScore=" + questScore
                + ", repeatRate=" + repeatRate + ", javaRate=" + javaRate + ", javascriptRate=" + javascriptRate 
                + ", htmlRate=" + htmlRate + ", cssRate=" + cssRate + ", pythonRate=" + pythonRate 
                + ", rubyRate=" + rubyRate + "]";
    }
	
}
