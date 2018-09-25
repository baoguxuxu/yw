package com.yinwang.information.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 问卷小区中间表
 * 
 * @author wjl
 * @email bushuo@163.com
 * @date 2018-05-30 13:40:29
 */
public class QuestionnairePlotDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//资讯ID
	private Long questionnaireId;
	//小区ID
	private Long plotId;

	
	public QuestionnairePlotDO(){};
	public QuestionnairePlotDO(Long questionnaireId,Long plotId){
		this.questionnaireId = questionnaireId;
		this.plotId = plotId;
	};
	
	
	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：资讯ID
	 */
	public void setQuestionnaireId(Long questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	/**
	 * 获取：资讯ID
	 */
	public Long getQuestionnaireId() {
		return questionnaireId;
	}
	/**
	 * 设置：小区ID
	 */
	public void setPlotId(Long plotId) {
		this.plotId = plotId;
	}
	/**
	 * 获取：小区ID
	 */
	public Long getPlotId() {
		return plotId;
	}
}
